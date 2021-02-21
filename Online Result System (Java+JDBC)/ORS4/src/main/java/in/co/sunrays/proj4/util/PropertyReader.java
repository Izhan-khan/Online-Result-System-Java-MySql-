package in.co.sunrays.proj4.util;

import java.util.ResourceBundle;
/**
 * This class helps to remove hard coding in the application
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */
public class PropertyReader {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.proj4.bundle.system");

	public static String getValue(String key) {
		
		try {
			key = rb.getString(key);
			//System.out.println(key+"=");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return key;
	}

	public static String getValue(String key, String param) {

		String msg = null;
		try {
			msg = rb.getString(key);
			msg = msg.replace("{0}", param);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return msg;
	}

	public static String getValue(String key, String[] param) {

		String msg = rb.getString(key);
		try {
			for (int i = 0; i < param.length; i++) {
				msg = msg.replace("{0}", param[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return msg;
		}
		return msg;

	}

	public static void main(String[] args) {
		String[] s = { "class1", "class2" };
		
		getValue("driver");
		
	}

}
