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
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		Date output = cal.getTime();
		return output;
	}

}
