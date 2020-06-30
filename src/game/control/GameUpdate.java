package game.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import community.message.GameMessage;
import database.DBEvent;
import database.DBPlanet;
import database.DBTechTree;
import database.DBUser;
import game.event.*;
import game.fleet.Fleet;
import game.planet.Planet;
import game.planet.buildings.ABuilding;
import game.player.*;
import game.research.Research;
import game.ressource.ARessource;

/**
 * This Class controls the Game and forces it to update when a Player refreshes the Browser
 * @author Martin
 *
 */
public class GameUpdate {
	
	private Player user;
	
	/**
	 * Only for testing, dont use for WebInterface
	 */
	public GameUpdate() {}
	
	/**
	 * Creates the GameUpdate for a given Player
	 * @param user
	 */
	public GameUpdate(Player user) {
		this.user = user;
	}
	
	public static void main(String[] args) {
		System.out.println("Test");
		System.out.println(new GameUpdate().toString());
		System.out.println(new GameUpdate().toString());
	}
	
	public void update() {
		this.pageRefresh();
	}
	
	/**
	 * When a Player refreshes his Browser, this method updates everything
	 * @return
	 */
	public boolean pageRefresh() {	
		// TODO Connection create here and give it to all others and close it after refresh
		System.out.println("pageRefresh() ...");
		boolean output = false;
		Date now = new Date();
		
		user.getInbox().update();	

		output = updatePlayerToDate(user, now);

		for (Planet planet: user.getPlanets()) { planet.update(user);}
		
		// Close connection
		return output;
	}
	
	public void reloadPlayer() {
		// Reload the Player ... f0000ck how to do dis :x
		// TODO Idea -> player.reload()
		// TODO Idea -> when new msg arrives
	}
	
	/**
	 * Updates a Player to a given Date
	 * @param user
	 * @param date
	 * @return
	 */
	private boolean updatePlayerToDate(Player user, Date date) {
		
		// Getting the Users EventCenter and update it
		EventCenter ec = user.getEventCenter();
		ec.update();
		
		// Getting the Events that already occured
		ArrayList<GameEvent> events = ec.getCalcEventsToDate(date);
		
		if (events.size() > 0) {
			// TODO trying to lock this user!!!
			return calculateEvents(events, user, date);
		}
		
		return true;		
	}
	
	private boolean updateOtherPlayerById(int playerid) {
		Date now = new Date();
		return updateOtherPlayerByIdToDate(playerid, now);
	}
	
	private boolean updateOtherPlayerByIdToDate(int playerid, Date date) {
		Player otherOne = GameLoader.loadPlayer(playerid);
		GameUpdate otherGU = new GameUpdate(otherOne);
		otherGU.updatePlayerToDate(user, date);
		return true;
	}
	
	
	
	/** 
	 * Calculates all given Events
	 * @param events
	 * @param user
	 * @param date
	 * @return
	 */
	private boolean calculateEvents(ArrayList<GameEvent> events, Player user, Date date) {				

		Iterator<GameEvent> i = events.iterator();
		while (i.hasNext()) {
			GameEvent event = i.next();					
			if (event.getEndTime().getTime() < date.getTime()) {
				switch (event.getType()) {
					case ATTACK:
						calculateAttack(event, i);
						break;
					case DEFEND:
						//calculateDefend(event, i);
						break;
					case TRANSPORT:
						calculateTransport(event, i);
						break;
					case BUILD:
						calculateBuild(event, i);
						break;
					case RESEARCH:
						calculateResearch(event, i);
						break;
					default:
						throw new IllegalArgumentException("Wrong Event in your List");				
				}
			}
		}

		
		return true;
	}
	
