package com.yni.fta.mm.iden.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.iden.UserIden;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.StringHelper;

/**
 * 사용자 인증을 위한 기능 구현
 * 
 * @author YNI-maker
 *
 */
@Service("userIden")
public class UserIdenImpl extends YniAbstractService implements UserIden {
	
	@Resource(name="userIdenDao")
	private UserIdenDao userIdenDao;
	
	public Map<String, Object> selectUserStatus(Map<String, Object> map) throws Exception {
		return userIdenDao.selectUserStatus(map);
	}
	
	public Map<String, Object> selectMember(Map<String, Object> map) throws Exception {
		Map<String, Object> sessionInfo = null;
		Map<String, Object> userInfo = userIdenDao.selectMember(map);
		
		if(userInfo!=null){
			String userAnonymous = StringHelper.null2void(map.get("USER_ID"));
			String userId = StringHelper.null2void(userInfo.get("USER_ID"));
			
			if(userId.equals(userAnonymous)){
				sessionInfo = new LinkedHashMap<String, Object>();
				
				sessionInfo.put(Constants.KEY_USER_ID, StringHelper.null2void(userInfo.get("USER_ID")));
				sessionInfo.put(Constants.KEY_COMPANY_CD, StringHelper.null2void(userInfo.get("COMPANY_CD")));
				sessionInfo.put(Constants.KEY_PARENT_COMPANY_CD, StringHelper.null2void(userInfo.get("PARENT_COMPANY_CD")));
				sessionInfo.put(Constants.KEY_RUN_TYPE, StringHelper.null2void(userInfo.get("RUN_TYPE")));
				sessionInfo.put(Constants.KEY_AUTH_GROUP, StringHelper.null2void(userInfo.get("AUTH_GROUP")));
				sessionInfo.put(Constants.KEY_BUSINESS_NO, StringHelper.null2void(userInfo.get("BUSINESS_NO")));
				sessionInfo.put(Constants.KEY_SE_DIVISION_CD, StringHelper.null2void(userInfo.get("DIVISION_CD")));
				sessionInfo.put(Constants.KEY_COMPANY_NAME, StringHelper.null2void(userInfo.get("COMPANY_NAME")));
				sessionInfo.put(Constants.KEY_USER_NAME, StringHelper.null2void(userInfo.get("USER_NAME")));
				sessionInfo.put(Constants.KEY_DEFAULT_LANGUAGE, StringHelper.null2void(userInfo.get("DEFAULT_LANGUAGE")));
				sessionInfo.put(Constants.KEY_WORK_YYYY_MM, StringHelper.null2void(userInfo.get("WORK_YYYY_MM")));
				sessionInfo.put(Constants.KEY_FTA_NATION, StringHelper.null2void(userInfo.get("FTA_NATION")));
				sessionInfo.put(Constants.KEY_LOCAL_CURRENCY, StringHelper.null2void(userInfo.get("LOCAL_CURRENCY")));
				sessionInfo.put(Constants.KEY_EXCHANGE_CURRENCY, StringHelper.null2void(userInfo.get("EXCHANGE_CURRENCY")));
				sessionInfo.put(Constants.KEY_EXCHANGE_CURRENCY_TYPE, StringHelper.null2void(userInfo.get("EXCHANGE_CURRENCY_TYPE")));
				sessionInfo.put(Constants.KEY_EXCHANGE_RATE, StringHelper.null2void(userInfo.get("EXCHANGE_RATE")));
				sessionInfo.put(Constants.KEY_SESSION_VENDOR_CO_TARGET, StringHelper.null2void(userInfo.get("VENDOR_CO_TARGET")));
				sessionInfo.put(Constants.KEY_SESSION_CRTCT_GOOD_YN, StringHelper.null2void(userInfo.get("CRTCT_GOOD_YN")));
				sessionInfo.put(Constants.KEY_SESSION_CRTCT_IMP_YN, StringHelper.null2void(userInfo.get("CRTCT_IMP_YN")));
				sessionInfo.put(Constants.KEY_SESSION_CUSTOMER_CO_TARGET, StringHelper.null2void(userInfo.get("CUSTOMER_CO_TARGET")));
				sessionInfo.put(Constants.KEY_SESSION_CRTCT_SUBITEM_YN, StringHelper.null2void(userInfo.get("CRTCT_SUBITEM_YN")));
				sessionInfo.put(Constants.KEY_SESSION_CRTCT_EXPORT_YN, StringHelper.null2void(userInfo.get("CRTCT_EXPORT_YN")));
				sessionInfo.put(Constants.KEY_SESSION_EXCEL_MAX_ROWNUM, StringHelper.null2void(userInfo.get("EXCEL_MAX_ROWNUM")));
				sessionInfo.put(Constants.KEY_SESSION_CSV_MAX_ROWNUM, StringHelper.null2void(userInfo.get("CSV_MAX_ROWNUM")));
				sessionInfo.put(Constants.KEY_TOMS_FTA_CERT_KEY, StringHelper.null2void(userInfo.get("TOMS_FTA_CERT_KEY")));
				sessionInfo.put(Constants.KEY_TOMS_CLOUD_SITE, StringHelper.null2void(userInfo.get("TOMS_CLOUD_SITE")));
				sessionInfo.put(Constants.KEY_AMOUNT_POINT_NM, StringHelper.null2void(userInfo.get("AMOUNT_POINT_NM")));
				sessionInfo.put(Constants.KEY_KAKAO_SCRIPT_KEY, StringHelper.null2void(userInfo.get("KAKAO_SCRIPT_KEY")));
				sessionInfo.put(Constants.KEY_MANAGER_YN, StringHelper.null2void(userInfo.get("MANAGER_YN")));
			}
		}
		
		return sessionInfo;
	}
	
