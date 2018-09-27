/**
 * @ JsonParser.java
 */
package parsing.logic;

import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * <pre>
 * parsing.logic
 * JsonParser.java 
 * </pre>
 *
 * @brief	: 
 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
 * @Date	: 2017. 7. 4.
 */
public class JsonParser {

	public static void main(String[] args) {
		// 쿼트(') 로 감싸는 것은 안되나 보다..
		
		String json = "{\"money\":10000}";
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
			//JSONArray array = jsonObj.get("");
			Set<String> keySet = jsonObj.keySet();
			for(String key : keySet){
				System.out.println(key);  //keyset의 아이템을 파라메터 명으로 보고....
			}
			// 참고 : https://walkinpcm.blogspot.jp/2016/03/java-json-json-parsing.html
			// TODO 뎁스가 무한정이므로 재귀함수로 다시 짜야겠다. 
					
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	}
}
