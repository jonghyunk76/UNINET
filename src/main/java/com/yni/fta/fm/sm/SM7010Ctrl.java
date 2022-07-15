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
 * 시스템 관리 > 사용자 권한관리
 * 
 * @author YNI-maker
 *
 */
@Controller
public class SM7010Ctrl extends YniAbstractController {

	@Resource(name="sm7010")
	private SM7010 sm7010;
	

	/**
	 * 시스템 관리 > 사용자 권한관리 리스트 조회 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fm/sm/sm7010_01")
	public ModelAndView sm7010_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/SM/SM-7010_01", dataMap);
	}

	/**
	 * 시스템 관리 > 사용자 권한관리 상세 화면으로 이동
	 * 
	 * @param request
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fm/sm/sm7010_02")
	public ModelAndView sm7010_02Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/SM/sm7010_02", dataMap);
	}
	
	/**
     * 권한그룹 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7010/selectAuthGroupList")
    public ModelAndView selectAuthGroupList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List result = null;
        String message = null;
        
        try {
        	result = sm7010.selectAuthGroupList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(result, message);
    }
    
    /**
     * 권한그룹 상세내역 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7010/selectAuthGroupInfo")
    public ModelAndView selectAuthGroupInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = sm7010.selectAuthGroupInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 
     * 권한그룹 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7010/insertAuthGroupInfo")
    public ModelAndView insertAuthGroupInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7010.insertAuthGroupInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 권한그룹 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7010/updateAuthGroupInfo")
    public ModelAndView updateAuthGroupInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7010.updateAuthGroupInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 권한그룹 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7010/deleteAuthGroupInfo")
    public ModelAndView deleteAuthGroupInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7010.deleteAuthGroupInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 권한그룹 사용자 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7010/selectAuthUserList")
    public ModelAndView selectAuthUserList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List result = null;
        String message = null;
        
        try {
        	result = sm7010.selectAuthUserList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(result, message);
    }
    
    
    /**
     * 
     * 권한그룹 사용자 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7010/insertAuthUserInfo")
    public ModelAndView insertAuthUserInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7010.insertAuthUserInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 권한그룹 사용자 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7010/deleteAuthUserInfo")
    public ModelAndView deleteAuthUserInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7010.deleteAuthUserInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 메뉴 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7010/selectMenuList")
    public ModelAndView selectMenuList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List result = null;
        String message = null;
        
        try {
        	result = sm7010.selectMenuList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(result, message);
    }
    
    /**
     * 권한그룹 메뉴 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7010/selectAuthMenuList")
    public ModelAndView selectAuthMenuList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List result = null;
        String message = null;
        
        try {
        	result = sm7010.selectAuthMenuList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(result, message);
    }
    
    /**
     * 
     * 권한그룹 메뉴 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7010/insertAuthMenuInfo")
    public ModelAndView insertAuthMenuInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7010.insertAuthMenuInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 권한그룹 메뉴 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7010/deleteAuthMenuInfo")
    public ModelAndView deleteAuthMenuInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7010.deleteAuthMenuInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 부여된 메뉴 목록 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7010/updateAuthMenuInfo")
    public ModelAndView updateAuthMenuInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7010.updateAuthMenuInfo(DataMapHelper.getMap(dataMap));
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }    
}
