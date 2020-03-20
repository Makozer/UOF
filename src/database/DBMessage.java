package database;

import static game.settings.GameSettings.*;
import java.sql.*;
import java.util.*;

import community.message.Message;
import game.player.Player;
import game.utils.ResultToTable;

public class DBMessage {
	
	private static Connection con = null;

	public static void main(String[] args) {
		

	}
	
	public static boolean createMessage(Message message) {
		
		boolean success = false;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO messages(touserid, fromuserid, title, message)"
					+ " VALUES ("
					+ "?, "
					+ "?, "
					+ "?, "
					+ "?); "
				);
				pstmt.setInt(1,message.getToId());
				pstmt.setInt(2, message.getFromId());
				pstmt.setString(3, message.getTitle());
				pstmt.setString(4, message.getMessage());
				//pstmt.setTimestamp(5,new Timestamp(new java.util.Date().getTime()), Calendar.getInstance(TimeZone.getTimeZone("UTC")));

				if (DEBUGMODE) {System.out.println("[DEBUG] SQL-Statement createMessage: " + pstmt.toString());}
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
	
	public static ArrayList<Message> getMessages(Player player) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT messageid, touserid, fromuserid, title, message, created "
					+ "FROM messages "
					+ "WHERE touserid = " 
					+ player.getPersData().getId());
			ResultSet rs = pstmt.executeQuery();
			return ResultToTable.convertMessages(rs, player); 
		} catch (SQLException e) {
			//return e.getMessage();
			return null;
		}
	}
	
	public static boolean deleteMessage(Player player, Message message) {
		// TODO
		
		return true;
	}

}