package database;

import game.player.*;
import game.event.*;
import game.utils.*;

import java.sql.*;
import java.util.*;

/**
 * NYI
 * @author cedri
 *
 */
public class DBLock {
	
	private static Connection con = null;
	
	public static void main(String[] args) {
		boolean check = false;
		//Player player = new Player();
		//player.getPersData().setId(1);
		GameEvent e = new GameEvent();
		e.setId(1);
		
		String lock = RandomString.rndString();
		//check = lockUser(player, lock);
		
		System.out.print(check);
		
		check = lockEvent(e, lock);
		System.out.print(check);
	}
	
	public static boolean lockUser(Player user, String lock) {
		boolean success = false;
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE public.users SET lock=? " 
					+ "WHERE userid=? AND lock=''"
					+ " RETURNING lock;"

				);
				pstmt.setString(1, lock);
				pstmt.setInt(2, user.getPersData().getId());


			System.out.println("[DEBUG] SQL-Statement lockUser: " + pstmt.toString());	

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (lock.equals(rs.getString(1))) {
					success = true;
				}
			} 
		} catch (SQLException e) {
			System.err.println("SQL-Fehler lockUser: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@lockUser: " + npe.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		
		return success;
	}
	
	public static boolean lockEvent(GameEvent event, String lock) {
		boolean success = false;
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE public.events SET lock=? " 
					+ "WHERE eventid=? AND lock=''"
					+ " RETURNING lock;"

				);
				pstmt.setString(1, lock);
				pstmt.setInt(2, event.getId());

			System.out.println("[DEBUG] SQL-Statement lockUser: " + pstmt.toString());	

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (lock.equals(rs.getString(1))) {
					success = true;
				}
			} 
		} catch (SQLException e) {
			System.err.println("SQL-Fehler lockUser: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@lockUser: " + npe.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		
		return success;
	}
	
	public static boolean unlock(Player user) {
		boolean success = false;
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE public.users SET lock='' " 
					+ "WHERE userid=?;"

				);
				pstmt.setInt(1, user.getPersData().getId());


			System.out.println("[DEBUG] SQL-Statement unlock: " + pstmt.toString());	
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				success = true; 
			} 
	
		} catch (SQLException e) {
			System.err.println("SQL-Fehler unlock: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@unlock: " + npe.getMessage());
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
