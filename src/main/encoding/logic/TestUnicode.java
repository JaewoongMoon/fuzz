/**
 * @ TestUnicode.java 
 */
package encoding.logic;

/**
 * <pre>
 * encoding.logic
 * TestUnicode.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2017. 1. 19.
 */
public class TestUnicode {

	public static void main(String[] args) {
		String str = "\\u0022\\u003C";
		str = str.replace("\\","");
		String[] arr = str.split("u");
		String text = "";
		for(int i = 1; i < arr.length; i++){
		    int hexVal = Integer.parseInt(arr[i], 16);
		    text += (char)hexVal;
		}
		System.out.println(text);
	}
}
