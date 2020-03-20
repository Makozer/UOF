package game.player;

import static game.settings.GameSettings.DEBUGMODE;

import java.util.*;

import community.message.*;
import database.DBEvent;
import database.DBPeon;
import database.DBPlanet;
import game.GameEvent;
import game.GameEvent.Type;
import game.fleet.*;
import game.planet.*;
import game.planet.buildings.*;
import game.research.*;
import game.ressource.*;
import game.settings.ShipRegister;
import game.utils.*;

public class Player {
	
	private PersonalData 				persData = null;	
	private TechTree 					techtree = null;
	
	private ArrayList<GameEvent> 		events 			= new ArrayList<GameEvent>();	
	private ArrayList<Planet> 			planets 		= new ArrayList<Planet>();
	private ArrayList<Message>			messages		= new ArrayList<Message>();
	
	boolean 							hasNewMessage 	= false;
	private int 						activePlanet 	= 0;
	
	public Player() {}
	
	public Player(int playerid, TechTree techtree) {
		this.techtree = techtree;
		this.persData = new PersonalData(playerid, "", "", "", "", new Date(), new Date(), new Date());
	}

	public Player(PersonalData data, TechTree techtree) {
		this.persData = data;
		this.techtree = techtree;
	}

	public static void main(String[] args) {
		// Testmain
	}
	
	public void update() {
		// Update Planets
		for (Planet planet: this.getPlanets()) { planet.update();}
		
		// Update Events
		updateEvents();
	}
	
	public boolean updateEvents() {
		Date now = new Date();
		Iterator<GameEvent> i = events.iterator();
		while (i.hasNext()) {
			GameEvent event = i.next();					
			if (event.getEndTime().getTime() < now.getTime()) {
				switch (event.getType()) {
				case ATTACK:
					calculateAttack(event, i);
					break;
				case DEFEND:
					calculateDefend(event, i);
					break;
				case TRANSPORT:
					calculateTransport(event, i);
					break;
				case BUILD:
					calculateBuild(event, i);
					break;
				default:
					throw new IllegalArgumentException("Wrong Event in your List");				
				}
			}
		}
		return true;
	}
	
	public void calculateAttack(GameEvent event, Iterator<GameEvent> i) {
		
	}
	
	public void calculateDefend(GameEvent event, Iterator<GameEvent> i) {
		
	}
	
	public void calculateTransport(GameEvent event, Iterator<GameEvent> i) {
		Planet planet = this.getPlanetByCoordinates(event.getTarget());
		if (planet != null) {
			planet.getFleet().addFleet(event.getFleet());
			planet.increaseRessources(event.getRessource());
			// TODO DATABASE INFORM
			i.remove();			
		} else {
			// TODO calculate with Database from other players planet!!!
		}
	}

	public void calculateBuild(GameEvent event, Iterator<GameEvent> i) {
		Planet planet = this.getPlanetByCoordinates(event.getCoordinates());
		if (planet == null) {return;}
		ABuilding building = planet.getBuildingByName(event.getBuildingName());
		building.levelUp(event.getEndTime());
		planet.setIsBuilding("");
		GameMessage message = new GameMessage(planet.getCoords().asCoords() + " Your " + building.getName() + "(new Level: " + building.getLevel() + ") has finished upgrading!", "Bla bla bla Mr. Freeman.");
		this.addMessage(message);
		i.remove();
		
		// Update DataBase
		DBPlanet.updatePlanet(this, planet);
		DBEvent.deleteEvent(event.getId());
	}
	
	public void increaseRess(Planet planet, ArrayList<ARessource> ress) {
		// Increasing Planets Ressources
		HashMap<String, ARessource> planetRessources = planet.getRessources();
		for (ARessource r: ress) {
			planetRessources.get(r.getName()).increaseValue(r.getValue());					
		}
	}
	
	public boolean hasRess(Planet planet, ArrayList<ARessource> ressCosts) {
		// Check if the Planet has enough Ressources
		HashMap<String, ARessource> planetRessources = planet.getRessources();
		for (ARessource r: ressCosts) {
			if (r.getValue() > planetRessources.get(r.getName()).getValue()) {
				if (DEBUGMODE) {System.out.println("Required " + r.getName() + "(" + r.getValue() + ") > " + planetRessources.get(r.getName()).getName() + "(" +  planetRessources.get(r.getName()).getValue() + ")");}
				return false;
			}
		}
		return true;
	}
	
	public boolean decreaseRess(Planet planet, ArrayList<ARessource> ressCosts) {		
		if (!this.hasRess(planet, ressCosts)) { return false; }
		HashMap<String, ARessource> planetRessources = planet.getRessources();
		for (ARessource r: ressCosts) {
			planetRessources.get(r.getName()).decreaseValue(r.getValue());
			
		}
		return true;
	}
	
	
	public boolean doAttack(Coordinates planetcoords, Coordinates target, Fleet fleet) {
		return false;
	}
	
	public boolean doResearch(Coordinates planetcoords, String research) {
		// TODO
		// Create Game Event
		return false;
	}
	
	public void doCancelResearch(GameEvent event) {
		// TODO
	}
	
	public boolean doBuild(String buildingName) {
		
		// Required Objects
		Planet planet = getActivePlanet();
		Coordinates planetcoords = planet.getCoords();
		ABuilding building = getActivePlanet().getBuildingByName(buildingName);
		ArrayList<ARessource> buildCosts = building.getBuildCosts();
		
		// Check if the Planet has enough Ressources and then decreases
		if (!this.decreaseRess(planet, buildCosts)) { 
			if (DEBUGMODE) {for(ARessource r : buildCosts) {System.out.println("r=" + r.toString());}System.out.println("NOT ENOUGH RESSOURCES");}
			return false; 
		}
		
		// Creating GameEvent
		Date endTime = new Date(new Date().getTime() + (long)(building.getTimeToBuild() * 1000));
		GameEvent event = new GameEvent(this.getPersData().getId(), GameEvent.Type.BUILD, planetcoords, buildingName, buildCosts, new Date(), endTime);
		
		//  DB Inform, get EventId
		int eventid = DBEvent.createEvent(event);
		if (eventid == 0) { return false; }
		event.setId(eventid);
		this.addEvent(event);
		planet.setIsBuilding(buildingName);
		
	
		return true;
	}
	
