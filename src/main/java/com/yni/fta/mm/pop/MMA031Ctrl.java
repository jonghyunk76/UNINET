package com.yni.fta.mm.pop;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.common.Consistent;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 공통 > 문의메일 발송
 * 
 * @author YNI-Maker
 *
 */
@Controller
public class MMA031Ctrl extends YniAbstractController {
	
	@Resource(name="mma031")
	private MMA031 mma031;
	
    /**
     * 상담문의 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/noses/mmA031_01")
    public ModelAndView mmA031_01Move(DataMap dataMap) throws Exception {
    	return WebAction.forwarding("/POP/MM-A031_01", dataMap);
    }
    
    /**
     * 메일발송 내용선택 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA031_02")
    public ModelAndView mmA031_02Move(DataMap dataMap) throws Exception {
    	return WebAction.forwarding("/POP/MM-A031_02", dataMap);
    }
    
    /**
     * 문의 등록 리스트 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/noses/mmA031_03")
    public ModelAndView mmA031_03Move(DataMap dataMap) throws Exception {
    	return WebAction.forwarding("/POP/MM-A031_03", dataMap);
    }
    
    /**
     * 요청 및 등록 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/noses/mmA031_04")
    public ModelAndView mmA031_04Move(DataMap dataMap) throws Exception {
    	return WebAction.forwarding("/POP/MM-A031_04", dataMap);
    }
    
    /**
     * 유지보수 처리서 출력 화면으로 이동
     * 
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mm/noses/mmA031_05")
    public ModelAndView mmA031_05Move(DataMap dataMap) throws Exception {
    	return WebAction.forwarding("/POP/MM-A031_05", dataMap);
    }
    
    /**
     * 문의 및 요청내역 목록 조회
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/noses/mmA031_01/selectInquiryEmailRecordList")
    public ModelAndView selectInquiryEmailRecordList(HttpServletRequest request, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList =  mma031.selectInquiryEmailRecordList(DataMapHelper.getMap(dataMap));
        } catch(Exception e) {
            message = getExceptionMessage(request, e, "MSG_UNSPECIFIED_ERROR");
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 파일 목록 조회
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/noses/mmA031_01/selectInquiryFileList")
    public ModelAndView selectInquiryFileList(HttpServletRequest request, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList =  mma031.selectInquiryFileList(DataMapHelper.getMap(dataMap));
        } catch(Exception e) {
            message = getExceptionMessage(request, e, "MSG_UNSPECIFIED_ERROR");
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 첨부파일 조회
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/noses/mmA031_01/selectInquiryRealFile")
    public ModelAndView selectInquiryRealFile(HttpServletRequest request, DataMap dataMap) throws Exception {
        Map fileMap = null;
        String message = null;
        byte[] data = null;
        
        try {
            fileMap =  mma031.selectInquiryRealFile(DataMapHelper.getMap(dataMap));
            
            String db = StringHelper.null2void(this.properties.get("application.db.type"));
    		
            if (db.equals("MSSQL") || db.equals("POSTGRESQL")) {
            	data = (byte[]) fileMap.get("REALFILE");
            } else {
            	Blob blob = (Blob) fileMap.get("REALFILE");
            	data = OracleTypeHelper.getBytes(blob);
            }
        } catch(Exception e) {
            message = getExceptionMessage(request, e, "MSG_UNSPECIFIED_ERROR");
        }
        
        return WebAction.returnFileView(data, fileMap.get("ORIGIN_FILE_NAME").toString(), message);
    }
    
    /**
	 * 상담문의 등록
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA031_01/insertInquiryEmailRecord")
	public ModelAndView insertInquiryEmailRecord(HttpServletRequest req, DataMap dataMap) throws Exception {
		String message = null;
		Map rstMap = new HashMap();
		
		try {
			Map map = DataMapHelper.getMap(dataMap);
			
			if(!StringHelper.null2void(map.get("INQUIRY_NO")).isEmpty()) {
				Map maxNumbrt = mma031.selectInquiryFileMaxNumber(map);
				
				map.putAll(maxNumbrt);
			}
			
			String inquiryId = mma031.insertInquiryEmailRecord(map);
			
			rstMap.put("INQUIRY_NO", inquiryId);
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnMap(rstMap, message);
	}
    
	/**
	 * 상담문의 수정
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA031_01/updateInquiryEmailRecord")
	public ModelAndView updateInquiryEmailRecord(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean rst = true;
		String message = null;

		try {
			Map map = DataMapHelper.getMap(dataMap);
			
			if(!StringHelper.null2void(map.get("INQUIRY_NO")).isEmpty()) {
				Map maxNumbrt = mma031.selectInquiryFileMaxNumber(map);
				
				map.putAll(maxNumbrt);
			}
			
			int cnt = mma031.updateInquiryEmailRecord(map);
			
			if(cnt < 0) {
				rst = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(rst, message);
	}
	
	/**
	 * 상담문의 삭제
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA031_01/deleteInquiryEmailRecord")
	public ModelAndView deleteInquiryEmailRecord(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean rst = true;
		String message = null;

		try {
			int cnt = mma031.deleteInquiryEmailRecord(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				rst = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(rst, message);
	}
	
	/**
	 * 상담문의 첨부파일 등록
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA031_01/insertInquiryFileRecord")
	public ModelAndView insertInquiryFileRecord(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean rst = true;
		String message = null;

		try {
			int cnt = mma031.insertInquiryFileRecord(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				rst = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(rst, message);
	}
	
	/**
	 * 상담문의 첨부파일 삭제
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA031_01/deleteInquiryFileRecord")
	public ModelAndView deleteInquiryFileRecord(HttpServletRequest req, DataMap dataMap) throws Exception {
		boolean rst = true;
		String message = null;
		
		try {
			int cnt = mma031.deleteInquiryFileRecord(DataMapHelper.getMap(dataMap));
			
			if(cnt < 0) {
				rst = false;
			}
		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.resultJsonMsg(rst, message);
	}
	
	/**
     * 시스템 공지사항 조회
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mm/noses/mmA031_01/selectSystemNoticeList")
    public ModelAndView selectSystemNoticeList(HttpServletRequest request, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList =  mma031.selectSystemNoticeList(DataMapHelper.getMap(dataMap));
        } catch(Exception e) {
            message = getExceptionMessage(request, e, "MSG_UNSPECIFIED_ERROR");
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
	 * 알림 카운트 조회
	 * 
	 * @param req
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/noses/mmA031_01/selectNoticeCount")
	public ModelAndView selectNoticeCount(HttpServletRequest req, DataMap dataMap) throws Exception {
		Map resultMap = null;
		String message = null;
		
		try {
			Map map = DataMapHelper.getMap(dataMap);
			
			map.put(Consistent.IF_PARAMETER_TOMS_FTA_CERT_KEY, StringHelper.null2void(map.get("LICENSE_KEY")));
			
			List resultList = mma031.selectNoticeCount(map);
			
			if(resultList.size() > 0) {
				resultMap = (Map) resultList.get(0); 
			}
		} catch (Exception e) {
			resultMap = new HashMap();
			
			resultMap.put("B_NO", "0");
		}

		return WebAction.returnMap(resultMap, message);
	}
	
}
