package parameterHack.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parameterHack.domain.DbmsType;
import parameterHack.domain.Fuzzer;
import parameterHack.domain.FuzzerType;

/**
 * @ ParameterHack.java
 */

/**
 * <pre>
 * 
 * ParameterHack.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 9. 16.
 */
public class ParameterHack {

	private Fuzzer fuzzer;
	
	public Fuzzer getFuzzer() {
		return fuzzer;
	}

	public void setFuzzer(Fuzzer fuzzer) {
		this.fuzzer = fuzzer;
	}

	
	/**
	 * @Method 	: makeFuzzerString
	 * @brief	: 일종의 어댑터 메서드. 퍼저타입에 따른 텍스트 변환을 수행한다.
	 * @author	: 문재웅(mjw8585@gmail.com)
	 * @Date	: 2016. 10. 5.
	 * @param src
	 * @param action
	 * @param value
	 * @return
	 */
	public String makeFuzzerString(String src){
		
		
		// validation 체크 
		if (src == null || fuzzer == null){
			throw new IllegalArgumentException("please insert argument!");
		}
		String[] params = src.split("&");
		
		FuzzerType fuzzerType = fuzzer.getFuzzerType();
		
		if(fuzzerType.equals(FuzzerType.XSS)){
			return xssScript(params);
		}else if (fuzzerType.equals(FuzzerType.XSAS)){
			return xsasScript(params);
		}else if (fuzzerType.equals(FuzzerType.CSRF)){
			return postForm(params, fuzzer.getParamValue());
		}else if (fuzzerType.equals(FuzzerType.SQL_INJECTION)){
			return normalType(params, sqlInjectionString(fuzzer.getDbmsType()));
		}else if (fuzzerType.equals(FuzzerType.BLIND_SQL_INJECTION)){
			return blindSqlInjectionString(params, fuzzer);
		}else{
			return normalType(params, fuzzer.getParamValue());
		}
	}
	
	private String sqlInjectionString(DbmsType dbmsType){
		
		if(dbmsType.equals(DbmsType.ORACLE)){
			return "' union select 1, 2 from dual --";
		}else if (dbmsType.equals(DbmsType.MY_SQL)){
			return "' union select 1, 2 # ";
		}else if (dbmsType.equals(DbmsType.MS_SQL)){
			return "' union select 1 --";
		}else{
			return "'";
		}
	}
	
	private String blindSqlInjectionString(String[] params, Fuzzer fuzzer){
		
		if(fuzzer.getFuzzerType().equals(FuzzerType.BLIND_SQL_INJECTION)){
			return "admin' and ascii(substr((select " + params[0] + 
					" from tablename where id='admin' ), 1,1)) < 120 #";
		}else{
			return "'";
		}
	}
	
	/**
	 * @Method 	: normalType
	 * @brief	: 모든 파라메터의 값을 value로 세팅한다.
	 * @author	: 문재웅(mjw8585@gmail.com)
	 * @Date	: 2016. 10. 4.
	 * @param params
	 * @param value
	 * @return
	 */
	private String normalType(String[] params, String value){
		String result = "";
		for(int i=0; i < params.length; i++){
			String param = params[i];
			String[] params2 = param.split("=");
			String newParam = params2[0] + "=" + value;
			result = getResult(result, i, "&", newParam);
		}
		return result;
	}
	
	/**
	 * @Method 	: xsasScript
	 * @brief	: 액션스크립트 XSS 테스트 스크립트를 생성한다.
	 * @author	: 문재웅(mjw8585@gmail.com)
	 * @Date	: 2016. 10. 4.
	 * @param params
	 * @return
	 */
	private String xsasScript(String[] params){
		String xss1 = "<mx:Script><![CDATA[trace('***";
		String xss2 = "***');]]></mx:Script>";
		String result = "";
		for(int i=0; i < params.length; i++){
			String param = params[i];
			String[] params2 = param.split("=");
			String newParam = params2[0] + "=" + xss1 + (i+1) + xss2;
			result = getResult(result, i, "&", newParam);
		}
		return result;
	}
	
	/**
	 * @Method 	: xssScript
	 * @brief	: XSS 테스트 스크립트를 생성한다.
	 * @author	: 문재웅(mjw8585@gmail.com)
	 * @Date	: 2016. 10. 4.
	 * @param params
	 * @return
	 */
	private String xssScript(String[] params){
		String xss1 = "\"/><script>alert(";
		String xss2 = ");</script>";
		String result = "";
		
		for(int i=0; i < params.length; i++){
			String param = params[i];  // ex) a=123
			// TYPE 1. 기존 파라메터 값은 초기화(공백)
			//String[] params2 = param.split("="); //  a, 123
			//String newParam = params2[0] + "=" + xss1 + (i+1) + xss2;
			
			// TYPE 2. 기존 파라메터 값 유지하면서 뒤에 xss 문자열 추가
			String newParam = param + xss1 + (i+1) + xss2;
			result = getResult(result, i, "&", newParam);
		}
		return result;
	}
	
	
	
	/**
	 * @Method 	: postForm
	 * @brief	: 파라메터 배열로부터 HTML 폼을 생성한다. 
	 * @author	: 문재웅(mjw8585@gmail.com)
	 * @Date	: 2016. 10. 4.
	 * @param params : 파라메터 배열
	 * @param action : 포워딩할 액션 경로
	 * @param value : 폼의 파라메터 값으로 일괄 적용할 값
	 * @return 만들어진 HTML 폼 
	 */
	private String postForm(String[] params, String value){
		String form = "<form name='csrfForm' action=''>\r\n";
		String str1 = "<input type='hidden' name='";
		String str2 = "' value='";
		String str3 = "' />";
		String result = form;
		for(int i=0; i < params.length; i++){
			String param = params[i];
			String[] params2 = param.split("=");
			String newParam = str1 + params2[0] + str2 + value + str3; 
			result = getResult(result, i, "\r\n", newParam);
		}
		result += "\r\n</form>";
		return result;
	}
	
	
	/**
	 * @Method 	: getResult
	 * @brief	: 
	 * @author	: 문재웅(mjw8585@gmail.com)
	 * @Date	: 2016. 10. 4.
	 * @param result
	 * @param idx
	 * @param chain : 스트링을 연결할 연결자 값 
	 * @param param
	 * @return
	 */
	private String getResult(String result, int idx, String chain, String param){
		if(idx == 0){
			result = result + param;
		}else{
			result = result + chain + param;
		}
		return result;
	}
	
}
