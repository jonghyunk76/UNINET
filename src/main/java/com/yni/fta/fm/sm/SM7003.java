package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 시스템관리 > 사용자 등록
 * 
 * @author carlos
 *
 */
public interface SM7003 {

    /**
     * 사용자 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectUserList(Map map) throws Exception;
    
    /**
     * 사용자 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectUserDupCheck(Map map) throws Exception;
    
    /**
     * 사용자 비번 검증 확인
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectUserPwdCheck(Map map) throws Exception;
    
    /**
     * 사용자 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectUserInfo(Map map) throws Exception;
    
    /**
     * 사용자 인증 키 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectUserOpenApiKeyList(Map map) throws Exception;
    
    public int insertUserInfo(Map map) throws Exception;

    public int updateUserInfo(Map map) throws Exception;
    
    public int deleteUserInfo(Map map) throws Exception;
    
    /**
     * 사용자 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectUserEntrInfo(Map map) throws Exception;
    
    /**
     * 사용자 OPENAPI 인증키 MERGE
     * @param map
     * @return
     * @throws Exception
     */
    public int updateUserOpenApiKey(Map map) throws Exception;
    
    /**
     * 사용자 통관 업데이트
     * @param map
     * @return
     * @throws Exception
     */
    public int updateUserEntr(Map map) throws Exception;    
    
    /**
     * selectUserEntrBcncList
     * 사용자 통관 거래처 List (USER_ENTR_BCNC)
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectUserEntrBcncList(Map map) throws Exception;    
    
    /**
     * deleteUserEntrBcnc
     * 사용자 통관 거래처 삭제 (USER_ENTR_BCNC)
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteUserEntrBcnc(Map map) throws Exception;    
    
}
