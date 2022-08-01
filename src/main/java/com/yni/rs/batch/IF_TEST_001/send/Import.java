package com.yni.rs.batch.IF_TEST_001.send;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.delegate.DataHandler;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.parameter.YniAbstractBatch;
import com.yni.rs.batch.ImportPackage;

import kr.yni.frame.util.StringHelper;

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
		log.debug("Send Import : " + map);
		
		Map impParam = new HashMap();
		
		// 파라메터에 등록할 데이터를 생성하는 프로그램 구현
		
		return impParam; 
	}

	@Override
	public void executeBatch(Object batchVo) throws Exception {
		BatchVo bvo = (BatchVo) batchVo;
		Map map = bvo.getMap();
		
		String process_type = StringHelper.null2void(map.get("PROCESS_TYPE")); // 처리방식 : Siebel(I), SOAP(S), HTTP(H), SMTP(T), FTP(F), JCO(J), Bypass(B), Procedure(P)
		
		log.debug("Import type= " + process_type + ", data : " + bvo.getImportData());
		
		// 프로토콜에 따라 데이터를 전송
		DataHandler dh = new DataHandler(bvo, process_type);
		boolean rst = dh.send(map, bvo.getImportData());
		
		if(rst) {
			bvo.setBatchStatus("S");
		} else {
			bvo.setBatchStatus("E");
		}
	}
	
}
