package com.yni.fta.mm.pop.impl;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA004;

import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 공통 > 한국 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
@Service("mmA004")
public class MMA004Impl extends YniAbstractService implements MMA004 {

    @Resource(name = "mmA004Dao")
    private MMA004Dao mmA004Dao;

    /**
     * 기발급 원산지 증명서 정보 조회(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectCOIssueInfo(Map map) throws Exception {
    	Map resultMap = mmA004Dao.selectCOIssueInfo(map);
				
		return resultMap;
    }
    
    /**
     * 증명서 발급 사유 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectIssueReasonInfo(Map map) throws Exception {
    	Map resultMap = mmA004Dao.selectIssueReasonInfo(map);
    	String db = StringHelper.null2void(this.properties.get("application.db.type"));

        if(db.equals("ORACLE")) {
			if(resultMap != null && resultMap.get("REASON_CONTENTS") != null){
	            Clob clob = (Clob) resultMap.get("REASON_CONTENTS");
	            resultMap.put("REASON_CONTENTS", StringHelper.escapeXml(OracleTypeHelper.getStringForCLOB(clob)));
	        }
        }
				
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
        return mmA004Dao.selectExportNProducerInfo(map);
    }

    /**
     * 기발급 증명서 원산지 리스트(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public List selectCOOriginList(Map map) throws Exception {
        return mmA004Dao.selectCOOriginList(map);
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

        if (flag.equals("D")) {
            List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));

            if(log.isDebugEnabled()) log.debug("issue data infomation = " + list.get(0));

            map.put("ISSUE_LIST", list);
            
            Map citemMap = null;
            List citemList = new ArrayList();
            
            for(int i = 0; i < list.size(); i++) {
            	Map cmap = (Map) list.get(i);
            	String citemCd = StringHelper.null2void(cmap.get("CUSTOMER_ITEM_CD"));
            	String pitemCd = StringHelper.null2void(cmap.get("PRODUCT_ITEM_CD"));
            	
            	if(!citemCd.isEmpty() && !citemCd.equals(pitemCd)) {
            		citemMap = new HashMap();
            		
            		citemMap.put("CUSTOMER_ITEM_CD", citemCd);
            		citemMap.put("CUSTOMER_CD", StringHelper.null2void(cmap.get("CUSTOMER_CD")));
            		
            		citemList.add(citemMap);
            	}
            }
            
            if(log.isDebugEnabled()) log.debug("customer item number = " + citemList.size());
            
            if(citemList.size() > 0) map.put("ISSUE_CITEM_LIST", citemList);
            
            map.remove("gridData");
        }

        return mmA004Dao.selectDeterminateList(map);
    }

    /**
     * 서명권자 정보 조회(신규등록)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectSignatureInfo(Map map) throws Exception {
        return mmA004Dao.selectSignatureInfo(map);
    }

    /**
     * 수출 원산지 학인서 인보이스 정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectInvoiceInfo(Map map) throws Exception {
        return mmA004Dao.selectInvoiceInfo(map);
    }

    /**
     * 수출 원산지 학인서 인보이스 정보 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateInvoiceInfo(Map map) throws Exception {
        return mmA004Dao.updateInvoiceInfo(map);
    }

    /**
     * 발급할 보고서 리스트 조회
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public List selectCoDocList(Map map) throws Exception {
        return mmA004Dao.selectCoDocList(map);
    }
    
    /**
     * 발급일자 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateCoIssueDate(Map map) throws Exception {
        return mmA004Dao.updateCoIssueDate(map);
    }
    
}
