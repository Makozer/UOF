package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import database.utils.*;
import game.*;
import game.planet.*;
import game.player.*;
import game.utils.ResultToTable;

import static game.settings.GameSettings.*;

public class DBEvent {
	
	private static Connection con = null;
	
	public static void main(String[] args) {
		GameEvent event = new GameEvent(GameEvent.Type.BUILD, new Coordinates(1, 33, 7), "HeadQuarter", null, new Date(), new Date());
		DBEvent.createEvent(event);
	}
	
	public static boolean createEvent(GameEvent event) {
		Coordinates c = event.getCoordinates();
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO public.event(" 
							+ "type, thisgalaxy, thissolarsystem, thisplanet, building" + ")"
							//+ "type, thisgalaxy, thissolarsystem, thisplanet, targetgalaxy, targetsolarsystem, targetplanet, building, fleet, ressource, starttime, endttime" + ")"
							//+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
							+ " VALUES (?, ?, ?, ?, ?);"
				);
				pstmt.setString(1, event.getTypeAsString());
				pstmt.setInt(2, c.getGalaxy());
				pstmt.setInt(3, c.getSolarSystem());
				pstmt.setInt(4, c.getPlanetNumber());
				pstmt.setString(5, event.getBuildingName());



			if (DEBUGMODE) {System.out.println("[DEBUG] createEvent " + pstmt.toString());}
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
	
	public static ArrayList<GameEvent> getEvents(Player player) {
		ArrayList<GameEvent> events = new ArrayList<GameEvent>();
		
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT eventid, type, thisgalaxy, thissolarsystem, thisplanet, targetgalaxy, targetsolarsystem, targetplanet, building, fleet, ressource, starttime, endttime "
					+ "FROM public.event "
					+ "WHERE touserid = " 
					+ player.getPersData().getId());
			ResultSet rs = pstmt.executeQuery();
			//return ResultToTable.convertMessages(rs, player); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		return events;
	}

}
