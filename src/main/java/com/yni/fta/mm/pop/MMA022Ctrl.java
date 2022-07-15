package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.fm.sm.SM7001;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 한국 확인서 form 조회 및 업로드 실행
 * 
 * @author carlos
 */
@Controller
public class MMA022Ctrl extends YniAbstractController {
    
    @Resource(name="mmA022")
    private MMA022 mmA022;
    
    @Resource(name = "sm7001")
    private SM7001 sm7001;
    
    /**
     * Excel Upload 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA022_01")
    public ModelAndView mmA022_01(DataMap dataMap) throws Exception {
    	Map sysMap = sm7001.selectSysconfigOptionInfo(DataMapHelper.getMap(dataMap));
    	
    	dataMap.put("NON_ORGIN_RESN_YN", StringHelper.null2string(sysMap.get("NON_ORGIN_RESN_YN"), "N"));
    	dataMap.put("NON_ORGIN_RESN_REQ_YN", StringHelper.null2string(sysMap.get("NON_ORGIN_RESN_REQ_YN"), "N"));
    	
        return WebAction.forwarding("/POP/MM-A022_01", dataMap);
    }
    
    /**
     * Excel Upload 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA022_02")
    public ModelAndView mmA022_02(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A022_02", dataMap);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA022_01/selectExcelSampleList")
    public ModelAndView selectExcelSampleList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA022.selectExcelSampleList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * Excel Upload & Save
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
    @RequestMapping(value = "/mm/pop/mmA022_01/saveExcelSample", method = RequestMethod.POST)
    public ModelAndView saveExcelSample(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
        	
            resultCnt = mmA022.saveExcelSample(pmap);
            
            if (resultCnt < 0) {
                success = false;
            } else {
            	mmA022.executeItemErrorCheck(pmap);
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA022_02/selectVientnamExcelSampleList")
    public ModelAndView selectVientnamExcelSampleList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA022.selectVientnamExcelSampleList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * Excel Upload & Save
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
    @RequestMapping(value = "/mm/pop/mmA022_02/saveVientnamExcelSample", method = RequestMethod.POST)
    public ModelAndView saveVientnamExcelSample(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
        	
            resultCnt = mmA022.saveVientnamExcelSample(pmap);
            
            if (resultCnt < 0) {
                success = false;
            } else {
            	mmA022.executeVientnamItemErrorCheck(pmap);
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 베트남 원산지 증명서 저장
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
    @RequestMapping(value = "/mm/pop/mmA022_02/insertVientnamExcelList", method = RequestMethod.POST)
    public ModelAndView insertVientnamExcelList(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
        	
            resultCnt = mmA022.insertVientnamExcelList(pmap);
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 베트남 원산지 증명서 업로드내역 전체 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
    @RequestMapping(value = "/mm/pop/mmA022_02/deleteVientnamExcelSample", method = RequestMethod.POST)
    public ModelAndView deleteVientnamExcelSample(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
        	
            resultCnt = mmA022.deleteVientnamExcelSample(pmap);
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 베트남 원산지 증명서 업로드내역 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exceptio
     */
    @RequestMapping(value = "/mm/pop/mmA022_02/deleteVientnamExcelSampleRow", method = RequestMethod.POST)
    public ModelAndView deleteVientnamExcelSampleRow(HttpServletRequest req, DataMap dataMap) throws Exception {
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
        	
            resultCnt = mmA022.deleteVientnamExcelSampleRow(pmap);
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
}
