package com.yni.fta.mm.pop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;
import kr.yni.frame.web.action.WebAction;
import net.sf.jazzlib.ZipEntry;
import net.sf.jazzlib.ZipOutputStream;

/**
 * 공통 > 증명서 변경요청
 * 
 * @author YNI-maker
 *
 */
@Controller
public class MMA021Ctrl extends YniAbstractController {

	@Resource(name="mmA021")
	private MMA021 mmA021;
	
	/**
     * 증명서 변경요청 조회 화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA021_01")
    public ModelAndView mmA021_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A021_01", dataMap);
    }
    
    /**
     * 증명서 변경 원산지 상세내역 화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA021_02")
    public ModelAndView mmA021_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A021_02", dataMap);
    }
    
    /**
     * 협력사 확인서 증빙자료 업로드(포괄법)
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA021_03")
    public ModelAndView mmA021_03Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A021_03", dataMap);
    }
    
    /**
     * 협력사 확인서 증빙자료 업로드(개별법)
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value="/mm/pop/mmA021_04")
    public ModelAndView mmA021_04Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A021_04", dataMap);
    }
    
    /**
     * 증명서 변경요청 리스트 조회
     * 
     * @param dataMap DataMap
     * @param req HttpServletRequest
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA021_01/selectMainList")
    public ModelAndView selectMainList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA021.selectMainList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    

    /**
     * 증명서 변경요청 사유 저장
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA021_01/updateReqReason")
    public ModelAndView updateReqReason(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = mmA021.updateReqReason(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 증명서 변경 원산지 상세내역
     * 
     * @param dataMap DataMap
     * @param req HttpServletRequest
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA021_02/selectRecieveCoDetail")
    public ModelAndView selectRecieveCoDetail(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA021.selectRecieveCoDetail(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 증빙파일 목록 조회
     * 
     * @param dataMap DataMap
     * @param req HttpServletRequest
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA021_02/selectEvdcFileList")
    public ModelAndView selectEvdcFileList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = mmA021.selectEvdcFileList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
	 * 증빙파일 목록 다운로드 파일
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
    @RequestMapping("/mm/pop/mmA021_02/selectEvdcDownloadFile")
	public ModelAndView selectEvdcDownloadFile(HttpServletRequest req, DataMap dataMap) throws Exception {
		List resultList = null;
		String message = null;
		String fname = null;
		byte[] data = null;
		byte[] buf = new byte[1024];
		byte[] returnData = null;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		
		try {
			Map map = DataMapHelper.getMap(dataMap);
			Locale locale = SystemHelper.getLocale(StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE));
			
			resultList = mmA021.selectEvdcDownloadFile(map);
			
			if(resultList != null && resultList.size() > 0) {
				fname = StringHelper.null2string(map.get("FILE_NAME"), "CO_Evidence_File_Supplier.zip");
				
				for(int i = 0; i < resultList.size(); i++) {
					Map result = (Map) resultList.get(i);
					
					data = null;
					
					if (result != null) {
						String dataType = StringHelper.null2string(result.get("FILE_OUT_TYPE"), "COLUMN");
		                
						if(dataType.equals("COLUMN")) {
							String db = StringHelper.null2void(this.properties.get("application.db.type"));
			
			                log.debug("Database Type = " + db);
			                
			                if (db.equals("MSSQL") || db.equals("POSTGRESQL")) {
			                	data = (byte[]) result.get("FILE_BIN");
			                } else {
			                	Blob blob = (Blob) result.get("FILE_BIN");
			                    data = blob.getBytes(1, (int) blob.length());
			                }
						} else if(dataType.equals("BYTE")) {
							data = (byte[]) result.get("FILE_BIN");
						}
		            }
					
					if(data == null) continue;
						
					ByteArrayInputStream bais = new ByteArrayInputStream(data);
					
					if(data != null && data.length > 0) {
						String tempFile = ".pdf";
						String revisionStr = this.messageSource.getMessage("REVSN,MSSNM", null, locale);
			            String fileFullName = StringHelper.null2string(result.get("ORIGIN_FILE_NAME"), StringHelper.null2void(result.get("CO_DOC_NO")) + "(" + revisionStr + "-" + StringHelper.null2string(result.get("MODIFY_REQ"), Integer.toString(i)) + ")" + tempFile);
						//String extension = fileFullName.substring(fileFullName.lastIndexOf("."), fileFullName.length());
						//String inFile = StringHelper.null2void(result.get("CO_DOC_NO")) + "(" + revisionStr + "-" + StringHelper.null2string(result.get("MODIFY_REQ"), Integer.toString(i)) + ")" + extension;
						
			            zos.putNextEntry(new ZipEntry(fileFullName));
			            
						int bytes_read = 0;
						while((bytes_read = bais.read(buf)) != -1){
			                zos.write(buf, 0, bytes_read);
						}
						
						if(resultList.size() == 1) {
							fname = fileFullName;
						}
					} 
					
					if(bais != null) bais.close();
				}
				
				if(resultList.size() == 1) {
					returnData = data;
				}
			}
			
			if(baos.size() <= 0) {
				message = this.messageSource.getMessage("MSG_EXCEL_NO_DATA", null, locale);
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		} finally {
			if(zos != null) {
				zos.closeEntry();
				zos.close();
			}
			
			if(baos != null) baos.close();
		}
		
		if(resultList.size() == 1) {
			return WebAction.returnFileView(returnData, fname, message);
		} else {
			return WebAction.returnFileView(baos.toByteArray(), fname, message);			
		}
	}
	
}
