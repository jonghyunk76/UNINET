package com.yni.fta.mm.pop;

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
 * 공통 > 자료 송수신 컨트롤 클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMB001Ctrl extends YniAbstractController {

	@Resource(name="MMB001")
	private MMB001 MMB001;
	

	/**
	 * 자료 송수신 공통팝업 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB001_01")
	public ModelAndView MMB001_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B001_01", dataMap);
	}
	
	/**
	 * 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB001_02")
	public ModelAndView MMB001_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B001_02", dataMap);
	}
	
	/**
	 * 송수신 자료 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB001_01/selectMainList")
	public ModelAndView selectMainList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = MMB001.selectMainList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
}