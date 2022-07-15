package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yni.fta.common.tools.WebsocketSupporter;
import com.yni.fta.common.tools.WebsocketSupporter.WebSocketController;
import com.yni.fta.fm.sm.SM7529;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 환경설정 > Set up > Web Socket관리 Interface implement Class
 * 
 * @author YNI-maker
 *
 */
@Service("sm7529")
public class SM7529Impl extends YniAbstractService implements SM7529 {
	
	/**
	 * Web Socket 세션 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectWebSocketList(Map map) throws Exception {
		String key = StringHelper.null2void(map.get("SESSION_KEY"));
		
		return WebSocketController.getClients(key);
	}
	
	/**
	 * Web Socket 세션 강제종료 시키기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int removeUserWebSocket(Map map) throws Exception {
		int cnt = 0;
		List gridData = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
		
		for(int i = 0; i < gridData.size(); i++) {
			Map gmap = (Map) gridData.get(i);
			
			cnt += WebSocketController.close(StringHelper.null2void(gmap.get("SESSION_KEY")));
		}
		
		return cnt;
	}
	
	/**
	 * Web Socket 초기화
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int removeAllWebSocket(Map map) throws Exception {
		return WebSocketController.clearAll();
	}
	
}
