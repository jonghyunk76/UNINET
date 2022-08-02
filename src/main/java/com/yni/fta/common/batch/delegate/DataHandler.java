package com.yni.fta.common.batch.delegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.batch.delegate.impl.FtpTransfer;
import com.yni.fta.common.batch.delegate.impl.HttpTransfer;
import com.yni.fta.common.batch.delegate.impl.JcoTransfer;
import com.yni.fta.common.batch.delegate.impl.SiebelTransfer;
import com.yni.fta.common.batch.delegate.impl.SmtpTransfer;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.beans.target.InterfaceTarget;
import com.yni.fta.common.tools.DataConvertUtil;

import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

public class DataHandler {
	
	private static Log log = LogFactory.getLog(DataHandler.class);
	
	private Transfer trans;
	
	private BatchVo batchVo;
	
	/**
	 *
	 * @param batchVo
	 */
	public DataHandler(BatchVo batchVo) {
		Map map = batchVo.getMap();
		String procType = StringHelper.null2void(map.get("PROCESS_TYPE")); // 처리방식 : Siebel(I), SOAP(S), HTTP(H), SMTP(T), FTP(F), JCO(J), Bypass(B), Procedure(P)
		
		log.debug("Transfer protocal type = " + procType);
    	
		// 데이터 처리방식에 따라 해당 클래스 인스턴스 생성
		if(procType.equals("I")) { // Siebel
    		trans = new SiebelTransfer();
    	} else if(procType.equals("S")) { // SOAP
//    		trans = new JcoTransfer();
    	} else if(procType.equals("H")) { // HTTP
    		trans = new HttpTransfer();
    	} else if(procType.equals("T")) { // SMTP
    		trans = new SmtpTransfer();
    	} else if(procType.equals("F")) { // FTP
    		trans = new FtpTransfer();
    	} else if(procType.equals("J")) { // JCO
    		trans = new JcoTransfer();
    	} else if(procType.equals("P")) { // Procedure
//    		trans = new JcoTransfer();
    	}
    	
    	this.batchVo = batchVo;
	}
	
	/**
	 * 데이터 수신
	 * 
	 * @param map = 인터페이스 정보
	 * @param datas = 요청할 데이터 정보
	 * @return
	 * @throws Exception
	 */
	public boolean receive(Map map, Object datas) throws Exception {
		String impFormat = StringHelper.null2void(map.get("IMP_DATA_FORMAT")); // Import 데이터 형식 : JSON, FILE, XML, OBJECT
		String resFormat = StringHelper.null2void(map.get("RES_DATA_FORMAT")); // Reponse 데이터 형식 : JSON, FILE, XML, OBJECT
		
		log.debug("Import data type = " + impFormat + ", Reponse type = " + resFormat);
		
		// 요청 시 배치정보에 지정된 리턴되는 데이터 형식에 맞게 변환
		Object iobj = DataConvertUtil.getConvertData(datas, impFormat);
		
    	if(trans == null) {
    		batchVo.setBatchStatus("E");
    		batchVo.setErrorMessage("수신할 클래스 객체가 선언되지 않았습니다.");
    		return false;
    	} else if(trans == null) {
    		batchVo.setBatchStatus("E");
    		batchVo.setErrorMessage("수신에 필요한 배치정보를 찾을 수 없습니다.");
    		return false;
    	} else {
	    	Object robj = trans.receive(batchVo, map, iobj);
	    	
	    	// 응답결과를 JSON 또는 XML로 변환한다.
	    	batchVo.setReturnData(DataConvertUtil.getConvertData(robj, resFormat));
    	}
    	
    	return true;
	}
	
	/**
	 * 데이터 송신
	 * 
	 * @param map = 인터페이스 정보
	 * @param datas = 전송할 데이터 정보
	 * @return
	 * @throws Exception
	 */
	public boolean send(Map map, Object datas) throws Exception {
		String impFormat = StringHelper.null2void(map.get("IMP_DATA_FORMAT")); // Import 데이터 형식 : JSON, FILE, XML, OBJECT
		String resFormat = StringHelper.null2void(map.get("RES_DATA_FORMAT")); // Reponse 데이터 형식 : JSON, FILE, XML, OBJECT
		
		log.debug("Import data type = " + impFormat + ", Reponse type = " + resFormat);
		
		// 배치정보에 지정된 리턴되는 데이터 형식에 맞게 변환
		Object iobj = DataConvertUtil.getConvertData(datas, impFormat);
		
		if(trans == null) {
    		batchVo.setBatchStatus("E");
    		batchVo.setErrorMessage("전송할 클래스 객체가 선언되지 않았습니다.");
    		return false;
    	} else if(trans == null) {
    		batchVo.setBatchStatus("E");
    		batchVo.setErrorMessage("전송에 필요한 배치정보를 찾을 수 없습니다.");
    		return false;
    	} else {
    		Object robj = trans.send(batchVo, map, iobj);
	    	
	    	// 응답된 데이터 타입에 따라 데이터 형식을 변환시킨다.
	    	batchVo.setReturnData(DataConvertUtil.getConvertData(robj, resFormat));
    	}
		
    	return true;
	}
	
}
