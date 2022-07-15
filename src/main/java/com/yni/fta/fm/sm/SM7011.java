package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

/**
 * 시스템 관리 > 공지사항
 * 
 * @author sbj1000
 *
 */
public interface SM7011 {
	
	/**
	 * 공지사항 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectNoticeList(Map map) throws Exception;
	
	/**
	 * 공지사항 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectNoticeInfo(Map map) throws Exception;
	
	/**
	 * 공지사항 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateNoticeInfo(Map map) throws Exception;
	
	/**
	 * 공지사항 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Object insertNoticeInfo(Map map) throws Exception;
	
	/**
	 * 공지사항 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteNoticeInfo(Map map) throws Exception;
	
	/**
	 * 첨부파일 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectNoticeFile(Map map) throws Exception;
	
	/**
	 * 최근 게시 번호 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectLastNoticeNo(Map map) throws Exception;
	
}
