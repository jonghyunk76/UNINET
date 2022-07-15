package com.yni.fta.mm.smp;

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
 * FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Controller
public class SMP1001Ctrl extends YniAbstractController {

	@Resource(name="smp1001")
	private SMP1001 smp1001;
	
	/**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1001")
    public ModelAndView smp1001(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1001_01", dataMap);
    }
	
    /**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1003")
    public ModelAndView smp1003(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1003_01", dataMap);
    }
    
    /**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1004")
    public ModelAndView smp1004(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1004_01", dataMap);
    }
    
    /**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1005")
    public ModelAndView smp1005(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1005_01", dataMap);
    }
    
    /**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1006")
    public ModelAndView smp1006(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1006_01", dataMap);
    }
    
    /**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1007")
    public ModelAndView smp1007(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1007_01", dataMap);
    }
    
    /**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1008")
    public ModelAndView smp1008(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1008_01", dataMap);
    }
    
    /**
     * 샘플 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws EframeException
     */
    @RequestMapping(value="/mm/smp/smp1010")
    public ModelAndView smp1010(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SMP/SMP1010_01", dataMap);
    }
    
    
    /**
     * FTA협정 리스트 조회
     * 
     * @param paramTwo
     *            - ModelMap model
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/smp/smp1001/selectMainList")
    public ModelAndView selectMainList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = smp1001.selectMainList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
}
