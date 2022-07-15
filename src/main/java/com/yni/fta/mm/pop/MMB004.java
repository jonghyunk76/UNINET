package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 *공통 > 협정적용 신청서 인터페이스
 * 
 * @author YNI-maker
 *
 */
public interface MMB004 {

	/**
	 * 협정적용 신청서 정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectImportCvtaApcPdoList(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateImportCvtaApcPdoInfo(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 란 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectImportCvtaDclrtLneList(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 란정보 일괄 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateBatchImportCvtaDclrtLne(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 란 상세정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectImportCvtaApcPdoLneList(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 란정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateImportCvtaApcPdoLneInfo(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 란정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteImportCvtaApcPdoLneInfo(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 원산지 규격 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectImpCvtaApcPdoStardList(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 원산지 규격 사용량 합계 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectImpCvtaApcPdoStardTotInfo(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 원산지 규격 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateImpCvtaApcPdoStardInfo(Map map) throws Exception;
	
	/**
	 * 협정적용 신청서 원산지 규격 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteImpCvtaApcPdoStardInfo(Map map) throws Exception;
	
}
