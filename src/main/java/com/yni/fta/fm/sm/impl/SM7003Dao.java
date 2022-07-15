package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 시스템관리 > 사용자 등록
 * 
 * @author carlos
 *
 */
@Repository("sm7003Dao")
public class SM7003Dao extends YniAbstractDAO {

    /**
     * 사용자 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectUserList(Map map) throws Exception {
        return this.listWithRowPaging("SM7003.selectUserList", map);
    }
    
    /**
     * 사용자 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectUserDupCheck(Map map) throws Exception {
        return (Map) this.selectByPk("SM7003.selectUserDupCheck", map);
    }
    
    /**
     * 사용자 비번 검증 확인
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectUserPwdCheck(Map map) throws Exception {
        return (Map) this.selectByPk("SM7003.selectUserPwdCheck", map);
    }
    
    /**
     * 사용자 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectUserInfo(Map map) throws Exception {
        return (Map) this.selectByPk("SM7003.selectUserInfo", map);
    }
    
    /**
     * 사용자 인증 키 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectUserOpenApiKeyList(Map map) throws Exception {
        return this.listWithRowPaging("SM7003.selectUserOpenApiKeyList", map);
    }    
    
    /**
     * 사용자 신규등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertUserInfo(Map map) throws Exception {
        return this.update("SM7003.insertUserInfo", map);
    }
    
    /**
     * 사용자 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateUserInfo(Map map) throws Exception {
        log.debug("updateUserInfo = " +  map.toString());
        return this.update("SM7003.updateUserInfo", map);
    }
    
    /**
     * 사용자 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteUserInfo(Map map) throws Exception {
        log.debug("deleteUserInfo = " +  map.toString());
        return this.delete("SM7003.deleteUserInfoReal", map);
        //return this.update("SM7003.deleteUserInfo", map);
    }
    
    /**
     * 사용자 통관 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectUserEntrInfo(Map map) throws Exception {
    	return (Map) this.selectByPk("SM7003.selectUserEntrInfo", map);
    }
    
    /**
     * 사용자 OPENAPI 인증키 MERGE
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateUserOpenApiKey(List list) throws Exception {
        return this.executeBatch("SM7003.updateUserOpenApiKey", list);
    }
    
    /**
     * 사용자 통관 업데이트
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateUserEntr(Map map) throws Exception {
    	return this.update("SM7003.updateUserEntr", map); 
    }
    
    /**
     * 사용자 인증 키 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectUserEntrBcncList(Map map) throws Exception {
        return this.listWithRowPaging("SM7003.selectUserEntrBcncList", map);
    } 
    
    /**
     * updateUserEntrBcnc
     * 사용자 통관 거래처 MERGE(USER_ENTR_BCNC)
     * @param map
     * @return
     * @throws Exception
     */
    public int updateUserEntrBcnc(List list) throws Exception {
        return this.executeBatch("SM7003.updateUserEntrBcnc", list);
    }    
    
    /**
     * deleteUserEntrBcnc
     * 사용자 통관 거래처 삭제 (USER_ENTR_BCNC)
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteUserEntrBcnc(Map map) throws Exception {
        return this.delete("SM7003.deleteUserEntrBcnc", map);
    }
}
