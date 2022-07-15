package com.yni.rs.st;

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
 * 모니터링 > 서비스 이력
 * 
 * @author YNI-maker
 */
@Controller
public class STR003Ctrl extends YniAbstractController {

	@Resource(name="stR003")
	private STR003 stR003;

	/**
	 * 서비스 이력 목록 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rs/st/stR003_01")
	public ModelAndView stR003_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/ST/ST-R003_01", dataMap);
	}

	/**
	 * 메시지(파라메터) 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rs/st/stR003_02")
	public ModelAndView stR003_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/ST/ST-R003_02", dataMap);
	}
	
	/**
	 * 서비스 이력 리스트 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/rs/st/stR003_01/selectServiceHistoryList")
	public ModelAndView selectServiceHistoryList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = stR003.selectServiceHistoryList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 인터페이스 이력 리스트 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/rs/st/stR003_01/selectInterfaeHistoryList")
	public ModelAndView selectInterfaeHistoryList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = stR003.selectInterfaeHistoryList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
}
