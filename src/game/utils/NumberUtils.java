package game.utils;

import java.math.*;
import java.text.*;
import java.util.concurrent.*;

/**
 * This Class is used for several Utilitys for Numbers, like 0.340001  -> 0.34 etc.
 * @author Martin
 */
public class NumberUtils {
	
	private static DecimalFormat df1 = new DecimalFormat("0.0");
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	/** 
	 * Rounds down any given Double to 1 dec
	 * @param input any given Double
	 * @return String n.n
	 */
	public static String round1decToString(double input) {
		df1.setRoundingMode(RoundingMode.DOWN);
		return df1.format(input);
	}
	
	/** 
	 * Rounds down any given Double to 2 dec
	 * @param input any given Double
	 * @return String n.nn
	 */
	public static String round2decToString(double input) {
		df2.setRoundingMode(RoundingMode.DOWN);
		return df2.format(input);
	}
	
	
	/** 
	 * Converts big numbers to more pleasing and readable numbers like 1000000 -> 1 Million
	 * @param n Number
	 * @return String to use in WebInterface
	 */
	public static String shortNumber(int n) {		
		if (n > 10000000) { return round2decToString(n/1000000.0) + "m";}
		if (n > 100000) { return round1decToString(n/1000.0) + "k";}
		return "" + n;
	}
	
	/** 
	 * Rounds down any given Double to 2 dec
	 * @param input any given Double
	 * @return double n.nn
	 */
	public static double doubleTo2dec(double number) {
		double output = number;
		output = output * 100;
		output = (int)output;
		output = output / 100;
		return output;
	}
	
	/** 
	 * Rounds down any given Double to 3 dec
	 * @param input any given Double
	 * @return String n.nnn
	 */
	public static double doubleTo3dec(double number) {
		double output = number;
		output = output * 1000;
		output = (int)output;
		output = output / 1000;
		return output;
	}
	
	/** 
	 * Rounds down any given Double to 4 dec
	 * @param input any given Double
	 * @return String n.nnnn
	 */
	public static double doubleTo4dec(double number) {
		double output = number;
		output = output * 10000;
		output = (int)output;
		output = output / 10000;
		return output;
	}
	
	/**
	 * Generates a random number between min and max
	 * @param min int whole number
	 * @param max int whole number
	 * @return int whole number
	 */
	public static int getRndInt(int min, int max) {
		int output = 0;
		output = ThreadLocalRandom.current().nextInt(min, max + 1);
		return output;
	}
	
	
	/**
	 * Tries to convert a String to an int value
	 * @param string
	 * @return int
	 */
	public static int stringAsInt(String string) {
		int output = 0;
        try { output = Integer.parseInt(string); } 
        catch (NumberFormatException e) { 
        	output = 0; 
        	//e.printStackTrace(); 
        }
        return output;
	}

}
