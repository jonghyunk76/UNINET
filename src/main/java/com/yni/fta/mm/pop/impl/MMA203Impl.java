package com.yni.fta.mm.pop.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA203;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 원산지 서류 > 원산지 포괄 확인서
 * 
 * @author YNI-maker
 */
@Service("mmA203")
public class MMA203Impl extends YniAbstractService implements MMA203 {

    @Resource(name = "mmA203Dao")
    private MMA203Dao mmA203Dao;

    /**
     * 기발급 원산지 증명서 정보 조회(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectCOIssueInfo(Map map) throws Exception {
    	Map resultMap = mmA203Dao.selectCOIssueInfo(map);
    	
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
        return mmA203Dao.selectExportNProducerInfo(map);
    }
    
    /**
     * 기발급 증명서 원산지 리스트(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public List selectCOOriginList(Map map) throws Exception {
        return mmA203Dao.selectCOOriginList(map);
    }
    
    /**
     * 원산지 판정내역 조회(신규등록)
     * 
     * @param map Map
     * @return List
     * @throws Exception
     */
    public List executeDeterminateList(Map map) throws Exception {
        String flag = StringHelper.null2void(map.get("EXPORT_FLAG"));
        
        if(flag.equals("D")) {
            List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
            
            //map.put("ISSUE_LIST", list);
            for(int i = 0; i < list.size(); i++) {
                Map pmap = (Map)list.get(i);
                
                pmap.put("ROW_SEQ", (i+1));
                pmap.put("CATEGORY_CD", "ISSUE_DO_LIST");
                pmap.put("USER_ID", StringHelper.null2void(map.get("SESSION_USER_ID")));
            }
            
            map.put("CATEGORY_CD", "ISSUE_DO_LIST");
            map.put("USER_ID", StringHelper.null2void(map.get("SESSION_USER_ID")));
            
            // 임시테이블 저장
            mmA203Dao.deleteExcelIssueItemSample(map);
            mmA203Dao.insertExcelIssueItemSample(list);
        }
        
        return mmA203Dao.selectDeterminateList(map);
    }
    
    /**
     * 서명권자 정보 조회(신규등록)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectSignatureInfo(Map map) throws Exception {
        return mmA203Dao.selectSignatureInfo(map);
    }
    
    /**
     * 수출 원산지 학인서 인보이스 정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectInvoiceInfo(Map map) throws Exception {
        return mmA203Dao.selectInvoiceInfo(map);
    }
    
    /**
     * 수출 원산지 학인서 인보이스 정보 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateInvoiceInfo(Map map) throws Exception {
        return mmA203Dao.updateInvoiceInfo(map);
    }
    
    /**
     * 발급할 보고서 리스트 조회
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public List selectCoDocList(Map map) throws Exception {
        return mmA203Dao.selectCoDocList(map);
    }
}
