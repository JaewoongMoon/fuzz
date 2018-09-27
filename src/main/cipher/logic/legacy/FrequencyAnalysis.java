/**
 * @ FrequencyAnalysis.java
 */
package cipher.logic.legacy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * cipher.logic.legacy
 * FrequencyAnalysis.java 
 * </pre>
 *
 * @brief	: 
 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
 * @Date	: 2017. 7. 7.
 */
public class FrequencyAnalysis {

	//private List<Map<String, Double>> englishLetterFreq = new ArrayList<Map<String, Double>>();
	private Map<String, Double> englishLetterFreq = new HashMap<String, Double>();
	
	private LetterCounter letterCounter = new LetterCounter();
	
	/**
	 * 
	 */
	public FrequencyAnalysis() {
		englishLetterFreq.put("E", 12.70);
		englishLetterFreq.put("T", 9.06);
		englishLetterFreq.put("A", 8.17);
		englishLetterFreq.put("O", 7.51);
		englishLetterFreq.put("I", 6.97);
		englishLetterFreq.put("N", 6.75);
		englishLetterFreq.put("S", 6.33);
		englishLetterFreq.put("H", 6.09);
		englishLetterFreq.put("R", 5.99);
		
		englishLetterFreq.put("D", 4.25);
		englishLetterFreq.put("L", 4.03);
		englishLetterFreq.put("C", 2.78);
		englishLetterFreq.put("U", 2.76);
		
		englishLetterFreq.put("M", 2.41);
		englishLetterFreq.put("W", 2.36);
		englishLetterFreq.put("F", 2.23);
		englishLetterFreq.put("G", 2.02);
		
		englishLetterFreq.put("Y", 1.97);
		englishLetterFreq.put("P", 1.93);
		englishLetterFreq.put("B", 1.29);
		englishLetterFreq.put("V", 0.98);
		englishLetterFreq.put("K", 0.77);
		englishLetterFreq.put("J", 0.15);
		englishLetterFreq.put("X", 0.15);
		englishLetterFreq.put("Q", 0.10);
		englishLetterFreq.put("Z", 0.07);
	}
	
	public void sum(){
		//KeySet
		Set<String> keySet = englishLetterFreq.keySet();
		Double sum = 0.00;
		for(String key : keySet){
			sum += englishLetterFreq.get(key);
		}
		System.out.println("sum : " + sum);
	}
	
	
	private Map<String, Integer> getLetterCount(String src){
		return letterCounter.getLetterCount(src);
	}
	
	public static void main(String[] args) {
		FrequencyAnalysis a = new FrequencyAnalysis();
		a.sum();
	}
}
