package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 문의메일 발송
 * 
 * @author YNI-maker
 *
 */
@Repository("mma031Dao")
public class MMA031Dao extends YniAbstractDAO {
	
	/**
	 * 문의이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInquiryEmailRecordList(Map map) {
		return this.listWithRowPaging("MMA031.selectInquiryEmailRecordList", map);
	}
	
	/**
	 * 첨부파일 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInquiryFileList(Map map) {
		return this.list("MMA031.selectInquiryFileList", map);
	}
	
	/**
	 * 상담문의 첨부파일 내려받기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectInquiryRealFile(Map map) {
		return (Map) this.selectByPk("MMA031.selectInquiryRealFile", map);
	}
	
	/**
	 * 첨부파일 최대 시퀀스 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectInquiryFileMaxNumber(Map map) {
		return (Map) this.selectByPk("MMA031.selectInquiryFileMaxNumber", map);
	}
	
	/**
	 * 상담문의 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Object insertInquiryEmailRecord(Map map) throws Exception {
		return this.insert("MMA031.insertInquiryEmailRecord", map);
	}
	
	/**
	 * 상담문의 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateInquiryEmailRecord(Map map) throws Exception {
		return this.update("MMA031.updateInquiryEmailRecord", map);
	}
	
	/**
	 * 상담문의와 관련 파일 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInquiryEmailRecord(Map map) throws Exception {
		int rst = this.delete("MMA031.deleteInquiryEmailRecord", map);
		
		if(rst > 0) {
			this.delete("MMA031.deleteInquiryFileRecord", map);
		}
		return rst;
	}
	
	/**
	 * 상담문의 파일 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertInquiryFileRecord(Map map) throws Exception {
		return this.update("MMA031.insertInquiryFileRecord", map);
	}
	
	/**
	 * 상담문의 첨부파일 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInquiryFileRecord(Map map) throws Exception {
		return this.update("MMA031.deleteInquiryFileRecord", map);
	}
	
	/**
	 * 시스템 공지사항 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSystemNoticeList(Map map) {
		return this.listWithRowPaging("MMA031.selectSystemNoticeList", map);
	}
	
}
