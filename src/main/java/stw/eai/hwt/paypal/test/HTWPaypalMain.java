/**
/* 프로젝트 : 미주 유상 결재모듈 개발
/* 제    목 : 미주 유상 결재모듈 JBS 연동 모듈 테스트 실행 CLASS 
/* 작 성 자 : dkkim@coreplus.co.kr
/* 작 성 일 : 2016.09.12
/* 수정이력 : 2016.09.12 , dkkim@coreplus.co.kr , 최초작성
*/

package stw.eai.hwt.paypal.test;

import stw.eai.hwt.paypal.main.HTWPayPalClient;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;
import com.yni.fta.common.exception.BizExceptionHandler;

public class HTWPaypalMain {
	
	// Object Type
	public static String OBJ_DETAIL_TYPE_HOLD 				= "HOLD";
	public static String OBJ_DETAIL_TYPE_HOLD_CANCEL 		= "HOLD_CANCEL";
	public static String OBJ_DETAIL_TYPE_CAPTURE 			= "CAPTURE";
	public static String OBJ_DETAIL_TYPE_HOLD_CAPTURE 		= "HOLD_CAPTURE";
	public static String OBJ_DETAIL_TYPE_REFUND 			= "REFUND";
	public static String OBJ_DETAIL_CAPTURE_TYPE_WEB_ISSUE	= "WEB_ISSUE";
	public static String OBJ_DETAIL_CAPTURE_TYPE_RMA_AR		= "RMA_AR";		

	// PAYMENT Part
	public static String TEST_PAYMENT_INTENT_AUTHORIZE 		= "authorize";
	
	// PAYER Part
	public static String TEST_PAYER_PAYMENTMETHOD_CREDITCARD = "credit_card";
	
	// CARD Part	
	// VISA Information
	public static String OBJ_CARD_TYPE_VISA					= "visa";
	public static String OBJ_CARD_NUMBER_VISA 				= "4032037167740741";
	public static String OBJ_CARD_CVV_VISA 					= "874";
	public static String OBJ_CARD_EXPIRE_YEAR_VISA 			= "2021";
	public static String OBJ_CARD_EXPIRE_MONTH_VISA 		= "9";
	public static String OBJ_CARD_FIRST_NAME_VISA 			= "VISA_F_NAME";
	public static String OBJ_CARD_LAST_NAME_VISA	 		= "VISA_L_NAME";
	
	// AMERICAN EXPRESS Information
	public static String OBJ_CARD_TYPE_AMEX					= "amex";
	public static String OBJ_CARD_NUMBER_AMEX 				= "373625082775407";
	public static String OBJ_CARD_CVV_AMEX 					= "874";
	public static String OBJ_CARD_EXPIRE_YEAR_AMEX 			= "2021";
	public static String OBJ_CARD_EXPIRE_MONTH_AMEX 		= "11";
	public static String OBJ_CARD_FIRST_NAME_AMEX 			= "AMEX_F_NAME";
	public static String OBJ_CARD_LAST_NAME_AMEX	 		= "AMEX_L_NAME";
	
	// DISCOVER Information
	public static String OBJ_CARD_TYPE_DISCOVER				= "discover";
	public static String OBJ_CARD_NUMBER_DISCOVER 			= "6011779513352198";
	public static String OBJ_CARD_CVV_DISCOVER 				= "874";
	public static String OBJ_CARD_EXPIRE_YEAR_DISCOVER 		= "2021";
	public static String OBJ_CARD_EXPIRE_MONTH_DISCOVER 	= "11";
	public static String OBJ_CARD_FIRST_NAME_DISCOVER 		= "DISCOVER_F_NAME";
	public static String OBJ_CARD_LAST_NAME_DISCOVER	 	= "DISCOVER_L_NAME";
	
	// MASTERCARD Information
	
