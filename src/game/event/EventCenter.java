package game.event;

import java.util.*;

import database.*;
import game.planet.Coordinates;
import game.planet.Planet;
import game.player.*;

public class EventCenter {
	
	Player 							user = null;
	private ArrayList<GameEvent>	events = new ArrayList<GameEvent>();
	private Date					lastupdate = null;
	
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
	
	public ArrayList<GameEvent> getEvents(String type) {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType().equals(type)) { output.add(e); }
		}
		return output;
	}
	
	public ArrayList<GameEvent> getFleetEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.ATTACK || e.getType() == GameEvent.Type.TRANSPORT) { output.add(e); }
		}
		return output;
	}

	public ArrayList<GameEvent> getAttackEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.ATTACK) { output.add(e); }
		}
		return output;
	}
	
	public ArrayList<GameEvent> getTransportEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.TRANSPORT) { output.add(e); }
		}
		return output;
	}
	
	
	public ArrayList<GameEvent> getDefendEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.DEFEND) { output.add(e); }
		}
		return output;
	}
	
	public ArrayList<GameEvent> getBuildingEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.BUILD) { output.add(e); }
		}
		return output;
	}
	
	public ArrayList<GameEvent> getResearchEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.RESEARCH) { output.add(e); }
		}
		return output;
	}
	

	public ArrayList<GameEvent> getCalcEvents() {
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		Date now = new Date();
		for (GameEvent e : events) {
			if (now.getTime() > e.getEndTime().getTime()) {
				output.add(e);
			}
		}
		return output;
	}
	
	private void update() {
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
	
	private void init() {

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
	
	private void sortMe() {
		events.sort(
				new Comparator<GameEvent>() {
					public int compare(GameEvent first, GameEvent second) {
						return first.compareTo(second);
					}
				});
	}	
	
	private void loadAll() {
		this.events.addAll(DBEvent.getEvents(user));
		this.lastupdate = new Date();
	}

}
