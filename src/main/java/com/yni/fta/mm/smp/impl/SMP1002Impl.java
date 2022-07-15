package com.yni.fta.mm.smp.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.smp.SMP1002;

import kr.yni.frame.service.YniAbstractService;

/**
 * 샘플링2
 * 
 * @author YNI-maker
 *
 */
@Service("smp1002")
public class SMP1002Impl extends YniAbstractService implements SMP1002 {
	
	@Resource(name="smp1002Dao")
	private SMP1002Dao smp1002Dao;
	
	/**
	 * 시스템 카테고리 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSysCategoryList(Map map) throws Exception {
		return smp1002Dao.selectSysCategoryList(map);
	}
	
	/**
	 * 시스템 카테고리 상세 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectSysCategoryDetail(Map map) throws Exception {
		return smp1002Dao.selectSysCodeDetail(map);
	}
	
	/**
	 * 시스템 카테고리 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSysCategoryInfo(Map map) throws Exception {
		return smp1002Dao.insertSysCategoryInfo(map);
	}

	/**
	 * 시스템 카테고리 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateSysCategoryInfo(Map map) throws Exception {
		return smp1002Dao.updateSysCategoryInfo(map);
	}
	
	/**
	 * 시스템 카테고리 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteSysCategoryInfo(Map map) throws Exception {
		return smp1002Dao.deleteSysCategoryInfo(map);
	}
	
	/**
	 * 시스템 코드 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSysCodeList(Map map) throws Exception {
		return smp1002Dao.selectSysCodeList(map);
	}
	
	/**
	 * 시스템 코드 상세 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectSysCodeDetail(Map map) throws Exception {
		return smp1002Dao.selectSysCodeDetail(map);
	}
	
	/**
	 * 시스템 코드 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSysCodeInfo(Map map) throws Exception {
		return smp1002Dao.insertSysCodeInfo(map);
	}

	/**
	 * 시스템 코드 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateSysCodeInfo(Map map) throws Exception {
		return smp1002Dao.updateSysCodeInfo(map);
	}
	
	/**
	 * 시스템 코드 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteSysCodeInfo(Map map) throws Exception {
		return smp1002Dao.deleteSysCodeInfo(map);
	}
	
	/**
	 * 시스템 코드 다국어 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSysCodeLangList(Map map) throws Exception {
		return smp1002Dao.selectSysCodeLangList(map);
	}
	
	/**
	 * 시스템 코드 다국어 상세 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectSysCodeLangDetail(Map map) throws Exception {
		return smp1002Dao.selectSysCodeLangDetail(map);
	}
	
	/**
	 * 시스템 코드 다국어 신규등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSysCodeLangInfo(Map map) throws Exception {
		return smp1002Dao.insertSysCodeLangInfo(map);
	}

	/**
	 * 시스템 코드 다국어 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateSysCodeLangInfo(Map map) throws Exception {
		return smp1002Dao.updateSysCodeLangInfo(map);
	}
	
	/**
	 * 시스템 코드 다국어 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteSysCodeLangInfo(Map map) throws Exception {
		return smp1002Dao.deleteSysCodeLangInfo(map);
	}
	
}
