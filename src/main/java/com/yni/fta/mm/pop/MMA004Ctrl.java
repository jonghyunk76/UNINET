package com.yni.fta.mm.pop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//import com.yni.fta.common.tools.IreportSupporter;
//import com.yni.fta.common.tools.JasperReportsPrintView;
import com.yni.fta.common.tools.WebsocketSupporter;

import kr.yni.frame.Constants;
import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.dao.oracle.OracleTypeHelper;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;
import kr.yni.frame.web.action.WebAction;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperPrint;

/**
 * 공통 > 한국 확인서 상세 조회 및 제출
 * 
 * @author hanaRyu
 */
@Controller
public class MMA004Ctrl extends YniAbstractController {

    @Resource(name = "mmA004")
    private MMA004 mmA004;
    
    /**
     * 수출 원산지 확인서 화면의 인보이스 수정화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA004_01")
    public ModelAndView mmA004_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A004_01", dataMap);
    }

    /**
     * 일괄 보고서 발급 툴팁 화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA004_02")
    public ModelAndView mmA004_02Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A004_02", dataMap);
    }
    
    /**
     * 보고서 발급 시 사유등록하는 화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA004_03")
    public ModelAndView mmA004_03Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A004_03", dataMap);
    }
    
    /**
     * 협력사 사급 화면서 발급하는 화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA004_04")
    public ModelAndView mmA004_04Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A004_04", dataMap);
    }
    
    /**
     * 개별 보고서 발급 툴팁 화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmA004_05")
    public ModelAndView mmA004_05Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-A004_05", dataMap);
    }
    
    /**
     * 기발급 원산지 증명서 정보 조회(상세보기 및 수정)
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_01/selectCOIssueInfo")
    public ModelAndView selectCOIssueInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;

        try {
            result = mmA004.selectCOIssueInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(result, message);
    }
    
    /**
     * 증명서 발급 사유 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_01/selectIssueReasonInfo")
    public ModelAndView selectIssueReasonInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;

        try {
            result = mmA004.selectIssueReasonInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(result, message);
    }

    /**
     * 수출자 및 생산자 조회(신규등록)
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_01/selectExportNProducerInfo")
    public ModelAndView selectExportNProducerInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;

        try {
            result = mmA004.selectExportNProducerInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(result, message);
    }

    /**
     * 기발급 증명서 원산지 리스트(상세보기 및 수정)
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_01/selectCOOriginList")
    public ModelAndView selectCOOriginList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = mmA004.selectCOOriginList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }

    /**
     * 원산지 판정내역 조회(신규등록)
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_01/selectDeterminateList")
    public ModelAndView selectDeterminateList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = mmA004.selectDeterminateList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }

    /**
     * 서명권자 정보 조회(신규등록)
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_01/selectSignatureInfo")
    public ModelAndView selectSignatureInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;

        try {
            Map map = DataMapHelper.getMap(dataMap);
            result = mmA004.selectSignatureInfo(map);

            if (result != null && result.size() > 0) {
                result.remove("SIGNATURE_IMAGE");

                map.putAll(result);
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(result, message);
    }

    /**
     * 서명권자 파일이미지 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @ResponseBody
    @RequestMapping("/mm/pop/mmA004_01/selectSignatureFile/{signatureSeq}")
    public byte[] selectSignatureFile(@PathVariable("signatureSeq") String signatureSeq, HttpServletRequest req, DataMap dataMap) throws Exception {
        byte[] data = null;
        String message = null;

        try {
            Map map = DataMapHelper.getMap(dataMap);

            map.put("SIGNATURE_SEQ", signatureSeq);

            Map result = mmA004.selectSignatureInfo(map);

            if (result != null) {
                String db = StringHelper.null2void(this.properties.get("application.db.type"));
                
                if (db.equals("MSSQL") || db.equals("POSTGRESQL")) {
                    data = (byte[]) result.get("SIGNATURE_IMAGE");
                } else {
                    Blob signFile = (Blob) result.get("SIGNATURE_IMAGE"); // 오라클에서 적용
                    data = OracleTypeHelper.getBytes(signFile);
                }
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return data;
    }

    /**
     * 수출 원산지 학인서 인보이스 정보 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_02/selectInvoiceInfo")
    public ModelAndView selectInvoiceInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        Map result = null;
        String message = null;

        try {
            result = mmA004.selectInvoiceInfo(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnMap(result, message);
    }

    /**
     * 수출 원산지 학인서 인보이스 정보 수정
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_02/updateInvoiceInfo")
    public ModelAndView updateInvoiceInfo(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        try {
            int resultCnt = mmA004.updateInvoiceInfo(DataMapHelper.getMap(dataMap));

            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.resultJsonMsg(success, message);
    }

    /**
     * 발급할 보고서 리스트 조회
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_01/selectCoDocList")
    public ModelAndView selectCoDocList(HttpServletRequest req, DataMap dataMap) throws Exception {
        List resultList = null;
        String message = null;

        try {
            resultList = mmA004.selectCoDocList(DataMapHelper.getMap(dataMap));
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.returnDataSet(resultList, message);
    }

    /**
     * 보고서 파일 다운로드
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/mm/pop/mmA004_01/executeIssueCoDoc")
    public ModelAndView executeIssueCoDoc(HttpServletRequest req, HttpServletResponse response, DataMap dataMap) throws Exception {
//        String message = null;
//        List<JasperPrint> jasList = null;
//        ByteArrayOutputStream bops = null;
//
//        Map map = DataMapHelper.getMap(dataMap);
//        
//        try {
//            JasperReportsPrintView view = new JasperReportsPrintView();
//            IreportSupporter operator = new IreportSupporter();
//
//            // 보고서 출력 형태(E:Empty, F:file, P:page)
//            String outType = StringHelper.null2void(dataMap.get("REPORT_PRINT_TYPE"));
//            // 레포트 파일명 : 여러 파일을 동시에 보여줄 경우 구분자(^)로 파일명을 만들 것
//            String fileNames = StringHelper.null2void(dataMap.get("DOC_FILE_NAME"));
//            // 레포트 타입
//            String fileType = StringHelper.null2void(dataMap.get("REPORT_TYPE"));
//            Locale locale = SystemHelper.getLocale(StringHelper.null2string(map.get("SESSION_DEFAULT_LANGUAGE"), "KR"));
//
//            if (outType.equals("E")) {
//                return WebAction.resultJsonMsg(true, null);
//            }
//            
//            if(fileNames.isEmpty()) {
//            	message = this.messageSource.getMessage("PARAM, MSG_NOT_FOUND_01", null, locale);
//            	if(log.isDebugEnabled()) log.debug(message);
//            	
//            	return WebAction.resultJsonMsg(false, message);
//            }
//            
//            // 확인서를 여러개 출력해야 하는 경우인지 체크해서 파라메터를 다르게 적용한다.
//            String coDocNos = StringHelper.null2void(dataMap.get("CO_DOC_NO"));
//            
//            if(coDocNos.isEmpty()) {
//            	ModelMap parameter = new ModelMap();
//            	
//            	// 레포트 공통 파라메터
//	            parameter.put("P_COMPANY_CODE", StringHelper.null2void(map.get(Constants.KEY_COMPANY_CD)));
//	            parameter.put("P_DIVISION_CODE", StringHelper.null2void(map.get("DIVISION_CD")));
//	            parameter.put("P_SALES_NO", "");
//	
//	            parameter.put("P_USER_ID", StringHelper.null2void(map.get(Constants.KEY_USER_ID)));
//	            parameter.put("P_FILE_NAME", fileNames);
//	            
//	            // 서명카드 출력 시 추가도리 파라메터
//	            parameter.put("P_START_DATE", StringHelper.null2void(map.get("START_DATE")));
//	            parameter.put("P_END_DATE", StringHelper.null2void(map.get("END_DATE")));
//	
//	            // 협력사 확인서 출력 시 추가될 파라메터
//	            parameter.put("P_VENDOR_CD", StringHelper.null2void(map.get("VENDOR_CD")));
//	            parameter.put("P_FTA_GROUP_CD", StringHelper.null2void(map.get("FTA_GROUP_CD")));
//	            
//	            // 실사 레포트 시 공통으로 포함될 파라메터
//	            parameter.put("P_INVESTIGATE_CD", StringHelper.null2void(map.get("INVESTIGATE_CD")));
//	            parameter.put("P_FTA_CD", StringHelper.null2void(map.get("FTA_CD")));
//	            
//	            // 제조 공정도 출력 시 추가될 파라메터
//	            parameter.put("P_PRODUCT_LINE_CD", StringHelper.null2void(map.get("PRODUCT_LINE_CD")));
//	            
//	            jasList = operator.getJasperPrintList(dataMap, parameter);
//            } else {
//				StringTokenizer st = new StringTokenizer(coDocNos, "^");
//	            List<ModelMap> params = new LinkedList();
//	            int i = 0;
//	            
//				while(st.hasMoreTokens()) {
//		            ModelMap parameter = new ModelMap();
//		            
//		            // 레포트 공통 파라메터
//		            parameter.put("P_COMPANY_CODE", StringHelper.null2void(map.get(Constants.KEY_COMPANY_CD)));
//		            parameter.put("P_COO_CERTIFY_NO", st.nextToken());
//		            parameter.put("P_DIVISION_CODE", StringHelper.null2void(map.get("DIVISION_CD")));
//		            parameter.put("P_SALES_NO", "");
//		
//		            parameter.put("P_USER_ID", StringHelper.null2void(map.get(Constants.KEY_USER_ID)));
//		            parameter.put("P_FILE_NAME", fileNames);
//		            
//		            // 서명카드 출력 시 추가도리 파라메터
//		            parameter.put("P_START_DATE", StringHelper.null2void(map.get("START_DATE")));
//		            parameter.put("P_END_DATE", StringHelper.null2void(map.get("END_DATE")));
//		
//		            // 협력사 확인서 출력 시 추가될 파라메터
//		            parameter.put("P_VENDOR_CD", StringHelper.null2void(map.get("VENDOR_CD")));
//		            parameter.put("P_FTA_GROUP_CD", StringHelper.null2void(map.get("FTA_GROUP_CD")));
//		            
//		            // 실사 레포트 시 공통으로 포함될 파라메터
//		            parameter.put("P_INVESTIGATE_CD", StringHelper.null2void(map.get("INVESTIGATE_CD")));
//		            parameter.put("P_FTA_CD", StringHelper.null2void(map.get("FTA_CD")));
//		            
//		            // 제조 공정도 출력 시 추가될 파라메터
//		            parameter.put("P_PRODUCT_LINE_CD", StringHelper.null2void(map.get("PRODUCT_LINE_CD")));
//		            
//		            params.add(parameter);
//		            
//		            i++;
//				}
//				
//				if(i > 1) {
//					jasList = operator.getJasperPrintList(dataMap, params);
//				} else {
//					jasList = operator.getJasperPrintList(dataMap, params.get(0));
//				}
//            }
//            
//            if (jasList == null) {
//                message = "JasperPrint is null.";
//            }
//            
//            String wsCode = StringHelper.null2void(map.get("SOCKET_ID"));
//            log.debug("Web socket id = " + wsCode);
//        	
//			WebsocketSupporter ws = new WebsocketSupporter();
//			Session session = null;
//			
//			try {
//				session = ws.getSession(wsCode);
//			} catch(Exception e) {
//				if(log.isErrorEnabled()) log.error(e);
//			}
//			
//            if (outType.equals("F")) {
//    			if(session != null) {
//    				ws.handleMessage("MM0001_01", session);
//    			}
//    			
//                return WebAction.returnIReportView(jasList, dataMap, message);
//            } else {
//                bops = new ByteArrayOutputStream();
//                Map exportParameter = new HashMap(); // export설정
//
//                exportParameter.put(JRExporterParameter.OUTPUT_STREAM, bops);
//                exportParameter.put(JRExporterParameter.JASPER_PRINT_LIST, jasList);
//
//                view.setExportParameter(exportParameter);
//                view.setReportOutputType(fileType); // pdf, xls, doc
//
//                // export 생성
//                view.createExporter();
//
//                // 레포트 뷰
//                view.renderReport(response);
//            }
//            
//			if(session != null) {
//				ws.handleMessage("MM0001_01", session);
//			}
//        } catch (Exception e) {
//            message = getExceptionMessage(req, e, "MSG_UNSPECIFIED_ERROR");
//
//            dataMap.clear();
//
//            return WebAction.resultJsonMsg(false, message);
//        }
//        
        return null;
    }
    
    /**
     * 발급일자 수정
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @RequestMapping("/mm/pop/mmA004_02/updateCoIssueDate")
    public ModelAndView updateCoIssueDate(HttpServletRequest req, DataMap dataMap) throws Exception {
        boolean success = true;
        String message = null;

        try {
            int resultCnt = mmA004.updateCoIssueDate(DataMapHelper.getMap(dataMap));

            if (resultCnt < 0) {
                success = false;
            }
        } catch (Exception e) {
            message = getExceptionMessage(req, e, this.getMessage("TXT_SYSTEM_ERROR", null, StringHelper.null2string(dataMap.get("SESSION_DEFAULT_LANGUAGE"), Constants.DEFAULT_LANGUAGE)));
        }

        return WebAction.resultJsonMsg(success, message);
    }
}
