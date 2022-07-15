package com.yni.fta.common.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.ModelMap;

import kr.yni.frame.collection.DataMap;
import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.context.ApplicationContextFactory;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.util.StringHelper;

/**
 * iReport 출력을 위한 DB작업을 도와주는 클래스
 * 
 * @author YNI-maker
 *
 */
public class IreportSupporter {
	private static Log log = LogFactory.getLog(IreportSupporter.class);
	
	/**
	 * jasper파일 경로 설정명
	 */
	private final static String REPORT_ROOT_PATH = "report.root.path";
	private final static String SYSTEM_DB_TYPE = "application.db.type";
	
	/**
	 * Jasper 파일 확장자명
	 */
	private final static String REPORT_EXTEND_NAME = "report.extend.name";
	
	/**
	 * Jasper가 실행한 결과를 가지는 JasperPrint 객체를 반환한다.
	 * 
	 * @param reportName jasper파일명
	 * @param parameter 파라에터
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private JasperPrint getJasperPrint(String reportName, ModelMap parameter, Connection conn) throws Exception{
		Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
		String dbType = StringHelper.null2void(configurator.getString(this.SYSTEM_DB_TYPE));
		String fileExt = StringHelper.null2void(configurator.getString(this.REPORT_EXTEND_NAME));
		String jasperPath = "";
		
		if(dbType.equals("MSSQL")) {
			jasperPath = StringHelper.null2void(configurator.getString(this.REPORT_ROOT_PATH)) + "mssql/out/";
		} else if(dbType.equals("POSTGRESQL")) {
			jasperPath = StringHelper.null2void(configurator.getString(this.REPORT_ROOT_PATH)) + "postgresql/out/";			
		} else if(dbType.equals("DB2")) {
			jasperPath = StringHelper.null2void(configurator.getString(this.REPORT_ROOT_PATH)) + "db2/out/";
		} else {
			jasperPath = StringHelper.null2void(configurator.getString(this.REPORT_ROOT_PATH)) + "oracle/out/";
		}
		
		// jasper파일이 존재하는지 체크하고 존재하면 Report객체를 얻는다.
		File compiledFile = new File(jasperPath + reportName + fileExt);
		
		if (!compiledFile.exists()) {
			throw new Exception("Jasper File not Found : " + jasperPath + reportName);
		} else {
			if(log.isDebugEnabled()) {log.debug("jasper file name : " + jasperPath + reportName);}
		}

		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(compiledFile.getPath()); 
		
		// DB Connection을 맺고 bind 파라메터를 넘겨 쿼리 수행 후 결과를 JasperPrint에 담는다. 
		JasperPrint jasper = JasperFillManager.fillReport(jasperReport, parameter, conn);
		
		return jasper;
	}
	
	/**
	 * JasperPrint의 집합을 가지는 List객체를 반환한다.
	 * 
	 * @param reportNames jasper파일들
	 * @param parameter 파라에터
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<JasperPrint> getJasperPrintList(DataMap dataMap, ModelMap parameter) throws Exception {
		Connection conn = null;
		List jasperPrintList = null;
		int idx = 0;
		
		try {
			conn = this.getConnection();
			
			jasperPrintList = new ArrayList();
			String reportNames = StringHelper.null2void(dataMap.get("DOC_FILE_NAME"));
			StringTokenizer st = new StringTokenizer(reportNames, "^");
			
			while(st.hasMoreTokens()) {
				String reportName = st.nextToken();
				
				if(!"N".equals(reportName)) {
					if(log.isDebugEnabled()) {log.debug("file name = " + reportName + ", parameter = " + parameter + ", connector = " + conn);}
					
					// DB Connection을 맺고 bind 파라메터를 넘겨 쿼리 수행 후 결과를 JasperPrint에 담는다. 
					JasperPrint jasper = this.getJasperPrint(reportName, parameter, conn);
					
					// 처리 결과를 담고 있는 JasperPrint객체를 리스트에 담는다.
					jasperPrintList.add(jasper);
				}
				
				idx++;
			}
		} finally {
			this.closeConnection(conn);
		}
		
		return jasperPrintList;
	}	
	
	/**
	 * JasperPrint의 집합을 가지는 List객체를 반환한다.
	 * 
	 * @param reportNames jasper파일들
	 * @param parameter 파라에터
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<JasperPrint> getJasperPrintList(DataMap dataMap, List<ModelMap> parameters) throws Exception {
		Connection conn = null;
		List jasperPrintList = null;
		int idx = 0;
		
		try {
			conn = this.getConnection();
			
			jasperPrintList = new ArrayList();
			String reportNames = StringHelper.null2void(dataMap.get("DOC_FILE_NAME"));
			StringTokenizer st = new StringTokenizer(reportNames, "^");
			
			while(st.hasMoreTokens()) {
				String reportName = st.nextToken();
				
				if(!"N".equals(reportName)) {
					if(log.isDebugEnabled()) {log.debug("file name = " + reportName + ", parameter = " + parameters.get(idx) + ", connector = " + conn);}
					
					// DB Connection을 맺고 bind 파라메터를 넘겨 쿼리 수행 후 결과를 JasperPrint에 담는다. 
					JasperPrint jasper = this.getJasperPrint(reportName, parameters.get(idx), conn);
					
					// 처리 결과를 담고 있는 JasperPrint객체를 리스트에 담는다.
					jasperPrintList.add(jasper);
				}
				
				idx++;
			}
		} finally {
			this.closeConnection(conn);
		}
		
		return jasperPrintList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<JasperPrint> getJasperPrintList(ModelMap parameter) throws Exception {
		Connection conn = null;
		List jasperPrintList = null;
		
		try {
			conn = this.getConnection();
			
			jasperPrintList = new ArrayList();
			String reportName = StringHelper.null2void(parameter.get("DOC_FILE_NAME"));
			
			if(log.isDebugEnabled()) {log.debug("file name = " + reportName + ", parameter = " + parameter + ", connector = " + conn);}
			
			// DB Connection을 맺고 bind 파라메터를 넘겨 쿼리 수행 후 결과를 JasperPrint에 담는다. 
			JasperPrint jasper = this.getJasperPrint(reportName, parameter, conn);
			
			// 처리 결과를 담고 있는 JasperPrint객체를 리스트에 담는다.
			jasperPrintList.add(jasper);

		} catch(Exception e) {
			throw new FrameException(e.getMessage());
		} finally {
			this.closeConnection(conn);
		}
		
		return jasperPrintList;
	}
	
	/**
	 * iReport 생성, OutputStream 생성 ==> BLOB 등록 시 사용
	 * 
	 * @param req
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JasperReportsPrintView getJasperPrintOutputStream(ModelMap parameter)  throws Exception { 
		ByteArrayOutputStream bops = null;
		JasperReportsPrintView view = new JasperReportsPrintView();
		
		try {
			List jasList = this.getJasperPrintList(parameter);
			
			// export설정
			Map exportParameter = new HashMap();
			
			bops = new ByteArrayOutputStream();
			
			exportParameter.put(JRExporterParameter.OUTPUT_STREAM, bops);
			exportParameter.put(JRExporterParameter.JASPER_PRINT_LIST, jasList);
			
			view.setExportParameter(exportParameter);
			view.setReportOutputType(StringHelper.null2void(parameter.get("REPORT_TYPE")));
			
			// export 생성
			view.createExporter();
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(bops != null) bops.close();
		}
		
		return view;
	}
	
	/**
	 * iReport 생성, OutputStream 생성 ==> BLOB 등록 시 사용
	 * 
	 * @param req
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JasperReportsPrintView getJasperPrintOutputStream(DataMap dataMap, ModelMap parameter)  throws Exception { 
		ByteArrayOutputStream bops = null;
		JasperReportsPrintView view = new JasperReportsPrintView();
		
		try {
			List jasList = this.getJasperPrintList(dataMap, parameter);
			
			// export설정
			Map exportParameter = new HashMap();
			
			bops = new ByteArrayOutputStream();
			
			exportParameter.put(JRExporterParameter.OUTPUT_STREAM, bops);
			exportParameter.put(JRExporterParameter.JASPER_PRINT_LIST, jasList);
			
			view.setExportParameter(exportParameter);
			view.setReportOutputType(StringHelper.null2void(dataMap.get("REPORT_TYPE")));
			
			// export 생성
			view.createExporter();
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(bops != null) bops.close();
		}
		
		return view;
	}
	
	
	/**
	 * iReport 생성, OutputStream 생성 ==> BLOB 등록 시 사용
	 * 
	 * @param req
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JasperReportsPrintView getJasperPrintOutputStream(DataMap dataMap, List<ModelMap> parameters)  throws Exception { 
		ByteArrayOutputStream bops = null;
		JasperReportsPrintView view = new JasperReportsPrintView();
		
		try {
			List jasList = this.getJasperPrintList(dataMap, parameters);
			
			// export설정
			Map exportParameter = new HashMap();
			
			bops = new ByteArrayOutputStream();
			
			exportParameter.put(JRExporterParameter.OUTPUT_STREAM, bops);
			exportParameter.put(JRExporterParameter.JASPER_PRINT_LIST, jasList);
			
			view.setExportParameter(exportParameter);
			view.setReportOutputType(StringHelper.null2void(dataMap.get("REPORT_TYPE")));
			
			// export 생성
			view.createExporter();
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(bops != null) bops.close();
		}
		
		return view;
	}
	
	/**
	 * DB컨넥션 후 Connection객체를 반환한다.
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException{
		ApplicationContext ctx = ApplicationContextFactory.getAppContext();
		BasicDataSource dataSource = (BasicDataSource) ctx.getBean("dataSource");
		
		return dataSource.getConnection();
	}
	
	/**
	 * DB컨넥션 연결을 종료시킨다.
	 * @throws SQLException
	 */
	private void closeConnection(Connection conn) throws SQLException {
		if(conn != null) {
			conn.close();
		}
	}
}
