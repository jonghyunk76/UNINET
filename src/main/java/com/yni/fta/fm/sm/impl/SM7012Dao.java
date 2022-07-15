package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * Demo DAO Class
 * 
 * @author YNI-maker
 *
 */
@Repository("sm7012Dao")
public class SM7012Dao extends YniAbstractDAO {

	/**
	 * 자료실 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectBoardList(Map map) throws Exception {
		return this.listWithRowPaging("BoardDao.selectBoardList", map);
	}
	
	/**
	 * 자료실 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map selectBoardDetail(Map map) throws Exception {
		return (Map) selectByPk("BoardDao.selectBoardDetail", map);
	}
	 /**
     * 자료실 추가
     * 
     * @param map
     * @return
     * @throws Exception
     */
	public Object insertBoardInfo(Map map) throws Exception {
    	Object regNo = this.insert("BoardDao.insertBoardInfo", map);
    	
    	if(log.isDebugEnabled()) log.debug("Register No : " + regNo);
    	
        return regNo;
    }
	
	/**
     * 자료실 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteBoardInfo(Map map) throws Exception {
        return this.update("BoardDao.deleteBoardInfo", map);
    }
    
    /**
     * 자료실 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateBoardInfo(Map map) throws Exception {
        return this.update("BoardDao.updateBoardInfo", map);
    }
    
    /**
     * 파일 조회
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectBoardFile(Map map) throws Exception {
        Map rmap = (Map) selectByPk("BoardDao.selectBoardFile", map);
        return rmap;
    }
    
    /**
     * 파일 목록 조회
     * 
     * @param map
     * @return file 목록
     * @exception
     * @see
     */
    @SuppressWarnings("rawtypes")
    public List selectBoardFileList(Map map) throws Exception {
        return list("BoardDao.selectBoardFileList", map);
    }
    
    /**
     * 파일 삭제
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteBoardFile(Map map) throws Exception {
        return this.delete("BoardDao.deleteBoardFile", map);
    }
    
    /**
     * 파일 등록
     * 
     * @param map  
     * @exception
     * @see
     */
    public int insertBoardFile(Map<String, Object> map) throws Exception {
        return update("BoardDao.insertBoardFile", map);
    }
}
