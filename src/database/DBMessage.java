package database;

import static game.settings.GameSettings.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import community.message.*;
import game.player.*;
import game.utils.*;

/**
 * Class for messages in db.
 * @author cedri
 *
 */
public class DBMessage {
	
	private static Connection con = null;

	public static void main(String[] args) {
		

	}
	
	/**
	 * creates new message.
	 * @param message
	 * @return
	 */
	public static boolean createMessage(Message message) {
		
		boolean success = false;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO messages(touserid, fromuserid, title, msgcontent)"
					+ " VALUES ("
					+ "?, "
					+ "?, "
					+ "?, "
					+ "?); "
				);
			pstmt.setInt(	1, message.getToId());
			pstmt.setInt(	2, message.getFromId());
			pstmt.setString(3, message.getTitle());
			pstmt.setString(4, message.getMessage());

			if (DEBUGMODE) {System.out.println("[DEBUG] SQL-Statement createMessage: " + pstmt.toString());}
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				success = true; 
			} 
		} catch (SQLException e) {
			System.err.println("SQL Error createMessage: " + e.getMessage());
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
	
	/**
	 * retrieves communitymessages.
	 * @param player
	 * @return
	 */
	public static ArrayList<CommunityMessage> getMessages(Player player) {
		ArrayList<CommunityMessage> messages = new ArrayList<CommunityMessage>();
		CommunityMessage 			message = null;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT messageid, touserid, fromuserid, title, msgcontent, created "
					+ "FROM messages "
					+ "WHERE touserid = ?");
			pstmt.setInt(1,player.getPersData().getId());
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				message = new CommunityMessage(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6));
				message.setMsgId(rs.getInt(1));
				messages.add(message);
		    }
			
		} catch (SQLException e) {
			if(DEBUGMODE) {System.out.println(e.getMessage());}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			} catch (NullPointerException e) {
				System.err.println(e.toString() + "in getMessages");
			}
		}
		return messages;
	}
	
	/**
	 * reetrieves messages from db.
	 * @param player
	 * @param timestamp
	 * @return
	 */
	public static ArrayList<CommunityMessage> getMessages(Player player, Date timestamp) {
		ArrayList<CommunityMessage> messages = new ArrayList<CommunityMessage>();
		CommunityMessage 			message = null;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT messageid, touserid, fromuserid, title, msgcontent, created "
					+ "FROM messages "
					+ "WHERE touserid = ? AND created BETWEEN ? AND ?");

			pstmt.setInt(1,player.getPersData().getId());
			pstmt.setTimestamp(2, new Timestamp(timestamp.getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				message = new CommunityMessage(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6));
				message.setMsgId(rs.getInt(1));
				messages.add(message);
		    }
		} catch (SQLException e) {
			if(DEBUGMODE) {System.out.println(e.getMessage());}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			} catch (NullPointerException e) {
				System.err.println(e.toString() + "in getMessages");
			}
		}
		return messages;
	}
	
	/**
	 * retrieves message from db by id.
	 * @param messageid
	 * @return
	 */
	public static Message getMessageById(int messageid) {		
		Message message = null;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT touserid, fromuserid, title, msgcontent, created "
					+ "FROM messages "
					+ "WHERE messageid = ?;");
			pstmt.setInt(1,messageid);
			
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				message = new Message(rs.getInt(2), rs.getInt(1), rs.getString(3), rs.getString(4), DateUtils.stampToDate(rs.getString(5)));
			}
			
		} catch (SQLException e) {
			if(DEBUGMODE) {System.out.println(e.getMessage());}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			} catch (NullPointerException e) {
				System.err.println(e.toString() + "in getMessageById");
			}
		}
		return message;
	}
	
	/**
	 * deletes message from db.
	 * @param player
	 * @param message
	 * @return
	 */
	public static boolean deleteMessage(Player player, Message message) {
		boolean success = false;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"DELETE FROM public.messages "
					+ "WHERE touserid = ? AND messageid = ?"
					+ ";"
				);
				pstmt.setInt(1,message.getToId());
				pstmt.setInt(2, message.getMsgId());

				if (DEBUGMODE) {System.out.println("[DEBUG] SQL-Statement deleteMessage: " + pstmt.toString());}
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				success = true; 
			} 
		} catch (SQLException e) {
			System.err.println("SQL-Fehler deleteMessage: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@deleteMessage: " + npe.getMessage());
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