	public static String OBJ_CARD_TYPE_MASTERCARD			= "mastercard";
	public static String OBJ_CARD_NUMBER_MASTERCARD 		= "5229787016003812";
	public static String OBJ_CARD_CVV_MASTERCARD 			= "874";
	public static String OBJ_CARD_EXPIRE_YEAR_MASTERCARD 	= "2021";
	public static String OBJ_CARD_EXPIRE_MONTH_MASTERCARD 	= "11";
	public static String OBJ_CARD_FIRST_NAME_MASTERCARD 	= "MASTERCARD_F_NAME";
	public static String OBJ_CARD_LAST_NAME_MASTERCARD	 	= "MASTERCARD_L_NAME";
		
	public static String OBJ_CARD_TYPE						= OBJ_CARD_TYPE_MASTERCARD;
	public static String OBJ_CARD_NUMBER 					= OBJ_CARD_NUMBER_MASTERCARD;
	public static String OBJ_CARD_CVV 						= OBJ_CARD_CVV_MASTERCARD;
	public static String OBJ_CARD_EXPIRE_YEAR 				= OBJ_CARD_EXPIRE_YEAR_MASTERCARD;
	public static String OBJ_CARD_EXPIRE_MONTH 				= OBJ_CARD_EXPIRE_MONTH_MASTERCARD;
	public static String OBJ_CARD_FIRST_NAME 				= OBJ_CARD_FIRST_NAME_MASTERCARD;
	public static String OBJ_CARD_LAST_NAME	 				= OBJ_CARD_LAST_NAME_MASTERCARD;	
	
	// ADDRESS Part
	public static String OBJ_ADDRESS_COUNTRY_CODE		= "US";
	public static String OBJ_ADDRESS_LINE1				= "52 N Main ST";
	public static String OBJ_ADDRESS_LINE2				= "";
	public static String OBJ_ADDRESS_PHONE				= "821012341234";
	public static String OBJ_ADDRESS_STATE				= "OH";
	public static String OBJ_ADDRESS_CITY				= "Johnstown";
	public static String OBJ_ADDRESS_POSTAL_CODE		= "43210";
	
	// TRANSACTION Part
	public static String OBJ_TRANSACTION_SUBTOTAL 		= "0";	
	//public static String OBJ_TRANSACTION_SUBTOTAL 		= "1000.00";
	public static String OBJ_TRANSACTION_TAX 			= "0";
	public static String OBJ_TRANSACTION_SHIPPING 		= "0";
	
	// AMOUNT Part 
	public static String OBJ_AMOUNT_CURRENCY 			= "USD";
	//public static String OBJ_AMOUNT_TOTAL 				= Integer.toString(Integer.parseInt(OBJ_TRANSACTION_SUBTOTAL)+Integer.parseInt(OBJ_TRANSACTION_TAX)+Integer.parseInt(OBJ_TRANSACTION_SHIPPING));
	public static String OBJ_AMOUNT_TOTAL 				= "0";
	//public static String OBJ_AMOUNT_TOTAL 				= "1000.00";
	
	// CAPTURE Part
	public static String OBJ_FINAL_CAPTURE 				= "Y";
	public static String OBJ_CAPTURE_CURRENCY 			= HTWPaypalMain.OBJ_AMOUNT_CURRENCY;
	public static String OBJ_CAPTURE_TOTAL 				= HTWPaypalMain.OBJ_AMOUNT_TOTAL;

