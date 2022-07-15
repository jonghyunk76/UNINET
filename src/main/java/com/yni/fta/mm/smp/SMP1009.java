package com.yni.fta.mm.smp;

import java.util.List;
import java.util.Map;

/**
 * FTA Rule > FTA협정등록
 * 
 * @author YNI-maker
 *
 */
public interface SMP1009 {
	
	/**
	 * 메시지 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectMessgeList(Map map) throws Exception;
	
	/**
	 * 메시지 상세내역 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map selectMessgeDetail(Map map) throws Exception;
	
    /**
     * 메시지 신규 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int insertMessageInfo(Map map) throws Exception;
    
    /**
     * 메시지 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int updateMessageInfo(Map map) throws Exception;
    
    /**
     * 메시지 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    int deleteMessgeDetail(Map map) throws Exception;
    
}
