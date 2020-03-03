package game.utils;

import java.text.*;
import java.math.*;

public class NumberUtils {
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	public static String round(double input) {
		return df.format(input);
	}

}
