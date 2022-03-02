package com.health.bdd.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class CommonUtils {
	
	static Random rand = null;

	public static String getRandomString(String charecterSet, int length) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = charecterSet.charAt(new Random().nextInt(charecterSet.length()));
		}
		return new String(text);
	}
	
	public static String getUniqueName() {
		String charSet = "abcdefghijklmnopqrstuvwxyzBCDEFGHIJKLMNOPQRSTUVWXYZ";
		return getRandomString(charSet,7);
	}
	
	public static int getRandomInterger(int min, int max) {
		rand = new Random();
		int randomNum = rand.nextInt((max-min) +1) + min;
		return randomNum;
	}
	
	public static String getUserdefineDate(int numOfDay) {
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, numOfDay);
		Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("MM//dd//yyyy");
		return df.format(date);
	}
	
	//getTodaysDateInRequiredFormat
	//getFutureDateInRequiredFormat
	//getRandomAlphabeticString
	
}
