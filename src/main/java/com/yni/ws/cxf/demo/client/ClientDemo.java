package com.yni.ws.cxf.demo.client;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.axis.utils.XMLUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.yni.fta.common.tools.XmlUtil;

import kr.yni.frame.util.JsonUtil;


public class ClientDemo {
	
	private static Log log = LogFactory.getLog(ClientDemo.class);
	
	private String namespace = "http://service.demo.cxf.ws.yni.com/";
	
	public void testInvoke() throws Exception {
		String endpointAddress = "http://localhost/ws/OrderProcess";
		
		QName serviceName = new QName(namespace, "OrderProcessService");
		QName portName = new QName(namespace, "OrderProcessPort");
		
		Service service = Service.create(serviceName);
		
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
		
		Dispatch dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
		BindingProvider bp = (BindingProvider) dispatch;
		MessageFactory factory = ((SOAPBinding) bp.getBinding()).getMessageFactory();
		
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "if_user");
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "@tomsRhksfl_2");
	    
		// 요청 파라메터 등록
		SOAPMessage request = factory.createMessage();
		SOAPBody body = request.getSOAPBody();
		QName payloadName = new QName(namespace, "getOrder", "ser"); // 매소드 지정
		SOAPBodyElement payload = body.addBodyElement(payloadName);
		
		QName userNoName = new QName("order"); // 파라메터 지정
		SOAPElement param = payload.addChildElement(userNoName);
		
//		param.addTextNode("대유 toms");
		
		SOAPMessage reply = null;
		
		try {
		  reply = (SOAPMessage) dispatch.invoke(request);
		} catch(WebServiceException ex) {
		  ex.printStackTrace();
		}
//		
		body = reply.getSOAPBody();
//		
//		QName responseName = new QName(namespace, "processOrderResponse");
//	    SOAPElement bodyElement = (SOAPElement) body.getChildElements(responseName).next();
//	    String responseMessageText = bodyElement.getTextContent();
//		
//		log.debug(">>>>>>>>>>>>>>>> " + responseMessageText);
		
		if(reply != null) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			reply.writeTo(out);
			
			String soapResult = new String(out.toByteArray(), "UTF-8"); // 응답 XML원본의 문자열을 구한다.
			
			if(out != null) out.close();
			
			org.json.JSONObject xmlJSONObj = XML.toJSONObject(soapResult);
			Map jmap = xmlJSONObj.toMap(); // XML원본에 대한 
			
			Iterator i = body.getChildElements();  
			Node node = (Node) i.next();
			String jsonResult = null;
			String noeStr = node.getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
			
			if(noeStr != null) {
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonValue = (JSONObject) jsonParser.parse(noeStr);
				
				jsonResult = jsonValue.toString(); // 응답XML 내부에서 Json타입의 문자열이 있다면 값을 구한다.
			}
			String textResult = body.getTextContent(); // 응답XML 내부에서 값만 구한다.
			
			log.debug("******************************************************************");
			log.debug("XML = " + soapResult);
			log.debug("text = " + textResult);
			log.debug("Json = " + jsonResult);
			log.debug("******************************************************************");
			
//			Map xmlMap = XmlUtil.xml2Map(XMLUtils.ElementToString(result.getAsDOM()));
		}
			
//		body = reply.gete;
		
		
	}
	
}
