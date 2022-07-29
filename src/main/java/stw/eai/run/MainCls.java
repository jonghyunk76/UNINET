package stw.eai.run;

import stw.db.STWDBService;
import stw.eai.common.Common;
import stw.eai.encryption.Encryption;
import stw.eai.jco.JCOClient;
import stw.eai.mySingle.STWMySingleClient;
import stw.eai.paypal.STWPaypalClient;
import stw.eai.utility.STWUtil;

import com.paypal.api.payments.util.GenerateAccessToken;
import com.paypal.core.rest.PayPalRESTException;
import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class MainCls {

	public static void main(String[] args) {
		try {
			paypalDoCapture();
		} catch (SiebelBusinessServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MainCls : "+e.getErrorMessage() +" / "+ e.getErrorCode());
		}
	}
	
	public static void runProcedure() throws SiebelBusinessServiceException
	{
		SiebelPropertySet inputs	= new SiebelPropertySet();
		SiebelPropertySet outputs	= new SiebelPropertySet();
		
		inputs.setProperty("PROCEDURE_NAME"	, "SIEBEL.STW_PROD_SN_CHECK_PROC");
		inputs.setProperty("DATABASE_TNS"	, "STW.DEV");
		SiebelPropertySet param		= new SiebelPropertySet();
		param.setType("Parameter");
		param.setProperty("SN", "A-00001");
		inputs.addChild(param);
		
		STWDBService bs = new STWDBService();
		bs.doInvokeMethod("ProcedureRun", inputs, outputs);
	}
	
	public static void paypalDoVoid() throws SiebelBusinessServiceException
	{
		SiebelPropertySet inputs			= new SiebelPropertySet();
		SiebelPropertySet outputs			= new SiebelPropertySet();
		SiebelPropertySet psSiebelMessage	= new SiebelPropertySet();
		SiebelPropertySet psDoVoid			= new SiebelPropertySet();
		SiebelPropertySet psPayment			= new SiebelPropertySet();
		SiebelPropertySet psPayer			= new SiebelPropertySet();
		SiebelPropertySet psFunding			= new SiebelPropertySet();
		SiebelPropertySet psCard			= new SiebelPropertySet();
		SiebelPropertySet psAddress			= new SiebelPropertySet();
		SiebelPropertySet psTransaction		= new SiebelPropertySet();
		SiebelPropertySet psAmount			= new SiebelPropertySet();
		SiebelPropertySet psDetails			= new SiebelPropertySet();
		
		psSiebelMessage	.setType("SiebelMessage");
		psDoVoid		.setType("PaypalDoVoid");
		psPayment		.setType("Payment");
		psPayer			.setType("payer");
		psFunding		.setType("funding_instruments");
		psCard			.setType("credit_card");
		psAddress		.setType("billing_address");
		psTransaction	.setType("transactions");
		psAmount		.setType("amount");
		psDetails		.setType("details");
		
		inputs			.addChild(psSiebelMessage);
		psSiebelMessage	.addChild(psDoVoid);
		psDoVoid		.addChild(psPayment);
		psPayment		.addChild(psPayer);
		psPayer			.addChild(psFunding);
		psFunding		.addChild(psCard);
		psCard			.addChild(psAddress);
		
		psPayment		.addChild(psTransaction);
		psTransaction	.addChild(psAmount);
		psAmount		.addChild(psDetails);
		
		
		psPayment.setProperty("intent", "authorize");
		
		psPayer.setProperty("payment_method", "credit_card");
		
		psCard.setProperty("number", "4017916124479593");
		psCard.setProperty("cvv2", "874");
		psCard.setProperty("last_name", "Shopper");
		psCard.setProperty("expire_month", "2");
		psCard.setProperty("expire_year", "2019");
		psCard.setProperty("type", "visa");
		psCard.setProperty("first_name", "Joe");
		
		psAddress.setProperty("country_code", "US");
		psAddress.setProperty("line1", "52 N Main ST");
		psAddress.setProperty("line2", "");
		psAddress.setProperty("phone", "821012341234");
		psAddress.setProperty("state", "OH");
		psAddress.setProperty("city", "Johnstown");
		psAddress.setProperty("postal_code", "43210");
		
		psTransaction.setProperty("description", "desc");
		
		psAmount.setProperty("total", "100.02");
		psAmount.setProperty("currency", "USD");
		
		psDetails.setProperty("subtotal", "100.00");
		psDetails.setProperty("tax", "0.01");
		psDetails.setProperty("shipping", "0.01");
		
		STWPaypalClient client = new STWPaypalClient();
		client.doInvokeMethod("doVoid", inputs, outputs);
	}
	
	public static void paypalDoCapture() throws SiebelBusinessServiceException
	{
		SiebelPropertySet inputs = new SiebelPropertySet();
		SiebelPropertySet outputs = new SiebelPropertySet();
		
		SiebelPropertySet psSiebelMsg	= new SiebelPropertySet();
		SiebelPropertySet psDoCapture	= new SiebelPropertySet();
		SiebelPropertySet psPayment 	= new SiebelPropertySet();
		SiebelPropertySet psPayer		= new SiebelPropertySet();
		SiebelPropertySet psFunding		= new SiebelPropertySet();
		SiebelPropertySet psCreditCard	= new SiebelPropertySet();
		SiebelPropertySet psBillAddr	= new SiebelPropertySet();
		SiebelPropertySet psTransaction	= new SiebelPropertySet();
		SiebelPropertySet psAmount		= new SiebelPropertySet();
		SiebelPropertySet psDetail		= new SiebelPropertySet();
		SiebelPropertySet psCapture		= new SiebelPropertySet();
		SiebelPropertySet psAmount_Cap	= new SiebelPropertySet();
		
		psSiebelMsg.setType("SiebelMessage");
		psDoCapture.setType("PaypalDoCapture");
		psPayment.setType("Payment");
		psPayer.setType("payer");
		psFunding.setType("funding_instrument");
		psCreditCard.setType("credit_card");
		psBillAddr.setType("billing_address");
		psTransaction.setType("transaction");
		psAmount.setType("amount");
		psDetail.setType("details");
		psCapture.setType("capture");
		psAmount_Cap.setType("amount_capture");
		
		inputs			.addChild(psSiebelMsg);
		
		psSiebelMsg		.addChild(psDoCapture);
		
		psDoCapture		.addChild(psPayment);
		
		psPayment		.addChild(psPayer);
		psPayer			.addChild(psFunding);
		psFunding		.addChild(psCreditCard);
		psCreditCard	.addChild(psBillAddr);
		
		psPayment		.addChild(psTransaction);
		psTransaction	.addChild(psAmount);
		psAmount		.addChild(psDetail);
		
		psDoCapture		.addChild(psCapture);
		psCapture		.addChild(psAmount_Cap);
		
		
		psPayment.setProperty("intent", "authorize");
		
		psPayer.setProperty("payment_method"	, "credit_card");

		psCreditCard.setProperty("cvv2"			, "874");
		psCreditCard.setProperty("expire_month"	, "2");
		psCreditCard.setProperty("expire_year"	, "2019");
		psCreditCard.setProperty("first_name"	, "Gerrald");
		psCreditCard.setProperty("last_name"	, "Lee");
		psCreditCard.setProperty("number"		, "4017916124479593");
		psCreditCard.setProperty("type"			, "visa");

		psBillAddr.setProperty("city"			, "Goyangsi");
		psBillAddr.setProperty("country_code"	, "KR");
		psBillAddr.setProperty("line1"			, "Janghangdong 718");
		psBillAddr.setProperty("postal_code"	, "43210");
		psBillAddr.setProperty("state"			, "GY");
		
		psTransaction.setProperty("description", "This is the payment transaction description.");

		psAmount.setProperty("currency", "USD");
		psAmount.setProperty("total", "100.02");

		psDetail.setProperty("shipping", "0.01");
		psDetail.setProperty("subtotal", "100.00");
		psDetail.setProperty("tax", "0.01");
		
		psCapture.setProperty("is_final_capture", "Y");

		psAmount_Cap.setProperty("currency", "USD");
		psAmount_Cap.setProperty("total", "55.50");
		
		try{
			STWPaypalClient client = new STWPaypalClient();
			client.doInvokeMethod("doCapture", inputs, outputs);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SiebelBusinessServiceException(e,e.getMessage(),e.getMessage());
		}
		finally{
			System.out.println("return: "+outputs.toString());
		}
		
	}
	
	public static void paypalToken() {
		try {
			String token = GenerateAccessToken.getAccessToken();
			System.out.println("token: " + token);
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getRevision() throws SiebelBusinessServiceException {
		SiebelPropertySet	inputs	= new SiebelPropertySet();
		SiebelPropertySet	outputs	= new SiebelPropertySet();
		
		SiebelPropertySet	psSiebelMessage	= new SiebelPropertySet();
		SiebelPropertySet	psGetRevision	= new SiebelPropertySet();
		SiebelPropertySet	psMisKeys		= new SiebelPropertySet();
		SiebelPropertySet	psMisKey		= new SiebelPropertySet();
		
		psSiebelMessage.setType("SiebelMessage");
		
		psGetRevision.setType("getRevisionByMisId");
		psSiebelMessage.addChild(psGetRevision);
		
		psMisKeys.setType("misKeyWSVOs");
		psGetRevision.addChild(psMisKeys);
		
		psMisKey.setType("MisKeyWSVO");
		psMisKey.setProperty("MisID"	, "HJLEE000020140305150527D11AP0043");
		psMisKey.setProperty("SystemID"	, Common.CONN_ID_AP);
		psMisKeys.addChild(psMisKey);
		
		inputs.addChild(psSiebelMessage);
		
		STWMySingleClient client	= new STWMySingleClient();
		client.doInvokeMethod("getRevisionByMisId", inputs, outputs);
	}
	
	public static void cancelML() throws SiebelBusinessServiceException {
		SiebelPropertySet inputs	= new SiebelPropertySet();
		SiebelPropertySet outputs	= new SiebelPropertySet();
		
		SiebelPropertySet	psSiebelMessage	= new SiebelPropertySet();
		SiebelPropertySet	psCancelMail	= new SiebelPropertySet();
		SiebelPropertySet	psAddrForCancel	= new SiebelPropertySet();
		SiebelPropertySet	psMailLists		= new SiebelPropertySet();
		SiebelPropertySet	resource		= new SiebelPropertySet();
		
		psSiebelMessage.setType("SiebelMessage");
		
		psCancelMail.setType("cancelMISMailByRecipient");
		psCancelMail.setProperty("mtrKey", "2014030401284911398110@crm.system");
		psSiebelMessage.addChild(psCancelMail);
		
		psAddrForCancel.setType("addressForCancel");
		psCancelMail.addChild(psAddrForCancel);
		
		psMailLists.setType("mailLists");
		psMailLists.setValue("siebel.lee@stage.samsung.com");
		psAddrForCancel.addChild(psMailLists);
		
		resource.setType("resourceCSVO");
		resource.setProperty("email", "crm.system@stage.samsung.com");
		resource.setProperty("encoding", "utf-8");
		resource.setProperty("locale", "ko-KR");
		resource.setProperty("password", "ifadmin@01");
		psCancelMail.addChild(resource);
		
		inputs.addChild(psSiebelMessage);
		
		STWMySingleClient client	= new STWMySingleClient();
		client.doInvokeMethod("cancelMISMail", inputs, outputs);
	}
	
	public static void getStatusML() throws SiebelBusinessServiceException {
		SiebelPropertySet inputs	= new SiebelPropertySet();
		SiebelPropertySet outputs	= new SiebelPropertySet();
		
		SiebelPropertySet	psSiebelMessage	= new SiebelPropertySet();
		SiebelPropertySet	psGetMail		= new SiebelPropertySet();
		SiebelPropertySet	psMtrKeys		= new SiebelPropertySet();
		SiebelPropertySet	psMtrKey		= new SiebelPropertySet();
		
		psSiebelMessage.setType("SiebelMessage");
		
		psGetMail.setType("getMailStatusCounts");
		psSiebelMessage.addChild(psGetMail);
		
		psMtrKeys.setType("mtrKeys");
		psGetMail.addChild(psMtrKeys);
		
		psMtrKey.setType("mtrKeys");
		psMtrKey.setValue("2014021310344229063040@crm.system");
		psMtrKeys.addChild(psMtrKey);
		
		inputs.addChild(psSiebelMessage);
		
		STWMySingleClient client	= new STWMySingleClient();
		client.doInvokeMethod("getMailStatus", inputs, outputs);
	}
	
	public static void cancelAp() throws SiebelBusinessServiceException {
		SiebelPropertySet input		= new SiebelPropertySet();
		SiebelPropertySet output	= new SiebelPropertySet();
		
		SiebelPropertySet siebelMessage	= new SiebelPropertySet();
		SiebelPropertySet cancelAp		= new SiebelPropertySet();
		SiebelPropertySet esbAuthVO		= new SiebelPropertySet();
		SiebelPropertySet cancelProc	= new SiebelPropertySet();
		
		siebelMessage.setType("SiebelMessage");
		input.addChild(siebelMessage);
		
		cancelAp.setType("cancelApproval");
		siebelMessage.addChild(cancelAp);
		
		esbAuthVO.setType("esbAuthVO");
		esbAuthVO.setProperty("cID", "");
		esbAuthVO.setProperty("cPW", "");
		cancelAp.addChild(esbAuthVO);
		
		cancelProc.setType("cancelProcessWSVO");
		cancelProc.setProperty("TimeZone"	, "GMT+9");
		cancelProc.setProperty("Opinion"	, "test");
		cancelProc.setProperty("MisID"		, "1932029a20140212145919aD11AP0043");
		cancelProc.setProperty("SystemID"	, "D11AP0043");
		cancelProc.setProperty("CancelDate"	, "20140213150054");
		cancelAp.addChild(cancelProc);
		
		
		STWMySingleClient client	= new STWMySingleClient();
		client.doInvokeMethod("cancelApproval", input, output);
	}
	
	public static void submitAp() throws SiebelBusinessServiceException
	{
		SiebelPropertySet input		= new SiebelPropertySet();
		SiebelPropertySet output	= new SiebelPropertySet();
		
		SiebelPropertySet siebelMessage		= null;
		SiebelPropertySet submitAP			= null;
		SiebelPropertySet startProcessWSVO	= null;
		SiebelPropertySet routeVOs			= null;
		SiebelPropertySet attachVOs			= null;
		
		siebelMessage		= new SiebelPropertySet();
		siebelMessage.setType("SiebelMessage");
		input.addChild(siebelMessage);
		
		submitAP			= new SiebelPropertySet();
		submitAP.setType("submitApproval");
		siebelMessage.addChild(submitAP);
		
		startProcessWSVO	= new SiebelPropertySet();
		startProcessWSVO.setType("startProcessWSVO");
		startProcessWSVO.setProperty("Body"			, "Body Content");
		startProcessWSVO.setProperty("BodyType"		, "1");
		startProcessWSVO.setProperty("CreateDate"	, "20140212141109");
		startProcessWSVO.setProperty("DrmOptionInfo", "pc_count=-1|valid_days=365|use_count=-1|can_view=1|can_print=0|can_save=0|confirm_mail_4in=1|confirm_mail=1");
		startProcessWSVO.setProperty("LocaleInfo"	, "ko_KR.EUC-KR");
		startProcessWSVO.setProperty("MisID"		, "1932029a20140212145919aD11AP0043");
		startProcessWSVO.setProperty("SystemID"		, "D11AP0043");
		startProcessWSVO.setProperty("NotiMail"		, "1");
		startProcessWSVO.setProperty("Priority"		, "0");
		startProcessWSVO.setProperty("Security"		, "0");
		startProcessWSVO.setProperty("TimeZone"		, "GMT+9");
		startProcessWSVO.setProperty("Title"		, "2014 02 17 Test AP");
		startProcessWSVO.setProperty("SendDMS"		, "N");
		startProcessWSVO.setProperty("TCode"		, "");
		startProcessWSVO.setProperty("TName"		, "");
		submitAP.addChild(startProcessWSVO);
		
		routeVOs			= new SiebelPropertySet();
		routeVOs.setType("RouteVOs");
		routeVOs.setProperty("ActionType"	, "0");
		routeVOs.setProperty("Activity"		, "0");
		routeVOs.setProperty("AlarmType"	, "1");
		routeVOs.setProperty("Approved"		, "");
		routeVOs.setProperty("Arbitrary"	, "-1");
		routeVOs.setProperty("Arrived"		, "");
		routeVOs.setProperty("BodyModify"	, "0");
		routeVOs.setProperty("CompCode"		, "D11");
		routeVOs.setProperty("CompName"		, "Samsung Techwin");
		routeVOs.setProperty("DeptCode", "");
		routeVOs.setProperty("DeptName", "");
		routeVOs.setProperty("Duration", "");
		routeVOs.setProperty("Duty", "");
		routeVOs.setProperty("DutyCode", "");
		routeVOs.setProperty("GroupCode", "");
		routeVOs.setProperty("GroupName", "");
		routeVOs.setProperty("MailAddress"	, "crm.system@stage.samsung.com");
		routeVOs.setProperty("Opinion"		, "Opinion Text");
		routeVOs.setProperty("Reserved"		, "");
		routeVOs.setProperty("RouteModify"	, "0");
		routeVOs.setProperty("Sequence"		, "0");
		routeVOs.setProperty("UserID", "");
		routeVOs.setProperty("UserName", "");
		routeVOs.setProperty("Delegated", "");
		routeVOs.setProperty("SocialID", "");
		startProcessWSVO.addChild(routeVOs);
		
		routeVOs			= new SiebelPropertySet();
		routeVOs.setType("RouteVOs");
		routeVOs.setProperty("ActionType"	, "0");
		routeVOs.setProperty("Activity"		, "1");
		routeVOs.setProperty("AlarmType"	, "1");
		routeVOs.setProperty("Approved"		, "");
		routeVOs.setProperty("Arbitrary"	, "-1");
		routeVOs.setProperty("Arrived"		, "");
		routeVOs.setProperty("BodyModify"	, "0");
		routeVOs.setProperty("CompCode"		, "D11");
		routeVOs.setProperty("CompName"		, "Samsung Techwin");
		routeVOs.setProperty("DeptCode", "");
		routeVOs.setProperty("DeptName", "");
		routeVOs.setProperty("Duration", "");
		routeVOs.setProperty("Duty", "");
		routeVOs.setProperty("DutyCode", "");
		routeVOs.setProperty("GroupCode", "");
		routeVOs.setProperty("GroupName", "");
		routeVOs.setProperty("MailAddress"	, "siebel.lee@stage.samsung.com");
		routeVOs.setProperty("Opinion"		, "");
		routeVOs.setProperty("Reserved"		, "");
		routeVOs.setProperty("RouteModify"	, "0");
		routeVOs.setProperty("Sequence"		, "1");
		routeVOs.setProperty("UserID", "");
		routeVOs.setProperty("UserName", "");
		routeVOs.setProperty("Delegated", "");
		routeVOs.setProperty("SocialID", "");
		startProcessWSVO.addChild(routeVOs);
		
		
		attachVOs			= new SiebelPropertySet();
		attachVOs.setType("AttachmentVOs");
		attachVOs.setProperty("file", "C:\\icvupdo.log");
		attachVOs.setProperty("FileName", "icvupdo.log");
		attachVOs.setProperty("Sequence", "1");
		startProcessWSVO.addChild(attachVOs);
		
		STWMySingleClient client	= new STWMySingleClient();
		client.doInvokeMethod("submitApproval", input, output);
	}
	
	public static void util() throws SiebelBusinessServiceException
	{
		SiebelPropertySet inputs	= new SiebelPropertySet();
		SiebelPropertySet outputs	= new SiebelPropertySet();
		
		inputs.setProperty("FilePath", "C:\\icvupdo.zip");
		inputs.setProperty("FileSize", "80000");
		
		STWUtil util	= new STWUtil();
		util.doInvokeMethod("FileToBin", inputs, outputs);
		
		System.out.println(outputs.getValue());
		System.out.println(outputs.getProperty("Result"));
	}
	
	public static void sendMail() throws SiebelBusinessServiceException
	{
		SiebelPropertySet input		= new SiebelPropertySet();
		SiebelPropertySet output	= new SiebelPropertySet();
		
		SiebelPropertySet siebelMessage	= new SiebelPropertySet();
		SiebelPropertySet sendMISMail	= new SiebelPropertySet();
		SiebelPropertySet header		= new SiebelPropertySet();
		SiebelPropertySet recip			= new SiebelPropertySet();
		SiebelPropertySet recips;
		SiebelPropertySet att			= new SiebelPropertySet();
//		SiebelPropertySet atts;
		SiebelPropertySet Resor			= new SiebelPropertySet();
		
		SiebelPropertySet eaiMaster		= new SiebelPropertySet();
		
//		SiebelPropertySet prop;
		input.setProperty("Server", "SSCRMQ01");
		
		sendMISMail.setType("sendMISMail");
		
		sendMISMail.setProperty("BodyStr","asfsadgsdfhgdfh");
		
		header.setType("HeaderHelperCSVO");	
		header.setProperty("subject", "Local JAVA Test");
		header.setProperty("msgType","personal");
		header.setProperty("bHtmlContentCheck","Y");
		header.setProperty("senderMailAddr","siebel.lee@partner.samsung.com");
		header.setProperty("timeZone","GMT+9");	
		header.setProperty("isDst","N");
		header.setProperty("bMhtBody","N");
		header.setProperty("epTrCode","");
		header.setProperty("epTrName","");
		header.setProperty("iFileCount","1");
		
		sendMISMail.addChild(header);
		
		recip.setType("RecipientEtyCSVO");
		
		recips = new SiebelPropertySet();
		recips.setType("recipientEtyCSVOs");
		recips.setProperty("recType","t");
		recips.setProperty("recAddr","siebel.lee@partner.samsung.com");
		recip.addChild(recips);
		
//		recips = new SiebelPropertySet();
//		recips.setType("recipientEtyCSVOs");
//		recips.setProperty("recType","c");
//		recips.setProperty("recAddr","siebel.lee@stage.samsung.com");
//		recip.addChild(recips);
		
		sendMISMail.addChild(recip);
		
		
		att.setType("AttachEtyCSVO");
		
		/*
		atts = new SiebelPropertySet();
		atts.setType("attachEtyCSVOs");
		atts.setProperty("file","C:\\Siebel\\Client_1\\TEMP\\Gerald-PC_6048_7820_0_TempFile.xml");
		atts.setProperty("fileName","Gerald-PC_6048_7820_0_TempFile.xml");
		att.addChild(atts);
		*/
//		
//		atts = new SiebelPropertySet();
//		atts.setType("attachEtyCSVOs");
//		prop=new SiebelPropertySet();prop.setType("file");prop.setValue("C:\\Siebel\\Client_1\\TEMP\\Gerald-PC_6048_7820_0_TempFile.xml");atts.addChild(prop);
//		prop=new SiebelPropertySet();prop.setType("fileName");prop.setValue("Gerald-PC_6048_7820_0_TempFile.xml");atts.addChild(prop);
//		att.addChild(atts);
		
		sendMISMail.addChild(att);
		
		
		Resor.setType("ResourceCSVO");
		
		Resor.setProperty("email","siebel.lee@partner.samsung.com");
		Resor.setProperty("encoding","utf-8");
		Resor.setProperty("locale","ko-KR");
		Resor.setProperty("password","hjyee@mar03");
		sendMISMail.addChild(Resor);
		
		siebelMessage.setType("SiebelMessage");
		siebelMessage.addChild(sendMISMail);
		
		input.addChild(siebelMessage);
		input.addChild(eaiMaster);
		
		System.out.println(siebelMessage.toString());
		
		STWMySingleClient client	= new STWMySingleClient();
		client.doInvokeMethod("SendMail", input, output);
		
		System.out.println(output.toString());
	}
	
	public static void sendMail_D() throws SiebelBusinessServiceException
	{
		SiebelPropertySet input		= new SiebelPropertySet();
		SiebelPropertySet output	= new SiebelPropertySet();
		
		SiebelPropertySet siebelMessage	= new SiebelPropertySet();
		SiebelPropertySet sendMISMail	= new SiebelPropertySet();
		SiebelPropertySet header		= new SiebelPropertySet();
		SiebelPropertySet recip			= new SiebelPropertySet();
		SiebelPropertySet recips;
		SiebelPropertySet att			= new SiebelPropertySet();
//		SiebelPropertySet atts;
		SiebelPropertySet Resor			= new SiebelPropertySet();
		
		SiebelPropertySet eaiMaster		= new SiebelPropertySet();
		
//		SiebelPropertySet prop;
		input.setProperty("Server", "SSCRMD01");
		
		sendMISMail.setType("sendMISMail");
		
		sendMISMail.setProperty("BodyStr","asfsadgsdfhgdfh");
		
		header.setType("HeaderHelperCSVO");	
		header.setProperty("subject", "Local JAVA Test");
		header.setProperty("msgType","personal");
		header.setProperty("bHtmlContentCheck","Y");
		header.setProperty("senderMailAddr","crm.system@stage.samsung.com");
		header.setProperty("timeZone","GMT+9");	
		header.setProperty("isDst","N");
		header.setProperty("bMhtBody","N");
		header.setProperty("epTrCode","");
		header.setProperty("epTrName","");
		header.setProperty("iFileCount","1");
		
		sendMISMail.addChild(header);
		
		recip.setType("RecipientEtyCSVO");
		
		recips = new SiebelPropertySet();
		recips.setType("recipientEtyCSVOs");
		recips.setProperty("recType","t");
		recips.setProperty("recAddr","siebel.lee@stage.samsung.com");
		recip.addChild(recips);
		
//		recips = new SiebelPropertySet();
//		recips.setType("recipientEtyCSVOs");
//		recips.setProperty("recType","c");
//		recips.setProperty("recAddr","siebel.lee@stage.samsung.com");
//		recip.addChild(recips);
		
		sendMISMail.addChild(recip);
		
		
		att.setType("AttachEtyCSVO");
		
		/*
		atts = new SiebelPropertySet();
		atts.setType("attachEtyCSVOs");
		atts.setProperty("file","C:\\Siebel\\Client_1\\TEMP\\Gerald-PC_6048_7820_0_TempFile.xml");
		atts.setProperty("fileName","Gerald-PC_6048_7820_0_TempFile.xml");
		att.addChild(atts);
		*/
//		
//		atts = new SiebelPropertySet();
//		atts.setType("attachEtyCSVOs");
//		prop=new SiebelPropertySet();prop.setType("file");prop.setValue("C:\\Siebel\\Client_1\\TEMP\\Gerald-PC_6048_7820_0_TempFile.xml");atts.addChild(prop);
//		prop=new SiebelPropertySet();prop.setType("fileName");prop.setValue("Gerald-PC_6048_7820_0_TempFile.xml");atts.addChild(prop);
//		att.addChild(atts);
		
		sendMISMail.addChild(att);
		
		
		Resor.setType("ResourceCSVO");
		
		Resor.setProperty("email","crm.system@stage.samsung.com");
		Resor.setProperty("encoding","utf-8");
		Resor.setProperty("locale","ko-KR");
		Resor.setProperty("password","ifadmin#12");
		sendMISMail.addChild(Resor);
		
		siebelMessage.setType("SiebelMessage");
		siebelMessage.addChild(sendMISMail);
		
		input.addChild(siebelMessage);
		input.addChild(eaiMaster);
		
		System.out.println(siebelMessage.toString());
		
		STWMySingleClient client	= new STWMySingleClient();
		client.doInvokeMethod("SendMail", input, output);
		
		System.out.println(output.toString());
	}
	
	public static void encryption()
	{
		SiebelPropertySet inputs = new SiebelPropertySet();
		SiebelPropertySet outputs = new SiebelPropertySet();
		
		inputs.setProperty("Text", "ifadmin@01");
		
		Encryption enc = new Encryption();
		try {
			enc.doInvokeMethod("MD5", inputs, outputs);
		} catch (SiebelBusinessServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(outputs.getProperty("MD5BASE64"));
		System.out.println(outputs.toString());
	}
	
	public static void JCO() {
		// TODO Auto-generated method stub
		JCOClient client = new JCOClient();
		
		SiebelPropertySet psInput	= new SiebelPropertySet();
		SiebelPropertySet psOutput	= new SiebelPropertySet();
		SiebelPropertySet psSiebelMessage = new SiebelPropertySet();
		SiebelPropertySet psEAIMaster	 = new SiebelPropertySet();
		
		psEAIMaster.setType("EAIMasterMsg");
		psSiebelMessage.setType("SiebelMessage");
		
		psEAIMaster.setProperty("Run Mode","SYNCHRONOUS");
		psEAIMaster.setProperty("Business Service Method","");
		psEAIMaster.setProperty("Response IO","");
		psEAIMaster.setProperty("Id","1-UJU3");
		psEAIMaster.setProperty("Enable Log","Y");
		psEAIMaster.setProperty("Interface Id","M2_PSC_0002");
		psEAIMaster.setProperty("Request IO","");
		
		//ZM2PSC_IF_BOS_SND
		/*
		psEAIMaster.setProperty("JCO:Function","ZM2PSC_IF_BOS_SND");
		psEAIMaster.setProperty("JCO:InputParams","IV_PSPID");
		psEAIMaster.setProperty("JCO:OutputParams","EV_MSG");
		psEAIMaster.setProperty("JCO:Tables","T_ZSM2PSC50020");
		psEAIMaster.setProperty("JCO:Fields:T_ZSM2PSC50020","PSPID,MATNR,MAKTX,GROES,MEINS,AMENGE,A_SUM_PRC,TWAER");
		
		psSiebelMessage.setProperty("IV_PSPID", "CS0001");
		*/
		
		//ZM2MMB_SEND_STOCK_LIST
		
		psEAIMaster.setProperty("JCO:Function","ZM2MMB_SEND_STOCK_LIST");
		psEAIMaster.setProperty("JCO:InputParams","IV_BATCH,IV_MATNR,IV_PLANT,IV_SLOC");
		psEAIMaster.setProperty("JCO:OutputParams", "EV_MSGTXT,EV_MSGTY");
		psEAIMaster.setProperty("JCO:Tables", "T_STK,T_SERNR");
		psEAIMaster.setProperty("JCO:Fields:T_SERNR","MATNR,LGORT,CHARG,SOBKZ,LIFNR,POSID,VBELN,POSNR,KUNNR,MENGE,UMENGE,QMENGE,BMENGE,TMENGE,MEINS");
		psEAIMaster.setProperty("JCO:Fields:T_STK","MATNR,LGORT,CHARG,SOBKZ,LIFNR,POSID,VBELN,POSNR,UMENGE,QMENGE,BMENGE,MEINS");
		
		psSiebelMessage.setProperty("IV_BATCH", "");
		psSiebelMessage.setProperty("IV_MATNR", "");
		psSiebelMessage.setProperty("IV_PLANT", "PK70");
		psSiebelMessage.setProperty("IV_SLOC", "CC90");
		
		
		//psEAIMaster.setProperty("OutputParams","MATNR,LGORT,CHARG,SOBKZ,LIFNR,POSID,VBELN,POSNR,MENGE,BMENGE,QMENGE,MEINS,WERKS,MATNR,LGORT,SERNR,CHARG,SOBKZ,LIFNR,VBELN,POSNR,POSID,EV_MSGTY,EV_MSGTXT");
		
		psEAIMaster.setProperty("Business Service","");
		psEAIMaster.setProperty("Proxy Business Service","STW EAI JCO Service");
		psEAIMaster.setProperty("Proxy Business Service Method","RunProcess");
		
		psEAIMaster.setProperty("Interface Type","RFC");
		psEAIMaster.setProperty("Web Service","");
		
		psInput.addChild(psSiebelMessage);
		psInput.addChild(psEAIMaster);
		
		try {
			client.doInvokeMethod("RunProcess", psInput, psOutput);
		} catch (SiebelBusinessServiceException e) {
			// TODO Auto-generated catch block
			System.out.println(psOutput.getProperty("Error"));
			e.printStackTrace();
		}
//		System.out.println(psOutput.getProperty("Error"));
	}
	
	public static void DES() {
//		DES		des	= new DES();
//		String	enc	= des.encryption("jdbc:oracle:thin:@55.200.10.144:1527:CRMOLTPD");
//		
//		String	dec	= des.decryption(enc);
		
		SiebelPropertySet inputs = new SiebelPropertySet();
		SiebelPropertySet outputs = new SiebelPropertySet();
		
		inputs.setProperty("Text", "STWCRM1224!");
		
		Encryption enc = new Encryption();
		try {
			enc.doInvokeMethod("DES", inputs, outputs);
		} catch (SiebelBusinessServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(outputs.getProperty("Result"));
		System.out.println(outputs.toString());
	}
	
	public static void RSA() {
		Encryption enc	= new Encryption();
		try{
			enc.RSA();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
