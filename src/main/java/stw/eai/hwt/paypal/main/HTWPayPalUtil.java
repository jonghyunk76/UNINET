/**
/* 프로젝트 : 미주 유상 결재모듈 개발
/* 제    목 : 공통 유틸 모듈
/* 작 성 자 : dkkim@coreplus.co.kr
/* 작 성 일 : 2016.09.12
/* 수정이력 : 2016.09.12 , dkkim@coreplus.co.kr , 최초작성
*/
package stw.eai.hwt.paypal.main;

import java.util.HashMap;
import java.util.List;
import com.paypal.api.payments.Error;
import com.paypal.api.payments.ErrorDetails;
import com.paypal.base.rest.PayPalRESTException;

public class HTWPayPalUtil {
	
	/**
	 * @param zeroLength
	 * @return
	 */
	public static String makeZeroStr(int zeroLength){		
		String returnStr = "";
		for (int zeroIndex=0 ; zeroIndex<zeroLength ; zeroIndex++ ){
			returnStr += "0";
		}
		return returnStr;
	}
	
	/**
	 * @param amountStr
	 * @return
	 */
	public static String convCurrencyAmount(String amountStr){
		return amountStr;
		
		/*
		String returnValue = "";
		
		if ( amountStr == null || amountStr.isEmpty() ){
			amountStr = "0";
		}
		
		int pointPos = 0;		
		int pointIndexPos = amountStr.indexOf(".");
		if ( pointIndexPos == -1 ){
			pointPos = pointIndexPos;
		} else {
			pointPos = amountStr.length() - pointIndexPos;
		}

		if ( pointPos == -1 ){
			returnValue = amountStr + "." + HTWPayPalUtil.makeZeroStr(2);
		} else if ( pointPos == 2 ){
			returnValue = amountStr + HTWPayPalUtil.makeZeroStr(1);
		} else {
			returnValue = amountStr;
		}
		return returnValue;
		*/
		
	}

	/*
	public static String makeInputParam(String args0, String args1){		
		return "["+args0+"]:["+args1+"]\\n";
	}
	*/	
	
