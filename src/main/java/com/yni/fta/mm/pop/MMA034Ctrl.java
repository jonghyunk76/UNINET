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
 * 공통 > 고객사 품목 조회
 * 
 * @author jonghyunkim
 *
 */
@Controller
public class MMA034Ctrl extends YniAbstractController {

    @Resource(name="mmA034")
    private MMA034 mmA034;

    /**
     * 데이터 그리드 생성 및 상세조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA034_01")
    public ModelAndView mmA034_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A034_01", dataMap);
    }
    
    /**
     * 데이터 그리드 사용자 설정 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA034_02")
    public ModelAndView mmA034_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A034_02", dataMap);
    }
    
    /**
     * 데이터 그리드 생성 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA034_03")
    public ModelAndView mmA034_03Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A034_03", dataMap);
    }
    
    /**
     * 데이터 그리드 등록 정보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA034_03/selectDatagridMstList")
    public ModelAndView selectDatagridMstList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = mmA034.selectDatagridMstList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 데이터 그리드 정보 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA034_03/deleteDatagridMstInfo")
    public ModelAndView deleteDatagridMstInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        try {
            int cnt = mmA034.deleteDatagridMstInfo(DataMapHelper.getMap(dataMap));
            
            if(cnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 데이터 그리드 정보 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA034_03/insertDatagridMstInfo")
    public ModelAndView insertDatagridMstInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        try {
            int cnt = mmA034.insertDatagridMstInfo(DataMapHelper.getMap(dataMap));
            
            if(cnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.resultJsonMsg(success, message);
    }
    
}
