package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 한국 확인서 form 조회 및 업로드 실행
 * 
 * @author carlos
 */
public interface MMA022 {
    
	/**
     * Excel Upload 결과 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    List selectExcelSampleList(Map map) throws Exception;
    
    
    /**
     * Excel Upload & Save
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    int saveExcelSample(Map map) throws Exception;
    
    /**
     * 데이터 유효성 체크
     * 
     * @param map
     * @return
     * @throws Exception
     */
    Map executeItemErrorCheck(Map map) throws Exception;
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    List selectVientnamExcelSampleList(Map map) throws Exception;
    
    /**
     * Excel Upload & Save
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    int saveVientnamExcelSample(Map map) throws Exception;
    
    /**
     * 베트남 데이터 유효성 체크
     * 
     * @param map
     * @return
     * @throws Exception
     */
    Map executeVientnamItemErrorCheck(Map map) throws Exception;
    
    /**
     * 베트남 원산지 증명서 저장
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    int insertVientnamExcelList(Map map) throws Exception;
    
    /**
     * 베트남 원산지 증명서 업로드내역 전체 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int deleteVientnamExcelSample(Map map) throws Exception;
    
    /**
     * 베트남 원산지 증명서 업로드내역 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int deleteVientnamExcelSampleRow(Map map) throws Exception;
    
}
