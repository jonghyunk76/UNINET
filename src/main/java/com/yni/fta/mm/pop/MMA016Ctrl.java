package com.yni.fta.mm.pop;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 엑셀 업로드(Excel Upload)
 * 
 * @author carlos
 *
 */
@Controller
public class MMA016Ctrl extends YniAbstractController {
    
    @Resource(name="mmA016")
    private MMA016 mmA016;
    
    /**
     * Excel Upload 메인 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA016_01")
    public ModelAndView mmA016_01(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A016_01", dataMap);
    }
    
    /**
     * Excel Upload 데이터 미리보기 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA016_02")
    public ModelAndView mmA016_02(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A016_02", dataMap);
    }
    
    /**
     * Excel Upload 열정의 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA016_03")
    public ModelAndView mmA016_03(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A016_03", dataMap);
    }
    
    /**
     * Excel Upload 완료 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA016_04")
    public ModelAndView mmA016_04(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A016_04", dataMap);
    }
    
    /**
     * Excel Upload 결과 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/selectExcelData")
    public ModelAndView selectExcelData(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	resultList = mmA016.selectExcelData(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * Excel Upload 실행
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/executeExcelUpload")
    public ModelAndView executeExcelUpload(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = null;
        String message = null;
        
        try {
        	resultMap = mmA016.executeExcelUpload(DataMapHelper.getMap(dataMap)); // 엑셀의 해더정보
        	
        	resultMap.put("SHEET_NAME", StringHelper.null2void(dataMap.get("SHEET_NAME")));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(resultMap, message);
    }
    
    /**
     * Excel 컬럼항목 및 유효성 체크 결과 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/selectExcelColmunList")
    public ModelAndView selectExcelColmunList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	resultList = mmA016.selectExcelColmunList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * Excel 컬럼별 추출 결과 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/selectExcelValueList")
    public ModelAndView selectExcelValueList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	resultList = mmA016.selectExcelValueList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 대상 테이블의 컬럼리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/selectJcoColumnList")
    public ModelAndView selectJcoColumnList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	resultList = mmA016.selectJcoColumnList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 대상 테이블의 컬럼정보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/selectJcoColumnInfo")
    public ModelAndView selectJcoColumnInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = null;
        String message = null;
        
        try {
        	resultMap = mmA016.selectJcoColumnInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(resultMap, message);
    }
    
    /**
     * 이관시킬 대상 컬럼ID 수정
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/updateTargetColumn")
    public ModelAndView updateTargetColumn(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            
        	int cnt = mmA016.updateTargetColumn(map);
            
        	if(cnt < 0) {
        		success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * Excel 원본 데이터 삭제
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/deleteSourceColumn")
    public ModelAndView deleteSourceColumn(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
            
        	int cnt = mmA016.deleteSourceColumn(map);
            
        	if(cnt < 0) {
        		success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * Excel 소스 자동 맵핑
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/executeChangeSource")
    public ModelAndView executeChangeSource(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = null;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
        	
        	resultMap = mmA016.executeChangeSource(pmap); // 엑셀의 해더정보
        	
        	if(!StringHelper.null2void(resultMap.get("O_RETURN")).equals("S")) {
            	message = StringHelper.null2void(resultMap.get("O_RETURNMSG"));
            } else {
            	mmA016.insertExcelData(pmap);
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(resultMap, message);
    }
    
    /**
     * Excel 인터페이스 항목 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_02/selectExcelInterfaceList")
    public ModelAndView selectExcelInterfaceList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	resultList = mmA016.selectExcelInterfaceList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 검증 후 Excel 데이터의 통계
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_04/selectExcelDataStatus")
    public ModelAndView selectExcelDataStatus(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = null;
        String message = null;
        
        try {
        	resultMap = mmA016.selectExcelDataStatus(DataMapHelper.getMap(dataMap));
        	
        	if(!StringHelper.null2void(resultMap.get("O_RETURNMSG")).isEmpty()) {
        		message = StringHelper.null2void(resultMap.get("O_RETURNMSG"));
        	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(resultMap, message);
    }
    
    /**
     * 검증 후 Excel 데이터의 해더정보
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_04/selectExcelHeaderList")
    public ModelAndView selectExcelHeaderList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	resultList = mmA016.selectExcelHeaderList(DataMapHelper.getMap(dataMap)); // 해더정보
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 엑셀업로드 완료 실행
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_01/executeExcelBatch")
    public ModelAndView executeExcelBatch(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map resultMap = null;
        String message = null;
        
        try {
        	resultMap = mmA016.executeExcelBatch(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(resultMap, message);
    }
    
    /**
     * 엑셀 샘플 다운로드
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA016_01/excelSampleDownload")
    public ModelAndView excelSampleDownload(HttpServletRequest req, DataMap dataMap) throws Exception {
    	byte[] datas = null;
        String message = null;
        String fileName = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	
        	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
        	Locale locale = SystemHelper.getLocale(lang);
			String path = FileUtil.getFullPath("file.excel.upload.dir");  // path  = /upload/excel/
        	fileName = StringHelper.null2void(map.get("FILE_NAME"));
        	
        	if(log.isDebugEnabled()) log.debug("Sample file = " + path + fileName);
        	
        	if(path.isEmpty() || fileName.isEmpty()) {
        		message = this.messageSource.getMessage("MSG_EXCEL_NO_DATA", null, locale);
        	} else {
        		datas = FileUtil.getBytesFromFile(new File(path + fileName));
        	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnFileView(datas, fileName, message);
    }
}
