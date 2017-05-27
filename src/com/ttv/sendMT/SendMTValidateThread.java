package com.ttv.sendMT;


import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.axis.AxisFault;
import org.apache.axis.encoding.Base64;

import com.ttv.bluesea.MTReceiverPortStub;
import com.ttv.dao.SwapDAO;
import com.ttv.dao.UserMobile;

public class SendMTValidateThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			sendMT();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void sendMT(){
		SwapDAO swapDAO = new SwapDAO();
		List<UserMobile> listMobile = new ArrayList<UserMobile>();
		listMobile = swapDAO.getUserMobile();
		try {
			
			URL myURL = new URL("http://sms.8x77.vn:8077/mt-services/MTService?wsdl");
			MTReceiverPortStub mtReceiverPortStub = new MTReceiverPortStub(myURL, null);
			int i = 0;
			String Service_ID = "TRAODOIDI";
			String Command_Code = "TRAODOIDI";
			String Message_Type = "0";
			String Content_Type ="0";
			while(i<listMobile.size()){
				UserMobile  userMobile = listMobile.get(i);
				String message64 = "";String message ="";
				if(userMobile.type_message==0){
					// update code validate
					//String code = swapDAO.updateUserMobileCode(userMobile.user_id);
					//send code validate
					message ="Ma so xac nhan dien thoai cua ban tai TraoDoiDi la:"+userMobile.code_validate;
					message64 =Base64.encode(message.getBytes());
				}else{
					message ="He thong chat da stop luc:"+Calendar.getInstance().getTime().toString();
					message64 =Base64.encode(message.getBytes());
				}
				
				int kq = mtReceiverPortStub.sendMT(userMobile.mobile, message64, Service_ID, Command_Code, Message_Type, userMobile.id+"", "1"
						, "1", "0", Content_Type);
				System.out.println(Calendar.getInstance().getTime().toString()+"-->KQSend:"+kq+"-Mobile:"+userMobile.mobile+"-"+message);
				
				//update had sended
				if(kq==0&&userMobile.type_message==0)swapDAO.updateCodeValidateSendedByMobile(userMobile.mobile);
				else swapDAO.deleteUserMobile(userMobile.user_id);
				i++;
			}
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	
	public static void main(String[] args) {
		Thread sendThread = new Thread(new SendMTValidateThread());
		sendThread.start();
	}
}
