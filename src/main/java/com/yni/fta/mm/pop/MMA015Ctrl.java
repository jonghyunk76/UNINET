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
 * FTA관리 > FTA Rule > HS Code 조회
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMA015Ctrl extends YniAbstractController {

	
	@Resource(name="mmA015")
	private MMA015 mmA015;
	

	/**
	 * 표준 Code 리스트 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA015_01")
	public ModelAndView mmA015_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A015_01", dataMap);
	}
	
	/**
	 * 기관발급용 표준 Code 리스트 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA015_02")
	public ModelAndView mmA015_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A015_02", dataMap);
	}
	
	/**
	 * 기관발급용 표준 Code 리스트 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA015_03")
	public ModelAndView mmA015_03Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A015_03", dataMap);
	}
	
	/**
	 * 표준 Code 리스트 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/mmA015_01/selectMainList")
	public ModelAndView selectMainList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA015.selectMainList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 기관발급용 표준 Code 리스트 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/mmA015_02/selectKcsStandardCodeList")
	public ModelAndView selectKcsStandardCodeList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA015.selectKcsStandardCodeList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
}
