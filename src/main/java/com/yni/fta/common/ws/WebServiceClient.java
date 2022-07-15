package com.yni.fta.common.ws;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.constants.Style;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.utils.XMLUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.yni.fta.common.tools.FTAPassSupporter;
import com.yni.fta.common.tools.XmlUtil;

import kr.yni.frame.Constants;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;

/**
 * FTA PASS 연계를 위한 웹서비스 클라이언트 클래스
 * 
 * @author ador2
 *
 */
public class WebServiceClient {
	
	private static Log log = LogFactory.getLog(WebServiceClient.class);
	
	// 가입사 정보 수신
	private final static String WS_INQUIRY_INFO_RUL = "http://www.ftapass.or.kr:7788/WebServices/services/FTAPASSInquiryService";
	// 가입사 리스트 조회
    private final static String WS_INQUIRY_LIST_URL = "http://www.ftapass.or.kr:7788/WebServices/services/XmlListInquiryService";
	// 원산지 확인서 수신
	private final static String WS_COMMUNICATE_URL = "http://www.ftapass.or.kr:7788/WebServices/services/RequestXmlService";
	// 원산지 확인서 요청서 전송
	private final static String WS_DOC_DATA_URL = "http://www.ftapass.or.kr:7788/WebServices/services/XmlDataService";
	
