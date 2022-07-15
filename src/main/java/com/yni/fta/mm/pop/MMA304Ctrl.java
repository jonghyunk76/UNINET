package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

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
 * 공통 > 베트남 원산지 서류 발급
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMA304Ctrl extends YniAbstractController {

	@Resource(name="mmA304")
	private MMA304 mmA304;

	/**
	 * 레포트 발급(베트남-기관발급) 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA304_01")
	public ModelAndView mmA304_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A304_01", dataMap);
	}

	/**
	 * 레포트 발급(베트남-내수) 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA304_02")
	public ModelAndView mmA304_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A304_02", dataMap);
	}

	/**
	 * 원산지 증명서 마스터 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/mmA304_01/selectInvCoMasterInfo")
	public ModelAndView selectInvCoMasterInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = mmA304.selectInvCoMasterInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 원산지 신고품목 리스트
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/mmA304_01/selectInvPackingList")
	public ModelAndView selectInvPackingList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mmA304.selectInvPackingList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
}
