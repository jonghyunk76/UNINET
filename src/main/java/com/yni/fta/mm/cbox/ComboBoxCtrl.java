package com.yni.fta.mm.cbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yni.fta.common.tools.DatagridSupporter;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.web.action.WebAction;

/**
 * 콤보박스 데이터를 획득을 위한 Controller 클래스
 * 
 * @author YNI-maker
 *
 */
@Controller
public class ComboBoxCtrl extends YniAbstractController {
	
	@Resource(name="comboBox")
	private ComboBox comboBox;
	
	/**
     * 회사정보 리스트 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectCompany")
    public ModelAndView selectCompany(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectCompany(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * FTA 관리 사이트 회사정보 조회(클라우드용)
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectFTAMgmtSysCompany")
    public ModelAndView selectFTAMgmtSysCompany(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        Map map = null;
        Map rstMap = null;
        
        try {
        	map = DataMapHelper.getMap(dataMap);
        	
        	resultList = comboBox.selectFTAMgmtSysCompany(map);
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        if(StringHelper.null2void(map.get("REQ_TYPE")).equals("EXISTS")) {
        	rstMap = new HashMap();
        	
        	if(resultList != null && resultList.size() > 0) {
        		rstMap.put("EXISTS", "true");
        	} else {
        		rstMap.put("EXISTS", "false");
        	}
        	
        	return WebAction.returnMap(rstMap, message);
        } else if(StringHelper.null2void(map.get("REQ_TYPE")).equals("INFO")) {
        	if(resultList != null && resultList.size() > 0) {
        		rstMap = (Map) resultList.get(0);
        	}
        	
        	return WebAction.returnMap(rstMap, message);
        } else {
        	return WebAction.returnJsonView(resultList, message);
        }
    }
    
    /**
     * 협력사 사이트 회사정보 조회(클라우드용)
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectSupplierSysCompany")
    public ModelAndView selectSupplierSysCompany(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        Map map = null;
        Map rstMap = null;
        
        try {
        	map = DataMapHelper.getMap(dataMap);
        	
            resultList = comboBox.selectSupplierSysCompany(map);
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        if(StringHelper.null2void(map.get("REQ_TYPE")).equals("EXISTS")) {
        	rstMap = new HashMap();
        	
        	if(resultList != null && resultList.size() > 0) {
        		rstMap.put("EXISTS", "true");
        	} else {
        		rstMap.put("EXISTS", "false");
        	}
        	
        	return WebAction.returnMap(rstMap, message);
        } else if(StringHelper.null2void(map.get("REQ_TYPE")).equals("INFO")) {
        	if(resultList != null && resultList.size() > 0) {
        		rstMap = (Map) resultList.get(0);
        	}
        	
        	return WebAction.returnMap(rstMap, message);
        } else {
        	return WebAction.returnJsonView(resultList, message);
        }
    }
    
    /**
     * 사업부 정보 리스트 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectDivision")
    public ModelAndView selectDivision(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectDivision(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 표준코드 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectStandardCode")
    public ModelAndView selectStandardCode(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectStandardCode(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 생산라인(제품군) 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectProductLine")
    public ModelAndView selectProductLine(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectProductLine(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * FTA 협정 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectFta")
    public ModelAndView selectFta(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectFta(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }

    /**
     * 서명권자 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectSignature")
    public ModelAndView selectSignature(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectSignature(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 고객판매유형 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectCustomerSalesType")
    public ModelAndView selectCustomerSalesType(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectCustomerSalesType(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * NALADISA 버전 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectNaladisaVersion")
    public ModelAndView selectNaladisaVersion(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectNaladisaVersion(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * TRACE 유형 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectTraceType")
    public ModelAndView selectTraceType(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectTraceType(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    
    /**
     * SYSTEM 유형 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectSystemType")
    public ModelAndView selectSystemType(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectSystemType(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 상위 메뉴 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectHighMenu")
    public ModelAndView selectHighMenu(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectHighMenu(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 실사 정보 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectInvestigate")
    public ModelAndView selectInvestigate(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectInvestigate(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 실사대상에 포함된 원산지 확인서 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectInvestigateCoNo")
    public ModelAndView selectInvestigateCoNo(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectInvestigateCoNo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 실사대상에 포함된 제품 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectInvestigateProduct")
    public ModelAndView selectInvestigateProduct(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectInvestigateProduct(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    

    /**
     * 현대차 FTA HUB 사용 고객사 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectHubCertCustomer")
    public ModelAndView selectHubCertCustomer(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectHubCertCustomer(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 현대차 FTA HUB 서명권자 명 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectHubSignature")
    public ModelAndView selectHubSignature(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectHubSignature(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * FTA Nation 별 협정 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectFtaNation")
    public ModelAndView selectFtaNation(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectFtaNation(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * FTA 국가목록 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectFtaNationByCode")
    public ModelAndView selectFtaNationByCode(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectFtaNationByCode(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 원산지 확인서 등록 품목 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectReceiveCoItem")
    public ModelAndView selectReceiveCoItem(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectReceiveCoItem(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 기관발급 표준코드 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectKcsStandardCode")
    public ModelAndView selectKcsStandardCode(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectKcsStandardCode(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 해외법인 고객사 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectForeignCustomer")
    public ModelAndView selectForeignCustomer(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectForeignCustomer(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 해외법인 협력사 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectForeignVendor")
    public ModelAndView selectForeignVendor(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectForeignVendor(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 그리드 컬럼 리스트 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectGridColumnSetList")
    public ModelAndView selectGridColumnSetList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
        	
        	resultList = comboBox.selectGridColumnSetList(pmap);
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 그리드 설정 정보 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectDatagridInfo")
    public ModelAndView selectDatagridInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
            List hlist = comboBox.selectDatagridInfo(pmap);
            
            if(hlist.size() > 0) {
            	resultList = DatagridSupporter.getGridHeader(hlist, pmap);
            	
            	// 정렬정보 저장
            	//Map rmap = new HashMap();
            	dataMap.put("sort", hlist.get(0));
            	//resultList.add(rmap);
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnDataSet(resultList, message);
    }
    
    /**
     * 사용자 그리드 설정 정보 삭제(초기화)
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/deleteUserDatagridInfo")
    public ModelAndView deleteUserDatagridInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
        	
            int resultCnt = comboBox.deleteUserDatagridInfo(pmap);
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 사용자 그리드 설정 정보 등록
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/insertUserDatagridInfo")
    public ModelAndView insertUserDatagridInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
    	boolean success = true;
        String message = null;
        
        try {
        	Map pmap = DataMapHelper.getMap(dataMap);
        	
            int resultCnt = comboBox.insertUserDatagridInfo(pmap);
            
            if(resultCnt < 0) {
            	success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.resultJsonMsg(success, message);
    }
    
    /**
     * 그리드 목록(콤보박스 용)
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectDatagridTableList")
    public ModelAndView selectDatagridTableList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectDatagridTableList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 그리드 필드 목록(실제 데이터를 표시하는 컬럼만 조회)
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectDatagridColumnList")
    public ModelAndView selectDatagridColumnList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectDatagridColumnList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 통관 차수 콤보 데이터
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectComboSttOdr")
    public ModelAndView selectComboSttOdr(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectComboSttOdr(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }
    
    /**
     * 사용자 리스트 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectFtaUserList")
    public ModelAndView selectFtaUserList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectFtaUserList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }    
    
    /**
     * 사이트 담당자 리스트 조회
     * 
     * @param req = HttpServletRequest
     * @param dataMap = DataMap
     * @return jsonString
     * @exception Exception
     */
    @RequestMapping("/mm/cbox/selectSiteUserList")
    public ModelAndView selectSiteUserList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;
        
        try {
            resultList = comboBox.selectSiteUserList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }
        
        return WebAction.returnJsonView(resultList, message);
    }    
    
}
