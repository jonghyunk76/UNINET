/**
 * @author ally
 */
package stw.eai.dhl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import stw.eai.common.Common;
import stw.eai.dhl.redefine.DHLClientReDef;
import stw.eai.dhl.redefine.LabelReportHandlerReDef;
import stw.eai.dhl.xml.datatypes.Condition;
import stw.eai.dhl.xml.request.ShipmentValidateRequestEA;
import stw.eai.dhl.xml.response.ErrorResponseMsg;

public class ShipValidGenerator {

	/**
	 * Create ShipmentValidateRequest.xml
	 * 
	 * @param shipValReq
	 * @return ShipmentValidateRequest.xml File AbsolutePath
	 * @throws Exception
	 */
	private String createRequestXML(ShipmentValidateRequestEA shipValReq) throws Exception {
		DateFormat today = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_SSS");
		String requestPath = Common.REQUEST_PATH;
		String requestFileName = "ShipmentValidateRequest" + "_" + today.format(new java.util.Date()) + ".xml";
		File requestXML = new File(requestPath + requestFileName);
		try {
			JAXBContext jc = JAXBContext.newInstance(Common.XML_CONTEXT_PATH);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty("jaxb.encoding", "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
			marshaller.marshal(shipValReq, new FileOutputStream(requestXML));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Fail job - createRequestXML \n" + e.getMessage());
		}
		return requestXML.getAbsolutePath();
	}

	/**
	 * Create ShipmentValidateResponse.xml
	 * 
	 * @param requestFilePath
	 * @return ShipmentValidateResponse.xml File AbsolutePath
	 */
	private String createResponeXML(String requestFilePath) throws Exception {
		String responseFilePath = null;
		try {
			DHLClientReDef dhlClient = new DHLClientReDef();
			System.out.println("createResponeXML.requestFilePath:::" + requestFilePath);
			responseFilePath = dhlClient.getShipValidResponseXML(requestFilePath, Common.getServletURL(), Common.XML_FILE_PATH);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Fail job - createResponeXML \n" + e.getMessage());
		}
		return responseFilePath;
	}

	/**
	 * Create AWBNumber.pdf 
	 *    And Change Location of ShipmentValidateResponse.xml(DHLCommon.XML_FILE_PATH => DHLCommon.PROCESSED_XML_FILE_PATH)
	 * 
	 * @param responseFilePath
	 * @return AWBNumber
	 * @throws Exception
	 */
	private String createPDFReport(File responseFile) throws Exception {
		String awbNumber = null;
		String movedFilePath = Common.PROCESSED_XML_FILE_PATH + responseFile.getName();
		try {
			LabelReportHandlerReDef labelReportHandler = new LabelReportHandlerReDef();
			awbNumber = labelReportHandler.generate(responseFile.getAbsolutePath(), Common.RESPONSE_PATH);
			File processedfile = new File(movedFilePath);
			responseFile.renameTo(processedfile);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new Exception("Fail job - createPDFReport \n"	+ e.getMessage());
		}
		return awbNumber;
	}

	/**
	 * Process ShipmentValidate 
	 * 1. createRequestXML => Create ShipmentValidateRequest_yyyy_MM_dd_hh_mm_ss_SSS.xml 
	 * 2. createResponeXML => Create ShipmentValidateResponse_yyyy_MM_dd_hh_mm_ss_SSS.xml 
	 * 3. createPDFReport => Create AWBNumber.pdf and Change Location of ShipmentValidateResponse.xml 
	 * 
	 * @return ResultShipValid => if(success) { AwbNumber & ResultDescription} else { null & ErrorDescription }
	 */
	public static ShipValidGeneratorResult work(ShipmentValidateRequestEA shipValReq) {
		ShipValidGenerator gen = new ShipValidGenerator();
		ShipValidGeneratorResult result = new ShipValidGeneratorResult();
		String blank = "";
		try {
			String requestFilePath = gen.createRequestXML(shipValReq);
			System.out.println("requestFilePath:::" + requestFilePath);
//			Setup Proxy 
//          JVM Option -Dhttp.proxyHost=55.200.250.10 -Dhttp.proxyPort=8080
//			OR
			System.setProperty("proxySet"	,Common.getProxySet());
			System.setProperty("proxyHost"	,Common.getProxyHost());
			System.setProperty("proxyPort"	,Common.getProxyPort());
			String responseFilePath = gen.createResponeXML(requestFilePath);
			File responseFile = new File(responseFilePath);
			if(responseFile.getName().contains("Error")) {
				
				//Stream Read File
				FileInputStream fis = new FileInputStream(responseFile);
				InputStreamReader isr = new InputStreamReader(fis,"UTF8");
				BufferedReader rd = new BufferedReader(isr);
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = rd.readLine()) != null) {
					sb.append(line).append("\n");
				}
				StringReader responseXML = null;
				responseXML = new StringReader(sb.toString());
				System.out.println("ShipValidGenerator.work : " + sb.toString());
				
				String errDef = sb.toString().split("<ConditionCode>")[1].split("</ConditionData>")[0];
				String errCode	= errDef.split("</ConditionCode>")[0];
				String errMsg	= errDef.split("<ConditionData>")[1];
				
				result.setAwbNumber("");
				result.setResultDescription(errCode + ": " + errMsg);
			} else {
				String awbNumber = gen.createPDFReport(responseFile);
				result.setAwbNumber(awbNumber);
				result.setResultDescription("Validated : AWB is[ " + awbNumber + " ]. Response Data Saved. ");	
			}
		} catch (Exception e) {
			result.setAwbNumber(blank);
			result.setResultDescription("Error : " + e.getMessage());
		}
		return result;
	}

}
