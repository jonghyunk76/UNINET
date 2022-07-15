package com.yni.fta.mm.pop;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//import com.yni.fta.common.tools.IreportSupporter;
//import com.yni.fta.common.tools.JasperReportsPrintView;
import com.yni.fta.common.tools.WebsocketSupporter;

import kr.yni.frame.collection.DataMap;
import kr.yni.frame.controller.YniAbstractController;
import kr.yni.frame.util.DataMapHelper;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;
import kr.yni.frame.web.action.WebAction;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperPrint;

/**
 * 공통 > 통관 보고서 발급
 * 
 * @author hanaRyu
 */
@Controller
public class MMB009Ctrl extends YniAbstractController {

    /**
     * 수출 원산지 확인서 화면의 인보이스 수정화면으로 이동
     * 
     * @param dataMap DataMap
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/mm/pop/mmB009_01")
    public ModelAndView mmB009_01Move(DataMap dataMap) throws Exception {
        return WebAction.forwarding("/POP/MM-B009_01", dataMap);
    }
    
    /**
     * 수입신고서 파일 다운로드
     * 
     * @param req HttpServletRequest
     * @param dataMap DataMap
     * @return ModelAndView
     * @exception Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping("/mm/pop/mmB009_01/executeIssueImpDclrtDoc")
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
//            ModelMap parameter = new ModelMap();
//            String port = StringHelper.null2void(req.getServerPort());
//            String ip = req.getServerName();
//            String url = null;
//            
//            if(ip.equals("106.249.244.186")) {
//            	url = "http://106.249.244.186:8099";
//            } else {
//            	url = (req.isSecure()?"https://":"http://")+ip+":"+port;
//            }
//        	
//        	if(log.isDebugEnabled()) log.debug("Request URL = " + url);
//        	
//        	// 레포트 공통 파라메터
//            parameter.put("P_COMPANY_CD", StringHelper.null2void(map.get("COMPANY_CD")));
//            parameter.put("P_IMP_BCNC_SETUP_SN", StringHelper.null2void(map.get("IMP_BCNC_SETUP_SN")));
//            parameter.put("P_STT_ODR", StringHelper.null2void(map.get("STT_ODR")));
//            parameter.put("P_SERVER_URL", url);
//            
//            jasList = operator.getJasperPrintList(dataMap, parameter);
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
        
        return null;
    }
    
}