	/**
	 * 회원정보 체크하기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectUserIdenCheck(Map<String, Object> map) throws Exception {
		return userIdenDao.selectMember(map);
	}
	
	public Map<String, Object> selectSupplier(Map<String, Object> map) throws Exception {
		Map<String, Object> sessionInfo = null;
		Map<String, Object> userInfo = userIdenDao.selectSupplierIdenCheck(map);
		
		if(userInfo!=null){
			String userAnonymous = StringHelper.null2void(map.get("USER_ID"));
			String userId = StringHelper.null2void(userInfo.get("USER_ID"));
			
			if(userId.equals(userAnonymous)){
				sessionInfo = new LinkedHashMap<String, Object>();
				
				sessionInfo.put(Constants.KEY_USER_ID, StringHelper.null2void(userInfo.get("USER_ID")));
				sessionInfo.put(Constants.KEY_COMPANY_CD, StringHelper.null2void(userInfo.get("COMPANY_CD")));
				sessionInfo.put(Constants.KEY_PARENT_COMPANY_CD, StringHelper.null2void(userInfo.get("PARENT_COMPANY_CD")));
				sessionInfo.put(Constants.KEY_RUN_TYPE, StringHelper.null2void(userInfo.get("RUN_TYPE")));
				sessionInfo.put(Constants.KEY_AUTH_GROUP, StringHelper.null2void(userInfo.get("AUTH_GROUP")));
				sessionInfo.put(Constants.KEY_BUSINESS_NO, StringHelper.null2void(userInfo.get("BUSINESS_NO")));
				sessionInfo.put(Constants.KEY_COMPANY_NAME, StringHelper.null2void(userInfo.get("COMPANY_NAME")));
				sessionInfo.put(Constants.KEY_S_VENDOR_CD, StringHelper.null2void(userInfo.get("VENDOR_CD")));
				sessionInfo.put(Constants.KEY_S_VENDOR_NAME, StringHelper.null2void(userInfo.get("VENDOR_NAME")));
				sessionInfo.put(Constants.KEY_USER_NAME, StringHelper.null2void(userInfo.get("USER_NAME")));
				sessionInfo.put(Constants.KEY_DEFAULT_LANGUAGE, StringHelper.null2void(userInfo.get("DEFAULT_LANGUAGE")));
				sessionInfo.put(Constants.KEY_WORK_YYYY_MM, StringHelper.null2void(userInfo.get("WORK_YYYY_MM")));
				sessionInfo.put(Constants.KEY_FTA_NATION, StringHelper.null2void(userInfo.get("FTA_NATION")));
				sessionInfo.put(Constants.KEY_LOCAL_CURRENCY, StringHelper.null2void(userInfo.get("LOCAL_CURRENCY")));
				sessionInfo.put(Constants.KEY_EXCHANGE_CURRENCY, StringHelper.null2void(userInfo.get("EXCHANGE_CURRENCY")));
				sessionInfo.put(Constants.KEY_SESSION_VENDOR_CO_TARGET, StringHelper.null2void(userInfo.get("VENDOR_CO_TARGET")));
				sessionInfo.put(Constants.KEY_SESSION_CRTCT_GOOD_YN, StringHelper.null2void(userInfo.get("CRTCT_GOOD_YN")));
				sessionInfo.put(Constants.KEY_SESSION_CRTCT_IMP_YN, StringHelper.null2void(userInfo.get("CRTCT_IMP_YN")));
				sessionInfo.put(Constants.KEY_SESSION_CUSTOMER_CO_TARGET, StringHelper.null2void(userInfo.get("CUSTOMER_CO_TARGET")));
				sessionInfo.put(Constants.KEY_SESSION_CRTCT_SUBITEM_YN, StringHelper.null2void(userInfo.get("CRTCT_SUBITEM_YN")));
				sessionInfo.put(Constants.KEY_SESSION_CRTCT_EXPORT_YN, StringHelper.null2void(userInfo.get("CRTCT_EXPORT_YN")));
				sessionInfo.put(Constants.KEY_SESSION_EXCEL_MAX_ROWNUM, StringHelper.null2void(userInfo.get("EXCEL_MAX_ROWNUM")));
				sessionInfo.put(Constants.KEY_SESSION_CSV_MAX_ROWNUM, StringHelper.null2void(userInfo.get("CSV_MAX_ROWNUM")));
				sessionInfo.put(Constants.KEY_TOMS_FTA_CERT_KEY, StringHelper.null2void(userInfo.get("TOMS_FTA_CERT_KEY")));
				sessionInfo.put(Constants.KEY_TOMS_CLOUD_SITE, StringHelper.null2void(userInfo.get("TOMS_CLOUD_SITE")));
				sessionInfo.put(Constants.KEY_AMOUNT_POINT_NM, StringHelper.null2void(userInfo.get("AMOUNT_POINT_NM")));
				sessionInfo.put(Constants.KEY_MANAGER_YN, StringHelper.null2void(userInfo.get("MANAGER_YN")));
			}
		}
		
		return sessionInfo;
	}
	
	/**
     * 협력사 사용자 정보 체크하기
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public Map<String, Object> selectSupplierIdenCheck(Map<String, Object> map) throws Exception {
		return userIdenDao.selectSupplierIdenCheck(map);
	}
	
	/**
	 * SSO 비밀번호 없이 사용자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectSsoSupplierIdenCheck(Map<String, Object> map) throws Exception {
		return userIdenDao.selectSsoSupplierIdenCheck(map);
	}
	
	/**
	 * 비밀번호 없이 사용자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectPassMember(Map<String, Object> map) throws Exception {
		return userIdenDao.selectPassMember(map);
	}
	
	/**
	 * 비밀번호 없이 협력사 담당자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectPassSupplier(Map<String, Object> map) throws Exception {
		return userIdenDao.selectPassSupplier(map);
	}
	
	/**
	 * 사용자가 속한 회사 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectCompanyOfUser(Map<String, Object> map) throws Exception {
		return userIdenDao.selectCompanyOfUser(map);
	}
	
	/**
	 * 협력사가 속한 고객사 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectCompanyOfSupplier(Map<String, Object> map) throws Exception {
		return userIdenDao.selectCompanyOfSupplier(map);
	}
	
	/**
	 * 사용자 상세 권한 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectMenuAuthorityInfo(Map<String, Object> map) throws Exception {
		return userIdenDao.selectMenuAuthorityInfo(map);
	}
	
	/**
	 * 사용자 사용이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertUseLogInfo(Map<String, Object> map) throws Exception {
		return userIdenDao.insertUseLogInfo(map);
	}
	
	/**
	 * 사용자 사용이력 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateUseLogInfo(Map<String, Object> map) throws Exception {
		return userIdenDao.updateUseLogInfo(map);
	}
	
	/**
	 * 사용자 사용이력 통계 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateUseLogStatus(Map<String, Object> map) throws Exception {
		return userIdenDao.updateUseLogStatus(map);
	}
	
	/**
	 * 사용자 마감월 정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateWorkDate(Map<String, Object> map) throws Exception {
		return userIdenDao.updateWorkDate(map);
	}
	
	/**
	 * 문의할 사용자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectInquireUserInfo(Map<String, Object> map) throws Exception {
		return userIdenDao.selectInquireUserInfo(map);
	}
	
}
