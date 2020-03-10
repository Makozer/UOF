package game.utils;

import java.math.*;
import java.text.*;
import java.util.concurrent.*;

public class NumberUtils {
	
	private static DecimalFormat df1 = new DecimalFormat("0.0");
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	public static String round1dec(double input) {
		df1.setRoundingMode(RoundingMode.DOWN);
		return df1.format(input);
	}
	
	public static String round2dec(double input) {
		df2.setRoundingMode(RoundingMode.DOWN);
		return df2.format(input);
	}
	
	public static String shortNumber(int n) {		
		if (n > 1000000) { return round1dec(n/1000000.0) + "m";}
		if (n > 1000) { return round1dec(n/1000.0) + "k";}
		return "" + n;
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
