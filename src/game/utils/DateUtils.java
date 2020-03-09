package game.utils;

import java.util.*;

public class DateUtils {
	
	public static Date getDate(int year, int month, int day) {
		return getDate(year, month, day, 0, 0, 0, 0);
	}
	
	public static Date getDate(int year, int month, int day, int hour) {
		return getDate(year, month, day, hour, 0, 0, 0);
	}
	
	public static Date getDate(int year, int month, int day, int hour, int minute) {
		return getDate(year, month, day, hour, minute, 0, 0);
	}
	
	public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
		return getDate(year, month, day, hour, minute, second, 0);
	}
	
	public static Date getDate(int year, int month, int day, int hour, int minute, int second, int millisecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, (month - 1));
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		Date output = (Date)cal.getTime();
		return output;
	}
	
	public static Date getFutureDateByHours(int hours) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, hours);
		Date output = (Date) cal.getTime();		
		return output;
	}
	
	public static Calendar getTodayCalendar() {
		Date date = new Date();// the date instance
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static String getRemainingTimeAsString(Date future) {
		String output = "";
		
		Date now = new Date();
		
		long diff = future.getTime() - now.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		
		if (diffDays > 0) {
			output += diffDays + " Tage, " + diffHours + " Stunden, " + diffMinutes + " Minuten";
		} else {
			output += diffHours + ":" + diffMinutes + ":" + diffSeconds;
		}
		
		return output;
	}

}
