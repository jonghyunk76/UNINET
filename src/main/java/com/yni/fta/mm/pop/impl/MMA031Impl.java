package com.yni.fta.mm.pop.impl;

import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.stereotype.Service;

import com.yni.fta.mm.batch.impl.CloudTransDao;
import com.yni.fta.mm.pop.MMA031;

import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.service.YniAbstractService;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.upload.FormFile;

/**
 * 공통 > 문의메일 발송
 * 
 * @author YNI-maker
 *
 */
@Service("mma031")
public class MMA031Impl extends YniAbstractService implements MMA031 {
	
	@Resource(name="mma031Dao")
	private MMA031Dao mma031Dao;
	
	/**
	 * 상담문의 이력 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInquiryEmailRecordList(Map map) throws Exception {
		return mma031Dao.selectInquiryEmailRecordList(map);
	}

	/**
	 * 첨부파일 리스트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectInquiryFileList(Map map) throws Exception {
		return mma031Dao.selectInquiryFileList(map);
	}

	/**
	 * 첨부파일 내력받기
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectInquiryRealFile(Map map) throws Exception {
		return mma031Dao.selectInquiryRealFile(map);
	}
	
	/**
	 * 첨부파일 최대 시퀀스 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map selectInquiryFileMaxNumber(Map map) throws Exception {
		return mma031Dao.selectInquiryFileMaxNumber(map);
	}
	
	/**
	 * 상담문의 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String insertInquiryEmailRecord(Map map) throws Exception {
		int fileMaxSeq = StringHelper.null2zero(map.get("FILE_MAX_SEQ"));
		List<FormFile> formFileList = DataMapHelper.getFormFile(map);
		
		String inquiryId = StringHelper.null2void(mma031Dao.insertInquiryEmailRecord(map));
		
		if(inquiryId != null) {
			for(int i = 0; i < formFileList.size(); i++) {
				FormFile file = formFileList.get(i);
				Map fileMap = new HashMap();
				
				fileMap.put("INQUIRY_NO", inquiryId);
				fileMap.put("FILE_SEQ", (i+1)+fileMaxSeq);
				fileMap.put("FILE_NAME", StringHelper.null2string(file.getFieldName(), file.getOriginalFilename()));
				fileMap.put("REALFILE", file.getFileData());
				fileMap.put("ORIGIN_FILE_NAME", file.getOriginalFilename());
				fileMap.put("FILE_TYPE", "B");
				
				mma031Dao.insertInquiryFileRecord(fileMap);
			}
		}
		
		return inquiryId;
	}
	
	/**
	 * 상담문의 수정
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateInquiryEmailRecord(Map map) throws Exception {
		int fileMaxSeq = StringHelper.null2zero(map.get("FILE_MAX_SEQ"));
		String inquiryId = StringHelper.null2void(map.get("INQUIRY_NO"));
		List<FormFile> formFileList = DataMapHelper.getFormFile(map);
		
		int cnt = mma031Dao.updateInquiryEmailRecord(map);
		
		if(inquiryId != null) {
			for(int i = 0; i < formFileList.size(); i++) {
				FormFile file = formFileList.get(i);
				Map fileMap = new HashMap();
				
				fileMap.put("INQUIRY_NO", inquiryId);
				fileMap.put("FILE_SEQ", (i+1)+fileMaxSeq);
				fileMap.put("FILE_NAME", StringHelper.null2string(file.getFieldName(), file.getOriginalFilename()));
				fileMap.put("REALFILE", file.getFileData());
				fileMap.put("ORIGIN_FILE_NAME", file.getOriginalFilename());
				fileMap.put("FILE_TYPE", "B");
				
				mma031Dao.insertInquiryFileRecord(fileMap);
			}
		}
		
		return cnt;
	}
	
	/**
	 * 상담문의와 관련 파일 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInquiryEmailRecord(Map map) throws Exception {
		return mma031Dao.deleteInquiryEmailRecord(map);
	}
	
	/**
	 * 상담문의 파일 등록
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertInquiryFileRecord(Map map) throws Exception {
		return mma031Dao.insertInquiryFileRecord(map);
	}
	
	/**
	 * 상담문의 첨부파일 삭제
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteInquiryFileRecord(Map map) throws Exception {
		return mma031Dao.deleteInquiryFileRecord(map);
	}
	
	/**
	 * 시스템 공지사항 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectSystemNoticeList(Map map) throws Exception {
		List rstList = mma031Dao.selectSystemNoticeList(map);
		String db = StringHelper.null2void(this.properties.get("application.db.type"));

        if(db.equals("ORACLE")) {
        	for(int i = 0; i < rstList.size(); i++) {
        		Map resultMap = (Map) rstList.get(i);
        		
				if(resultMap != null && resultMap.get("CONTENT1") != null){
		            Clob clob = (Clob) resultMap.get("CONTENT1");
		            resultMap.put("CONTENT1", StringHelper.escapeXml(OracleTypeHelper.getStringForCLOB(clob)));
		        }
        	}
        }
        
        return rstList;
	}
	
	/**
	 * 알림 카운트 조회
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List selectNoticeCount(Map map) throws Exception {
		List returnList = null; 
		String url = StringHelper.null2void(map.get("URL"));
		String key = StringHelper.null2void(map.get("LICENSE_KEY"));
		
		CloudTransDao cloud = new CloudTransDao();
		CloseableHttpResponse response = cloud.getReponse(url, map);
    	int statusCode = response.getStatusLine().getStatusCode();
        
        if(statusCode == 200) { // 정상적으로 처리된 경우
        	returnList = cloud.getList(response, key); // HUB에서 추출한 정보
        }
        
		return returnList;
	}
	
}
