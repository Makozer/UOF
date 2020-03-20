package database;

import java.sql.*;
import java.util.*;

import database.utils.PasswordUtils;
import game.player.*;
import game.research.TechTree;
import game.utils.DateUtils;
import game.utils.ResultToTable;

public class DBPlayer {
	
	private static Connection con = null;
	
	public static void main(String[] args) {
		// Test Insert
		//TechTree techtree = new TechTree();
		//PersonalData pd = new PersonalData(0, "test@test.de", "TestDisplayName", "TestPreName", "TestSurName", DateUtils.getDate(1888, 8, 8), new Date(), new Date());
		//Player player = new Player(pd, techtree);
		//DBPlayer.createPlayer(player, "test");
		
		// Test getId
		System.out.println(DBPlayer.getPlayerIdByDisplayName("TestDisplayName"));
	}
	
	public static boolean createPlayer(Player player, String password) {
		PersonalData pd = player.getPersData();
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"WITH uid AS (INSERT INTO public.player(" 
							+ "displayname, prename, surname, email, birthday" + ")"
					+ " VALUES (?, ?, ?, ?, ?)"
					+ " RETURNING userid)"
					+ " INSERT INTO passwoerter(userid, passwort) select userid, ? from uid;"
				);
				pstmt.setString(1, player.getDisplayName());
				pstmt.setString(2, pd.getPreName());
				pstmt.setString(3, pd.getSurName());
				pstmt.setString(4, pd.getEmail());
				pstmt.setTimestamp(5, new Timestamp(pd.getBirthday().getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
				pstmt.setString(6, PasswordUtils.getSaltyPassword(password));


			System.out.println("[DEBUG] SQL-Statement createPlayer: " + pstmt.toString());	
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				return true; 
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
		//
		return false;
	}
	
	private static boolean updatePlayer(Player player) {
		return false;
	}
	
	public static int getPlayerIdByDisplayName(String displayname) {
		int playerId = 0;
		
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT userid FROM public.player WHERE displayname = ?" 
					);
			pstmt.setString(1, displayname);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return playerId;
	}
	
	public static int getPlayerIdByEmail(String email) {
		int playerId = 0;
		
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT userid FROM public.player WHERE email = ?" 
					);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return playerId;
	}
	
	public static Player getPlayerById(int playerid) {
		Player player = null;
		
		TechTree techtree = DBTechTree.getTechTree(playerid);
		if (techtree == null) {System.out.println("TechTree was null at getPlayerById(" + playerid + ")"); return null;}
		
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT userid, email, displayname, prename, surname, birthday, created, lastlogin FROM public.player WHERE userid = ?" 
					);
			pstmt.setInt(1, playerid);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				PersonalData pd = new PersonalData(playerid, rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), 
												DateUtils.stampToDate(rs.getString(6)), DateUtils.stampToDate(rs.getString(7)), DateUtils.stampToDate(rs.getString(8)));
				return new Player(pd, techtree);
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("getPlayerById SQL Error " + e.getMessage());
		}
		
		return player;
	}
	
	

}
