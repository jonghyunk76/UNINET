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
 * 시스템관리 > 사용자 등록
 * 
 * @author carlos
 *
 */
@Controller
public class SM7003Ctrl extends YniAbstractController {

    @Resource(name = "sm7003")
    private SM7003 sm7003;
    
    @Resource(name = "sm7001")
    private SM7001 sm7001;
    
    /**
     * 사용자 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7003_01")
    public ModelAndView sm7003_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7003_01", dataMap);
    }

    /**
     * 사용자 상세 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7003_02")
    public ModelAndView sm7003_02Move(DataMap dataMap) throws Exception {
    	Map sysMap = sm7001.selectSysconfigOptionInfo(DataMapHelper.getMap(dataMap));
    	
    	// 시스템 설정(메일계정 통합관리 여부), 통합으로 관리 시 사용자 STMP 계정을 수정할 수 없도록 막는다.
    	dataMap.put("EMAIL_AUTH_INTG_YN", StringHelper.null2string(sysMap.get("EMAIL_AUTH_INTG_YN"), "N"));
    	
        return WebAction.forwarding("/SM/SM-7003_02", dataMap);
    }
    
    /**
     * 
     * 사용자 상세 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7003_03")
    public ModelAndView sm7003_03Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7003_03", dataMap);
    }
    
    /**
     * 사용자 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7003_01/selectUserList")
    public ModelAndView selectUserList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7003.selectUserList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 
     * 사용자 중복건수 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7003_01/selectUserDupCheck")
    public ModelAndView selectUserDupCheck(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        int nDupCnt = 0;
        try {
            
            Map dupInfo = sm7003.selectUserDupCheck(DataMapHelper.getMap(dataMap));
            
            if (dupInfo != null) {
                nDupCnt = StringHelper.null2zero(dupInfo.get("DUPLICATE"));
            }
            
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, "리턴건수:"+nDupCnt);
    }
    
    /**
     * 사용자 정보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7003_02/selectUserInfo")
    public ModelAndView selectUserInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = sm7003.selectUserInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 사용자 인증 키 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7003_02/selectUserOpenApiKeyList")
    public ModelAndView selectUserOpenApiKeyList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7003.selectUserOpenApiKeyList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
        
    
    /**
     * 
     * 사용자 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7003_02/insertUserInfo")
    public ModelAndView insertUserInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7003.insertUserInfo(DataMapHelper.getMap(dataMap));
            
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
     * 사용자 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7003_02/updateUserInfo")
    public ModelAndView updateUserInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7003.updateUserInfo(DataMapHelper.getMap(dataMap));
            
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
     * 사용자 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7003_02/deleteUserInfo")
    public ModelAndView deleteUserInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7003.deleteUserInfo(DataMapHelper.getMap(dataMap));
            
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
     * 사용자 OPENAPI 인증키 MERGE
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7003_02/updateUserOpenApiKey")
    public ModelAndView updateUserOpenApiKey(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7003.updateUserOpenApiKey(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }    
    
    /**
     * 사용자 통관 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7003_02/selectUserEntrInfo")
    public ModelAndView selectUserEntrInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = sm7003.selectUserEntrInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * selectUserEntrBcncList
     * 사용자 통관 거래처 List (USER_ENTR_BCNC)
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7003_02/selectUserEntrBcncList")
    public ModelAndView selectUserEntrBcncList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7003.selectUserEntrBcncList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }    
    
    /**
     * deleteUserEntrBcnc
     * 사용자 통관 거래처 삭제 (USER_ENTR_BCNC)
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7003_02/deleteUserEntrBcnc")
    public ModelAndView deleteUserEntrBcnc(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7003.deleteUserEntrBcnc(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }    
}
