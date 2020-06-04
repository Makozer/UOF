package database.utils;

import static game.settings.GameSettings.DEBUGMODE;

import java.sql.*;

import database.DatabaseConnection;

public class ClearWholeDB {
	
	private static Connection con = null;

	public static void main(String[] args) {
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"DELETE FROM public.event; "
					+ "DELETE FROM public.messages; "
					//+ "DELETE FROM public.passwoerter; "
					//+ "DELETE FROM public.planet; "
					//+ "DELETE FROM public.player; "
					//+ "DELETE FROM public.techtree; "
				);



			if (DEBUGMODE) {System.out.println("[DEBUG] createEvent " + pstmt.toString());}
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				return; 
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

	}

}
