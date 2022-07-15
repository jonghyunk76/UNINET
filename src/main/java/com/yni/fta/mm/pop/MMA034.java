package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 고객사 품목 조회
 * 
 * @author jonghyunkim
 *
 */
public interface MMA034 {

	/**
     * 데이터 그리드 등록 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    List selectDatagridMstList(Map map) throws Exception;

    /**
     * 데이터 그리드 정보 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int deleteDatagridMstInfo(Map map) throws Exception;
    
    /**
     * 데이터 그리드 정보 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int insertDatagridMstInfo(Map map) throws Exception;
    
}
