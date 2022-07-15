package com.yni.fta.mm.pop;

import java.util.HashMap;
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
 * 공통 > 가격신고서 컨트롤 클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMB003Ctrl extends YniAbstractController {

	@Resource(name="MMB003")
	private MMB003 MMB003;
	
	/**
	 * 가격신고서 메인화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB003_01")
	public ModelAndView MMB003_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B003_01", dataMap);
	}
	
	/**
	 * 가격신고방법 선택 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/MMB003_02")
	public ModelAndView MMB003_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-B003_02", dataMap);
	}
	
	/**
	 * 가격신고서 초기 작성시 보여줄 정보 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB003_01/selectEmptyImpPrnoInfo")
	public ModelAndView selectEmptyImpPrnoInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = MMB003.selectEmptyImpPrnoInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 가격신고서 등록된 정보 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB003_01/selectRegistImpPrnoInfo")
	public ModelAndView selectRegistImpPrnoInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;

		try {
			resultMap = MMB003.selectRegistImpPrnoInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 가격신고서 정보 등록
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB003_01/insertImpPrnoInfo")
	public ModelAndView insertImpPrnoInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = new HashMap();
		String message = null;

		try {
			String no = MMB003.insertImpPrnoInfo(DataMapHelper.getMap(dataMap));
			
			if(!no.isEmpty()) {
				resultMap.put("IMP_PRNO_SN", no); // 등록번호
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(resultMap, message);
	}
	
	/**
	 * 가격신고서 정보 수정
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB003_01/updateImpPrnoInfo")
	public ModelAndView updateImpPrnoInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean success = true;
		String message = null;

		try {
			int cnt = MMB003.updateImpPrnoInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				success = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(success, message);
	}
	
	/**
	 * 가격신고서 정보 삭제
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/mm/pop/MMB003_01/deleteImpPrnoInfo")
	public ModelAndView deleteImpPrnoInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean success = true;
		String message = null;

		try {
			int cnt = MMB003.deleteImpPrnoInfo(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				success = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(success, message);
	}
	
	
}