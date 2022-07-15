package com.yni.fta.fm.sm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.common.logger.InterfaceLogger;
import com.yni.fta.common.tools.WebsocketSupporter;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * Salseforce 인터페이스 클래스
 * 
 * @author YNI-Framework
 *
 */
@Controller
public class SM8001Ctrl extends YniAbstractController {

    @Resource(name = "SM8001")
    private SM8001 SM8001;

    /**
     * Salesforce 인터페이스(RESTFull) Test 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/SM8001_01")
    public ModelAndView SM8001_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-8001_01", dataMap);
    }
    
    /**
     * 서버간 연계 Test
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/SM8001_02")
    public ModelAndView SM8002_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-8001_02", dataMap);
    }
    
    /**
     * Access Token 구하기
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */   
    @RequestMapping("/fm/sm/SM8001_01/selectAccessToken")
    public ModelAndView selectAccessToken(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = new HashMap();
        String message = null;

        try {
        	String token = SM8001.selectAccessToken(DataMapHelper.getMap(dataMap));
        	
        	if(token != null) {
        		resultMap.put("ACCESS_TOKEN", token);
        	}
        	
//        	ActiveMQSupporter mq = new ActiveMQSupporter();
//        	mq.getMessage("tcp://localhost:61616", StringHelper.null2void("IF-WS-ACTIVITY-VIEW-001"));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(resultMap, message);
    }
	
    /**
     * Salesforce 데이터 요청
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */   
    @RequestMapping("/fm/sm/SM8001_01/executeInterface")
    public ModelAndView executeInterface(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resulMap = null;
        String message = null;
        
        try {
        	long stime = System.currentTimeMillis();
        	
        	Map map = DataMapHelper.getMap(dataMap);
        	resulMap = SM8001.executeInterface(map);
        	
        	String params = StringHelper.null2void(map.get("REQ_PARAMS"));
        	Map pmap = JsonUtil.getMap(params);
        	
        	log.debug("result code = " + resulMap.get("resultCode") +", message = " + resulMap.get("resultMsg"));
        	
        	long ftime = System.currentTimeMillis();
        	
        	// 중계서버인 경우 실행
	    	if(Constants.APPLICATION_SYSTEM_ID.equals("RS")) {
	    		// 클라이언트 요청경로를 접속중인 모든 클라이언트에게 Websocket 메시지를 보낸다.
		    	List<Session> clients = WebsocketSupporter.clients;
		    	
		    	for(Session s : clients) {
		    		WebsocketSupporter ws = new WebsocketSupporter();
		    		
		    		ws.handleMessage("MMA001_07"+"[REL]"+ InterfaceLogger.getRealtimeMessage(req, "R", StringHelper.null2void(pmap.get("Interface_id")), StringHelper.null2void(resulMap.get("resultCode")), (ftime - stime)), s);
		    	}
	    	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(resulMap, message);
    }
    
    /**
     * 서버간 연계 테스트 요청
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */   
    @RequestMapping("/fm/sm/SM8001_02/executeRelayBatch")
    public ModelAndView executeRelayBatch(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resulMap = null;
        String message = null;
        
        try {
        	long stime = System.currentTimeMillis();
        	
        	Map map = DataMapHelper.getMap(dataMap);
        	resulMap = SM8001.executeRelayBatch(map);
        	
        	log.debug("result code = " + resulMap.get("resultCode") +", message = " + resulMap.get("resultMsg"));
        	
        	long ftime = System.currentTimeMillis();
        	
        	// 중계서버인 경우 실행
	    	if(Constants.APPLICATION_SYSTEM_ID.equals("RS")) {
	    		// 클라이언트 요청경로를 접속중인 모든 클라이언트에게 Websocket 메시지를 보낸다.
		    	List<Session> clients = WebsocketSupporter.clients;
		    	
		    	for(Session s : clients) {
		    		WebsocketSupporter ws = new WebsocketSupporter();
		    		
		    		ws.handleMessage("MMA001_07"+"[REL]"+ InterfaceLogger.getRealtimeMessage(req, "R", StringHelper.null2void(map.get("SERVICE_ID")), StringHelper.null2void(resulMap.get("resultCode")), (ftime - stime)), s);
		    	}
	    	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(resulMap, message);
    }
    
}
