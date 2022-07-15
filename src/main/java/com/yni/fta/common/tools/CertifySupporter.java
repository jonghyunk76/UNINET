package com.yni.fta.common.tools;

import java.net.InetAddress;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.context.ApplicationContextFactory;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

public class CertifySupporter {
	
	private static Log log = LogFactory.getLog(CertifySupporter.class);
	
	public CertifySupporter(){}
	
	/**
     * 언어 change
     * @param req
     * @param chageLanguage
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void changeDefaultLanguage(HttpServletRequest req, String chageLanguage) throws Exception {
    	SessionLocaleResolver localeResolver = null;
    	localeResolver = (SessionLocaleResolver) ApplicationContextFactory.getAppContext().getBean("localeResolver");
    	
    	Map map = (LinkedHashMap) req.getSession().getAttribute(Constants.SESSION_KEY);
    	String defaultLanguage = StringHelper.null2string(chageLanguage, Constants.DEFAULT_LANGUAGE);
		
		if (defaultLanguage.equals("EN")) {
			localeResolver.setDefaultLocale(Locale.ENGLISH);
		} else if (defaultLanguage.equals("KR")) {
			localeResolver.setDefaultLocale(Locale.KOREA);
		} else if (defaultLanguage.equals("ES")) {
			localeResolver.setDefaultLocale(new Locale("es", "ES"));
		} else if (defaultLanguage.equals("VN")) {
			localeResolver.setDefaultLocale(new Locale("vi", "VN"));
		} else {
			localeResolver.setDefaultLocale(req.getLocale());
			defaultLanguage = StringHelper.null2void(req.getLocale().getLanguage()).toUpperCase();
		}
		
		if(log.isDebugEnabled()) log.debug("set changed language : " + defaultLanguage);
		
		map.put(Constants.KEY_DEFAULT_LANGUAGE, defaultLanguage);
		
    	req.getSession().setAttribute(Constants.SESSION_KEY, map);
    }
    
    /**
	 * 로그인 사용자 세션에 공통적으로 생성할 정보를 추가하는 메소드 
	 * 
	 * @param dataMap 세션정보에 포함시킬 정보를 담고 있는 Map
	 * @return 세션에 등록할 인증정보를 담고 있는 Map
	 * @throws FrameException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void addUserSessionInfo(Map map) throws Exception {
		String userId = StringHelper.null2void(map.get(Constants.KEY_USER_ID));
		String exRate = StringHelper.null2void(map.get(Constants.KEY_EXCHANGE_RATE));

		map.put(Constants.KEY_EXCHANGE_RATE, exRate);
		
		// 생성자와 수정자 설정
		map.put(Constants.KEY_CREATE_BY, userId);
		map.put(Constants.KEY_UPDATE_BY, userId);
		
		// 권한 부여(허용:inline, 거부:none)
		map.put(Constants.KEY_SEL_AUTH, "inline"); // 현재 조회 권한은 무조건 허용(inline)하고 있음
		map.put(Constants.KEY_REG_AUTH, "none");
		map.put(Constants.KEY_UPD_AUTH, "none");
		map.put(Constants.KEY_DEL_AUTH, "none");
		map.put(Constants.KEY_EXC_AUTH, "none");
		map.put(Constants.KEY_FLE_AUTH, "none");
		
		// properties설정 값 추가
		map.put("APPLICATION_SERVICE_TYPE", Constants.APPLICATION_SERVICE_TYPE);
	}
	
	/**
     * 작업월 변경
     * 
     * @param req
     * @param chageLanguage
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void changeWorkClosingMonth(HttpServletRequest req, String mon) throws Exception {
    	Map map = (LinkedHashMap) req.getSession().getAttribute(Constants.SESSION_KEY);
    	
    	if(log.isDebugEnabled()) log.debug("set changed work month : " + mon);
		
		map.put(Constants.KEY_WORK_YYYY_MM, mon);
		
    	req.getSession().setAttribute(Constants.SESSION_KEY, map);
    }
    
    /**
     * 사용자 이용로그정보 생성
     * 
     * @param req
     * @param certInfo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public static Map registerUseLog(HttpServletRequest req, Map certInfo) throws Exception {
    	// 로그기록
		Map map = new HashMap();
        InetAddress addr = InetAddress.getLocalHost();
        String reqType = StringHelper.null2void(req.getParameter("REQUEST_TYPE"));
        
        map.put("LOG_SEQ", StringHelper.null2void(certInfo.get(Constants.KEY_LOG_SEQ)));
    	map.put("COMPANY_CD", StringHelper.null2void(certInfo.get(Constants.KEY_S_COMPANY_CD)));
    	map.put("USER_ID", StringHelper.null2void(certInfo.get(Constants.KEY_S_USER_ID)));
    	map.put("SITE_TYPE", StringHelper.null2void(certInfo.get(Constants.KEY_S_CERTIFY_TYPE)));
    	map.put("COOPER_CD", StringHelper.null2string(certInfo.get(Constants.KEY_S_VENDOR_CD), certInfo.get(Constants.KEY_S_COMPANY_CD).toString()));
    	map.put("MENU_ID", StringHelper.null2void(req.getParameter("MENU_ID")));
    	map.put("SERVER_IP", addr.getHostAddress());
    	map.put("REQUEST_TYPE", reqType);
        map.put("REQUEST_URL", req.getRequestURI());
        map.put("CLIENT_IP", req.getRemoteAddr());
        map.put("PARAMETER", getParameters(req));
        
        if(log.isInfoEnabled()) log.info("Logging information(" + map.toString() + ")");
        
        return map;
    }
    
    /**
     * 요청 파라메터 구하기
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public static String getParameters(HttpServletRequest request) throws Exception {
    	StringBuffer buf = new StringBuffer();
    	Enumeration paramObjs = request.getParameterNames();
        
        while(paramObjs.hasMoreElements()) {
            Object obj = paramObjs.nextElement();
            
            if(obj != null) {
                if(obj instanceof String) {
                	String pname = obj.toString();
                    Object param = request.getAttribute(pname);
                    
                    if(param instanceof List) {
                        List listObj = (List) param;
                        
                        if(log.isDebugEnabled()) log.info("Parameter is List : " + listObj.toString());
                    } else {
                    	String logStr = "name=" + pname + ", value=" + request.getParameter(pname);
                    	
                    	if(!(pname.equals("COMPANY_CD") || pname.equals("USER_ID") || pname.equals("SITE_TYPE") || pname.equals("COOPER_CD") || 
                       	     pname.equals("MENU_ID") || pname.equals("REQUEST_TYPE"))) {
	                    	
	                    	buf.append("[");
	                    	buf.append(logStr);
	                    	buf.append("],");
                    	}
                    }
                }
            }
        }
        
		String rst = buf.toString(); 
		
		if(rst.length() > 0) {
			rst = rst.substring(0,rst.length()-1);
		}
		
		return rst;
    }
    
}
