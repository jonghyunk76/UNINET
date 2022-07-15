package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 멕시코 확인서 원산지 등록
 * 
 * @author YNI-maker
 */
public interface MMA020 {

    /**
     * 멕시코 증명서 품목 HS Code 조회
     * 
     * @param map Map
     * @return String
     * @exception Exception
     */
    String selectHsCode(Map map) throws Exception;
    
    /**
     * 멕시코 증명서 품목 Naladisa Code 조회
     * 
     * @param map Map
     * @return String
     * @exception Exception
     */
    String selectNaladisaCd(Map map) throws Exception;
    
    /**
     * 멕시코 증명서 품목 결정기준 조회
     * 
     * @param map Map
     * @return List
     * @exception Exception
     */
    List selectRuleContents(Map map) throws Exception;
    
}
