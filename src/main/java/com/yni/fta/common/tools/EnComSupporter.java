package com.yni.fta.common.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.yni.frame.util.StringHelper;

public class EnComSupporter {

	private static Log log = LogFactory.getLog(EnComSupporter.class);

	private static final String dataSeq = "DATA_SEQ"; 							// 에러 리스트 index
	private static final String impExpDclrtSn = "ENTR_ALL_SN"; 					// 수입(IMP_DCLRT_SN), 수출(EXP_DCLRT_SN)	
	private static final String colA = "COL_A"; 								// 키 컬럼	
	private static final String encomKey = "COL_B"; 							// 키 컬럼
	private static final String colC = "COL_C"; 								// 키2 컬럼	
	private static final String encomTrntErr = "ENCOM_TRNT_ERR_CN"; 			// 유효성 오류
	private static final String encomTrntUpdate = "ENCOM_TRNT_UPDATE_CN"; 		// 유효성 주의 ==> 값을 변경 하여 전송 함, 예로 소수점
	private static final String enpsMgmtNo = "ENPS_MGMT_NO";					// 업체관리번호
	private static final String newLine = System.getProperty("line.separator");


	/**
	 * getdocStleSeFileNameMapping
	 * @return
	 * @throws Exception
	 */
	public static Map getdocStleSeFileNameMapping() throws Exception {
		// Map 문서형태구분:파일명 OR 파일명:문서형태구분
		HashMap<String,String> docStleSeMap = new HashMap<>();
		docStleSeMap.put("ENCOM_EXP","EXPRE");
		docStleSeMap.put("ENCOM_IMP","IMPRE");
		docStleSeMap.put("ENCOM_PC","GAKYKRE");
		docStleSeMap.put("ENCOM_CVTA","CUSAGRRE");
		
		docStleSeMap.put("EXPRE","ENCOM_EXP");
		docStleSeMap.put("IMPRE","ENCOM_IMP");
		docStleSeMap.put("GAKYKRE","ENCOM_PC");
		docStleSeMap.put("CUSAGRRE","ENCOM_CVTA");		
		
		return docStleSeMap;
	}

	
	public static String getConfigDocDir(String docType) throws Exception {
		String dir = "";

		dir = docType;

		return dir;
	}

//	public void createSendFile(Map map) throws Exception {
//		List resultList = this.IC1001Dao.selectImportDeclarationList(map);
//
//		CSVSupporter csv = new CSVSupporter();
//		CSVSupporter.listToCsvFile(localFileFullName, resultList, "^", false);
//		CSVSupporter.listToCsvFile(localFileFullName, resultList, "^", false);
//		
//	}

	/**
	 * 엔컴 송수신 파일 명
	 * 
	 * @param docType
	 * @param trntRecSe
	 * @return
	 * @throws Exception
	 */
	public static String getFileName(String docStleSe, String trntRecSe) throws Exception {
		String fileName = "";

//		의뢰정보 전송시 수출입구분(IMPREQ)+생성일시(YYYYMMDDHHMMSS).TXT
//		수리정보 전송시 수출입구분(IMPRES)+생성일시(YYYYMMDDHHMMSS).TXT		
//		의뢰정보 전송시 수출입구분(EXPREQ)+생성일시(YYYYMMDDHHMMSS).TXT
//		수리정보 전송시 수출입구분(EXPRES)+생성일시(YYYYMMDDHHMMSS).TXT		
	
		// 가격신고서
//      의뢰정보 전송시 가격신고서구분(GAKYKREQ)+생성일시(YYYYMMDDHHMMSS).TXT					
//      수리정보 전송시 가격신고서구분(GAKYKRES)+생성일시(YYYYMMDDHHMMSS).TXT					

		// 협정관세적용신청서
//      의뢰정보 전송시 협정관세적용신청서구분(CUSAGRREQ)+생성일시(YYYYMMDDHHMMSS).TXT					
//      수리정보 전송시 협정관세적용신청서구분(CUSAGRRES)+생성일시(YYYYMMDDHHMMSS).TXT	
		
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String dataNow = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		Map docStleSeMap = getdocStleSeFileNameMapping();

		fileName = (String) docStleSeMap.get(docStleSe);
		
		if (trntRecSe.equals("S")) {
			fileName = fileName + "Q" + dataNow;
		} else if (trntRecSe.equals("R")) {
			fileName = fileName + "S" + dataNow;
		}

		return fileName + ".txt";
	}
	
