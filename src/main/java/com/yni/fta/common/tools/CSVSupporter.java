package com.yni.fta.common.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 통관 엔컴 송수신 지원
 * @author kjseo
 *
 */
public class CSVSupporter {
	private static Log log = LogFactory.getLog(CSVSupporter.class);

//	private static String toCSV(List<Map<String, Object>> list) {
//		List<String> headers = list.stream().flatMap(map -> map.keySet().stream()).distinct().collect(toList());
//		final StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < headers.size(); i++) {
//			sb.append(headers.get(i));
//			sb.append(i == headers.size() - 1 ? "\n" : ",");
//		}
//		for (Map<String, Object> map : list) {
//			for (int i = 0; i < headers.size(); i++) {
//				sb.append(map.get(headers.get(i)));
//				sb.append(i == headers.size() - 1 ? "\n" : ",");
//			}
//		}
//		return sb.toString();
//	}

	public static List<List<String>> readCSV() {
		// 반환용 리스트
		List<List<String>> ret = new ArrayList<List<String>>();
		BufferedReader br = null;

		try {
			br = Files.newBufferedReader(Paths.get("C:\\Users\\world\\Desktop\\employee1.csv"));
			// Charset.forName("UTF-8");
			String line = "";

			while ((line = br.readLine()) != null) {
				// CSV 1행을 저장하는 리스트
				List<String> tmpList = new ArrayList<String>();
				String array[] = line.split(",");
				// 배열에서 리스트 반환
				tmpList = Arrays.asList(array);
				System.out.println(tmpList);
				ret.add(tmpList);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * List to File (csv, txt)
	 * @param fileName
	 * @param list
	 * @param delimiter
	 * @param headerYn
	 * @throws Exception 
	 */
	public static void listToCsvFile(String fileName, List<LinkedHashMap<String, String>> list, String delimiter, boolean headerYn)
			throws Exception {
		List<String> headers = list.stream().flatMap(map -> map.keySet().stream()).distinct()
				.collect(Collectors.toList());
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(fileName, true);
			
			// header 생성 여부
			if (headerYn) {
				for (String string : headers) {
					writer.write(string);
					writer.write(delimiter);
				}
				writer.write("\r\n");
			}
			

			for (LinkedHashMap<String, String> lmap : list) {
				if (!lmap.isEmpty()) {
					for (Entry<String, String> string2 : lmap.entrySet()) {
						String string3 = String.valueOf(string2.getValue());
						string3 = string3.replace("\n", "").replace("\r", "").replace("null", "");
						writer.write(string3);
						writer.write(delimiter);
					}
					writer.write("\r\n");
				}
			}

		} catch (Exception e) {
			throw new Exception("CSVSupporter.listToCsvFile Error " + e.getMessage());			
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
			} catch (IOException e) {
				log.error("Error while flushing/closing fileWriter !!!", e);
			}
		}
	}


	/**
	 * Txt File to List
	 * @param fileName
	 * @param titleList
	 * @param delimiter
	 * @param sprtrCmmn 구분자(공통) ex) EXPO1:수출신고서, EXDTL:수출란
	 * @param entrTranrSetupSn
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public static List csvToList(String fileName, List<LinkedHashMap<String, String>> titleList, String delimiter,  String sprtrCmmn, String entrTranrSetupSn, String userId) throws Exception {

		List list =  new ArrayList();
		BufferedReader br = null;
		String compnayCd = null;

		try {

			File file = new File(fileName);
			br = new BufferedReader(new FileReader(file));

            String line = null;
            
            if (titleList.size() > 0) {
        		compnayCd = titleList.get(0).get("COMPANY_CD");
            }
            
            while ((line = br.readLine()) != null) {
                Map dataMap = new LinkedHashMap();
                String[] lineArray = line.split("\\"+delimiter);
                
                if (sprtrCmmn.equals("EXDTL")) {
                	System.out.println(sprtrCmmn);
                }
                // 구분자(공통), Line 정보, Txt파일의 첫번째 컬럼 ex) EXPO1:수출신고서, EXDTL:수출란
                String lineSprtrCmmn = lineArray[0].trim();
                
                if (lineSprtrCmmn.equals(sprtrCmmn)) { // && lineArray.length == titleList.size()
                    for (int i = 0; i < titleList.size(); i++) {
                    	Map<String, String> titleMap = (Map<String, String>) titleList.get(i);
                    	String title = titleMap.get("EXCEL_ROW_ADRES");
                    	
                    	try {
                    		dataMap.put(title, lineArray[i]);	
                    	} catch (IndexOutOfBoundsException  e) {
                    		dataMap.put(title, "");
                    	}
                    }
                    
                    dataMap.put("COMPANY_CD", compnayCd);
                    dataMap.put("ENTR_TRANR_SETUP_SN", entrTranrSetupSn);
                    dataMap.put("USER_ID", userId);    
                    
                    list.add(dataMap);
                }
                
            }
            
            br.close();

		} catch (Exception  e) {
			throw new Exception("CSVSupporter.csvToList Error " + e.getMessage());	
		} finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
		}
		
		return list;
	}
	
	public static void main(String[] args) throws IOException {

//		// createCSV Test
//		List<LinkedHashMap<String, String>> list = new ArrayList<>();
//		LinkedHashMap map1 = new LinkedHashMap<>();
//		map1.put("header1", "1한글1\n\r");
//		map1.put("header2", "2value2");
//		map1.put("header3", "3value3");
//		map1.put("header4", "4한글 입니다4\n\r");
//		LinkedHashMap map2 = new LinkedHashMap<>();
//		map2.put("header1", "");
//		map2.put("header2", "6value6");
//		map2.put("header3", "7value7");
//		map2.put("header4", "8value8");
//		list.add(map1);
//		list.add(map2);
//		createCSV("D://test/abced.txt", list, "^", false);
		
		String val = "#";
		
		
		System.out.println(StringUtils.repeat(val, 4));
		
		DecimalFormat format = new DecimalFormat("."+StringUtils.repeat(val, 4));

	}
}
