package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 게시판 > 자료실
 * 
 * @author YNI-maker
 *
 */
public interface SM7012 {

	/**
	 * 자료실 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectBoardList(Map map) throws Exception;
	
	/**
	 * 자료실 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectBoardDetail(Map map) throws Exception;
	
	/**
	 * 자료실 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Object insertBoardInfo(Map map) throws Exception;
	
	/**
	 * 자료실 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateBoardInfo(Map map) throws Exception;
	
	/**
	 * 자료실 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteBoardInfo(Map map) throws Exception;
	
	/**
	 * 파일목록 조회
	 * 
	 * @param map
	 *            검색조건
	 * @return 메세지 정보
	 * @throws Exception
	 */
	List selectBoardFileList(Map map) throws Exception;
	
	/**
	 * 파일 조회
	 * 
	 * @param map
	 *            검색조건
	 * @return 메세지 정보
	 * @throws Exception
	 */
	Map selectBoardFile(Map map) throws Exception;
	
	/**
	 * 파일 삭제
	 * 
	 * @param map
	 *            검색조건
	 * @return 메세지 정보
	 * @throws Exception
	 */
	int deleteBoardFile(Map map) throws Exception;
}