	public static String makeUserErrorMsg(PayPalRESTException e){
		
		HashMap<String, String> hmErrorMsg  = new HashMap<String, String>();
		String returnErrorMessage = "";
		String errorMsg = e.toString();
		
		try{			
			hmErrorMsg.put("payer.funding_instruments[0].credit_card".toUpperCase(),"Credit Card");			
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.type".toUpperCase(),"Credit Card");	
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.number".toUpperCase(),"Credit Card Number");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.expire_year".toUpperCase(),"Expire Year");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.expire_month".toUpperCase(),"Expire Month");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.cvv2".toUpperCase(),"Expire Month");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.billing_address.city".toUpperCase(),"City");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.billing_address.country_code".toUpperCase(),"Country");		
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.billing_address".toUpperCase(),"Billing Address");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.billing_address.line1".toUpperCase(),"Billing Address");		
			hmErrorMsg.put("transactions[0].amount".toUpperCase(),"Amount");
			hmErrorMsg.put("transactions[0].amount.total".toUpperCase(),"Amount");				
			hmErrorMsg.put("transactions[0].amount.currency".toUpperCase(),"Currency");				
			hmErrorMsg.put("amount".toUpperCase(),"Amount");
			hmErrorMsg.put("amount.total".toUpperCase(),"Amount");
			hmErrorMsg.put("amount.currency".toUpperCase(),"Amount Currency");
			
			if ( e.getDetails() != null ){
				Error details = e.getDetails();
				List<ErrorDetails> listDetail = details.getDetails();
				if ( listDetail != null ) {
											
					int index = listDetail.size();
					String hmName = "";
					if ( index != 0 ){ 					
						for ( int i=0; i<index ; i++ ){
							if ( i!=0){
								returnErrorMessage += ", ";
							}
							hmName = hmErrorMsg.get( ((String)listDetail.get(i).getField().toUpperCase()).trim() );
							if ( hmName == null || hmName == "" ){
								returnErrorMessage += "[" + ((String)listDetail.get(i).getField().toUpperCase()).trim() + "]";
							} else {
								returnErrorMessage += "[" + hmName + "]";
							}
							returnErrorMessage += " " + (String)listDetail.get(i).getIssue();
						}
					} else {
						returnErrorMessage += "[" + details.getName()+"] " + details.getMessage();
					}
				} else {
					returnErrorMessage =  "[" + details.getName()+"] " + details.getMessage();
				}
				returnErrorMessage += " >> [PayPal] ";
			} else {
				returnErrorMessage = errorMsg + " >> [PayPal] ";
			}
		} catch (Exception e1){
			returnErrorMessage = "Exception >> [PayPal Error Message] " + errorMsg;
		}
		return returnErrorMessage;
	}
	
	public static String makeCommonErrorMsg(Exception e){		
		return "System Error >> [4CUST] " + e.getMessage();
	}
	
	/*
	public static String makeUserErrorMsg(String oriErrorMsg){
		
		HashMap<String, String> hmErrorMsg  = new HashMap<String, String>();
		String returnErrorMessage = "";
		String errorMsg = oriErrorMsg;
		
		try{			
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.type".toUpperCase(),"Credit Card");	
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.number".toUpperCase(),"Credit Card Number");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.expire_year".toUpperCase(),"Expire Year");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.expire_month".toUpperCase(),"Expire Month");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.cvv2".toUpperCase(),"Expire Month");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.billing_address.city".toUpperCase(),"City");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.billing_address.country_code".toUpperCase(),"Country");		
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.billing_address".toUpperCase(),"Billing Address");
			hmErrorMsg.put("payer.funding_instruments[0].credit_card.billing_address.line1".toUpperCase(),"Billing Address");		
			hmErrorMsg.put("transactions[0].amount".toUpperCase(),"Amount");
			hmErrorMsg.put("transactions[0].amount.total".toUpperCase(),"Amount");				
			hmErrorMsg.put("transactions[0].amount.currency".toUpperCase(),"Currency");				
			hmErrorMsg.put("amount".toUpperCase(),"Amount");
			hmErrorMsg.put("amount.total".toUpperCase(),"Amount");
			hmErrorMsg.put("amount.currency".toUpperCase(),"Amount Currency");			
			
			
			JsonElement jelement = new JsonParser().parse(errorMsg.substring(errorMsg.lastIndexOf(" with response : ")+" with response : ".length()));
			JsonObject jobject = jelement.getAsJsonObject();
			JsonArray jarrayDetails = jobject.getAsJsonArray("details");

			//String name = jobject.get("name").getAsString();
			String message = jobject.get("message").getAsString();
			
			// 1안
			//if ( jarrayDetails != null){
			//	jobject = jarrayDetails.get(0).getAsJsonObject();
			//	String result0 = jobject.get("field").getAsString();
			//	String result1 = jobject.get("issue").getAsString();
			//	returnErrorMessage = "["+hmErrorMsg.get(result0.toUpperCase())+"] "+result1+" >> [PayPal Error Message] "+oriErrorMsg;
			//}else{
			//	returnErrorMessage = message+" >> [PayPal Error Message] "+oriErrorMsg;
			//}
			
			// 2안
			if ( jarrayDetails != null){
				int detailSize = jarrayDetails.size();
				String result0 = "";
				String result1 = "";
				String result2 = "";
				for ( int detailIndex=0; detailIndex<detailSize ; detailIndex++){
					
					jobject = jarrayDetails.get(detailIndex).getAsJsonObject();
					result0 = jobject.get("field").getAsString();
					result1 = jobject.get("issue").getAsString();
					result2 = hmErrorMsg.get(result0.toUpperCase());
					if ( detailIndex == 0 ) {
						returnErrorMessage = ( result2 != null ? "[" + result2 + "] " : "" ) + result1;
					} else {
						returnErrorMessage += "" + result1;
					}
				}
				returnErrorMessage += " >> [PayPal] ";
			}else{
				returnErrorMessage = message + " >> [PayPal] ";
			}			
			
		} catch (Exception e){
			// 1안
			//returnErrorMessage = "Exception >> [PayPal Error Message] "+oriErrorMsg;
						
			// 2안
			returnErrorMessage = "System Error >> [4CUST] " + oriErrorMsg;
		}
		
		return returnErrorMessage;
	}
	*/

}