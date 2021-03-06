package com.yni.rs.batch.IF_TEST_001.receive;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.parameter.YniAbstractBatch;
import com.yni.rs.batch.ImportPackage;

/**
 * 수신할 파라메터 정보 생성 클래스
 * 
 * @author ador2
 *
 */
public class Import extends YniAbstractBatch implements ImportPackage {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 요청 파라메터 값 생성
	 * - JCO XML에 지정된 import 파라메터와 맵핑되는 정보를 생성해야 함
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Object getParameter(Map map) throws Exception {
		log.debug("Receive Import : " + map);
		
		Map rst = new HashMap();
		
		// 파라메터에 등록할 데이터를 생성하는 프로그램 구현
		
		return rst; 
	}

	@Override
	public void executeBatch(Object vo) throws Exception {
		log.debug("Send Batch : " + vo);
	}
	
}