	/**
	 * 인터페이스 명
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	public static String getInterfaceName(Map dataMap) throws Exception {
		String interfaceName = ""; 
		String interfaceType = (String) dataMap.get(colA);
		
		if (interfaceType.equals("GAKYKNEW")) {
			interfaceName = "[가격]";
		} else if (interfaceType.contains("CUSA")) {
			interfaceName = "[협정]";
		}
		
		return interfaceName;
		
	}
	
	/**
	 * getFileNameToDocStleSe
	 * Get 파일명 TO 문서형태 
	 * ex) IMPRES2022030122.txt ==> ENCOM_IMP
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String getFileNameToDocStleSe(String fileName) throws Exception {
		Map docStleSeMap = getdocStleSeFileNameMapping();
		String docStleSe = "";

		if (fileName.startsWith("EXPRE")) {
			// 엔컴 수출 송수신
			docStleSe = (String) docStleSeMap.get("EXPRE");
		} else if (fileName.startsWith("IMPRE")) {
			// 엔컴 수입 송수신
			docStleSe = (String) docStleSeMap.get("IMPRE");
		} else if (fileName.startsWith("GAKYKRE")) {
			// 엔컴 가격 송수신
			docStleSe = (String) docStleSeMap.get("GAKYKRE");
		} else if (fileName.startsWith("CUSAGRRE")) {
			// 엔컴 협정관세 송수신
			docStleSe = (String) docStleSeMap.get("CUSAGRRE");
		}
		
		return docStleSe;
	}
	/**
	 * 엔컴 송신 데이터 유효성 체크
	 * 
	 * @param typeList
	 * @param dataList
	 * @return
	 * @throws Exception
	 */
	public static List validCheck(List typeList, List dataList, List errorList) throws Exception {
		List list = new ArrayList();
		String excelRowNm = "";
		try {
			for (int i = 0; i < typeList.size(); i++) {
				Map<String, String> typeMap = (Map<String, String>) typeList.get(i);
	
				excelRowNm = typeMap.get("EXCEL_ROW_NM"); // 컬럼 명
				String excelRowAdresCol = typeMap.get("EXCEL_ROW_ADRES_COL"); // 컬럼 주소 (COL_A, COL_B...)
				String colTyp = typeMap.get("COL_TYP"); // 컬럼 타입
				String essnAt = typeMap.get("ESSN_AT");	// 필수 여부
				int colLt = Integer.parseInt(String.valueOf(typeMap.get("COL_LT"))); // 컬럼 사이즈
				int colDcpoLt = Integer.parseInt(String.valueOf(typeMap.get("COL_DCPO_LT"))); // 컬럼 소수점 사이즈
				int dataSequence = 0;
				Map errorMap = null;
				for (int j = 0; j < dataList.size(); j++) {
					Map<String, String> dataMap = (Map<String, String>) dataList.get(j);
					String enpsMgmtNo = String.valueOf(dataMap.get(getEncomKey(dataMap)));	// 업체관리번호
					dataSequence = getErrorListSequence(enpsMgmtNo, dataMap, errorList);	// Get Sequence in Error List
					errorMap = getErrorMap(dataSequence, errorList);
	
					boolean result = true;
					String colValue = StringHelper.null2string(dataMap.get(excelRowAdresCol), "");
					colValue = colValue.equals("null") ? "" : colValue;
	//				colValue = StringUtils.isEmpty(colValue) ? "" : colValue;
	//				colValue = StringUtils.isBlank(colValue) ? "" : colValue;
	//				
//					if(log.isDebugEnabled()) log.debug("================================================================");
//					if(log.isDebugEnabled()) log.debug("colValue:" + colValue + ":");
//					if(log.isDebugEnabled()) log.debug("colTyp:" + colTyp + ":");
//					if(log.isDebugEnabled()) log.debug("colLt:" + colLt + ":");
//					if(log.isDebugEnabled()) log.debug("colDcpoLt:" + colDcpoLt + ":");
					if (!colValue.equals("")) {
						int colValueLt = colValue.length();	// 값 길이
						if (colTyp.equals("CHAR")) {
							// CHAR 문자
							result = checkLength(excelRowNm, excelRowAdresCol, dataMap, errorMap, colValueLt, colLt, false);
						} else if (colTyp.equals("DEC") || colTyp.equals("CURR")) {
							int intColValue = (int) Double.parseDouble(colValue); 					// 정수만
							int intColValueLt =  (int)(Math.log10(intColValue)+1);					// 정수 길이
							int colValueDcpoLt = 0;
							double dcopColValue = 0; // 소수점만 ex) 0.123
							// DEC 숫자, CURR 실수
							boolean chk = checkNumber(excelRowNm, dataMap, errorMap, colValue); 	// 숫자 체크

							/*
							 * if(log.isDebugEnabled()) log.debug("intColValue:" + intColValue + ":");
							 * if(log.isDebugEnabled()) log.debug("intColValueLt:" + intColValueLt + ":");
							 */
							if (colTyp.equals("DEC")) {
								// 정수만 길이 체크
								result = checkLength(excelRowNm, excelRowAdresCol, dataMap, errorMap, intColValueLt, colLt, false);
							}
							// 소수점 길이 체크
							if (colTyp.equals("CURR")) {
								// (주의) BigDecimal 변경 후 계산 해야 소수점이 깨지지 않음.
								BigDecimal colValueDecimal = new BigDecimal(colValue);
								BigDecimal intColValueDecimal = new BigDecimal(intColValue);
								 
								dcopColValue = colValueDecimal.subtract(intColValueDecimal).doubleValue();
								colValueDcpoLt = Double.toString(dcopColValue).length() - 2; 		// 소수점 길이, -2 ==> 0.
								/*
								 * if(log.isDebugEnabled()) log.debug("dcopColValue:" + dcopColValue + ":");
								 * if(log.isDebugEnabled()) log.debug("colValueDcpoLt:" + colValueDcpoLt + ":");
								 */
								result = checkLength(excelRowNm, excelRowAdresCol, dataMap, errorMap, colValueDcpoLt, colDcpoLt, true);
							}
						} else if (colTyp.equals("DATS")) {
							// DATS 날짜
						} else {

						}
					} else {
						// 필수 체크
						if (essnAt.equals("Y")) {
							errorMap.put(encomTrntUpdate, errorMap.get(encomTrntErr) + getInterfaceName(dataMap) + excelRowNm + ":" + " 필수" + newLine);
						}
					}				
				}
				
				errorList.set(dataSequence, errorMap);
			}
		} catch (Exception e) {
			throw new Exception("엔컴 송신 데이터 유효성 체크 EnComSupporter.validCheck Error ["+excelRowNm+"], " + e.getMessage());		
		}
			 
		return errorList;
	}

