package com.yni.fta.common.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sap.mw.jco.JCO;

import kr.yni.frame.Constants;
import kr.yni.frame.mapper.element.Column;
import kr.yni.frame.mapper.util.JcoMapValidator.DataHelper;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;
import kr.yni.frame.web.upload.FormFile;

public class ExcelFileSupporter extends FileUtil {
	
	private static Log log = LogFactory.getLog(ExcelFileSupporter.class);
	
	/**
     * 엑셀 파일의 유효성 체크
     * 
     * @param formFile FormFile
     */
    public static void checkFormFileForExcel(FormFile formFile) throws Exception {
    	checkFormFileForExcel(formFile, Constants.DEFAULT_LANGUAGE);
    }
    
    /**
     * 엑셀 파일의 유효성 체크
     * 
     * @param formFile FormFile
     */
    public static void checkFormFileForExcel(FormFile formFile, String lang) throws Exception {
    	MessageResource messageSource = MessageResource.getMessageInstance();
        Locale locale = SystemHelper.getLocale(lang);
        
        if(formFile == null) {
            throw new RuntimeException(messageSource.getMessage("MSG_FILE_NOT_FOUND", null, locale));
        }

        String originalExtension = StringHelper.null2void(formFile.getOriginalExtension()).toLowerCase();
        int fileSize = formFile.getFileSize();

        if(!(originalExtension.equals(".xlsx") || originalExtension.equals(".xls"))) {
            throw new RuntimeException(messageSource.getMessage("MSG_EXTENSION_XLSX", null, locale));
        }

        if(Constants.FILE_MAX_UPLOAD_SIZE < fileSize) {
            throw new RuntimeException(messageSource.getMessage("MSG_FILE_SIZE_OVER", null, locale));
        }
    }
    
    /**
     * JCO XML의 이관컬럼명를 구함
     * 
     * @param datas 검증할 데이터(컬럼리스트 정보)
     * @param jcoId 인터페이스ID
     * @param lang 다국어 코드
     * @return
     * @throws Exception
     */
    public static List setTargetColumnName(List datas, String jcoId, String lang) throws Exception {
    	MessageResource messageSource = MessageResource.getMessageInstance();
        Locale locale = SystemHelper.getLocale(lang);
        
        List dataList = new LinkedList();
    	JcoMapHelper jco = new JcoMapHelper(jcoId);
    	List columns = jco.getTableColumnName(0); // 단일 테이블에 대해서만 처리
    	
    	for(int i = 0; i < datas.size(); i++) {
    		Map data = (Map) datas.get(i); // 셀정보 및 값
    		
            String errorMsg = StringHelper.null2void(data.get("ERROR_MSG"));
			
    		boolean errorFlag = true;
    		String colId = StringHelper.null2void(data.get("COLUMN_ID"));
    		
			for(int c = 0; c < columns.size(); c++) {
    			Map column = (Map) columns.get(c);
    			
    			String trans = StringHelper.null2void(column.get("COLUMN_TRANS"));
    			String name = StringHelper.null2void(column.get("COLUMN_NAME"));
    			
    			// Map Name과 일치하는 trans가 있다면 명칭을 변경한다.
    			if(trans.equals(colId)) {
    				String msg = StringHelper.null2void(column.get("COLUMN_MESSAGE"));
    				
    				if(msg.isEmpty()) {
    					msg = StringHelper.null2void(column.get("COLUMN_DESC"));
    				} else {
    					msg = messageSource.getMessage(msg, null, locale);
    				}
    				
    				data.put("TARGET_COLUMN_NAME", msg);
    				errorFlag = false;
    				
    				break;
    			}
    		}
    	
			// 일치하는 Interface정보가 없다면 오류로 처리한다.
			if(errorFlag) {
				data.put("TARGET_COLUMN_NAME", "-");
			}
			
			// 필수항목 누락된 경우 에러메시지를 추가한다.
			StringBuffer rstMsg = new StringBuffer();
			Map checkMap = getRequiredCheck(columns, datas, lang);
			String requiredMsg = StringHelper.null2void(checkMap.get("REQUIRED_NOINPUT_MSG"));
			
			if(!requiredMsg.isEmpty()) {
				rstMsg.append("1.");
				rstMsg.append(requiredMsg);
				rstMsg.append("<br>");
			}
			
			if(!errorMsg.isEmpty()) {
				String label = (rstMsg.length() == 0) ? "1." : "2.";
				
				rstMsg.append(label);
				rstMsg.append(messageSource.getMessage("MSG_UPLOAD_DATA_ERR", null, locale));
				rstMsg.append("<br>");
				rstMsg.append(errorMsg.replaceFirst(", ", ""));
			}
			
			data.put("ERROR_MSG_T", rstMsg.toString());
			
			dataList.add(data);
    	}
    	
    	return dataList;
    }
    
