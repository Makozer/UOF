package database.utils;

import java.util.regex.Pattern;

import database.FunktionaleAnforderungen;
import database.utils.*;

public class FehlerManager {
	
	public static boolean checkbox(String tick) {
		boolean checked = false;
		// Checkbox ist entweder "on" oder "null" wenn kein Value gesetzt wird
		if (tick != null) {
			if (tick.equals("on")) {	
				checked = true;
			}
		}
		return checked;
	}
	

	public static boolean pruefeAlter(String alter) {
		boolean valid = false; 
		// Baut die Regex stellenweise auf. �ber OR getrennt.
		if (Pattern.matches("^([0-9]|[0-9][0-9]|1[0-2][0-3])$", alter)) {
			valid = true; 
		}
		return valid; 
	}
	
	public static boolean pruefeEmail(String email) {
		boolean valid = false; 
		// W�rde auch schoenb@.de matchen. Daher hinter dem @ sollte es mindestens \w+ lauten.
		if (Pattern.matches("[a-zA-Z.-_+]{2,20}[@]{1,1}\\w*[.]{1,1}[a-z]{2,3}", email)) {
			valid = true;
		} 
		return valid; 
	}
	
	public static boolean pruefeTelefonnr(String telefonnr) {
		boolean valid = false;
		if (Pattern.matches("([+]\\d\\d|0){1}\\d{4}[-/]?\\d{0,10}", telefonnr)) {
			valid = true;
		} 
		return valid; 
	}
	
	public static boolean isNameUsed(String displayname) {
		return FunktionaleAnforderungen.isDisplayNameUsed(displayname);
	}
	
	public static boolean isEmailUsed(String email) {
		return FunktionaleAnforderungen.isEmailNameUsed(email);
	}
	
}
