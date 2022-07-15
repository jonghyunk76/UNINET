package com.yni.fta.mm.batch.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.tools.FileReaderSupporter;

import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.reader.FileReader;
import kr.yni.frame.reader.type.ExcelSSReader;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.StringHelper;

public class ExcelTransDao extends YniAbstractDAO {

    private static Log log = LogFactory.getLog(ExcelTransDao.class);

    /**
     * 엑셀업로드한 데이터를 조회해서 SY테이블(인터페이스 테이블)에 저장한 후 인터페이스된 정보를 리턴한다.
     * 
     * @param batchVo 배치를 수행할 정보
     * @return 이관된 리스트 정보
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> callLocalExcelParsing(BatchVo batchVo) throws Exception {
        List<Object> rstList = null;

        Map paramMap = batchVo.getParameter().getMap();

        try {
        	// JCO ID에 해당하는 jco정보 획득
        	String id = batchVo.getJcoId();
            JcoMapHelper jcoMap = new JcoMapHelper(id);
            
            List<String> tableParam = jcoMap.getTableParameterName();
            
            for (int i = 0; i < tableParam.size(); i++) {
            	Map inMap = new HashMap();
            	
	            String tableName = tableParam.get(i);
	            String companyCd = StringHelper.null2void(batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
	        	
	        	inMap.put(Consistent.IF_JOB_COMPANY_CD, companyCd);
	        	inMap.put(Consistent.IF_BATCH_INTERFACE_CD, id);

            	Map infMap = this.selectInterfaceItemMst(inMap);
            	List infList = this.selectInterfaceItemDtl(inMap);
            	
	        	inMap.put("SOURCE_TABLE", StringHelper.null2string(infMap.get(Consistent.IF_BATCH_SOURCE_TABLE), tableName));
	        	inMap.put("TARGET_TABLE", infMap.get(Consistent.IF_BATCH_TARGET_TABLE));
	        	inMap.put("COLUMN_LIST", infList);
	        	inMap.put("USER_ID", StringHelper.null2void(paramMap.get("USER_ID")));
	        	
	        	// SY 테이블의 기 저장데이터 삭제
	            this.deleteSourceTable(inMap);
	            // SY 테이블 저장
	            this.insertSourceTable(inMap);
	            // 저장된 데이터를 조회한다.
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
            if(log.isErrorEnabled()) log.error(exp.getCause());
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
            returnValue = list("MMExcelBATCH.selectSourceTable", map);
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
            returnValue = delete("MMExcelBATCH.deleteSourceTable", map);
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
            returnValue = delete("MMExcelBATCH.deleteTargetTable", map);
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
            returnValue = update("MMExcelBATCH.insertSourceTable", map);
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
            returnValue = (Map) this.selectByPk("MMExcelBATCH.selectInterfaceItemMst", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
    /**
     * 인터페이스 항목의 상세컬럼 정보를 조회한다.
     * 
     * @param map
     * @return
     * @throws Exception
     */
    public List<Object> selectInterfaceItemDtl(Map map) throws Exception {
        List<Object> returnValue = null;
        
        try {
            returnValue = list("MMExcelBATCH.selectInterfaceItemDtl", map);
        } catch (Exception exp) {
        	if(log.isErrorEnabled()) log.error(exp.getCause());
            throw exp;
        }

        return returnValue;
    }
    
    /**
     * 업로드한 엑셀파일을 삭제한다.
     * 
     * @param map
     * @throws Exception
     */
    public boolean excelFileDelete(Map map) throws Exception {
        String file = StringHelper.null2void(map.get("EXCEL_FILE_NAME"));
        String lang = StringHelper.null2void(map.get("DEFAULT_LANGUAGE"));
        boolean rst = FileUtil.deleteTo(file);

        if (!rst) {
            throw new FrameException("File not found in the server", null, lang);
        }

        return rst;
    }
}