    /**
     * 업로드한 항목에 대해 필수항이 누락된 항목을 검색해서 메시지로 리턴함
     * 
     * @param columns 체크할 테이블 컬럼 정보
     * @param data 엑셀 등록 항목
     * @param lang 다국어 코드
     * @return
     * @throws Exception
     */
    public static Map getRequiredCheck(List columns, List datas, String lang) throws Exception {
    	MessageResource messageSource = MessageResource.getMessageInstance();
        Locale locale = SystemHelper.getLocale(lang);
        
        Map checkMap = new HashMap();
        String rst = null;
        int cnt = 0;
        StringBuffer requiredMsg = new StringBuffer();
    	
    	// 필수항목 중 누락된 항목 조회
    	for(int c = 0; c < columns.size(); c++) {
			Map column = (Map) columns.get(c);
			
			String required = StringHelper.null2void(column.get("COLUMN_REQUIRED"));
			String trans = StringHelper.null2void(column.get("COLUMN_TRANS"));
			
			// 회사코드는 엑셀업로드 시 로그인한 세션정보에 의해 결정되므로 필수여부를 체크할 대상에서 제외됨.
			if(!trans.equals("COMPANY_CD") && required.equals("Y")) {
				boolean errorFlag = true;
				
				for(int l = 0; l < datas.size(); l++) {
		    		Map data = (Map) datas.get(l); // 셀정보 및 값
		    		
		    		String colId = StringHelper.null2void(data.get("COLUMN_ID"));
		    		
		    		if(trans.equals(colId)) {
		    			errorFlag = false;
		    			break;
		    		}
				}
				
				if(errorFlag) {
					String msg = StringHelper.null2void(column.get("COLUMN_MESSAGE"));
    				
    				if(msg.isEmpty()) {
    					msg = StringHelper.null2void(column.get("COLUMN_DESC"));
    				} else {
    					msg = messageSource.getMessage(msg, null, locale);
    				}
    				
					requiredMsg.append(msg);
					requiredMsg.append(", ");
					
					cnt++;
				}
			}
    	}
    	
    	if(requiredMsg.length() > 0) {
        	rst = requiredMsg.toString();
        	
        	rst = rst.substring(0, rst.length()-2);
        	rst = rst + " " + messageSource.getMessage("MSG_IS_REQUIRED_FILED", null, locale);
        }
        
    	checkMap.put("REQUIRED_NOINPUT_MSG", rst);
    	checkMap.put("REQUIRED_NOINPUT_CNT", cnt);
    	
        return checkMap;
    }
    
    /**
     * <p>
     * 엑셀에서 추출한 데이터의 Map의 Name이 JCO.trans인 경우, JCO.name으로 Name을 변경한다.<br>
     * <p>
     * [JCO XML의 테이블.컬럼 정보]<br>
     * colAtrMap.put("COLUMN_NAME", name);<br>
	 * colAtrMap.put("COLUMN_TYPE", type);<br>
	 * colAtrMap.put("COLUMN_LENG", leng);<br>
	 * colAtrMap.put("COLUMN_DECIMALS", decimals);<br>
	 * colAtrMap.put("COLUMN_FORMAT", format);<br>
	 * colAtrMap.put("COLUMN_REQUIRED", required);<br>
	 * colAtrMap.put("COLUMN_DEFAULTVAL", defaultval);<br>
	 * colAtrMap.put("COLUMN_TRANS", trans);<br>
	 * colAtrMap.put("COLUMN_DESC", desc);<br>
	 * colAtrMap.put("COLUMN_BASECODE", basecode);<br>
	 * colAtrMap.put("COLUMN_ALERT", alert);<br>
	 * colAtrMap.put("COLUMN_MESSAGE", message);<br>
     * 
     * @param datas 엑셀에서 추출한 데이터
     * @param jcoId 인터페이스 ID
     * @param lang 언어
     * @return
     * @throws Exception
     */
    public static List changeColumnNameToTrans(List datas, String jcoId, String lang) throws Exception {
    	return changeColumnNameToTrans(datas, jcoId, lang, null, null);
    }
    
