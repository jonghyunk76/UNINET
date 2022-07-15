package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 증명서 변경요청
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA021Dao")
public class MMA021Dao extends YniAbstractDAO {
	
	/**
	 * 증명서 변경요청 리스트 조회
	 * 
	 * @param map Map
	 * @return List
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA021.selectMainList", map);
	}
	
	/**
	 * 증명서 변경요청 사유 저장
	 * 
	 * @param map Map
	 * @return int
	 * @throws Exception
	 */
	public int updateReqReason(Map map) throws Exception {
	    return this.update("MMA021.updateReqReason", map);
	}
	
	/**
	 * 증명서 변경요청 이력 저장
	 * 
	 * @param map Map
	 * @return int
	 * @throws Exception
	 */
	public int insertReqHistory(Map map) throws Exception {
	    return this.update("MMA021.insertReqHistory", map);
	}
	
	/**
	 * 증명서 변경 원산지 상세내역
	 * 
	 * @param map Map
	 * @return List
	 * @throws Exception
	 */
	public List selectRecieveCoDetail(Map map) throws Exception {
		return this.listWithRowPaging("MMA021.selectRecieveCoDetail", map);
	}
	
	/**
	 * 증빙파일 목록 다운로드 파일
	 * 
	 * @param map Map
	 * @return List
	 * @throws Exception
	 */
	public List selectEvdcDownloadFileList(Map map) throws Exception {
		return this.list("MMA021.selectEvdcDownloadFileList", map);
	}
	
}
