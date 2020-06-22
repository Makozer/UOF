/**
 * zusammengefasste fehlerbehandlung fuer forms - Cedric
 */

package database.utils;

import java.util.regex.Pattern;

import database.FunktionaleAnforderungen;

/**
 * behandelt mgl Fehler bei registrierung
 * @author cedri
 *
 */
public class FehlerManager {
	
	/**
	 * prueft ob checkboxen getickt sind für registrierung
	 * @param tick
	 * @return
	 */
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
	
	/**
	 * prueft alter auf format  für registrierung
	 * @param alter
	 * @return
	 */
	public static boolean pruefeAlter(String alter) {
		boolean valid = false; 
		// Baut die Regex stellenweise auf. �ber OR getrennt.
		if (Pattern.matches("^([0-9]|[0-9][0-9]|1[0-2][0-3])$", alter)) {
			valid = true; 
		}
		return valid; 
	}
	
	/**
	 * prueft email auf format  für registrierung
	 * @param email
	 * @return
	 */
	public static boolean pruefeEmail(String email) {
		boolean valid = false; 
		// W�rde auch schoenb@.de matchen. Daher hinter dem @ sollte es mindestens \w+ lauten.
		if (Pattern.matches("[a-zA-Z.-_+]{2,20}[@]{1,1}\\w*[.]{1,1}[a-z]{2,3}", email)) {
			valid = true;
		} 
		return valid; 
	}
	

	/**
	 *  für registrierung -  konfliktvermeidung
	 * @param displayname
	 * @return
	 */
	public static boolean isNameUsed(String displayname) {
		return FunktionaleAnforderungen.isDisplayNameUsed(displayname);
	}
	
	/**
	 * für registrierung -  konfliktvermeidung
	 * @param email
	 * @return
	 */
	public static boolean isEmailUsed(String email) {
		return FunktionaleAnforderungen.isEmailNameUsed(email);
	}
	
	/**
	 * tests if string is null
	 * @param test
	 * @return
	 */
	public static boolean isNull(String test) {
		boolean isNull = false;
		try {
		    test.equalsIgnoreCase(null);
		} catch (NullPointerException npe) {
		    isNull = true;
		}
		return isNull;
	}
}