    /**
     * <p>
     * 엑셀에서 추출한 데이터의 Map의 Name이 JCO.trans인 경우, JCO.name으로 Name을 변경한다.<br>
     * 
     * @param datas 엑셀에서 추출한 데이터
     * @param jcoId 인터페이스 ID
     * @param lang 언어
     * @param itemList 품목리스트(자산구분 포함) : 데이터가 있는 경우에만 자산부분이 판매유형으로 등록됨
     * @return
     * @throws Exception
     */
    public static List changeColumnNameToTrans(List datas, String jcoId, String lang, List itemList) throws Exception {
    	return changeColumnNameToTrans(datas, jcoId, lang, itemList, null);
    }
    /**
     * <p>
     * 엑셀에서 추출한 데이터의 Map의 Name이 JCO.trans인 경우, JCO.name으로 Name을 변경한다.<br>
     * 
     * @param datas 엑셀에서 추출한 데이터
     * @param jcoId 인터페이스 ID
     * @param lang 언어
     * @param itemList 품목리스트(자산구분 포함) : 데이터가 있는 경우에만 자산부분이 판매유형으로 등록됨
     * @return
     * @throws Exception
     */
    public static List changeColumnNameToTrans(List datas, String jcoId, String lang, List itemList, List divList) throws Exception {
    	MessageResource messageSource = MessageResource.getMessageInstance();
        Locale locale = SystemHelper.getLocale(lang);
        
    	JcoMapHelper jco = new JcoMapHelper(jcoId);
    	List columns = jco.getTableColumnName(0); // 단일 테이블에 대해서만 처리
    	String itemCd_1 = "";
    	
    	for(int l = 0; l < datas.size(); l++) {
    		Map data = (Map) datas.get(l); // 셀정보 및 값
    		
    		boolean errorFlag = true;
    		String colId = StringHelper.null2void(data.get("COLUMN_ID")); // ATTRIBUTE + 컬럼순번
    		boolean itemTypeCheck = false;
    		
			for(int c = 0; c < columns.size(); c++) {
    			Map column = (Map) columns.get(c);
    			
    			String trans = StringHelper.null2void(column.get("COLUMN_TRANS")); // ATTRIBUTE + 순번
    			String name = StringHelper.null2void(column.get("COLUMN_NAME"));   // 컬럼명
    			String defaultVal = StringHelper.null2void(column.get("COLUMN_DEFAULTVAL"));
    			String desc = StringHelper.null2void(column.get("COLUMN_DESC"));
    			
    			// Map Name과 일치하는 trans가 있다면 명칭을 변경한다.
    			if(trans.equals(colId)) {
    				String value = StringHelper.null2void(data.get("COLUMN_VALUE"));
    				String month = StringHelper.null2void(data.get("CLOSING_MONTH"));
    				
    				if(name.equals("PRODUCT_ITEM_CD")) itemCd_1 = value;
    				
    				// 엑셀에 등록된 값이 없으면 기본값으로 변경한다.
    				if(value.isEmpty()) {
    					if(!defaultVal.isEmpty()) {
	    					value = defaultVal;
	    					
	    					data.put("COLUMN_VALUE", value);
	    				} else {
	    					// 예외) 매출원장 인터페이스 시 시스템 설정에서 판매유형을 품목의 자산구분으로 잡을 경우 판매유형을 자동으로 입력시킴(YNI-Maker, 2017.06.07)
	    					if((jcoId.equals("RPT_SD_005") || jcoId.equals("RPT_SD_008")) &&
	    							name.equals("ITEM_TYPE") && itemList != null && itemList.size() > 0) {
	    						
	    						itemTypeCheck = true;
	    						
	    						if(!itemCd_1.isEmpty()) {
		    						// 품목정보에서 자산구분을 판매유형으로 등록한다.
		    						for(int i = 0; i < itemList.size(); i++) {
		    							Map itemMap = (Map) itemList.get(i);
		    							
		    							String itemCd_2 = StringHelper.null2void(itemMap.get("ITEM_CD"));
		    							
		    							if(itemCd_1.equals(itemCd_2)) {
		    								value = StringHelper.null2void(itemMap.get("ITEM_TYPE"));
		    								
		    								data.put("COLUMN_VALUE", value);
		    								
		    								break;
		    							}
		    						}
	    						}
	    					}
	    				}
    				}
    				
    				String error = StringHelper.null2void(getSourceCheckForType(column, value, lang, month, itemTypeCheck));
    				
    				// 사업부 코드 컬럼인 경우에는 실제 등록된 사업부인지 체크한다.(2018.05.31) 
    				if(!value.isEmpty()) {
	    				if(divList != null && name.equals("DIVISION_CD") && divList.size() > 0) {
	    					boolean divState = false;
	    					
	    					for(int d = 0; d < divList.size(); d++) {
	    						Map divMap = (Map) divList.get(d);
	    						
	    						if(StringHelper.null2void(divMap.get("DIVISION_CD")).equals(value)) {
	    							divState = true;
	    							break;
	    						}
	    					}
	    					
	    					if(!divState) { // 사업부가 존재하지 않다면 에러메시지를 띄운다.
	    						error += " • " + messageSource.getMessage("SYS, UNREG, CODE", null, locale) + "<br>";
	    					}
	    				}
    				}
    				
    				if(error.isEmpty()) {
    					data.put("ERROR_CODE", "S");
    					data.put("ERROR_MSG", "");
    				} else {
    					data.put("ERROR_CODE", "F");
    					data.put("ERROR_MSG", error);
    				}
    				
    				errorFlag = false;
    				
    				break;
    			}
    		}
			
			// 일치하는 Interface정보가 없다면 오류로 처리한다.
			if(errorFlag) {
				data.put("ERROR_CODE", "U");
				data.put("ERROR_MSG", " • " + messageSource.getMessage("TARGT, TXT_COLUMN, OMISS", null, locale));
			}
    	}
    	
    	return datas;
    }
    
