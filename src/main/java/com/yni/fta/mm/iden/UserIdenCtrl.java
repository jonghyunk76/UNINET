package com.yni.fta.mm.iden;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.common.tools.CertifySupporter;
import com.yni.fta.common.tools.StringUtil;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 사용자 인증을 위한 Controller클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class UserIdenCtrl extends YniAbstractController {
	
	@Resource(name="userIden")
	private UserIden userIden;
	
	/**
     * main 페이지로 이동
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/contentsIndex")
    public ModelAndView contentsIndex(HttpServletRequest req, DataMap dataMap) throws Exception {
    	String message = null;
    	
    	try {
    		String lang = StringHelper.null2void(dataMap.get("selectedLanguage"));
    		
    		if(!lang.isEmpty()){	
            	CertifySupporter.changeDefaultLanguage(req, lang);
            } else {
            	log.debug("moved main page....(data map = " + dataMap.getMap()+")");
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
    	
    	return WebAction.forwarding("/MAIN/index", dataMap, message);
    }
    
    /**
     * 사용자 등록상태 조회
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/cbox/selectUserStatus")
    public ModelAndView selectUserStatus(HttpServletRequest req, DataMap dataMap) throws Exception {
    	String message = null;
    	Map returnMap = null;
    	
    	try {
    		Map map = DataMapHelper.getMap(dataMap);
    		
    		dataMap.put("USER_ID", StringHelper.null2void(map.get("REQUEST_USER_ID")));
    		dataMap.put("COMPANY_CD", StringHelper.null2void(map.get("REQUEST_COMPANY_CD")));
    		
			returnMap = userIden.selectUserStatus(map);
    	} catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
    	
    	return WebAction.returnMap(returnMap, message);
    }
    
    /**
     * 사용자 인증여부 체크
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/cbox/selectUserIdenCheck")
    public ModelAndView selectUserIdenCheck(HttpServletRequest req, DataMap dataMap) throws Exception {
    	String message = null;
    	Map returnMap = null;
    	
    	try {
    		Map map = DataMapHelper.getMap(dataMap);
    		
			dataMap.put("USER_ID", StringHelper.null2void(map.get("REQUEST_USER_ID")));
			dataMap.put("PASSWORD", StringUtil.getSHA128(StringHelper.null2void(map.get("REQUEST_PASSWORD"))));
			dataMap.put("INIT_PASSWORD", StringHelper.null2void(map.get("REQUEST_PASSWORD")));
			dataMap.put("COMPANY_CD", StringHelper.null2void(map.get("REQUEST_COMPANY_CD")));
			dataMap.put("USER_IP", StringHelper.null2void(req.getRemoteAddr()));
			
			returnMap = userIden.selectUserIdenCheck(map);
    	} catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
    	
    	return WebAction.returnMap(returnMap, message);
    }
    
    /**
     * 협력사 사용자 정보 체크하기
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/cbox/selectSupplierIdenCheck")
    public ModelAndView selectSupplierIdenCheck(HttpServletRequest req, DataMap dataMap) throws Exception {
    	String message = null;
    	Map returnMap = null;
    	
    	try {
    		Map map = DataMapHelper.getMap(dataMap);
    		
			dataMap.put("USER_ID", StringHelper.null2void(map.get("REQUEST_USER_ID")));
			dataMap.put("PASSWORD", StringUtil.getSHA128(StringHelper.null2void(map.get("REQUEST_PASSWORD"))));
			dataMap.put("INIT_PASSWORD", StringHelper.null2void(map.get("REQUEST_PASSWORD")));
			dataMap.put("COMPANY_CD", StringHelper.null2void(map.get("REQUEST_COMPANY_CD")));
			dataMap.put("USER_IP", StringHelper.null2void(req.getRemoteAddr()));
			
			returnMap = userIden.selectSupplierIdenCheck(map);
    	} catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
    	
    	return WebAction.returnMap(returnMap, message);
    }
    
    /**
     * main 페이지로 이동
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/changeCompany")
    public ModelAndView changeCompany(HttpServletRequest req, DataMap dataMap) throws Exception {
    	String message = null;
    	
    	try {
    		Map map = DataMapHelper.getMap(dataMap);
    		
    		HttpSession session = req.getSession();
    		Map member = (LinkedHashMap) session.getAttribute(Constants.SESSION_KEY);
    		String cmpcd = StringHelper.null2void(map.get("COMPANY_CD"));
    		String usrid = StringHelper.null2void(map.get("USER_ID"));
    		
    		if(cmpcd.isEmpty() || usrid.isEmpty()) {
    			if(log.isDebugEnabled()) log.debug("can't remove user session...");
    		} else {
    			if(member != null) {
	    			session.removeAttribute(Constants.SESSION_KEY);
	    			
	    			if(log.isDebugEnabled()) log.debug("remove user session...");
    			}
    		
	    		String certType = StringHelper.null2void(map.get("CERTIFY_TYPE"));
	    		Map certInfo = null;
	    		
	    		log.debug("Site type = " + certType + ", request info = " + map.toString());
	    		
	    		if(certType.equals("external")) {
	    			certInfo = userIden.selectPassSupplier(map);
	    		} else {
	    			certInfo = userIden.selectPassMember(map);
	    		}
	    		
	    		if(certInfo != null && certInfo.size() > 0) {
		    		CertifySupporter.addUserSessionInfo(certInfo);
		    		
		    		session.setAttribute(Constants.SESSION_KEY, certInfo);
		    		
		    		String lang = StringHelper.null2void(dataMap.get("selectedLanguage"));
		    		if(!lang.isEmpty()){
		    			CertifySupporter.changeDefaultLanguage(req, lang);
		    		}
		    		
		    		if(log.isDebugEnabled()) log.debug("new create user session = " + certInfo.toString());
	    		}
    		}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
    	
    	return WebAction.forwarding("/MAIN/index", dataMap, message);
    }
    
    /**
     * 사용자가 속한 회사 목록 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/selectCompanyOfUser")
    public ModelAndView selectCompanyOfUser(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = userIden.selectCompanyOfUser(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 협력사가 속한 고객사 목록 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/selectCompanyOfSupplier")
    public ModelAndView selectCompanyOfSupplier(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = userIden.selectCompanyOfSupplier(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 문의할 사용자 정보 조회
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/selectInquireUserInfo")
    public ModelAndView selectInquireUserInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	String message = null;
    	Map returnMap = null;
    	
    	try {
    		Map map = DataMapHelper.getMap(dataMap);
    		
			returnMap = userIden.selectInquireUserInfo(map);
    	} catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
    	
    	return WebAction.returnMap(returnMap, message);
    }
    
}
