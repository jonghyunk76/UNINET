package com.yni.fta.mm.iden;

import java.util.List;
import java.util.Map;

/**
 * 사용자 인증을 위한 기능 선언
 * 
 * @author YNI-maker
 *
 */
public interface UserIden {
	
	/**
	 * 회원정보 등록상태 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectUserStatus(Map<String, Object> map) throws Exception; 
	
	/**
	 * 회원정보 조회 및 세션에 등록할 정보 생성
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectMember(Map<String, Object> map) throws Exception; 
	
	/**
	 * 회원정보 체크하기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectUserIdenCheck(Map<String, Object> map) throws Exception;
	
	/**
	 * 협력사 정보 조회 및 세션에 등록할 정보 생성
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectSupplier(Map<String, Object> map) throws Exception; 
	
	/**
	 * 협력사 사용자 정보 체크하기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectSupplierIdenCheck(Map<String, Object> map) throws Exception;
	
	/**
	 * 비밀번호 없이 사용자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectPassMember(Map<String, Object> map) throws Exception;
	
	/**
	 * SSO 비밀번호 없이 협력사 담당자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectSsoSupplierIdenCheck(Map<String, Object> map) throws Exception;
	
	/**
	 * 비밀번호 없이 협력사 담당자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectPassSupplier(Map<String, Object> map) throws Exception;
	
	/**
	 * 사용자가 속한 회사 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectCompanyOfUser(Map<String, Object> map) throws Exception;
	
	/**
	 * 협력사가 속한 고객사 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectCompanyOfSupplier(Map<String, Object> map) throws Exception;
	
	/**
	 * 사용자 상세 권한 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectMenuAuthorityInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 사용자 사용이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertUseLogInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 사용자 사용이력 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateUseLogInfo(Map<String, Object> map) throws Exception;
	
	/**
	 * 사용자 사용이력 통계 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateUseLogStatus(Map<String, Object> map) throws Exception;
	
	/**
	 * 사용자 마감월 정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateWorkDate(Map<String, Object> map) throws Exception;
	
	/**
	 * 문의할 사용자 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> selectInquireUserInfo(Map<String, Object> map) throws Exception;
	
}
