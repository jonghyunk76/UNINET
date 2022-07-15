package com.yni.fta.common.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.yni.fta.common.tools.CertifySupporter;

import kr.yni.frame.Constants;
import kr.yni.frame.context.ApplicationContextFactory;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.ServletHelper;
import kr.yni.frame.util.StringHelper;

/**
 * <p>
 * Controller를 거치지 않고 직접 jsp나 html로 연결하기 위한 클래스
 * <br>
 * ModelAndView의 명칭(URL)이 *.view로 끝나는 경우 본 클래스가 수행된다.
 * </p>
 * 
 * @author YNI-maker
 *
 */
public class SimpleForwardController extends YniAbstractController implements Controller {
    
    private Log log = LogFactory.getLog(SimpleForwardController.class); 
    
    private String prefixName;
    
    public void setPrefixName(String name) {
    	this.prefixName = name;
    }
   
    /**
     * Request URL에 대한 filtering 을 거쳐 해당하는 URL로 응답한다.<br>
     * Controller를 거치지 않고 바로 jsp로 리턴된다.
     * @return model
     */
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse respone) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map map = ServletHelper.getReqeustParameters(req);
        
        if(req.getRequestURI().endsWith("supplier_login.htm")) {
        	mav.addObject("SUPPLIER_TYPE", "ST");
        }
        
        // 최초 접속 시 index.jsp에서 요청시 chageLanguage 파라메터를 넘겨준다.
        String lang = StringHelper.null2string(map.get("chageLanguage"), Constants.DEFAULT_LANGUAGE);
        SessionLocaleResolver localeResolver = null;
        localeResolver = (SessionLocaleResolver) ApplicationContextFactory.getAppContext().getBean("localeResolver");
    	
        // 페이지 언어셋팅
        if (lang.equals("EN")) {
			localeResolver.setDefaultLocale(Locale.ENGLISH);
		} else if (lang.equals("KR") || lang.equals("KO")) {
			localeResolver.setDefaultLocale(Locale.KOREA);
			lang = "KR";
		} else if (lang.equals("ES")) {
			localeResolver.setDefaultLocale(new Locale("es", "ES"));
		} else if (lang.equals("VN") || lang.equals("VI")) {
			localeResolver.setDefaultLocale(new Locale("vi", "VN"));
			lang = "VN";
		} else {
			localeResolver.setDefaultLocale(req.getLocale());
		}
        
        map.put("chageLanguage", lang);
        map.put("APPLICATION_SERVICE_TYPE", Constants.APPLICATION_SERVICE_TYPE);
        mav.addAllObjects(map);
        mav.setViewName(getViewName(req));
        
        log.debug("MEMBER_SESSION = " + map);
        
        return mav;
    }
    
    /**
     * URL확장자를 뺀 URI로 변환 03-31 jk1742
     * @param request
     * @return
     */
    public String getViewName(HttpServletRequest req) {
        String viewName = "";
        String[] urlSlices = req.getRequestURI().split("/");
        
        viewName = "/"+StringHelper.null2void(prefixName)+"/"+urlSlices[urlSlices.length-1];
        viewName = viewName.replaceAll(".htm", "");
        
        log.debug("Simple forward path: " + viewName);
        
        /*
        String viewName = "";
        /// .view로 호출되는 경우에는 Controller를 거치디 않고 바로 jsp페이지로 이동시킨다. 
        viewName = req.getRequestURI();
        viewName = viewName.replaceAll(req.getContextPath(), "");
        viewName = viewName.replaceAll(".htm", "");
        viewName = viewName.replaceFirst("/" + StringHelper.null2void(prefixName) + "/", "/");
        log.debug("Simple forward path: " + viewName);
 		*/      
        
        return viewName;
    }
}