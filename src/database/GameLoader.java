package database;

import game.player.*;

public class GameLoader {
	
	public static Player loadPlayer(int playerid) {

		Player player = DBPlayer.getPlayerById(playerid);
		
		// DataBase get Planets by Player ID
		player.addPlanets(DBPlanet.getPlanetsById(playerid));
		
		// DataBase get events by Player ID
		player.setEvents(DBEvent.getEvents(player));
		
		// DataBase get messages by Player ID
		// player.getInbox().update();	 
		// HINT: should work on its own!!!
		
		// Update Player to actual stand
		player.update(false);
		
		return player;
	}

}
