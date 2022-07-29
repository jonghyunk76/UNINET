package stw.eai.dhl;

import stw.eai.dhl.siebel.STWDhlClient;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessServiceException;

public class RoutingProcessorTestInvoke {

	public RoutingProcessorTestInvoke() {
	}
	
	public static void requestRoute() throws SiebelBusinessServiceException {
		/*------------------------- */
//		ObjectFactory factory = new ObjectFactory();
//		RoutingRequestEA routing = factory.createRoutingRequestEA();		
//		routing.setRequest(DHLCommon.getRequest());
//		routing.setRequestType("O"); //legacy RoutingRequestEARequestType.O
//		routing.setAddress1("Schoorgras 10 Ind. Beringe"); //legacy asp_con_add1
//		routing.setAddress2("Schoorgras 10 Ind. Beringe"); //legacy asp_con_add2
//		routing.setCountryCode("NL"); //legacy asp_con_cc
//		routing.setCountryName("NETHERLANDS"); //legacy asp_con_cn
//		routing.setPostalCode("5986 PK"); //legacy asp_con_zip
//		routing.setCity("Beringe"); //legacy asp_con_city
//		routing.setOriginCountryCode("NL"); //legacy asp_con_cc
//		/*------------------------- */
//
//		String responseMsg =RoutingProcessor.processing(routing);
//		System.out.println(responseMsg);
		
		SiebelPropertySet psInput = new SiebelPropertySet();
		SiebelPropertySet psOutput = new SiebelPropertySet();
		
		psInput.setProperty("Server", "Local");
		
		SiebelPropertySet psSiebelMessage = new SiebelPropertySet();
		psSiebelMessage.setType("SiebelMessage");
		
		SiebelPropertySet psRouting = new SiebelPropertySet();
		psRouting.setType("RouteRequest");
		
		psRouting.setProperty("RequestType", "O");
		psRouting.setProperty("Address1", "Schoorgras 10 Ind. Beringe");
		psRouting.setProperty("Address2", "Schoorgras 10 Ind. Beringe");
		psRouting.setProperty("CountryCode", "NL"); // NL
		psRouting.setProperty("CountryName", "NETHERLANDS");
		psRouting.setProperty("PostalCode", "5986 PK");
		psRouting.setProperty("City", "Beringe");
		psRouting.setProperty("OriginCountryCode", "NL");
		
		psSiebelMessage.addChild(psRouting);
		psInput.addChild(psSiebelMessage);
		
		STWDhlClient sendRouting = new STWDhlClient();
		System.out.println("inputs");
		System.out.println(psInput.toString());
		
		sendRouting.doInvokeMethod("Routing", psInput, psOutput);
		
		System.out.println("outputs");
		System.out.println(psOutput.toString());
	}

}
