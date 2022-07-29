package stw.Interface.sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SMSDataController {
	private static Connection smsCon;
	private static PreparedStatement smsPstmt;
	private static ResultSet smsRs;
	
	private static String sendSql;
	private static final String tmpSql = "INSERT INTO SDK_SMS_SEND ("
														          + "  USER_ID        "  //1    
														          + " ,SCHEDULE_TYPE  "  //2    
														          + " ,SUBJECT        "  //3    
														          + " ,SMS_MSG        "  //4    
														          + " ,CALLBACK_URL   "  //5    
														          + " ,NOW_DATE       "  //6    
														          + " ,SEND_DATE      "  //7    
														          + " ,CALLBACK       "  //8    
														          + " ,DEST_TYPE      "  //9    
														          + " ,DEST_COUNT     "  //10    
														          + " ,DEST_INFO      "  //11    
														          + " ,KT_OFFICE_CODE "  //12    
														          + " ,CDR_ID         "  //13    
														          + " ,RESERVED1      "  //14    
														          + " ,RESERVED2      "  //15    
														          + " ,RESERVED3      "  //16    
														          + " ,RESERVED4      "  //17    
														          + " ,RESERVED5      "  //18    
														          + " ,RESERVED6      "  //19    
														          + " ,SEND_STATUS    "  //20    
														          + " ,SEND_COUNT     "  //21    
				                                                  + ") VALUES ( " ;

	private static final String receiveSql  = "SELECT T1.RESERVED1,               "
											+ "       T2.RESULT,                  "
									        + "       T2.TCS_RESULT               "
								            + "  FROM SDK_SMS_REPORT T1,          "
								            + "       SDK_SMS_REPORT_DETAIL T2    "
								            + " WHERE T1.MSG_ID = T2.MSG_ID       "
								            + "   AND T1.RESERVED6 = 'Siebel CRM' "
								            + "   AND SUBSTRING(T1.SEND_DATE,1,8) = CONVERT(VARCHAR,GETDATE(),112)";

	
	public SMSDataController(Connection smsConnection) throws Exception {
		smsCon = smsConnection;
		if (sendSql == null){
			sendSql = tmpSql;
		       for(int i=0; i<21; i++){
		    	   if(i==0){
		    		   sendSql = sendSql + " ? ";
		    	   }else{
		    		   sendSql = sendSql + " , ? ";
		    	   }
		       }
		       sendSql = sendSql + " )";
		}
	}
	  
	public void sendMessage (SendRecord[] recordSet) throws Exception {
		try{
			smsPstmt = smsCon.prepareStatement(sendSql);
			for(SendRecord record : recordSet){
				smsPstmt.setString(1, record.getUser_id());
		  	    smsPstmt.setInt(2, record.getSchedule_type());
		  	    smsPstmt.setString(3, record.getSubject());
		  	    smsPstmt.setString(4, record.getSms_msg());
		  	    smsPstmt.setString(5, record.getCallback_url());
		  	    smsPstmt.setString(6, record.getNow_date());
		  	    smsPstmt.setString(7, record.getSend_date());
		  	    smsPstmt.setString(8, record.getCallback());
		  	    smsPstmt.setInt(9, record.getDest_type());
		  	    smsPstmt.setInt(10, record.getDest_count());
		  	    smsPstmt.setString(11, record.getDest_info());
		  	    smsPstmt.setString(12, record.getKt_office_code());
		  	    smsPstmt.setString(13, record.getCdr_id());
		  	    smsPstmt.setString(14, record.getReserved1()); 
		  	    smsPstmt.setString(15, record.getReserved2());
		  	    smsPstmt.setString(16, record.getReserved3());
		  	    smsPstmt.setString(17, record.getReserved4());
		  	    smsPstmt.setString(18, record.getReserved5());
		  	    smsPstmt.setString(19, record.getReserved6());
		  	    smsPstmt.setInt(20, record.getSend_status());
		  	    smsPstmt.setInt(21, record.getSend_count());
		  	    smsPstmt.executeUpdate();
			}
		}catch(Exception e){
			throw e;
		}finally{
			smsPstmt.close();
		}
	}
	
	public ReceiveRecord[] receiveResult() throws Exception{
		try{
			smsPstmt = smsCon.prepareStatement(receiveSql);
			smsRs = smsPstmt.executeQuery();
			List<ReceiveRecord> recordSet = new ArrayList<ReceiveRecord>();
		    while(smsRs.next()){
		    	 ReceiveRecord record = new ReceiveRecord();
		    	 record.setReserved(smsRs.getString("RESERVED1"));
		    	 record.setResult(smsRs.getInt("RESULT"));
		    	 record.setTcs_result(smsRs.getInt("TCS_RESULT"));
		    	 recordSet.add(record);
		    }
			ReceiveRecord[] receiveRecordSet = new ReceiveRecord[recordSet.size()];
			Iterator<ReceiveRecord> it = recordSet.iterator();
			int i=0;
			while(it.hasNext()){
				receiveRecordSet[i] = it.next();
				i++;
			}
			return receiveRecordSet;
		}catch(Exception e){
			throw e;
		}finally{
			smsPstmt.close();
		}
	}

}
