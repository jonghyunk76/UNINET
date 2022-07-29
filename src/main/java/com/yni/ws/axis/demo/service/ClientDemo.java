package com.yni.ws.axis.demo.service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.constants.Style;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.utils.XMLUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.yni.fta.common.tools.XmlUtil;
import com.yni.ws.cxf.demo.vo.OrderVo;

import kr.yni.frame.util.StringHelper;

public class ClientDemo {
	
	private static Log log = LogFactory.getLog(ClientDemo.class);
	
	public Map textInvoke() throws Exception {
		Map rstMap = null;
		Call call = null;
		
		try {
			String endPoint = "http://localhost/ws/OrderProcess";
			String namespace = "http://service.demo.cxf.ws.yni.com/";
			
			SOAPHeaderElement actionHeader = new SOAPHeaderElement("",   "Action");
			actionHeader.setObjectValue("NA");
			
			Service ws = new Service();
			call = (Call) ws.createCall();
			
			call.setTargetEndpointAddress(new URL(endPoint));
			call.setOperation("inquiry");
			call.setOperationStyle(Style.WRAPPED);
			call.addHeader(actionHeader);
			call.setUsername("if_user");
			call.setPassword("@tomsRhksfl_2");
			
//			call.setOperationName(new QName(namespace, "getUserId")); // 수행할 매소드 지정 <wsdl:operation name="getUserId">
//			call.addParameter("userId", XMLType.XSD_STRING, String.class, ParameterMode.IN);
			
			QName order = new QName("order"); // 파라메터 지정
			call.setOperationName(new QName(namespace, "getOrder")); // 수행할 매소드 지정 <wsdl:operation name="getUserId">
			call.addParameter("order", order, OrderVo.class, ParameterMode.IN);
			
			OrderVo vo = new OrderVo();
			
			vo.setOrderNumber("111111");
			
			Object resSOAPBodyElements = call.invoke(new Object [] {vo});
			
			SOAPBodyElement result = null;
			if(resSOAPBodyElements instanceof Vector) {
				Vector resultVal = (Vector) resSOAPBodyElements;
				
				result = (SOAPBodyElement) resultVal.get(0);
			} else if(resSOAPBodyElements instanceof Class) {
				log.debug("result = " + resSOAPBodyElements);
			} else {
				result = (SOAPBodyElement) resSOAPBodyElements;
			} 
			
			if(result != null) {
				log.debug("******************************************************************");
				log.debug( call.getMessageContext().getRequestMessage().getSOAPPartAsString() );
				log.debug("******************************************************************");
				log.debug( XMLUtils.ElementToString(result.getAsDOM()));
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
	
}
