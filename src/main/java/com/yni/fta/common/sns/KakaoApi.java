package com.yni.fta.common.sns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.yni.frame.util.JsonUtil;
import kr.yni.frame.util.StringHelper;

public class KakaoApi {
	private static Log log = LogFactory.getLog(KakaoApi.class);
	
	/**
	 * 카카오톡 인증 Token 가져오기
	 * 
	 * @param authorize_code
	 * @return
	 * @throws Exception
	 */
	public static String getAccessToken (Map map, String authorize_code) throws Exception {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=3c97ae83f85ee5037f0db28ebc969098");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost/fm/sm/sm7035_01/selectKakaLogin");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.debug("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.debug("response body : " + result);

            // Json parsing
            Map jmap = JsonUtil.getMap(result);

            access_Token = StringHelper.null2void(jmap.get("access_token"));
            refresh_Token = StringHelper.null2void(jmap.get("refresh_token"));

            log.debug("access_token : " + access_Token);
            log.debug("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch(Exception e) {
            log.error(e);
            throw e;
        }

        return access_Token;
    }
	
	/**
	 * 카카오톡 사용자 정보 가져오기
	 * 
	 * @param access_Token
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, Object> getUserInfo (String access_Token) throws Exception {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            log.debug("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            
            log.debug("response body : " + result);

            Map jmap = JsonUtil.getMap(result);
            
            log.debug("user info. = " + jmap.toString());
        } catch (IOException e) {
        	log.error(e);
            throw e;
        }

        return userInfo;
    }
}
