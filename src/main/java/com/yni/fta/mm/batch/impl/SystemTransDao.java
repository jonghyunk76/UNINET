package com.yni.fta.mm.batch.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.tools.PythonSupporter;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

/**
 * O/S 시스템 실행 및 데이터 추출 클래스
 * 
 * @author ador2
 *
 */
public class SystemTransDao extends YniAbstractDAO {

    /**
     * Python 파일 실행
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> callRemotePython(BatchVo batchVo) throws Exception {
        List<Object> rstList = new LinkedList();
        
        try {
	        ParameterVo pvo = batchVo.getParameter();
	        Map pmap = pvo.getMap();
	        
	        String url = pvo.getUrl();
			String fname = pvo.getIfCode(); // 파이썬 실행파일은 인터페이스ID와 동일한 조건
			String nationCd = StringHelper.null2void(pmap.get("NATION_CD"));
			String hsCde = StringHelper.null2void(pmap.get("HS_CODE"));
			String ccd = StringHelper.null2void(pmap.get("COMPANY_CD"));
//			String fdate = pvo.getFromDate();
//			String edate = "";
//			if(fdate != null && fdate.length() > 3) edate = fdate.substring(0, 4)+"1231"; // 1년단위로 지정함
			
			String tableName = ""; 
	        
			// JCO ID에 해당하는 jco정보 획득
        	String id = batchVo.getJcoId();
            JcoMapHelper jcoMap = new JcoMapHelper(id);
            List<String> tableParam = jcoMap.getTableParameterName();
            
            if(tableParam != null && tableParam.size() > 0) {
            	tableName = tableParam.get(0);
            }
            
	        if(pvo.getIfCode().equals("TFC_KR_001")) { // Tradenavi tariff 크롤링
	        	Map infMap = this.selectInterfaceItemMst(pmap);
            	
	        	pmap.put("SOURCE_TABLE", StringHelper.null2string(infMap.get(Consistent.IF_BATCH_SOURCE_TABLE), tableName));
	        	pmap.put("TARGET_TABLE", infMap.get(Consistent.IF_BATCH_TARGET_TABLE));
	        	
	        	// SY 테이블의 기 저장데이터 삭제
                this.deleteSourceTable(pmap);
                // TR 테이블의 기 저장데이터 삭제
                this.deleteTargetTable(pmap);
                
	        	List tlist = null; // 인터페이스 대상 목록
	        	
	        	if(nationCd.isEmpty()) {
	        		tlist = this.selectExportTargetList(pmap); // 직수출 내역을 대상으로 검색할 대상으로 조회
	        	} else {
	        		tlist = new LinkedList();
	        		
	        		tlist.add(pmap);
	        	}
	        	
	        	for(int t = 0; t < tlist.size(); t++) {
	        		Map tmap = (Map) tlist.get(t);
	        		
	        		String ncd = StringHelper.null2void(tmap.get("NATION_CD"));
	        		String hcd = StringHelper.null2void(tmap.get("HS_CODE"));
	        		
	        		Map jmap = new LinkedHashMap();
	        		
	        		jmap.put("RUN_FILE_NAME", fname);
	        		jmap.put("CRAWL_URL", url);
	        		jmap.put("PY_PARAM", "[{\"TYPE\":\"STR\", \"VALUE\":\"" + ncd + "\"}, {\"TYPE\":\"STR\", \"VALUE\":\"" + hcd + "\"}]"); // 수출국가코드, HS코드 4단위
	        		
	        		if(log.isDebugEnabled()) log.debug("Company Code = " + ccd + ", Execute paramter = " + jmap);
	        		
	        		String rst = PythonSupporter.excuteScript(jmap);
	        		
	        		List rlist = JsonUtil.getList(rst);
	        		
	        		if(rlist != null && rlist.size() > 0) {
	        			
	        		    for(int i = 0; i < rlist.size(); i++) {
	        		    	Map tmpMap = (Map) rlist.get(i);
	        		    	
	        	            String hsCd = null;
	        	            String naCd = null;
	        	            String hs_desc = null;
	        	            String rate_se_desc = null;
	        	            String rate_desc = null;
	        	            
	        	            for (Iterator it = tmpMap.entrySet().iterator(); it.hasNext();) {
	        	                Map.Entry entry = (Map.Entry) it.next();
	        	                String key = StringHelper.null2void(entry.getKey());
	        	                String ckey = key.toUpperCase().replaceAll(" ", "");
	        	                
	        	                if(ckey.indexOf("HSCODE") >= 0) hsCd = StringHelper.null2void(tmpMap.get(key));
	        	                else if(ckey.indexOf("NATION") >= 0) naCd = StringHelper.null2void(tmpMap.get(key));
	        	                else if(ckey.indexOf("HS_DESC") >= 0) hs_desc = StringHelper.null2void(tmpMap.get(key));
	        	            }
	        	            
//	        	            log.debug("hs code = " + hsCd + ", nation code = " + naCd);
	        	            
	        	            if(hsCd != null && naCd != null) {
	        		            for (Iterator it = tmpMap.entrySet().iterator(); it.hasNext();) {
	        		                Map.Entry entry = (Map.Entry) it.next();
	        		                String key = StringHelper.null2void(entry.getKey());
	        		                String ckey = key.toUpperCase().replaceAll(" ", "");
	        		                
	        		                if(!(ckey.indexOf("HSCODE") >= 0 || ckey.indexOf("NATION") >= 0 || ckey.indexOf("HS_DESC") >= 0 || ckey.indexOf("RATE_DESC") >= 0 || ckey.indexOf("RATE_SE_DESC") >= 0)) {
	        		                	Map pMap = new HashMap();
	        		                	
	        		                	pMap.put("COMPANY_CD", ccd);
	        		                	pMap.put("EXP_NATION_CD", "KR");
	        		                	pMap.put("IMP_NATION_CD", naCd);
	        		                	pMap.put("HS_CODE", hsCd);
	        		                	pMap.put("HS_DESC", hs_desc);
	        		                	pMap.put("TARRATE_SE_NM", key); // 세율구분명
	        		                	pMap.put("RATE_SE_DESC", StringHelper.null2void(tmpMap.get(key+"_RATE_SE_DESC"))); // 원문 - 세율구분명
	        		                	pMap.put("TARRATE", StringHelper.null2void(tmpMap.get(key))); // 세율
	        		                	pMap.put("RATE_DESC", StringHelper.null2void(tmpMap.get(key+"_RATE_DESC"))); // 원문 - 세율설명
	        		                	pMap.put("CRAWL_URL", url.replace("{0}", ncd).replace("{1}", hcd)); // 실제 요청URL
//	        		                	pMap.put("APPLY_DATE", fdate);
//	        		                	pMap.put("END_DATE", edate);
	        		                	
	        		                	rstList.add(pMap);
	        		                }
	        		            }
	        	            }
	        		    }
	        		}
	        	}
	        }
	        
	        if(rstList.size() > 0) {
        		batchVo.setBatchStatus("S");
        		batchVo.setTotalRows(Integer.toString(rstList.size()));
        		batchVo.setErrorMessage("");
        	} else {
        		batchVo.setBatchStatus("E");
        		batchVo.setTotalRows("0");
                batchVo.setErrorMessage(this.getMessage("MSG_NOT_EXIST_DATA", null, batchVo.getLanguage()));
        	}
        } catch (Exception exp) {
        	exp.printStackTrace();
            if(log.isErrorEnabled()) log.error(exp);
            
            throw exp;
        }
        
        return rstList;
    }
    
    /**
     * 직수출 Tariff 대상 목록 조회
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectExportTargetList(Map map) throws Exception {
        List<Object> returnValue = null;
        
        try {
            returnValue = list("MMTARIFFBATCH.selectExportTargetList", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
    /**
     * XML 데이터를 저장하고 있는 SY테이블을 조회한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectSourceTable(Map map) throws Exception {
        List<Object> returnValue = null;
        
        try {
            returnValue = list("MMTARIFFBATCH.selectSourceTable", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
    /**
     * 요청서  XML 데이터를  SY테이블에서 삭제한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteSourceTable(Map map) throws Exception {
        int returnValue = 0;
        
        try {
            returnValue = delete("MMTARIFFBATCH.deleteSourceTable", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
    /**
     * 요청서 자재마스터 XML 데이터를  TR테이블에서 삭제한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int deleteTargetTable(Map map) throws Exception {
        int returnValue = 0;
        
        try {
            returnValue = delete("MMTARIFFBATCH.deleteTargetTable", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
	/**
     * 요청서  정보 XML 데이터를  SY테이블에 저장한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public int insertSourceTable(Map map) throws Exception {
        int returnValue = 0;
        
        try {
            returnValue = update("MMTARIFFBATCH.insertSourceTable", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
    /**
     * 인터페이스 항목정보를 조회한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectInterfaceItemMst(Map map) throws Exception {
        Map<String, Object> returnValue = null;
        
        try {
            returnValue = (Map) this.selectByPk("MMTARIFFBATCH.selectInterfaceItemMst", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
}
