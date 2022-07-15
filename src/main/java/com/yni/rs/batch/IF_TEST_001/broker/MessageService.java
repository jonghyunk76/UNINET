package com.yni.rs.batch.IF_TEST_001.broker;

import java.util.Map;

/**
 * 서비스 인터페이스
 * 
 * @author ador2
 *
 */
public interface MessageService {
	
	/**
     * 서버간 연계 테스트 요청
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	Map executeRelayBatch(Map map) throws Exception; 
	
}
