package com.yni.fta.common.handler;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kr.yni.frame.util.StringHelper;

/**
 * FTA HUB - XML Parser
 * 
 * @author YNI-Maker
 *
 */
public class HubDataHandler extends DefaultHandler {
	
	protected static Log log = LogFactory.getLog(HubDataHandler.class);
	
	private SAXParserFactory parserFactory;
    private SAXParser parser;
    
    private Integer itemIndex = -1;
    private Integer ftaIndex = -1;
    private String fileName;
    private String documentType;
    private StringBuffer valueBuf = new StringBuffer();
    private LinkedList<String> eList;
    private ArrayList<Map<String, Object>> dataList;
    
    public HubDataHandler() {
    	this(null);
    }
    
    public HubDataHandler(String fileName) {
    	super();
    	
    	try {
            parserFactory = SAXParserFactory.newInstance();
            parser = parserFactory.newSAXParser();
            
            this.fileName = fileName;
            
            if(this.dataList == null) this.dataList = new ArrayList<Map<String, Object>>();
        } catch(Exception e) {
            if(log.isErrorEnabled()) log.error("Exception(method-HubDataHandler) >> " + e);
        }
    }
    
    public ArrayList<Map<String, Object>> getData() {
    	try {
    		FileInputStream fis = new FileInputStream(fileName);
    		
            parser.parse(fis, this);
        } catch(Exception e) {
        	if(log.isErrorEnabled()) log.error("Exception(method-getData) >> " + e);
        }
    	
        return dataList;
    }
    
    /**
     * Start of the document
     */
    public void startDocument(){
    	if (log.isDebugEnabled()) {
			log.debug("parsing a submit map file...");
		}
    	
    	eList = new LinkedList<String>();
    }
    
    /**
     * End of the document
     */
    public void endDocument(){
    	if (log.isDebugEnabled()) {
			log.debug("parsing the submit map has completed.");
		}
    }
    
    /**
     * This will be called when the tags of the XML start.
     * 
     * @param uri
     * @param localName Element's Name(uri)
     * @param qName Element's Name(non-uri)
     * @param attributes Attribute in a element
     * @throws SAXException
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	String startElement = null;
    	
    	if(StringHelper.null2void(uri).isEmpty()) {
    		startElement = qName.trim();
    	} else {
    		startElement = localName.trim();
    	}
    	
    	if(startElement.equals("rsm:FTABusinessDocument")) {
    		this.documentType = attributes.getValue("xmlns:rsm");
    	} else if(startElement.equals("rsm:RequestVerificationOfOriginTradeLineItem")) { //N개의 Item 등록을 위한 구분 인덱스
    		itemIndex++;
		} else if(startElement.equals("ram:AgreementTypeCode")) { // N개의 협정 등록을 위한 구분 인덱스
    		ftaIndex++;
		}
    	
    	eList.addLast(startElement);
    }
    
    /**
     * This will be called when the tags of the XML end.
     * 
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	String endElement = null;
    	
    	if(StringHelper.null2void(uri).isEmpty()) {
    		endElement = qName.trim();
    	} else {
    		endElement = localName.trim();
    	}
    	
    	if(valueBuf.length() > 0) {
    		String val = valueBuf.toString().trim();
    		
    		if(!val.isEmpty()) {
	    		Map<String, Object> dataMap = new HashMap<String, Object>();
	    		
	    		StringBuffer path = new StringBuffer();
	    		
	    		for(int i = 0; i < eList.size(); i++) {
	    			path.append("/");
	    			path.append(StringHelper.null2void(eList.get(i)).trim());
	    		}
	    		
	    		String chPath = path.toString();
	    		Integer index = 0;
	    		
	    		// 동일한 XML 경로가 존재하는 체크해서 index를 구한다.
	    		for(int i = 0; i < dataList.size(); i++) {
	    			if(chPath.equals(StringHelper.null2void(dataList.get(i).get("PATH")))) {
	    				index++;
	    			}
	    		}
	    		
	    		dataMap.put("INDEX", index);
	    		dataMap.put("VALUE", val);
	    		dataMap.put("PATH", chPath);
	    		// 현대차 허브용 정보
	    		dataMap.put("ITEM_INDEX", itemIndex);
	    		dataMap.put("FTA_INDEX", ftaIndex);
	    		
	    		//log.debug("[index=" + index + ", item = " + itemIndex + ", fta = " + ftaIndex + " ] " + path + " = " + val);
	    		
	    		dataList.add(dataMap);
    		}
    	}
    	
    	if(endElement.equals("ram:CountryOfOriginDecisionExaminationRequest")){
			ftaIndex = -1;
		} else if(endElement.equals("rsm:RequestVerificationOfOrigin")) {
			itemIndex = -1;
			ftaIndex = -1;
		}
    	
    	valueBuf.setLength(0);
    	if(eList.size() > 0) eList.removeLast();
    }
    
    /**
     * This is called to get the tags value
     **/
    public void characters(char[] ch, int start, int length) throws SAXException {
        valueBuf.append(ch, start, length);
    }
    
    /**
     * 문서타입 리턴
     * @return
     */
    public String getDocumentType() {
    	return this.documentType;
    }
    
}
