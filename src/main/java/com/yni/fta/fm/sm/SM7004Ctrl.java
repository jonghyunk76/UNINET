package com.yni.fta.fm.sm;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.common.tools.QuartzSupporter;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 시스템관리 > 인터페이스 > 스케쥴 관리
 * 
 * @author carlos
 *
 */
@Controller
public class SM7004Ctrl extends YniAbstractController {

    private static final Logger logger = LoggerFactory.getLogger(SM7004Ctrl.class);
    
    @Resource(name = "sm7004")
    private SM7004 sm7004;

    /**
     * 스케쥴 리스트 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7004_01")
    public ModelAndView sm7004_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7004_01", dataMap);
    }

    /**
     * 스케쥴 상세 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7004_02")
    public ModelAndView sm7004_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7004_02", dataMap);
    }
    
    /**
     * 
     * 스케쥴 상세 조회 및 관리 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7004_03")
    public ModelAndView sm7004_03Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7004_03", dataMap);
    }
    
    /**
     * 
     * 스케쥴 배치 이력 조회 화면으로 이동
     * 
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fm/sm/sm7004_04")
    public ModelAndView sm7004_04Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/SM/SM-7004_04", dataMap);
    }
    
    /**
     * 스케쥴 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7004_01/selectScheduleList")
    public ModelAndView selectScheduleList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7004.selectScheduleList(DataMapHelper.getMap(dataMap));
            
            for(int i = 0; i < resultList.size(); i++) {
            	Map rstMap = (Map) resultList.get(i);
            	
            	rstMap.put("NEXT_TIME", QuartzSupporter.getNextFireTime(rstMap));
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 스케쥴 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7004_01/selectQuartzJobList")
    public ModelAndView selectQuartzJobList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
        	resultList = QuartzSupporter.getQuartzJobList();
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 
     * 스케쥴 중복건수 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7004_01/selectScheduleDupCheck")
    public ModelAndView selectScheduleDupCheck(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        int nDupCnt = 0;
        try {
            
            Map dupInfo = sm7004.selectScheduleDupCheck(DataMapHelper.getMap(dataMap));
            
            if (dupInfo != null) {
                nDupCnt = StringHelper.null2zero(dupInfo.get("DUPLICATE"));
            }
            
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, "리턴건수:"+nDupCnt);
    }
    
    /**
     * 스케쥴 정보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7004_02/selectScheduleInfo")
    public ModelAndView selectScheduleInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	
            result = sm7004.selectScheduleInfo(map);
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    
    /**
     * 
     * 스케쥴 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping("/fm/sm/sm7004_02/insertScheduleInfo")
    public ModelAndView insertScheduleInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7004.insertScheduleInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            } else {
                Map rData = sm7004.selectScheduleInfo(DataMapHelper.getMap(dataMap));
                
                if (rData != null && !rData.isEmpty()) {
                    QuartzSupporter.setNextFireTime(rData);
                    rData.put("NEXT_TIME", QuartzSupporter.getNextFireTime(rData));
                    
                    logger.debug("SCHEDULE INFO : {}", rData);
                }
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 스케쥴 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping("/fm/sm/sm7004_02/updateScheduleInfo")
    public ModelAndView updateScheduleInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	
            int resultCnt = sm7004.updateScheduleInfo(map);
            
            if (resultCnt < 0) {
                success = false;
            } else {
                Map rData = sm7004.selectScheduleInfo(map);
                
                if (rData != null && !rData.isEmpty()) {
                    QuartzSupporter.setNextFireTime(rData);
                    rData.put("NEXT_TIME", QuartzSupporter.getNextFireTime(rData));
                    
                    logger.debug("SCHEDULE INFO : {}", rData);
                }
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 스케쥴 삭제
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("/fm/sm/sm7004_02/deleteScheduleInfo")
    public ModelAndView deleteScheduleInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7004.deleteScheduleInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            } else {
                Map rData = DataMapHelper.getMap(dataMap);
                if (rData != null && !rData.isEmpty()) {
                    QuartzSupporter.setNextFireTime(rData);
                    rData.put("NEXT_TIME", QuartzSupporter.getNextFireTime(rData));
                    logger.debug("SCHEDULE INFO : {}", rData);
                }
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 스케쥴 맵핑 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7004_02/selectScheduleMappingList")
    public ModelAndView selectScheduleMappingList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = sm7004.selectScheduleMappingList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 스케쥴 맵핑 정보 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/fm/sm/sm7004_03/selectScheduleMappingInfo")
    public ModelAndView selectScheduleMappingInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;
        
        try {
            result = sm7004.selectScheduleMappingInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnMap(result, message);
    }
    
    
    /**
     * 
     * 스케쥴 맵핑 신규 등록
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7004_03/insertScheduleMappingInfo")
    public ModelAndView insertScheduleMappingInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7004.insertScheduleMappingInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 
     * 스케쥴 매핑 수정
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7004_03/updateScheduleMappingInfo")
    public ModelAndView updateScheduleMappingInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7004.updateScheduleMappingInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 쿼츠 스케쥴 실행 리스트 조회
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7004_03/deleteScheduleMappingInfo")
    public ModelAndView deleteScheduleMappingInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
            int resultCnt = sm7004.deleteScheduleMappingInfo(DataMapHelper.getMap(dataMap));
            
            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 스케쥴 강제 종료
     * 
     * @param req - HttpServletRequest
     * @param dataMap - DataMap
     * @return
     */
    @RequestMapping("/fm/sm/sm7004_01/executeStopScheduler")
    public ModelAndView executeStopScheduler(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;
        
        try {
        	Map map = DataMapHelper.getMap(dataMap);
        	String companyCd = StringHelper.null2string(map.get("COMPANY_CD"), StringHelper.null2void(map.get("jobGroup")));
        	String scheduleCd = StringHelper.null2string(map.get("SCHEDULE_CD"), StringHelper.null2void(map.get("jobName")));
        	
        	if(companyCd.isEmpty() || scheduleCd.isEmpty()) {
        		success = false;
        	} else {
	        	map.put("COMPANY_CD", companyCd);
	        	map.put("SCHEDULE_CD", scheduleCd);
	        	
	        	success = QuartzSupporter.stopScheduler(map);
        	}
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
}
