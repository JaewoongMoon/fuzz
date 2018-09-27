/**
 * @ EncodeType.java 
 */
package encoding.domain;

/**
 * <pre>
 * encoding.domain
 * EncodeType.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2017. 1. 31.
 */
public enum EncodeType {

	UNICODE(1),
	URL(2)
	;
	
	private final int value;
	
	EncodeType(int value){
		this.value = value;
	}
	
	public static EncodeType valueOf(int value){
		switch(value){
		case 1: return UNICODE;
		case 2: return URL;
		default : throw new AssertionError("Unknown value : " +value);
		}
	}
}
