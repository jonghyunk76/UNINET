package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * Salseforce 인터페이스 클래스
 * 
 * @author YNI-Framework
 *
 */
public interface SM8001 {
    
    /**
     * Access Token 구하기
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	String selectAccessToken(Map map) throws Exception;
	
	/**
     * Salesforce 데이터 요청
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	Map executeInterface(Map map) throws Exception; 
	
	/**
     * 서버간 연계 테스트 요청
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	Map executeRelayBatch(Map map) throws Exception; 
	
}
