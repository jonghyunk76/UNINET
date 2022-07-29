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

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Capture;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class HTWPayPalDoCapture {
	
	SiebelPropertySet	psDoCapture;
	SiebelPropertySet	psSiebelMessage;
	
	/**
	 * Capture 정보를 등록한다.
	 * @param inputs
	 * @param outputs  
	 * @return void
	 * @throws SiebelBusinessServiceException
	 */
	protected void request(SiebelPropertySet inputs, SiebelPropertySet outputs, String captureType) throws SiebelBusinessServiceException {

		this.psSiebelMessage	= Common.getChild(inputs			, "SiebelMessage"); 
		this.psDoCapture		= Common.getChild(psSiebelMessage	, "PaypalDoCapture");
		
		APIContext 			apiContext			= null;	
		Authorization 		objAuthorization 	= null;
		Capture				capture				= null;
		SiebelPropertySet	psResponse			= null;
		String 				hold_amount			= "";

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

			// 3. get Authorization
			objAuthorization = getAuthorization(apiContext, captureType);
			
			// 4. get Hold Amount
			hold_amount = getHoldAmount(objAuthorization, captureType);
			
			// 5. Call Capture
			capture = getCapture(apiContext, captureType, hold_amount);

			// 6. Return SiebelMessage
			this.psSiebelMessage = new SiebelPropertySet();
			this.psSiebelMessage.setType("SiebelMessage");

			psResponse	= new SiebelPropertySet();
			psResponse.setType("PaypalCaptureResponse");
			
			// 출력 공통항목.
			psResponse.setProperty("id"					, "");
			psResponse.setProperty("hold_id"			, "");
			psResponse.setProperty("capture_id"			, capture.getId());
			psResponse.setProperty("hold_amount"		, hold_amount );
			
			// SandBox에서는 데이타 값이 들어오나 Live에서는 데이타 값이 들어오지 않는다.
			if ( capture.getCreateTime() != null )		{ psResponse.setProperty("create_time"		, capture.getCreateTime() ); 	} else { psResponse.setProperty("create_time"		, "" ); }			
			if ( capture.getUpdateTime() != null )		{ psResponse.setProperty("update_time"		, capture.getUpdateTime()); 	} else { psResponse.setProperty("update_time"		, "" ); }			
			if ( capture.getState() != null )			{ psResponse.setProperty("state"			, capture.getState()); 			} else { psResponse.setProperty("state"				, "" ); }			
			if ( capture.getParentPayment() != null )	{ psResponse.setProperty("parent_payment"	, capture.getParentPayment()); 	} else { psResponse.setProperty("parent_payment"	, "" ); }			
			
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
			this.psDoCapture		= null;			
			apiContext				= null;
			capture					= null;
			psResponse				= null;
		}
	}
	
	private Authorization getAuthorization(APIContext apiContext, String captureType) throws PayPalRESTException {

		SiebelPropertySet 	psObjectDetail		= null;
		Authorization 		objAuthorization 	= null;
		String 				holdId 				= "";
		
		try {
			
			psObjectDetail = Common.getChild( this.psDoCapture, "object_detail");			
			
			holdId = psObjectDetail.getProperty("id"); 
			objAuthorization = Authorization.get(apiContext, holdId);			
			
		} catch (PayPalRESTException e0) {
			throw e0;
		} catch (Exception e1) {			
			throw e1;		
		} finally {
		}
		return objAuthorization;
	}
	
	private String getHoldAmount(Authorization objAuthorization, String captureType) throws Exception {
			
		SiebelPropertySet	psCapture		= null;
		SiebelPropertySet	psAmount		= null;		
		String 				hold_amount		= "";
		
		try {
			psCapture	= Common.getChild(this.psDoCapture, "capture");
			
			if( "WEB_ISSUE".equals(captureType) )
			{
				// WEB_ISSUE
				hold_amount = HTWPayPalUtil.convCurrencyAmount(objAuthorization.getAmount().getTotal());
			} 
			else 
			{
				// RMA_AR
				hold_amount = HTWPayPalUtil.convCurrencyAmount(objAuthorization.getAmount().getTotal());
				if ( hold_amount == null || hold_amount == "" ) 
				{
					if ( psCapture != null ) 
					{
						psAmount	= Common.getChild(psCapture, "amount_capture");
						if ( psAmount != null )
						{
							hold_amount = HTWPayPalUtil.convCurrencyAmount(psAmount.getProperty("total"));
						}
					}
				}
			}
			return hold_amount;
			
		} catch (Exception e) {
			
			throw e;
			
		} finally {
			
			objAuthorization	= null;
			psCapture			= null;
			psAmount			= null;
			
		}
	}
	
	/**
	 * Hold Void 정보를 등록한다.
	 * @param apiContext
	 * @return Authorization
	 * @throws Exception
	 */
	private Capture getCapture(APIContext apiContext, String captureType, String holdAmount) throws PayPalRESTException {
					
		SiebelPropertySet 	psObjectDetai	= null;		
		SiebelPropertySet	psCapture		= null;
		SiebelPropertySet	psAmount		= null;
		Capture				capture			= null;
		Amount				amount			= null;
		Capture				responseCapture	= null;		
		
		Authorization objAuthorization;
		String holdId = "";
		
		try {
			psObjectDetai = Common.getChild( this.psDoCapture, "object_detail");
			
			holdId = psObjectDetai.getProperty("id");
			objAuthorization = Authorization.get(apiContext, holdId);
			
			// ###Capture
			psCapture	= Common.getChild(this.psDoCapture, "capture");
			
			if ( psCapture != null ) {
				capture 	= new Capture();				
				capture.setIsFinalCapture("Y".equals((psCapture.getProperty("is_final_capture")).toUpperCase()) ? true : false);

				psAmount	= Common.getChild(psCapture, "amount_capture");
				if(holdAmount != null){
					amount = new Amount();
					amount.setCurrency	(psAmount.getProperty("currency"));
					amount.setTotal		(HTWPayPalUtil.convCurrencyAmount(holdAmount));
					capture.setAmount(amount);
				}
			}
			/*
			System.out.println("=================Request Capture==================");
			System.out.println(objAuthorization);
			System.out.println("==================================================");
			*/
			
			responseCapture = objAuthorization.capture(apiContext, capture);
			
			/*
			System.out.println("=================Response Capture=================");
			System.out.println("= Capture ID : " + responseCapture.getId() );
			System.out.println("==================================================");
			System.out.println(responseCapture);
			System.out.println("==================================================");
			 */
			
			return responseCapture;
			
		} catch (PayPalRESTException e0) {
			throw e0;
		} catch (Exception e1) {			
			throw e1;	
			
		} finally {
			
			psObjectDetai		= null;
			objAuthorization	= null;
			psCapture			= null;
			capture				= null;
			psAmount			= null;
			amount				= null;
			
		}
	}
}