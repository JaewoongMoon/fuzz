/**
 * @ ExtractModel.java
 */
package extract.domain;

/**
 * <pre>
 * extract.domain
 * ExtractModel.java 
 * </pre>
 *
 * @brief	: 
 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
 * @Date	: 2017. 3. 23.
 */
public class ExtractModel {

	private ExtractType extractType = ExtractType.INPUT_FIELD_NAME;

	public ExtractType getExtractType() {
		return extractType;
	}

	public void setExtractType(ExtractType extractType) {
		this.extractType = extractType;
	}
	
	
}
