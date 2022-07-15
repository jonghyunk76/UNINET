package com.yni.fta.common.controller;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.tools.AESUtil;
import com.yni.fta.common.tools.CertifySupporter;
import com.yni.fta.common.tools.PasswordValidator;
import com.yni.fta.common.tools.StringUtil;
import com.yni.fta.mm.cbox.ComboBox;
import com.yni.fta.mm.iden.UserIden;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.util.AES256Util;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.ServletHelper;
import kr.yni.frame.util.StringHelper;

/**
 * 사용자 인증 처리 클래스
 * 
 * @author YNI-maker
 *
 */
public class CertifyForwordController extends YniAbstractController implements Controller {
	
	private UserIden userIden;

	private ComboBox comboBox;

	private String certifyType; // internal : 내부 사용자, external : 외부사용자(협력사)

	private String prefixName; // 업무명

	public void setCertifyType(String type) {
		this.certifyType = type;
	}

	public void setPrefixName(String name) {
		this.prefixName = name;
	}

	public void setUserIden(UserIden service) {
		this.userIden = service;
	}

	public void setComboBox(ComboBox service) {
		this.comboBox = service;
	}

	/**
	 * 사용자 인증 또는 해지를 처리하는 매소드
	 * Separate connection internal and external jk1742
	 * @param
	 * @param
	 * @return model
	 */
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse respone) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(certifyType.equals("internal")) {
			mav = this.internalCertify(req, respone);
		} else if(certifyType.equals("external")) {
			mav = this.externalCertify(req, respone);
		} else if(certifyType.equals("cloud")) {
			mav = this.cloudCertify(req, respone);
		} else if(certifyType.equals("supplier")) {
			mav = this.externalCertify(req, respone);
		}
		
		if(log.isDebugEnabled()) log.debug("Request - certify type = " + certifyType);
		
		return mav;
	}
	
	/**
	 * 내부 사용자 인증
	 * @param req
	 * @param respone
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ModelAndView internalCertify(HttpServletRequest req, HttpServletResponse respone) throws Exception {
		if (log.isDebugEnabled()) {
            log.debug("start internalCertify user.......");
        }
		
		ModelAndView mav = new ModelAndView();
		
		Map certInfo = null;
		String id = null;
		String pwd = null;
		String companyCode = null;
		String strHost = null;
		
		String redirectUrl = "redirect:/" + StringHelper.null2void(prefixName) + "/contentsIndex.htm";
		String url = StringHelper.null2void(req.getRequestURL());
		HttpSession session = req.getSession();
		
		if (url.indexOf("/authority/logonProcess") > -1) { // 로그인 수행
			// 사용중인 사용자 인증 세션정보가 있는지 체크
			Map member = (LinkedHashMap) session.getAttribute(Constants.SESSION_KEY);
			
			if(member != null) {
    			session.removeAttribute(Constants.SESSION_KEY);
    			member = null;
    			
    			if(log.isDebugEnabled()) log.debug("remove user session...");
    		}
			
			if (member == null) {
				String ssoYn = StringHelper.null2string(req.getParameter("ssoYn"), "N");
				String logType = StringHelper.null2void(req.getParameter("login_type"));
				
				if(logType.equals("sso")) { // 로그인 타입이 sso인 경우, 법인 개별처리를 의미함
					ssoYn = "Y";
				}
				
				DataMap dataMap = new DataMap();
				
				if(log.isDebugEnabled()) log.debug("SSO login Y/N = " + ssoYn);
				
				dataMap.put("ssoYn", ssoYn);
				
				if (ssoYn.equals("Y")) {
					if(logType.equals("sso")) { // 서연그룹 SSO 적용
						id = StringHelper.null2void(req.getParameter("login_id"));
						pwd = "0000";
						
						if(id.length() > 3) {
							companyCode = id.substring(0, 3) + "0";
							
							if(companyCode.equals("1000")) { // 서연그룹 시스템 관리자인 경우 서연이화로 자동연결됩니다.
								companyCode = "1100";
							}
						}
					} else {
						id = StringHelper.null2string(req.getParameter("EMP_NO"), StringHelper.null2void(req.getParameter("USER_ID")));
						pwd = StringHelper.null2void(req.getParameter("PASSWORD"));
						companyCode = StringHelper.null2void(req.getParameter("COMPANY"));
					}
					
					String encryptYn = StringHelper.null2void(properties.getProperty("sso.encryption.use")); // 암호화 적용여부
					String encryptSite = StringHelper.null2void(properties.getProperty("sso.encryption.site")); // 암호화 적용사이트
					String encryptType = StringHelper.null2void(properties.getProperty("sso.encryption.type")); // 암호화 방식
					String encryptPw = StringHelper.null2void(properties.getProperty("sso.encryption.password")); // 암호화 비밀번호
					
					if(encryptYn.equals("1") && (encryptSite.equals("0") || encryptSite.equals("1"))) {
						// 암호화 key값 생성
						StringBuffer keybuf = new StringBuffer();
						String[] keysplit = encryptPw.split("#");
						
						for(int i = 0; i < keysplit.length; i++) {
							if(keysplit[i].indexOf(":") > 0) {
								String[] formatsplit = keysplit[i].split(":");
								String keyStr = "";
								
								if(formatsplit.length > 1) {
									if(formatsplit[0].toUpperCase().equals("DATE")) {
										 SimpleDateFormat sdf = new SimpleDateFormat(formatsplit[1]);
										 Date date = new Date();
										 
										 keyStr = sdf.format(date);
									}
								}
								
								keybuf.append(keyStr);
							} else {
								keybuf.append(keysplit[i]);
							}
						}
						
						log.fatal("decrypt(type:"+encryptType+"/key:"+keybuf.toString()+")");
						
						if(encryptType.equals("AES256")) {							
							id = AESUtil.decrypt(id, keybuf.toString());
							companyCode = AESUtil.decrypt(companyCode, keybuf.toString());
							pwd = AESUtil.decrypt(pwd, keybuf.toString());
						}
						
						log.fatal("decrypt info.(id = " + id + ", companyCode = " + companyCode + ", password = " + pwd + ")");
					}
					
					strHost = req.getRemoteAddr(); // 현재 Local의 IP를 얻는다.
					
					if(log.isDebugEnabled()) log.debug("SSO login info.(id = " + id + ", company = " + companyCode + ")");
					
					dataMap.put("USER_ID", id);
					dataMap.put("PASSWORD", pwd);
					dataMap.put("INIT_PASSWORD", StringUtil.getSHA128(pwd));
					dataMap.put("COMPANY_CD", companyCode);
					dataMap.put("USER_IP", strHost);
					
					certInfo = this.getSsoLoginInfo(dataMap, req, respone); // Authority Certification
					CertifySupporter.addUserSessionInfo(certInfo);
				} else {
					id = StringHelper.null2void(req.getParameter("REQUEST_USER_ID"));
					pwd = StringHelper.null2void(req.getParameter("REQUEST_PASSWORD"));
					companyCode = StringHelper.null2void(req.getParameter("REQUEST_COMPANY_CD"));
					strHost = req.getRemoteAddr(); // 현재 Local의 IP를 얻는다.
					
					dataMap.put("USER_ID", id);
					dataMap.put("PASSWORD", StringUtil.getSHA128(pwd));
					dataMap.put("INIT_PASSWORD", pwd);
					dataMap.put("COMPANY_CD", companyCode);
					dataMap.put("USER_IP", strHost);
					
					certInfo = this.getUserLoginInfo(dataMap);
					CertifySupporter.addUserSessionInfo(certInfo);
				}
				
				if (certInfo == null || certInfo.size() <= 0) {
					redirectUrl = "redirect:/mm/login.htm";

					mav.addAllObjects(ServletHelper.getReqeustParameters(req));
					mav.addObject("MEMBER_SESSION", "N");
				} else {
					certInfo.put(Constants.KEY_LOG_SEQ, Calendar.getInstance().getTimeInMillis());
					
					// Session 정보를 Setting 한다.
					session.setAttribute(Constants.SESSION_KEY, certInfo);
					session.setMaxInactiveInterval(60 * 60 * 6);
					
					try {
						Map map = CertifySupporter.registerUseLog(req, certInfo);
						
				        userIden.insertUseLogInfo(map);
				        userIden.updateUseLogStatus(map);
			        } catch(Exception e) {
			        	if(log.isErrorEnabled()) log.error("Logging's Exception : " + e.getMessage());
			        }
					
					CertifySupporter.changeDefaultLanguage(req, StringHelper.null2string(req.getParameter("selectedLanguage"), StringHelper.null2void(certInfo.get(Constants.KEY_DEFAULT_LANGUAGE))));
				}
			}
			
			if (log.isDebugEnabled()) {
				log.debug("complete login(id=" + id + ", company=" + companyCode + ", session=" + session.getAttribute(Constants.SESSION_KEY) + ")");
			}
		} else if (url.indexOf("/authority/logoffProcess") > -1) { // 로그아웃 수행
			Map member = (LinkedHashMap) session.getAttribute(Constants.SESSION_KEY);
			
			// 로그아웃 시에는 사용자 작업마감월을 초기화 시킨다.
			member.put("WORK_YYYYMM", "");
			
			userIden.updateWorkDate(member);
			
			redirectUrl = "redirect:/mm/login.htm";
			
			session.removeAttribute(Constants.SESSION_KEY); // 로그아웃
			session.invalidate();
		} else if (url.indexOf("/cert/sessionCheck") > -1) { // 로그인 세션정보 체크
			// 사용중인 사용자 인증 세션정보가 있는지 체크
			Map member = (LinkedHashMap) session.getAttribute(Constants.SESSION_KEY);
			
			if (member == null) {
				redirectUrl = "redirect:/mm/login.htm";
				
				mav.addAllObjects(ServletHelper.getReqeustParameters(req));
				mav.addObject("MEMBER_SESSION", "N");
			} else {
				redirectUrl = "redirect:/" + StringHelper.null2void(prefixName) + "/contentsIndex.htm";
				
				mav.addAllObjects(ServletHelper.getReqeustParameters(req));
				mav.addObject("MEMBER_SESSION", "Y");
			}
		}
		
		mav.setViewName(redirectUrl);
		
		log.debug("Redirect RUL = " + redirectUrl);
		
		return mav;
	}
	
	/**
	 * 일반 로그인화면에서 입력한 정보로 사용자 인증정보를 구한다.
	 * 
	 * @param dataMap
	 *          요청 파라메터 정보
	 * @return 세션에 등록할 인증정보를 담고 있는 Map
	 * @throws FrameException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getUserLoginInfo(DataMap dataMap) throws Exception {
		Map<String, Object> result = userIden.selectMember(DataMapHelper.getMap(dataMap)); //TODO Authority Certification
		
		if (result != null && result.size() > 0) {
		    String productAssetsType = StringHelper.null2void(properties.getProperty("biz.product.assets.type"));
		    String partAssetsType = StringHelper.null2void(properties.getProperty("biz.part.assets.type"));
		  
		    result.put(Constants.KEY_SE_PRODUCT_ASSETS_TYPE, productAssetsType); // 자가생산품
		    result.put(Constants.KEY_SE_PART_ASSETS_TYPE, partAssetsType); // 투입자재
		    // 생성자와 수정자 설정
		    result.put(Constants.KEY_S_CERTIFY_TYPE, "internal");
		  
		    if (log.isDebugEnabled())log.debug("login complete.");
	    } else {
	        if (log.isDebugEnabled())log.debug("login failed.");
	    }
		
	    return result;
	}
  
	/**
     * 외부 사용자 인증
     * 
     * @param req
     * @param respone
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private ModelAndView externalCertify(HttpServletRequest req, HttpServletResponse respone) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("start externalCertify user.......");
        }
        
        ModelAndView mav = new ModelAndView();
        
        String redirectUrl = "redirect:/" + StringHelper.null2void(prefixName) + "/contentsIndex.htm";
        String url = StringHelper.null2void(req.getRequestURL());
        HttpSession session = req.getSession();
        
        if (url.indexOf("/authority/logExtProcess") > -1 || url.indexOf("/authority/logSupProcess") > -1 || url.indexOf("/authority/logonProcess") > -1) { // 로그인 수행
            DataMap dataMap = new DataMap();
            
            String strHostIP = req.getRemoteAddr(); //ipAddress
			
            // 사용중인 사용자 인증 세션정보가 있는지 체크
            Map member = (LinkedHashMap) session.getAttribute(Constants.SESSION_KEY);
            
            if(member != null) {
    			session.removeAttribute(Constants.SESSION_KEY);
    			member = null;
    			
    			if(log.isDebugEnabled()) log.debug("remove user session...");
    		}
            
            if (member == null) {
            	// 세션정보를 생성한다.
            	String ssoYn = StringHelper.null2string(req.getParameter("ssoYn"), "N");
				String logType = StringHelper.null2void(req.getParameter("login_type"));
				
				if(logType.equals("sso")) { // 로그인 타입이 sso인 경우, 법인 개별처리를 의미함
					ssoYn = "Y";
				}
				
                Map result = new HashMap();
                
                if(log.isDebugEnabled()) log.debug("SSO login Y/N = " + ssoYn);
				
				dataMap.put("ssoYn", ssoYn);
				
				if (ssoYn.equals("Y")) {
					String vendorCd = StringHelper.null2void(req.getParameter("VENDOR"));
					String companyCd = StringHelper.null2void(req.getParameter("COMPANY"));
					
					String encryptYn = StringHelper.null2void(properties.getProperty("sso.encryption.use")); // 암호화 적용여부
					String encryptSite = StringHelper.null2void(properties.getProperty("sso.encryption.site")); // 암호화 적용사이트
					String encryptType = StringHelper.null2void(properties.getProperty("sso.encryption.type")); // 암호화 방식
					String encryptPw = StringHelper.null2void(properties.getProperty("sso.encryption.password")); // 암호화 비밀번호
					
					if(encryptYn.equals("1") && (encryptSite.equals("0") || encryptSite.equals("2"))) {
						// 암호화 key값 생성
						StringBuffer keybuf = new StringBuffer();
						String[] keysplit = encryptPw.split("#");
						
						for(int i = 0; i < keysplit.length; i++) {
							if(keysplit[i].indexOf(":") > 0) {
								String[] formatsplit = keysplit[i].split(":");
								String keyStr = "";
								
								if(formatsplit.length > 1) {
									if(formatsplit[0].toUpperCase().equals("DATE")) {
										 SimpleDateFormat sdf = new SimpleDateFormat(formatsplit[1]);
										 Date date = new Date();
										 
										 keyStr = sdf.format(date);
									}
								}
								
								keybuf.append(keyStr);
							} else {
								keybuf.append(keysplit[i]);
							}
						}
						
						log.fatal("decrypt(type:"+encryptType+"/key:"+keybuf.toString()+")");
						
						try {
							if(encryptType.equals("AES256")) {		
								companyCd = AESUtil.decrypt(companyCd, keybuf.toString());
								vendorCd = AESUtil.decrypt(vendorCd, keybuf.toString());
							}
						} catch(Exception ae) { 
							log.error(ae);
						}
						
						log.fatal("decrypt info.(vendor code = " + vendorCd + ", company code = " + companyCd + ")");
					}
					
					if(log.isDebugEnabled()) log.debug("SSO login info.(vendor code = " + vendorCd + ", company code = " + companyCd + ")");
					
					dataMap.put("COMPANY_CD", companyCd);
					dataMap.put("USER_IP", strHostIP);
					dataMap.put("VENDOR_CD", vendorCd);
					
					result = this.getSsoLogin4SCMInfo(dataMap, req, respone); // Authority Certification
					
					if (result != null && result.size() > 0) {
						mav.addObject("REQUEST_TYPE", "1");
						redirectUrl = "redirect:/mm/contentsIndex.htm";
					} else {
						mav.addObject("REQUEST_TYPE", "0");
						throw new FrameException(this.getMessage("MSG_LOGIN_FAILED", null, Consistent.IF_BATCH_LANGUAGE));
					}
				} else {
					String id = StringHelper.null2void(req.getParameter("REQUEST_USER_ID"));
		            String pwd = StringHelper.null2void(req.getParameter("REQUEST_PASSWORD"));
		            String companyCode = StringHelper.null2void(req.getParameter("REQUEST_COMPANY_CD"));
		            
	                dataMap.put("USER_ID", id);
					dataMap.put("PASSWORD", StringUtil.getSHA128(pwd));
					dataMap.put("INIT_PASSWORD", pwd);
					dataMap.put("COMPANY_CD", companyCode);
					dataMap.put("USER_IP", strHostIP);
	                
			        result = userIden.selectSupplier(DataMapHelper.getMap(dataMap));
				}
				
                if (result != null && result.size() > 0) {
                	// 사용자 정보에 설정된 기본언어 설정
                    String defaultLanguage = StringHelper.null2void(result.get(Constants.KEY_DEFAULT_LANGUAGE));
                    
                    result.put(Constants.KEY_DEFAULT_LANGUAGE, defaultLanguage);
                    result.put(Constants.KEY_S_CERTIFY_TYPE, "external");
                    result.put("SUPPLIER_TYPE", "ST");
                    
                    // Session 정보를 Setting 한다.
                    session.setAttribute(Constants.SESSION_KEY, result);
                    
                    if (log.isDebugEnabled())log.debug("login complete.");
                }
                
                CertifySupporter.addUserSessionInfo(result);
				
                if (result == null || result.size() <= 0) {
                	if(certifyType.equals("supplier")) {
                		redirectUrl = "redirect:/supplier/supplier_login.htm";
                	} else {
                		redirectUrl = "redirect:/mm/login.htm";
                	}
                	
					mav.addAllObjects(ServletHelper.getReqeustParameters(req));
					mav.addObject("MEMBER_SESSION", "N");
				} else {
					result.put(Constants.KEY_LOG_SEQ, Calendar.getInstance().getTimeInMillis());
					
					// Session 정보를 Setting 한다.
					session.setAttribute(Constants.SESSION_KEY, result);
					session.setMaxInactiveInterval(60 * 60 * 6);
					
					// 로그기록
					try {
						Map map = CertifySupporter.registerUseLog(req, result);
						
				        userIden.insertUseLogInfo(map);
				        userIden.updateUseLogStatus(map);
			        } catch(Exception e) {
			        	if(log.isErrorEnabled()) log.error("Logging's Exception : " + e.getMessage());
			        }
					
					// 언어설정
					CertifySupporter.changeDefaultLanguage(req, StringHelper.null2string(req.getParameter("selectedLanguage"), StringHelper.null2void(result.get(Constants.KEY_DEFAULT_LANGUAGE))));
				}
            }
            
            if (log.isDebugEnabled()) {
                log.debug("supplier complete login(session=" + session.getAttribute(Constants.SESSION_KEY) + ")");
            }
        } else if (url.indexOf("/authority/logoffProcess") > -1 || url.indexOf("logoffSupProcess") > -1) { // 로그아웃 수행
            // 로그아웃
            session.removeAttribute(Constants.SESSION_KEY);
            session.invalidate(); 
            
            if(certifyType.equals("supplier")) {
        		redirectUrl = "redirect:/supplier/supplier_login.htm";
        	} else {
        		redirectUrl = "redirect:/mm/login.htm";
        	}
            
            if (log.isErrorEnabled()) log.error("supplier complete logout.(session=" + Constants.SESSION_KEY + ")");
        } else if (url.indexOf("/cert/sessionCheck.do") > -1) { // 로그인 세션정보 체크
        	// 사용중인 사용자 인증 세션정보가 있는지 체크
			Map member = (LinkedHashMap) session.getAttribute(Constants.SESSION_KEY);
			
			if (member == null) {
				if(certifyType.equals("supplier")) {
            		redirectUrl = "redirect:/supplier/supplier_login.htm";
            	} else {
            		redirectUrl = "redirect:/mm/login.htm";
            	}
				
				mav.addAllObjects(ServletHelper.getReqeustParameters(req));
				mav.addObject("MEMBER_SESSION", "N");
			} else {
				redirectUrl = "redirect:/" + StringHelper.null2void(prefixName) + "/contentsIndex.htm";
				
				mav.addAllObjects(ServletHelper.getReqeustParameters(req));
				mav.addObject("MEMBER_SESSION", "Y");
			}
        } else {
        	// 로그아웃
            session.removeAttribute(Constants.SESSION_KEY);
            session.invalidate(); 
            
        	if(certifyType.equals("supplier")) {
        		redirectUrl = "redirect:/supplier/supplier_login.htm";
        	} else {
        		redirectUrl = "redirect:/mm/login.htm";
        	}
        }
        
        mav.setViewName(redirectUrl);
		
		log.debug("Redirect RUL = " + redirectUrl);
		
		return mav;
    }
	
    /**
     * 클라우드 가입회사 인증
     * 
     * @param req
     * @param respone
     * @return
     * @throws Exception
     */
	private ModelAndView cloudCertify(HttpServletRequest req, HttpServletResponse respone) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("start cloudCertify user.......");
        }
        
        ModelAndView mav = new ModelAndView();
        
        String url = StringHelper.null2void(req.getRequestURL());
        String serviceType = StringHelper.null2void(req.getParameter("SERVICE_TYPE"));
        String companyCode = StringHelper.null2void(req.getParameter("COMPANY_CD"));
        String certKey =  StringHelper.null2void(req.getParameter("LICENSE_NO"));
        
        if(url.isEmpty()) {
        	// 잘못된 경로에 대한 에러메시지 리턴
        	throw new FrameException(this.getMessage("MSG_NO_RESPONSE_URL", null, Consistent.IF_BATCH_LANGUAGE));
        } else {
        	DataMap dataMap = new DataMap();
            
            dataMap.put("SERVICE_TYPE", serviceType);
			dataMap.put("COMPANY_CD", companyCode);
			dataMap.put("LICENSE_NO", certKey);
			
        	// 클라우드 가입여부 조회
        	String openYn = "N";
        	
        	// 인증실패 시 에러메시지를 리턴
        	if(openYn == null || openYn.equals("N")) {
        		throw new FrameException(this.getMessage("MSG_NO_CERTIFY_COMPANY", null, Consistent.IF_BATCH_LANGUAGE));
        	}
        }
        
        mav.setViewName(url);
        
        log.debug("Redirect RUL = " + url);
        
        return mav;
    }

    /**
     * SSO 로그인시 그룹웨어 시스템과 연계해서 사용자 인증정보를 구한다.
     * 
     * @param dataMap
     * @return 세션에 등록할 인증정보를 담고 있는 Map
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private Map getSsoLoginInfo(DataMap dataMap,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = null;
        
        if(dataMap.getString("USER_ID").equals("fta")) {
        	result = userIden.selectMember(DataMapHelper.getMap(dataMap)); //TODO Authority Certification
        } else {
        	result = userIden.selectPassMember(DataMapHelper.getMap(dataMap));
        }
        
        if (result != null && result.size() > 0) {
            String productAssetsType = StringHelper.null2void(properties.getProperty("biz.product.assets.type"));
            String partAssetsType = StringHelper.null2void(properties.getProperty("biz.part.assets.type"));

            result.put(Constants.KEY_SE_PRODUCT_ASSETS_TYPE, productAssetsType); // 자가생산품
            result.put(Constants.KEY_SE_PART_ASSETS_TYPE, partAssetsType); // 투입자재
            // 생성자와 수정자 설정
            result.put(Constants.KEY_S_CERTIFY_TYPE, "internal");

            if (log.isDebugEnabled())log.debug("SSO login complete.");
        } else {
            if (log.isDebugEnabled())log.debug("SSO login failed.");
            
            request.setAttribute("chageLanguage", "KR");
            request.setAttribute("APPLICATION_SERVICE_TYPE", "ON");
            
            String url = "http://"+request.getServerName() + ":" + request.getServerPort()+"/mm/bypass/login.htm";
            
            log.debug("address = " + url);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);

  	      	dispatcher.forward(request, response);
        }

        return result;
    }
    
    /**
     * SSO 로그인시 SCM 시스템과 연계해서 사용자 인증정보를 구한다.
     * 
     * @param dataMap
     * @return 세션에 등록할 인증정보를 담고 있는 Map
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private Map getSsoLogin4SCMInfo(DataMap dataMap,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = null;
        
        result = userIden.selectSsoSupplierIdenCheck(DataMapHelper.getMap(dataMap));
        
        if (result != null && result.size() > 0) {
            result.put(Constants.KEY_S_CERTIFY_TYPE, "external");
            
            if (log.isDebugEnabled())log.debug("SSO login complete.");
        } else {
            if (log.isDebugEnabled())log.debug("SSO login failed.");
            
            request.setAttribute("chageLanguage", "KR");
            request.setAttribute("APPLICATION_SERVICE_TYPE", "ON");
            //result.put("REQUEST_TYPE", "0");
           
            //String url = "http://"+request.getServerName() + ":" + request.getServerPort()+"/supplier/supplier_login.htm";
            
            //log.debug("address = " + url);
            
            //RequestDispatcher dispatcher = request.getRequestDispatcher(url);

  	      	//dispatcher.forward(request, response);
        }

        return result;
    }
    
    /**
     * ID,PWD 체크,PWD_DATE 확인,FAIL_DATE확인
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    public Map<String, Object> loginCheck(HttpServletRequest req, Map map, String exist) throws Exception {
        Map<String, Object> resultMap = new HashMap();
        String message = "";
        boolean result = false;
        String type = "";

        /*
        * try {
        * 
        * if (exist.equals("0")) { // Count 초기화 =================================> //int cnt = mainService.insertOpUserFailClean(map); int cnt = 1;
        * 
        * if(cnt != 1){ throw new Exception("업데이트에 실패했습니다."); } if (passwordValidation(req, map)) { result = true; } else { result = true; // 주석 해제 //message = "패스워드 정책 오류"; //type= "passModifyPopup"; } } else if (exist.equals("1")) {
        * 
        * 2. 유저 상태 조회(실패횟수,실패타입,패스워드변경시간 등)
        * 
        * //Map OpUserChkMap = mainService.selectOpUserChk(map); //
        * 
        * //int failCnt = Integer.parseInt(StringHelper.null2void(OpUserChkMap.get("FAIL_CNT"))); //String failTimeFlag = StringHelper.null2void(OpUserChkMap.get("FAIL_TIME_FLAG")); //String pwdFlag = StringHelper.null2void(OpUserChkMap.get("PWD_FLAG")); //String pauseYn = StringHelper.null2void(OpUserChkMap.get("PAUSE_YN"));
        * 
        * //if (failCnt > 5 && "F".equals(failTimeFlag)) { // message = "해당 ID는 5회연속 틀렸습니다.<br>60분후에 다시 접속하세요."; //} else if (failCnt > 5 && "T".equals(failTimeFlag)) {
        * 
        * //int cnt = mainService.insertOpUserFailClean(map);
        * 
        * if(cnt != 1){ throw new Exception("업데이트에 실패했습니다."); } message = "해당 ID가 초기화 되었습니다.다시 접속해주세요.";
        * 
        * } else if ("T".equals(pwdFlag) && "Y".equals(pauseYn)) { message = "해당 아이디 패스워드 변경일이 90일이 지나 휴먼 개정으로 등록되었습니다.</br>관리자에게 문의하세요."; } else if ("T".equals(pwdFlag) && "N".equals(pauseYn)) { message = "해당 아이디 패스워드 변경일이 90일이 지났습니다.패스워드를 변경해주세요."; type= "passModifyPopup"; }
        * 
        * } else if (exist.equals("2")){ message = "해당 아이디는 미 존재 합니다."; }
        * 
        * resultMap.put("message", StringHelper.null2void(message)); resultMap.put("result", result); resultMap.put("type", type);
        * 
        * } catch (Exception exp) { message = getExceptionMessage(req, exp, "MSG_UNSPECIFIED_ERROR"); }
        */

        return resultMap;
    }

    /**
     * PWD Validation 체크
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    public boolean passwordValidation(HttpServletRequest req, Map<String, Object> map) throws Exception {
        boolean passcheck = false;
        String userId = StringHelper.null2void(map.get("userId"));
        String password = StringHelper.null2void(map.get("password"));
        /*
        * 1. 패스워드 유효성 체크 1) 영문대문자, 영문소문자, 숫자, 특수문자 3가지 이상 조합 2) 8자리 이상
        */
        PasswordValidator va = new PasswordValidator();
        passcheck = va.validate(password);

        return passcheck;
    }
}