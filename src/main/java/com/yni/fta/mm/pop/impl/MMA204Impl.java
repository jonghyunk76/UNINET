package com.yni.fta.mm.pop.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.mm.pop.MMA204;

import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * 수출 원산지 증명서 발급
 * 
 * @author YNI-maker
 */
@Service("mmA204")
public class MMA204Impl extends YniAbstractService implements MMA204 {

    @Resource(name = "mmA204Dao")
    private MMA204Dao mmA204Dao;

    /**
     * 기발급 원산지 증명서 정보 조회(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectCOIssueInfo(Map map) throws Exception {
    	Map resultMap = mmA204Dao.selectCOIssueInfo(map);
    	
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
        return mmA204Dao.selectExportNProducerInfo(map);
    }
    
    /**
     * 기발급 증명서 원산지 리스트(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public List selectCOOriginList(Map map) throws Exception {
        return mmA204Dao.selectCOOriginList(map);
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
        String type = StringHelper.null2void(map.get("SEARCH_TYPE"));
        
        if(flag.equals("D") || type.equals("ITEM")) {
            List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));

            if(log.isDebugEnabled()) log.debug("issue data infomation = " + list.get(0));

            //map.put("ISSUE_LIST", list);
            
//            if(type.equals("ITEM")) {
//	            Map citemMap = null;
//	            List citemList = new ArrayList();
//	            
//	            for(int i = 0; i < list.size(); i++) {
//	            	Map cmap = (Map) list.get(i);
//	            	String citemCd = StringHelper.null2void(cmap.get("CUSTOMER_ITEM_CD"));
//	            	//String pitemCd = StringHelper.null2void(cmap.get("PRODUCT_ITEM_CD"));
//	            	
//	            	if(!citemCd.isEmpty() && !citemList.contains(citemCd)) {
//	            		citemMap = new HashMap();
//	            		
//	            		citemMap.put("CUSTOMER_ITEM_CD", citemCd);
//	            		citemMap.put("CUSTOMER_CD", StringHelper.null2void(cmap.get("CUSTOMER_CD")));
//	            		
//	            		citemList.add(citemMap);
//	            	}
//	            }
//	            
//	            if(log.isDebugEnabled()) log.debug("customer item number = " + citemList.size());
//	            
//	            if(citemList.size() > 0) map.put("ISSUE_CITEM_LIST", citemList);
//            }
            for(int i = 0; i < list.size(); i++) {
                Map pmap = (Map) list.get(i);
                
                pmap.put("ROW_SEQ", (i+1));
                pmap.put("CATEGORY_CD", "ISSUE_CO_LIST");
                pmap.put("USER_ID", StringHelper.null2void(map.get("SESSION_USER_ID")));
            }
            
            map.put("CATEGORY_CD", "ISSUE_CO_LIST");
            map.put("USER_ID", StringHelper.null2void(map.get("SESSION_USER_ID")));
            
            // 임시테이블 저장
            mmA204Dao.deleteExcelIssueItemSample(map);
            mmA204Dao.insertExcelIssueItemSample(list);
        }
        
        return mmA204Dao.selectDeterminateList(map);
    }
    
    /**
     * 생산자 리스트 조회(신규등록)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public List executeProducerList(Map map) throws Exception {
    	String flag = StringHelper.null2void(map.get("EXPORT_FLAG"));
        String type = StringHelper.null2void(map.get("SEARCH_TYPE"));
        
        if(flag.equals("D") || type.equals("ITEM")) {
            List list = JsonUtil.getList(StringHelper.null2string(map.get("gridData"), "[]"));
            
//            map.put("ISSUE_LIST", list);
//            
//            if(type.equals("ITEM")) {
//	            Map citemMap = null;
//	            List citemList = new ArrayList();
//	            
//	            for(int i = 0; i < list.size(); i++) {
//	            	Map cmap = (Map) list.get(i);
//	            	String citemCd = StringHelper.null2void(cmap.get("CUSTOMER_ITEM_CD"));
//	            	//String pitemCd = StringHelper.null2void(cmap.get("PRODUCT_ITEM_CD"));
//	            	
//	            	if(!citemCd.isEmpty() && !citemList.contains(citemCd)) {
//	            		citemMap = new HashMap();
//	            		
//	            		citemMap.put("CUSTOMER_ITEM_CD", citemCd);
//	            		citemMap.put("CUSTOMER_CD", StringHelper.null2void(cmap.get("CUSTOMER_CD")));
//	            		
//	            		citemList.add(citemMap);
//	            	}
//	            }
//	            
//	            if(log.isDebugEnabled()) log.debug("customer item number = " + citemList.size());
//	            
//	            if(citemList.size() > 0) map.put("ISSUE_CITEM_LIST", citemList);
//            }
            
            for(int i = 0; i < list.size(); i++) {
                Map pmap = (Map) list.get(i);
                
                pmap.put("ROW_SEQ", (i+1));
                pmap.put("CATEGORY_CD", "ISSUE_CO_LIST");
                pmap.put("USER_ID", StringHelper.null2void(map.get("SESSION_USER_ID")));
            }
            
            map.put("CATEGORY_CD", "ISSUE_CO_LIST");
            map.put("USER_ID", StringHelper.null2void(map.get("SESSION_USER_ID")));
            
            // 임시테이블 저장
            mmA204Dao.deleteExcelIssueItemSample(map);
            mmA204Dao.insertExcelIssueItemSample(list);
        }
        
        return mmA204Dao.selectProducerList(map);
    }
    
    /**
     * 생산자 리스트 조회(상세보기 및 수정)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public List selectCOProducerList(Map map) throws Exception {
        return mmA204Dao.selectCOProducerList(map);
    }
    
    /**
     * 서명권자 정보 조회(신규등록)
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectSignatureInfo(Map map) throws Exception {
        return mmA204Dao.selectSignatureInfo(map);
    }
    
    /**
     * 수출 원산지 학인서 인보이스 정보 조회
     * 
     * @param map Map
     * @return Map
     * @throws Exception
     */
    public Map selectInvoiceInfo(Map map) throws Exception {
        return mmA204Dao.selectInvoiceInfo(map);
    }
    
    /**
     * 수출 원산지 학인서 인보이스 정보 수정
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public int updateInvoiceInfo(Map map) throws Exception {
        return mmA204Dao.updateInvoiceInfo(map);
    }
    
    /**
     * 발급할 보고서 리스트 조회
     * 
     * @param map Map
     * @return int
     * @throws Exception
     */
    public List selectCoDocList(Map map) throws Exception {
        return mmA204Dao.selectCoDocList(map);
    }
}
