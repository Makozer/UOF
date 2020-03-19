package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.research.TechTree;

public class DBTechTree {

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
	
	public static boolean createTechTree(int playerId, TechTree techtree) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO public.techtree(playerid, tree)"
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
	
	public static boolean updateTechTree(int playerId, TechTree techtree) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE public.techtree SET tree=? WHERE playerid=?;"
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
	
	public static TechTree getTechTree(int playerid) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT tree FROM public.techtree WHERE playerId = ?" 
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
