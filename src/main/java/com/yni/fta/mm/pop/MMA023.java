package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 멕시코 확인서 form 조회 및 업로드 실행
 * 
 * @author carlos
 */
public interface MMA023 {
    
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
    
}
