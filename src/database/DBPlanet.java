package database;

import java.sql.*;
import java.util.*;
import java.util.Date;

import community.message.Message;
import game.planet.*;
import game.player.*;
import game.research.TechTree;
import game.utils.ResultToTable;

public class DBPlanet {
	
	private static Connection con = null;
	
	public static void main(String[] args) {
		// Test if Planet already exists
		Coordinates shouldExist = new Coordinates(1, 33, 7);
		System.out.println(DBPlanet.hasPlanet(shouldExist));
		
		Coordinates shouldNOTExist = new Coordinates(1, 33, 13);
		System.out.println(DBPlanet.hasPlanet(shouldNOTExist));
	}
	
	public static boolean createPlanet(Player player, Planet planet) {
		// required Objects
		Coordinates c = planet.getCoords();
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO planet(" 
							+ "galaxy, solarsystem, planetnumber, playerid, name, ressources, buildings, timeinteger" + ")"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);"
				);
				pstmt.setInt(1, c.getGalaxy());
				pstmt.setInt(2, c.getSolarSystem());
				pstmt.setInt(3, c.getPlanetNumber());
				pstmt.setInt(4, player.getPersData().getId());
				pstmt.setString(5, player.getDisplayName());
				pstmt.setString(6, planet.asRessourceSQLString());
				pstmt.setString(7, planet.asBuildingSQLString());
				int now = (int)(new Date().getTime() / 1000);
				pstmt.setInt(8, now);


			System.out.println("[DEBUG] SQL-Statement Benutzer aktualisieren: " + pstmt.toString());	
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
	
	public static void updatePlanet(Player player, Planet planet) {
		
	}
	
	public static void getPlanetByCoordinates(Coordinates coordinates) {
		
	}
	
	public static ArrayList<Planet> getPlanetsById(int playerid) {
		ArrayList<Planet> planets = new ArrayList<Planet>();
		
		TechTree techTree = DBTechTree.getTechTree(playerid);
		
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT galaxy, solarsystem, planetnumber, name, ressources, buildings, fleet, spaceportqueue, timeinteger "
					+ "FROM public.planet "
					+ "WHERE playerid = ?;" 
					);
			pstmt.setInt(1, playerid);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()){
				System.out.println("Planet create: " + new Coordinates(rs.getInt(1), rs.getInt(2), rs.getInt(3)).asCoords() + "; " 
				+ "; " + rs.getString(4) + "; " + rs.getString(5) + "; " + rs.getString(6) + "; " + rs.getString(7) + "; " + rs.getString(8) + "; " +
						 rs.getInt(9));
				planets.add(new Planet(techTree, new Coordinates(rs.getInt(1), rs.getInt(2), rs.getInt(3)), 
										rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)
										, rs.getInt(9)));
		    }
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		return planets;
	}
	
	public static boolean hasPlanet(Coordinates coordinates) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT name "
					+ "FROM planet "
					+ "WHERE galaxy = " + coordinates.getGalaxy() + " AND solarsystem = " + coordinates.getSolarSystem() + " AND planetnumber = " + coordinates.getPlanetNumber()
					);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
