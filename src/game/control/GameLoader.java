package game.control;

import database.DBEvent;
import database.DBPlanet;
import database.DBUser;
import game.player.*;

public class GameLoader {
	
	public static Player loadPlayer(int playerid) {

		Player player = DBUser.getPlayerById(playerid);
		if (player == null) {return null;}
		
		// DataBase get Planets by Player ID
		player.addPlanets(DBPlanet.getPlanetsById(playerid));
		
		// DataBase get events by Player ID
		//player.setEvents(DBEvent.getEvents(player));
		player.loadEvents();
		
		// DataBase get messages by Player ID
		// player.getInbox().update();	 
		// HINT: should work on its own!!!
		
		// Update Player to actual stand
		player.update(false);
		
		return player;
	}

}
