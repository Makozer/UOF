/*
 * Benoetigt um das Profil zu loeschen, auf
 */

package database;

import java.sql.*;
import database.DatabaseConnection;
import game.utils.*;

public class FunktionaleAnforderungen {
	private static Connection con = null;
	
	/**
	 * zufaelliger Spieler wird geloescht fuer testseite
	 * @return
	 */
	public static boolean deleteRandomUser() {
		boolean success = false;

		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"DELETE FROM public.users WHERE userid IN (" + 
					"SELECT userid FROM public.users ORDER BY random()" + 
					"LIMIT 1);");
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0 ) {
				success = true; 
			} 
		} catch (SQLException e) {
			System.err.println("SQL-Fehler beim user löschen: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@deleteBenutzer: " + npe.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		return success;
	}
	
	/** Löschen eines Benutzers anhand eines displaynamens (unique). benoetigt um Profil zu loeschen
	 * @return Erfolgsstatus, ob die Entfernung aus der Datenbank funktionierte.
	 */
	public static boolean deleteUser(String displayname) {
		boolean success = false;
		try {
			con = DatabaseConnection.getConnection();
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(
				"DELETE FROM public.users "
				+ "WHERE displayname = ? ;"
			);
			pstmt.setString(1, displayname);
			System.out.println("[DEBUG] SQL-Statement zu Player löschen: " + pstmt.toString());
			int updatedRows = pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			
			if (updatedRows > 0 ) {
				success = true; 
			} 
		} catch (SQLException e) {
			System.err.println("SQL-Fehler beim Benutzer l�schen: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@deleteBenutzer: " + npe.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		return success;
	}
	
	/*
	 * gibt bis zu 5 zufaellige Benutzer aus - fuer Testseite
	 */
	public static String getBenutzer() {
		String result = "";
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT * FROM public.users NATURAL JOIN public.passwords NATURAL JOIN public.planets "
					+ "ORDER BY RANDOM() "
					+ "LIMIT 5	;"
			);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = ResultToTable.resultToTable(rs);
			}
		} catch (SQLException e) {
			System.err.println("Fehler beim Benutzer ausgeben: " + e.getMessage());
		}
		return result;
	}
	

	/**
	 * Gibt die Benutzeranzahl zur�ck. 
	 * Fuer Testzwecke
	 * 
	 * @return
	 */
	public static int getBenutzerAnzahl() {
		int zahl = 0;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
				"SELECT COUNT(*) FROM public.users;"
			);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				zahl = rs.getInt(1);
			} else {
				zahl = 0;
			}
		} catch (SQLException e) {
			System.err.println("Fehler beim Benutzer z�hlen: " + e.getMessage());
		}
		return zahl;
	}
	
	/**
	 * Benoetigt fuer Registration um Konflikte zu vermeiden
	 * @param displayname
	 * @return
	 */
	public static boolean isDisplayNameUsed(String displayname) {
		boolean used = false;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT * FROM public.users WHERE displayname = ? ;"
			);
			pstmt.setString(1, displayname);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				used = true;
			} 
		} catch (SQLException e) {
			System.err.println("Fehler beim Benutzer z�hlen: " + e.getMessage());
		}
		return used;
	}
	
	/**
	 * 
	 * Benoetigt fuer Registration um Konflikte zu vermeiden
	 *
	 * @param email
	 * @return
	 */
	public static boolean isEmailNameUsed(String email) {
		boolean used = false;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT * FROM public.users WHERE email = ? ;"
			);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				used = true;
			} 
		} catch (SQLException e) {
			System.err.println("Fehler beim Benutzer zaehlen: " + e.getMessage());
		}
		return used;
	}
	
	
}
