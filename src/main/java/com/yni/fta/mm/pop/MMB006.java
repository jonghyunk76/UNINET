package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 *공통 > 인보이스/인보이스 항목 업로드 인터페이스
 * 
 * @author YNI-maker
 *
 */
public interface MMB006 {
	
	/**
	 * 최근 설정된 값 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectMaxInvUpSetupNo(Map map) throws Exception;
	
	/**
	 * 인보이스 업로드 설정정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectInvUpSetupInfo(Map map) throws Exception;
	
	/**
	 * 인보이스 업로드 설정 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectInvUpSetupList(Map map) throws Exception;
	
	/**
	 * 인보이스 업로드 설정정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String insertInvUpSetupInfo(Map map) throws Exception;
	
	/**
	 * 인보이스 업로드 설정정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateInvUpSetupInfo(Map map) throws Exception;
	
	/**
	 * 인보이스 업로드 설정정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteInvUpSetupInfo(Map map) throws Exception;
	
	/**
	 * 인보이스 항목 기본정보  조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectBaseInvUpList(Map map) throws Exception;
	
	/**
	 * 인보이스 항목 업로드 설정정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectInvUpMasterInfo(Map map) throws Exception;
	
	/**
	 * 인보이스 항목 업로드 설정정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertInvUpMasterInfo(Map map) throws Exception;
	
	/**
	 * 인보이스 항목 업로드 설정정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateInvUpMasterInfo(Map map) throws Exception;
	
	/**
	 * 인보이스 항목 업로드 설정정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteInvUpMasterInfo(Map map) throws Exception;
	
	/**
	 * 인보이스/패킹리스트 업로드 이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectUploadHistList(Map map) throws Exception;
	
	/**
	 * 인보이스 마스터 정보 업로드 이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertInvUploadHist(Map map) throws Exception;
	
	/**
	 * 인보이스 마스터 정보 업로드 이력 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteInvUploadHist(Map map) throws Exception;
	
	/**
	 * 패킹리스트 업로드 이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertPlUploadHist(Map map) throws Exception;
	
	/**
	 * 패킹리스트 업로드 이력 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updatePlUploadHist(Map map) throws Exception;
	
	/**
	 * 패킹리스트 업로드 이력 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deletePlUploadHist(Map map) throws Exception;
	
	/**
	 * 패킹리스트 업로드 이력 일괄 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteBatchUploadHist(Map map) throws Exception;
	
	/**
	 * excel 업로드한 파일내역 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List executeExcelUploadFile(Map map) throws Exception;
	
}
