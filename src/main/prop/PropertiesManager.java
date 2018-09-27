package prop;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

/**
 * @ PropertiesManager.java 
 */

/**
 * <pre>
 * 
 * PropertiesManager.java 
 * </pre>
 *
 * @brief	: 
 * @author	: 문재웅(mjw8585@gmail.com)
 * @Date	: 2016. 12. 26.
 */
public class PropertiesManager {

	
	private Locale locale;

	private Properties props;
	
	public PropertiesManager(){
		this.locale = Locale.KOREA; //Default
		props = readProperties();
	}
	
	public PropertiesManager(Locale locale){
		this.locale = locale;
		props = readProperties();
	}
	
	public Locale getLocale(){
		return locale;
	}
	
	private String getPropFileName(){
		String path = "/resources/prop/label_%s.properties";
		return String.format(path, 
				locale.getLanguage().toLowerCase());
	}
	
	private Properties readProperties(){
        // 프로퍼티 객체 생성
        Properties props = new Properties();
        
		try{
        	String propFile = getPropFileName();
        	//System.out.println(propFile);
        	InputStream is = PropertiesManager.class.getResourceAsStream(propFile);
        	props.load(new BufferedInputStream(is));
        	is.close();
        	
        }catch(Exception e){
            e.printStackTrace();
        }
		
	    return props;

	}
	
	public String getProperty(String messageId){
		return props.getProperty(messageId) ;
	}
	
}