    /**
     * Source값의 유효성을 체크한 후 에러메시지 리턴
     * 
     * @param col XML의 컬럼 객체
     * @param source 체크할 대상 값
     * @param locale 설정된 언어
     * @param month 마감월 체크
     * @param itemTypeSource 판매유형의 소스(S:현상태의 매출원장, I:품목의 자산구분
     * @return validation 체크 결과(오류가 없는 경우 빈값을 리턴한다.)
     */
    private static String getSourceCheckForType(Map col, String source, String lang, String month, boolean itemTypeCheck) {
    	MessageResource messageSource = MessageResource.getMessageInstance();
        Locale locale = SystemHelper.getLocale(lang);
        
        String rst = null;
    	StringBuffer resultStr = new StringBuffer();
        
        int type = StringHelper.null2zero(col.get("COLUMN_TYPE"));
        int leng = StringHelper.null2zero(col.get("COLUMN_LENG"), 500);
        int decimals = StringHelper.null2zero(col.get("COLUMN_DECIMALS"));
        String format = StringHelper.null2void(col.get("COLUMN_FORMAT"));
        String desc = StringHelper.null2void(col.get("COLUMN_DESC"));
        String required = StringHelper.null2void(col.get("COLUMN_REQUIRED"));
        String basecode = StringHelper.null2void(col.get("COLUMN_BASECODE"));  // 마감월 검증을 위한 코드값(=YYYYMM)
        
        if(source != null && !source.isEmpty()) {
            if(type == JCO.TYPE_DATE) { // 날짜 형식인 경우
                if(!DataHelper.isDate(source, format)) {
                    resultStr.append(" • " + messageSource.getMessage("ERROR_INVALID_DATE", new Object[]{messageSource.getMessage(desc, null, locale)}, locale) + " ");
                }
                if(basecode.equals("YYYYMM") && !month.isEmpty()) { // 마감월 체크
                	if(source.length() > 5) {
	                	if(!source.substring(0, 6).equals(month)) {
	                		resultStr.append(" • " + messageSource.getMessage("MSG_INCORRECT_YYYYMM", new Object[]{month}, locale) + " ");
	                	}
                	}
                }
            } else if(type == JCO.TYPE_FLOAT || type == JCO.TYPE_NUM) { // 숫자타입인 경우
                if(!DataHelper.isNumberValid(source)) {
                    resultStr.append(" • " + messageSource.getMessage("ERROR_INVALID_NUMBER", new Object[]{messageSource.getMessage(desc, null, locale)}, locale) + " ");
                }
                if(!DataHelper.compareLength(source, leng, decimals)) {
                    resultStr.append(" • " + messageSource.getMessage("ERROR_INVALID_LENGTH", new Object[]{messageSource.getMessage(desc, null, locale)}, locale) + "[max:"+leng+" | decimal:"+decimals+"] ");
                }
            } else if(type == JCO.TYPE_XSTRING) { // json 타입
                ; // json타입은 체크하지 않음
            } else if(type == JCO.TYPE_BYTE) { // byte 타입
                ; // json타입은 체크하지 않음
            } else { // 기타는 문자타입으로 처리
            	if(!DataHelper.compareLength(source, leng)) {
                    resultStr.append(" • " + messageSource.getMessage("ERROR_INVALID_LENGTH", new Object[]{messageSource.getMessage(desc, null, locale)}, locale) + "[max:"+leng+"] ");
                }
            }
        } else {
        	if(!DataHelper.compareRequired(source, required)) {
                resultStr.append(" • " + messageSource.getMessage("ERROR_REQUIRED_YN", new Object[]{messageSource.getMessage(desc, null, locale)}, locale) + " ");
                
                if(itemTypeCheck) {
                	resultStr.append("(" + messageSource.getMessage("LTIT, TXT_ASSETS_TYPE, OMISS", null, locale) + ") ");
                }
            }
        }
        
        if(resultStr.length() > 0) {
        	rst = resultStr.toString();
        	
        	rst = rst.substring(0, rst.length()-1) + "<br>";
        }
        	
        return rst;
    }
	