	/**
	 * 길이 체크
	 * 
	 * @param excelRowNm  컬럼 명
	 * @param map         컬럼 Map
	 * @param valueLength 값 길이
	 * @param standLength 기준 길이
	 * @param pointYn     소수점 여부 (true:소수점)
	 * @return
	 * @throws Exception 
	 */
	public static boolean checkLength(String excelRowNm, String excelRowAdresCol, Map dataMap, Map errorMap,
			int valueLength, int standLength, boolean pointYn) throws Exception {
		String errMessage = "";

		if (excelRowNm.equals("순중량")) {
			System.out.println(excelRowNm);
		}
		if (valueLength > standLength) {
			if (pointYn) {
				double colValue = Double.parseDouble(dataMap.get(excelRowAdresCol).toString());
				DecimalFormat df = new DecimalFormat("#." + StringUtils.repeat("#", standLength));
								
				// 소수점이 긴 경우 데이터를 변경 하여 송신 함.(엔컴 기준으로)
				dataMap.put(excelRowAdresCol, df.format(colValue));
				errorMap.put(encomTrntUpdate, errorMap.get(encomTrntErr) + getInterfaceName(dataMap) + excelRowNm + ":" + "소수점 길이 변경(" + colValue + ":" + df.format(colValue) + ")" + newLine);
			} else {
				errorMap.put(encomTrntErr, errorMap.get(encomTrntErr) + getInterfaceName(dataMap) + excelRowNm + ":" + "길이 오류(" + valueLength + ":" + standLength + ")" + newLine);
				return false;
			}
		}
		return true;
	}

