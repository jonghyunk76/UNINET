package com.yni.fta.common.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sap.mw.jco.JCO;
import com.yni.fta.common.Consistent;
import com.yni.fta.common.batch.delegate.DataHandler;
import com.yni.fta.common.batch.vo.BatchVo;
import com.yni.fta.common.batch.vo.ParameterVo;
import com.yni.fta.common.beans.target.InterfaceTarget;

import kr.yni.frame.dao.JcoDAO;
import kr.yni.frame.exception.FrameException;
import kr.yni.frame.mapper.ParamReader;
import kr.yni.frame.mapper.element.Column;
import kr.yni.frame.mapper.element.Export;
import kr.yni.frame.mapper.element.Import;
import kr.yni.frame.mapper.element.Jco;
import kr.yni.frame.mapper.element.Parameter;
import kr.yni.frame.mapper.element.Table;
import kr.yni.frame.mapper.util.JcoMapValidator;
import kr.yni.frame.util.JcoMapHelper;
import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;
import kr.yni.frame.util.SystemHelper;
import net.sf.json.JSONObject;

public class BatchSupporter extends JcoDAO {
	
	private static Log log = LogFactory.getLog(BatchSupporter.class);
	
	private Jco jco = null;
	
	private JcoMapHelper jcoMap = null;
	
	public BatchSupporter(BatchVo batchVo) {
		try {
			jco = ParamReader.getJcoParameter(batchVo.getJcoId());
			jcoMap = new JcoMapHelper(batchVo.getJcoId()); // JCO ID에  해당하는 jco정보 획득
		} catch(Exception e) {
			log.error(e);
		}
	}
	
	/**
	 * 요청 시 Import할 파라메터 정보 생성
	 * 
	 * @param batchVo
	 * @param pmap
	 * @return
	 * @throws Exception
	 */
	public Map getImportPrameter(BatchVo batchVo, Map pmap) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Import imp = null;
		if(jco != null) imp = jco.getFunction(0).getImports();
		
		if(imp == null) {
			return pmap;
		}
		
		for (int i = 0; i < imp.getParameterCount(); i++) {
			Parameter param = imp.getParams(i);
			String paramName = StringHelper.null2void(param.getName());
			String transName = StringHelper.null2void(param.getTrans());
			
			if(param.getType() == JCO.TYPE_ITAB) { // itab인 경우에는 SAP JCO 정보를 사용하지 않음(2020-12-19)
				List tbl = new ArrayList();
				Object mapObj = pmap.get(paramName);
				
				if(param.getColumnCount() > 0) {
					if(mapObj != null && mapObj instanceof List) { // 리스트 값이 입력된 경우
						List lobj = (List) mapObj;
						
						for(int j = 0; j <lobj.size(); j++) {
							Map colMap = new HashMap();
							Map mobj = (Map) lobj.get(j);
							
							for (int k = 0; k < param.getColumnCount(); k++) {
								Column col = param.getColumn(k);
								
								String colName = StringHelper.null2void(col.getName());
								String paramValue = null;
								String defaultVal = StringHelper.null2void(col.getDefault());
								
								paramValue = StringHelper.null2string(mobj.get(colName), defaultVal);
								
								if(!transName.isEmpty()) colMap.put(transName, paramValue);
								else colMap.put(colName, paramValue);
							}
							
							tbl.add(colMap);
						}
					} else { // Map 값이 입력된 경우
						Map colMap = new HashMap();
						Map dataMap = (Map) mapObj;
						
						for(int k = 0; k < param.getColumnCount(); k++) {
							Column col = param.getColumn(k);
							
							String colName = StringHelper.null2void(col.getName());
							String paramValue = null;
							String defaultVal = StringHelper.null2void(col.getDefault());
							
							paramValue = StringHelper.null2string(dataMap.get(colName), defaultVal);
							
							if(!transName.isEmpty()) colMap.put(transName, paramValue);
							else colMap.put(colName, paramValue);
						}
						
						tbl.add(colMap);
					}
				}
				
				if(!transName.isEmpty()) paramMap.put(transName, tbl);
				else paramMap.put(paramName, tbl);
			} else if(param.getType() == JCO.TYPE_XSTRING) {
				if(param.getColumnCount() > 0) {
					Map colMap = new HashMap();
					
					for(int k = 0; k < param.getColumnCount(); k++) {
						Column col = param.getColumn(k);
						
						String colName = StringHelper.null2void(col.getName());
						String paramValue = null;
						String defaultVal = StringHelper.null2void(col.getDefault());
						
						paramValue = StringHelper.null2string(pmap.get(colName), defaultVal);
						
						if(!transName.isEmpty()) colMap.put(transName, paramValue);
						else colMap.put(colName, paramValue);
					}
					
					if(!transName.isEmpty()) paramMap.put(transName, colMap);
					else paramMap.put(paramName, colMap);
				}
		    } else {
				String defaultVal = StringHelper.null2void(param.getDefault());
				Object vobj = pmap.get(paramName);
				
				if(vobj instanceof String) {
					int lang = param.getOffset();
					String paramValue = StringHelper.null2string(vobj, defaultVal);
					
					if(paramValue.length() > lang) {
						paramValue = paramValue.substring(0, lang);
					}
					
					if(!transName.isEmpty()) paramMap.put(transName, paramValue);
					else paramMap.put(paramName, paramValue);
				} else {
					if(!transName.isEmpty()) paramMap.put(transName, vobj);
					else paramMap.put(paramName, vobj);
				}
			}
		}
		