	public static void main(String[] args) {

		//System.out.println("* Start PayPal Test Main Class");
		
		try {
			boolean isTest = false;
			
			// 6W968981PR094494K : 199
			// 7C486990Y6743960K : 100 , 사용자 입력
			
			HTWPaypalMain ObjMain = new HTWPaypalMain();
			ObjMain.testDoHold();							// doHold
//			ObjMain.testDoHoldCancel("3BH36363HR9296314");
//			if ( isTest ) {							
//				ObjMain.testDoHold();							// doHold			
//				ObjMain.testDoHoldCancel("92X44089YM204074R");	// doHoldCancel, 	Hold ID
//				ObjMain.testDoCapture("32Y01249SD1067313");		// doCapture, 		Hold ID
//				ObjMain.testDoRefund("29624513NE162445G"); 		// doRefund,		Capture ID			
//				ObjMain.testDoHoldCapture();					// doHoldCapture
//			}
			
		} catch (SiebelBusinessServiceException e) {
			System.out.println("* HTWPaypalMain : "+e.getErrorMessage() +" / "+ e.getErrorCode());
		}
		
		//System.out.println("* End PayPal Test Main Class");
	}
	
	public void testDoHold() throws SiebelBusinessServiceException {
		
		SiebelPropertySet inputs			= new SiebelPropertySet();
		SiebelPropertySet outputs			= new SiebelPropertySet();
		SiebelPropertySet psSiebelMessage	= new SiebelPropertySet();
		SiebelPropertySet psDoHold			= new SiebelPropertySet();
		SiebelPropertySet psObjectDetail	= new SiebelPropertySet();
		SiebelPropertySet psPayment			= new SiebelPropertySet();
		SiebelPropertySet psPayer			= new SiebelPropertySet();
		SiebelPropertySet psFunding			= new SiebelPropertySet();
		SiebelPropertySet psCard			= new SiebelPropertySet();
		SiebelPropertySet psAddress			= new SiebelPropertySet();
		SiebelPropertySet psTransaction		= new SiebelPropertySet();
		SiebelPropertySet psAmount			= new SiebelPropertySet();
		SiebelPropertySet psDetails			= new SiebelPropertySet();
		
		psSiebelMessage	.setType("SiebelMessage");
		psDoHold		.setType("PaypalDoCapture");
		psObjectDetail	.setType("object_detail");
		psPayment		.setType("Payment");
		psPayer			.setType("payer");
		psFunding		.setType("funding_instrument");
		psCard			.setType("credit_card");
		psAddress		.setType("billing_address");
		psTransaction	.setType("transaction");
		psAmount		.setType("amount");
		psDetails		.setType("details");
		
		inputs			.addChild(psSiebelMessage);
		psSiebelMessage	.addChild(psDoHold);
		psDoHold		.addChild(psObjectDetail);
		psDoHold		.addChild(psPayment);
		
		psPayment		.addChild(psPayer);
		psPayer			.addChild(psFunding);
		psFunding		.addChild(psCard);
		psCard			.addChild(psAddress);		
		
		psPayment		.addChild(psTransaction);
		psTransaction	.addChild(psAmount);
		psAmount		.addChild(psDetails);		
		
		// psObjectDetail
		psObjectDetail.setProperty("paypal_type", HTWPaypalMain.OBJ_DETAIL_TYPE_HOLD);
		psObjectDetail.setProperty("id", "");
		
		// psPayment
		psPayment.setProperty("intent", HTWPaypalMain.TEST_PAYMENT_INTENT_AUTHORIZE);
		
		// psPayer
		psPayer.setProperty("payment_method", HTWPaypalMain.TEST_PAYER_PAYMENTMETHOD_CREDITCARD);
		
		// psCard
		psCard.setProperty("number", 		HTWPaypalMain.OBJ_CARD_NUMBER);
		psCard.setProperty("cvv2", 			HTWPaypalMain.OBJ_CARD_CVV);
		psCard.setProperty("expire_month", 	HTWPaypalMain.OBJ_CARD_EXPIRE_MONTH);
		psCard.setProperty("expire_year", 	HTWPaypalMain.OBJ_CARD_EXPIRE_YEAR);
		psCard.setProperty("type", 			HTWPaypalMain.OBJ_CARD_TYPE);		
		psCard.setProperty("first_name", 	HTWPaypalMain.OBJ_CARD_FIRST_NAME);
		psCard.setProperty("last_name",		HTWPaypalMain.OBJ_CARD_LAST_NAME);
		
		// psAddress
		psAddress.setProperty("country_code", 	HTWPaypalMain.OBJ_ADDRESS_COUNTRY_CODE);
		psAddress.setProperty("line1", 			HTWPaypalMain.OBJ_ADDRESS_LINE1);
		psAddress.setProperty("line2", 			HTWPaypalMain.OBJ_ADDRESS_LINE2);
		psAddress.setProperty("phone", 			HTWPaypalMain.OBJ_ADDRESS_PHONE);
		psAddress.setProperty("state", 			HTWPaypalMain.OBJ_ADDRESS_STATE);
		psAddress.setProperty("city", 			HTWPaypalMain.OBJ_ADDRESS_CITY);
		psAddress.setProperty("postal_code",	HTWPaypalMain.OBJ_ADDRESS_POSTAL_CODE);		
		
		// psTransaction
		psTransaction.setProperty("description", 	"desc");
		
		// psAmount
		psAmount.setProperty("total", 		HTWPaypalMain.OBJ_AMOUNT_TOTAL);
		psAmount.setProperty("currency", 	HTWPaypalMain.OBJ_AMOUNT_CURRENCY);
		
		// psDetails
		psDetails.setProperty("subtotal", 	HTWPaypalMain.OBJ_TRANSACTION_SUBTOTAL);
		psDetails.setProperty("tax", 		HTWPaypalMain.OBJ_TRANSACTION_TAX);
		psDetails.setProperty("shipping", 	HTWPaypalMain.OBJ_TRANSACTION_SHIPPING);
		
		HTWPayPalClient client = new HTWPayPalClient();
		client.doInvokeMethod("doCapture", inputs, outputs);
		
		System.out.println("===================================================");
		System.out.println("= Sieble JBS Information Detail                   =");
		System.out.println("===================================================");
		System.out.println("= Input SiebelPropertySet : "); 
		System.out.println("===================================================");
		System.out.println( inputs.toString() );
		System.out.println("===================================================");
		System.out.println("= Output SiebelPropertySet : ");
		System.out.println("===================================================");
		System.out.println( outputs.toString() );
		System.out.println("===================================================");

	}
	
