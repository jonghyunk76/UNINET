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
 * 배치 > 인터페이스 이력 데이터 상세 조회
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMA027Ctrl extends YniAbstractController {

	@Resource(name="mma027")
	private MMA027 mma027;
	

	/**
	 * 조회 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/pop/mmA027_01")
	public ModelAndView mmA007_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/POP/MM-A027_01", dataMap);
	}

	/**
	 * 데이터 그리드 해더 정보 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/dpop/mmA027_01/selectHeaderInfo")
	public ModelAndView selectHeaderInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mma027.selectHeaderInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 서비스 방식에서 데이터 그리드 해더 정보 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/dpop/mmA027_01/selectServiceHeaderInfo")
	public ModelAndView selectServiceHeaderInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mma027.selectServiceHeaderInfo(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 인터페이스 이력 상세 리스트 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/dpop/mmA027_01/selectInterfaceHistoryList")
	public ModelAndView selectInterfaceHistoryList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mma027.selectInterfaceHistoryList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
}