/**
 * @ Extractor.java
 */
package extract.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import extract.domain.ExtractModel;
import extract.domain.ExtractType;

/**
 * <pre>
 * extraction
 * Extractor.java 
 * </pre>
 *
 * @brief	: 
 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
 * @Date	: 2017. 3. 16.
 */
public class Extractor {

	private ExtractModel model;
	
	
	
	public ExtractModel getModel() {
		return model;
	}



	public void setModel(ExtractModel model) {
		this.model = model;
	}



	public Set<String> getNameFields(String html, ExtractType extractType){
		Set<String> result = new HashSet<String>();
		if(extractType.equals(ExtractType.INPUT_FIELD_NAME)){
			// MODE 1. INPUT 필드 중에서만 찾는다. 
			String[] inputs = html.split("<input");
			
			for (int i=1; i < inputs.length; i++){
				String input = inputs[i];
				if(input.contains("name")){
					String[] splits = input.split(" name=\"");
					if(splits.length > 1){ //존재한다면
						String name = splits[1];
						int endIdx = name.indexOf("\"");
						name = name.substring(0, endIdx);
						result.add(name);
					}
				}
			}
		}else if (extractType.equals(ExtractType.DOM_FILED_NAME)){
			// MODE 2. SEARCH ALL NAME PROP
			String[] names = html.split(" name=\"");
			for (String name : names){
				if(name.length() < 300){
					int endIdx = name.indexOf("\"");
					if(endIdx > 0){
						result.add(name.substring(0, endIdx));
					}
				}
			}
		}
		return result;
	}
	
	
}
