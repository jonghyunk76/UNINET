package com.yni.fta.common.tools;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * iReport 지원 클래스
 * 
 * @author jonghyunkim
 *
 */
public class JasperReportsPrintView {
	private static Log log = LogFactory.getLog(JasperReportsPrintView.class);
	
	/**
	 * 레포트 출력 형태
	 */
	private String REPORT_OUTPUT_TYPE = "pdf";
	
	/**
	 * export설정
	 */
	private Map<JRExporterParameter, Object> EXPORT_PARAMETERS;
	
	/**
	 * Default Constructor
	 */
	public JasperReportsPrintView() { }
	
	public void setReportOutputType(String type) {
		if(type.isEmpty()) {
			this.REPORT_OUTPUT_TYPE = type;
		}
	}
	
	public void setExportParameter(Map<JRExporterParameter, Object> map) {
		this.EXPORT_PARAMETERS = map;
	}
	
	/**
	 * 파라메터에서 지정한 형태의 매개변수를 설정한다. 
	 * 
	 * @return
	 * @throws Exception
	 */
	public void createExporter() throws Exception {
		JRExporter exporter = null;
		
		if("pdf".equals(REPORT_OUTPUT_TYPE)) {
			exporter = new JRPdfExporter();
		} else if("xls".equals(REPORT_OUTPUT_TYPE) || "xlsx".equals(REPORT_OUTPUT_TYPE)) {
			exporter = new JRDocxExporter();
		} else if("doc".equals(REPORT_OUTPUT_TYPE) || "docx".equals(REPORT_OUTPUT_TYPE)) {
			exporter = new JRXlsxExporter();
		} else if("applet".equals(REPORT_OUTPUT_TYPE)) {
			exporter = null;
		}
		
		// 문서를 보낼 매개변수를 설정한다.
		if(exporter != null) {
			Iterator<JRExporterParameter> keys = EXPORT_PARAMETERS.keySet().iterator();
			while(keys.hasNext()) {
				JRExporterParameter key = keys.next();
				Object value = EXPORT_PARAMETERS.get(key);
				exporter.setParameter(key, value);
			}
			
			exporter.exportReport();
		}
	}
	
	/**
	 * Perform rendering for a single Jasper Reports exporter,
	 * i.e. a pre-defined output format.
	 */
	@SuppressWarnings("rawtypes")
	public void renderReport(HttpServletResponse response) throws Exception {
		byte bytes[];
		
		if(!"applet".equals(REPORT_OUTPUT_TYPE)) {
			ByteArrayOutputStream output = (ByteArrayOutputStream) EXPORT_PARAMETERS.get(JRExporterParameter.OUTPUT_STREAM);
			bytes = output.toByteArray();
			
			if (bytes != null && bytes.length > 0) {
				if("pdf".equals(REPORT_OUTPUT_TYPE)) {
					response.setContentType("application/pdf");
					//response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + ".pdf\"");
				} else if("xls".equals(REPORT_OUTPUT_TYPE) || "xlsx".equals(REPORT_OUTPUT_TYPE)) {
					response.setContentType("application/x-msexcel");
					//response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + ".xlsx\"");
				} else if("doc".equals(REPORT_OUTPUT_TYPE) || "docx".equals(REPORT_OUTPUT_TYPE)) {
					response.setContentType("application/msword");
					//response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + ".docx\"");
				}
				
				if(log.isDebugEnabled()) log.debug("Content type = " + REPORT_OUTPUT_TYPE + ", file size = " + bytes.length);
				
				response.setContentLength(bytes.length);
				
				ServletOutputStream ouputStream = response.getOutputStream();
				try {
					ouputStream.write(bytes, 0, bytes.length);                    
					ouputStream.flush();
				} finally {
					if(ouputStream != null) ouputStream.close();
				}
			} else {
				log.error("Byte is 0...");
			}
		} else {
			ObjectOutputStream output = null;
			ServletOutputStream outputStream = response.getOutputStream();
			
			response.setContentType("application/octet-stream");
			
			try {
				output =(ObjectOutputStream) EXPORT_PARAMETERS.get(JRExporterParameter.OUTPUT_STREAM);
				List jasperList = (List) EXPORT_PARAMETERS.get(JRExporterParameter.JASPER_PRINT_LIST);
				
				output.writeObject((JasperPrint)jasperList.get(0));
				output.flush();
			} finally {
				if(outputStream != null) outputStream.close();
				if(output != null) output.close();
			}
		}
	}
	
	/**
	 * iReport파일을 byte배열로 리턴한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public byte[] getIReportFileStream() throws Exception {
		byte bytes[];
		
		try {
			ByteArrayOutputStream output = (ByteArrayOutputStream) EXPORT_PARAMETERS.get(JRExporterParameter.OUTPUT_STREAM);
			bytes = output.toByteArray();
		} catch(Exception ex) {
			throw ex;
		}
		
		return bytes;
	}	
}