	/**
	 * Calculates the Attack
	 * @param event of the Attack
	 * @param i the Iterator for the ArrayList to remove savely the Element
	 */
	private void calculateAttack(GameEvent event, Iterator<GameEvent> i) {
		// Required Objects
		// Actual Player
		int myplayerid = user.getPersData().getId();
		Planet myplanet = user.getActivePlanet();
		Fleet myfleet = event.getFleet();
		

		// Enemy Player
		int enemyplayerid = DBUser.getPlayerIdByCoordinates(event.getTarget());
		Player enemyplayer = GameLoader.loadPlayer(enemyplayerid);		
		// if Player isnt there anymore
		if (enemyplayer == null) {
			GameEvent survivorevent = new GameEvent(0, myplayerid, myplayerid, GameEvent.Type.TRANSPORT, event.getTarget(), event.getCoordinates(), "", event.getFleet(), new ArrayList<ARessource>(), event.getEndTime(), 
					new Date(new Date().getTime() + (event.getEndTime().getTime() - event.getStartTime().getTime())));
				DBEvent.createEvent(survivorevent);
				// DataBase inform
				deleteEvent(event);
				i.remove();
				return;
		}
		Planet enemyplanet = enemyplayer.getPlanetByCoordinates(event.getTarget());
		Fleet enemyfleet = enemyplanet.getFleet();
		
		myfleet.setPlayerId(user.getPersData().getId());
		enemyfleet.setPlayerId(enemyplayerid);
		
		Fleet myfleetclone = myfleet.clone();
		Fleet enemyfleetclone = enemyfleet.clone();
		
		Fleet winner = Combat.fight(myfleet, enemyfleet);
		if (winner.getPlayerId() == myplayerid) {
			ArrayList<ARessource> prey = new ArrayList<ARessource>();
			ARessource iron = enemyplanet.getIron(); ARessource rare = enemyplanet.getRareEarth();ARessource water = enemyplanet.getWater();ARessource tritium = enemyplanet.getTritium();
			prey.add(iron);prey.add(rare);prey.add(water);prey.add(tritium);
			Combat.createCombatLog(user, enemyplayer, event.getTarget(), winner, myfleetclone, enemyfleetclone, prey);
			GameEvent survivorevent = new GameEvent(0, myplayerid, myplayerid, GameEvent.Type.TRANSPORT, event.getTarget(), event.getCoordinates(), "", winner, prey, event.getEndTime(), 
										new Date(new Date().getTime() + (event.getEndTime().getTime() - event.getStartTime().getTime())));
			DBEvent.createEvent(survivorevent);
		} else {
			// Fleet has been destructed
			Combat.createCombatLog(enemyplayer, user, event.getTarget(), winner, myfleetclone, enemyfleetclone, new ArrayList<ARessource>());
		}
		
		// DataBase inform
		deleteEvent(event);
		i.remove();

	}
	

	/**
	 * Calculates the Transport
	 * @param event
	 * @param i the Iterator for the ArrayList to remove savely the Element
	 */
	private void calculateTransport(GameEvent event, Iterator<GameEvent> i) {
		Planet planet = user.getPlanetByCoordinates(event.getTarget());
		if (planet != null) {
			planet.getFleet().addFleet(event.getFleet());
			planet.increaseRessources(event.getRessource());
			// TODO DATABASE INFORM
			i.remove();			
		} else {
			// TODO calculate with Database from other players planet!!!
		}
	}

	/**
	 * Calculates a Build on a Planet
	 * @param event
	 * @param i the Iterator for the ArrayList to remove savely the Element
	 */
	private void calculateBuild(GameEvent event, Iterator<GameEvent> i) {
		Planet planet = user.getPlanetByCoordinates(event.getCoordinates());
		if (planet == null) {return;}
		ABuilding building = planet.getBuildingByName(event.getBuildingName());
		building.levelUp(event.getEndTime());
		planet.setIsBuilding("");
		GameMessage message = new GameMessage(planet.getCoords().asCoords() + " Your " + building.getName() + "(new Level: " + building.getLevel() + ") has finished upgrading!", "Bla bla bla Mr. Freeman.");
		user.getInbox().addMessage(message);
		i.remove();
		
		// Update DataBase
		DBPlanet.updatePlanet(user, planet);
		deleteEvent(event);
	}
	
	/**
	 * Calculates a Research on a Planet
	 * @param event
	 * @param i the Iterator for the ArrayList to remove savely the Element
	 */
	private void calculateResearch(GameEvent event, Iterator<GameEvent> i) {
		Planet planet = user.getPlanetByCoordinates(event.getCoordinates());
		if (planet == null) {return;}
		Research research = planet.getResearchByName(event.getBuildingName());
		user.getTechTree().setLevel(research.getName(), (user.getTechTree().getLevel(research.getName()) + 1));
		planet.setIsResearching("");
		GameMessage message = new GameMessage(planet.getCoords().asCoords() + " Your " + research.getName() + "(new Level: " + research.getLevel() + ") has finished upgrading!", "Bla bla bla Mr. Freeman.");
		user.getInbox().addMessage(message);
		i.remove();
		
		// Update DataBase
		DBTechTree.updateTechTree(event.getPlayerid(), user.getTechTree());
		deleteEvent(event);
	}
	
	
	/**
	 * Deletes the given Event and informs the DataBase
	 * @param event
	 * @return
	 */
	private boolean deleteEvent(GameEvent event) {
		EventCenter ec = user.getEventCenter();		
		DBEvent.deleteEvent(event.getId());
		return ec.deleteEvent(event);
	}

}
