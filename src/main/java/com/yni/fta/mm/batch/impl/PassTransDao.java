package com.yni.fta.mm.batch.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sun.jna.platform.FileUtils;
import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.tools.FileReaderSupporter;
import com.yni.fta.common.tools.HubSupporter;
import com.yni.fta.common.tools.StringUtil;

import kr.yni.frame.Constants;
import kr.yni.frame.dao.YniAbstractDAO;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.StringHelper;

/**
 * FTA PASS 인터페이스 데이터 추출 클래스
 * 
 * @author ador2
 *
 */
@Repository("PassTransDAO")
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class PassTransDao extends YniAbstractDAO {
	
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
        	String path = StringHelper.null2void(batchVo.getParameter().getMap().get("XML_FILE_PATH")); 
        	String fileName = StringHelper.null2void(batchVo.getParameter().getMap().get("XML_FILE_NAME")); 
        	
        	if(path.isEmpty()) {
        		batchVo.setBatchStatus("E");
        		batchVo.setTotalRows("0");
                batchVo.setErrorMessage(this.getMessage("MSG_XML_FILE_YN", null, batchVo.getLanguage()));
                
                return null;
        	}
        	
        	if(log.isDebugEnabled()) log.debug("Search directory path = " + path);
        	
        	String[] ext = {".xml"};
        	FileReaderSupporter fileSupport = new FileReaderSupporter();
    		File[] files = fileSupport.getPathAllFileList(path, ext);
    		
			// JCO ID에 해당하는 jco정보 획득
    		String id = batchVo.getJcoId();
    		String companyCd = StringHelper.null2void(batchVo.getMap().get(Consistent.IF_JOB_COMPANY_CD));
        	JcoMapHelper jcoMap = new JcoMapHelper(id);
        	Map inMap = new HashMap();
        	
            inMap.put(Consistent.IF_JOB_COMPANY_CD, companyCd);
        	inMap.put(Consistent.IF_BATCH_INTERFACE_CD, id);
        	
            Map infMap = this.selectInterfaceItemMst(inMap);
        	
        	inMap.put("SOURCE_TABLE", StringHelper.null2void(infMap.get(Consistent.IF_BATCH_SOURCE_TABLE)));
        	inMap.put("TARGET_TABLE", infMap.get(Consistent.IF_BATCH_TARGET_TABLE));
        	
        	// SY 테이블의 기 저장데이터 삭제
            this.deleteSourceTable(inMap);
            // TR 테이블의 기 저장데이터 삭제
            this.deleteTargetTable(inMap);
	            
            if(files.length == 0) {
    			batchVo.setBatchStatus("E");
        		batchVo.setTotalRows("0");
                batchVo.setErrorMessage(this.getMessage("MSG_NOT_EXIST_DATA", null, batchVo.getLanguage()));
    		} else {
	    		for(int f = 0; f < files.length; f++) {
	    			String fname = files[f].getName();
	    			
	    			// 처리할 특정 파일을 지정했을 경우에는 해당 파일만 처리하도록 함 
	    			if(!fileName.isEmpty() && !fileName.equals(fname)) {
	    				continue;
	    			}
	    			
	    			String file = files[f].getAbsolutePath();
	    			
		    		if(log.isDebugEnabled()) log.debug("XML File = " + file + ", name = " + files[f].getName());
		    		
		        	HubSupporter supporter = new HubSupporter(file); // 2. XML Parsing
		            List<String> tableParam = jcoMap.getTableParameterName();
		            
		            for(int i = 0; i < tableParam.size(); i++) { // 테이블 갯수만큼 loop
		            	inMap = new HashMap();
		            	
		            	String tableName = tableParam.get(i);
		            	List list = jcoMap.getTableColumnName(i);
		            	int itemCount = 1;
		            	
		            	inMap.put(Consistent.IF_JOB_COMPANY_CD, companyCd);
		            	inMap.put(Consistent.IF_BATCH_INTERFACE_CD, id);
		            	
		            	inMap.put("SOURCE_TABLE", StringHelper.null2string(infMap.get(Consistent.IF_BATCH_SOURCE_TABLE), tableName));
			        	inMap.put("TARGET_TABLE", infMap.get(Consistent.IF_BATCH_TARGET_TABLE));
			        	
		                // 품목별 협정별 원산지 정보를 추출하기 위해 등록된 개수를 구한다.
		                if(id.equals("TFP_IN_002")) {
		                	itemCount = supporter.getItemCount("/wco:Declaration/wco:Consignment/wco:ConsignmentItem/wco:SequenceNumeric");
	            		}
		                
		                log.debug("Item detail count = " + itemCount);
		                
		                for(int a = 0; a < itemCount; a++) { // 항목 수만큼 Loop
			                List<Map<String, Object>> dataList = new ArrayList();
			            	
			            	for(int t = 0; t < list.size(); t++) { // 컬럼 갯수만큼 loop
			            		Map<String, Object> colAtrMap = (Map) list.get(t);
			            		Map<String, Object> dataMap = new HashMap();
			            		
			            		String colName = StringHelper.null2void(colAtrMap.get("COLUMN_NAME"));
			                	String xml_path = StringHelper.null2void(colAtrMap.get("COLUMN_BASECODE"));
			                	String type = StringHelper.null2void(colAtrMap.get("COLUMN_TYPE"));
			                	String format = StringHelper.null2void(colAtrMap.get("COLUMN_FORMAT"));
			                	String value = "";
			                	
			                	if(colName.equals("COMPANY_CD")) {
			                		value = companyCd;
			                	} else {
				                	if(!xml_path.isEmpty()) {
				                		if(colName.equals("CO_DOC_NO")) {
				                			value = supporter.getValue(xml_path, 0); // 확인서발급번호는 상위 1개만 존재함
				                		} else {
				                			value = supporter.getValue(xml_path, a);
				                		}
				                	}
			                	}
			            		
			                	dataMap.put("COLUMN_NAME", colName);
			                	dataMap.put("COLUMN_TYPE", type);
			                	
			                	log.debug(colName + " : " + type + ", XML path : " + xml_path);
			                	
			                	if(type.equals("4")) {
			                		if(colName.equals("XML_REAL_FILE")) { // XML 파일내용
			                			dataMap.put("COLUMN_VALUE", FileUtil.getBytesFromFile(files[f]));
			                		} else {
				                		if(!value.isEmpty()) {
				                			byte[] byteData = StringUtil.fileBase64Decoder(value);
				                			
				                			dataMap.put("COLUMN_VALUE", byteData);
				                		}
			                		}
			                		
			                		log.debug("byte type = " + dataMap.toString());
			                		
			                		// BYTE 타입의 데이터는 이력 테이블에 등록할 수 없기 때문에 삭제한다.
			                		colAtrMap.put(colName, "");
			                	} else {
			                		// 연락처는 동일 경로로 최대 3가지 정보가 존재함
			                		if(!xml_path.isEmpty()) {
				                		if(colName.equals("EXPORTER_PHONE_NO")) {
				                			value = ""; // wco:Declaration/wco:Manufacturer/wco:Communication/wco:ID 경로의 첫번째값을 초기화시킨 후 원하는 값을 찾도록 함
				                			
			                				for(int c = 0; c < 3; c++) {
				                				if(supporter.getValue("/wco:Declaration/wco:Manufacturer/wco:Communication/wco:TypeID", c).equals("TE")) {
				                					value = supporter.getValue("/wco:Declaration/wco:Manufacturer/wco:Communication/wco:ID", c);
				                					break;
				                				}
				                			}
				                		} else if(colName.equals("EXPORTER_FAX_NO")) {
				                			value = "";
				                			
				                			for(int c = 0; c < 3; c++) {
				                				if(supporter.getValue("/wco:Declaration/wco:Manufacturer/wco:Communication/wco:TypeID", c).equals("FX")) {
				                					value = supporter.getValue("/wco:Declaration/wco:Manufacturer/wco:Communication/wco:ID", c);
				                					break;
				                				}
				                			}
				                		} else if(colName.equals("EXPORTER_EMAIL")) {
				                			value = "";
				                			
				                			for(int c = 0; c < 3; c++) {
				                				if(supporter.getValue("/wco:Declaration/wco:Manufacturer/wco:Communication/wco:TypeID", c).equals("EM")) {
				                					value = supporter.getValue("/wco:Declaration/wco:Manufacturer/wco:Communication/wco:ID", c);
				                					break;
				                				}
				                			}
				                		} else if(colName.equals("PRODUCER_PHONE_NO")) {
				                			value = "";
				                			
			                				for(int c = 0; c < 3; c++) {
				                				if(supporter.getValue("/wco:Declaration/wco:Exporter/wco:Communication/wco:TypeID", c).equals("TE")) {
				                					value = supporter.getValue("/wco:Declaration/wco:Exporter/wco:Communication/wco:ID", c);
				                					break;
				                				}
				                			}
				                		} else if(colName.equals("PRODUCER_FAX_NO")) {
				                			value = "";
				                			
				                			for(int c = 0; c < 3; c++) {
				                				if(supporter.getValue("/wco:Declaration/wco:Exporter/wco:Communication/wco:TypeID", c).equals("FX")) {
				                					value = supporter.getValue("/wco:Declaration/wco:Exporter/wco:Communication/wco:ID", c);
				                					break;
				                				}
				                			}
				                		} else if(colName.equals("PRODUCER_EMAIL")) {
				                			value = "";
				                			
				                			for(int c = 0; c < 3; c++) {
				                				if(supporter.getValue("/wco:Declaration/wco:Exporter/wco:Communication/wco:TypeID", c).equals("EM")) {
				                					value = supporter.getValue("/wco:Declaration/wco:Exporter/wco:Communication/wco:ID", c);
				                					break;
				                				}
				                			}
				                		}
			                		}
			                		
			                		if(colName.equals("XML_FILE_NAME")) { // XML 파일명
			                			value = fname;
			                		}
			                		
			                		dataMap.put("COLUMN_VALUE", value);
			                		
			                		if(log.isDebugEnabled()) log.debug(colName + "[Type:" + type + "] = " + value);
			                	}
			                	
			                	dataList.add(dataMap);
			                }
			            	
			            	inMap.put("COLUMN_LIST", dataList);
			            	
			                // SY 테이블 저장
			                this.insertSourceTable(inMap);
		                }
		                
		            	rstList = this.selectSourceTable(inMap);
		            	
		            	if(rstList.size() > 0) {
		            		batchVo.setBatchStatus("S");
		            		batchVo.setTotalRows(Integer.toString(rstList.size()));
		            		batchVo.setErrorMessage("");
		            	} else {
		            		batchVo.setBatchStatus("E");
		            		batchVo.setTotalRows("0");
		                    batchVo.setErrorMessage(this.getMessage("MSG_NOT_EXIST_DATA", null, batchVo.getLanguage()));
		            	}
		            	
		            	break;
		            }
		            
		            // 백업디렉토리로 이동
		            if(id.equals("TFP_IN_002")) {
		            	String backPath = Constants.APPLICATION_REAL_PATH + "/upload/pass/backup/";
		            	
		            	FileUtil.makeDirectrory(backPath);
		            	
		            	// 이동전에 중복된 파일이 있으면 지우고 생성한다.
		            	File mfile = new File(backPath+fname);
		            	
		            	if(mfile.exists()) {
		            		mfile.delete();
		            	}
		            	
		            	if(FileUtil.fileMove(path, fname, backPath)) {
			            	log.info("moved file(name = " + fname + ", Path = "+path+" > "+backPath);
			            }
		            }
		            
		            if (log.isInfoEnabled()) {
		                log.info("Request to select the results(Interface code = " + batchVo.getInterfaceCode() + " | Result Description = "
		                        + batchVo.getErrorMessage());
		            }
	    		} // end for(int f = 0; f < files.length; f++)
    		} // end if(files.length == 0)
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
