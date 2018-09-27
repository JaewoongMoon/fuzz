/**
 * @ ExtractType.java
 */
package extract.domain;

/**
 * <pre>
 * extract.domain
 * ExtractType.java 
 * </pre>
 *
 * @brief	: 
 * @author	: Jae-Woong Moon(mjw8585@gmail.com)
 * @Date	: 2017. 3. 23.
 */
public enum ExtractType {

	INPUT_FIELD_NAME(1),
	DOM_FILED_NAME(2)
	;
	
	private final int value;
	
	ExtractType(int value){
		this.value = value;
	}
	
	public int intValue(){
		return value;
	}
	
	public static ExtractType valueOf(int value){
		switch(value){
		case 1: return INPUT_FIELD_NAME;
		case 2: return DOM_FILED_NAME;
		default : throw new AssertionError("Unknown value : " +value);
		}
	}
}