    /**
     * JCO XML에 정의된 테이블 정보 리턴 
     * 
     * @param jcoId 인터페이스ID
     * @param lang 표시할 언어코드
     * @return
     * @throws Exception
     */
    public static List getJcoColumns(String jcoId, String lang) throws Exception {
    	MessageResource messageSource = MessageResource.getMessageInstance();
        Locale locale = SystemHelper.getLocale(lang);
        
        List colList = new LinkedList();
        Map colMap = new LinkedHashMap();
        JcoMapHelper jco = new JcoMapHelper(jcoId);
    	List columns = jco.getTableColumnName(0); // 단일 테이블에 대해서만 처리
    	
    	// 첫 콤보에는 선택안함을 위치시킴
    	colMap.put("COLUMN_TRANS", "");
    	colMap.put("COLUMN_DESC", messageSource.getMessage("NOSEL", null, locale));
    	
    	colList.add(colMap);
    	
    	for(int c = 0; c < columns.size(); c++) {
			Map column = (Map) columns.get(c);
			
			String trans = StringHelper.null2void(column.get("COLUMN_TRANS"));
			String msg = StringHelper.null2void(column.get("COLUMN_MESSAGE"));
			
			if(msg.isEmpty()) {
				msg = StringHelper.null2void(column.get("COLUMN_DESC"));
			} else {
				msg = messageSource.getMessage(msg, null, locale);
			}
			
			column.put("COLUMN_DESC", msg);
			
			colMap = new LinkedHashMap();
			
			if(!trans.equals("COMPANY_CD")) { // 엑셀업로드시에는 회사코드를 default로 입력하므로 항목에서 제거함.
				colMap.putAll(column);
				
				colList.add(colMap);
			}
    	}
    	
    	return colList;
    }
    
