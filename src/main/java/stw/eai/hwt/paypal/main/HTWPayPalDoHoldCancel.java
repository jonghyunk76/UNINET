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

import com.paypal.api.payments.Authorization;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class HTWPayPalDoHoldCancel {
	
	SiebelPropertySet	psDoHoldCancel;
	SiebelPropertySet	psSiebelMessage;
	
	/**
	 * Hold Cancel 정보를 등록한다.
	 * @param inputs
	 * @param outputs  
	 * @return void
	 * @throws SiebelBusinessServiceException
	 */
	protected void request(SiebelPropertySet inputs, SiebelPropertySet outputs) throws SiebelBusinessServiceException {

		this.psSiebelMessage	= Common.getChild(inputs			, "SiebelMessage"); 
		this.psDoHoldCancel		= Common.getChild(psSiebelMessage	, "PaypalDoVoid");
		
		APIContext apiContext	= null;		
		Authorization			authorization;
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

			// 3. Call Authorization Void
			authorization = getHoldCancel(apiContext);

			// 4. Return SiebelMessage
			this.psSiebelMessage = new SiebelPropertySet();
			this.psSiebelMessage.setType("SiebelMessage");

			psResponse	= new SiebelPropertySet();
			psResponse.setType("PaypalVoidResponse");
			
			// 출력 공통항목.
			psResponse.setProperty("id"					, "");
			
			// SandBox에서는 데이타 값이 들어오나 Live에서는 데이타 값이 들어오지 않는다.
			if ( authorization.getCreateTime() != null )	{ psResponse.setProperty("create_time"		, authorization.getCreateTime() ); 	} else { psResponse.setProperty("create_time"		, "" ); }			
			if ( authorization.getUpdateTime() != null )	{ psResponse.setProperty("update_time"		, authorization.getUpdateTime()); 	} else { psResponse.setProperty("update_time"		, "" ); }			
			if ( authorization.getState() != null )			{ psResponse.setProperty("state"			, authorization.getState()); 		} else { psResponse.setProperty("state"				, "" ); }			
			if ( authorization.getParentPayment() != null )	{ psResponse.setProperty("parent_payment"	, authorization.getParentPayment());} else { psResponse.setProperty("parent_payment"	, "" ); }
			
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
			this.psDoHoldCancel		= null;			
			apiContext				= null;
			authorization			= null;
			psResponse				= null;
		}
	}
	
	/**
	 * Hold Void 정보를 등록한다.
	 * @param apiContext
	 * @return Authorization
	 * @throws Exception
	 */
	private Authorization getHoldCancel(APIContext apiContext) throws PayPalRESTException {

		SiebelPropertySet 	psObjectDetail 		= null;		
		Authorization 		objAuthorization 	= null;
		Authorization 		authorization 		= null;
		String holdId 		= "";
		
		try {									
			psObjectDetail = Common.getChild( this.psDoHoldCancel	, "object_detail");
			holdId = psObjectDetail.getProperty("id");
			
			authorization = new Authorization();
			authorization.setId( holdId );			
			
			/*
			System.out.println("=================Request HoldCancel==================");
			System.out.println(authorization);
			System.out.println("===================================================");
			System.out.println("");
			*/
			
			objAuthorization = authorization.doVoid(apiContext);			 
			
			/*
			System.out.println("=================Response HoldCancel==================");
			System.out.println("= Hold ID : " + holdId );
			System.out.println("===================================================");
			System.out.println(objAuthorization);
			System.out.println("===================================================");
			*/
			
			return objAuthorization;
			
		} catch (PayPalRESTException e0) {
			throw e0;
		} catch (Exception e1) {			
			throw e1;	
			
		} finally {
			authorization = null;
			psObjectDetail = null;
		}
	}
}