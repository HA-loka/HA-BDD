package com.health.bdd.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;

public class DateCommonUtils extends DateUtils {
	
	private static Date formatDateString(String value,String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = formatter.parse(value);	
		} catch (Exception e) {
			StringBuffer message = new StringBuffer();
			message.append("Date parsing failure value" + value + "Formatter: " + format);
			message.append("\n Caused by :"+ e.getMessage());
			Assert.fail(message.toString());
		}
		return date;
	}
	
	private static String formatDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	public static String formatDate(String value, String format) {
		return new SimpleDateFormat(format).format(formatDateString(value, format));
	}
	
	public static String formatDate(Calendar calendar, String pattern) {
		return formatDate(calendar.getTime(), pattern);
	}
	
	/**.
	 * @param : "Monday, March 5, 2019"
	 */
	public static String formatDate(String value, String sourcepattern, String destpattern) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat(sourcepattern).parse(value));
		} catch (Exception e) {
			StringBuilder message = new StringBuilder();
			message.append("Attempt converting :" + value);
			message.append("\n source format :" + sourcepattern);
			Log.info(message.toString());
			e.printStackTrace();
		}
		return formatDate(cal, destpattern);
	}
		
}