    /**
     * JCO XML에 정의된 테이블 정보 리턴 
     * 
     * @param jcoId 인터페이스ID
     * @param name 검색할 컬럼명
     * @param lang 표시할 언어코드
     * @return
     * @throws Exception
     */
    public static Map getJcoColumnInfo(String jcoId, String name, String lang) throws Exception {
    	MessageResource messageSource = MessageResource.getMessageInstance();
        Locale locale = SystemHelper.getLocale(lang);
        
        Map colMap = new LinkedHashMap();
        JcoMapHelper jco = new JcoMapHelper(jcoId);
    	List columns = jco.getTableColumnName(0); // 단일 테이블에 대해서만 처리
    	
    	for(int c = 0; c < columns.size(); c++) {
			Map column = (Map) columns.get(c);
			
			String trans = StringHelper.null2void(column.get("COLUMN_TRANS"));
			
			if(trans.equals(name)) {
				String msg = StringHelper.null2void(column.get("COLUMN_MESSAGE"));
				int type = StringHelper.null2zero(column.get("COLUMN_TYPE"));
				String typeName = "";
				String required = StringHelper.null2void(column.get("COLUMN_REQUIRED"));
				String requiredName = "";
				
				if(msg.isEmpty()) {
					msg = StringHelper.null2void(column.get("COLUMN_DESC"));
				} else {
					msg = messageSource.getMessage(msg, null, locale);
				}
				
				if(type == JCO.TYPE_DATE) { // 날짜 형식인 경우
					typeName = messageSource.getMessage("DTE", null, locale);
	            } else if(type == JCO.TYPE_FLOAT || type == JCO.TYPE_NUM) { // 숫자타입인 경우
	            	typeName = messageSource.getMessage("NUM", null, locale);
	            } else if(type == JCO.TYPE_XSTRING) { // json 타입
	            	typeName = messageSource.getMessage("BYTE, STR", null, locale);
	            } else if(type == JCO.TYPE_BYTE) { // byte 타입
	            	typeName = messageSource.getMessage("BYTE", null, locale);
	            } else { // 기타는 문자타입으로 처리
	            	typeName = messageSource.getMessage("STR", null, locale);
	            }
				
				if(required.equals("Y")) {
					requiredName = messageSource.getMessage("REQUD,ITEMS", null, locale);
				} else {
					requiredName = messageSource.getMessage("NOREQ", null, locale);
				}
				
				column.put("COLUMN_REQUIRED_NAME", requiredName);
				column.put("COLUMN_TYPE_NAME", typeName);
				column.put("COLUMN_DESC", msg);
				
				colMap.putAll(column);
				
				break;
			}
    	}
    	
    	return colMap;
    }
    
