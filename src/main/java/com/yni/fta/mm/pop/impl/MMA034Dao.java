package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 데이터 그리드 설정
 * 
 * @author 김종현
 *
 */
@Repository("mmA034Dao")
public class MMA034Dao extends YniAbstractDAO {

    /**
     * 데이터 그리드 등록 정보 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectDatagridMstList(Map map) throws Exception {
        return this.listWithRowPaging("MMA034.selectDatagridMstList", map);
    }
    
    /**
     * 데이터 그리드 정보 삭제
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteDatagridMstInfo(Map map) throws Exception {
        return this.delete("MMA034.deleteDatagridMstInfo", map);
    }
    
    /**
     * 데이터 그리드 정보 등록
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertDatagridMstInfo(List list) throws Exception {
        return this.executeBatch("MMA034.insertDatagridMstInfo", list);
    }
    
}
