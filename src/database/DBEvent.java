package database;

import java.sql.*;
import java.util.Date;

import community.message.CommunityMessage;

import java.util.*;
import database.utils.*;
import game.*;
import game.event.GameEvent;
import game.fleet.*;
import game.planet.*;
import game.player.*;
import game.research.*;
import game.utils.*;

import static game.settings.GameSettings.*;

public class DBEvent {
	
	private static Connection con = null;
	
	public static void main(String[] args) {
		GameEvent event = new GameEvent(0, 0, 0, GameEvent.Type.BUILD, new Coordinates(1, 33, 7), new Coordinates(1, 33, 6), "HeadQuarter", null, null, new Date(), new Date());
		int eventid = DBEvent.createEvent(event);
		System.out.println("ID: " + eventid);
		System.out.println("Check if its there:");
		for (GameEvent e : DBEvent.getEvents(new Player(0, new TechTree()))) {
			System.out.println(e.toString());
		}
		
		//Test delete
		//System.out.println(DBEvent.deleteEvent(1));
	}
	
	public static int createEvent(GameEvent event) {
		Coordinates c = event.getCoordinates();
		Coordinates t = event.getTarget();

		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO public.events(" 
							+ "typ, thisgalaxy, thissolarsystem, thisplanet, targetgalaxy, targetsolarsystem, targetplanet, " 
							+ "building, fleet, ressource, starttime, endttime, userid, targetuserid" + ") "
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" 
							+ " RETURNING eventid"
							+ ";"
				);
			pstmt.setString(1, event.getTypeAsString()); pstmt.setInt(2, c.getGalaxy()); pstmt.setInt(3, c.getSolarSystem()); pstmt.setInt(4, c.getPlanetNumber());
			pstmt.setInt(5, t.getGalaxy()); pstmt.setInt(6, t.getSolarSystem()); pstmt.setInt(7, t.getPlanetNumber());
			pstmt.setString(8, event.getBuildingName()); pstmt.setString(9, event.getFleet().asSQLString());
			pstmt.setString(10, RessourceUtils.arrayToSQL(event.getRessource()));
			pstmt.setTimestamp(11, new Timestamp(event.getStartTime().getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
			pstmt.setTimestamp(12, new Timestamp(event.getEndTime().getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
			pstmt.setInt(13, event.getPlayerid());
			pstmt.setInt(14, event.getTargetplayerid());


			if (DEBUGMODE) {System.out.println("[DEBUG] createEvent " + pstmt.toString());}
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
			
			/*int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				return true; 
			} 
			*/
		} catch (SQLException e) {
			System.err.println("SQL-Fehler beim Benutzer aktualisieren: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@updateBenutzer: " + npe.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		
		return 0;
	}
	
	public static ArrayList<GameEvent> getEvents(Player player) {
		ArrayList<GameEvent> events = new ArrayList<GameEvent>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT eventid, typ, thisgalaxy, thissolarsystem, "
						+ "thisplanet, targetgalaxy, targetsolarsystem, targetplanet, "
						+ "building, fleet, ressource, starttime, "
						+ "endttime, userid, targetuserid "
					+ "FROM public.events "
					+ "WHERE userid = ? OR targetuserid = ?");
			pstmt.setInt(1, player.getPersData().getId());
			pstmt.setInt(2, player.getPersData().getId());
			ResultSet rs = pstmt.executeQuery();

			try {
				ResultSetMetaData rsmd = rs.getMetaData();
				
				while (rs.next())
			      {
					events.add(
							new GameEvent(rs.getInt(1), rs.getInt(14), rs.getInt(15), GameEvent.Type.valueOf(rs.getString(2)),
											new Coordinates(rs.getInt(3), rs.getInt(4), rs.getInt(5)), new Coordinates(rs.getInt(6), rs.getInt(7), rs.getInt(8)), 
											rs.getString(9), new Fleet(player.getTechTree(), rs.getString(10)), RessourceUtils.sqlToRessourceArrayList(rs.getString(11)), 
											DateUtils.stampToDate(rs.getString(12)), DateUtils.stampToDate(rs.getString(13)))
							);
			      }
				return events;
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		
		return events;
	}
	
	public static ArrayList<GameEvent> getEvents(Player player, Date timestamp) {
		ArrayList<GameEvent> 	events = new ArrayList<GameEvent>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT eventid, typ, thisgalaxy, thissolarsystem, "
						+ "thisplanet, targetgalaxy, targetsolarsystem, targetplanet, "
						+ "building, fleet, ressource, starttime, "
						+ "endttime, userid, targetuserid "
					+ "FROM public.events "
					+ "WHERE (userid = ? AND created BETWEEN ? AND ?) OR (targetuserid = ? AND created BETWEEN ? AND ?)");
			pstmt.setInt(1, player.getPersData().getId());
			pstmt.setTimestamp(2, new Timestamp(timestamp.getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
			pstmt.setInt(4, player.getPersData().getId());
			pstmt.setTimestamp(5, new Timestamp(timestamp.getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
			pstmt.setTimestamp(6, new Timestamp(new Date().getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
			ResultSet rs = pstmt.executeQuery();

			try {
				ResultSetMetaData rsmd = rs.getMetaData();
				
				while (rs.next())
			      {
					events.add(
							new GameEvent(rs.getInt(1), rs.getInt(14), rs.getInt(15), GameEvent.Type.valueOf(rs.getString(2)),
											new Coordinates(rs.getInt(3), rs.getInt(4), rs.getInt(5)), new Coordinates(rs.getInt(6), rs.getInt(7), rs.getInt(8)), 
											rs.getString(9), new Fleet(player.getTechTree(), rs.getString(10)), RessourceUtils.sqlToRessourceArrayList(rs.getString(11)), 
											DateUtils.stampToDate(rs.getString(12)), DateUtils.stampToDate(rs.getString(13)))
							);
			      }
				return events;
			} catch (SQLException e) {
				System.out.println(e.toString());
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
		return events;
	}
	
	public static boolean deleteEvent(int eventid) {
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"DELETE FROM public.events WHERE eventid = ?;"
				);
				pstmt.setInt(1, eventid); 

			if (DEBUGMODE) {System.out.println("[DEBUG] deleteEvent " + pstmt.toString());}
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				return true; 
			} 
		} catch (SQLException e) {
			System.err.println("SQL-Fehler deleteEvent: " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@deleteEvent: " + npe.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung konnte nicht geschlossen werden.");
			}
		}
		
		return false;
	}

}
