package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 증명서 변경요청
 * 
 * @author YNI-maker
 *
 */
public interface MMA021 {

	/**
	 * 증명서 변경요청 리스트 조회
	 * 
	 * @param map Map
	 * @return List
	 * @throws Exception
	 */
	List selectMainList(Map map) throws Exception;
	
	
	/**
	 * 증명서 변경요청 사유 저장
	 * 
	 * @param map Map
	 * @return int
	 * @throws Exception
	 */
	int updateReqReason(Map map) throws Exception;
	
	/**
	 * 증명서 변경 원산지 상세내역
	 * 
	 * @param map Map
	 * @return List
	 * @throws Exception
	 */
	List selectRecieveCoDetail(Map map) throws Exception;
	
	/**
     * 증빙파일 목록 조회
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
	List selectEvdcFileList(Map map) throws Exception;
	
	/**
     * 증빙파일 다운로드
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
	List selectEvdcDownloadFile(Map map) throws Exception;
	
}
