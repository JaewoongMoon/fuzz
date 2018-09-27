/**
 * @ UnicodeManager.java 
 */
package encoding.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <pre>
 * json.logic
 * UnicodeManager.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2017. 1. 26.
 */
public class UnicodeHacker {

	public static String decode(String unicode)throws Exception {
		  StringBuffer str = new StringBuffer();

		  char ch = 0;
		  for( int i= unicode.indexOf("\\u"); i > -1; i = unicode.indexOf("\\u") ){
		   ch = (char)Integer.parseInt( unicode.substring( i + 2, i + 6 ) ,16);
		   str.append( unicode.substring(0, i) );
		   str.append( String.valueOf(ch) );
		   unicode = unicode.substring(i + 6);
		  }
		  str.append( unicode );

		  return str.toString();
	 }
	 
	 public static String encode(String unicode)throws Exception {
	      StringBuffer str = new StringBuffer();
	      
	      for (int i = 0; i < unicode.length(); i++) {
	       if(((int) unicode.charAt(i) == 32)) {
	        str.append(" ");
	        continue;
	       }
	       str.append("\\u");
	       str.append(Integer.toHexString((int) unicode.charAt(i)));
	       
	      }
	      
	      return str.toString();

	 }
	 
	 public static String lineFeedChanger(String src) {
			
		 // 다양한 경우의 수를 판단해서 진행되도록 개선 필요. . 
		 // 예를들어 dos/windows 라면 LF + CR 이지만, 리눅스는 LF 만 있을 것이다. 
		 String result = src.replaceAll("\n", System.getProperty("line.separator"));
		 return result;
	 }
	 
	 public static void main(String[] args) throws Exception {
		 // test 1. 
		 String str = encode("한글");
		 System.out.println(str);
		 System.out.println(decode(str));
		 
		 // test 2.
		 String testStr = "\u003Ctable\n  class=\u0027";
		 System.out.println(decode(testStr));  // 어라? 라인피드 처리도 자동으로 해주네?
		 
		 // test 3. 파일에서 읽기
		 String fileName = "new-line-test.txt";
		 String result = "";
		 try {
		      ////////////////////////////////////////////////////////////////
		      BufferedReader in = new BufferedReader(new FileReader(fileName));
		      String s;

		      while ((s = in.readLine()) != null) {
		    	  //System.out.println(s);
		    	  
		    	  result += s;
		      }
		      in.close();
		      ////////////////////////////////////////////////////////////////
		    } catch (IOException e) {
		        System.err.println(e); // 에러가 있다면 메시지 출력
		        System.exit(1);
		    }
		 System.out.println(lineFeedChanger(decode(result))); // 라인피드 처리가 안된다. 왜? 
		 // 제대로 처리가 안되네... 뭔가 버그가 있는 듯...
		 
		 // 라인피드 체인지 테스트 
		 String testStringwithLF = "This is\r\n new line";
		 System.out.println(lineFeedChanger(testStringwithLF));
		 
		 
		 System.out.println(encode("\""));
	 }
}
