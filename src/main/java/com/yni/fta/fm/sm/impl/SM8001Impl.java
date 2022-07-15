package com.yni.fta.fm.sm.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.common.batch.BatchServiceCaller;
import com.yni.fta.common.ws.RestServiceClient;
import com.yni.fta.fm.sm.SM8001;
import com.yni.fta.mm.batch.BatchService;

import kr.yni.frame.service.YniAbstractService;

/**
 * Salseforce 인터페이스 클래스
 * 
 * @author YNI-Framework
 *
 */
@Service("SM8001") 
public class SM8001Impl extends YniAbstractService implements SM8001 {

	/**
	 * Access Token 구하기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String selectAccessToken(Map map) throws Exception {
		RestServiceClient service = new RestServiceClient();
		
		return service.getToken(map);
    }
	
	/**
	 * Salesforce 데이터 요청
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeInterface(Map map) throws Exception {
		RestServiceClient service = new RestServiceClient();
		
		return service.executeInterface(map);
    }
	
	@Resource(name="batchService")
	private BatchService batch;
	
	/**
	 * 서버간 연계 테스트 요청
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map executeRelayBatch(Map map) throws Exception {
		BatchServiceCaller caller = new BatchServiceCaller();
		
		return caller.execute(map, batch);
    }
	
}
