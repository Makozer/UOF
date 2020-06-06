package game.utils;

import static game.utils.NumberUtils.getRndInt;

public class RandomString {
	
	private static final String POSSIBLE_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static void main(String[] args) {
		// Testing ...
		for (int i = 0; i < 11; i++) {
			String rndString = RandomString.rndString();
			System.out.println(rndString.length() + "; " + rndString);
		}

	}
	
	public static String rndString() {
		return rndString(11, 22);
	}
	
	public static String rndString(int minchars, int maxchars) {
		int max = getRndInt(minchars, maxchars);
		int rndChar = 0;
		StringBuilder output = new StringBuilder();
		
		for (int i = 0; i < max; i++) {
			rndChar = getRndInt(0, POSSIBLE_CHARS.length() - 1);
			output.append(POSSIBLE_CHARS.charAt(rndChar));
		}
		return output.toString();
	}

}
