package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.research.TechTree;

/**
 * Class for db techtree updates.
 * @author cedri
 *
 */
public class DBTechTree {

	/**
	 * Testmethode.
	 * @param args
	 */
	public static void main(String[] args) {
		// Test method
		TechTree techtree = new TechTree();
		//techtree.testFill();
		//DBTechTree.createTechTree(2, techtree);
		
		System.out.println("Test TechTree:\n" + DBTechTree.getTechTree(2).asSQLString());
		System.out.println("Update");
		techtree.setLevel("Test", 1337);
		System.out.println(DBTechTree.updateTechTree(2, techtree));
		System.out.println("Test TechTree:\n" + DBTechTree.getTechTree(2).asSQLString());
		
	}
	
	/**
	 * Erstellt neuen Forschungsfortschritt fuer nutzer.
	 * @param playerId
	 * @param techtree
	 * @return
	 */
	public static boolean createTechTree(int playerId, TechTree techtree) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO public.techtrees(userid, tree)"
					+ "VALUES (?, ?);"
					);
			pstmt.setInt(1, playerId);
			pstmt.setString(2, techtree.asSQLString());
			
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				return true; 
			} 
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return false;
	}
	
	/**
	 * updaten den techtree des nutzers.
	 * @param playerId
	 * @param techtree
	 * @return
	 */
	public static boolean updateTechTree(int playerId, TechTree techtree) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE public.techtrees SET tree=? WHERE userid=?;"
					);
			pstmt.setString(1, techtree.asSQLString());
			pstmt.setInt(2, playerId);
			
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				return true; 
			} 
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return false;
	}
	
	/**
	 * holt tech aus db.
	 * @param playerid
	 * @return
	 */
	public static TechTree getTechTree(int playerid) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT tree FROM public.techtrees WHERE userid = ?" 
					);
			pstmt.setInt(1, playerid);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new TechTree(rs.getString(1));
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
