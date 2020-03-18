package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.Connection;
import java.sql.ResultSet;

import game.player.PersonalData;
import java.time.LocalDate;
import java.sql.Date;

public class DBPeon {
	
	private static Connection con = null;
	
	public static void createNewMessage(int fromPlayerId, int toPlayerId, String title, String message) {
		
		// DataBase connect
		
		// Insert this message
		
		// Code der mithilfe der Parameter die Daten in die Datenbank eintr�gt
	}
	
	public static void createNewEvent() {
		
	}
	
	public static void updatePlanet() {
		
		
	}
	
	public static boolean insertBenutzer(PersonalData PersData) {
		boolean success = false;
		try {
			con = DatabaseConnection.getConnection();
		
			PreparedStatement pstmt = con.prepareStatement(
					
					"WITH uid AS (INSERT INTO player(displayname, prename, surname, email, timecreated, lastlogin, agbterms)" + 
							"VALUES (" 
							+ "?, "
							+ "?, "
							+ "?, "
							+ "?, "
							+ "?, "
							+ "?, "
							+ "?) "
							+ "RETURNING userid)" 
							+ "INSERT INTO passwoerter(userid, passwort) select userid, "
							+ "?"
							+ " from uid;"
							);
			
			pstmt.setString(1, PersData.getDisplayName());
			pstmt.setString(2, PersData.getPreName());
			pstmt.setString(3, PersData.getSurName());
			pstmt.setString(4, PersData.getEmail());
			pstmt.setDate(5, new Date(new java.util.Date().getTime()));
			pstmt.setDate(6, new Date(new java.util.Date().getTime()));
			pstmt.setBoolean(7, PersData.isConsent());
			pstmt.setString(8, PersData.getPassword());
			
			System.out.println("[DEBUG] SQL-Statement Benutzer anlegen: " + pstmt.toString());
			int updatedRows = pstmt.executeUpdate();
			// Hier werden die beiden Statements de-facto erst ausgef�hrt! 
			// Die Variable updatedRows wird trotzdem bef�llt.
			//con.commit(); 
			// Zur�ck zum Default-Wert. 			
			// Pr�fe anhand der R�ckgabewerte des SQL-Statements, ob der Eintrag erfolgt ist. 
			if (updatedRows > 0) {
				success = true; 
			} 
		} catch (SQLException e) {
			System.err.println("SQL-Fehler beim Benutzer einf�gen: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@insertBenutzer: " + npe.getMessage());		
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		return success;
	}
	
	public static boolean updateBenutzer(PersonalData PersData) {
		boolean success = false;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
				"UPDATE player "
				+ "SET "
					+ "displayname = ?, "
					+ "prename = ?, "
					+ "surname = ?, "
					+ "email = ?, "
					+ "agbterms = ?, "
					+ "lastlogin = ? "
				+ "WHERE "
					+ "email = ?;"
			);
			pstmt.setString(1, PersData.getDisplayName());
			pstmt.setString(2, PersData.getPreName());
			pstmt.setString(3, PersData.getSurName());
			pstmt.setString(4, PersData.getEmail());
			pstmt.setBoolean(5, PersData.isConsent());
			pstmt.setDate(6, new java.sql.Date(PersData.getLastLogin().getTime()));
			System.out.println("[DEBUG] SQL-Statement Benutzer aktualisieren: " + pstmt.toString());
			
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				success = true; 
			} 
		} catch (SQLException e) {
			System.err.println("SQL-Fehler beim Benutzer aktualisieren: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@updateBenutzer: " + npe.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		return success;
	}
	

}
