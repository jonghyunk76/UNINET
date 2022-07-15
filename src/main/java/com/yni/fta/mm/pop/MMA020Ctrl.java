package com.yni.fta.mm.pop;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 멕시코 확인서 원산지 등록
 * 
 * @author YNI-maker
 */
@Controller
public class MMA020Ctrl extends YniAbstractController {

    @Resource(name="mmA020")
    private MMA020 mmA020;
    

    /**
     * 멕시코 증명서 품목 화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA020_01")
    public ModelAndView mmA020_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A020_01", dataMap);
    }
    
    /**
     * 멕시코 증명서 품목 HS Code 조회
     * 
     * @param companyCd String
     * @param itemCd String
     * @param res HttpServletResponse
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return String
     * @exception Exception
     * */
    @ResponseBody
    @RequestMapping("/mm/pop/MMA020_01/selectHsCode/{COMPANY_CD}&{ITEM_CD}")
    public String selectHsCode(
            @PathVariable("COMPANY_CD") String companyCd, @PathVariable("ITEM_CD") String itemCd, 
            HttpServletResponse res, HttpServletRequest req, DataMap dataMap) throws Exception  {
        
        dataMap.put("COMPANY_CD", companyCd);
        dataMap.put("ITEM_CD", itemCd);
        
        String result = null;
        
        try {
            result = mmA020.selectHsCode(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            new RuntimeException(getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE))));
        }
        
        return result;
    }
    
    
    /**
     * 멕시코 증명서 품목 Naladisa Code 조회
     * 
     * @param companyCd String
     * @param itemCd String
     * @param res HttpServletResponse
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return String
     * @exception Exception
     * */
    @ResponseBody
    @RequestMapping("/mm/pop/MMA020_01/selectNaladisaCd/{COMPANY_CD}&{ITEM_CD}")
    public String selectNaladisaCd(
            @PathVariable("COMPANY_CD") String companyCd, @PathVariable("ITEM_CD") String itemCd, 
            HttpServletResponse res, HttpServletRequest req, DataMap dataMap) throws Exception  {
        
        dataMap.put("COMPANY_CD", companyCd);
        dataMap.put("ITEM_CD", itemCd);
        
        String result = null;
        
        try {
            result = mmA020.selectNaladisaCd(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            new RuntimeException(getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE))));
        }
        
        return result;
    }
    
    /**
     * 멕시코 증명서 품목 결정기준 조회
     * 
     * @param dataMap DataMap
     * @param req HttpServletRequest
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/MMA020_01/selectRuleContents")
    public ModelAndView selectRuleContents(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA020.selectRuleContents(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
}
