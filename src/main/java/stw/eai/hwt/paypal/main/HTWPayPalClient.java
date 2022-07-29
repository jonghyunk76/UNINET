/**
/* 프로젝트 : 미주 유상 결재모듈 개발
/* 제    목 : 미주 유상 결재모듈 JBS 연동 CLASS
/* 작 성 자 : dkkim@coreplus.co.kr
/* 작 성 일 : 2016.09.12
/* 수정이력 : 2016.09.12 , dkkim@coreplus.co.kr , 최초작성
*/

package stw.eai.hwt.paypal.main;

import stw.eai.common.Common;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;

public class HTWPayPalClient extends SiebelBusinessService 
{
	
	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException 
	{	
		
		Common.SERVER = inputs.getProperty("Server");
		HTWPayPalLog filelog = new HTWPayPalLog();
		
		try
		{
			
			//System.out.println("* Start doInvokeMethod([" + methodName + "]");
			
			// 파일 로그 사용 설정(true:사용, false:미사용)
			filelog.initLog(false);
			filelog.addLog("* SiebelPropertySet inputs : " + inputs.toString() );		
			SiebelPropertySet child0 = Common.getChild(inputs	, "SiebelMessage");
			SiebelPropertySet child1 = null;
			SiebelPropertySet child2 = null;
			
			if ("doCapture".equals(methodName) ) 
			{
				child1 = Common.getChild(child0	, "PaypalDoCapture");
			}
			else
			{
				child1 = Common.getChild(child0	, "PaypalDoVoid");
			}
			
			child2 = Common.getChild(child1	, "object_detail");
			String paypalType = child2.getProperty("paypal_type");
			String captureType = "";
					
			filelog.addLog("* methodName  : " + methodName );
			filelog.addLog("* paypalType  : " + paypalType );
			
			if ("doCapture".equals(methodName) ) 
			{
				if ( "HOLD".equals(paypalType) )
				{
					new HTWPayPalDoHold().request(inputs, outputs);	
				} 
				else if ( "CAPTURE".equals(paypalType) )
				{			
					captureType = child2.getProperty("capture_type");
					filelog.addLog("* captureType : " + captureType );
					new HTWPayPalDoCapture().request(inputs, outputs, captureType);					
				} 
				else if ( "HOLD_CAPTURE".equals(paypalType) )
				{
					new HTWPayPalDoHoldCapture().request(inputs, outputs);	
				}			
			} 
			else if ("doVoid".equals(methodName)) 
			{
				if ( "HOLD_CANCEL".equals(paypalType) )
				{				
					new HTWPayPalDoHoldCancel().request(inputs, outputs);
				} 
				else if ( "REFUND".equals(paypalType) )
				{			
					new HTWPayPalDoRefund().request(inputs, outputs);
				}
			}
			filelog.addLog("* SiebelPropertySet outputs : " + outputs.toString() );
			filelog.writeLog();
			//System.out.println("* End doInvokeMethod([" + methodName + "]");			
		} 
		catch(SiebelBusinessServiceException e)
		{			
			filelog.addLog("SiebelPropertySet outputs : " + outputs.toString() );
			filelog.writeLog();
			throw new SiebelBusinessServiceException(e.getErrorCode(), e.getErrorMessage() );
		}
	}
}