package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA103;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 한국 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
@Service("mmA103")
public class MMA103Impl extends YniAbstractService implements MMA103 {
    
    @Resource(name = "mmA103Dao")
    private MMA103Dao mmA103Dao;

    /**
     * 기발급 원산지 증명서 정보 조회(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectCOIssueInfo(Map map) throws Exception {
    	Map resultMap = mmA103Dao.selectCOIssueInfo(map);
    	
		return resultMap;
    }
    
    /**
     * 수출자 및 생산자 조회(신규등록)
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public Map selectExportNProducerInfo(Map map) throws Exception {
        return mmA103Dao.selectExportNProducerInfo(map);
    }
    
    /**
     * 기발급 증명서 원산지 리스트(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public List selectCOOriginList(Map map) throws Exception {
        return mmA103Dao.selectCOOriginList(map);
    }
    
    /**
     * 원산지 판정내역 조회(신규등록)
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List selectDeterminateList(Map map) throws Exception {
        String flag = StringHelper.null2void(map.get("EXPORT_FLAG"));
        
        if(flag.equals("D")) {
            List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
            
            if(log.isDebugEnabled()) log.debug("grid data = " + list.get(0));
            
            map.put("ISSUE_LIST", list);
            
            map.remove("gridData");
        }
        
        return mmA103Dao.selectDeterminateList(map);
    }
    
    /**
     * 서명권자 정보 조회(신규등록)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectSignatureInfo(Map map) throws Exception {
        return mmA103Dao.selectSignatureInfo(map);
    }
    
    /**
     * 수출 원산지 학인서 인보이스 정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectInvoiceInfo(Map map) throws Exception {
        return mmA103Dao.selectInvoiceInfo(map);
    }
    
    /**
     * 수출 원산지 학인서 인보이스 정보 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateInvoiceInfo(Map map) throws Exception {
        return mmA103Dao.updateInvoiceInfo(map);
    }
    
    /**
     * 발급할 보고서 리스트 조회
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public List selectCoDocList(Map map) throws Exception {
        return mmA103Dao.selectCoDocList(map);
    }
    
}
