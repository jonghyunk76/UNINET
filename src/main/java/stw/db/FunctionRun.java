package stw.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import stw.eai.common.Utils;

import com.siebel.data.SiebelPropertySet;

public class FunctionRun {
	protected static Log logger = LogFactory.getLog(FunctionRun.class);

	public void executePrepareCall(SiebelPropertySet psInput, SiebelPropertySet psOutput) {
		Connection con 			  = null;
		ResultSet rs			  = null;
		PreparedStatement pstmt	  = null;
		SiebelPropertySet psClone = new SiebelPropertySet();
		SiebelPropertySet psOutChild = null;
		String sParameter 		= "";
		String sFunction 		= "";
		String sConnectionDB 	= "";
		String sPropSetName 	= "";
//		String sServer 			= "";
		String sSql 			= "";
		int i 					= 1;
		int cols 				= 0;
		try {
			sFunction 	  = nChkValue(psInput.getProperty("FUNCTION_NAME"));
//			sConnectionDB = nChkValue(psInput.getProperty("DATABASE_TNS").toUpperCase());
			sConnectionDB = "STW";
			if (sFunction == "")
				throw new Exception("Function name doesn't exists.");
			if (sConnectionDB == "") {
				throw new Exception("Connection DB doesn't exists.");
			}

			if (psInput.getChildCount() > 0) {
				psClone = psInput.getChild(0).copy();
				sPropSetName = psClone.getFirstProperty();
				while (sPropSetName != "") {
					if (sParameter != "")
						sParameter = "," + sParameter;
					sParameter = sPropSetName + " => '" + psClone.getProperty(sPropSetName) + "'" + sParameter;
					sPropSetName = psClone.getNextProperty();
				}
				//logger.debug("Search Parameter : " + sParameter);
			}

			//sServer = ConfigPropServerInfo.getValue("KE.SERVER");
			//logger.debug("Server Info : " + sServer);
			
			//con = DataSource.getConnection(sConnectionDB + "." + sServer);
			con = DataSource.getConnection(sConnectionDB);
			sSql = "Select " + sFunction + "(" + sParameter+ ") AS RESULT_VALUE from dual";
			//sSql = "Select SIEBEL.month('2014-01-02') AS RESULT_VALUE from dual";
			//logger.debug("Execute SQL : " + sSql);
			System.out.println("Sql : " + sSql);
			pstmt = con.prepareStatement(sSql);
			pstmt.execute();
			
			rs = pstmt.getResultSet();

			cols = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				psOutChild = new SiebelPropertySet();
				psOutChild.setType("Function");
				for (int j = 1; j <= cols; ++j) {
					//psOutChild.setProperty(rs.getMetaData().getColumnName(j), nChkValue(rs.getString(j)));
					psOutChild.setProperty("RESULT_VALUE", nChkValue(rs.getString(j)));
					//logger.debug("RESULT_VALUE : " + i + " ��° Line : " + j + " ��° cols : " + rs.getMetaData().getColumnName(j) + "  " + j + " ��° Data : " + rs.getString(j));
					System.out.println("RESULT_VALUE : " + i + " ��° Line : " + j + " ��° cols : " + rs.getMetaData().getColumnName(j) + "  " + j + " ��° Data : " + rs.getString(j));
				}
				psOutput.addChild(psOutChild);
				++i;
			}
			psOutput.setType("Response");
			psOutput.setProperty("REPLY_CODE", "OK");
			psOutput.setProperty("REPLY_MSG", "");
			
		} catch (Exception e) {
			psOutput.setType("Response");
			psOutput.setProperty("REPLY_CODE", "FunctionRunError");
			psOutput.setProperty("REPLY_MSG", e.getMessage());

			logger.error(Utils.getStackTrace(e));
		} finally {
				if(rs!=null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(psOutChild!=null)psOutChild=null;
				if(psClone!=null)psClone=null;
			try {
				DataSource.close(pstmt);
				DataSource.close(con);
			} catch (Exception localException2) {
			}
		}
	}
	private static String nChkValue(String value) {
		if ((value == null) || (value.isEmpty())) {
			value = "";
		}
		return value;
	}

	public static void main(String[] args) {
		SiebelPropertySet input = new SiebelPropertySet();
		SiebelPropertySet inputChild = new SiebelPropertySet();
		SiebelPropertySet output = new SiebelPropertySet();

		String methodName = "Search_DBFunction_01_0";

		if (methodName.equals("Search_DBFunction_01_0")) {
			input.setType("Request");
			input.setProperty("DATABASE_TNS", "STW.DEV");
			input.setProperty("FUNCTION_NAME", "SIEBEL.month");
			
			inputChild.setType("Parameter");
			inputChild.setProperty("date_var", "2014-01-02");
			input.addChild(inputChild);
			
		}
		new FunctionRun().executePrepareCall(input, output);
	}
}