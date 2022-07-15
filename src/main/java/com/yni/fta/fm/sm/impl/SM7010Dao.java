package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 시스템 관리 > 사용자 권한관리
 * 
 * @author YNI-maker
 *
 */
@Repository("sm7010Dao")
public class SM7010Dao extends YniAbstractDAO {

	/**
	 * 권한그룹 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectAuthGroupList(Map map) throws Exception {
		return this.list("SM7010.selectAuthGroupList", map);
	}

	/**
	 * 권한그룹 상세내역 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectAuthGroupInfo(Map map) throws Exception {
		return (Map) this.selectByPk("SM7010.selectAuthGroupInfo", map);
	}

	/**
	 * 권한그룹 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertAuthGroupInfo(Map map) throws Exception {
		return this.update("SM7010.insertAuthGroupInfo", map);
	}

	/**
	 * 권한그룹 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateAuthGroupInfo(Map map) throws Exception {
		return this.update("SM7010.updateAuthGroupInfo", map);
	}
	
	/**
	 * 권한그룹 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthGroupInfo(Map map) throws Exception {
		return this.delete("SM7010.deleteAuthGroupInfo", map);
	}
	
	/**
	 * 권한그룹 사용자 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectAuthUserList(Map map) throws Exception {
		return this.list("SM7010.selectAuthUserList", map);
	}

	/**
	 * 권한그룹 사용자 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertAuthUserInfo(Map map) throws Exception {
		return this.update("SM7010.insertAuthUserInfo", map);
	}

	/**
	 * 권한그룹 사용자 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthUserInfo(Map map) throws Exception {
		return this.delete("SM7010.deleteAuthUserInfo", map);
	}
	
	/**
	 * 권한그룹 미등록 메뉴 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMenuList(Map map) throws Exception {
		return this.list("SM7010.selectMenuList", map);
	}
	
	/**
	 * 권한그룹 메뉴 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectAuthMenuList(Map map) throws Exception {
		return this.list("SM7010.selectAuthMenuList", map);
	}
	
	/**
	 * 권한그룹 메뉴 신규등록
	 * 
	 * @param list
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertAuthMenuInfo(List list, Map map) throws Exception {
		return this.executeBatch("SM7010.insertAuthMenuInfo", list, map);
	}

	/**
	 * 권한그룹 메뉴 삭제
	 * 
	 * @param list
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteAuthMenuInfo(List list, Map map) throws Exception {
		return this.executeBatch("SM7010.deleteAuthMenuInfo", list, map);
	}
	

    /**
     * 사용자 권한 중복체크
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectAuthUserDupCheck(Map map) throws Exception {
        return (Map) this.selectByPk("SM7010.selectAuthUserDupCheck", map);
    }
    
    
    /**
     * 그룹 권한 중복체크
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectAuthGroupDupCheck(Map map) throws Exception {
        return (Map) this.selectByPk("SM7010.selectAuthGroupDupCheck", map);
    }
    
	/**
	 * 부여된 메뉴 목록 수정
	 * 
	 * @param list
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateAuthMenuInfo(List list, Map map) throws Exception {
		return this.executeBatch("SM7010.updateAuthMenuInfo", list, map);
	}    
}