	/**
	 * 업체에서 가져가야 할 전자문서의 목록을 반환합니다.<b>
	 * - 가입사 정보 수신
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static Map getInquiryInfo(Map map) throws Exception {
		Map rstMap = null;
		Call call = null;
		
		try{
			log.debug("FTAPASSInquiry Start...");
			
			SimpleDateFormat _secformatter = new SimpleDateFormat ("yyyyMMddhhmmssSSS");
			Date currentTime = new Date();
			String _ssecdateString = _secformatter.format(currentTime);
			String messageid = _ssecdateString;
			
			String endPoint = WS_INQUIRY_INFO_RUL;    // PASS
			
			String companyCode = StringHelper.null2void(map.get("PASS_COMPANY_CD")); // PASS 가입업체 코드
			String bizNum = StringHelper.null2void(map.get("BUSINESS_NO")); // 사업자 등록번호
			
			SOAPHeaderElement actionHeader = new SOAPHeaderElement("",   "Action");
			actionHeader.setObjectValue("NA");
			
			Service ws = new Service();
			call = (Call) ws.createCall();
			call.setTargetEndpointAddress(new URL(endPoint));
			call.setOperation("inquiry");
			call.setOperationStyle(Style.WRAPPED);
			call.addHeader(actionHeader);

			SOAPBodyElement[] reqSOAPBodyElements = new SOAPBodyElement[1];
			
		    DocumentImpl newdoc = new DocumentImpl();
		    Element rootElement = newdoc.createElement("FTAPASSInquiryRequest");
			
			Element cmpCdEle = newdoc.createElement("CompanyCode");
			cmpCdEle.appendChild(newdoc.createTextNode(companyCode));
			Element bizNumEle = newdoc.createElement("BizNum");
			bizNumEle.appendChild(newdoc.createTextNode(bizNum));
		    
			rootElement.appendChild(cmpCdEle);
			rootElement.appendChild(bizNumEle);
			newdoc.appendChild(rootElement);
			
			reqSOAPBodyElements[0] = new SOAPBodyElement(newdoc.getDocumentElement());
			
			Vector resSOAPBodyElements=  (Vector) call.invoke(new Object [] {reqSOAPBodyElements[0]});
			SOAPBodyElement result =(SOAPBodyElement) resSOAPBodyElements.get(0);
			
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("C:/TEMP/FTA_PASS_COMPANY_INFO.xml")));
//			bos.write(result.getAsString().getBytes());
//			bos.close();
//			
			log.debug("******************************************************************");
			log.debug( call.getMessageContext().getRequestMessage().getSOAPPartAsString() );
			log.debug("******************************************************************");
			log.debug( XMLUtils.ElementToString(result.getAsDOM()));
			
			Map xmlMap = XmlUtil.xml2Map(XMLUtils.ElementToString(result.getAsDOM()));
			
			// 문서함 번호 추출
			Map element1 = (Map) xmlMap.get("FTAPASSInquiryResponse");
			Map element2 = new HashMap();
			
			Object obj2 = element1.get("ResultCount");
			if(obj2 instanceof Map) {
				element2 = (Map) obj2;
			} else if(obj2 instanceof List) {
				element2 = ((List<Map>) obj2).get(0);
			}
			
			if(!StringHelper.null2void(element2.get("SRID")).isEmpty()) {
				rstMap = new HashMap();
				
				rstMap.put("PASS_DOCBOX_NO", element2.get("SRID"));
				rstMap.put("PASS_COMPANY_CD", map.get("PASS_COMPANY_CD"));
				rstMap.put("COMPANY_CD", map.get("COMPANY_CD"));
				rstMap.put("VENDOR_CD", map.get("VENDOR_CD"));
			}
		} catch(Exception e){
			log.debug("### Send Fail ###");
			log.debug("******************************************************************");
			
			try {
				log.debug( call.getMessageContext().getRequestMessage().getSOAPPartAsString() );
			} catch (AxisFault e1) {
				throw e1;
			}
			
			throw e;
		}
		
		return rstMap;
	}
	
	/**
	 * 업체에서 가져가야 할 전자문서의 목록을 반환합니다.<b>
	 * - 확인서 요청 리스트 수신
	 * - http://www.ftapass.or.kr:7788/WebServices/services/XmlListInquiryService
	 * - 2021.02.15 : 송수신 테스트 완료
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static List getInquiryList(Map map) throws Exception {
		Call call = null;
		List rstList = new LinkedList();
		
		try{
			log.debug("XmlListInquiryService start...");
			
			SimpleDateFormat _secformatter = new SimpleDateFormat ("yyyyMMddhhmmssSSS");
			Date currentTime = new Date();
			String _ssecdateString = _secformatter.format(currentTime);
			String messageid = _ssecdateString;
			String endPoint = WS_INQUIRY_LIST_URL; // PASS
			String companyCd = StringHelper.null2void(map.get("SENDER_PASS_ID")); // 확인서 수신처의 Pass 가입코드
			String senderId = StringHelper.null2void(map.get("SENDER_DOCBOX_NO"));
			String receiverId = StringHelper.null2string(map.get("RECEIVER_DOCBOX_NO"), StringHelper.null2void(map.get("PASS_DOCBOX_NO")));
			String reqDocName = "5WH"; // F100:테스트 하면서 입력한 문서명
			
			Service ws = new Service();
			call = (Call)ws.createCall();
			call.setTargetEndpointAddress(new URL(endPoint));
			call.setOperation("Inquiry");
			call.setOperationStyle(Style.WRAPPED);
			//call.setTimeout(9000000);
			call.setEncodingStyle("UTF-8");

	        SOAPHeaderElement cmpncd = new SOAPHeaderElement("",   "CompanyCode");
	        SOAPHeaderElement senderid = new SOAPHeaderElement("",   "SenderId");
	        SOAPHeaderElement receiverid = new SOAPHeaderElement("",   "ReceiverId");
	        SOAPHeaderElement docName = new SOAPHeaderElement("",   "RequestDocName");
	        SOAPHeaderElement returnCount = new SOAPHeaderElement("",   "ReturnCount");
	        SOAPHeaderElement pageNo = new SOAPHeaderElement("",   "PageNo");
	        
	        cmpncd.setObjectValue(companyCd);
	        log.debug("Docuemnt name = " + reqDocName + ", Company code = " + companyCd + ", Sender ID = " + senderId + ", Receiver ID = " + receiverId);
	        senderid.setObjectValue(senderId); // 확인서를 수신할 업체의 문서함 번호(자사)
	        receiverid.setObjectValue(receiverId); // 확인서 보내줄  업체의 문서함 번호(협력사)
	        
	        docName.setObjectValue(reqDocName); // 원산지 확인서
	        returnCount.setObjectValue(100);
	        pageNo.setObjectValue(1);
	        
	        call.addHeader(cmpncd);
	        call.addHeader(senderid);
	        call.addHeader(receiverid);
	        call.addHeader(docName);
	        call.addHeader(returnCount);
	        call.addHeader(pageNo);
	        
	        String outputFolder = Constants.APPLICATION_REAL_PATH + "/upload/xml/";
	        
	        File Files = new File(outputFolder + "XML_FPK001_001.xml");	//빈 파일 아무거나
	        FileInputStream fis = new FileInputStream(Files);
            
			SOAPBodyElement[] reqSOAPBodyElements = new SOAPBodyElement[1];
			reqSOAPBodyElements[0] = new SOAPBodyElement(XMLUtils.newDocument(fis).getDocumentElement());
			
			log.debug("===========================================================");
			log.debug(XMLUtils.ElementToString(reqSOAPBodyElements[0].getAsDOM()));
			log.debug("===========================================================");
			
			Vector resSOAPBodyElements=  (Vector) call.invoke(new Object [] {reqSOAPBodyElements[0]});
			SOAPBodyElement result =(SOAPBodyElement) resSOAPBodyElements.get(0);
			
			log.debug("******************************************************************");
			log.debug( call.getMessageContext().getRequestMessage().getSOAPPartAsString() );
			log.debug("******************************************************************");
			log.debug( XMLUtils.ElementToString(result.getAsDOM()));
			
			Map xmlMap = XmlUtil.xml2Map(XMLUtils.ElementToString(result.getAsDOM()));
			
			// 문서함 번호 추출
			Map element1 = (Map) xmlMap.get("XMLListInquiryResponse");
			
			Object obj2 = element1.get("DocItem");
			
			if(obj2 instanceof Map) {
				Map objMap = (Map) obj2;
				
				log.debug("Recieve info. = " + objMap.toString());
				
				rstList.add(objMap);
			} else if(obj2 instanceof List) {
				List<Map> objList = (List<Map>) obj2;
				
				for(int i = 0; i < objList.size(); i++) {
					Map objMap = objList.get(i);
					
					log.debug("Recieve info. = " + objMap.toString());
					
					rstList.add(objMap);
				}
			}
		} catch(Exception e) {
			e.printStackTrace(System.err);
			log.debug("### Send Fail ###");
			
			throw e;
		}
		
		return rstList;
	}
	
	/**
	 * 원산지 정보원에 있는 전자문서를 요청하여 반환 받은 메소드
	 * - 확인서 수신
	 * - 2021.02.15 : 송수신 테스트 완료
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static File getResponseXML(Map map) throws Exception {
		Call call = null;
		File outFile = null; 
				
		try{
			log.debug("RequestXmlService start...");
			
			SimpleDateFormat _secformatter = new SimpleDateFormat ("yyyyMMddhhmmssSSS");
			Date currentTime = new Date();
			String _ssecdateString = _secformatter.format(currentTime);
			String messageid = _ssecdateString;
			String companyCd = StringHelper.null2void(map.get("SENDER_PASS_ID"));
			String senderId = StringHelper.null2void(map.get("SENDER_DOCBOX_NO"));
			String receiverId = StringHelper.null2string(map.get("RECEIVER_DOCBOX_NO"), StringHelper.null2void(map.get("PASS_DOCBOX_NO")));
			String coDocNo = StringHelper.null2void(map.get("CO_DOC_NO")); // PASS에서 발급 후 발신한 확인서 발급번호(요청서 발급번호와는 무관하기 때문에, PASS에서 발급 시 일치시켜야 함)
			String reqDocName = "5WH";
			String endPoint = WS_COMMUNICATE_URL; // PASS
			
			Service ws = new Service();
			call = (Call)ws.createCall();
			call.setTargetEndpointAddress(new URL(endPoint));
			call.setOperation("Response");
			call.setOperationStyle(Style.WRAPPED);
			call.setTimeout(9000000);
			call.setEncodingStyle("UTF-8");
			
	        SOAPHeaderElement cmpncd = new SOAPHeaderElement("",   "CompanyCode");
	        SOAPHeaderElement senderid = new SOAPHeaderElement("",   "SenderId");
	        SOAPHeaderElement receiverid = new SOAPHeaderElement("",   "ReceiverId");
	        SOAPHeaderElement requestId = new SOAPHeaderElement("",   "RequestId");
	        SOAPHeaderElement requestDocName = new SOAPHeaderElement("",   "RequestDocName");
	        SOAPHeaderElement sendType = new SOAPHeaderElement("",   "M_BSEND_RESULT");
	        
	        
	        cmpncd.setObjectValue(companyCd);
	        log.debug("Docuemnt name = " + reqDocName + ", Company code = " + companyCd + ", Sender ID = " + senderId + ", Receiver ID = " + receiverId + ", Doc id = " + coDocNo);
	        senderid.setObjectValue(senderId); // 확인서를 수신할 업체의 문서함 번호(자사)
	        receiverid.setObjectValue(receiverId); // 확인서 보내줄  업체의 문서함 번호(협력사)
	        requestId.setObjectValue(coDocNo);  // 원산지 확인서 증명번호
	        requestDocName.setObjectValue(reqDocName); // 확인서 요청서
	        sendType.setObjectValue("T");
	        
	        call.addHeader(cmpncd);
	        call.addHeader(senderid);
	        call.addHeader(receiverid);
	        call.addHeader(requestId);
	        call.addHeader(requestDocName);
	        call.addHeader(sendType);

	        String inputFolder = Constants.APPLICATION_REAL_PATH + "/upload/xml/";
	        String outputFolder = StringHelper.null2void(map.get("OUT_FILE_PATH"));
	        
	        FileUtil.makeDirectrory(outputFolder); // 폴더가 없으면 생성한다.
	        
	        File Files = new File(inputFolder + "XML_FPK001_001.xml");	//빈 파일 아무거나
	        //File Files = new File("C:/TEMP/FTA_PASS_COMPANY_INFO.xml");	// 확인서 제출 리스트
	        
	        FileInputStream fis = new FileInputStream(Files);
            
			SOAPBodyElement[] reqSOAPBodyElements = new SOAPBodyElement[1];
			reqSOAPBodyElements[0] = new SOAPBodyElement(XMLUtils.newDocument(fis).getDocumentElement());
			
			Vector ret = (Vector) call.invoke(new Object [] {reqSOAPBodyElements[0]});
			SOAPBodyElement result =(SOAPBodyElement) ret.get(0);
			
			// XML파일로 저장하기
			String fileName = UUID.randomUUID().toString() + ".xml";
			String filePath =  outputFolder + fileName;
			outFile = new File(filePath);
			
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));
			bos.write(result.getAsString().getBytes());
			bos.close();
			
//			map.put("XML_FILE_NAME", fileName);
//	        map.put("XML_REAL_FILE", FileUtil.getBytesFromFile(outFile));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			log.debug("### Send Fail ###");
			
			throw e;
		}
		
		return outFile;
	}
	
	/**
	 * 업체의 전자문서를 FTA유통망으로 전송합니다.
	 * - 확인서 요청서
	 * 
	 * @param map  요청서 마스터 정보
	 * @param list 요청서 자재 리스트
	 * @return
	 * @throws Exception
	 */
	public static boolean getRecieveXML(Map map, List list) throws Exception {
		boolean success = true;
		Call call = null;
		
		try {
			log.debug("XmlDataService Start");
			
			SimpleDateFormat _secformatter = new SimpleDateFormat ("yyyyMMddhhmmssSSS");
			Date currentTime = new Date();
			String _ssecdateString = _secformatter.format(currentTime);
			String messageid = _ssecdateString;
			String reqDocName = "F103";
			String endPoint = WS_DOC_DATA_URL; 

			Service ws = new Service();
			call = (Call)ws.createCall();
			call.setTargetEndpointAddress(new URL(endPoint));
			call.setOperation("xmlDocumentRecv");
			call.setOperationStyle(Style.WRAPPED);
			
	        SOAPHeaderElement cmpncd = new SOAPHeaderElement("",   "CompanyCode");
	        SOAPHeaderElement senderid = new SOAPHeaderElement("",   "SenderId");
	        SOAPHeaderElement receiverid = new SOAPHeaderElement("",   "ReceiverId");
	        SOAPHeaderElement docName = new SOAPHeaderElement("",   "DocName");
	        
	        cmpncd.setObjectValue(map.get("SENDER_PASS_ID"));
	        senderid.setObjectValue(map.get("SENDER_DOCBOX_NO"));
	        receiverid.setObjectValue(map.get("RECEIVER_DOCBOX_NO"));
	        docName.setObjectValue(reqDocName);

	        call.addHeader(cmpncd);
	        call.addHeader(senderid); // 확인서를 요청하는 업체의 문서함 번호(자사)
	        call.addHeader(receiverid);  // 확인서 요청를 수신할 문서함 번호(협력사)
	        call.addHeader(docName);
	        
	        log.debug("Docuemnt name = " + reqDocName + ", Sender doc no = " + map.get("SENDER_DOCBOX_NO") + ", Receiver doc no = " + map.get("RECEIVER_DOCBOX_NO"));
	        
	        String fileName = FTAPassSupporter.createCOVerificationRequest2XML(map, list);
	        String outputFolder = Constants.APPLICATION_REAL_PATH + "/upload/Temp/";
	        
	        File Files = new File(outputFolder + fileName);
	        FileInputStream fis = new FileInputStream(Files);
	        
	        log.debug("File info. = " + Files.exists());
	        
			SOAPBodyElement[] reqSOAPBodyElements = new SOAPBodyElement[1];
			reqSOAPBodyElements[0] = new SOAPBodyElement(XMLUtils.newDocument(fis).getDocumentElement());
			
			Vector resSOAPBodyElements=  (Vector) call.invoke(new Object [] {reqSOAPBodyElements[0]});
			
			SOAPBodyElement result =(SOAPBodyElement) resSOAPBodyElements.get(0);
			String returnValue = XMLUtils.ElementToString(result.getAsDOM());
			
			if(returnValue.indexOf("OK") > -1){
				map.put("XML_FILE_NAME", fileName);
		        map.put("XML_REAL_FILE", FileUtil.getBytesFromFile(Files));
		        
				success = true;
				log.debug("xml = " + returnValue);
			}else { 
				success = false;
				log.debug("### WEB Send Fail ###");
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
			log.debug("### Send Fail ###");

			throw e;
		}
		
		return success;
	}
	
}
