package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 시스템 관리 > 공지사항
 * 
 * @author sbj1000
 *
 */
@Repository("sm7011Dao")
public class SM7011Dao extends YniAbstractDAO {
	
	/**
	 * 게시판 > 공지사항 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectNoticeList(Map map) throws Exception {
		return list("SM7011.selectNoticeList", map);
	}
	
	/**
	 * 공지사항 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectNoticeInfo(Map map) throws Exception {
		return (Map) selectByPk("SM7011.selectNoticeInfo", map);
	}
	
	/**
     * 공지사항 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateNoticeInfo(Map map) throws Exception {
        return this.update("SM7011.updateNoticeInfo", map);
    }
    
    /**
     * 공지사항 추가
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Object insertNoticeInfo(Map map) throws Exception {
    	Object regNo = this.insert("SM7011.insertNoticeInfo", map);
    	
    	if(log.isDebugEnabled()) log.debug("Register No : " + regNo);
    	
        return regNo;
    }
    
    /**
     * 공지사항 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteNoticeInfo(Map map) throws Exception {
        return this.update("SM7011.deleteNoticeInfo", map);
    }
    
    /**
     * 공지사항 파일 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectNoticeFile(Map map) throws Exception {
        return (Map) this.selectByPk("SM7011.selectNoticeFile", map);
    }
    
    /**
     * NOTICE_NO 반환
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectLastNoticeNo(Map map) throws Exception {
        return (Map) this.selectByPk("SM7011.selectLastNoticeNo", map);
    }
}
