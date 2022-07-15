package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.yni.frame.dao.YniAbstractDAO;

/**
 * 공통 > 협정적용 신청서 DAO 클래스
 * 
 * @author YNI-maker
 *
 */
@Repository("MMB004Dao")
public class MMB004Dao extends YniAbstractDAO {

	/**
	 * 협정적용 신청서 상세조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectImportCvtaApcPdoList(Map map) throws Exception {
		return (Map) this.selectByPk("MMB004.selectImportCvtaApcPdoList", map);
	}
	
	/**
	 * 협정적용 신청서  시 거래처 정보 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportCvtaApcPdoInfo(Map map) throws Exception {
		return this.update("MMB004.updateImportCvtaApcPdoInfo", map);
	}
	
	/**
	 * 협정적용 신청서 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportCvtaApcBcnc(Map map) throws Exception {
		return this.update("MMB004.updateImportCvtaApcBcnc", map);
	}
	
	/**
	 * 협정적용 신청서 시 해외거래처 정보 업데이트
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportCvtaApcOvseaBcnc(Map map) throws Exception {
		return this.update("MMB004.updateImportCvtaApcOvseaBcnc", map);
	}
	
	/**
	 * 협정적용 신청서 란 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectImportCvtaDclrtLneList(Map map) throws Exception {
		return this.list("MMB004.selectImportCvtaDclrtLneList", map);
	}
	
	/**
	 * 협정적용 신청서 란정보 일괄 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateBatchImportCvtaDclrtLne(Map map) throws Exception {
		return this.update("MMB004.updateBatchImportCvtaDclrtLne", map);
	}
	
	/**
	 * 협정적용 신청서 란 상세정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectImportCvtaApcPdoLneList(Map map) throws Exception {
		return (Map) this.selectByPk("MMB004.selectImportCvtaApcPdoLneList", map);
	}
	
	/**
	 * 협정적용 신청서 란정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportCvtaApcPdoLneInfo(Map map) throws Exception {
		this.update("MMB007.updateImportCvtaApcPdoLneInputInfo", map);
		
		return this.update("MMB004.updateImportCvtaApcPdoLneInfo", map);
	}
	
	/**
	 * 협정적용 신청서 란정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteImportCvtaApcPdoLneInfo(Map map) throws Exception {
		return this.update("MMB004.deleteImportCvtaApcPdoLneInfo", map);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectImpCvtaApcPdoStardList(Map map) throws Exception {
		return this.list("MMB004.selectImpCvtaApcPdoStardList", map);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 사용량 합계 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectImpCvtaApcPdoStardTotInfo(Map map) throws Exception {
		return (Map) this.selectByPk("MMB004.selectImpCvtaApcPdoStardTotInfo", map);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImpCvtaApcPdoStardInfo(Map map) throws Exception {
		return this.update("MMB004.updateImpCvtaApcPdoStardInfo", map);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteImpCvtaApcPdoStardInfo(Map map) throws Exception {
		return this.update("MMB004.deleteImpCvtaApcPdoStardInfo", map);
	}
	
}
