package com.yni.fta.mm.DEMO;

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
 * Demo Controller Class
 * 
 * @author YNI-maker
 *
 */
@Controller
public class DE0000Ctrl extends YniAbstractController {

	@Resource(name="de0000")
	private DE0000 de0000;
	

	/**
	 * 데모 샘플 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/de/de0000_01")
	public ModelAndView de0000_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/DEMO/DE-0000_01", dataMap);
	}
	
	/**
	 * datagrid db 설정 데모버전 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/de/de0000_02")
	public ModelAndView de0000_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/DEMO/DE-0000_02", dataMap);
	}
	
	/**
	 * 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/de/de0000_01/selectMainList")
	public ModelAndView selectMainList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = de0000.selectMainList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
}