    /**
     * 엑셀에서 데이터그리드의 보여줄 해더정보를 구한다.
     *  
     * @param headers 해더에 보여줄 컬럼 리스트
     * @param jcoId 인터페이스 ID
     * @param eflag 에러정보 표시여부
     * @param lang 다국어 국가코드
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> getExcelHeaderList(List headers, String jcoId, String eflag,String lang) throws Exception {
    	MessageResource messageSource = MessageResource.getMessageInstance();
        Locale locale = SystemHelper.getLocale(lang);
        
        List<Map<String, Object>> reList = new LinkedList();
    	Map colMap = null;
    	JcoMapHelper jco = new JcoMapHelper(jcoId);
    	List columns = jco.getTableColumnName(0); // 단일 테이블에 대해서만 처리
    	
    	if("Y".equals(eflag)) {
    		colMap = new LinkedHashMap();
        	
        	colMap.put("field", "ERROR_NAME");
        	colMap.put("title", messageSource.getMessage("VFCTN,RSULT", null, locale));
        	colMap.put("width", 80);
        	colMap.put("align", "center");
        	colMap.put("halign", "center");
        	colMap.put("sortable", "true");
        	colMap.put("hidden", "false");
        	colMap.put("editor", "null");
        	colMap.put("checkbox", "null");
        	colMap.put("rowspan", "0");
        	colMap.put("colspan", "0");
        	colMap.put("frozen", "false");
        	colMap.put("styler", "null");
        	colMap.put("cell", ""); // 셀번호를 찾기위한 참조값
        	
        	reList.add(colMap);
        	
        	colMap = new LinkedHashMap();
        	
        	colMap.put("field", "ERROR_MSG");
        	colMap.put("title", messageSource.getMessage("TXT_ERROR_MESSAGE", null, locale));
        	colMap.put("width", 150);
        	colMap.put("align", "left");
        	colMap.put("halign", "center");
        	colMap.put("sortable", "true");
        	colMap.put("hidden", "false");
        	colMap.put("editor", "null");
        	colMap.put("checkbox", "null");
        	colMap.put("rowspan", "0");
        	colMap.put("colspan", "0");
        	colMap.put("frozen", "false");
        	colMap.put("styler", "null");
        	colMap.put("cell", ""); // 셀번호를 찾기위한 참조값
        	
        	reList.add(colMap);
    	}
    	
    	for(int l = 0; l < headers.size(); l++) {
    		Map data = (Map) headers.get(l); // 셀정보 및 값
    		
    		String colId = StringHelper.null2void(data.get("COLUMN_ID"));
    		
			for(int c = 0; c < columns.size(); c++) {
    			Map column = (Map) columns.get(c);
    			
    			String trans = StringHelper.null2void(column.get("COLUMN_TRANS"));
    			
    			// Map Name과 일치하는 trans가 있다면 명칭을 변경한다.
    			if(trans.equals(colId)) {
    				String name = StringHelper.null2void(column.get("COLUMN_NAME"));
    				String msg = StringHelper.null2void(column.get("COLUMN_MESSAGE"));
    				String colName = StringHelper.null2void(column.get("COLUMN_NAME"));
                	String type = StringHelper.null2void(column.get("COLUMN_TYPE"));
                	String required = StringHelper.null2void(column.get("COLUMN_REQUIRED"));
                	int length = StringHelper.null2zero(column.get("COLUMN_LENG")) * 10;
                	String align = "center";
                	
                	if(type.equals("7")) align = "right";
                	if(length < 100) length = 100;
                	if(length > 150) length = 150;
                	if(msg.isEmpty()) {
    					msg = StringHelper.null2void(column.get("COLUMN_DESC"));
    				} else {
    					msg = messageSource.getMessage(msg, null, locale);
    				}
                	
                	if(required.equals("Y")) {
                		msg = "* " + msg;
                	}
                	
    				colMap = new LinkedHashMap();
    	        	
    				colMap.put("field", trans);
                	colMap.put("title", msg);
                	colMap.put("width", Integer.toString(length));
                	colMap.put("align", align);
                	colMap.put("halign", "center");
                	colMap.put("sortable", "true");
                	colMap.put("hidden", "false");
                	colMap.put("editor", "null");
                	colMap.put("checkbox", "null");
                	colMap.put("rowspan", "0");
                	colMap.put("colspan", "0");
                	colMap.put("frozen", "false");
                	colMap.put("styler", "null");
    	        	
    	        	reList.add(colMap);
    	        	
    				break;
    			}
    		}
    	}
    	
    	return reList;
    }
    
    /**
     * JCO에서 날짜 또는 숫자 타입으로 지정된 경우 특정 문자열을 치환시킨 후 리턴한다.
     * 
     * @param jcoId 인터페이스 ID
     * @param columnName trans속성의 컬럼명
     * @param value 변경할 값
     * @return
     * @throws Exception
     */
    public static String getJcoChangeValue(String jcoId, String columnName, String value) throws Exception {
    	String rstVal = null;
    	
    	JcoMapHelper jco = new JcoMapHelper(jcoId);
    	List columns = jco.getTableColumnName(0); // 단일 테이블에 대해서만 처리
    	
    	for(int c = 0; c < columns.size(); c++) {
			Map column = (Map) columns.get(c);
			
			String trans = StringHelper.null2void(column.get("COLUMN_TRANS"));
			
			if(trans.equals(columnName)) {
				String msg = StringHelper.null2void(column.get("COLUMN_MESSAGE"));
				int type = StringHelper.null2zero(column.get("COLUMN_TYPE"));
				
				if(type == JCO.TYPE_DATE) { // 날짜 형식인 경우
					rstVal = value.replaceAll("-", "");
				} else if(type == JCO.TYPE_FLOAT || type == JCO.TYPE_NUM) { // 숫자타입인 경우
					rstVal = value.replaceAll(",", "");
	            } else {
	            	rstVal = value;
	            }
				
				break;
			}
    	}
    	
    	return (rstVal == null)?value:rstVal;
    }
    
}
