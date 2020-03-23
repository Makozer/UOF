package database;

import java.sql.*;
import static game.settings.GameSettings.*;
import java.util.*;
import java.util.Date;
import community.message.Message;
import game.planet.*;
import game.player.*;
import game.research.TechTree;
import game.utils.DateUtils;
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
							+ "galaxy, solarsystem, planetnumber, playerid, name, ressources, buildings" + ")"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?);"
				);
				pstmt.setInt(1, c.getGalaxy());
				pstmt.setInt(2, c.getSolarSystem());
				pstmt.setInt(3, c.getPlanetNumber());
				pstmt.setInt(4, player.getPersData().getId());
				pstmt.setString(5, player.getDisplayName());
				pstmt.setString(6, planet.asRessourceSQLString());
				pstmt.setString(7, planet.asBuildingSQLString());


				if (DEBUGMODE) {System.out.println("[DEBUG] SQL-Statement createPlanet: " + pstmt.toString());}
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
	
	public static boolean updatePlanet(Player player, Planet planet) {
		
		Coordinates c = planet.getCoords();
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE public.planet SET "
					+ "name=?, ressources=?, buildings=?, fleet=?, spaceportqueue=?, lastupdate=? "
					+ "WHERE galaxy=? AND solarsystem=? AND planetnumber=?;"
				);
			pstmt.setString(1, planet.getName());
			pstmt.setString(2, planet.asRessourceSQLString());
			pstmt.setString(3, planet.asBuildingSQLString());
			pstmt.setString(4, planet.asFleetSQLString());
			pstmt.setString(5, planet.asSpacePortQueueSQLString());
			pstmt.setTimestamp(6, new Timestamp(planet.getLastupdate().getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
			
			// WHERE
			pstmt.setInt(7, c.getGalaxy());
			pstmt.setInt(8, c.getSolarSystem());
			pstmt.setInt(9, c.getPlanetNumber());


			if (DEBUGMODE) {System.out.println("[DEBUG] SQL-Statement updatePlanet: " + pstmt.toString());}
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
		return false;
		
	}
	
	private static void getPlanetByCoordinates(Coordinates coordinates) {
		// TODO 
		
	}
	
	public static ArrayList<Planet> getPlanetsById(int playerid) {
		ArrayList<Planet> planets = new ArrayList<Planet>();
		
		TechTree techTree = DBTechTree.getTechTree(playerid);
		if (techTree == null) { 
			if (DEBUGMODE) {System.out.println("TECHTREE NULL @ getPlanetsById");}
			return null;
		} else {if (DEBUGMODE) {System.out.println("techtree loaded.");}}
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT galaxy, solarsystem, planetnumber, name, ressources, buildings, fleet, spaceportqueue, lastupdate "
					+ "FROM public.planet "
					+ "WHERE playerid = ?;" 
					);
			pstmt.setInt(1, playerid);
			ResultSet rs = pstmt.executeQuery();
			if (DEBUGMODE) {System.out.println("getPlanetsById wurde geladen " + pstmt.toString());}
			while (rs.next()){
				System.out.println("Planet: " + new Coordinates(rs.getInt(1), rs.getInt(2), rs.getInt(3)).asCoords() + "; " 
						+ rs.getString(4) + "; " + rs.getString(5) + "; " + rs.getString(6) + "; " + rs.getString(7) + "; " + rs.getString(8) + "; " +
						 rs.getString(9));
				planets.add(new Planet(techTree, new Coordinates(rs.getInt(1), rs.getInt(2), rs.getInt(3)), 
										rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
										//DateUtils.dateToStamp(new Date(rs.getTimestamp(9).getTime()))));
										rs.getString(9)));
		    }
			
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		
		return planets;
	}
	
	public static boolean hasPlanet(Coordinates coordinates) {
		try {
			con = DatabaseConnection.getConnection();
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
	}
	
	public static ArrayList<String> getSolarSystem(int galaxy, int solarsystem) {
		ArrayList<String> output = new ArrayList<String>();
		int i = 1;
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT planetnumber, name "
					+ "FROM planet "
					+ "WHERE galaxy = " + galaxy + " AND solarsystem = " + solarsystem
					+ " ORDER BY planetnumber ASC");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				while (rs.getInt(1) > i) {
					output.add("Leer");
					i++;
				}				
				output.add(rs.getString(2));
				i++;
			} // while
			output.add("Leer"); // Always one lonely Planet at the end :)
		} catch (SQLException e) {
			System.out.println(e.getMessage());			
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		return output;
	}

}
