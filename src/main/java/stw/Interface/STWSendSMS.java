package stw.Interface;

import stw.Interface.sms.SMSSender;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;

public class STWSendSMS extends SiebelBusinessService {

	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs,	SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		if("RunProcess".equals(methodName)) {
			Run(inputs,outputs);
		}
	}
		
	public void Run(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		try {
			outputs.setProperty("Response", SMSSender.work());
		} catch (Exception e) {
			throw new SiebelBusinessServiceException(e, e.getMessage(), e.getMessage());
		}
	}
}
