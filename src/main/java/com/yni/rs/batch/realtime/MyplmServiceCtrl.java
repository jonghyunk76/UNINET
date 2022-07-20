package com.yni.rs.batch.realtime;

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
 * MY PLM 연계 Controller 클래스
 * 
 * @author ador2
 *
 */
@Controller
public class MyplmServiceCtrl extends YniAbstractController {
	
	@Resource(name="myplmservice")
	private MyplmService service;
	
	/**
	 * 외부시스템 요청 수신처리
	 * 
	 * @param req
	 * @param dataMap
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/rs/batch/myplm/executeMemberInfo")
	public ModelAndView executeMemberInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resulMap = null;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	resulMap = service.executeRelayBatch(map);
        	
        	log.debug("result code = " + resulMap.get("resultCode") +", message = " + resulMap.get("resultMsg"));
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
	@RequestMapping("/rs/batch/myplm/insertMemberInfo")
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
