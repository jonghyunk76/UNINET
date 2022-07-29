/**
/* 프로젝트 : 미주 유상 결재모듈 개발
/* 제    목 : PayPal Authorization 연동 모듈
/* 작 성 자 : dkkim@coreplus.co.kr
/* 작 성 일 : 2016.09.12
/* 수정이력 : 2016.09.12 , dkkim@coreplus.co.kr , 최초작성
*/

package stw.eai.hwt.paypal.main;

import java.util.HashMap;

import stw.eai.common.Common;

import com.paypal.api.payments.Capture;
import com.paypal.api.payments.Refund;
import com.paypal.api.payments.RefundRequest;
//import com.paypal.api.payments.util.GenerateAccessToken;
//import com.paypal.core.rest.APIContext;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class HTWPayPalDoRefund {
	
	SiebelPropertySet	psDoRefund;
	SiebelPropertySet	psSiebelMessage;
	
	/**
	 * Refund 정보를 등록한다.
	 * @param inputs
	 * @param outputs  
	 * @return void
	 * @throws SiebelBusinessServiceException
	 */
	protected void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {

		this.psSiebelMessage	= Common.getChild(inputs			, "SiebelMessage"); 
		this.psDoRefund			= Common.getChild(psSiebelMessage	, "PaypalDoVoid");
		
		APIContext apiContext	= null;		
		Refund					refund;
		SiebelPropertySet		psResponse;

		try {

			// 1. Get Token
			//String accessToken = GenerateAccessToken.getAccessToken();
			HashMap<String,String> paypalConfigMap = HTWPayPalConfig.getPayPalConfig();			
			String mode 		= (String) paypalConfigMap.get("mode");
			String clientID 	= (String) paypalConfigMap.get("clientID");
			String clientSecret = (String) paypalConfigMap.get("clientSecret");			
			
			// 2. get APIContext
			//apiContext = new APIContext(accessToken);
			apiContext = new APIContext( clientID, clientSecret, mode);

			// 3. Call Refund
			refund = getRefund(apiContext);

			// 4. Return SiebelMessage
			this.psSiebelMessage = new SiebelPropertySet();
			this.psSiebelMessage.setType("SiebelMessage");

			psResponse	= new SiebelPropertySet();
			psResponse.setType("PaypalVoidResponse");
			
			// 출력 공통항목.
			psResponse.setProperty("id"					, refund.getId());
			psResponse.setProperty("update_time"		, "");
			
			// SandBox에서는 데이타 값이 들어오나 Live에서는 데이타 값이 들어오지 않는다.
			if ( refund.getCreateTime() != null )	{ psResponse.setProperty("create_time"		, refund.getCreateTime() ); 	} else { psResponse.setProperty("create_time"		, "" ); }						
			if ( refund.getState() != null )		{ psResponse.setProperty("state"			, refund.getState()); 			} else { psResponse.setProperty("state"				, "" ); }			
			if ( refund.getParentPayment() != null ){ psResponse.setProperty("parent_payment"	, refund.getParentPayment());	} else { psResponse.setProperty("parent_payment"	, "" ); }			
			
			this.psSiebelMessage.addChild(psResponse);
			
			outputs.addChild(this.psSiebelMessage);
			
		} catch (PayPalRESTException e0) {
			outputs.setProperty("Error", HTWPayPalUtil.makeUserErrorMsg(e0) );
			throw new SiebelBusinessServiceException(e0, "doHold:doPost:","doHold:doPost: "+HTWPayPalUtil.makeUserErrorMsg(e0));			
		} catch (Exception e1) {
			outputs.setProperty("Error", HTWPayPalUtil.makeCommonErrorMsg(e1) );
			throw new SiebelBusinessServiceException(e1, "doHold:doPost:","doHold:doPost: "+HTWPayPalUtil.makeCommonErrorMsg(e1));
		} finally {
			this.psSiebelMessage	= null;
			this.psDoRefund			= null;			
			apiContext				= null;
			refund					= null;
			psResponse				= null;
		}
	}
	
	/**
	 * Capture Refund 정보를 등록한다.
	 * @param apiContext
	 * @return Authorization
	 * @throws Exception
	 */
	private Refund getRefund(APIContext apiContext) throws PayPalRESTException {
					
		SiebelPropertySet 	psObjectDetail 	= null;
		//SiebelPropertySet	psRefund		= null;
		//SiebelPropertySet	psAmount		= null;
		Capture				capture			= null;
		//Amount			amount			= null;
		Refund				responseRefund	= null;
		//Refund			refund			= null;
		RefundRequest 		refund 			= null;
		
		String captureId = "";
		
		try {
			psObjectDetail = Common.getChild( this.psDoRefund	, "object_detail");			
			captureId = psObjectDetail.getProperty("id");
			
			capture = Capture.get(apiContext, captureId);
			
			/*
			// ###Refund
			psRefund	= Common.getChild(this.psDoRefund, "Refund");
			
			if(psRefund != null){
				refund = new Refund();
				// ###Amount
				// Let's you specify a capture amount.
				psAmount	= Common.getChild(psRefund, "amount_refund");
				if(psAmount != null){
					amount = new Amount();
					amount.setCurrency	(psAmount.getProperty("currency"));
					amount.setTotal		(HTWPayPalUtil.convCurrencyAmount(psAmount.getProperty("total")));
					refund.setAmount(amount);
				}
			}
			*/
			
			if(capture != null){
				//refund = new Refund();
				refund = new RefundRequest();
				refund.setAmount(capture.getAmount());
			}
			
			/*
			System.out.println("=================Request Capture==================");
			System.out.println(capture);
			System.out.println("==================================================");
			*/
			
			responseRefund = capture.refund(apiContext, refund);
			
			/*
			System.out.println("=================Response Capture=================");
			System.out.println("= Capture ID : " + responseRefund.getCaptureId() );
			System.out.println("= Refund ID  : " + responseRefund.getId() );
			System.out.println("==================================================");
			System.out.println(responseRefund);
			System.out.println("==================================================");
			*/
			
			return responseRefund;
			
		} catch (PayPalRESTException e0) {
			throw e0;
		} catch (Exception e1) {			
			throw e1;
		} finally {
			
			psObjectDetail	= null;
			//psRefund		= null;
			//psAmount		= null;
			capture			= null;
			//amount		= null;
			refund			= null;
					
		}
	}
}