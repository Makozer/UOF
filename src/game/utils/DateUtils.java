package game.utils;

import java.util.*;
import java.util.regex.Pattern;
import static game.settings.GameSettings.*;

public class DateUtils {
	
	public static void main(String[] args) {
		// STAMP TEST
		Date date = new Date();
		System.out.println(date);
		String stamp = DateUtils.dateToStamp(date);
		System.out.println(stamp);
		Date date2 = DateUtils.stampToDate(stamp);
		System.out.println(date2);
		String stamp2 = DateUtils.dateToStamp(date2);
		System.out.println(stamp2);
	}
	
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
	
	/** Generates a Date with a given timestamp
	 * @param stamp MUST LOOK LIKE 2020-03-20 01:20:58.000000+01
	 * @return Date with the stamp as time
	 */
	public static Date stampToDate(String stamp) {
		// Check if NULL
		if (stamp == null || stamp.length() == 0) {
			if (DEBUGMODE) {System.out.println("stamp==NULL||0 AT stampToDate");}
			return null;
		}
		
		String[] split = stamp.split( Pattern.quote( " " ) );
		String[] cal = split[0].split( Pattern.quote( "-" ) );
		int year = NumberUtils.stringAsInt(cal[0]);
		int month = NumberUtils.stringAsInt(cal[1]);
		int day = NumberUtils.stringAsInt(cal[2]);
		String[] time = split[1].split( Pattern.quote( ":" ) );
		int hour = NumberUtils.stringAsInt(time[0]);
		int min = NumberUtils.stringAsInt(time[1]);
		int sec = NumberUtils.stringAsInt(time[2].split(Pattern.quote("."))[0]);
		return getDate(year, month, day, hour, min, sec);
	}
	
	public static String dateToStamp(Date date) {
		String stamp = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		stamp += cal.get(Calendar.YEAR) + "-" + zeroFill(cal.get(Calendar.MONTH) + 1) + "-" + zeroFill(cal.get(Calendar.DAY_OF_MONTH)) + " ";
		stamp += zeroFill(cal.get(Calendar.HOUR_OF_DAY)) + ":" + zeroFill(cal.get(Calendar.MINUTE)) + ":" + zeroFill(cal.get(Calendar.SECOND)) + ".000000+01";
		return stamp;
	}
	
	private static String zeroFill(int n) {
		if (n < 10) {
			return "0" + n;
		} else {
			return "" + n;
		}		
	}

}
