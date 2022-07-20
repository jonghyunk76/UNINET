package com.yni.rs.batch.realtime.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.common.batch.BatchServiceCaller;
import com.yni.fta.mm.batch.BatchService;
import com.yni.rs.batch.realtime.MyplmService;

import kr.yni.frame.service.YniAbstractService;

/**
 * MY PLM 연계 로직을 구현하는 클래스
 * 
 * @author ador2
 *
 */
@Service("myplmservice")
public class MyplmServiceImpl extends YniAbstractService implements MyplmService {
	
	@Resource(name="myplmdbo")
	private MyplmServiceDao dao;
	
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
