package com.yni.fta.mm.iden.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;


import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.StringHelper;

/**
 * 사용자 인증을 위한 데이터 관리
 * 
 * @author YNI-maker
 *
 */
@Repository("userIdenDao")
public class UserIdenDao extends YniAbstractDAO {
	
	/**
	 * 회원정보 등록 상태
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectUserStatus(Map<String, Object> map) {
		return (Map)selectByPk("AuthorityCheck.selectUserStatus", map);
	}
	
	/**
	 * 회원정보 조회
	  
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectMember(Map<String, Object> map) {
		return (Map)selectByPk("AuthorityCheck.selectMember", map);
	}
	
	/**
	 * SSO 비밀번호 없이 사용자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectSsoSupplierIdenCheck(Map<String, Object> map) {
		return (Map)selectByPk("AuthorityCheck.selectSsoSupplierIdenCheck", map);
	}
	
	/**
	 * 비밀번호 없이 사용자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectPassMember(Map<String, Object> map) {
		return (Map)selectByPk("AuthorityCheck.selectPassMember", map);
	}
	
	/**
     * 협력사 사용자 정보 체크하기
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public Map<String, Object> selectSupplierIdenCheck(Map<String, Object> map) {
		return (Map)selectByPk("AuthorityCheck.selectSupplierIdenCheck", map);
	}
	
	/**
	 * 비밀번호 없이 협력사 담당자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectPassSupplier(Map<String, Object> map) {
		return (Map)selectByPk("AuthorityCheck.selectPassSupplier", map);
	}
	
	/**
     * 사용자가 속한 회사 목록 조회
     * 
     * @param map
     * @return 
     * @exception Exception
     */
	public List selectCompanyOfUser(Map<String, Object> map) {
		return this.list("AuthorityCheck.selectCompanyOfUser", map);
	}
	
	/**
     * 협력사가 속한 고객사 목록 조회
     * 
     * @param map
     * @return 
     * @exception Exception
     */
	public List selectCompanyOfSupplier(Map<String, Object> map) {
		return this.list("AuthorityCheck.selectCompanyOfSupplier", map);
	}
	
	/**
	 * 사용자 상세 권한 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectMenuAuthorityInfo(Map<String, Object> map) {
		return (Map)selectByPk("AuthorityCheck.selectMenuAuthorityInfo", map);
	}
	
	/**
	 * 사용자 사용이력 등록
	 * 
	 * @param map
	 * @return
	 */
	public int insertUseLogInfo(Map<String, Object> map) throws Exception {
		if(StringHelper.null2void(map.get("MENU_ID")).isEmpty()) {
			return 0;
		} else {
			return this.update("AuthorityCheck.insertUseLogInfo", map);
		}
	}
	
	/**
	 * 사용자 사용이력 업데이트
	 * 
	 * @param map
	 * @return
	 */
	public int updateUseLogInfo(Map<String, Object> map) throws Exception {
		return this.update("AuthorityCheck.updateUseLogInfo", map);
	}
	
	/**
	 * 사용자 사용이력 통계 업데이트
	 * 
	 * @param map
	 * @return
	 */
	public int updateUseLogStatus(Map<String, Object> map) throws Exception {
		return this.update("AuthorityCheck.updateUseLogStatus", map);
	}
	
	/**
	 * 사용자 마감월 정보 수정
	 * 
	 * @param map
	 * @return
	 */
	public int updateWorkDate(Map<String, Object> map) throws Exception {
		return this.update("AuthorityCheck.updateWorkDate", map);
	}
	
	/**
	 * 문의할 사용자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectInquireUserInfo(Map<String, Object> map) {
		String site = StringHelper.null2void(map.get("SESSION_CERTIFY_TYPE"));
		Map rst = null;
		
		if(site.equals("external")) {
			rst = (Map)selectByPk("AuthorityCheck.selectInquireSupplierInfo", map);
		}
		
		if(site.equals("internal")) {
			rst = (Map)selectByPk("AuthorityCheck.selectInquireUserInfo", map);
		}
		
		return rst;
	}
	
}
