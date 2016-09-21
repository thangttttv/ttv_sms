package com.ttv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

public class SwapDAO {

	public List<UserMobile> getUserMobile() {
		UserMobile user = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		List<UserMobile> listNews = new ArrayList<UserMobile>();
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("SELECT id,user_id,mobile,code_validate,type_message FROM  ms_user_mobile  Where is_sended = 0 LIMIT 0, 50 ");
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				user = new UserMobile();
				user.id = rs.getInt("id");
				user.user_id = rs.getInt("user_id");
				user.mobile = rs.getString("mobile");
				user.code_validate = rs.getString("code_validate");
				user.type_message = rs.getInt("type_message");
			listNews.add(user);
			}
		
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			SwapPool.attemptClose(conn,preStmt,rs);
		}
		return listNews;
	}
	
	public void deleteUserMobile(int user_id) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("DELETE  FROM  ms_user_mobile  WHERE user_id = ?  ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,user_id);
			conn.setAutoCommit(false);
			int kq = preStmt.executeUpdate();
			if(kq>0) conn.commit();
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			SwapPool.attemptClose(conn,preStmt);
		}
	}
	
	public void updateMobileValidate(int user_id) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("UPDATE  ms_user  SET verify_mobile = 1 Where id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,user_id);
			conn.setAutoCommit(false);
			int kq = preStmt.executeUpdate();
			if(kq>0) conn.commit();
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			SwapPool.attemptClose(conn,preStmt);
		}
	}
	
	public void updateUserMobileSended(int user_id) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("UPDATE  ms_user_mobile  SET is_sended = 1,update_date=NOW() Where user_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,user_id);
			conn.setAutoCommit(false);
			int kq = preStmt.executeUpdate();
			if(kq>0) conn.commit();
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			SwapPool.attemptClose(conn,preStmt);
		}
	}
	
	public String updateUserMobileCode(int user_id) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		String times ="";
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("UPDATE  ms_user_mobile  SET code_validate = ?,update_date=NOW() Where user_id = ?  ");
			preStmt = conn.prepareStatement(strSQL.toString());
			times = Calendar.getInstance().getTimeInMillis()+"";
			times = times.substring(times.length()-5);
			preStmt.setString(1,times);
			preStmt.setInt(2,user_id);
			conn.setAutoCommit(false);
			int kq = preStmt.executeUpdate();
			if(kq>0) conn.commit();
			conn.setAutoCommit(true);
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			SwapPool.attemptClose(conn,preStmt);
		}
		return times;
	}
}
