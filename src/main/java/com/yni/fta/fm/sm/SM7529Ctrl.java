package com.yni.fta.fm.sm;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 환경설정 > Set up > Web Socket관리 클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class SM7529Ctrl extends YniAbstractController {

	@Resource(name="sm7529")
	private SM7529 sm7529;
	

	/**
	 * Web Socket관리 조회 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fm/sm/sm7529_01")
	public ModelAndView sm7529_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/SM/SM-7529_01", dataMap);
	}
	
	/**
	 * Web Socket 세션 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7529_01/selectWebSocketList")
	public ModelAndView selectWebSocketList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = sm7529.selectWebSocketList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * Web Socket 세션 강제종료 시키기
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7529_01/removeUserWebSocket")
	public ModelAndView removeUserWebSocket(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = false;
		String message = null;

		try {
			int cnt = sm7529.removeUserWebSocket(DataMapHelper.getMap(dataMap));
			
			if(cnt > 0) {
				result = true;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	/**
	 * Web Socket 초기화
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7529_01/removeAllWebSocket")
	public ModelAndView removeAllWebSocket(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean result = false;
		String message = null;

		try {
			int cnt = sm7529.removeAllWebSocket(DataMapHelper.getMap(dataMap));
			
			if(cnt > 0) {
				result = true;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(result, message);
	}
	
	
}