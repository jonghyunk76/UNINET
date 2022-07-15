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
 * FTA협정등록
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMA011Ctrl extends YniAbstractController {

	@Resource(name="mmA011")
	private MMA011 mmA011;
	
	/**
     * FTA협정 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA011_01")
    public ModelAndView mmA011_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A011_01", dataMap);
    }
	
    /**
     * FTA협정 상세 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA011_02")
    public ModelAndView mmA011_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A011_02", dataMap);
    }
    
    /**
     * FTA협정 리스트 조회
     * 
     * @param paramTwo
     *            - ModelMap model
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA011_01/selectMainList")
    public ModelAndView selectMainList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA011.selectMainList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
}
