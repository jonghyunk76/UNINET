package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통팝업 : 사업부 및 회사 조회
 * 
 * @author YNI-maker
 *
 */
@Repository("mmA002Dao")
public class MMA002Dao extends YniAbstractDAO {

	/**
	 * 사업부 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return this.listWithRowPaging("MMA002.selectMainList", map);
	}
	
	/**
	 * 회사 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainCompanyList(Map map) throws Exception {
		return this.listWithRowPaging("MMA002.selectMainCompanyList", map);
	}
	
	/**
     * 계약회사 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectContractCompanyList(Map map) throws Exception {
        return this.listWithRowPaging("MMA002.selectContractCompanyList", map);
    }
}
