package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 문의메일 발송
 * 
 * @author YNI-maker
 *
 */
public interface MMA031 {
	
	/**
	 * 문의 및 요청내역 목록 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectInquiryEmailRecordList(Map map) throws Exception;
	
	/**
	 * 첨부파일 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectInquiryFileList(Map map) throws Exception;
	
	/**
	 * 첨부파일 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectInquiryRealFile(Map map) throws Exception;
	
	/**
	 * 첨부파일 최대 시퀀스 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectInquiryFileMaxNumber(Map map) throws Exception;
	
	/**
	 * 상담문의 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	String insertInquiryEmailRecord(Map map) throws Exception;
	

	/**
	 * 상담문의 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int updateInquiryEmailRecord(Map map) throws Exception;
	
	/**
	 * 상담문의와 관련 파일 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteInquiryEmailRecord(Map map) throws Exception;
	
	/**
	 * 상담문의 파일 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int insertInquiryFileRecord(Map map) throws Exception;
	
	/**
	 * 상담문의 첨부파일 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int deleteInquiryFileRecord(Map map) throws Exception;
	
	/**
	 * 시스템 공지사항 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectSystemNoticeList(Map map) throws Exception;
	
	/**
	 * 알림 카운트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectNoticeCount(Map map) throws Exception;
	
}
