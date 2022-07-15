package com.yni.fta.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <p>
 * Controller 클래스의 URL Mapping 전 사용자 세션을 생성하는 클래스
 * </p>
 * 
 * @author YNI-maker
 *
 */
public class SessionInterceptorHandler extends HandlerInterceptorAdapter {
	
	protected Log log = LogFactory.getLog(SessionInterceptorHandler.class);
	
	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 */
	public boolean preHandle(HttpServletResponse response, Object handler) throws Exception {
		log.info("sessionInterceptor launched");
		return true;
	}
}
