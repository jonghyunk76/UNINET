package com.yni.fta.fm.sm.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 시스템관리 > 사업부등록
 * 
 * @author carlos
 *
 */
@Repository("sm7002Dao")
public class SM7002Dao extends YniAbstractDAO {

    /**
     * 사업부 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectDivisionList(Map map) throws Exception {
        return this.listWithRowPaging("SM7002.selectDivisionList", map);
    }
    
    /**
     * 사업부 정보 중복건수 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectDivisionDupCheck(Map map) throws Exception {
        return (Map) this.selectByPk("SM7002.selectDivisionDupCheck", map);
    }
    
    /**
     * 사업부 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map selectDivisionInfo(Map map) throws Exception {
        return (Map) this.selectByPk("SM7002.selectDivisionInfo", map);
    }
    
    /**
     * 사업부 신규등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertDivisionInfo(Map map) throws Exception {
        return this.update("SM7002.insertDivisionInfo", map);
    }

    /**
     * 사업부 수정
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateDivisionInfo(Map map) throws Exception {
        return this.update("SM7002.updateDivisionInfo", map);
    }
    
    /**
     * 사업부 바츠 인증ID 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int updateDivisionVaatzIDInfo(Map map) throws Exception {
        return this.update("SM7002.updateDivisionVaatzIDInfo", map);
    }
    
    /**
     * 사업부 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteDivisionInfo(Map map) throws Exception {
        log.debug("deleteDivisionInfo = " +  map.toString());
        return this.update("SM7002.deleteDivisionInfo", map);
    }
    
    /**
     * 사업부 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteDivisionInfoReal(Map map) throws Exception {
        log.debug("deleteDivisionInfo = " +  map.toString());
        return this.delete("SM7002.deleteDivisionInfo", map);
    }
}
