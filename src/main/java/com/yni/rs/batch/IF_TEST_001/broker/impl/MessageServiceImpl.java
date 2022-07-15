package com.yni.rs.batch.IF_TEST_001.broker.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.common.batch.BatchServiceCaller;
import com.yni.fta.mm.batch.BatchService;
import com.yni.rs.batch.IF_TEST_001.broker.MessageService;

import kr.yni.frame.service.YniAbstractService;

/**
 * 서비스가 수행할 로직을 구현하는 클래스
 * 
 * @author ador2
 *
 */
@Service("if_test_0010")
public class MessageServiceImpl extends YniAbstractService implements MessageService {
	
	@Resource(name="if_test_001_dao")
	private MessageServiceDao dao;
	
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
