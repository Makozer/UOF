package database.utils;

import static game.settings.GameSettings.DEBUGMODE;

import java.sql.*;

import database.*;

public class DropWholeDB {

	private static Connection con = null;

	public static void main(String[] args) {
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"DROP TABLE public.events CASCADE; "
					+ "DROP TABLE public.messages CASCADE; "
					+ "DROP TABLE public.passwords CASCADE; "
					+ "DROP TABLE public.planets CASCADE; "
					+ "DROP TABLE public.users CASCADE; "
					+ "DROP TABLE public.techtrees CASCADE; "
					// Sequence
					+ "DROP SEQUENCE public.eventid_seq CASCADE; "
					+ "DROP SEQUENCE public.messageid_seq CASCADE; "
					+ "DROP SEQUENCE public.userid_seq CASCADE; "
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
