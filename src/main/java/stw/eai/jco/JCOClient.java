package stw.eai.jco;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;

public class JCOClient extends SiebelBusinessService{
	final static String SAP_SERVER = "SAP_SERVER"; 
	
	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs,	SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		// TODO Auto-generated method stub
		if("RunProcess".equals(methodName)){
			CallRFC(inputs,outputs);
		}
		else{
			
		}
	}
	
	public void CallRFC(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException
	{
		SiebelPropertySet psSiebelMessage	= null;
		SiebelPropertySet psEAIMaster		= null;
		SiebelPropertySet psTemp			= null;
		
		SiebelPropertySet psResponse		= null;
		SiebelPropertySet psInput			= null;
		SiebelPropertySet psOutput			= null;
		SiebelPropertySet psTables			= null;
		SiebelPropertySet psTable			= null;
		SiebelPropertySet psItem			= null;
		
		String		sFunction	= "";
		String		sParams		= "";
		String		sFields		= "";
		
		String[]	arParams	= null;
		String[]	arFields	= null;
		
		JCoFunction		function	= null;
		JCoDestination	destination	= null;
		
		try
		{
			outputs.setProperty("RFCOutput"	, "");
			outputs.setProperty("Error"		, "");
			
			// 1. SiebelMessage, EAIMasterMsg ����
			// =======================================================================
			int cnt = 0;
			psTemp = null;
			while(cnt < inputs.getChildCount()){
				psTemp = inputs.getChild(cnt);
				if("SiebelMessage".equals(psTemp.getType())){
					psSiebelMessage = psTemp;
				}
				else if( "EAIMasterMsg".equals(psTemp.getType())){
					psEAIMaster = psTemp;
				}
				cnt++;
			}
			// =======================================================================
			
			// 2. RFC ���� �Ķ���� ����
			// =======================================================================
			sFunction	= psEAIMaster.getProperty("JCO:Function");
			
			sParams		= psEAIMaster.getProperty("JCO:InputParams");
			arParams	= sParams.split(",");
			// =======================================================================
			
			// 3. JCO 
			// =======================================================================
			destination	= JCoDestinationManager.getDestination(SAP_SERVER);
			function	= destination.getRepository().getFunction(sFunction);
			
			if(function == null)
	            throw new RuntimeException(sFunction+" not found in SAP.");
			
			cnt = 0;
			while(cnt < arParams.length){
				String param = arParams[cnt];
				function.getImportParameterList().setValue(param, psSiebelMessage.getProperty(param));
				cnt++;
			}
			
			function.execute(destination);
			
			System.out.println(function.toXML());
			// =======================================================================
			
			// Output 
			// =======================================================================
			outputs.setProperty("RFCOutput", function.toXML());
			
			psResponse	= new SiebelPropertySet();
			psInput		= new SiebelPropertySet();
			psOutput	= new SiebelPropertySet();
			psTables	= new SiebelPropertySet();
			
			psResponse	.setType(sFunction);
			psInput		.setType("INPUT");
			psOutput	.setType("OUTPUT");
			psTables	.setType("TABLES");
			
			psItem		= new SiebelPropertySet();
			psItem.setType("item");
			
			// INPUT
			cnt = 0;
			while(cnt < arParams.length){
				String param = arParams[cnt];
				psInput.setProperty(param,psSiebelMessage.getProperty(param));
				cnt++;
			}
			
			// OUTPUT
			sParams	= psEAIMaster.getProperty("JCO:OutputParams");
			arParams = sParams.split(",");
			
			cnt = 0;
			while(cnt < arParams.length){
				String param = arParams[cnt];
				psOutput.setProperty(param, function.getExportParameterList().getString(param));
				cnt++;
			}
			
			// TABLES
			sParams	= psEAIMaster.getProperty("JCO:Tables");
			arParams = sParams.split(",");
			
			cnt = 0;
			while(cnt < arParams.length){
				String tableName = arParams[cnt];
				
				JCoTable jcoTable = function.getTableParameterList().getTable(tableName);
				
				psTable	= new SiebelPropertySet();
				psTable.setType(tableName);
				
				sFields	= psEAIMaster.getProperty("JCO:Fields:"+tableName);
				arFields = sFields.split(",");
				
				// item
				if(!jcoTable.isEmpty())
				{
					jcoTable.firstRow();
					psItem = new SiebelPropertySet();
					psItem.setType("item");
					
					int i = 0;
					while(i < arFields.length){
						String fieldName = arFields[i];
						
						psItem.setProperty(fieldName, jcoTable.getString(fieldName));
						i++;
					}
					psTable.addChild(psItem);
					
					while(jcoTable.nextRow()){
						psItem = new SiebelPropertySet();
						psItem.setType("item");
						
						i = 0;
						while(i < arFields.length){
							String fieldName = arFields[i];
							
							psItem.setProperty(fieldName, jcoTable.getString(fieldName));
							i++;
						}
						psTable.addChild(psItem);
					}
				}
				
				psTables.addChild(psTable);
				
				cnt++;
			}
			
			psResponse.addChild(psInput);
			psResponse.addChild(psOutput);
			psResponse.addChild(psTables);
			
			psSiebelMessage = new SiebelPropertySet();
			psSiebelMessage.setType("SiebelMessage");
			psSiebelMessage.addChild(psResponse);
			
			outputs.addChild(psSiebelMessage);
			// =======================================================================
		}
		catch (Exception e)
		{
			outputs.setProperty("Error", e.getMessage());
			System.out.println("Exception:"+e.getMessage());
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
		}
		finally
		{
			psSiebelMessage	= null;
			psEAIMaster		= null;
			psTemp			= null;
			
			psResponse		= null;
			psInput			= null;
			psOutput		= null;
			psTables		= null;
			psTable			= null;
			psItem			= null;
			
			arParams		= null;
			arFields		= null;
			
			function		= null;
			destination		= null;
		}
	}
}