	public boolean doCancelBuild(GameEvent event) {
		
		// Required Objects
		Planet planet = getPlanetByCoordinates(event.getCoordinates());
		ABuilding building = planet.getBuildingByName(event.getBuildingName());
		ArrayList<ARessource> buildCosts = building.getBuildCosts();		
		
		// Increasing Planets Ressources
		this.increaseRess(planet, buildCosts);
		
		// Deleting GameEvent
		this.removeEvent(event);
		planet.setIsBuilding("");
		
		// DataBase inform
		DBEvent.deleteEvent(event.getId());	
		return true;
	}
	
	public void addPlanet(Planet planet) {
		this.planets.add(planet);
	}
	
	public void addPlanets(ArrayList<Planet> planets) {
		this.planets.addAll(planets);
	}
	
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	
	public Planet getPlanet(int n) {
		return planets.get(n);
	}
	
	public Planet getPlanetByCoordinates(Coordinates coordinates) {
		Planet output = null;
		for (Planet p: planets) {
			if (coordinates.asCoords().equals(p.getCoords().asCoords())) {
				return p;
			}
		}
		return output;
	}
	
	public void removePlanet(Planet planet) {
		this.planets.remove(planet);
	}	
	
	public void addEvent(GameEvent event) {
		this.events.add(event);
	}
	
	public void setEvents(ArrayList<GameEvent> events) {
		this.events = events;
		// Build
		for (Planet planet : planets) {
			GameEvent buildevent = this.getBuildEventByCoords(planet.getCoords());
			// GameEvent researchevent = this.getResearchEventByCoords(planet.getCoords());
			if (buildevent != null) {
				planet.setIsBuilding(buildevent.getBuildingName());
			}
			// if researchevent != null
		}
		// TODO same for research at university
	}
	
	public void removeEvent(GameEvent event) {
		this.events.remove(event);
	}
	
	public GameEvent getBuildEventByCoords(Coordinates coordinates) {
		GameEvent output = null;
		for (GameEvent e: events) {
			if (e.getCoordinates().asCoords().equals(coordinates.asCoords()) && e.getType() == GameEvent.Type.BUILD) {
				output = e;
			}
		}
		return output;
	}
	
	public ArrayList<GameEvent> getEvents() {
		sortEvents();
		return events;
	}
	
	public ArrayList<GameEvent> getEvents(String type) {
		sortEvents();
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType().equals(type)) { output.add(e); }
		}
		return output;
	}
	
	public ArrayList<GameEvent> getFleetEvents() {
		sortEvents();
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.ATTACK || e.getType() == GameEvent.Type.TRANSPORT) { output.add(e); }
		}
		return output;
	}

	public ArrayList<GameEvent> getAttackEvents() {
		sortEvents();
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.ATTACK) { output.add(e); }
		}
		return output;
	}
	
	public ArrayList<GameEvent> getTransportEvents() {
		sortEvents();
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.TRANSPORT) { output.add(e); }
		}
		return output;
	}
	
	
	public ArrayList<GameEvent> getDefendEvents() {
		sortEvents();
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.DEFEND) { output.add(e); }
		}
		return output;
	}
	
	public ArrayList<GameEvent> getBuildingEvents() {
		sortEvents();
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.BUILD) { output.add(e); }
		}
		return output;
	}
	
	public ArrayList<GameEvent> getEventsSorted() {
		sortEvents();
		return events;
	}
	
	public void sortEvents() {
		events.sort(
				new Comparator<GameEvent>() {
					public int compare(GameEvent first, GameEvent second) {
						return first.compareTo(second);
					}
				});
	}	

	public PersonalData getPersData() {
		return persData;
	}

	public void setPersData(PersonalData persData) {
		this.persData = persData;
	}

	public TechTree getTechTree() {
		return techtree;
	}

	public void setTechtree(TechTree techtree) {
		this.techtree = techtree;
	}

	public Planet getActivePlanet() {
		return this.getPlanet(activePlanet);
	}

	public void setActivePlanet(int activePlanet) {
		this.activePlanet = activePlanet;
	}

	public ArrayList<Message> getMessages() {
		this.messages.sort(
				new Comparator<Message>() {
					public int compare(Message first, Message second) {
						return first.compareTo(second);
					}
				});
		this.setHasNewMessage(false);
		return messages;
	}

	public void addMessage(Message message) {
		this.messages.add(message);
		this.setHasNewMessage(true);
	}	
	
	public void addMessageArray(ArrayList<Message> messages) {
		this.messages.addAll(messages);
	}
	
	public void deleteMessage(int msgId) {
		for (Message m: messages) {
			if (m.getMsgId() == msgId) {
				this.messages.remove(m);
				break;
			}
		}
		
	}
	
	public int getMessageCount() {
		return this.messages.size();
	}
	
	public int getNewMessageCount() {
		return 1;
	}

	public boolean hasNewMessage() {
		return hasNewMessage;
	}

	public void setHasNewMessage(boolean hasNewMessage) {
		this.hasNewMessage = hasNewMessage;
	}
	
	public String getDisplayName() {
		return this.persData.getDisplayName();
	}
	
	
}
