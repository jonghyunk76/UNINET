package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 게시판 > 사용자 로그
 * 
 * @author YNI-maker
 *
 */
@Repository("sm7018Dao")
public class SM7018Dao extends YniAbstractDAO {

    /**
     * 사용자 로그 목록 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectUseLogList(Map map) throws Exception {
        return this.listWithRowPaging("SM7018.selectUseLogList", map);
    }
    
    /**
     * 사용자 로그 통계정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectUseLogStatusList(Map map) throws Exception {
        return this.listWithRowPaging("SM7018.selectUseLogStatusList", map);
    }
    
}
