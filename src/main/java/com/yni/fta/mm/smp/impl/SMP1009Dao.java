package com.yni.fta.mm.smp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * FTA Rule > 엑셀 데이터 저장 결과 조회
 * 
 * @author YNI-maker
 *
 */
@Repository("smp1009Dao")
public class SMP1009Dao extends YniAbstractDAO {
    
	/**
	 * 메시지 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMessgeList(Map map) throws Exception {
		return this.listWithRowPaging("SMP1009.selectMessgeList", map);
	}

	/**
	 * 메시지 상세내역 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectMessgeDetail(Map map) throws Exception {
		return (Map) this.selectByPk("SMP1009.selectMessgeDetail", map);
	}

	/**
	 * 메시지 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMessageInfo(Map map) throws Exception {
		return this.update("SMP1009.insertMessageInfo", map);
	}

	/**
	 * 메시지 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMessageInfo(Map map) throws Exception {
		return this.update("SMP1009.updateMessageInfo", map);
	}
	
	/**
	 * 메시지 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteMessgeDetail(Map map) throws Exception {
		return this.update("SMP1009.deleteMessgeDetail", map);
	}
	
}
