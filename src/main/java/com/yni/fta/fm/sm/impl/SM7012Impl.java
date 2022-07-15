package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.fm.sm.SM7012;
import com.yni.fta.fm.sm.impl.SM7012Dao;

import kr.yni.frame.service.YniAbstractService;

/**
 * 게시판 > 자료실
 * 
 * @author YNI-maker
 *
 */
@Service("sm7012")
public class SM7012Impl extends YniAbstractService implements SM7012 {
	
	@Resource(name="sm7012Dao")
	private SM7012Dao sm7012Dao;
	
	/**
	 * 자료실 리스트 조회
	 * 
	 * @param map 검색 조건
	 */
	public List selectBoardList(Map map) throws Exception {
		return sm7012Dao.selectBoardList(map);
	}
	/**
	 * 자료실 상세조회
	 * 
	 * @param map 검색조건
	 * @return 
	 * @throws Exception
	 */
	public Map selectBoardDetail(Map map) throws Exception {
		Map resultMap = sm7012Dao.selectBoardDetail(map);
		return resultMap;
	}
	
	
	public Object insertBoardInfo(Map map) throws Exception {
        return sm7012Dao.insertBoardInfo(map);
    }
	
	
	
	public int deleteBoardInfo(Map map) throws Exception {        
		return sm7012Dao.deleteBoardInfo(map);
    }
	
	public int updateBoardInfo(Map map) throws Exception {   
        return sm7012Dao.updateBoardInfo(map);
		
	}
        
        
	public Map selectBoardFile(Map map) throws Exception {
        return sm7012Dao.selectBoardFile(map);
    }
	
	public List selectBoardFileList(Map map) throws Exception {
		List returnValue = sm7012Dao.selectBoardFileList(map);
		return returnValue;
	}
	
	public int deleteBoardFile(Map map) throws Exception {
		return sm7012Dao.deleteBoardFile(map);
	}
	
}
