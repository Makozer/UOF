package game.utils;

import game.control.NewPlayerManager;
import game.player.PersonalData;
import game.player.Player;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
/*
 * creates a random Benutzer
 */
public class RandomPlayer {
	public static final String LOWER = "abcdefghijklmnopqrstuvxyz";
	public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZÖÄÜ";
	public static final String NBR = "0123456789";
	
	public static void main(String[] args) {
		
	}
	
	public static Player randomPlayer() {
		PersonalData pd = new PersonalData();
		
		pd.setDisplayName(randomName());
		pd.setPreName(randomName());
		pd.setSurName(randomName());
		pd.setEmail(randomEmail());
		pd.setBirthday(randomDate());
		pd.setCreated(randomDate());
		pd.setLastLogin(randomDate());
		
		System.out.println(pd.toString());
		
		return NewPlayerManager.createNewPlayer(pd, randomPas());
	}
	
	
	//creates random int in specified boundaries
	private static int randomInt(int lower, int upper) {
		return ThreadLocalRandom.current().nextInt(lower, upper+1);
	}

	private static String randomString(String chars, int n) 
    {  
        StringBuilder name = new StringBuilder(n); 
        for (int i = 0; i < n; i++) { 
            int index = (int)(chars.length() * Math.random()); 
            name.append(chars.charAt(index)); 
        } 
        return name.toString(); 
    } 
	private static String randomName() {
		return randomString(UPPER + LOWER, randomInt(3, 15));
	}
	private static String randomEmail() {
		return(randomString(LOWER, randomInt(5,10))
				+ "@" + randomString(LOWER, randomInt(2,9)) 
				+ "." + randomString(LOWER, randomInt(2,3)));
	}
	private static int randomAge() {
		return randomInt(1, 123);
	}
	
	/*
	 * Since the constructor's time unit is milliseconds, we're converting the 32-bit epoch seconds to milliseconds by multiplying it by 1000.
	 */
	private static Date randomDate() {
		return new Date(ThreadLocalRandom.current().nextInt() * 1000L);
	}
	
	private static String randomTel() {
		return randomString(NBR,randomInt(8,14));
	}
	
	private static String randomPas() {
		return randomString(UPPER + LOWER + NBR , randomInt(5,20));
	}
}

