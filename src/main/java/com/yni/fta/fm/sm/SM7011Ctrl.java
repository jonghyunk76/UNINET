package com.yni.fta.fm.sm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.FileUtil;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 시스템 관리 > 공지사항
 * 
 * @author sbj1000
 *
 */
@Controller
public class SM7011Ctrl extends YniAbstractController {

	@Resource(name="sm7011")
	private SM7011 sm7011;
	
	/**
     * 공지사항 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fm/sm/sm7011_01")
    public ModelAndView sm7011_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding( "/SM/SM-7011_01", dataMap);
    }
	
    /**
     * 공지사항 상세 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fm/sm/sm7011_02")
    public ModelAndView sm7011_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding( "/SM/SM-7011_02", dataMap);
    }
    
    /**
     * 공지사항 리스트 조회
     * 
     * @param paramTwo
     *            - ModelMap model
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7011_01/selectNoticeList")
    public ModelAndView selectNoticeList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	
        	resultList = sm7011.selectNoticeList(map);
        	
        	//업데이트 리스트 조회 및 추가
        	if("MMA001_01".equals(StringHelper.null2void(map.get("TARGET_PID")))) {
        		try {
	        		String filePath = Constants.APPLICATION_REAL_PATH+File.separator+"docs"+File.separator+"changelog.his";
	        		BufferedReader bin = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),Constants.APPLICATION_CONTEXT_CHARSET));
				    StringBuilder sb = new StringBuilder();
				    String b;
				    
				    while ((b = bin.readLine()) != null) {
				    	sb.append(b);
	    		    }
	        		
	                if(sb != null) {
		        		List updates = JsonUtil.getList(sb.toString());
		                String lang = StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE);
		                
		                for(int i = 0; i < updates.size(); i++){
		                	Map update = (Map) updates.get(i);
		                	
		                	update.put("POST_TYPE_NAME", this.getMessage("UPDAT", null, lang));
		                	update.put("POST_TYPE", "U");
		                	
		                	resultList.add(update);
		                }
	                }
        		} catch(Exception e) {
        			if(log.isErrorEnabled()) log.error(e.getMessage());
        		}
        	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
	 * 공지사항 정보 조회
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7011_02/selectNoticeInfo")
    public ModelAndView selectNoticeInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        try {
            result = sm7011.selectNoticeInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        return WebAction.returnMap(result, message);
    }
	
	/**
	 * 공지사항 수정
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7011_02/updateNoticeInfo")
    public ModelAndView updateNoticeInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7011.updateNoticeInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
	/**
	 * 공지사항 추가
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7011_02/insertNoticeInfo")
    public ModelAndView insertNoticeInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Object regNo = sm7011.insertNoticeInfo(DataMapHelper.getMap(dataMap));
            
            if (regNo == null) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
	/**
	 * 공지사항 삭제
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7011_02/deleteNoticeInfo")
    public ModelAndView deleteNoticeInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7011.deleteNoticeInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
	@ResponseBody
	@RequestMapping("/fm/sm/sm7011_02/selectNoticeFile/{companyCd}&{noticeNo}")
    public byte[] selectNoticeFile(
            @PathVariable("companyCd") String companyCd,@PathVariable("noticeNo") String noticeNo,
            HttpServletResponse res, HttpServletRequest req, DataMap dataMap) throws Exception  {
		        
        String contentType = "application/octet-stream";
        res.setContentType(contentType);
        
        dataMap.put("COMPANY_CD", companyCd);
        dataMap.put("NOTICE_NO", noticeNo);
        
        @SuppressWarnings("rawtypes")
        Map result = sm7011.selectNoticeFile(DataMapHelper.getMap(dataMap));
             
        byte[] data = null;

        if (result != null) {
            try {
                data = (byte[]) result.get("NOTICE_FILE_DATA");
            } catch (Exception e) {
            	
            }
            if (data == null) {
                try {
                	String db = StringHelper.null2void(this.properties.get("application.db.type"));
            		
                    if (db.equals("MSSQL") || db.equals("POSTGRESQL")) {
                    	data = (byte[]) result.get("NOTICE_FILE_DATA");
                    } else {
                    	Blob blob = (Blob) result.get("NOTICE_FILE_DATA");
                    	data = OracleTypeHelper.getBytes(blob);
                    }
                } catch (Exception e) {
                	
                }
            }
        }
        String fileName = (String) result.get("NOTICE_FILE_NAME");
        res.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(fileName,"UTF-8"));
        
        return data;
        
    }
    
}
