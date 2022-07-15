package com.yni.fta.mm.pop;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.common.tools.HubSupporter;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 파일뷰어
 * 
 * @author carlos
 *
 */
@Controller
public class MMA028Ctrl extends YniAbstractController {

    /**
     * 파일뷰어 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA028_01")
    public ModelAndView mmA028_01Move(DataMap dataMap) throws Exception {
    	// FTA HUB 송/수신 파일을 처리하기 위해 자동으로 경로를 찾아 요청화면으로 보낸다.
    	Map map = dataMap.getMap();
    	String filePath = StringHelper.null2void(dataMap.get("FILE_PATH"));
    	String fileName = StringHelper.null2void(dataMap.get("FILE_NAME"));
    	String flag = StringHelper.null2void(dataMap.get("SEND_RECEIVE_FLAG")); // 송신(S), 수신(R)
    	String docType = StringHelper.null2void(dataMap.get("DOCUMENT_TYPE_CD")); // FTA HUB 문서타입코드
    	String runType = StringHelper.null2void(dataMap.get("BATCH_EXE_YN")); // 배치실행여부(처리전:0, 처리후:1)
    	String vendorHubID = StringHelper.null2void(dataMap.get("VENDOR_CD"));
    	String issueDate = StringHelper.null2void(dataMap.get("ISSUE_DOC_CREATE_DATE")); // 제출일자
    	
    	if(filePath.isEmpty() && !fileName.isEmpty()) {
    		if(runType.equals("Y")) {
    			map.put("FILE_PATH", HubSupporter.getConfigFilePath(flag, docType, vendorHubID) + File.separator + fileName);
    		} else {
    			String fpath = null;
    			String rpath = HubSupporter.getConfigFilePath(flag, null, vendorHubID);
    			File tfile = new File(rpath + File.separator + fileName);
    			
    			if(tfile.exists()) {
    				fpath = tfile.getAbsolutePath();
    			} else {
    				rpath = HubSupporter.getConfigFilePath(flag, "BACKUP", vendorHubID);
    				
    				fpath = rpath + File.separator + issueDate + File.separator + fileName;
    			}
    			
    			map.put("FILE_PATH", fpath);
    		}
    	}
    	
        return WebAction.forwarding("/POP/MM-A028_01", dataMap);
    }
    
    /**
     * 지정된 파일을 서버에서 찾아 다운로드 시킴
     * 
     * @param req
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/mm/pop/mmA028_01/selectViewFile")
    public ModelAndView selectViewFile(HttpServletResponse res, HttpServletRequest req, DataMap dataMap) throws Exception {
    	Map map = DataMapHelper.getMap(dataMap);
    	String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
    	
    	byte[] fileByte = null;
    	InputStream in = null;
    	BufferedInputStream bis = null;
    	ByteArrayOutputStream arrayBuff = new ByteArrayOutputStream();
    	ModelAndView view = new ModelAndView();
    	
        try {
        	byte[] buffer = new byte[1024];
        	
        	view.setViewName("/POP/MM-A028_02");
        	
            String filePath = StringHelper.null2void(dataMap.get("FILE_PATH"));
            
            File file = new File(filePath);
            
            if(file.exists()) {
	            if(file.isFile()) {
	            	in=new FileInputStream(file);
	            	bis = new BufferedInputStream(in);
	            	
	            	int len = 0;
	                while ((len = bis.read(buffer)) >= 0) {
	                    arrayBuff.write(buffer, 0, len);
	                }
	                
	                fileByte = arrayBuff.toByteArray();
	                
	                log.debug("file path = " + filePath + ", size = " + fileByte.length);
	            }
	            
	            if (fileByte != null) {
					res.setContentLength(fileByte.length);
					String xmlContents = new String(fileByte, Constants.APPLICATION_CONTEXT_CHARSET);
					
					view.addObject("XML_CONTENTS", xmlContents);
				} else {
					view.addObject("XML_CONTENTS", this.getMessage("TXT_FILE_DOWNLOAD_ERROR", null, lang));
				}
            } else {
            	view.addObject("XML_CONTENTS", this.getMessage("MSG_XML_FILE_YN", null, lang));
            }
        } catch (Exception e) {
        	view.addObject("XML_CONTENTS", this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        } finally {	 
            try {
                if(in != null) in.close();
                if(bis != null) bis.close();
            } catch (Exception e) {
            	view.addObject("XML_CONTENTS", this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
            }
        }
        
        return view;
    }
    
    /**
     * 설문지 뷰 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA028_03")
    public ModelAndView mmA028_03Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A028_03", dataMap);
    }
    
    /**
     * 협력사 설문지 작성 및 제출
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA028_04")
    public ModelAndView mmA028_04Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A028_04", dataMap);
    }
    
}
