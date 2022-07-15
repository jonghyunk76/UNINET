package com.yni.fta.fm.sm;

import java.util.HashMap;
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
 * Python 인터페이스 클래스
 * 
 * @author YNI-Framework
 *
 */
@Controller
public class SM8003Ctrl extends YniAbstractController {

    @Resource(name = "SM8003")
    private SM8003 SM8003;

    /**
     * 인터페이스 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/SM8003_01")
    public ModelAndView SM8001_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-8003_01", dataMap);
    }    
    
    /**
     * Access Token 구하기
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */   
    @RequestMapping("/fm/sm/SM8003_01/executePythonFile")
    public ModelAndView executePythonFile(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = new HashMap();
        String message = null;

        try {
        	resultMap = SM8003.executePythonFile(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(resultMap, message);
    }
	
}
