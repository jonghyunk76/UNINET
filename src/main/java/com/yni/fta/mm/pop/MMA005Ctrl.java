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
 * 공통팝업 > 품목 조회
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMA005Ctrl extends YniAbstractController {

	
	@Resource(name="mmA005")
	private MMA005 mmA005;
	

	/**
	 * 품목 조회 및 선택 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA005_01")
	public ModelAndView mmA005_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A005_01", dataMap);
	}

	/**
	 * BOM 투입자재 조회 및 선택 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA005_02")
	public ModelAndView mmA005_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A005_02", dataMap);
	}

	/**
	 * 품목 리스트 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/mmA005_01/selectMainList")
	public ModelAndView selectMainList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA005.selectMainList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * BOM 자재 리스트 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/mmA005_02/selectBomItemList")
	public ModelAndView selectBomItemList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA005.selectBomItemList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
}
