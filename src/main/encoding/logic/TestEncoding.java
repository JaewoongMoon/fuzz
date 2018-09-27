/**
 * @ TestEncoding.java 
 */
package encoding.logic;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * <pre>
 * encoding.logic
 * TestEncoding.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2017. 1. 6.
 */
public class TestEncoding {

	public static void main(String[] args) throws UnsupportedEncodingException {
		//shiftJIS("そんな");
		//shiftJIS(new byte[] {(byte)0x82, (byte)0xbb});
		shiftJIS(hexStringToByteArray("82bb27"));   // (そ' 로 보인다)
		shiftJIS(hexStringToByteArray("82bb5c27")); // addslashes 함수를 지난 후의 문자열  (そ\' 로 보인다)
		utf8(hexStringToByteArray("e3819d27")); // (そ' 로 보인다)
		utf8(hexStringToByteArray("e3819d5c27")); // (そ\' 로 보인다)
		
		shiftJIS(hexStringToByteArray("e3819d5c27")); // (縺拿' 로 보인다. 즉, 쿼트(')가 살아났다!!)
		shiftJIS(hexStringToByteArray("e3819d5c22")); // %e3%81%9d%22 혹은 %E3%81%9D%22
		
		String s1 = "e29c93";
		utf8(hexStringToByteArray(s1));
		
		String s2 = "%E3%83%86%E3%82%B9%E3%83%88%E3%83%81%E3%83%BC%E3%83%A0";
		System.out.println(escapeURL(s2));
		utf8(hexStringToByteArray(escapeURL(s2)));
		
		utf8(hexStringToByteArray("e3819d5c22"));
		
		shiftJIS(hexStringToByteArray("e3819d5c22"));
	}
	
	public static String escapeURL(String url){
		return url.replaceAll("%", "");
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesArrayToHexString(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static void shiftJIS(byte[] bytes) throws UnsupportedEncodingException{
		String decoded = new String(bytes, "Shift_JIS");
		System.out.println(decoded);
	}
	
	public static void utf8(byte[] bytes) throws UnsupportedEncodingException{
		String decoded = new String(bytes, "UTF-8");
		System.out.println(decoded);
	}
	
	public static void utf8(String msg) throws UnsupportedEncodingException{
		byte[] bytes = msg.getBytes("UTF-8");
		String decoded = new String(bytes, "UTF-8");
		System.out.println(decoded);
	}
	
	public static void shiftJIS(String msg){
	   try {
	        //String test = "インターネットをもっと快適に";
	        byte[] bytes = msg.getBytes("Shift_JIS");
	        
	        byte[] inShiftJis = {
	                -125, 67, -125, -109, -125, 94, -127, 91, -125, 108, -125, 98, -125, 103, -126,
	                -16, -126, -32, -126, -63, -126, -58, -119, -11, -109, 75, -126, -55
	        };

	        String decoded = new String(bytes, "Shift_JIS");
	        System.out.println(decoded);
	        String fromShiftJis = new String(inShiftJis, "Shift_JIS");
	        System.out.println(fromShiftJis);
	    } catch (UnsupportedEncodingException e) {

	    }
	}
	
}
