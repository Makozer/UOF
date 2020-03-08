package game.utils;

import java.text.*;

public class NumberUtils {
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	public static String round2dec(double input) {
		return df.format(input);
	}
	
	public static String shortNumber(int n) {		
		if (n > 1000000) { return (n/1000000.0) + "m";}
		if (n > 1000) { return (n/1000.0) + "k";}
		return "" + n;
	}

}
