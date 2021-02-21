package in.co.sunrays.proj4.util;

import java.util.Date;

/**
 * This class the given input is of data type as requirement
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */
public class DataValidator {

	// this class checks the input which we have given in the application if its
	// of certain type and converts it into required data type

	// this method checks if the given data type is Null
	public static boolean isNull(String val) {
		if (val == null || val.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	// This method checks if the given data is notNull
	public static boolean isNotNull(String val) {
		return !isNull(val);
	}

	// This method checks if the given data is an Integer
	public static boolean isInteger(String val) {
		if (isNotNull(val)) {
			try {
				int i = Integer.parseInt(val);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else
			return false;
	}

	// this method checks if the given data is of long type or not
	public static boolean isLong(String val) {
		if (isNotNull(val)) {
			try {
				long l = Long.parseLong(val);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else
			return false;
	}

	// this method checks if the given data is an email or not
	public static boolean isEmail(String val) {
		if (isNotNull(val)) {
			try {
				String emailregulexpression = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				return val.matches(emailregulexpression);
			} catch (Exception e) {
				return false;
			}
		} else
			return false;
	}

	// This method checks if the given data is of Date type or not
	/** what's the point of this method */
	public static boolean isDate(String val) {

		Date d = null;
		if (isNotNull(val)) {
			d = DataUtility.getDate(val);
		}
		return d != null;
	}

	public static void main(String[] args) {

	}

}