package com.yni.rs.batch.DHL_ROUTE_1.send;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.parameter.YniAbstractBatch;
import com.yni.rs.batch.ImportPackage;

/**
 * 전송할 파라메터 정보 생성 클래스
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
		log.debug("User Import : " + map);
		
		// 파라메터에 등록할 데이터를 생성하는 프로그램 구현
		Map rst = (Map) map.get("SiebelMessage");
		
		return rst; 
	}

	@Override
	public void executeBatch(Object batchVo) throws Exception {
		BatchVo bvo = (BatchVo) batchVo;
		
		log.debug("Import data : " + bvo.getImportData());
		
	}
	
}
