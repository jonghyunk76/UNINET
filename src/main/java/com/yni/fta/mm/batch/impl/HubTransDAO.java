package com.yni.fta.mm.batch.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.tools.HubSupporter;
import com.yni.fta.common.tools.StringUtil;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.StringHelper;

/**
 * FTA HUB(HKMC 바츠) 인터페이스 데이터 추출 클래스
 * 
 * @author ador2
 *
 */
@Repository("hubTransDAO")
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class HubTransDAO extends YniAbstractDAO {
	
	/**
	 * 수신된 XML파일 정보를 읽어 SY테이블(인터페이스 테이블)에 저장한 후 인터페이스된 정보를 리턴한다.
	 * 
	 * @param batchVo 배치를 수행할 정보
	 * @return 이관된 리스트 정보
	 * @throws Exception
	 */
	public List callHubXMLParsing(BatchVo batchVo) throws Exception {
		List<Object> rstList = new LinkedList();

        try {
        	// 1.디렉토리를 검색해서 모든 XML파일을 리스트로 검색한다.
        	String file = StringHelper.null2void(batchVo.getParameter().getMap().get("XML_FILE_PATH"));
        	String fileName = null;
        	
        	if(file.isEmpty()) {
        		batchVo.setBatchStatus("E");
        		batchVo.setTotalRows("0");
                batchVo.setErrorMessage(this.getMessage("MSG_XML_FILE_YN", null, batchVo.getLanguage()));
                
                return null;
        	} else {
        		File fobj = new File(file);
        		
        		fileName = fobj.getName();
        		
        		if(log.isDebugEnabled()) log.debug("XML File = " + file + ", name = " + fileName);
        	}
        		
        	// 2. XML Parsing
        	HubSupporter supporter = new HubSupporter(file);
        	
        	// JCO ID에 해당하는 jco정보 획득
        	String id = batchVo.getJcoId();
            JcoMapHelper jcoMap = new JcoMapHelper(id);
            
            List<String> tableParam = jcoMap.getTableParameterName();
            
            for (int i = 0; i < tableParam.size(); i++) {
                Map inMap = new HashMap();
                
                String tableName = tableParam.get(i);
                List list = jcoMap.getTableColumnName(i);
            	int itemRowCount = 0;
            	String companyCd = StringHelper.null2void(batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
            	
            	inMap.put(Consistent.IF_JOB_COMPANY_CD, companyCd);
            	inMap.put(Consistent.IF_BATCH_INTERFACE_CD, id);

            	Map infMap = this.selectInterfaceItemMst(inMap);
            	
            	inMap.put("SOURCE_TABLE", StringHelper.null2string(infMap.get(Consistent.IF_BATCH_SOURCE_TABLE), tableName));
	        	inMap.put("TARGET_TABLE", infMap.get(Consistent.IF_BATCH_TARGET_TABLE));
	        	
            	// 자재 추출 갯수 조회
            	if(id.equals("RPT_FH_002") || id.equals("RPT_FH_003")) {
	            	for(int t = 0; t < list.size(); t++) {
	                	Map<String, Object> colAtrMap = (Map) list.get(t);
	                	
	                	String colName = StringHelper.null2void(colAtrMap.get("COLUMN_NAME"));
	                	String path = StringHelper.null2void(colAtrMap.get("COLUMN_BASECODE"));
	                	
	                	if(colName.equals("ITEM_CD")) {
	                		itemRowCount = supporter.getItemCount(path);
	                		break;
	                	}
	                }
            	} else {
            		itemRowCount = 1;
            	}
            	
//            	for(int t = 0; t < list.size(); t++) { // 설정된 테이블 컬럼수만큼 불필요하게 중복되는 데이터를 가져와서 주석처리함(2022-02-11)  
            		int agetRowCount = 0;
            		int t = 0;
            		
            		// 협정 등록 갯수 조회
            		if(id.equals("RPT_FH_003")) {
	            		for(int a = 0; a < list.size(); a++) {
		                	Map<String, Object> colAtrMap = (Map) list.get(a);
		                	
		                	String colName = StringHelper.null2void(colAtrMap.get("COLUMN_NAME"));
		                	String path = StringHelper.null2void(colAtrMap.get("COLUMN_BASECODE"));
		                	
		                	if(colName.equals("FTA_CD")) {
		                		agetRowCount = supporter.getAgreementCount(path, "ITEM", t);
		                		break;
		                	}
		                }
            		} else {
            			agetRowCount = 1;
            		}
            		
            		log.debug("FTA Argemnet count = " + agetRowCount);
            		
            		// 데이터 추출
            		for(int a = 0; a < agetRowCount; a++) {
            			List<Map<String, Object>> dataList = new ArrayList();
            			String refrerenceId = null;
            			String documentId = null;
            			String itemSeq = null;
            			String ftaCd = null;
            			
		                for(int c = 0; c < list.size(); c++) { // 컬럼
		                	Map<String, Object> dataMap = new HashMap();
		                	Map<String, Object> colAtrMap = (Map) list.get(c);
		                	
		                	String colName = StringHelper.null2void(colAtrMap.get("COLUMN_NAME"));
		                	String path = StringHelper.null2void(colAtrMap.get("COLUMN_BASECODE"));
		                	String type = StringHelper.null2void(colAtrMap.get("COLUMN_TYPE"));
		                	String format = StringHelper.null2void(colAtrMap.get("COLUMN_FORMAT"));
		                	String value = null;
		                	
		                	if(colName.equals("COMPANY_CD")) {
		                		value = companyCd;
		                	} else if(colName.equals("FILE_NAME")) {
		                		value = fileName;
		                    } else {
			                	if(!path.isEmpty()) {
			                		if(id.equals("RPT_FH_003")) {
			                			value = supporter.getValue(path, "ITEM", t, "FTA", a); // 협정정보
			                		}
			                		if(id.equals("RPT_FH_002") || id.equals("RPT_FH_003")) {
			                			if(value == null || value.isEmpty()) {
			                				value = supporter.getValue(path, "ITEM", t); // 자재정보
			                			}
			                		}
			                		if(value == null || value.isEmpty()) {
			                			value = supporter.getValue(path, 0);
			                		}
			                		
			                		if(colName.equals("REFERENCE_ID")) refrerenceId = value;
			                		if(colName.equals("DOCUMENT_ID")) documentId = value;
			                		if(colName.equals("ITEM_SEQ")) itemSeq = value;
			                		if(colName.equals("FTA_CD")) ftaCd = value;
			                	}
		                	}
		                	
		                	if(format.equals("BASE64")) {
		                		dataMap.put("COLUMN_VALUE", StringUtil.fileBase64Decoder(value));
		                		
		                		//if(log.isDebugEnabled()) log.debug(colName + "[BASE64 - Type:" + type + "] = byte");
	                		} else {
	                			dataMap.put("COLUMN_VALUE", value);
	                			
	                			//if(log.isDebugEnabled()) log.debug(colName + "[Type:" + type + "] = " + value);
	                		}
		                	dataMap.put("COLUMN_NAME", colName);
		                	dataMap.put("COLUMN_TYPE", type);
		                	dataMap.put("COLUMN_NAME", colName);
		                	
		                	dataList.add(dataMap);
		                }
		                
		                inMap.put("COLUMN_LIST", dataList);
		                inMap.put("REFERENCE_ID", refrerenceId);
		                inMap.put("ITEM_SEQ", itemSeq);
		                inMap.put("FTA_CD", ftaCd);
		                inMap.put("DOCUMENT_ID", documentId);
		                
		                // SY 테이블의 기 저장데이터 삭제
		                this.deleteSourceTable(inMap);
		                
		                // SY 테이블 저장
		                this.insertSourceTable(inMap);
            		}
//            	}
            	
            	rstList = this.selectSourceTable(inMap);
            	
            	if(rstList.size() > 0) {
            		batchVo.setBatchStatus("S");
            		batchVo.setTotalRows(Integer.toString(rstList.size()));
            		batchVo.setErrorMessage("");
            		
            		// TR 테이블의 기 저장데이터 삭제
	                this.deleteTargetTable(inMap);
            	} else {
            		batchVo.setBatchStatus("E");
            		batchVo.setTotalRows("0");
                    batchVo.setErrorMessage(this.getMessage("MSG_NOT_EXIST_DATA", null, batchVo.getLanguage()));
            	}
            	
            	break;
            }
            
            if (log.isInfoEnabled()) {
                log.info("Request to select the results(Interface code = " + batchVo.getInterfaceCode() + " | Result Description = "
                        + batchVo.getErrorMessage());
            }
        } catch (Exception exp) {
        	exp.printStackTrace();
            if(log.isErrorEnabled()) log.error(exp);
            throw exp;
        }

        return rstList;
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
            returnValue = list("MMHUBBATCH.selectSourceTable", map);
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
            returnValue = delete("MMHUBBATCH.deleteSourceTable", map);
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
            returnValue = delete("MMHUBBATCH.deleteTargetTable", map);
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
            returnValue = update("MMHUBBATCH.insertSourceTable", map);
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
            returnValue = (Map) this.selectByPk("MMHUBBATCH.selectInterfaceItemMst", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
}