	/**
	 * 숫자 체크
	 * 
	 * @param s
	 * @return
	 * @throws Exception 
	 */
	public static boolean checkNumber(String excelRowNm, Map dataMap, Map errorMap, String s) throws Exception {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			errorMap.put(encomTrntErr, errorMap.get(encomTrntErr) + getInterfaceName(dataMap) + excelRowNm + ":" + "숫자 아님" + newLine);
			return false;
		}
	}

	/**
	 * Create Error List
	 * @param dataList
	 * @return
	 * @throws Exception 
	 */
	public static List createErrorList(Map map, List dataList) throws Exception {		
		List errorList = new ArrayList();
		
		String companyCd = StringHelper.null2void(map.get("COMPANY_CD"));
		String docStleSe = StringHelper.null2void(map.get("DOC_STLE_SE"));	// 문서형태구분
		String userId = StringHelper.null2string(map.get("USER_ID"), StringHelper.null2void(map.get("SESSION_USER_ID")));
		String impExpCol = "";
		
		if (docStleSe.equals("ENCOM_EXP")) {
			impExpCol = "EXP_DCLRT_SN";	// 수출			
		} else {
			impExpCol = "IMP_DCLRT_SN";	// 수입
		}		
		
		for (int i = 0; i < dataList.size(); i++) {
			Map<String, String> dataMap = (Map<String, String>) dataList.get(i);
			Map errorMap = new HashMap();
			
			errorMap.put("COMPANY_CD", companyCd);
			errorMap.put(impExpCol, StringHelper.null2void(String.valueOf(dataMap.get(impExpDclrtSn))));
			errorMap.put(dataSeq, i);
			errorMap.put("DOC_STLE_SE", docStleSe);
			errorMap.put(getEncomKey(dataMap), StringHelper.null2void(String.valueOf(dataMap.get(getEncomKey(dataMap)))));
			errorMap.put(encomTrntErr, StringHelper.null2void(dataMap.get(encomTrntErr)));
			errorMap.put(encomTrntUpdate, StringHelper.null2void(dataMap.get(encomTrntUpdate)));
			errorMap.put("USER_ID", userId);
			errorMap.put("INIT_YN", "N");

			errorList.add(errorMap);
		}				
		
		return errorList;
	}
	

	/**
	 * 
	 * @param dataSequence
	 * @param errorList
	 * @return
	 */
	public static Map getErrorMap(int dataSequence, List errorList) {		
		Map<String, String> errorMap = (Map<String, String>) errorList.get(dataSequence);
		
		return errorMap;
	}	

	/**
	 * Get Sequence in Error List
	 * 
	 * @param sttNo
	 * @param dataList
	 * @return
	 * @throws Exception 
	 */
	public static int getErrorListSequence(String enpsMgmtNo, Map dataMap, List errorList) throws Exception {		
		int dataSequence = 0;
		String val;
		
		for (int j = 0; j < errorList.size(); j++) {
			Map<String, String> errorMap = (Map<String, String>) errorList.get(j);

			val = errorMap.get(getEncomKey(dataMap));
			if (enpsMgmtNo.equals(val)) {
				dataSequence = Integer.parseInt(String.valueOf(errorMap.get(dataSeq)));
				break;
			}
		}

		return dataSequence;
	}
	
	/**
	 * 엔컴 송신 파일 생성 (오류건 제외)
	 * 
	 * @param localFileFullName 파일 전체 경로
	 * @param txtCreateList	파일 생성 전체 리스트
	 * @param errorList	에러 List 
	 * @return Error No list
	 * @throws Exception
	 */
	public static void listToCsvFile(String localFileFullName, List txtCreateList, List errorList) throws Exception {
		try {
			List finalTxtList = new ArrayList();				// 최종 생성 파일
			List finalTxtOrderList = new ArrayList();			// 최종 생성 파일(신고번호 순서)
			List keyValArr = new ArrayList<String>();
			
			for (int i = 0; i < txtCreateList.size(); i++) {
				List txtList = (List) txtCreateList.get(i);
				for (int k = 0; k < txtList.size(); k++) {
					Map<String, String> txtMap = (Map<String, String>) txtList.get(k);
					String encomKey1 = String.valueOf(txtMap.get(encomKey));
					
					// 오류 건 삭제 
					for (int j = 0; j < errorList.size(); j++) {
						Map<String, String> errorMap = (Map<String, String>) errorList.get(j);
						String encomErrorKey1 = errorMap.get(getEncomKey(errorMap));
						String encomTrntErr1 = errorMap.get(encomTrntErr);
						
						// 오류건은 제거
						if (encomKey1.equals(encomErrorKey1) && !encomTrntErr1.isEmpty()) {
							txtMap.clear();
						}
						
						txtMap.remove(impExpDclrtSn);
					}
					
					// ENTR_ALL_SN 필드 삭제
					txtMap.remove(impExpDclrtSn);	
					finalTxtList.add(txtMap);
				}				
				
				// List to TXT 파일 쓰기
				// CSVSupporter.listToCsvFile(localFileFullName, txtList, "^", false);				
			}
			
			System.out.println(finalTxtList);
			
			keyValArr = getListKeyArray(localFileFullName, txtCreateList);
			
			for (int i = 0; i < keyValArr.size(); i++) {
				String keyVal = (String) keyValArr.get(i);
				
				for (int j = 0; j < finalTxtList.size(); j++) {
					Map<String, String> finalTxtMap = (Map<String, String>) finalTxtList.get(j);
					String subKeyVal = finalTxtMap.get(getEncomNameKey(localFileFullName));
					if (keyVal.equals(subKeyVal)) {
						finalTxtOrderList.add(finalTxtMap);
					}
				}				
			}
			
			// txt 파일 생성			
			if (finalTxtOrderList.size() > 0) {
				CSVSupporter.listToCsvFile(localFileFullName, finalTxtOrderList, "^", false);	
			}
		} catch (Exception e) {
			throw new Exception("엔컴 송신 파일 생성 (오류건 제외) EnComSupporter.listToCsvFile Error, " + e.getMessage());		
		}
	}
	
	/***
	 * getListKeyArray
	 * Get 전체 리스트에서 KEY ex) 신고번호(수입,수출), 제출번호(협정) 
	 * @param localFileFullName	txt 파일 명
	 * @param txtCreateList		txt 파일 생성 리스트
	 * @param errorList
	 * @return
	 * @throws Exception
	 */
	public static List getListKeyArray(String localFileFullName, List txtCreateList) throws Exception {
		try {
			List keyValArr = new ArrayList<String>();
			List keyValReturnArr = new ArrayList<String>();
			String keyCol = ""; 
					
			keyCol = getEncomNameKey(localFileFullName);
				
			if (txtCreateList.size() > 0) {
				for (int i = 0; i < 1; i++) {	// 공통, 첫번째만 
					List txtList = (List) txtCreateList.get(i);
					for (int k = 0; k < txtList.size(); k++) {
						Map<String, String> txtMap = (Map<String, String>) txtList.get(k);
						String keyVal = "";
						
						if (i == 0) {
							keyVal = (String) txtMap.get(keyCol);
							keyValArr.add(keyVal);
						}
					}
				}
			}

			for (int k = 0; k < keyValArr.size(); k++) {
				if (keyValArr.get(k) != null) {
					keyValReturnArr.add(keyValArr.get(k));
				}
			}
			
			return keyValReturnArr;
			
		} catch (Exception e) {
			throw new Exception("Get 전체 리스트에서 KEY, EnComSupporter.getListKeyArray Error, " + e.getMessage());	
		}
	}
	
	
	
	/**
	 * 오류 없는 신고서 List
	 * @param errorList
	 * @return
	 * @throws Exception
	 */
	public static List errorNoList(List errorList) throws Exception {
		List errorNoList = new ArrayList();
		int i = 0;
		for (int j = 0; j < errorList.size(); j++) {
			Map<String, String> errorMap = (Map<String, String>) errorList.get(j);
			String encomTrntErr1 = errorMap.get(encomTrntErr);
			if (encomTrntErr1.equals("")) {
				errorNoList.add(errorMap);
			}
		}
		return errorNoList;	
	}
	
	/**
	 * Get Key 컬럼
	 * 수입,수출 B컬럼
	 * 가격,협정 C컬럼
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	public static String getEncomKey(Map dataMap) throws Exception {
		String keyCol;
		String interfaceType = StringHelper.null2void(dataMap.get(colA));
		String docStleSe;
		
		// Error List
		if (interfaceType.equals("")) {
			docStleSe = (String) dataMap.get("DOC_STLE_SE");
			
			if (docStleSe.equals("ENCOM_IMP") || docStleSe.equals("ENCOM_EXP")) {
				return encomKey;
			} else {
				return colC;
			}
		}
		
		if (interfaceType.equals("GAKYKNEW") || interfaceType.equals("CUSAGREEM") || interfaceType.equals("CUSAGREED")
				|| interfaceType.equals("CUSAGREEDHR")) {
			// 가격, 협정관세 신고서
			keyCol = colC;
		} else {
			keyCol = encomKey;
		}

		return keyCol;
	}	
	
	/**
	 * getEncomNameKey
	 * Get Key 컬럼
	 * 수입,수출 B컬럼
	 * 가격,협정 C컬럼
	 * @param dataMap
	 * @return
	 * @throws Exception
	 */
	public static String getEncomNameKey(String localFileFullName) throws Exception {
		String keyCol;
		
		if (localFileFullName.contains("CUSAG") || localFileFullName.contains("GAKYK")) {
			// 협정, 가격
			keyCol = colC;			// COL_C
		} else {
			// 수입, 수출
			keyCol = encomKey;		// COL_B
		}

		return keyCol;
	}	
}