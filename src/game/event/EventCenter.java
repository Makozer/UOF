package game.event;

import java.util.*;

import database.*;
import game.planet.Coordinates;
import game.planet.Planet;
import game.player.*;

/**
 * The Players EventCenter, all Events are stored here
 * @author Martin
 *
 */
public class EventCenter {
	
	Player 							user = null;
	private ArrayList<GameEvent>	events = new ArrayList<GameEvent>();
	private Date					lastupdate = null;
	
	/**
	 * Constructor of EventCenter for a given Player
	 * @param user
	 */
	public EventCenter(Player user) {
		this.user = user;
		update();
		init();
	}
	///////////////////////////

	public void addEvent(GameEvent event) {
		this.events.add(event);
		
	}
	
	
	
	public void removeEvent(GameEvent event) {
		this.events.remove(event);
	}
	
	/**
	 * Returns the building Event for given Coordinates if the Player owns that Planet, returns null if not
	 * @param coordinates
	 * @return Event or Null(if there is no building Event or Planet)
	 */
	public GameEvent getBuildEventByCoords(Coordinates coordinates) {
		GameEvent output = null;
		for (GameEvent e: events) {
			if (e.getCoordinates().asCoords().equals(coordinates.asCoords()) && e.getType() == GameEvent.Type.BUILD) {
				output = e;
				return output;
			}
		}
		return output;
	}
	
	/**
	 * Returns the research Event for given Coordinates if the Player owns that Planet, returns null if not
	 * @param coordinates
	 * @return Event or Null(if there is no research Event or Planet)
	 */
	public GameEvent getResearchEventByCoords(Coordinates coordinates) {
		GameEvent output = null;
		for (GameEvent e: events) {
			if (e.getCoordinates().asCoords().equals(coordinates.asCoords()) && e.getType() == GameEvent.Type.RESEARCH) {
				output = e;
				return output;
			}
		}
		return output;
	}
	
	public ArrayList<GameEvent> getEvents() {
		return events;
	}
	
	/**
	 * Returns all Events from a given Type
	 * @param type ATTACK or DEFEND .. etc
	 * @return ArrayList<GameEvent> all Events of that Kind
	 */
	public ArrayList<GameEvent> getEvents(String type) {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType().equals(type)) { output.add(e); }
		}
		return output;
	}
	
	/**
	 * Returns all Fleet Events
	 * @return ArrayList<GameEvent> Array with all Events of that Type
	 */
	public ArrayList<GameEvent> getFleetEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.ATTACK || e.getType() == GameEvent.Type.TRANSPORT) { output.add(e); }
		}
		return output;
	}

	/**
	 * Returns all Attack Events
	 * @return ArrayList<GameEvent> Array with all Events of that Type
	 */
	public ArrayList<GameEvent> getAttackEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.ATTACK) { output.add(e); }
		}
		return output;
	}
	
	/**
	 * Returns all Transport Events
	 * @return ArrayList<GameEvent> Array with all Events of that Type
	 */
	public ArrayList<GameEvent> getTransportEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.TRANSPORT) { output.add(e); }
		}
		return output;
	}
	
	
	/**
	 * Returns all Defend Events
	 * @return ArrayList<GameEvent> Array with all Events of that Type
	 */
	public ArrayList<GameEvent> getDefendEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.DEFEND) { output.add(e); }
		}
		return output;
	}
	
	/**
	 * Returns all Buildings Events
	 * @return ArrayList<GameEvent> Array with all Events of that Type
	 */
	public ArrayList<GameEvent> getBuildingEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.BUILD) { output.add(e); }
		}
		return output;
	}
	
	/**
	 * Returns all Research Events
	 * @return ArrayList<GameEvent> Array with all Events of that Type
	 */
	public ArrayList<GameEvent> getResearchEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.RESEARCH) { output.add(e); }
		}
		return output;
	}
	

	
	/**
	 * Returns all Events that need to be calculated because they already happened
	 * @return ArrayList<GameEvent> of events that need to be calculated
	 */
	public ArrayList<GameEvent> getCalcEvents() {
		Date now = new Date();
		return getCalcEventsToDate(now);
	}
	
	/**
	 * Returns all Events that need to be calculated to a certain point of time
	 * @param date up to all calculate events are returned
	 * @return
	 */
	public ArrayList<GameEvent> getCalcEventsToDate(Date date) {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		sortMe();
		for (GameEvent e : events) {
			if (date.getTime() > e.getEndTime().getTime()) {
				output.add(e);
			}
			// TODO Lowprio break if there arent any more calc events
		}
		return output;
	}
	
	/**
	 * Updates this EventCenter with the DataBase
	 */
	public void update() {

		if (this.lastupdate == null) {
			loadAll();
			sortMe();
			return;
		}

		// Cooldown 10 Sec for updating with DataBase to increase overall performance
		if (    ((new Date().getTime() - this.lastupdate.getTime()) / 1000) > 10 ) {
			this.events.addAll(DBEvent.getEvents(user, lastupdate));
			this.lastupdate = new Date();
			sortMe();
		}
	}
	
	/**
	 * Should be used once if the EventCenter is created or started
	 */
	public void init() {

		// Build
		for (Planet planet : user.getPlanets()) {
			GameEvent buildevent = this.getBuildEventByCoords(planet.getCoords());
			GameEvent researchevent = this.getResearchEventByCoords(planet.getCoords());
			if (buildevent != null) {
				planet.setIsBuilding(buildevent.getBuildingName());
			}
			if (researchevent != null) {
				planet.setIsResearching(researchevent.getBuildingName());
			}
		}
		
		// ATTACK EVENT CONV
		for (GameEvent event : events) {
			if (event.getType() == GameEvent.Type.ATTACK && user.getPlanetByCoordinates(event.getTarget()) != null) {
				event.setType(GameEvent.Type.DEFEND);
				System.out.print("conv attack -> defend;");
			}
		}
	}
	
	/**
	 * Sorts all Events
	 */
	private void sortMe() {
		events.sort(
				new Comparator<GameEvent>() {
					public int compare(GameEvent first, GameEvent second) {
						return first.compareTo(second);
					}
				});
	}	
	
	/**
	 * Loads all Events from DataBase 
	 */
	private void loadAll() {
		this.events.addAll(DBEvent.getEvents(user));
		this.lastupdate = new Date();
	}
	
	/** Deletess a given Event
	 * @param event
	 * @return
	 */
	public boolean deleteEvent(GameEvent event) {
		events.remove(event);
		return true;
	}

}
