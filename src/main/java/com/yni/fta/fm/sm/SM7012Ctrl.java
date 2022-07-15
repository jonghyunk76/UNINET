package com.yni.fta.fm.sm;

import java.sql.Blob;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 게시판 > 자료실
 * 
 * @author YNI-maker
 *
 */
@Controller
public class SM7012Ctrl extends YniAbstractController {

	@Resource(name="sm7012")
	private SM7012 sm7012;
	

	/**
	 * 조회 화면으로 이동
	 * 
	 * @param dataMap 요청 파라메터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fm/sm/sm7012_01")
	public ModelAndView sm7012_01Move(DataMap dataMap) throws Exception {
		return WebAction.forwarding("/SM/SM-7012_01", dataMap);
	}
	
	/**
     * 자료실 상세 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fm/sm/sm7012_02")
    public ModelAndView sm7012_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding( "/SM/SM-7012_02", dataMap);
    }

	/**
	 * 자료실 리스트 조회
	 * 
	 * @param req HttpServletRequest
	 * @param dataMap 요청 파라메터
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7012_01/selectBoardList")
	public ModelAndView selectBoardList(HttpServletRequest req, DataMap dataMap) throws Exception {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		String message = null;
		
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>(); 
			list = sm7012.selectBoardList(DataMapHelper.getMap(dataMap));

			for(int i=0;i<list.size();i++) {
	        	Map<String, Object> map = list.get(i);
	          
	        	for(Map.Entry<String, Object> entry : map.entrySet()) {
	 	        	String key = entry.getKey();
	 	        	if(entry.getValue() != null){
	 	        		Object value = entry.getValue();
	 	        
						if(value.getClass().getName() == "oracle.sql.CLOB"){
					    	Clob clob = (Clob) value;
					      
					    	if(clob != null){
						    	value = StringHelper.escapeXml(OracleTypeHelper.getStringForCLOB(clob));
					    	}
						}
						map.put(key,value);
	 	        	}
	        	}
	        	resultList.add(map);
			}

		} catch (Exception e) {
			message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
		}

		return WebAction.returnDataSet(resultList, message);
	}
	
	 /**
		 * 자료실 정보 조회
		 * 
		 * @param paramTwo
		 *            - ModelMap model
		 * @return ModelAndView
		 * @exception Exception
		 */
		@RequestMapping("/fm/sm/sm7012_02/selectBoardDetail")
	    public ModelAndView selectBoardDetail(HttpServletRequest request, DataMap dataMap) throws Exception {
			Map resultMap = null;
	        String message = null;
	        
	        try {
	        	resultMap =  sm7012.selectBoardDetail(DataMapHelper.getMap(dataMap));
	        } catch(Exception e) {
	            message = getExceptionMessage(request, e, "MSG_UNSPECIFIED_ERROR");
	        }
	        
	        return WebAction.returnMap(resultMap, message);
	    }
	
	/**
	 * 자료실 추가
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7012_02/insertBoardInfo")
	public ModelAndView insertBoardInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Object regNo = sm7012.insertBoardInfo(DataMapHelper.getMap(dataMap));
            
            if (regNo == null) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
	/**
	 * 자료실 수정
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7012_02/updateBoardInfo")
    public ModelAndView updateBoardInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7012.updateBoardInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
	/**
	 * 자료실 삭제
	 * 
	 * @param paramTwo
	 *            - ModelMap model
	 * @return ModelAndView
	 * @exception Exception
	 */
	@RequestMapping("/fm/sm/sm7012_02/deleteBoardInfo")
    public ModelAndView deleteBoardInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7012.deleteBoardInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt > 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
	
	/**
     * 파일 목록 조회
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fm/sm/sm7012_02/selectBoardFileList")
    public ModelAndView selectBoardFileList(HttpServletRequest request, DataMap dataMap) throws Exception {
        List resultList = new ArrayList();
        String message = null;
        
        try {
            resultList =  sm7012.selectBoardFileList(DataMapHelper.getMap(dataMap));
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
    @RequestMapping(value="/fm/sm/sm7012_02/selectBoardFile")
    public ModelAndView selectBoardFile(HttpServletRequest request, DataMap dataMap) throws Exception {
        Map fileMap = null;
        String message = null;
        byte[] data = null;
        
        try {
            fileMap =  sm7012.selectBoardFile(DataMapHelper.getMap(dataMap));
            
            String db = StringHelper.null2void(this.properties.get("application.db.type"));
    		
            if(db.equals("MSSQL") || db.equals("POSTGRESQL")) {
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
     * 첨부파일 삭제
     * 
     * @param parmOne
     * @param paramTwo
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7012_02/deleteBoardFile")
    public ModelAndView deleteBoardFile(HttpServletRequest req, DataMap dataMap) throws Exception {
    	String message = null;
        boolean success = true;

        try {
            Map map = DataMapHelper.getMap(dataMap);
            int delCnt = sm7012.deleteBoardFile(map);
            
            if(delCnt > 0) {
            	success = false;
            }
        } catch (Exception exp) {
            message = getExceptionMessage(req, exp, "MSG_UNSPECIFIED_ERROR");
        }

        return WebAction.resultJsonMsg(success, message);
    }
}
    