/**
 * @ LetterCounter.java
 */
package cipher.logic.legacy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * cipher.logic.legacy
 * LetterCounter.java 
 * </pre>
 *
 * @brief	: 
 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
 * @Date	: 2017. 7. 7.
 */
public class LetterCounter {

	/**
	 * @Method 	: getLetterCount
	 * @brief	: 문자열의 각 캐릭터별 카운트 결과를 리턴한다.
	 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
	 * @Date	: 2017. 7. 7.
	 * @param src
	 * @return
	 */
	public Map<String,Integer> getLetterCount(String src){
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		for(int i=0; i < src.length(); i++){
			char ch = src.charAt(i);
			String c = (ch +"").toUpperCase();
			// 결과에 없다면 새로 넣는다.
			if(result.get(c) == null){
				result.put(c+"", 1);
			}else{
				result.replace(c+"", result.get(c)+1);
			}
		}
		return result;
	}
	
	public void printMap(Map map){
		Set<String> keySet = map.keySet();
		
		for(String key: keySet){
			System.out.println(key + " : " + map.get(key));
		}
	}
	
	/**
	 * @Method 	: getLetterList
	 * @brief	: 빈도 건수가 많은 순서대로 정렬한 리스트를 리턴한다. 
	 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
	 * @Date	: 2017. 7. 7.
	 * @return
	 */
	public List getLetterList(Map map){
		List list = new ArrayList(map.values());
		
		list.sort(Comparator.reverseOrder());
		for(Object item : list){
			System.out.println(item.toString());
		}
		
		return list;
	}
	
	
	public static void main(String[] args) {
		String src = "53‡‡†305))6*;4826)4‡.)4‡);806*;48†8¶60))85;1‡(; :‡*8†83(88)5*†;46(;88*96*?;8)*‡(;485);5*†*2:‡(;4956*2(5*-4)8¶8*;4069285);)6†8)4‡‡;1(‡9;48081;8:8‡1;48†85;4)485†528806*81(‡9;48;(88;4(‡?34;48)4‡;161;:188;‡?;";
		LetterCounter counter = new LetterCounter();
		Map map = counter.getLetterCount(src);
		//counter.printMap(map);
		counter.getLetterList(map);
	}
}
