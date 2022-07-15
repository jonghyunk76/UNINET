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
 * 공통 > 도움말
 * 
 * @author YNI-Maker
 *
 */
@Controller
public class MMA029Ctrl extends YniAbstractController {
	
	@Resource(name="mma029")
	private MMA029 mma029;
	
    /**
     * 도움말 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA029_01")
    public ModelAndView mmA029_01Move(DataMap dataMap) throws Exception {
    	return WebAction.forwarding("/POP/MM-A029_01", dataMap);
    }
    
    /**
     * 도움말 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA029_02")
    public ModelAndView mmA029_02Move(DataMap dataMap) throws Exception {
    	return WebAction.forwarding("/POP/MM-A029_02", dataMap);
    }
    
    /**
	 * 도움말 목록 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/pop/mmA029_01/selectHelpMenuList")
	public ModelAndView selectHelpMenuList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mma029.selectHelpMenuList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnJsonView(resultList, message);
	}
    
	/**
	 * 도움말 검색
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/pop/mmA029_01/selectKeyContents")
	public ModelAndView selectKeyContents(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = mma029.selectKeyContents(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnJsonView(resultList, message);
	}
    
}
