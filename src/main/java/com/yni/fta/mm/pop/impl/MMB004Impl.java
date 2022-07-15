package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMB004;

import kr.yni.frame.Constants;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 협정적용 신청서 구현 클래스
 * 
 * @author YNI-maker
 *
 */
@Service("MMB004")
public class MMB004Impl extends YniAbstractService implements MMB004 {
	
	@Resource(name="MMB004Dao")
	private MMB004Dao MMB004Dao;
	
	/**
	 * 협정적용 신청서 정보 조회
	 * 
	 * @param map 검색 조건
	 */
	public Map selectImportCvtaApcPdoList(Map map) throws Exception {
		return MMB004Dao.selectImportCvtaApcPdoList(map);
	}
	
	/**
	 * 협정적용 신청서 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportCvtaApcPdoInfo(Map map) throws Exception {
		int cnt = MMB004Dao.updateImportCvtaApcPdoInfo(map);
		
		// 거래처 정보 업데이트
		MMB004Dao.updateImportCvtaApcBcnc(map); // 거래처 정보 업데이트
		MMB004Dao.updateImportCvtaApcOvseaBcnc(map); // 해외 거래처 정보 업데이트
		
		return cnt;
	}
	
	/**
	 * 협정적용 신청서 란 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectImportCvtaDclrtLneList(Map map) throws Exception {
		return MMB004Dao.selectImportCvtaDclrtLneList(map);
	}
	
	/**
	 * 협정적용 신청서 란정보 일괄 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateBatchImportCvtaDclrtLne(Map map) throws Exception {
		List gridList = JsonUtil.getList(StringHelper.null2void(map.get("gridData")));
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	int cnt = 0;
    	
        if (gridList == null || gridList.size() <= 0) {
            throw new RuntimeException(this.getMessage("MSG_NOT_EXIST_DATA", null, lang));
        } else {
        	for(int i = 0; i < gridList.size(); i++) {
        		Map gridMap = (Map) gridList.get(i);
        		
        		gridMap.put("MOD_BATCH_ITEM", StringHelper.null2void(map.get("MOD_BATCH_ITEM")));
        		gridMap.put("MOD_BATCH_VALUE", StringHelper.null2void(map.get("MOD_BATCH_VALUE")));
        		
        		cnt += MMB004Dao.updateBatchImportCvtaDclrtLne(gridMap);
        	}
        }
        
		return cnt;
	}
	
	/**
	 * 협정적용 신청서 란 상세정보 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectImportCvtaApcPdoLneList(Map map) throws Exception {
		return MMB004Dao.selectImportCvtaApcPdoLneList(map);
	}
	
	/**
	 * 협정적용 신청서 란정보 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImportCvtaApcPdoLneInfo(Map map) throws Exception {
		return MMB004Dao.updateImportCvtaApcPdoLneInfo(map);
	}
	
	/**
	 * 협정적용 신청서 란정보 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteImportCvtaApcPdoLneInfo(Map map) throws Exception {
		return MMB004Dao.deleteImportCvtaApcPdoLneInfo(map);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectImpCvtaApcPdoStardList(Map map) throws Exception {
		return MMB004Dao.selectImpCvtaApcPdoStardList(map);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 사용량 합계 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectImpCvtaApcPdoStardTotInfo(Map map) throws Exception {
		return MMB004Dao.selectImpCvtaApcPdoStardTotInfo(map);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateImpCvtaApcPdoStardInfo(Map map) throws Exception {
		return MMB004Dao.updateImpCvtaApcPdoStardInfo(map);
	}
	
	/**
	 * 협정적용 신청서 원산지 규격 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteImpCvtaApcPdoStardInfo(Map map) throws Exception {
		return MMB004Dao.deleteImpCvtaApcPdoStardInfo(map);
	}
	
}
