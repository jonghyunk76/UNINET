package com.yni.fta.mm.pop.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 표준통관예정 자료 업로드 DAO 클래스
 * 
 * @author YNI-maker
 *
 */
@Repository("MMB008Dao")
public class MMB008Dao extends YniAbstractDAO {

	/**
	 * 표준통관 업로드 데이터 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectStandardEntrUploList(Map map) throws Exception {
		return this.list("MMCCPOP.selectStandardEntrUploList", map);
	}
	
	/**
	 * 표준통관 업로드 데이터 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteStandardEntrUplo(Map map) throws Exception {
		return this.delete("MMCCPOP.deleteStandardEntrUplo", map);
	}
	
	/**
	 * 표준통관 업로드 데이터 일괄 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateStandardEntrUplo(List updlist, List inslist) throws Exception {
		this.executeBatch("MMCCPOP.insertNewStandardEntrUplo", inslist);
		
		return this.executeBatch("MMCCPOP.updateStandardEntrUplo", updlist);
	}
	
	/**
	 * 표준통관 업로드 데이터 일괄 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteStandardEntrUplo(List list) throws Exception {
		return this.executeBatch("MMCCPOP.deleteStandardEntrUplo", list);
	}
	
	/**
	 * 표준통관 업로드 데이터 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertStandardEntrUplo(List list) throws Exception {
		return this.executeBatch("MMCCPOP.insertStandardEntrUplo", list);
	}
	
	/**
	 * 표준통관 데이터 중복수(요건번호, 품목코드, 수량이 중복된 개수) 업데이트
	 * (사용한 잔량을 구하기 위한 정보로 활용된다.)
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateStandardEntrDupCnt(Map map) throws Exception {
		return this.update("MMCCPOP.updateStandardEntrDupCnt", map);
	}
	
	/**
	 * 표준통관 데이터 최대 중복수 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String selectStandardEntrDupCnt(Map map) throws Exception {
		return (String) this.selectByPk("MMCCPOP.selectStandardEntrDupCnt", map);
	}
	
	/**
	 * 표통과 수입업로드 자료를 매칭정보 초기화
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateInitStandardEntrUploadNo(Map map) throws Exception {
		return this.update("MMCCPOP.updateInitStandardEntrUploadNo", map);
	}
	
	/**
	 * 표통과 수입업로드 자료를 매칭해서 표통에 수입업로드번호를 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateStandardEntrUploadNo(List list) throws Exception {
		// 1.수량이 일치한건을 우선 반영
		int cnt = this.executeBatch("MMCCPOP.updateStandardEntrUploadNo", list);
		
		// 2.입력수량보다 표통수량이 큰건을 반영
		for(int i = 0; i < list.size(); i++) {
			Map pmap = new HashMap();
			
			pmap.put("STEP_NO", "2");
		}
		this.executeBatch("MMCCPOP.updateStandardEntrUploadNo", list);
		
		return cnt;
	}
	
}
