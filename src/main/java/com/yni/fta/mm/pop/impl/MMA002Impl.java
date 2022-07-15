package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA002;

import kr.yni.frame.service.YniAbstractService;

/**
 * 공통팝업 : 사업부 및 회사 조회
 * 
 * @author YNI-maker
 *
 */
@Service("mmA002")
public class MMA002Impl extends YniAbstractService implements MMA002 {
	
	@Resource(name="mmA002Dao")
	private MMA002Dao mmA002Dao;

	/**
	 * 사업부 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainList(Map map) throws Exception {
		return mmA002Dao.selectMainList(map);
	}
	
	/**
	 * 회사 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectMainCompanyList(Map map) throws Exception {
		return mmA002Dao.selectMainCompanyList(map);
	}
	
	/**
     * 계약회사 리스트 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List selectContractCompanyList(Map map) throws Exception {
        return mmA002Dao.selectContractCompanyList(map);
    }
	
}
