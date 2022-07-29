package stw.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.siebel.data.SiebelPropertySet;

public class ProcedureRun {
//	private static final Logger logger = Logger.getLogger(ProcedureRun.class);

	public void executePrepareCall(SiebelPropertySet psInput, SiebelPropertySet psOutput) {
		Connection con 				= null;
		CallableStatement cstmt 	= null;
		SiebelPropertySet psClone 	= new SiebelPropertySet();
		SiebelPropertySet psProp 	= null;
		ResultSet rs 				= null;
		String sParameter 			= "";
		String sStoreProcedure 		= "";
		String sConnectionDB 		= "";
		String sPropSetName 		= "";
//		String sServer 				= "";
		String sSql 				= "";
		int i 						= 1;
		int cols 					= 0;
		int Props					= 0;
		try {
			sStoreProcedure = nChkValue(psInput.getProperty("PROCEDURE_NAME"));
//			sConnectionDB = nChkValue(psInput.getProperty("DATABASE_TNS").toUpperCase());
			sConnectionDB 	= "STW";
			if (sStoreProcedure == "")
				throw new Exception("Procedure name doesn't exists.");
			if (sConnectionDB == "") {
				throw new Exception("Connection DB doesn't exists.");
			}
			
			/*
			if (psInput.getChildCount() > 0) {
				psClone = psInput.getChild(0).copy();
				sPropSetName = psClone.getFirstProperty();
				while (sPropSetName != "") {
					//logger.debug("sPropSetName : " + sPropSetName);
					if (sParameter != "")
						sParameter = "," + sParameter;
					sParameter = "'" + psClone.getProperty(sPropSetName) + "'" + sParameter;
					sPropSetName = psClone.getNextProperty();
				}
				logger.debug("Search Parameter : " + sParameter);
			}*/
			
			if (psInput.getChildCount() > 0)
				Props	= psInput.getChild(0).getPropertyCount();
			while(Props > 0)
			{
				if(sParameter=="")
					sParameter = sParameter + "?";
				else
					sParameter = sParameter + ",?";
				Props--;	
			}
			
			con = DataSource.getConnection(sConnectionDB);
			sSql = "{ call " + sStoreProcedure + "(" + sParameter+ ",?) }";
			//logger.debug("Execute sSql : " + sSql);
			System.out.println("Execute sSql : " + sSql);
			cstmt = con.prepareCall(sSql);
			int k=1;
			if (psInput.getChildCount() > 0) {
				psClone = psInput.getChild(0).copy();
				sPropSetName = psClone.getFirstProperty();
				while (sPropSetName != "") {
					cstmt.setString(k, psClone.getProperty(sPropSetName));
					k++;
					sPropSetName = psClone.getNextProperty();
				}
			}
			cstmt.registerOutParameter(k, -10);
			//cstmt.registerOutParameter(k, OracleTypes.CURSOR);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(k);
			cols = rs.getMetaData().getColumnCount();
			
			while (rs.next()) {
				psProp = new SiebelPropertySet();
				psProp.setType("Procedure");
				for (int j = 1; j <= cols; ++j) {
					psProp.setProperty(rs.getMetaData().getColumnName(j), nChkValue(rs.getString(j)));
					//logger.debug("Return Data : " + i + " 번째 Line : " + j + " 번째 cols : " + rs.getMetaData().getColumnName(j) + "  " + j + " 번째 Data : " + rs.getString(j));
					System.out.println("Return Data : " + i + " 번째 Line : " + j + " 번째 cols : " + rs.getMetaData().getColumnName(j) + "  " + j + " 번째 Data : " + rs.getString(j));
				}
				psOutput.addChild(psProp);
				++i;
			}
			psOutput.setType("Response");
			psOutput.setProperty("REPLY_CODE", "OK");
			psOutput.setProperty("REPLY_MSG", "");
		} catch (Exception e) {
			e.printStackTrace();
			psOutput.setType("Response");
			psOutput.setProperty("REPLY_CODE", "ProcedureRunError");
			psOutput.setProperty("REPLY_MSG", ""+e.getMessage());

//			logger.error(Utils.getStackTrace(e));
		} finally {
				if(psClone!=null) psClone=null;
				if(psProp!=null)  psProp=null;
				if(rs!=null)
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			try {
				DataSource.close(cstmt);
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

		String methodName = "Search_DBProcedure_01_0";

		if (methodName.equals("Search_DBProcedure_01_0")) {
			input.setType("Request");
			input.setProperty("PROCEDURE_NAME", "SIEBEL.STW_PROD_SN_CHECK_PROC");
			input.setProperty("DATABASE_TNS", "STW.DEV");
			inputChild.setType("Parameter");
			inputChild.setProperty("SN", "A-00001");
			input.addChild(inputChild);
		}
		new ProcedureRun().executePrepareCall(input, output);
	}
}