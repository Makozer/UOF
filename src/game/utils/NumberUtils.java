package game.utils;

import java.text.*;

public class NumberUtils {
	
	private static DecimalFormat df1 = new DecimalFormat("0.0");
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	public static String round1dec(double input) {
		return df1.format(input);
	}
	
	public static String round2dec(double input) {
		return df2.format(input);
	}
	
	public static String shortNumber(int n) {		
		if (n > 1000000) { return round1dec(n/1000000.0) + "m";}
		if (n > 1000) { return round1dec(n/1000.0) + "k";}
		return "" + n;
	}

}
