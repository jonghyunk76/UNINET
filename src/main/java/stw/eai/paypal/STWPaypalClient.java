package stw.eai.paypal;

import stw.eai.common.Common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;

public class STWPaypalClient extends SiebelBusinessService {
	
	private static Log log = LogFactory.getLog(STWPaypalClient.class);
	
	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		Common.SERVER = inputs.getProperty("Server");
		
		log.debug("Server name = " + Common.SERVER + ", Method Name = " + methodName);
		
		if("doCapture".equals(methodName)){
			//PaypalDoCapture doCapture = new PaypalDoCapture();
			//doCapture.request(inputs, outputs);
		}
		else if("doVoid".equals(methodName)){
			//PaypalDoVoid doVoid = new PaypalDoVoid();
			//doVoid.request(inputs, outputs);
		}
	}
}
