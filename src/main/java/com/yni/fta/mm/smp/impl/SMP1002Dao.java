package com.yni.fta.mm.smp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 샘플링2
 * 
 * @author YNI-maker
 *
 */
@Repository("smp1002Dao")
public class SMP1002Dao extends YniAbstractDAO {
	
	/**
	 * 시스템 카테고리 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSysCategoryList(Map map) throws Exception {
		return this.listWithRowPaging("SMP1002.selectSysCategoryList", map);
	}
	
	/**
	 * 시스템 카테고리 상세 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectSysCategoryDetail(Map map) throws Exception {
		return (Map) this.selectByPk("SMP1002.selectSysCategoryDetail", map);
	}
	
	/**
	 * 시스템 카테고리 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSysCategoryInfo(Map map) throws Exception {
		return this.update("SMP1002.insertSysCategoryInfo", map);
	}

	/**
	 * 시스템 카테고리 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateSysCategoryInfo(Map map) throws Exception {
		return this.update("SMP1002.updateSysCategoryInfo", map);
	}
	
	/**
	 * 시스템 카테고리 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteSysCategoryInfo(Map map) throws Exception {
		int cnt = this.delete("SMP1002.deleteSysCodeInfo", map);
		cnt += this.delete("SMP1002.deleteSysCategoryInfo", map);
		return cnt;
	}
	
	/**
	 * 시스템 코드 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSysCodeList(Map map) throws Exception {
		return this.list("SMP1002.selectSysCodeList", map);
	}
	
	/**
	 * 시스템 코드 상세 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectSysCodeDetail(Map map) throws Exception {
		return (Map) this.selectByPk("SMP1002.selectSysCodeDetail", map);
	}
	
	/**
	 * 시스템 코드 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSysCodeInfo(Map map) throws Exception {
		return this.update("SMP1002.insertSysCodeInfo", map);
	}

	/**
	 * 시스템 코드 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateSysCodeInfo(Map map) throws Exception {
		return this.update("SMP1002.updateSysCodeInfo", map);
	}
	
	/**
	 * 시스템 코드 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteSysCodeInfo(Map map) throws Exception {
		return this.delete("SMP1002.deleteSysCodeInfo", map);
	}
	
	/**
	 * 시스템 코드 리스트 다국어 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSysCodeLangList(Map map) throws Exception {
		return this.list("SMP1002.selectSysCodeLangList", map);
	}
	
	/**
	 * 시스템 코드 상세 다국어 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectSysCodeLangDetail(Map map) throws Exception {
		return (Map) this.selectByPk("SMP1002.selectSysCodeLangDetail", map);
	}
	
	/**
	 * 시스템 코드 다국어 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSysCodeLangInfo(Map map) throws Exception {
		return this.update("SMP1002.insertSysCodeLangInfo", map);
	}

	/**
	 * 시스템 코드 다국어 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateSysCodeLangInfo(Map map) throws Exception {
		return this.update("SMP1002.updateSysCodeLangInfo", map);
	}
	
	/**
	 * 시스템 코드 다국어 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteSysCodeLangInfo(Map map) throws Exception {
		return this.delete("SMP1002.deleteSysCodeLangInfo", map);
	}
	
}
