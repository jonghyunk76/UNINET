package stw.Interface.sms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SiebelDataController {
	private static Connection siebelCon;
	private static PreparedStatement siebelPstmt;
	private static ResultSet siebelRs;
	
	private static final String postWorkSql =     "UPDATE SIEBEL.CX_SDK_SMS_SEND "
				                                + "   SET SEND_STATUS = 1, LAST_UPD = SYSDATE "
				                                + " WHERE SEND_STATUS = 0 "
				                                + "   AND ROW_ID = ? ";
	
	private static final String gatherSql =       "SELECT * FROM SIEBEL.CX_SDK_SMS_SEND WHERE SEND_STATUS = 0";
	
    private static final String applyResultSql =  "UPDATE SIEBEL.CX_SDK_SMS_SEND "
		                                        + "   SET LAST_UPD = SYSDATE, "
		                                        + "       RESULT = ? , "
		                                        + "       TCS_RESULT = ? "
		                                        + " WHERE ROW_ID = ? "
		                                        + "   AND RESULT < 2 " ;
	
	public SiebelDataController(Connection siebelConnection) throws Exception{
	    siebelCon = siebelConnection;
	} 
	
	public SendRecord[] gatherMessage() throws Exception{
		List<SendRecord> recordSet = new ArrayList<SendRecord>();
		try{
		      
		      siebelPstmt = siebelCon.prepareStatement(gatherSql);
		      siebelRs = siebelPstmt.executeQuery();
		      
		      siebelPstmt = siebelCon.prepareStatement(postWorkSql);
		      
		      while(siebelRs.next()){
		    	  SendRecord record = new SendRecord();
		          record.setUser_id(siebelRs.getString("USER_ID"));
		          record.setSchedule_type(siebelRs.getInt("SCHEDULE_TYPE"));
		          record.setSubject(siebelRs.getString("SUBJECT"));
		          record.setSms_msg(siebelRs.getString("SMS_MSG"));
		          record.setCallback_url(siebelRs.getString("CALLBACK_URL"));
		          record.setNow_date(siebelRs.getString("NOW_DATE"));
		          record.setSend_date(siebelRs.getString("SEND_DATE"));
		          record.setCallback(siebelRs.getString("CALLBACK"));
		          record.setDest_type(siebelRs.getInt("DEST_TYPE"));
		          record.setDest_count( siebelRs.getInt("DEST_COUNT"));
		          record.setDest_info(siebelRs.getString("DEST_INFO"));
		          record.setKt_office_code(siebelRs.getString("KT_OFFICE_CODE"));
		          record.setCdr_id(siebelRs.getString("CDR_ID"));
		          record.setReserved1(siebelRs.getString("RESERVED1"));
		          record.setReserved2(siebelRs.getString("RESERVED2"));
		          record.setReserved3(siebelRs.getString("RESERVED3"));
		          record.setReserved4(siebelRs.getString("RESERVED4"));
		          record.setReserved5(siebelRs.getString("RESERVED5"));
		          record.setReserved6(siebelRs.getString("RESERVED6"));
		          record.setSend_status(siebelRs.getInt("SEND_STATUS"));
		          record.setSend_count(siebelRs.getInt("SEND_COUNT"));
		    	  recordSet.add(record);
		    	  
		    	  siebelPstmt.setString(1, siebelRs.getString("ROW_ID"));
		    	  siebelPstmt.executeUpdate();
		      }
		}catch(Exception e){
			throw e;
		}finally{
			siebelPstmt.close();
			siebelRs.close();
		}
	      SendRecord[] smsRecordSet = new SendRecord[recordSet.size()];
	      Iterator<SendRecord> it = recordSet.iterator();
	      int i=0;
	      while(it.hasNext()){
	    	  smsRecordSet[i] = (SendRecord) it.next();
	    	  i++;
	      }
	      return smsRecordSet;
	}
	
	public void applyResult(ReceiveRecord[] recordSet) throws Exception{
		try{
			siebelPstmt = siebelCon.prepareStatement(applyResultSql);
			for(ReceiveRecord record : recordSet){
				siebelPstmt.setInt(1, record.getResult());
				siebelPstmt.setInt(2, record.getTcs_result());
				siebelPstmt.setString(3, record.getReserved());
				siebelPstmt.executeUpdate();
			}
		}catch(Exception e){
			throw e;
		}finally{
			siebelPstmt.close();
		}
	}

}
