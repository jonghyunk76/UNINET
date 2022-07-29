package stw.Interface.sms;

import java.sql.Connection;
import java.sql.SQLException;

import stw.Interface.common.DBConnector;
import stw.Interface.common.DBSource;

public class SMSSender {
	  private static Connection siebelConnection = null;
	  private static Connection smsConnection= null;
	  private static SiebelDataController siebelDC= null;
	  private static SMSDataController smsDC= null;

	  public static String work() {
		  String result = "";
		try {
			// Job Order 
			// 1) SiebelDataController.gatherMsg
			// 2) SMSDataController.sendMessage
			// 3) SMSDataController.receiveResult
			// 4) SiebelDataController.applyResult
			siebelConnection = DBConnector.getConnection(DBSource.SIEBEL);
			smsConnection = DBConnector.getConnection(DBSource.SMS);
			siebelDC = new SiebelDataController(siebelConnection);
			smsDC = new SMSDataController(smsConnection);
			
			//1. Gathering SIEBEL SMS Data 
			SendRecord[] smsRecordSet = siebelDC.gatherMessage();
			result = "1. Send Message (Count = " + smsRecordSet.length + ") \n";
			
			//2. Insert SMS Server
			try {
				smsDC.sendMessage(smsRecordSet);
				DBConnector.commit(smsConnection);
				result = result + "2. SMS Insert Success \n";
			} catch (Exception error) {
				DBConnector.rollback(siebelConnection);
				DBConnector.rollback(smsConnection);
				result = result + "2. SMS Insert Error And Send Message Rollback (" + error.toString() + ") \n";
			}
			
			//3. Select From SMS Server
			ReceiveRecord[] reciveRecordSet = smsDC.receiveResult();
			result = result + "3. SMS Recevie (Count = " + reciveRecordSet.length+ ") \n";
			
            //4. Update SIEBEL Data
			siebelDC.applyResult(reciveRecordSet);
			DBConnector.commit(siebelConnection);
			result = result + "4. Applied Siebel \n";
			
		} catch (Exception e) {
			result = result + "Error Raised. \n" + e.toString();
		} finally {
			try {
				DBConnector.close(siebelConnection);
				DBConnector.close(smsConnection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	  }
	  
	  public static void main(String[] args) {
		  System.out.println(SMSSender.work());
	  }
}
