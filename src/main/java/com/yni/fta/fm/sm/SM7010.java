package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 시스템 관리 > 사용자 권한관리
 * 
 * @author YNI-maker
 *
 */
public interface SM7010 {

	/**
	 * 권한그룹 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectAuthGroupList(Map map) throws Exception;

	/**
	 * 권한그룹 상세내역 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectAuthGroupInfo(Map map) throws Exception;

	/**
	 * 권한그룹 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertAuthGroupInfo(Map map) throws Exception;

	/**
	 * 권한그룹 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateAuthGroupInfo(Map map) throws Exception;
	
	/**
	 * 권한그룹 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteAuthGroupInfo(Map map) throws Exception;
	
	/**
	 * 권한그룹 사용자 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectAuthUserList(Map map) throws Exception;

	/**
	 * 권한그룹 사용자 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertAuthUserInfo(Map map) throws Exception;

	/**
	 * 권한그룹 사용자 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteAuthUserInfo(Map map) throws Exception;
	
	/**
	 * 권한그룹 미등록 메뉴 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMenuList(Map map) throws Exception;
	
	/**
	 * 권한그룹 메뉴 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectAuthMenuList(Map map) throws Exception;
	
	/**
	 * 권한그룹 메뉴 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertAuthMenuInfo(Map map) throws Exception;

	/**
	 * 권한그룹 메뉴 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteAuthMenuInfo(Map map) throws Exception;
	
	/**
	 * 부여된 메뉴 목록 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateAuthMenuInfo(Map map) throws Exception;	
	
}