	public void testDoHoldCapture() throws SiebelBusinessServiceException {
		
		SiebelPropertySet inputs			= new SiebelPropertySet();
		SiebelPropertySet outputs			= new SiebelPropertySet();
		SiebelPropertySet psSiebelMessage	= new SiebelPropertySet();
		SiebelPropertySet psDoHoldCapture	= new SiebelPropertySet();
		SiebelPropertySet psObjectDetail	= new SiebelPropertySet();
		SiebelPropertySet psPayment			= new SiebelPropertySet();
		SiebelPropertySet psPayer			= new SiebelPropertySet();
		SiebelPropertySet psFunding			= new SiebelPropertySet();
		SiebelPropertySet psCard			= new SiebelPropertySet();
		SiebelPropertySet psAddress			= new SiebelPropertySet();
		SiebelPropertySet psTransaction		= new SiebelPropertySet();
		SiebelPropertySet psAmount			= new SiebelPropertySet();
		SiebelPropertySet psDetails			= new SiebelPropertySet();
		SiebelPropertySet psCapture 		= new SiebelPropertySet();
		SiebelPropertySet psAmountCapture	= new SiebelPropertySet();
		
		psSiebelMessage	.setType("SiebelMessage");
		psDoHoldCapture	.setType("PaypalDoCapture");
		psObjectDetail	.setType("object_detail");
		psPayment		.setType("Payment");
		psPayer			.setType("payer");
		psFunding		.setType("funding_instrument");
		psCard			.setType("credit_card");
		psAddress		.setType("billing_address");
		psTransaction	.setType("transaction");
		psAmount		.setType("amount");
		psDetails		.setType("details");
		psCapture		.setType("capture");
		psAmountCapture	.setType("amount_capture");
		
		inputs			.addChild(psSiebelMessage);
		psSiebelMessage	.addChild(psDoHoldCapture);
		psDoHoldCapture	.addChild(psPayment);
		psDoHoldCapture	.addChild(psObjectDetail);
		
		psPayment		.addChild(psPayer);
		psPayer			.addChild(psFunding);
		psFunding		.addChild(psCard);
		psCard			.addChild(psAddress);		
		
		psPayment		.addChild(psTransaction);
		psTransaction	.addChild(psAmount);
		psAmount		.addChild(psDetails);	
		
		psDoHoldCapture	.addChild(psCapture);
		psCapture		.addChild(psAmountCapture);
				
		// psObjectDetail
		psObjectDetail.setProperty("paypal_type", HTWPaypalMain.OBJ_DETAIL_TYPE_HOLD_CAPTURE);
		psObjectDetail.setProperty("id", "");
		
		// psPayment
		psPayment.setProperty("intent", HTWPaypalMain.TEST_PAYMENT_INTENT_AUTHORIZE);
		
		// psPayer
		psPayer.setProperty("payment_method", HTWPaypalMain.TEST_PAYER_PAYMENTMETHOD_CREDITCARD);
		
		// psCard
		psCard.setProperty("number", 		HTWPaypalMain.OBJ_CARD_NUMBER);
		psCard.setProperty("cvv2", 			HTWPaypalMain.OBJ_CARD_CVV);
		psCard.setProperty("expire_month", 	HTWPaypalMain.OBJ_CARD_EXPIRE_MONTH);
		psCard.setProperty("expire_year", 	HTWPaypalMain.OBJ_CARD_EXPIRE_YEAR);
		psCard.setProperty("type", 			HTWPaypalMain.OBJ_CARD_TYPE);		
		psCard.setProperty("first_name", 	HTWPaypalMain.OBJ_CARD_FIRST_NAME);
		psCard.setProperty("last_name",		HTWPaypalMain.OBJ_CARD_LAST_NAME);
		
		// psAddress
		psAddress.setProperty("country_code", 	HTWPaypalMain.OBJ_ADDRESS_COUNTRY_CODE);
		psAddress.setProperty("line1", 			HTWPaypalMain.OBJ_ADDRESS_LINE1);
		psAddress.setProperty("line2", 			HTWPaypalMain.OBJ_ADDRESS_LINE2);
		psAddress.setProperty("phone", 			HTWPaypalMain.OBJ_ADDRESS_PHONE);
		psAddress.setProperty("state", 			HTWPaypalMain.OBJ_ADDRESS_STATE);
		psAddress.setProperty("city", 			HTWPaypalMain.OBJ_ADDRESS_CITY);
		psAddress.setProperty("postal_code",	HTWPaypalMain.OBJ_ADDRESS_POSTAL_CODE);		
		
		// psTransaction
		psTransaction.setProperty("description", 	"desc");
		
		// psAmount
		psAmount.setProperty("total", 		HTWPaypalMain.OBJ_AMOUNT_TOTAL);
		psAmount.setProperty("currency", 	HTWPaypalMain.OBJ_AMOUNT_CURRENCY);
		
		// psDetails
		psDetails.setProperty("subtotal", 	HTWPaypalMain.OBJ_TRANSACTION_SUBTOTAL);
		psDetails.setProperty("tax", 		HTWPaypalMain.OBJ_TRANSACTION_TAX);
		psDetails.setProperty("shipping", 	HTWPaypalMain.OBJ_TRANSACTION_SHIPPING);
		
		// psCapture
		psCapture.setProperty("is_final_capture", "Y");

		// psCaptureAmount
		psAmountCapture.setProperty("currency", HTWPaypalMain.OBJ_CAPTURE_CURRENCY);
		psAmountCapture.setProperty("total", HTWPaypalMain.OBJ_CAPTURE_TOTAL);		
		
		HTWPayPalClient client = new HTWPayPalClient();
		client.doInvokeMethod("doCapture", inputs, outputs);
		
		System.out.println("===================================================");
		System.out.println("= Sieble JBS Information Detail                   =");
		System.out.println("===================================================");
		System.out.println("= Input SiebelPropertySet : "); 
		System.out.println("===================================================");
		System.out.println( inputs.toString() );
		System.out.println("===================================================");
		System.out.println("= Output SiebelPropertySet : ");
		System.out.println("===================================================");
		System.out.println( outputs.toString() );
		System.out.println("===================================================");		 
	}
	
