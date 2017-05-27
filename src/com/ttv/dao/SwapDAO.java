package com.ttv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	public void updateCodeValidateSendedByMobile(String mobile) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("UPDATE  ms_user_mobile  SET is_sended = 1,update_date=NOW() Where mobile = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setString(1,mobile);
			conn.setAutoCommit(false);
			preStmt.executeUpdate();
			conn.commit();
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
			if("".equalsIgnoreCase(times)||times==null)  times ="45878";
			System.out.println(Calendar.getInstance().getTime().toString()+"-->"+user_id+":"+times);
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
	
	
	public Map<Integer,ChatMessage> getChatMessageQueue() {
		ChatMessage message = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		Map<Integer,ChatMessage> mapMessage = new HashMap<Integer,ChatMessage>();
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("SELECT 	id,app_client_id, fuID, tuID,pID,p_swap_ids, content, price, quantity, ship,"
					+ "file_path,type_message, time_receive, time_send FROM  vtc_swaphub.ms_chat_message_queue"
					+ " WHERE   is_send_sms = 0  AND ((UNIX_TIMESTAMP()*1000-time_receive)/(1000*60) )> 5 Order BY id limit 100 ");
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				message = new ChatMessage();
				message.id = rs.getInt("id");
				message.app_client_id = rs.getInt("app_client_id");
				message.pID = rs.getInt("pID");
				message.fuID = rs.getInt("fuID");
				message.tuID = rs.getInt("tuID");
				message.content = rs.getString("content");
				message.price = rs.getDouble("price");
				message.quantity = rs.getInt("quantity");
				message.file_path = rs.getString("file_path");
				message.type_message = rs.getInt("type_message");
				mapMessage.put(message.pID , message);
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
		return mapMessage;
	}
	
	public Product getProduct(int id) {
		Product product = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer(" SELECT * FROM ms_product WHERE id = "+id);
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				product = new Product();
				product.id = rs.getInt("id");
				product.user_id = rs.getInt("user_id");
				product.title = rs.getString("title");
				product.cate_id = rs.getInt("cate_id");
				product.cate_parent_id = rs.getInt("cate_parent_id");
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
		return product;
	}
	
	public User getUser(int id) {
		User user = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer(" SELECT * FROM ms_user WHERE id =  "+id);
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();
			if (rs.next()) {				
				user = new User();
				user.id = rs.getInt("id");
				user.fullname = rs.getString("fullname");
				user.mobile = rs.getString("mobile");
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
		return user;
	}
	
	public void updateSendSMSChatQueue(int product_id,int fUID,int tuID) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("UPDATE  ms_chat_message_queue  SET is_send_sms = 1 Where pID = ? And fuID = ? And tuID = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,product_id);
			preStmt.setInt(2,fUID);
			preStmt.setInt(3,tuID);
			conn.setAutoCommit(false);
			preStmt.executeUpdate();
			conn.commit();
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
	
	public void deleteChatMessageInQueue(int chat_id) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("DELETE  FROM  ms_chat_message_queue  WHERE id = ?  ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,chat_id);
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
	
	public List<TCoidAction> getListTCoidAction() {
		TCoidAction tCoidAction = null;
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		ResultSet rs = null;
		List<TCoidAction> listNews = new ArrayList<TCoidAction>();
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("SELECT 	id, app_client_id, action_user_id, invite_code, "
					+ "action_code,tcoid, ip, STATUS, data_log, create_date "
					+ "FROM vtc_swaphub.ms_tcoid_action_log Where status = 1 LIMIT 0, 50");
			preStmt = conn.prepareStatement(strSQL.toString());
			rs = preStmt.executeQuery();
			while (rs.next()) {				
				tCoidAction = new TCoidAction();
				tCoidAction.id = rs.getInt("id");
				tCoidAction.app_client_id = rs.getInt("app_client_id");
				tCoidAction.action_user_id = rs.getInt("action_user_id");
				tCoidAction.invite_code = rs.getString("invite_code");
				tCoidAction.action_code = rs.getString("action_code");
				tCoidAction.tcoid = rs.getDouble("tcoid");
				tCoidAction.ip = rs.getString("ip");
				tCoidAction.data_log = rs.getString("data_log");
				tCoidAction.create_date = rs.getString("create_date");
			listNews.add(tCoidAction);
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
	
	public void inactiveStatusTCoidAction(int tcoid_id) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("UPDATE  ms_tcoid_action_log  SET status = 0 Where id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,tcoid_id);
			conn.setAutoCommit(false);
			preStmt.executeUpdate();
			conn.commit();
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
	
	public int insertTcoidTransaction(int action_id,int from_user_id,int to_user_id,double tcoid,int type_tran,String note) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		int kq=0;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("INSERT INTO vtc_swaphub.ms_tcoid_transaction "
					+ " (action_id,from_user_id,to_user_id,tcoid,type_tran,note,create_date) "
					+ " VALUES (?,?,?,?,?, ?,NOW());");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setInt(1,action_id);
			preStmt.setInt(2,from_user_id);
			preStmt.setInt(3,to_user_id);
			preStmt.setDouble(4,tcoid);
			preStmt.setInt(5,type_tran);
			preStmt.setString(6,note);
			conn.setAutoCommit(false);
			preStmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			kq=1;
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			SwapPool.attemptClose(conn,preStmt);
		}
		
		return kq;
	}
	
	public void insertNotify(int object_id,int to_user,int from_user,String content, 
			String icon,String url,int object_type,String create_user) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("INSERT INTO vtc_swaphub.ms_notify (object_id,to_user,from_user,content, "
					+ " icon,url,object_type,STATUS,time_sent,create_date,create_user) "
					+ " VALUES (?, ?, ?, ? ,? ,? , ?, 1, NOW(), NOW() ,? );");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setInt(1,object_id);
			preStmt.setInt(2,to_user);
			preStmt.setInt(3,from_user);
			preStmt.setString(4,content);
			preStmt.setString(5,icon);
			preStmt.setString(6,url);
			preStmt.setInt(7,object_type);
			preStmt.setString(8,create_user);
			
			conn.setAutoCommit(false);
			preStmt.executeUpdate();
			conn.commit();
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
	
	public double sumTCoidActionByUserID(int user_id) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		double tcoid = 0;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("SELECT SUM(tcoid*type_tran) as tcoid FROM ms_tcoid_transaction WHERE to_user_id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setInt(1,user_id);
			
			
			ResultSet rs = preStmt.executeQuery();
			if(rs.next())
			 tcoid = rs.getDouble("tcoid");
			
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			SwapPool.attemptClose(conn,preStmt);
		}
		return tcoid;
	}
	
	public void updateTCoidUser(int user_id,double tcoid) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("UPDATE  ms_user  SET tcoid = ? Where id = ? ");
			preStmt = conn.prepareStatement(strSQL.toString());
			preStmt.setDouble(1,tcoid);
			preStmt.setInt(2,user_id);
			
			conn.setAutoCommit(false);
			preStmt.executeUpdate();
			conn.commit();
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
	
	public User getUserByInviteCode(String invite_code) {
		PreparedStatement preStmt = null;
		StringBuffer strSQL = null;
		Connection conn = null;
		User user = null;
		try {
			conn = SwapPool.getConnection();
			strSQL = new StringBuffer("SELECT * FROM ms_user WHERE invite_code = ?");
			preStmt = conn.prepareStatement(strSQL.toString());
			
			preStmt.setString(1,invite_code);
			
			
			ResultSet rs = preStmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.id = rs.getInt("id");
			}
			 
			
		} catch (NoSuchElementException nse) {
			nse.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			SwapPool.attemptClose(conn,preStmt);
		}
		return user;
	}
}
