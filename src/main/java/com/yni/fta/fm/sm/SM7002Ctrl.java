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
 * 시스템관리 > 사업부등록
 * 
 * @author carlos
 *
 */
@Controller
public class SM7002Ctrl extends YniAbstractController {

    @Resource(name = "sm7002")
    private SM7002 sm7002;
    
    @Resource(name = "sm7001")
    private SM7001 sm7001;

    /**
     * 사업부 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7002_01")
    public ModelAndView sm7002_01Move(DataMap dataMap) throws Exception {
    	Map sysMap = sm7001.selectSysconfigOptionInfo(DataMapHelper.getMap(dataMap));
    	
    	dataMap.put("ORGAN_ISSUE_YN", StringHelper.null2string(sysMap.get("ORGAN_ISSUE_YN"), "N"));
    	
        return WebAction.forwarding("/SM/SM-7002_01", dataMap);
    }

    /**
     * 사업부 상세 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7002_02")
    public ModelAndView sm7002_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7002_02", dataMap);
    }

    /**
     * 
     * 사업부 상세 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7002_03")
    public ModelAndView sm7002_03Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7002_03", dataMap);
    }

    /**
     * 사업부 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7002_01/selectDivisionList")
    public ModelAndView selectDivisionList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7002.selectDivisionList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }

    /**
     * 
     * 사업부 중복건수 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7002_01/selectDivisionDupCheck")
    public ModelAndView selectDivisionDupCheck(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        int nDupCnt = 0;
        try {

            Map dupInfo = sm7002.selectDivisionDupCheck(DataMapHelper.getMap(dataMap));

            if (dupInfo != null) {
                nDupCnt = StringHelper.null2zero(dupInfo.get("DUPLICATE"));
            }

        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.resultJsonMsg(success, "리턴건수:" + nDupCnt);
    }

    /**
     * 사업부 정보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7002_02/selectDivisionInfo")
    public ModelAndView selectDivisionInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;

        try {
            result = sm7002.selectDivisionInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(result, message);
    }

    /**
     * 
     * 사업부 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7002_02/insertDivisionInfo")
    public ModelAndView insertDivisionInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        try {
            int resultCnt = sm7002.insertDivisionInfo(DataMapHelper.getMap(dataMap));

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
     * 사업부 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7002_02/updateDivisionInfo")
    public ModelAndView updateDivisionInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        try {
            int resultCnt = sm7002.updateDivisionInfo(DataMapHelper.getMap(dataMap));

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
     * 사업부 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7002_02/deleteDivisionInfo")
    public ModelAndView deleteDivisionInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        try {
            int resultCnt = sm7002.deleteDivisionInfo(DataMapHelper.getMap(dataMap));

            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.resultJsonMsg(success, message);
    }

}
