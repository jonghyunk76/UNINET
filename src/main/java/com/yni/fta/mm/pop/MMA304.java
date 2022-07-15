package com.yni.fta.mm.pop;

import java.util.List;
import java.util.Map;

/**
 * 공통 > 베트남 원산지 서류 발급
 * 
 * @author YNI-maker
 *
 */
public interface MMA304 {

	/**
	 * 원산지 증명서 마스터 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectInvCoMasterInfo(Map map) throws Exception;
	
	/**
	 * 원산지 신고품목 리스트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectInvPackingList(Map map) throws Exception;
	
}
