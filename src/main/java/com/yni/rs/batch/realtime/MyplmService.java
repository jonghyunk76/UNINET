package com.yni.rs.batch.realtime;

import java.util.Map;

/**
 * MY PLM 연계 인터페이스
 * 
 * @author ador2
 *
 */
public interface MyplmService {
	
	/**
     * 서버간 연계 테스트 요청
     * 
     * @param map
     * @return
     * @throws Exception
     */    
	Map executeRelayBatch(Map map) throws Exception; 
	
}
