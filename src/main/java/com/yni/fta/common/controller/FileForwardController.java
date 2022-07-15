package com.yni.fta.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.InternalResourceView;

import kr.yni.frame.Constants;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.ServletHelper;

/**
 * 파일(엑셀) 다운로드를 처리하기 위한 forward Controller 클래스
 * 
 * @author YNI-maker
 *
 */
public class FileForwardController  extends YniAbstractController implements Controller {
	
	private static final Log log = LogFactory.getLog(FileForwardController.class);
    
    private final String INIT_PAGE = "1";
    private final String MAX_ROWS = "1000000";
    
    /**
     * Request URL에 대한 filtering 을 거쳐 해당하는 URL로 응답한다.<br>
     * 
     * @param
     * @param
     * @return model
     */
    @SuppressWarnings("rawtypes")
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse respone) throws Exception {
    	req.setCharacterEncoding(Constants.APPLICATION_CONTEXT_CHARSET);
        
        if(log.isDebugEnabled()) {log.debug("Content Type = " + req.getContentType());}
        
        View view = new InternalResourceView(getViewName(req));
        ModelAndView mav = new ModelAndView(view);
        
        Map map = ServletHelper.getReqeustParameters(req);

        // 페이징일 경우 한번에 조회는 최대 건수를 1000000건으로 변경한다.
        if(map.get("page") != null) {
            map.put("page", INIT_PAGE);
        }
        if(map.get("rows") != null) {
            map.put("rows", MAX_ROWS);
        } 
        
        log.debug("File Forward...(Map:" + map.toString() + ")");
        
        mav.addAllObjects(map);
        
        return mav;
    }
    
    /**
     * 엑셀다운로드를 위한 파라메터 조정 및 URL를 변경한다.
     * 
     * @param request
     * @return
     */
    public String getViewName(HttpServletRequest req) {
        String viewName = "";
         
        viewName = req.getRequestURI();
        viewName = viewName.replaceAll(".fle", ".do");
        
        log.debug("forward path: " + viewName);
        
        return viewName;
    }
}