	public void testDoHoldCancel(String holdID) throws SiebelBusinessServiceException {
		SiebelPropertySet inputs			= new SiebelPropertySet();
		SiebelPropertySet outputs			= new SiebelPropertySet();
		SiebelPropertySet psSiebelMessage	= new SiebelPropertySet();
		SiebelPropertySet psDoHoldCancel	= new SiebelPropertySet();
		SiebelPropertySet psObjectDetail	= new SiebelPropertySet();
		
		psSiebelMessage	.setType("SiebelMessage");
		psDoHoldCancel	.setType("PaypalDoVoid");
		psObjectDetail	.setType("object_detail");
		
		inputs			.addChild(psSiebelMessage);
		psSiebelMessage	.addChild(psDoHoldCancel);
		psDoHoldCancel	.addChild(psObjectDetail);
		
		// psObjectDetail
		psObjectDetail.setProperty("paypal_type", HTWPaypalMain.OBJ_DETAIL_TYPE_HOLD_CANCEL);
		psObjectDetail.setProperty("id", holdID);		
				
		HTWPayPalClient client = new HTWPayPalClient();
		client.doInvokeMethod("doVoid", inputs, outputs);
		
		System.out.println("===================================================");
		System.out.println("= Sieble JBS Information Detail                   =");
		System.out.println("===================================================");
		System.out.println("= Input SiebelPropertySet : "); 
		System.out.println("===================================================");
		System.out.println( inputs.toString() );
		System.out.println("===================================================");
		System.out.println("= Output SiebelPropertySet : ");
		System.out.println("===================================================");
		System.out.println( outputs.toString() );
		System.out.println("===================================================");
	}
	
