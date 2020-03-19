package database.utils;

import java.security.*;

public class PasswordUtils {
	
	public static void main(String[] args) {
		System.out.println(PasswordUtils.getSaltyPassword("test"));
		System.out.println(PasswordUtils.getSaltyPassword("test"));
	}
	
	
	public static String getSaltyPassword(String password) {
		// TODO BETTER PROTECTION!
		String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}
	

}
