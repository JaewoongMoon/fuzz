/**
 * @ Fuzzer.java 
 */
package parameterHack.domain;

/**
 * <pre>
 * parameterHack.domain
 * Fuzzer.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 10. 5.
 */
public class Fuzzer {

	private FuzzerType fuzzerType = FuzzerType.NORMAL;
	private DbmsType dbmsType = DbmsType.ORACLE; //default value
	private String actionPath;
	private String paramValue;
	
	public FuzzerType getFuzzerType() {
		return fuzzerType;
	}
	public void setFuzzerType(FuzzerType fuzzerType) {
		this.fuzzerType = fuzzerType;
	}
	public DbmsType getDbmsType() {
		return dbmsType;
	}
	public void setDbmsType(DbmsType dbmsType) {
		this.dbmsType = dbmsType;
	}
	public String getActionPath() {
		return actionPath;
	}
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
}
