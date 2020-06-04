package game.utils;

import java.math.*;
import java.text.*;
import java.util.concurrent.*;

public class NumberUtils {
	
	private static DecimalFormat df1 = new DecimalFormat("0.0");
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	public static String round1decToString(double input) {
		df1.setRoundingMode(RoundingMode.DOWN);
		return df1.format(input);
	}
	
	public static String round2decToString(double input) {
		df2.setRoundingMode(RoundingMode.DOWN);
		return df2.format(input);
	}
	
	public static String shortNumber(int n) {		
		if (n > 10000000) { return round2decToString(n/1000000.0) + "m";}
		if (n > 100000) { return round1decToString(n/1000.0) + "k";}
		return "" + n;
	}
	
	public static double doubleTo2dec(double number) {
		double output = number;
		output = output * 100;
		output = (int)output;
		output = output / 100;
		return output;
	}
	
	public static double doubleTo3dec(double number) {
		double output = number;
		output = output * 1000;
		output = (int)output;
		output = output / 1000;
		return output;
	}
	
	public static double doubleTo4dec(double number) {
		double output = number;
		output = output * 10000;
		output = (int)output;
		output = output / 10000;
		return output;
	}
	
	public static int getRndInt(int min, int max) {
		int output = 0;
		output = ThreadLocalRandom.current().nextInt(min, max + 1);
		return output;
	}
	
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
