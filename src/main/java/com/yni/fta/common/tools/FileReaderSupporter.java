package com.yni.fta.common.tools;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.record.RecordFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import com.yni.fta.common.Consistent;

import kr.yni.frame.exception.FrameException;
import kr.yni.frame.reader.FileReader;
import kr.yni.frame.resources.MessageResource;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class FileReaderSupporter {
    
    private static Log log = LogFactory.getLog(FileReaderSupporter.class);
    
    private FileReader reader;
    
    public FileReaderSupporter() {}
    
    /**
     * <code>FileReader</code>을 설정하는 클래스 생성자 메소드
     * @param reader
     */
    public FileReaderSupporter(FileReader reader) {
        this.reader = reader;
    }
    
    /**
     * 이미 처리된 파일의 워크시트 리스트를 구한다.
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getSheet() throws Exception {
    	return this.getSheet(null, null);
    }
    
    /**
     * 디렉토리 하위의 모든 파일 목록을 리턴한다.
     * 
     * @param path  검색할 경로
     * @param ext   검색할 파일 확장자
     * @return 파일리스트
     * @throws Exception
     */
    public File[] getPathAllFileList(String path, String[] ext) throws Exception {
    	String[] fileArray = FileUtil.getFilenamesUnder(path, ext, false); // 임시디렉토리 하위를 모두 확인해야 하도록 수정할 것
        File[] fileList = new File[fileArray.length];
        
    	for(int i = 0; i < fileArray.length; i++) {
    		fileList[i] = new File(fileArray[i]);
    	}
    	
    	return fileList;
    }
    
    
    /**
     * 지정된 파일의 워크시트 리스트를 구한다.
     * 
     * @param fpath 파일 디렉토리 경로(값이 null인 경우 Temp디렉토리로 자동 설정됨)
     * @param fname 파일명
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getSheet(String fpath, String fname) throws Exception {
    	if(reader == null && !StringHelper.null2void(fname).isEmpty()) {
            reader = FileUtil.getFileReader(fname);
        }
    	
    	List sheetList = new LinkedList();
    	
    	Workbook workbook = this.reader.getWorkbook(FileUtil.getFile(fpath, fname));
    	int numSheets = workbook.getNumberOfSheets();
    	
    	for(int i = 0; i < numSheets; i++) {
    		Map map = new HashMap();
    		
    		map.put("CODE", workbook.getSheetName(i));
    		map.put("NAME", workbook.getSheetName(i));
    		
    		sheetList.add(map);
    	}
    	
    	return sheetList;
    }
    
    /**
     * <p>
     * 지정된 파일을 읽어 List로 반환한다.
     * </p>
     * @param fname 파일명
     * @return
     * @throws Exception
     */
    public List getContents(String fname) throws Exception {
        return this.getContents(null, fname, 0, null, false);
    }
    
    /**
     * <p>
     * 지정된 파일을 읽어 List로 반환한다.
     * </p>
     * @param fname 파일명
     * @param srow  읽기 시작할 row 수
     * @return
     * @throws Exception
     */
    public List getContents(String fname, int srow) throws Exception {
        return this.getContents(null, fname, srow, null, false);
    }
    
    /**
     * <p>
     * 지정된 파일을 읽어 List로 반환한다.
     * </p>
     * @param fname 파일명
     * @param srow  읽기 시작할 row 수
     * @param sheet 시트명
     * @return
     * @throws Exception
     */
    public List getContents(String fname, int srow, String sheet) throws Exception {
        return this.getContents(null, fname, srow, sheet, false);
    }
    
    /**
     * <p>
     * 지정된 파일을 읽어 List로 반환한다.
     * </p>
     * @param fname 파일명
     * @param srow  읽기 시작할 row 수
     * @param sheet 시트명
     * @param all   비어있는 열을 포함할지 여부(default:false)
     * @return
     * @throws Exception
     */
    public List getContents(String fname, int srow, String sheet, boolean all) throws Exception {
        return this.getContents(null, fname, srow, sheet, all);
    }
    
    /**
     * <p>
     * 지정된 파일을 읽어 List로 반환한다.
     * </p>
     * @param fpath 파일 디렉토리 경로(값이 null인 경우 Temp디렉토리로 자동 설정됨)
     * @param fname 파일명
     * @param srow  읽기 시작할 row 수
     * @param sheet 시트명
     * @return
     * @throws Exception
     */
    public List getContents(String fpath, String fname, int srow, String sheet) throws Exception {
    	return this.getContents(fpath, fname, srow, sheet, false);
    }
    
    /**
     * <p>
     * 지정된 파일을 읽어 List로 반환한다.
     * </p>
     * @param fpath 파일 디렉토리 경로(값이 null인 경우 Temp디렉토리로 자동 설정됨)
     * @param fname 파일명
     * @param srow  읽기 시작할 row 수
     * @param sheet 시트명
     * @param all   비어있는 열을 포함할지 여부(default:false)
     * @return
     * @throws Exception
     */
    public List getContents(String fpath, String fname, int srow, String sheet, boolean all) throws Exception {
        List resultList = new LinkedList();
        List sourceList = null;
        
        if(reader == null) {
            reader = FileUtil.getFileReader(fname);
        }
        
        log.debug("file path = " + fpath + ", name = " + fname + ", sheet = " + sheet + " >> " + FileUtil.getFile(fpath, fname));
        
        if("Smelter List".equals(sheet)) {
            sourceList = reader.read(FileUtil.getFile(fpath, fname), srow, sheet, true);
        } else {
            sourceList = reader.read(FileUtil.getFile(fpath, fname), srow, sheet);
        }
        
        log.debug("excel total rows = " + sourceList.size());
        
        for(int i = 0; i < sourceList.size(); i++) {
            Map tmpMap = (Map) sourceList.get(i);
            String value = "";
            String key = "";
            
            for (Iterator it = tmpMap.entrySet().iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                key = StringHelper.null2void(entry.getKey());
                
                if(i == 0) log.debug("key name = " + key);
                
                if(!key.equals("ROW_HEIGHT")) {
	                if("Smelter List".equals(sheet) || "Product List".equals(sheet)) {
	                    if(key.startsWith("B") || key.startsWith("C") || key.startsWith("D")) {
	                        value += StringHelper.null2void(tmpMap.get(key)).trim();
	                        
	                        if(!value.isEmpty()) break;
	                    }
	                } else {
	                    value += StringHelper.null2void(tmpMap.get(key)).trim();
	                    
	                    if(!value.isEmpty()) break;
	                }
                }
            }
            
//            log.debug("value size = " + value.length());
            
            if((!value.isEmpty()) || all) {
                resultList.add(tmpMap);
            }
        }
        
        return resultList;
    }
    
    /**
     * <p>
     * <code>List<Map<String, Object>></code>안 첨부된 데이터를 지정된 파일로 변환한다.<br>
     * FileReader가 엑셀의 SS타입인 경우, CSV파일로 저장한다.
     * </p>
     * @param contents 파일에 기록한 Data
     * @param fpath    파일 디렉토리 경로(값이 null인 경우 Temp디렉토리로 자동 설정됨)
     * @param fname    파일명
     * @throws Exception
     */
    public void write(List contents, String fname) throws Exception {
        this.write(contents, null, fname);
    }
    
    /**
     * <p>
     * <code>List<Map<String, Object>></code>안 첨부된 데이터를 지정된 파일로 변환한다.<br>
     * FileReader가 엑셀의 SS타입인 경우, CSV파일로 저장한다.
     * </p>
     * @param contents 파일에 기록한 Data
     * @param fpath    파일 디렉토리 경로(값이 null인 경우 Temp디렉토리로 자동 설정됨)
     * @param fname    파일명
     * @throws Exception
     */
    public void write(List contents, String fpath, String fname) throws Exception {
        if(reader == null) {
            reader = FileUtil.getFileReader(fname);
        }
        
        reader.write(contents, FileUtil.getFile(fpath, fname));
    }
    
    /**
     * <p>
     * 지정된 파일이 사용가능한지 유효성을 체크한다.
     * </p>
     * @param fname 파일명
     * @param lang 다국어 코드
     * @return
     * @throws Exception
     */
    public boolean validation(String fname, String lang) throws Exception {
        return this.validation(null, fname, lang);
    }
    
    /**
     * <p>
     * 지정된 파일이 사용가능한지 유효성을 체크한다.
     * </p>
     * @param fpatn 파일 디렉토리 경로
     * @param fname 파일명
     * @param lang 다국어 코드
     * @return
     * @throws Exception
     */
    public boolean validation(String fpath, String fname, String lang) throws Exception {
        MessageResource messageSource = MessageResource.getMessageInstance();
        Locale locale = SystemHelper.getLocale(lang);
        boolean sucess = true;
        
        try {
            if(reader == null) {
                reader = FileUtil.getFileReader(fname);
            }
            
            reader.read(FileUtil.getFile(fpath, fname), 0);
        } catch(EncryptedDocumentException en) { // 비밀번호와 같은 보안으로 잠겨있는 문서
            sucess = false;
            throw new FrameException(messageSource.getMessage(Consistent.MESSAGE_NUMBER_E4, null, locale), en);
        } catch(RecordFormatException en) { // 엑셀에 입력된 값에 잘 못된 format/data가 존재 
            sucess = false;
            throw new FrameException(messageSource.getMessage(Consistent.MESSAGE_NUMBER_E5, null, locale), en);
        } catch(POIXMLException ie) {  // 문서 paring오류
            sucess = false;
            throw new FrameException(messageSource.getMessage(Consistent.MESSAGE_NUMBER_E13, null, locale), ie);
        } catch(Exception ep) { // 기타 다른 오류
            sucess = false;
            throw new FrameException(messageSource.getMessage(Consistent.MESSAGE_NUMBER_E6, null, locale), ep);
        }
        
        return sucess;
    }
    
    /**
     * 엑셀의 cell단위로 validation을 체크하기 위해 List를 Map으로 변환시킨다.<br>
     * (Map의 명칭은 cell명칭으로 정의됨)
     * 
     * @param rows 엑셀데이터
     * @return cell명칭으로 나열된 Map정보
     * @throws Exception
     */
    public Map getCellMapForRows(List rows) throws Exception {
        Map fileMap = new LinkedHashMap();
        
        for(int i = 0; i < rows.size(); i++) {
            Map tmpMap = (Map) rows.get(i);
            String value = "";
            
            // 각 row에 값이 하나도 없으면 List에 담지 않도록 한다.
            for (Iterator it = tmpMap.entrySet().iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = StringHelper.null2void(entry.getKey());
                
                if(!key.equals("ROW_HEIGHT")) {
	                value += StringHelper.null2void(tmpMap.get(key)).trim();
	                if(!value.isEmpty()) break;
                }
            }
           
            if(!value.isEmpty()) {
                fileMap.putAll(tmpMap);
            }
        }
        
        return fileMap;
    }
    
    /**
     * 엑셀에서 데이터그리드의 보여줄 해더정보를 구한다.
     *  
     * @param map 파일 데이터
     * @param view 해더 표시방법(value:1열의 값, cell:엑셀의 셀번호)
     * @return
     * @throws Exception
     */
    public List getFileHeaderList(Map map, String view) throws Exception {
    	return this.getFileHeaderList(map, view, false);
    }
    
    /**
     * 엑셀에서 데이터그리드의 보여줄 해더정보를 구한다.
     *  
     * @param map 파일 데이터
     * @param view 해더 표시방법(value:1열의 값, cell:엑셀의 셀번호)
     * @param row 셀번호 표시여부
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getFileHeaderList(Map map, String view, boolean row) throws Exception {
    	List<Map<String, Object>> reList = new LinkedList();
    	Map colMap = null;
    	int no = 1;
    	
    	if(row) {
    		colMap = new LinkedHashMap();
        	
        	colMap.put("field", "ROW_NO");
        	colMap.put("title", "Row");
        	colMap.put("width", 60);
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
    	}
    	
    	for(Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            
            if(entry.getKey().equals("ROW_HEIGHT")) continue; // 통관 표준통관보고서 분석을 위한 ROW_HEIGHT 속성은 해더에서 제외시킴(2020-12-17)
            if(log.isDebugEnabled()) log.debug("Excel : " + entry.getKey() + " = " + map.get(entry.getKey()));
            
            String key = StringHelper.null2void(entry.getKey());
            String val = StringHelper.null2void(map.get(entry.getKey()));
            
            if(!key.isEmpty()) {
	            int length = 100;
	        	String align = "left";
	        	String msg = null;
	        	String field = "ATTRIBUTE";
	        	
	        	if("cell".equals(view)) {
	        		msg = key.substring(0, key.length()-1);
	        	} else {
	        		if(val.isEmpty()) continue;
	        		
	        		msg = val;
	        	}
	        	
	        	String id = ((no < 10)?"0":"")+no;
        	
	        	colMap = new LinkedHashMap();
	        	
	        	colMap.put("field", field+id);
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
	        	colMap.put("cell", key.substring(0, key.length()-1)); // 행번호를 찾기위한 참조값
	        	
	        	reList.add(colMap);
	        	
	        	no++;
            }
    	}
    	
    	return reList;
    }
    
}
