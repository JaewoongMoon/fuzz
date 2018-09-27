/**
 * @ AESEncryptor.java
 */
package cipher.logic;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <pre>
 * encryption
 * AESEncryptor.java 
 * </pre>
 *
 * @brief	: 
 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
 * @Date	: 2017. 6. 30.
 */
public class AESEncryptor {

	private static final int KEY_SIZE = 128; // 128 bit (= 16 byte)
	
	/**
	 * @brief KEY_SIZE 비트만큼을 바이트 배열에서 복사한다. (키배열의 값이 정해진 키의 크기를 벗어날 경우에 대비한 조정함수) 
	 * @param key : 키 배열
	 * @return
	 */
	private static byte[] adjustKey(byte[] key){
		return Arrays.copyOf(key, KEY_SIZE / 8); 
	}
	
	public static String encryptECB(String key, byte[] value){
		try{
			SecretKeySpec skeySpec = new SecretKeySpec(adjustKey(key.getBytes("UTF-8")), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING"); 
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			
			byte[] encrypted = cipher.doFinal(value);
			String encryptedMsg = Base64.getEncoder().encodeToString(encrypted);
			return encryptedMsg;
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decryptECB(String key, String encrypted){
		try{
			SecretKeySpec skeySpec = new SecretKeySpec(adjustKey(key.getBytes("UTF-8")), "AES");
			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			
			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
			return original;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String encryptCBC(String key, String initVector, String value){
		try{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(adjustKey(key.getBytes("UTF-8")), "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			
			byte[] encrypted = cipher.doFinal(value.getBytes());
			String encryptedMsg = Base64.getEncoder().encodeToString(encrypted);
			return encryptedMsg;
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decryptCBC(String key, String initVector, String encrypted){
		try{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(adjustKey(key.getBytes("UTF-8")), "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			
			byte[] bytes = Base64.getDecoder().decode(encrypted.getBytes());
			byte[] original = cipher.doFinal(bytes);
			return new String(original, StandardCharsets.UTF_8);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
