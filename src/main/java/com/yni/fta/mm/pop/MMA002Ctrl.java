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
 * 공통팝업 : 사업부 및 회사 조회
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMA002Ctrl extends YniAbstractController {

	 
	@Resource(name = "mmA002")
	private MMA002 mmA002;

	/**
	 * 사업부 리스트 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA002_01")
	public ModelAndView mmA002_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A002_01", dataMap);
	}

	/**
	 * 회사 상세 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA002_02")
	public ModelAndView mmA002_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A002_02", dataMap);
	}
	
	/**
	 * 계약회사 상세 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA002_03")
	public ModelAndView mmA002_03Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A002_03", dataMap);
	}
	
	/**
	 * 사업부 리스트 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/mmA002_01/selectMainList")
	public ModelAndView selectMainList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA002.selectMainList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 회사 리스트 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/mmA002_02/selectMainCompanyList")
	public ModelAndView selectMainCompanyList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA002.selectMainCompanyList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 계약회사 리스트 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/mmA002_02/selectContractCompanyList")
	public ModelAndView selectContractCompanyList(HttpServletRequest req, DataMap dataMap) throws Exception {
	    List resultList = null;
	    String message = null;
	    
	    try {
	        resultList = mmA002.selectContractCompanyList(DataMapHelper.getMap(dataMap));
	    } catch (Exception e) {
	        message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
	    }
	    
	    return WebAction.returnDataSet(resultList, message);
	}
	
}
