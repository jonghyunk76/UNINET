package com.yni.fta.fm.sm.impl;

import java.sql.Clob;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yni.fta.fm.sm.SM7011;
import com.yni.fta.fm.sm.impl.SM7011Dao;

import kr.yni.frame.Constants;
import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * 시스템 관리 > 공지사항
 * 
 * @author sbj1000
 *
 */
@Service("sm7011")
public class SM7011Impl extends YniAbstractService implements SM7011 {
	
	@Resource(name="sm7011Dao")
		private SM7011Dao sm7011Dao;
	
	public List selectNoticeList(Map map) throws Exception {
		return sm7011Dao.selectNoticeList(map);
	}
	
	public Map selectNoticeInfo(Map map) throws Exception {
		Map resultMap = sm7011Dao.selectNoticeInfo(map);
		String db = StringHelper.null2void(this.properties.get("application.db.type"));

        if(db.equals("ORACLE")) {
			if(resultMap != null && resultMap.get("CONTENT1") != null){
	            Clob clob = (Clob) resultMap.get("CONTENT1");
	            resultMap.put("CONTENT1", StringHelper.escapeXml(OracleTypeHelper.getStringForCLOB(clob)));
	        }
        }
        
        return resultMap;
    }
	
	public int updateNoticeInfo(Map map) throws Exception {     
        List<FormFile> formFileList = DataMapHelper.getFormFile(map);
        FormFile formFile = null;
        
        int nFileLen = 0;
        
        if (formFileList != null) {
            nFileLen = formFileList.size();
        }
        
        if (nFileLen > 0) {
            formFile = formFileList.get(0);
        }
        
        if (formFile != null) {
            this.checkFormFile(formFile);
            
            byte[] Noticefile = formFile.getFileData();
            
            map.put("NOTICE_FILE_DATA", Noticefile);
        }
        
        int rtnCnt = sm7011Dao.updateNoticeInfo(map);
        
        map.remove("NOTICE_FILE_DATA");
        
        return rtnCnt;
    }
	
	public Object insertNoticeInfo(Map map) throws Exception {        
        List<FormFile> formFileList = DataMapHelper.getFormFile(map);
        FormFile formFile = null;
        
        int nFileLen = 0;
        
        if (formFileList != null) {
            nFileLen = formFileList.size();
        }
        
        if (nFileLen > 0) {
            formFile = formFileList.get(0);
        }
        
        if (formFile != null) {
            // 파일정보가 있는경우

            // 첨부파일체크
            this.checkFormFile(formFile);
            
            byte[] Noticefile = formFile.getFileData();
            map.put("NOTICE_FILE_DATA", Noticefile);
            
        }
        
        Object regNo = sm7011Dao.insertNoticeInfo(map);
        
        map.remove("NOTICE_FILE_DATA");
        
        return regNo;
    }
	
	public int deleteNoticeInfo(Map map) throws Exception {
		return sm7011Dao.deleteNoticeInfo(map);
	}
	
	private void checkFormFile(FormFile formFile) {
        if (formFile == null) {
            throw new RuntimeException(this.getMessage("MSG_FILE_NOT_FOUND"));
        }
        
        String contentType = StringHelper.null2void(formFile.getContentType());
        String originalExtension = StringHelper.null2void(formFile.getOriginalExtension());
        int fileSize = formFile.getFileSize();

        if (originalExtension.equals(".exe")
         || originalExtension.equals(".java") 
         || originalExtension.equals(".jsp") 
         || originalExtension.equals(".css")
         || originalExtension.equals(".js")) {
            throw new RuntimeException(this.getMessage("MSG_EXTENSION_IMPOSSIBLE"));
        }
        
        if (fileSize > Constants.FILE_MAX_UPLOAD_SIZE) {
            throw new RuntimeException(this.getMessage("MSG_FILE_SIZE_OVER"));
        }
    }
	
	public Map selectNoticeFile(Map map) throws Exception {
        return sm7011Dao.selectNoticeFile(map);
    }
	
	public Map selectLastNoticeNo(Map map) throws Exception {
        return sm7011Dao.selectLastNoticeNo(map);
    }
}