		return paramMap;
	}
	
	/**
	 * 처리결과를 구한다.
	 * 
	 * @param batchVo
	 * @param pmap
	 * @return
	 * @throws Exception
	 */
	public Map getExportParameter(BatchVo batchVo, Map pmap) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Export exp = null;
		if(jco != null) exp = jco.getFunction(0).getExports();
		
		if(exp == null) {
			return pmap;
		}
		
		for (int i = 0; i < exp.getParameterCount(); i++) {
			Parameter param = exp.getParams(i);
			String paramName = param.getName();

			if(param.getType() == JCO.TYPE_ITAB) { // itab인 경우에는 SAP JCO 정보를 사용하지 않음(2020-12-19)
				List tbl = new ArrayList();
				Object mapObj = pmap.get(paramName);
				
				if(param.getColumnCount() > 0) {
					if(mapObj != null && mapObj instanceof List) { // 리스트 값이 입력된 경우
						List lobj = (List) mapObj;
						
						for(int j = 0; j <lobj.size(); j++) {
							Map colMap = new HashMap();
							Map mobj = (Map) lobj.get(j);
							
							for (int k = 0; k < param.getColumnCount(); k++) {
								Column col = param.getColumn(k);
								
								String colName = StringHelper.null2void(col.getName());
								String transName = StringHelper.null2void(col.getTrans());
								String paramValue = null;
								String defaultVal = StringHelper.null2void(col.getDefault());
								
								paramValue = StringHelper.null2string(mobj.get(colName), defaultVal);
								
								if(!transName.isEmpty()) colMap.put(transName, paramValue);
								else colMap.put(colName, paramValue);
							}
							
							tbl.add(colMap);
						}
					} else { // Map 값이 입력된 경우
						Map colMap = new HashMap();
						Map dataMap = (Map) mapObj;
						
						for(int k = 0; k < param.getColumnCount(); k++) {
							Column col = param.getColumn(k);
							
							String colName = StringHelper.null2void(col.getName());
							String transName = StringHelper.null2void(col.getTrans());
							String paramValue = null;
							String defaultVal = StringHelper.null2void(col.getDefault());
							
							paramValue = StringHelper.null2string(dataMap.get(colName), defaultVal);
							
							if(!transName.isEmpty()) colMap.put(transName, paramValue);
							else colMap.put(colName, paramValue);
						}
						
						tbl.add(colMap);
					}
				}
				
				paramMap.put(paramName, tbl);
			} else if(param.getType() == JCO.TYPE_XSTRING) {
				if(param.getColumnCount() > 0) {
					Map colMap = new HashMap();
					
					for(int k = 0; k < param.getColumnCount(); k++) {
						Column col = param.getColumn(k);
						
						String colName = StringHelper.null2void(col.getName());
						String transName = StringHelper.null2void(col.getTrans());
						String paramValue = null;
						String defaultVal = StringHelper.null2void(col.getDefault());
						
						paramValue = StringHelper.null2string(pmap.get(colName), defaultVal);
						
						if(!transName.isEmpty()) colMap.put(transName, paramValue);
						else colMap.put(colName, paramValue);
					}
					
					paramMap.put(paramName, colMap);
				}
		    } else {
				String transName = StringHelper.null2void(param.getTrans());
				String defaultVal = StringHelper.null2void(param.getDefault());
				int lang = param.getOffset();
				String paramValue = null;
				
				paramValue = StringHelper.null2string(pmap.get(paramName), defaultVal);
				
				if(paramValue.length() > lang) {
					if(!transName.isEmpty()) paramMap.put(transName, paramValue.substring(0, lang));
					else paramMap.put(paramName, paramValue.substring(0, lang));
				} else {
					if(!transName.isEmpty()) paramMap.put(transName, paramValue);
					else paramMap.put(paramName, paramValue);
				}
			}
		}
		
		return paramMap;
	}
	
	/**
	 * 테이블 데이터를 구한다.
	 * 
	 * @param batchVo
	 * @param pmap
	 * @return
	 * @throws Exception
	 */
	public Map getTableData(BatchVo batchVo, Map pmap) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Table tlb = null;
		if(jco != null) tlb = jco.getFunction(0).getTable();
		
		if(tlb == null) {
			return pmap;
		}
		
		for(int i = 0; i < tlb.getParameterCount(); i++) {
			Parameter param = tlb.getParams(i);
			String paramName = param.getName(); // table명

			if(param.getType() == JCO.TYPE_ITAB) { // itab인 경우에는 SAP JCO 정보를 사용하지 않음(2020-12-19)
				List tbl = new ArrayList();
				Object mapObj = pmap.get(paramName);
				
				if(param.getColumnCount() > 0) {
					if(mapObj != null && mapObj instanceof List) { // 리스트 값이 입력된 경우
						List lobj = (List) mapObj;
						
						for(int j = 0; j <lobj.size(); j++) {
							Map colMap = new HashMap();
							Map mobj = (Map) lobj.get(j);
							
							for (int k = 0; k < param.getColumnCount(); k++) {
								Column col = param.getColumn(k);
								
								String colName = StringHelper.null2void(col.getName());
								String transName = StringHelper.null2void(col.getTrans());
								String paramValue = null;
								String defaultVal = StringHelper.null2void(col.getDefault());
								
								paramValue = StringHelper.null2string(mobj.get(colName), defaultVal);
								String source = JcoMapValidator.getSourceString(paramValue, col, null);
								// 유효성 체크
								String chkstr = StringHelper.null2void(JcoMapValidator.getSourceCheckForType(col, source, SystemHelper.getLocale(null)));
								
								if (chkstr.length() > 0) {
									colMap.put("ERROR_YN", "Y");
								} else {
									colMap.put("ERROR_YN", "N");
								}
								colMap.put("ERROR_MESSAGE", chkstr);
								
								if(!transName.isEmpty()) colMap.put(transName, source);
								else colMap.put(colName, source);
							}
							
							tbl.add(colMap);
						}
					} else { // Map 값이 입력된 경우
						Map colMap = new HashMap();
						Map dataMap = (Map) mapObj;
						
						for(int k = 0; k < param.getColumnCount(); k++) {
							Column col = param.getColumn(k);
							
							String colName = StringHelper.null2void(col.getName());
							String transName = StringHelper.null2void(col.getTrans());
							String paramValue = null;
							String defaultVal = StringHelper.null2void(col.getDefault());
							
							paramValue = StringHelper.null2string(dataMap.get(colName), defaultVal);
							String source = JcoMapValidator.getSourceString(paramValue, col, null);
							// 유효성 체크
							String chkstr = StringHelper.null2void(JcoMapValidator.getSourceCheckForType(col, source, SystemHelper.getLocale(null)));
							
							if (chkstr.length() > 0) {
								colMap.put("ERROR_YN", "Y");
							} else {
								colMap.put("ERROR_YN", "N");
							}
							colMap.put("ERROR_MESSAGE", chkstr);
							
							if(!transName.isEmpty()) colMap.put(transName, source);
							else colMap.put(colName, source);
						}
						
						tbl.add(colMap);
					}
				}
				
				paramMap.put(paramName, tbl);
			} else if(param.getType() == JCO.TYPE_XSTRING) {
				if(param.getColumnCount() > 0) {
					Map colMap = new HashMap();
					
					for(int k = 0; k < param.getColumnCount(); k++) {
						Column col = param.getColumn(k);
						
						String colName = StringHelper.null2void(col.getName());
						String transName = StringHelper.null2void(col.getTrans());
						String paramValue = null;
						String defaultVal = StringHelper.null2void(col.getDefault());
						
						paramValue = StringHelper.null2string(pmap.get(colName), defaultVal);
						String source = JcoMapValidator.getSourceString(paramValue, col, null);
						// 유효성 체크
						String chkstr = StringHelper.null2void(JcoMapValidator.getSourceCheckForType(col, source, SystemHelper.getLocale(null)));
						
						if (chkstr.length() > 0) {
							colMap.put("ERROR_YN", "Y");
						} else {
							colMap.put("ERROR_YN", "N");
						}
						colMap.put("ERROR_MESSAGE", chkstr);
						
						if(!transName.isEmpty()) colMap.put(transName, source);
						else colMap.put(colName, source);
					}
					
					paramMap.put(paramName, colMap);
				}
		    } else {
				String transName = StringHelper.null2void(param.getTrans());
				String defaultVal = StringHelper.null2void(param.getDefault());
				int lang = param.getOffset();
				String paramValue = null;
				
				paramValue = StringHelper.null2string(pmap.get(paramName), defaultVal);
				
				// 유효성 체크
				String chkstr = "";
				
				if (chkstr.length() > 0) {
					paramMap.put("ERROR_YN", "Y");
				} else {
					paramMap.put("ERROR_YN", "N");
				}
				paramMap.put("ERROR_MESSAGE", chkstr);
				
				if(paramValue.length() > lang) {
					if(!transName.isEmpty()) paramMap.put(transName, paramValue.substring(0, lang));
					else paramMap.put(paramName, paramValue.substring(0, lang));
				} else {
					if(!transName.isEmpty()) paramMap.put(transName, paramValue);
					else paramMap.put(paramName, paramValue);
				}
			}
		}
		
		return paramMap;
	}
	
	/**
	 * 응답 데이터를 인터페이스 이력테이블로 저장
	 * 
	 * @param batchTarget = DB 통신 객체
	 * @param batchVo = 배치정보
	 * @param pmap = 파라메터
	 * @return 처리되는 데이터건수
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int insertTableData(InterfaceTarget batchTarget, BatchVo batchVo, Map pmap) throws Exception {
		int cnt = 0;
		
		if(jco == null) {
			cnt = pmap.size();
			return cnt;
		}
		
		Map bmap = batchVo.getMap();
		Map revMap = new HashMap(); // 수신한 Map정보
		
		Table tlb = jco.getFunction(0).getTable();
		
		for(int i = 0; i < tlb.getParameterCount(); i++) {
			Parameter param = tlb.getParams(i);
			String paramName = param.getName(); // table명
			
			if(param.getType() == JCO.TYPE_ITAB) {
				List rlist = (List) pmap.get(paramName);
				
				for(int j = 0; j < rlist.size(); j++) {
					Map colMap = (Map) rlist.get(j);
					
					colMap.put(Consistent.IF_BATCH_TRANS_DATA_ID, paramName); // 조회되는 테이블명
	                colMap.put(Consistent.IF_BATCH_TRANS_ID, batchVo.getTransId()); // 인터페이스 이력번호
	                colMap.put(Consistent.IF_JOB_COMPANY_CD, bmap.get(Consistent.IF_JOB_COMPANY_CD));
	                colMap.put(Consistent.IF_BATCH_TRANS_SEQ, (i+1));
	                colMap.put(Consistent.IF_JOB_CREATE_BY, batchVo.getMap().get(Consistent.IF_JOB_CREATE_BY));
				}
				
				cnt += rlist.size();
				
				batchTarget.insertTransDtlData(rlist);
			} else {
				String transName = StringHelper.null2void(param.getTrans());
				String defaultVal = StringHelper.null2void(param.getDefault());
				int lang = param.getOffset();
				String paramValue = null;
				
				paramValue = StringHelper.null2string(pmap.get(paramName), defaultVal);
				
				if(paramValue.length() > lang) {
					if(!transName.isEmpty()) revMap.put(transName, paramValue.substring(0, lang));
					else revMap.put(paramName, paramValue.substring(0, lang));
				} else {
					if(!transName.isEmpty()) revMap.put(transName, paramValue);
					else revMap.put(paramName, paramValue);
				}
			}
		}
		
		// 수신 파라메터 업데이트
		if(revMap.size() > 0) {
			String rev = JsonUtil.getViewJson(revMap);
			
			log.debug("Receive data = " + rev);
			
			Map rmap = new HashMap();
			
			rmap.put("RECEIVE_PARAM", rev);
			rmap.put(Consistent.IF_BATCH_TRANS_ID, batchVo.getTransId()); // 인터페이스 이력번호
			rmap.put(Consistent.IF_JOB_COMPANY_CD, bmap.get(Consistent.IF_JOB_COMPANY_CD));
			
			batchTarget.updateProcedureResult(rmap);
		}
		
		return cnt;
	}
	
	/**
	 * 송신할 데이터를 인터페이스 이력 테이블에서 조회하고 메시지 맵핑된 리스트 리턴
	 * 
	 * @param batchTarget = DB 통신 객체
	 * @param batchVo = 배치정보
	 * @param pmap = 파라메터
	 * @return
	 * @throws Exception
	 */
	public boolean getInterfaceData(InterfaceTarget batchTarget, BatchVo batchVo, Map pmap) throws Exception {
		boolean result = true;
		ParameterVo pvo = batchVo.getParameter();
		
		try {
			String rparam = batchTarget.selectSendParameterInfo(pmap); // 이력 테이블(interface_history)에서 수신된 파라메터 조회
			
			log.debug("send data(before) = " + rparam);
			
			if(rparam != null && !rparam.isEmpty()) {
				Map rmap = JsonUtil.getMap(rparam);
				
				log.debug("send data(after) = " + rmap);
				
				pvo.setPutAll(rmap); // 수신된 파라메터 정보를 포함시킨다.
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			// 전송할 데이터에 대한 JCO Map이 있는 경우에만 아래 로직을 수행함
			if(jco == null) {
				return result;
			}
			
			Table tlb = jco.getFunction(0).getTable();
			Import imp = jco.getFunction(0).getImports();
			
			for(int i = 0; i < tlb.getParameterCount(); i++) {
				Parameter param = tlb.getParams(i);
				String paramName = param.getName(); // table명
				
				if(param.getType() == JCO.TYPE_ITAB) {
					pmap.put("OBJECT_NAME", paramName); // table 객체명
					
					List datas = batchTarget.selectSendDataList(pmap); // 이력 테이블(interface_history_data)에서 전송할 데이터 조회
					
					Map tmap = new HashMap();				
					tmap.put(paramName, datas);
					
					paramMap = this.getTableData(batchVo, tmap);
					
					String key = paramName;
					
					// 전송될 데이터명 구하기
					for(int p = 0; p < imp.getParameterCount(); p++) {
						Parameter iparam = imp.getParams(p);
						
						String iparamName = StringHelper.null2void(iparam.getName());
						
						if(param.getType() == JCO.TYPE_ITAB && iparamName.equals(paramName)) { // 데이터 타입이 itabe이면서 name에 같은 경우, parameter name을 변경함
							key = StringHelper.null2string(iparam.getTrans(), iparamName);
							break;
						}
					}
					
					// 전송할 인터페이스 이력등록
					for(int j = 0; j < datas.size(); j++) {
						Map colMap = (Map) datas.get(j);
						
						colMap.put(Consistent.IF_BATCH_TRANS_DATA_ID, "_" + key); // 전송되는 테이블명(_+테이블명)
		                colMap.put(Consistent.IF_BATCH_TRANS_ID, batchVo.getTransId()); // 인터페이스 이력번호
		                
		                log.debug("Data("+colMap.get("ROW_SEQ")+") >>> " + colMap);
					}
					
					batchTarget.insertTransDtlData(datas);
				}
			}
			
			// import할 문자열 파라메터 생성
			for(int i = 0; i < imp.getParameterCount(); i++) {
				Parameter param = imp.getParams(i);
				String paramName = param.getName();
				
				if(param.getType() != JCO.TYPE_ITAB) {
					String paramValue = StringHelper.null2void(pmap.get(paramName));
					
					paramMap.put(paramName, paramValue);
				}
			}
			
			log.debug("Result parameter = " + paramMap.size() + ", data = " + paramMap);
			
			pvo.setPutAll(paramMap);
			batchVo.setBatchStatus("S");
		} catch(Exception e) {
			result = false;
			log.error(e.getCause());
			
			batchVo.setBatchStatus("E");
			batchVo.setErrorMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 데이터 요청후 수신받기
	 * 
	 * @param batchTarget = DBO객체
	 * @param batchVo = 배치정보
	 * @param pvo = User Parameter
	 * @param iobj = JCO Import parameter(User parameter를 변환시킨 결과)
	 * @param map = 인터페이스 정보 > batchVo.getMap();
	 * @return
	 * @throws Exception
	 */
	public boolean receive(InterfaceTarget batchTarget, BatchVo batchVo, ParameterVo pvo, Object iobj, Map map) throws Exception {
//		String protocal = StringHelper.null2void(map.get("PROTOCAL")); // 통신방법 : HTTP, SMTP, FTP, JCO
		String reqFormat = StringHelper.null2void(map.get("IMP_DATA_FORMAT")); // Import 데이터 형식 : JSON, FILE, XML, OBJECT
		String process_type = StringHelper.null2void(map.get("PROCESS_TYPE")); // 처리방식 : Siebel(I), SOAP(S), HTTP(H), SMTP(T), FTP(F), JCO(J), Bypass(B), Procedure(P)
		String trandId = batchVo.getTransId();
		
		// 배치정보에 지정된 리턴되는 데이터 형식에 맞게 변환
		Map reqMap = new HashMap();
		
		if(iobj != null) {
			pvo.clear(); // 요청 파라메터는 초기화 후 재생성함
			
			if(reqFormat.equals("OBJECT")) {
				reqMap.put("REQ_PARAMS", iobj); // Object 객체를 그대로 적용
			} else if(reqFormat.equals("JSON")) {
				if(iobj instanceof Map) reqMap.put("REQ_PARAMS", JsonUtil.getViewJson((Map) iobj)); // Json타입의 문자열 변환
				else if(iobj instanceof List) reqMap.put("REQ_PARAMS", JsonUtil.getViewJson((List) iobj)); // Json타입의 문자열 변환
				else log.error("JSON 타입으로 변환 가능한 객체가 아닙니다.(Map 또는 List 객체 필요)");
			} else if(reqFormat.equals("FILE")) {
				reqMap.put("REQ_PARAMS", iobj);
			} else if(reqFormat.equals("XML")) {
				if(iobj instanceof String) reqMap.put("REQ_PARAMS", JsonUtil.getXMLMap(iobj.toString())); // XML로 변환
				else log.error("XML 타입으로 변환 가능한 객체가 아닙니다.(XML format 오류)");
			}
			
			pvo.setPutAll(reqMap);
        }
		
		log.debug("Receive import parameter = " + pvo.getMap());
		
    	// 4.서버 통신방법에 따라 데이터 요청
		DataHandler dh = new DataHandler(batchVo, process_type);
		boolean result = dh.receive(map, pvo);
		
		// 수신 결과데이터 업데이트
		Map resMap = new HashMap();
		
		resMap.put("INTERFACE_HISTORY_ID", trandId);
		
		try {
			Object robj = batchVo.getReturnData();
			
			if(iobj != null) resMap.put("REQUEST_PARAM", iobj.toString()); // 수신측 요청 파라메터(요청측 데이터 원본 - 문자열)
			if(robj != null) resMap.put("RECEIVE_PARAM", robj.toString()); // 수신측 응답 데이터(Json타입의 데이터 - 문자열)
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		
		batchTarget.updateProcedureResult(resMap);
		
		return result;
	}
	
	/**
	 * 데이터 송신
	 * JCO Import 정보에 설정된 정보를 이용하여 데이터를 송신한다.
	 * 
	 * @param batchVo = 배치VO
	 * @param pmap = 송신할 파라메터 정보
	 * @param map = 인터페이스 정보 > batchVo.getMap();
	 * @return
	 * @throws Exception
	 */
	public boolean send(InterfaceTarget batchTarget, BatchVo batchVo, Map pmap, Map map) throws Exception {
		if(jco != null) return true;
		
		String process_type = StringHelper.null2void(map.get("PROCESS_TYPE")); // 처리방식 : Siebel(I), SOAP(S), HTTP(H), SMTP(T), FTP(F), JCO(J), Bypass(B), Procedure(P)
		String resFormat = StringHelper.null2void(map.get("RES_DATA_FORMAT")); // Import 데이터 형식 : JSON, FILE, XML, OBJECT
    	int pageNum = StringHelper.null2zero(map.get("TRAN_ROW_NUM")); // 한번에 전송할 데이터건수
    	String trandId = batchVo.getTransId();
    	
		Map commVal = new HashMap();
		Map<String, List> listMap = new HashMap<String, List>();
		
		Import imp = jco.getFunction(0).getImports();
		
		// 공통으로 넘겨줄 문자열로 지정된 데이터를 찾음
		for(int i = 0; i < imp.getParameterCount(); i++) {
			Parameter param = imp.getParams(i);
			
			String paramName = StringHelper.null2void(param.getName());
			String transName = StringHelper.null2void(param.getTrans());
			String defaultVal = StringHelper.null2void(param.getDefault());
			Object vobj = pmap.get(paramName);
			
			if(vobj instanceof String) {
				int lang = param.getOffset();
				String paramValue = StringHelper.null2string(vobj, defaultVal);
				
				if(paramValue.length() > lang) {
					paramValue = paramValue.substring(0, lang);
				}
				
				if(!transName.isEmpty()) commVal.put(transName, paramValue);
				else commVal.put(paramName, paramValue);
			} else if(vobj instanceof Map) {
				commVal.putAll((Map) vobj);
			} else if(vobj instanceof List) {
				if(!transName.isEmpty()) listMap.put(transName, (List) vobj);
				else listMap.put(transName, (List) vobj);
			}
		}
		
		// 공통으로 Service ID, Transaction ID, 전송일시을 추가함
		commVal.put("TRANSACTION_ID", trandId);
		commVal.put("SERVICE_ID", StringHelper.null2void(map.get("SERVICE_ID")));
		
		// 리스트로 저장된 값인 경우에는 한번에 전송가능한 건수만큼 Loop로 전송함
		if(listMap != null && listMap.size() > 0) {
			log.debug("Send info[size = " + listMap.size() + ", data " + listMap + "]");
			
			for (Iterator it = listMap.entrySet().iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = StringHelper.null2void(entry.getKey());
                List rlist = listMap.get(key);
			
				int totalCnt = rlist.size();
				int pageCnt = 0;
				int startPage = 0;
				int endPage = 0;
				
				// Loop건수 조회
				if(pageNum == 0) {
					pageCnt = 1;
					startPage = 0;
					endPage = totalCnt;
				} else {
					pageCnt = totalCnt/pageNum;
					
					if(totalCnt % pageNum > 0) {
						pageCnt++;
					}
				}
				
				// Loop로 전송
				for(int i = 0; i < pageCnt; i++) {
					List slist = null;
					
					if(pageNum == 0) {
						slist = rlist;
					} else {
						startPage = (i * pageNum);
						endPage = (i * pageNum) + pageNum;
						if(endPage > totalCnt) endPage = totalCnt;
						endPage = endPage - 1;
						
						slist = rlist.subList(startPage, endPage);
					}
					
					log.debug("send data(page = " + startPage + " ~ " + endPage+ ")");
					
					// 전송할 데이터 생성
					Map sendData = new HashMap();
					
					sendData.putAll(commVal);
					
					// 응답되는 데이터 타입으로 변환
					if(resFormat.equals("JSON")) {
						sendData.put(key, JsonUtil.getViewJson(slist));
					} else {
						sendData.put(key, slist);
					}
					
					// 프로토콜에 따라 데이터를 전송
					DataHandler dh = new DataHandler(batchVo, process_type);
					
					boolean rst = dh.send(map, sendData);
					
					if(!rst) return false;
				}
			}
		}
		
		// 전송되는 모든 데이터를 기록한다.
		Map resMap = new HashMap();
		
		resMap.put("INTERFACE_HISTORY_ID", trandId);
		
		try {
			resMap.put("REQUEST_PARAM", JsonUtil.getViewJson(commVal));
		} catch(Exception e) {
			log.error(e.getMessage());
			
			resMap.put("REQUEST_PARAM", commVal.toString());
		}
		
		batchTarget.updateProcedureResult(resMap);
		
		return true;
	}
	
	/**
	 * 동적 패키지 경로에 대한 클래스 호출
	 * 
	 * @param cname 클래스명
	 * @param fname 호출한 매소드명
	 * @param bmap 배치정보
	 * @param params 매소드에 전달할 인자값(파라메터)
	 * @return
	 * @throws Exception
	 */
	public Object callMethod(String cname, String fname, Map bmap, Object params) throws Exception {
		String snd_rev_type = StringHelper.null2void(bmap.get("INTERFACE_MTH"));
		String ifCode = StringHelper.null2void(bmap.get("SERVICE_ID"));
		String className = "com.yni.rs.batch.";
		
		try {
			if(snd_rev_type.equals("R")) {
				className += ifCode + ".receive." + cname;
			} else if(snd_rev_type.equals("S")) {
				className += ifCode + ".send." + cname;
			}
			
			Object robj = SystemHelper.executeMethod(className, fname, params);
			
			if(robj != null) {
				return robj;
			} else {
				return null;
			}
		} catch(Exception e) {
			log.warn("클래스 경로를 찾을 수 없습니다.(" + className + ")");
		}
		
		return null;
	}
	
}
