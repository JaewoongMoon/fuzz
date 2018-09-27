/**
 * @ DbmsType.java
 */
package parameterHack.domain;

/**
 * <pre>
 * parameterHack
 * DbmsType.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 10. 5.
 */
public enum DbmsType {

	ORACLE(1),
	MY_SQL(2),
	MS_SQL(3)
	;
	
	private final int value;
	
	DbmsType(int value){
		this.value = value;
	}
	
	public int intValue(){
		return value;
	}
	
	public static DbmsType valueOf(int value){
		switch(value){
		case 1: return ORACLE;
		case 2: return MY_SQL;
		case 3: return MS_SQL;
		default : throw new AssertionError("Unknown value : " +value);
		}
	}
}
