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
 * 시스템관리 > 인터페이스 > 항목 관리
 * 
 * @author carlos
 *
 */
@Controller
public class SM7005Ctrl extends YniAbstractController {

    @Resource(name = "sm7005")
    private SM7005 sm7005;

    /**
     * 인터페이스 항목 마스터 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7005_01")
    public ModelAndView sm7005_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7005_01", dataMap);
    }

    /**
     * 인터페이스 항목 마스터(테이블) 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7005_02")
    public ModelAndView sm7005_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7005_02", dataMap);
    }
    
    /**
     * 
     *  인터페이스 항목 디테일 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7005_03")
    public ModelAndView sm7005_03Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7005_03", dataMap);
    }

    /**
     * 
     *  인터페이스 항목 마스터(프로시저) 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7005_04")
    public ModelAndView sm7005_04Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7005_04", dataMap);
    }
    
    /**
     * 
     * 클라이언트별 엑셀양식 등록 및 수정 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7005_05")
    public ModelAndView sm7005_05Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7005_05", dataMap);
    }
    
    /**
     * 
     * 인터페이스 항목 선택 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7005_06")
    public ModelAndView sm7005_06Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7005_06", dataMap);
    }
    
    /**
     * 인터페이스 항목 마스터 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7005_01/selectInterfaceItemMstList")
    public ModelAndView selectInterfaceItemMstList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7005.selectInterfaceItemMstList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 인터페이스 항목 마스터 정보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7005_02/selectInterfaceItemMstInfo")
    public ModelAndView selectInterfaceItemMstInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = sm7005.selectInterfaceItemMstInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 인터페이스 항목 마스터 콤보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7005_01/selectInterfaceItemMstCombo")
    public ModelAndView selectInterfaceItemMstCombo(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = sm7005.selectInterfaceItemMstCombo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 
     * 인터페이스 항목 마스터 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7005_02/insertInterfaceItemMstInfo")
    public ModelAndView insertInterfaceItemMstInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7005.insertInterfaceItemMstInfo(DataMapHelper.getMap(dataMap));
            
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
     * 인터페이스 항목 마스터 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7005_02/updateInterfaceItemMstInfo")
    public ModelAndView updateInterfaceItemMstInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7005.updateInterfaceItemMstInfo(DataMapHelper.getMap(dataMap));
            
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
     * 인터페이스 항목 마스터 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7005_02/deleteInterfaceItemMstInfo")
    public ModelAndView deleteInterfaceItemMstInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7005.deleteInterfaceItemMstInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }

    /**
     * 인터페이스 항목 마스터 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7005_02/selectInterfaceItemDtlList")
    public ModelAndView selectInterfaceItemDtlList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7005.selectInterfaceItemDtlList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 인터페이스 항목 디테일 정보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7005_03/selectInterfaceItemDtlInfo")
    public ModelAndView selectInterfaceItemDtlInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = sm7005.selectInterfaceItemDtlInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    /**
     * 
     * 인터페이스 항목 디테일 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7005_03/insertInterfaceItemDtlInfo")
    public ModelAndView insertInterfaceItemDtlInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7005.insertInterfaceItemDtlInfo(DataMapHelper.getMap(dataMap));
            
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
     * 인터페이스 항목 디테일 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7005_03/updateInterfaceItemDtlInfo")
    public ModelAndView updateInterfaceItemDtlInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7005.updateInterfaceItemDtlInfo(DataMapHelper.getMap(dataMap));
            
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
     * 인터페이스 항목 디테일 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7005_03/deleteInterfaceItemDtlInfo")
    public ModelAndView deleteInterfaceItemDtlInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7005.deleteInterfaceItemDtlInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
}
