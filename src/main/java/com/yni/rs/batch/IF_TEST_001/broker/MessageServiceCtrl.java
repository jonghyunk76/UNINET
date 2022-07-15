package com.yni.rs.batch.IF_TEST_001.broker;

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
 * 외부에서 호출되는 서버시를 연결할 Controller 클래스
 * 
 * @author ador2
 *
 */
@Controller
public class MessageServiceCtrl extends YniAbstractController {
	
	@Resource(name="if_test_0010")
	private MessageService service;
	
	/**
	 * 외부시스템 요청 수신처리
	 * 
	 * @param req
	 * @param dataMap
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/rs/batch/if_test_0010/executeMemberInfo")
	public ModelAndView executeMemberInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resulMap = null;
        String message = null;
        
        try {
        	long stime = System.currentTimeMillis();
        	
        	Map map = DataMapHelper.getMap(dataMap);
        	resulMap = service.executeRelayBatch(map);
        	
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
	
	/**
	 * 중앙서버에서 요청한 내용을 처리한 후 결과 리턴하기
	 * 
	 * @param req
	 * @param dataMap
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/rs/batch/if_test_0010/insertMemberInfo")
	public ModelAndView insertMemberInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = new HashMap();
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	
        	log.debug("Request parameter = " + map);
//            int resultCnt = service.insertMemberInfo(map);
        	
        	Map batch = new HashMap();
        	
        	batch.put("resultCode", "S"); // S:성공, E:실패, N:데이터 없음
        	batch.put("resultMsg", "송신을 정상적으로 완료했습니다.");
        	
        	resultMap.put("rows", JsonUtil.getViewJson(batch)); // 필수사항
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return new ModelAndView("jrows", resultMap);
	}
	
}
