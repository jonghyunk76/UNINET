package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 시스템관리 > 사업부등록
 * 
 * @author carlos
 *
 */
public interface SM7002 {

    /**
     * 사업부 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectDivisionList(Map map) throws Exception;
    
    /**
     * 사업부 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectDivisionDupCheck(Map map) throws Exception;
    
    /**
     * 사업부 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectDivisionInfo(Map map) throws Exception;
    
    public int insertDivisionInfo(Map map) throws Exception;

    public int updateDivisionInfo(Map map) throws Exception;
    
    public int deleteDivisionInfo(Map map) throws Exception;
    
}
