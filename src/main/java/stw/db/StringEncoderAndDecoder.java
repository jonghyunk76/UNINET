package stw.db;

import stw.eai.common.Utils;
import stw.encryption.DES;


public class StringEncoderAndDecoder {

	public static void main(String[] args) {
		
		String value = "";
		
		// 암호화
		value = "jdbc:oracle:thin:@55.200.10.233:1527:CRMOLTPQ";
		showEncValue(value);
		
		// 복호화
		//value = "2bfbbe63d733e9bcec149a7575335f4c";
		//showDecValue(value);
	}

	// 암호화된 값 출력
	private static void showEncValue(String value) {
		String encValue = getEncString(value);
		System.out.println("--> [암호화] 입력값 : " + value + ", 암호화값 : " + encValue);
		printConfirmEncValue(value, encValue);
	}
	
	// 복호화된 값 출력
	private static void showDecValue(String value) {
		System.out.println("--> [복호화] 입력값 : " + value + ", 복호화값 : " + getDecString(value));
	}
	
	// 암호화
	private static String getEncString(String value) {
		return new DES().encryption(value);
	}

	// 복호화
	private static String getDecString(String value) {
		return new DES().decryption(value);
	}
	
	// 암호화된 값 확인 --> 암호화된 값을 복호화하여, 암호화 이전값과 같은지 비교한다.
	private static void printConfirmEncValue(String orgStr, String encStr) {
		System.out.println("----------------암호화값 확인중-----------------------");
		String result = (getDecString(encStr).equals(orgStr)) ? "[성공] 암호화값 확인하였습니다. " : "[실패] 확인에 실패하였습니다.";
		System.out.println("[암호값 확인 결과] => " + result);
		System.out.println("----------------암호화값 확인끝------------------------");
	}
	
	private static void urlDecTest() {
		String oldDecUrl = new DES().decryption("539a2c19b1f03f49db19b0f7e0597b7a624bc2e30ffc34e914073da3d9c63869f85b99d420600fbdce807a2f50b0d253");
		String orgStr = "jdbc:oracle:thin:@55.200.10.156:1527:CRMOLTPP";

		String url = new DES().encryption(orgStr);
		System.out.println(url);
		String decUrl = new DES().decryption(url);
		if (orgStr.equals(decUrl)) {
			System.out.println("equal");
		} else {
			System.out.println("Error!");
		}

		System.out.println("oldDecUrl : " + oldDecUrl);

		//String url = new DES().decryption("777fd63d744afd526f1dbb0cd916f8763da14995107aa93f01fe12e115f9a0b2e623de593d69f43fe7a786c9838cb7968ee36651948859f81b765bf024eb4171");
		//System.out.println(url);

	}
	
		
}
