package com.yni.fta.mm.pop;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 멕시코 확인서 form 조회 및 업로드 실행
 * 
 * @author carlos
 */
@Controller
public class MMA023Ctrl extends YniAbstractController {
    
    @Resource(name="mmA023")
    private MMA023 mmA023;
    
    /**
     * Excel Upload 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA023_01")
    public ModelAndView mmA023(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A023_01", dataMap);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA023_01/selectExcelSampleList")
    public ModelAndView selectExcelSampleList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA023.selectExcelSampleList(DataMapHelper.getMap(dataMap));
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
    @RequestMapping(value = "/mm/pop/mmA023_01/saveExcelSample", method = RequestMethod.POST)
    public ModelAndView saveExcelSample(HttpServletRequest req, DataMap dataMap) throws Exception {
       
        int resultCnt = 0;
        boolean success = true;
        String message = null;
        
        try {
            resultCnt = mmA023.saveExcelSample(DataMapHelper.getMap(dataMap));
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
}
