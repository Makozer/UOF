package game.control;

import java.util.*;

import database.DBPlanet;
import database.DBTechTree;
import database.DBUser;
import game.planet.*;
import game.player.*;
import game.research.*;
import game.settings.*;
import game.utils.*;

/**
 * This Class creates a newly registered Player and gives him a random Planet in the Universe etc.
 * @author Martin
 *
 */
public class NewPlayerManager {
	
	/**
	 * Creates the new Player with his given Personal Data and Password
	 * @param data Personal Data for the Player
	 * @param password his Password
	 * @return Player the new Player as Object
	 */
	public static Player createNewPlayer(PersonalData data, String password) {
		TechTree techtree = new TechTree();
		Player player = new Player(data, techtree);
		
		// insert Player into DB
		if (!DBUser.createPlayer(player, password)) { System.out.println("DBPlayer.createPlayer WAS NULL"); return null; }
		// TODO lowprio returning ID!!! performance!
		// get his Id
		int playerId = DBUser.getPlayerIdByEmail(data.getEmail());
		if (playerId == 0) { 
			System.out.println("PLAYER ID WAS 0");
			return null; 
		} else {
			player.getPersData().setId(playerId);
			DBTechTree.createTechTree(playerId, techtree);
		}
		
		// random Planet Coords
		Coordinates firstplanetCoords = new Coordinates(
									NumberUtils.getRndInt(1, UniverseSettings.GALAXYMAX), 
									NumberUtils.getRndInt(1, UniverseSettings.SOLARSYSTEMMAX), 
									NumberUtils.getRndInt(1, UniverseSettings.PLANETMAX));
		// Check if the Planet is already taken
		while (DBPlanet.hasPlanet(firstplanetCoords)) {
			firstplanetCoords = new Coordinates(
					NumberUtils.getRndInt(1, UniverseSettings.GALAXYMAX), 
					NumberUtils.getRndInt(1, UniverseSettings.SOLARSYSTEMMAX), 
					NumberUtils.getRndInt(1, UniverseSettings.PLANETMAX));
		}
		
		// create Planet
		Planet planet = new Planet(techtree, firstplanetCoords, data.getDisplayName(), 
										500, 500, 500, 0,
										1, 0, 0,
										1, 1, 1, 0,
										1, 1, 1, 0,
										new Date());				
		
		// one last check
		if (DBPlanet.hasPlanet(firstplanetCoords)) { System.out.println("PLANET MEGA ERROR"); return null; }
		
		// insert Planet into DB
		DBPlanet.createPlanet(player, planet);
		player.addPlanet(planet);
		player.loadEvents();
		return player;
	}

}
