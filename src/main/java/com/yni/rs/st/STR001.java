package com.yni.rs.st;

import java.util.List;
import java.util.Map;

public interface STR001 {
	
	/**
	 * 서비스 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List selectServerList(Map map) throws Exception;

	/**
     * 서버 등록
     * @param map
     * @return
     * @throws Exception
     */
    int insertServerMst(Map map) throws Exception;
    
    /**
     * 서버 수정
     * @param map
     * @return
     * @throws Exception
     */
    int updateServerMst(Map map) throws Exception;
    
    /**
     * 서버 삭제
     * @param map
     * @return
     * @throws Exception
     */
    int deleteServerMst(Map map) throws Exception;
	
}
