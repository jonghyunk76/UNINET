package com.yni.fta.common.tools;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.yni.frame.config.Configurator;
import kr.yni.frame.config.ConfiguratorFactory;
import kr.yni.frame.util.AES256Util;
import kr.yni.frame.util.StringHelper;
import sun.misc.BASE64Decoder;

/**
 * String 공통 Util
 * 
 */
public class StringUtil {
	
    /**
     * split 문자내에 문자 추가 ex) a,b,c ==> 'a','b','c'
     * 
     * @param str
     *                    원본 문자
     * @param seperators
     *                    구분자
     * @param addval
     *                    추가문자
     * @return ex) StringUtil.splitStringAdd("a,b,c", ",", "'")
     */
    public static String splitStringAdd(String str, String seperators, String addval) {
        String val = "";
        String[] strSplit = null;
        
        if (str.equals("")) {
            return "";
        } else {
            strSplit = str.split(seperators);
            for (int i = 0; i < strSplit.length; i++) {
                val = val + addval + strSplit[i] + addval + ",";
            }
            val = val.substring(0, val.length() - 1);
        }
        
        return val;
    }
    
    public static String getClobString(Map map, String key) throws Exception {
        String val = "";
        Clob clob = (Clob) map.get(key);
        
        if (clob != null) {
            Reader input = clob.getCharacterStream();
            char[] buffer = new char[1024];
            int byteRead;
            StringBuilder output = new StringBuilder();
            while ((byteRead = input.read(buffer, 0, 1024)) != -1) {
                output.append(buffer, 0, byteRead);
            }
            
            val = output.toString();
        }
        return val;
    }
    
    /**
     * <p>
     * 입력받은 날짜가 유효한지 체크
     * <p>
     * 
     * @param yyyymmdd
     * @return
     * @throws Exception
     */
    public static boolean isDate(String val) {
        String dt = val.substring(0, 4) + "-" + val.substring(4, 6) + "-" + val.substring(6, 8);
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            Date df2 = df.parse(dt);
        } catch (ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    
    /**
     * <p>SHA128로 암호화</p>
     * SHA128은 단방향 암호화로 복호화가 불가능하므로 비밀번호와 같은 데이터에만 적용할 것
     *  
     * @param plainText
     * @return
     */
    public static String getSHA128(String plainText){
		try {
			Configurator configurator = ConfiguratorFactory.getInstance().getConfigurator();
			String entype = configurator.getString("application.context.charset");
			
			if(entype == null || entype.isEmpty()) entype = "UTF-8";
			
			byte[] bytes = plainText.getBytes(Charset.forName(entype));

			byte[] result = null;
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			
			// 해쉬값 업데이트
			md.update(bytes);
			// 바이트 배열로 해쉬값 구하기
			result = md.digest();
			
			// 암호화된 값을 문자열로 변환 후 반환함
			return Base64.getEncoder().encodeToString(result);
		} catch (Exception e){
			e.printStackTrace();
			
			return null;
		}
	}
    
    /**
	 * Desc : 파일을 문자열로 변환 
	 * 
	 * @Method Name fileBase64Decoder
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String encodeBase64String(byte[] file) throws IOException {
		String byteStr = Base64.getEncoder().encodeToString(file);
		
		return byteStr;
	}
	
    /**
	 * Desc : 문자열을 파일로 변환 
	 * 
	 * @Method Name fileBase64Decoder
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] fileBase64Decoder(String file) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] fileByte = decoder.decodeBuffer(file);
		
		return fileByte;
	}
	
	/**
	 * List<Map<String, Object>>에서 Map의 값을 암호화하거나 복호화한 후 리턴합니다. 
	 * 
	 * @param list 
	 * @param secureKey 암호화 key
	 * @param type 암/복호화 (암호화:encode, 복호화:decode)
	 * @return
	 * @throws Exception
	 */
	public static List aes256ToList(List list, String secureKey, String type) throws Exception {
		if(secureKey != null) {
			AES256Util aes = new AES256Util(secureKey);
			
			for(int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				
				getAes256(map, aes, type);
			}
		}
		
		return list;
	}
	
	/**
	 * Map<String, String>의 값이 문자열인 경우 해당 값을 인코딩한 후 리턴합니다. 
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map aes256ToMap(Map map, String secureKey, String type) throws Exception {
		if(secureKey != null) {
			AES256Util aes = new AES256Util(secureKey);
			
			map = getAes256(map, aes, type);
		}
		
		return map;
	}
	
	/**
	 * Map<String, String>의 값이 문자열인 경우 해당 값을 인코딩한 후 리턴합니다. 
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map getAes256(Map map, AES256Util aes, String type) throws Exception {
		Iterator iter = map.entrySet().iterator();
		
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			
			String enValue = null;
			String key = entry.getKey().toString();
			String value = StringHelper.null2void(entry.getValue());
			
			if(value.toLowerCase().equals("null")) {
				value = "";
			}
			
			if(type.equals("encode")) {
				map.put(key, aes.encrypt(value)); // 암호화
			} else if(type.equals("decode")) {
				map.put(key, aes.decrypt(value)); // 암호화
			}
		}
		
		return map;
	}
	
	/**
     * 바이너리 바이트 배열을 스트링으로 변환
     * 
     * @param b
     * @return
     */
    public static String byteArrayToBinaryString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; ++i) {
            sb.append(byteToBinaryString(b[i]));
        }
        return sb.toString();
    }
 
    /**
     * 바이너리 바이트를 스트링으로 변환
     * 
     * @param n
     * @return
     */
    public static String byteToBinaryString(byte n) {
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) {
            if (((n >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return sb.toString();
    }
 
    /**
     * 바이너리 스트링을 바이트배열로 변환
     * 
     * @param s
     * @return
     */
    public static byte[] binaryStringToByteArray(String s) {
        int count = s.length() / 8;
        byte[] b = new byte[count];
        for (int i = 1; i < count; ++i) {
            String t = s.substring((i - 1) * 8, i * 8);
            b[i - 1] = binaryStringToByte(t);
        }
        return b;
    }
 
    /**
     * 바이너리 스트링을 바이트로 변환
     * 
     * @param s
     * @return
     */
    public static byte binaryStringToByte(String s) {
        byte ret = 0, total = 0;
        for (int i = 0; i < 8; ++i) {
            ret = (s.charAt(7 - i) == '1') ? (byte) (1 << i) : 0;
            total = (byte) (ret | total);
        }
        return total;
    }
    
    /**
     * 문자열에서 알파벳만 추출함
     *  
     * @param str
     * @return
     */
    public static String getAlphabet(String str) {
    	Pattern nonValidPattern = Pattern.compile("[a-zA-Z]");

    	Matcher matcher = nonValidPattern.matcher(str);
    	String result = ""; 
    	  
    	while(matcher.find()) {
    	    result += matcher.group(); 
    	}
    	
    	return result;
    }
    
}