	public void testDoCapture(String holdID) throws SiebelBusinessServiceException {
		SiebelPropertySet inputs			= new SiebelPropertySet();
		SiebelPropertySet outputs			= new SiebelPropertySet();
		SiebelPropertySet psSiebelMessage	= new SiebelPropertySet();
		SiebelPropertySet psObjectDetail	= new SiebelPropertySet();
		SiebelPropertySet psDoCapture		= new SiebelPropertySet();		
		SiebelPropertySet psCapture 		= new SiebelPropertySet();
		SiebelPropertySet psAmountCapture	= new SiebelPropertySet();	
		
		String captureType	= "";
		captureType	= HTWPaypalMain.OBJ_DETAIL_CAPTURE_TYPE_WEB_ISSUE;
		//captureType	= HTWPaypalMain.OBJ_DETAIL_CAPTURE_TYPE_RMA_AR;
		
		psSiebelMessage	.setType("SiebelMessage");
		psDoCapture		.setType("PaypalDoCapture");
		psObjectDetail	.setType("object_detail");
		psCapture		.setType("capture");
		psAmountCapture	.setType("amount_capture");
			
		inputs			.addChild(psSiebelMessage);
		psSiebelMessage	.addChild(psDoCapture);
		psDoCapture		.addChild(psObjectDetail);
		psDoCapture		.addChild(psCapture);
		psCapture		.addChild(psAmountCapture);
	
		// psObjectDetail
		psObjectDetail.setProperty("paypal_type"	, HTWPaypalMain.OBJ_DETAIL_TYPE_CAPTURE);
		psObjectDetail.setProperty("id"				, holdID );	
		
		// psCapture
		psCapture.setProperty("is_final_capture", "Y");
		
		if ( HTWPaypalMain.OBJ_DETAIL_CAPTURE_TYPE_WEB_ISSUE.equals(captureType) ){
			psObjectDetail.setProperty("capture_type"	, HTWPaypalMain.OBJ_DETAIL_CAPTURE_TYPE_WEB_ISSUE	);
			
			// psAmountCapture
			psAmountCapture.setProperty("currency", HTWPaypalMain.OBJ_CAPTURE_CURRENCY);
		} else {
			psObjectDetail.setProperty("capture_type"	, HTWPaypalMain.OBJ_DETAIL_CAPTURE_TYPE_RMA_AR );
			
			// psAmountCapture
			psAmountCapture.setProperty("currency", HTWPaypalMain.OBJ_CAPTURE_CURRENCY);
			psAmountCapture.setProperty("total", HTWPaypalMain.OBJ_CAPTURE_TOTAL);
		}
				
		HTWPayPalClient client = new HTWPayPalClient();
		client.doInvokeMethod("doCapture", inputs, outputs);
		
		System.out.println("===================================================");
		System.out.println("= Sieble JBS Information Detail                   =");
		System.out.println("===================================================");
		System.out.println("= Input SiebelPropertySet : "); 
		System.out.println("===================================================");
		System.out.println( inputs.toString() );
		System.out.println("===================================================");
		System.out.println("= Output SiebelPropertySet : ");
		System.out.println("===================================================");
		System.out.println( outputs.toString() );
		System.out.println("===================================================");

	}
	
