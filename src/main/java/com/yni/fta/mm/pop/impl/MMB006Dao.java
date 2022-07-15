package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 인보이스/인보이스 항목 업로드 DAO 클래스
 * 
 * @author YNI-maker
 *
 */
@Repository("MMB006Dao")
public class MMB006Dao extends YniAbstractDAO {

	/**
	 * 최근 설정된 값 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectMaxInvUpSetupNo(Map map) throws Exception {
		return (Map) this.selectByPk("MMCCPOP.selectMaxInvUpSetupNo", map);
	}
	
	/**
	 * 인보이스 업로드 설정정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectInvUpSetupInfo(Map map) throws Exception {
		return (Map) this.selectByPk("MMCCPOP.selectInvUpSetupInfo", map);
	}
	
	/**
	 * 인보이스 업로드 설정 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInvUpSetupList(Map map) throws Exception {
		return this.list("MMCCPOP.selectInvUpSetupInfo", map);
	}
	
	/**
	 * 인보이스 업로드 설정정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String insertInvUpSetupInfo(Map map) throws Exception {
		return (String) this.insert("MMCCPOP.insertInvUpSetupInfo", map);
	}
	
	/**
	 * 인보이스 업로드 설정정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateInvUpSetupInfo(Map map) throws Exception {
		return this.update("MMCCPOP.updateInvUpSetupInfo", map);
	}
	
	/**
	 * 인보이스 업로드 설정정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInvUpSetupInfo(Map map) throws Exception {
		return this.delete("MMCCPOP.deleteInvUpSetupInfo", map);
	}
	
	/**
	 * 인보이스 항목 기본정보  조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectBaseInvUpList(Map map) throws Exception {
		return this.list("MMCCPOP.selectBaseInvUpList", map);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInvUpMasterInfo(Map map) throws Exception {
		return this.list("MMCCPOP.selectInvUpMasterInfo", map);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertInvUpMasterInfo(List list, Map map) throws Exception {
		return this.executeBatch("MMCCPOP.insertInvUpMasterInfo", list, map);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateInvUpMasterInfo(List list, Map map) throws Exception {
		return this.executeBatch("MMCCPOP.updateInvUpMasterInfo", list, map);
	}
	
	/**
	 * 인보이스 항목 업로드 설정정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInvUpMasterInfo(Map map) throws Exception {
		return this.delete("MMCCPOP.deleteInvUpMasterInfo", map);
	}
	
	/**
	 * 인보이스/패킹리스트 업로드 이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectUploadHistList(Map map) throws Exception {
		return this.listWithRowPaging("MMCCPOP.selectUploadHistList", map);
	}
	
	/**
	 * 인보이스 마스터 정보 업로드 이력 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertInvUploadHist(Map map) throws Exception {
		return this.update("MMCCPOP.insertInvUploadHist", map);
	}
	
	/**
	 * 거래처 셋업에 계약번호 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportSetupFileNo(Map map) throws Exception {
		return this.update("MMCCPOP.updateImportSetupFileNo", map);
	}
	
	/**
	 * 인보이스 마스터 정보 업로드 이력 등록 및 수정
	 * - 패킹리스트 업로드 후 인보이스 마스터 정보를 등록함
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateInvUploadHist(Map map) throws Exception {
		return this.update("MMCCPOP.updateInvUploadHist", map);
	}
	
	/**
	 * 인보이스 마스터 정보 업로드 이력 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInvUploadHist(Map map) throws Exception {
		return this.update("MMCCPOP.deleteInvUploadHist", map);
	}
	
	/**
	 * 패킹리스트 열번호 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectPlHistMaxNo(Map map) throws Exception {
		return (Map)this.selectByPk("MMCCPOP.selectPlHistMaxNo", map);
	}
	
	/**
	 * 패킹리스트 업로드 이력정보 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertPlUploadHist(List list) throws Exception {
		return this.executeBatch("MMCCPOP.insertPlUploadHist", list);
	}
	
	/**
	 * 패킹리스트 업로드 이력정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updatePlUploadHist(List list, Map map) throws Exception {
		return this.executeBatch("MMCCPOP.updatePlUploadHist", list, map);
	}
	
	/**
	 * 패킹리스트 업로드 이력 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deletePlUploadHist(Map map) throws Exception {
		return this.update("MMCCPOP.deletePlUploadHist", map);
	}
	
	/**
	 * 패킹리스트 업로드 이력 일괄 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteBatchUploadHist(List list, Map map) throws Exception {
		int cnt = 0;
		
		cnt = this.executeBatch("MMCCPOP.deleteBatchPlUploadHist", list);
		this.delete("MMCCPOP.deleteBatchInvUploadHist", map);
		
		return cnt;
	}
	
}
