
package in.co.sunrays.proj4.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The use of this class is conversion of one data type into another
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */

public class DataUtility {
	// The use of this class is to convert one data type into another data type

	// Application date and time format
	public static String APP_DATE_FORMAT = "dd/MM/yyyy";
	public static String APP_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

	// Date and time formatter
	private static SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);
	private static SimpleDateFormat timeformatter = new SimpleDateFormat(APP_TIME_FORMAT);

	// getter of the String value and trims it if required
	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}

	}

	// Converts Object into String
	public static String getStringData(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	// Converts String into Integer
	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	// Converts String into Long
	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}

	/** why are we surrounding only date and time classes in try-catch */
	// Converts Date into String
	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {
		}
		return "";
	}

	// Converts String into Date
	public static Date getDate(String val) {
		Date date = null;
		try {
			return date = formatter.parse(val);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}

	public static Date getDate(Date date, int day) {
		return null;
	}

	// Converts String into Time
	public static Timestamp getTimestamp(String val) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp((timeformatter.parse(val)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	// Converts long to Time
	public static Timestamp getTimestamp(long l) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}

	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	public static void main(String[] args) {

	}

}
