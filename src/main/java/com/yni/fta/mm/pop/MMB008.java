package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 *공통 > 표준통관예정 자료 업로드 인터페이스
 * 
 * @author YNI-maker
 *
 */
public interface MMB008 {

	/**
	 * 표준통관 업로드 데이터 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectStandardEntrUploList(Map map) throws Exception;
	
	/**
	 * 표준통관 업로드 데이터 일괄 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateStandardEntrUplo(Map map) throws Exception;
	
	/**
	 * 표준통관 업로드 데이터 일괄 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteStandardEntrUplo(Map map) throws Exception;
	
	/**
     * Excel Upload & Save
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    int saveExcelSample(Map map) throws Exception;
    
    /**
     * 표통과 수입업로드 자료 매칭 수행
     * 
     * @param map
     * @throws Exception
     */
    int updateStandardEntrUploadNo(Map map) throws Exception;
}
