/**
 * @ ParameterHackTest.java
 * 
 */
package test.parameterHack.logic;



import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import parameterHack.domain.Fuzzer;
import parameterHack.domain.FuzzerType;
import parameterHack.logic.ParameterHack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * <pre>
 * parameterHack
 * ParameterHackTest.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 10. 4.
 */
public class ParameterHackTest {

	private final Logger log = Logger.getLogger(ParameterHackTest.class);
	
	private ParameterHack paramHack;
	
	@Before
	public void init(){
		paramHack = new ParameterHack();
	}
	
	@Test
	public void xssTest(){
		
		String src =  "loginFieldVO.companyid.value=security&loginFieldVO.userid.value=admin&loginFieldVO.password.value=pass123%21&dispatchURL=";
		Fuzzer fuzzer = new Fuzzer();
		fuzzer.setFuzzerType(FuzzerType.XSS);
		paramHack.setFuzzer(fuzzer);
		
		String result = paramHack.makeFuzzerString(src);
		assertThat(result, is("loginFieldVO.companyid.value=\"/><script>alert(1);</script>&loginFieldVO.userid.value=\"/><script>alert(2);</script>&loginFieldVO.password.value=\"/><script>alert(3);</script>&dispatchURL=\"/><script>alert(4);</script>"));
	}
	
	
	/* 자유롭게 output 구하고 싶을 때 사용*/ 
	@Test
	public void outputTest(){
		String paramSrc = "loginFieldVO.companyid.value=security&loginFieldVO.userid.value=admin&loginFieldVO.password.value=pass123%21&dispatchURL=";
		
		Fuzzer fuzzer = new Fuzzer();
		fuzzer.setFuzzerType(FuzzerType.CSRF);
		paramHack.setFuzzer(fuzzer);
		
		String result = paramHack.makeFuzzerString(paramSrc);
		System.out.println(result);
	}
}