	public void testDoRefund(String captureID) throws SiebelBusinessServiceException {
		SiebelPropertySet inputs			= new SiebelPropertySet();
		SiebelPropertySet outputs			= new SiebelPropertySet();
		SiebelPropertySet psSiebelMessage	= new SiebelPropertySet();
		SiebelPropertySet psDoRefund		= new SiebelPropertySet();
		SiebelPropertySet psObjectDetail	= new SiebelPropertySet();
		//SiebelPropertySet psRefund 		= new SiebelPropertySet();
		//SiebelPropertySet psAmountRefund	= new SiebelPropertySet();
	
		psSiebelMessage	.setType("SiebelMessage");
		psDoRefund		.setType("PaypalDoVoid");
		psObjectDetail	.setType("object_detail");
		//psRefund		.setType("Refund");
		//psAmountRefund.setType("amount_refund");
			
		inputs			.addChild(psSiebelMessage);
		psSiebelMessage	.addChild(psDoRefund);
		psDoRefund		.addChild(psObjectDetail);
		//psDoRefund		.addChild(psRefund);
		//psRefund		.addChild(psAmountRefund);
		
		// psObjectDetail
		psObjectDetail.setProperty("paypal_type"	, HTWPaypalMain.OBJ_DETAIL_TYPE_REFUND);
		psObjectDetail.setProperty("id"				, captureID );	

		// psAmountRefund
		//psAmountRefund.setProperty("currency", HTWPaypalMain.OBJ_CAPTURE_CURRENCY);
		//psAmountRefund.setProperty("total", HTWPaypalMain.OBJ_CAPTURE_TOTAL);
				
		HTWPayPalClient client = new HTWPayPalClient();
		client.doInvokeMethod("doVoid", inputs, outputs);
		
		System.out.println("===================================================");
		System.out.println("= Sieble JBS Information Detail                   =");
		System.out.println("===================================================");
		System.out.println("= Input SiebelPropertySet : "); 
		System.out.println("===================================================");
		System.out.println( inputs.toString() );
		System.out.println("===================================================");
		System.out.println("= Output SiebelPropertySet : ");
		System.out.println("===================================================");
		System.out.println( outputs.toString() );
		System.out.println("===================================================");
	}
}