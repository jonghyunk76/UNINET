package stw.eai.myEagle;

import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;

public class NewSTWMyEagleClient extends SiebelBusinessService{

	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs,	SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		
		Common.SERVER = inputs.getProperty("Server");
		
		NewClient		client			= null;

		if("SendMail".equals(methodName))
		{
			client			= new NewSendMail();
		}
		else if("submitApproval".equals(methodName))
		{
			client			= new NewSubmitApproval();
		}
		else if("cancelApproval".equals(methodName))
		{
			client			= new NewCancelApproval();
		}
		else if("getMailStatus".equals(methodName))
		{
			client			= new NewGetMailStatusCounts();
		}
		else if("cancelMISMail".equals(methodName))
		{
			client			= new NewCancelMail();
		}
		else if("getRevisionByMisId".equals(methodName))
		{
			client			= new NewGetRevisionByMisId();
			
		}else if("getStatusByMisId".equals(methodName))
		{
			client			= new NewGetStatusByMisId();
		}
		else return;
		client.request(inputs, outputs);
	}
}
