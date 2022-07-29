package stw.eai.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import stw.eai.encoding.Base64;
import stw.encryption.DES;

import com.siebel.data.SiebelPropertySet;
import com.siebel.eai.SiebelBusinessService;
import com.siebel.eai.SiebelBusinessServiceException;

public class Encryption extends SiebelBusinessService {
	
	public static void main(String[] args) {
		SiebelPropertySet inputs = new SiebelPropertySet();
		SiebelPropertySet outputs = new SiebelPropertySet();
		
		inputs.setProperty("Text", "jdbc:oracle:thin:@55.200.10.148:1527:CRMOLTPQ");
		
		Encryption enc = new Encryption();
		try {
			enc.doInvokeMethod("DES", inputs, outputs);
		} catch (SiebelBusinessServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(outputs.getProperty("Result"));
		System.out.println(outputs.toString());
	}

	@Override
	public void doInvokeMethod(String methodName, SiebelPropertySet inputs,	SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		if("MD5".equals(methodName)){
			MD5(inputs,	outputs);
		}
		else if("DES".equals(methodName)){
			DES(inputs,outputs);
		}
		else{
			outputs.setProperty("end", "else");
		}
	}
	
	public void DES(SiebelPropertySet inputs,	SiebelPropertySet outputs) throws SiebelBusinessServiceException
	{
		String text		= "";
		String result	= "";
		try
		{
			text	= inputs.getProperty("Text");
			result	= new DES().encryption(text);
			
			outputs.setProperty("Result", result);
		}
		catch(Exception e)
		{
			throw new SiebelBusinessServiceException(e,"Encryption : DES : ",e.getMessage());
		}
		finally
		{
			
		}
	}

	public String RSA() throws NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException	{
		KeyPairGenerator 	generator	= null;
		KeyPair 			keyPair		= null;
		PublicKey			publicKey	= null;
		PrivateKey			privateKey	= null;

		try {
			generator	= KeyPairGenerator.getInstance("RSA");
			keyPair		= generator.generateKeyPair();
			publicKey	= keyPair.getPublic();
			privateKey	= keyPair.getPrivate();
			
			System.out.println("=====publicKey.toString()=====");
			System.out.println(publicKey.toString());
			System.out.println("=====publicKey.getAlgorithm()=====");
			System.out.println(publicKey.getAlgorithm());
			System.out.println("=====publicKey.getFormat()=====");
			System.out.println(publicKey.getFormat());
			System.out.println("=====publicKey.getEncoded()=====");
			System.out.println(publicKey.getEncoded());
			System.out.println("=====publicKey.hashCode()=====");
			System.out.println(publicKey.hashCode());
			System.out.println(privateKey.toString());

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			String text = "아~ 인생은 나그네길~";
			byte[] t0 = text.getBytes();

			System.out.println("P : " + new String(t0));
			byte[] b0 = cipher.doFinal(t0);
			System.out.println("E : " + readByteArray(b0));

			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] b1 = cipher.doFinal(b0);
			System.out.println("D : " + readByteArray(b1));
			System.out.println(new String(b1));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}


		return "";
	}

	public void MD5 (SiebelPropertySet inputs,	SiebelPropertySet outputs) throws SiebelBusinessServiceException {
		String value	= "";
		byte[] digest	= null;
		StringBuffer sb	= null;
		String md5		= "";

		outputs.setProperty("MD5",			"");
		outputs.setProperty("MD5BASE64",	"");
		try {
			value	= inputs.getProperty("Text");
			digest	= MessageDigest.getInstance("MD5").digest( value.getBytes("UTF-8") );

			sb		= new StringBuffer();
			for( int i = 0; i < digest.length; i++ )
			{ 
				sb.append( Integer.toString( ( digest[i] & 0xf0) >> 4, 16 ) ); 
				sb.append( Integer.toString( digest[i] & 0x0f, 16 ) );
			}

			md5	= sb.toString();
			outputs.setProperty("MD5", md5);
			outputs.setProperty("MD5BASE64", Base64.encodeToString(md5.getBytes("UTF-8"), 0));
		}
		catch (NoSuchAlgorithmException e) {
			outputs.setProperty("Error Message", e.getMessage());
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			outputs.setProperty("Error Message", e.getMessage());
			e.printStackTrace();
		}
		finally {
			digest	= null;
			sb		= null;
		}
	}
	
	private String readByteArray(byte[] b) {
		StringBuffer sb	= null;
		
		sb		= new StringBuffer();
		for( int i = 0; i < b.length; i++ )
		{ 
			sb.append( Integer.toString( ( b[i] & 0xf0) >> 4, 16 ) ); 
			sb.append( Integer.toString( b[i] & 0x0f, 16 ) );
		}
		
		return sb.toString();
	}

}
