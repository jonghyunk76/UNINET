package stw.eai.mySingle;

import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;

public class STWMySingleClient extends SiebelBusinessService{

	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs,	SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		System.out.println("STWMySingleClient : "+Common.MYSINGLE_SERVER_ADDR());
		Common.SERVER = inputs.getProperty("Server");
		
		Client		client			= null;

		if("SendMail".equals(methodName))
		{
			client			= new SendMail();
		}
		else if("submitApproval".equals(methodName))
		{
			client			= new SubmitApproval();
		}
		else if("cancelApproval".equals(methodName))
		{
			client			= new CancelApproval();
		}
		else if("getMailStatus".equals(methodName))
		{
			client			= new GetMailStatusCounts();
		}
		else if("cancelMISMail".equals(methodName))
		{
			client			= new CancelMail();
		}
		else if("getRevisionByMisId".equals(methodName))
		{
			client			= new GetRevisionByMisId();
		}
		else return;
		client.request(inputs, outputs);
	}
}
