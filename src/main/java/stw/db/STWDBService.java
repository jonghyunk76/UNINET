package stw.db;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;

public class STWDBService extends SiebelBusinessService	{

	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		if("ProcedureRun".equals(methodName)){
			new ProcedureRun().executePrepareCall(inputs, outputs);
		}
		else if("FunctionRun".equals(methodName)){
			new FunctionRun().executePrepareCall(inputs, outputs);
		}
	}
	
}
