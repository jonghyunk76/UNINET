package com.yni.fta.common.handler;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yni.fta.common.logger.InterfaceLogger;
import com.yni.fta.common.tools.CertifySupporter;
import com.yni.fta.common.tools.WebsocketSupporter;
import com.yni.fta.mm.iden.UserIden;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * 응답전 사용자 권한 등을 검색하는 클래스
 * 
 * @author YNI-maker
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ResponseInterceptorHandler extends HandlerInterceptorAdapter {
    
    private static Log log = LogFactory.getLog(ResponseInterceptorHandler.class);
    
    private UserIden userIden;
    
    public void setUserIden(UserIden service) {
        this.userIden = service;
    }
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map sessionMap = (LinkedHashMap) request.getSession().getAttribute(Constants.SESSION_KEY);
        
        if(log.isDebugEnabled()) log.debug("intercceptor prehandle start....(sessionMap = " + sessionMap + ")");
        
        if(sessionMap != null) {
            String lang = StringHelper.null2void(sessionMap.get(Constants.KEY_S_DEFAULT_LANGUAGE));
            
            if(!lang.isEmpty()) {
                CertifySupporter.changeDefaultLanguage(request, lang);
            }
            
	        // 사용로그 정보 등록
	        Map map = new HashMap();
	        String reqType = StringHelper.null2void(request.getParameter("REQUEST_TYPE"));
	        
	        if(log.isDebugEnabled()) log.debug("Request type = " + reqType);
	        
	        if(!reqType.isEmpty()) {
		        try {
		        	sessionMap.put(Constants.KEY_LOG_SEQ, Calendar.getInstance().getTimeInMillis());
		        	
		        	map = CertifySupporter.registerUseLog(request, sessionMap);
		        	
			        // 사용자 로그 기록
			        userIden.insertUseLogInfo(map);
			        userIden.updateUseLogStatus(map);
		        } catch(Exception e) {
		        	if(log.isErrorEnabled()) log.error("Logging's Exception : " + e.getMessage());
		        }
	        } else {
	        	sessionMap.remove(Constants.KEY_LOG_SEQ);
	        }
	        
	        if(reqType.equals("1") || reqType.equals("2")) { // 0:로그인, 1:메뉴이동, 3:dialog
	          this.setMenuAuthority(request, map);
	        }
        }
        
        return true;
    }
    
    /**
     * 사용자 메뉴 권한 조회 및 변경
     * 
     * @param req
     * @param dataMap
     * @throws Exception
     */
    public void setMenuAuthority(HttpServletRequest request, Map map) throws Exception {
        Map sessionMap = (LinkedHashMap) request.getSession().getAttribute(Constants.SESSION_KEY);
        
        if(sessionMap != null) {
            String certifyType = StringHelper.null2void(sessionMap.get(Constants.KEY_S_CERTIFY_TYPE));
            
            if("external".equals(certifyType)) { // 외부 협력사 사용자인 경우
                sessionMap.put(Constants.KEY_SEL_AUTH, "inline");
                sessionMap.put(Constants.KEY_REG_AUTH, "inline");
                sessionMap.put(Constants.KEY_UPD_AUTH, "inline");
                sessionMap.put(Constants.KEY_DEL_AUTH, "inline");
                sessionMap.put(Constants.KEY_EXC_AUTH, "inline");
                sessionMap.put(Constants.KEY_FLE_AUTH, "inline");
            } else {
                Map authMap = userIden.selectMenuAuthorityInfo(map);
                
                // 스케쥴이 특정 서버에서만 실행되도록 지정할 경우 이와 관련된 서버에서만 스케쥴을 제어할 수 있도록 권한을 변경한다.
                boolean allow = false;
            
                Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
                String allowServer = StringHelper.null2void(configurator.getString("batch.server.name"));
                
                if(log.isDebugEnabled()) log.debug("server host = " + Constants.SERVER_HOST_ADDRESS + ",allow server = " + allowServer);
                
                List<String> serverList = StringHelper.split(allowServer, ",");
            
                if(serverList.size() > 0) {
                    for(int i = 0; i < serverList.size(); i++) {
                        if(StringHelper.strEquals(serverList.get(i), Constants.SERVER_HOST_ADDRESS) || serverList.get(i).equals("localhost") || serverList.get(i).equals("127.0.0.1")) {
                            allow = true;
                            break;
                        }
                    }
                } else {
                    allow = true;
                }
                
                if(authMap != null && allow) {
                    sessionMap.put(Constants.KEY_SEL_AUTH, StringHelper.null2string(authMap.get("SEL_AUTH"), "inline"));
                    sessionMap.put(Constants.KEY_REG_AUTH, StringHelper.null2string(authMap.get("REG_AUTH"), "none"));
                    sessionMap.put(Constants.KEY_UPD_AUTH, StringHelper.null2string(authMap.get("UPD_AUTH"), "none"));
                    sessionMap.put(Constants.KEY_DEL_AUTH, StringHelper.null2string(authMap.get("DEL_AUTH"), "none"));
                    sessionMap.put(Constants.KEY_EXC_AUTH, StringHelper.null2string(authMap.get("EXC_AUTH"), "none"));
                    sessionMap.put(Constants.KEY_FLE_AUTH, StringHelper.null2string(authMap.get("FLE_AUTH"), "none"));
                } else {
                    sessionMap.put(Constants.KEY_SEL_AUTH, "inline");
                    sessionMap.put(Constants.KEY_REG_AUTH, "none");
                    sessionMap.put(Constants.KEY_UPD_AUTH, "none");
                    sessionMap.put(Constants.KEY_DEL_AUTH, "none");
                    sessionMap.put(Constants.KEY_EXC_AUTH, "none");
                    sessionMap.put(Constants.KEY_FLE_AUTH, "none");
                }
            }
            
            request.getSession().setAttribute(Constants.SESSION_KEY, sessionMap);
        }
    }
    
    /**
     * HttpServetRequest객체에 Session 중 값이 없는 경우 remove시킨다.
     * 
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(log.isDebugEnabled()) log.debug("Response ContentType = " + response.getContentType() + ", Encoding Type = " + response.getCharacterEncoding());
        
        try {
        	Map map = new HashMap();
            Map sessionMap = (LinkedHashMap) request.getSession().getAttribute(Constants.SESSION_KEY);
            String logSeq = StringHelper.null2void(sessionMap.get(Constants.KEY_LOG_SEQ));
            
            if(!logSeq.isEmpty()) {
	            map.put(Constants.KEY_LOG_SEQ, logSeq);
	            
		        userIden.updateUseLogInfo(map);
            }
        } catch(Exception e) {
        	if(log.isErrorEnabled()) log.error("Logging's Exception : " + e.getMessage());
        }
        
		// file type인 경우 부하를 줄이기 위해 응답되는 파일정보를 삭제한다.
		if(request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			
			DataMap dataMap = (DataMap)multipartRequest.getAttribute(Constants.DATA_MAP);
			Map map = DataMapHelper.getMap(dataMap);
			
			for(Iterator it = map.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = (String) entry.getKey();
				
				if(map.get(key) instanceof FormFile) {
					if(log.isDebugEnabled()) log.debug("Removed files : name = " + key + ", value = " + map.get(key));
				}
			}
			
			DataMapHelper.removeFormFile(map);
		}
		
        // 세션 검색
        Enumeration atObjs = request.getAttributeNames();
        
        while(atObjs.hasMoreElements()) {
            Object obj = atObjs.nextElement();
            
            if(obj != null) {
                if(obj instanceof String){
                    Object att = request.getAttribute(obj.toString());
                    
                    if(att instanceof List) {
                        List listObj = (List) att; // 1차 배열
                        
                        if(listObj != null && listObj.size() > 0) {
                          for(int i = 0; i < listObj.size(); i++) {
                            if(listObj.get(i) instanceof List) {
                                    List listObj1 = (List) listObj.get(i); // 2차 배열
                                    
                                    if(listObj1 == null || listObj1.size() == 0) {
                                        listObj1.add(i, new LinkedHashMap());
                                    }
                            }
                          }
                        } else {
                            request.removeAttribute(obj.toString());
                            if(log.isDebugEnabled()) log.info("Attribute has remove(Array's index 0) : " + obj.toString());
                            
                            break; // resin 반영시에는 주석처리 하시기 바랍니다.
                        }    
                    }
                }
            }
        }
        
        // 파라메터 검색
        StringBuffer paramBf = new StringBuffer();
        Enumeration paramObjs = request.getParameterNames();
        String wsCode = null;
        		
        while(paramObjs.hasMoreElements()) {
            Object obj = paramObjs.nextElement();
            if(obj != null) {
                if(obj instanceof String) {
                    Object param = request.getAttribute(obj.toString());
                    String valStr = request.getParameter(obj.toString());
                    String logStr = "name=" + obj.toString();
                    
                    if(valStr.length() > 300) log.info("Request Parameter : " + logStr + ", value(overflow)=" + valStr.substring(0, 300));
                    else if(log.isDebugEnabled()) log.info("Request Parameter : " + logStr + ", value=" + valStr);
                    
                    if(obj.toString().equals("websocket")) {
                    	wsCode = valStr;
                    }
                    
                    if(param instanceof List) {
                        List listObj = (List) param;
                        
                        if(log.isDebugEnabled()) log.info("Parameter is List : " + listObj.toString());
                    }
                }
            }
        }
    }
    
    /**
     * 컨트롤러 진입 후 view가 정상적으로 랜더링 된 후 제일 마지막에 실행이 되는 메서드
     * 
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    	Enumeration paramObjs = request.getParameterNames();
        String wsCode = null;
        String rslog = "N"; // 중계서버 로그 표시여부
        
        try {
	        while(paramObjs.hasMoreElements()) {
	            Object obj = paramObjs.nextElement();
	            
	            if(obj != null) {
	                if(obj instanceof String) {
	                    Object param = request.getAttribute(obj.toString());
	                    String valStr = request.getParameter(obj.toString());
	                    
	                    // 웹소켓 검색
	                    if(obj.toString().equals("websocket")) {
	                    	wsCode = valStr;
	                    	break;
	                    }
	                    
	                    if(obj.toString().equals("RELAY_SERVER_LOG_YN")) {
	                    	rslog = valStr;
	                    	break;
	                    }
	                }
	            }
	        }
        
    		log.debug("Web socket close(id = " + wsCode + ")");
    		
	    	if(wsCode != null && !"NULL".equals(wsCode.toUpperCase())) {
				WebsocketSupporter ws = new WebsocketSupporter();
				Session session = null;
				
				try {
					session = ws.getSession(wsCode);
				} catch(Exception e) {
					if(log.isErrorEnabled()) log.error(e);
				}
				
				if(session != null) {
					ws.handleMessage("MM0001_01", session);
				}
	    	}
	    	
	    	// 중계서버인 경우, 클라이언트 요청경로를 접속중인 모든 클라이언트에게 Websocket 메시지를 보낸다.
	    	if(Constants.APPLICATION_SYSTEM_ID.equals("RS")) {
		    	List<Session> clients = WebsocketSupporter.clients;
		    	
		    	for(Session s : clients) {
		    		WebsocketSupporter ws = new WebsocketSupporter();
		    		
		    		ws.handleMessage("MMA001_07"+"[SYS]"+ InterfaceLogger.getHeaderMessage(request) + request.getRequestURI(), s);
		    	}
	    	}
        } catch(Exception e) {
        	if(log.isErrorEnabled()) log.error(e);
        }
	
        log.debug("postHandle end");
    }
}
