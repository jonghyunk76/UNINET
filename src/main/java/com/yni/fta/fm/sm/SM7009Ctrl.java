package com.yni.fta.fm.sm;

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
 * 메뉴관리
 * 
 * @author sbj1000
 *
 */
@Controller
public class SM7009Ctrl extends YniAbstractController {

	@Resource(name="sm7009")
	private SM7009 sm7009;
	

	/**
	 * 메뉴 리스트 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fm/sm/sm7009_01")
	public ModelAndView sm7009_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/SM/SM-7009_01", dataMap);
	}

	/**
	 * 시스템관리 > 메뉴리스트 상세화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fm/sm/sm7009_02")
	public ModelAndView sm7009_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/SM/SM-7009_02", dataMap);
	}
	
	/**
	 * 시스템관리 > 메뉴언어 리스트 상세화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fm/sm/sm7009_03")
	public ModelAndView sm7009_03Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding( "/SM/SM-7009_03", dataMap);
	}

	/**
	 * 메뉴 리스트 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7009_01/selectMenuList")
	public ModelAndView selectMenuList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = sm7009.selectMenuList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 메뉴 언어 리스트 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7009_01/selectMenuLanguageList")
	public ModelAndView selectMenuLanguageList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;

		try {
			resultList = sm7009.selectMenuLanguageList(DataMapHelper.getMap(dataMap));
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	/**
	 * 메뉴 정보 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7009_02/selectMenuInfo")
    public ModelAndView selectMenuInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        try {
            result = sm7009.selectMenuInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        return WebAction.returnMap(result, message);
    }
	
	/**
     * 
     * 메뉴 정보 추가
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7009_02/insertMenuInfo")
    public ModelAndView insertChargerInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        try {
            int resultCnt = sm7009.insertMenuInfo(DataMapHelper.getMap(dataMap));
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 메뉴 정보 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7009_02/updateMenuInfo")
    public ModelAndView updateMenuInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        try {
            int resultCnt = sm7009.updateMenuInfo(DataMapHelper.getMap(dataMap));
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
	 * 메뉴 언어 정보 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7009_03/selectMenuLanguageInfo")
    public ModelAndView selectMenuLanguageInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        try {
            result = sm7009.selectMenuLanguageInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        return WebAction.returnMap(result, message);
    }
	
	/**
     * 
     * 메뉴 언어 정보 추가
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7009_03/insertMenuLanguageInfo")
    public ModelAndView insertMenuLanguageInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        try {
            int resultCnt = sm7009.insertMenuLanguageInfo(DataMapHelper.getMap(dataMap));
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 메뉴 언어 정보 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7009_03/updateMenuLanguageInfo")
    public ModelAndView updateMenuLanguageInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        try {
            int resultCnt = sm7009.updateMenuLanguageInfo(DataMapHelper.getMap(dataMap));
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        return WebAction.resultJsonMsg(success, message);
    }
	
}
