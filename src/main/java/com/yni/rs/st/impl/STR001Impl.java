package com.yni.rs.st.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.rs.st.STR001;

import kr.yni.frame.service.YniAbstractService;

/**
 * 마감 및 통계 > 마감관리
 * 
 * @author YNI-maker
 *
 */
@Service("stR001")
public class STR001Impl extends YniAbstractService implements STR001 {
	
	@Resource(name="stR001Dao")   
	private STR001Dao stR001Dao;  
	
	/**
	 * 서비스 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectServerList(Map map) throws Exception {
		return stR001Dao.selectServerList(map);
	}

	/**
     * 서버 등록
     * 
     * @param map
     */
    public int insertServerMst(Map map) throws Exception {
        return stR001Dao.insertServerMst(map);
    }
    
    /**
     * 서버 수정
     * 
     * @param map
     */
    public int updateServerMst(Map map) throws Exception {
        return stR001Dao.updateServerMst(map);
    }
    
    /**
     * 서버 삭제
     * 
     * @param map
     */
    public int deleteServerMst(Map map) throws Exception {
        return stR001Dao.deleteServerMst(map);
    }
	
}
