package game.player;

import static game.settings.GameSettings.DEBUGMODE;

import java.util.*;

import community.message.*;
import database.*;
import game.*;
import game.control.Combat;
import game.control.GameLoader;
import game.event.EventCenter;
import game.event.GameEvent;
import game.event.GameEvent.Type;
import game.fleet.*;
import game.planet.*;
import game.planet.buildings.*;
import game.research.*;
import game.ressource.*;
import game.settings.*;
import game.utils.*;

public class Player {
	
	private PersonalData 				persData = new PersonalData(0, "Empty PersonalData", "", "", "", new Date(), new Date(), new Date());	
	private TechTree 					techtree = null;
	
	private ArrayList<GameEvent> 		events 			= new ArrayList<GameEvent>();	

	private EventCenter					eventCenter		= null;

	
	private ArrayList<Planet> 			planets 		= new ArrayList<Planet>();
	private int 						activePlanet 	= 0;
	
	private Inbox						inbox 			= new Inbox(this);


	public Player() {}
	
	public Player(int playerid, TechTree techtree) {
		this.techtree = techtree;
		this.persData = new PersonalData(playerid, "Empty PersonalData", "", "", "", new Date(), new Date(), new Date());
	}

	public Player(PersonalData data, TechTree techtree) {
		this.persData = data;
		this.techtree = techtree;
	}

	public static void main(String[] args) {
		// Testmain
	}
	
	public void update(boolean war) {
		
		// Update Events
		//updateEvents(war); // EVENTCENTER OUTSURCED
		
		// Update Planets
		//updatePlanets();

	}
	
	public void updatePlanets() {
		for (Planet planet: this.getPlanets()) { planet.update(this);}
	}
	
	public boolean updateEvents() {
		return updateEvents(true);
	}
	
	public boolean updateEvents(boolean war) {
		Date now = new Date();
		Iterator<GameEvent> i = events.iterator();
		while (i.hasNext()) {
			GameEvent event = i.next();					
			if (event.getEndTime().getTime() < now.getTime()) {
				switch (event.getType()) {
				case ATTACK:
					if (war) {calculateAttack(event, i);}
					break;
				case DEFEND:
					if (war) {calculateDefend(event, i);}
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
	
	public void calculateAttack(GameEvent event, Iterator<GameEvent> i) {
		// Required Objects
		// Actual Player
		Player thisplayer = this;
		int myplayerid = this.getPersData().getId();
		Planet myplanet = this.getActivePlanet();
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
				DBEvent.deleteEvent(event.getId());	
				i.remove();
				return;
		}
		Planet enemyplanet = enemyplayer.getPlanetByCoordinates(event.getTarget());
		Fleet enemyfleet = enemyplanet.getFleet();
		
		myfleet.setPlayerId(this.getPersData().getId());
		enemyfleet.setPlayerId(enemyplayerid);
		
		Fleet myfleetclone = myfleet.clone();
		Fleet enemyfleetclone = enemyfleet.clone();
		
		Fleet winner = Combat.fight(myfleet, enemyfleet);
		if (winner.getPlayerId() == myplayerid) {
			ArrayList<ARessource> prey = new ArrayList<ARessource>();
			ARessource iron = enemyplanet.getIron(); ARessource rare = enemyplanet.getRareEarth();ARessource water = enemyplanet.getWater();ARessource tritium = enemyplanet.getTritium();
			prey.add(iron);prey.add(rare);prey.add(water);prey.add(tritium);
			Combat.createCombatLog(this, enemyplayer, event.getTarget(), winner, myfleetclone, enemyfleetclone, prey);
			GameEvent survivorevent = new GameEvent(0, myplayerid, myplayerid, GameEvent.Type.TRANSPORT, event.getTarget(), event.getCoordinates(), "", winner, prey, event.getEndTime(), 
										new Date(new Date().getTime() + (event.getEndTime().getTime() - event.getStartTime().getTime())));
			DBEvent.createEvent(survivorevent);
		} else {
			// Fleet has been destructed
			Combat.createCombatLog(enemyplayer, this, event.getTarget(), winner, myfleetclone, enemyfleetclone, new ArrayList<ARessource>());
		}
		
		// DataBase inform
		DBEvent.deleteEvent(event.getId());	
		i.remove();

	}
	
	public void calculateDefend(GameEvent event, Iterator<GameEvent> i) {
		// Required Objects		
		// Actual Player
		Player thisplayer = this;
		Planet myplanet = this.getActivePlanet();
		Fleet myfleet = myplanet.getFleet();
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
		this.getInbox().addMessage(message);
		i.remove();
		
		// Update DataBase
		DBPlanet.updatePlanet(this, planet);
		DBEvent.deleteEvent(event.getId());
	}
	
	public void calculateResearch(GameEvent event, Iterator<GameEvent> i) {
		Planet planet = this.getPlanetByCoordinates(event.getCoordinates());
		if (planet == null) {return;}
		Research research = planet.getResearchByName(event.getBuildingName());
		this.techtree.setLevel(research.getName(), (this.techtree.getLevel(research.getName()) + 1));
		planet.setIsResearching("");
		GameMessage message = new GameMessage(planet.getCoords().asCoords() + " Your " + research.getName() + "(new Level: " + research.getLevel() + ") has finished upgrading!", "Bla bla bla Mr. Freeman.");
		this.getInbox().addMessage(message);
		i.remove();
		
		// Update DataBase
		DBTechTree.updateTechTree(event.getPlayerid(), this.techtree);
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
	
	public boolean doResearch(String researchname) {
		// Required Objects
		Planet planet = getActivePlanet();
		Coordinates planetcoords = planet.getCoords();
		Research research = getActivePlanet().getResearchByName(researchname); // TODO planet ... -> techtree
		if (research == null) {System.out.println("doResearch research==null"); return false;}
		ArrayList<ARessource> researchCosts = research.getResearchCosts();
				
		// Check if the Planet has enough Ressources and then decreases
		if (!this.decreaseRess(planet, researchCosts)) { 
			if (DEBUGMODE) {for(ARessource r : researchCosts) {System.out.println("r=" + r.toString());}System.out.println("NOT ENOUGH RESSOURCES");}
			return false; 
		}
				
		// Creating GameEvent
		Date endTime = new Date(new Date().getTime() + (long)(research.getTimeToResearch(planet.getUniversity().getLevel()) * 1000));
		GameEvent event = new GameEvent(this.getPersData().getId(), GameEvent.Type.RESEARCH, planetcoords, researchname, researchCosts, new Date(), endTime);
				
		//  DB Inform, get EventId
		int eventid = DBEvent.createEvent(event);
		if (eventid == 0) { return false; }
		event.setId(eventid);
		this.addEvent(event);
		planet.setIsResearching(researchname);			
			
		return true;
	}
	
	public boolean doCancelResearch(GameEvent event) {
		// Required Objects
		Planet planet = getPlanetByCoordinates(event.getCoordinates());
		Research research = getActivePlanet().getResearchByName(event.getBuildingName());
		ArrayList<ARessource> buildCosts = research.getResearchCosts();		
				
		// Increasing Planets Ressources
		this.increaseRess(planet, buildCosts);
				
		// Deleting GameEvent
		this.removeEvent(event);
		planet.setIsResearching("");
				
		// DataBase inform
		DBEvent.deleteEvent(event.getId());	
		return true;
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
		if (planets == null) {System.err.println("NULLPOINTER @ addPlanets");return;}
		this.planets.addAll(planets);
	}
	
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	
	public Planet getPlanet(int n) {
		return planets.get(n);
	}
	
	public Planet getPlanetByCoordinates(Coordinates coordinates) {
		for (Planet p: planets) {
			if (coordinates.asCoords().equals(p.getCoords().asCoords())) {
				return p;
			}
		}
		return null;
	}     
	
	public void removePlanet(Planet planet) {
		this.planets.remove(planet);
	}	
	
	public void addEvent(GameEvent event) {
		this.getEventCenter().addEvent(event);
	}
	
	public void setEvents(ArrayList<GameEvent> events) {
		this.events = events;
		// Build
		for (Planet planet : planets) {
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
			if (event.getType() == GameEvent.Type.ATTACK && this.getPlanetByCoordinates(event.getTarget()) != null) {
				event.setType(GameEvent.Type.DEFEND);
				System.out.print("conv attack -> defend;");
			}
		}
	}
	
	public void removeEvent(GameEvent event) {
		this.getEventCenter().removeEvent(event);
	}
	
	public GameEvent getBuildEventByCoords(Coordinates coordinates) {
		return this.getEventCenter().getBuildEventByCoords(coordinates);
		/*
		GameEvent output = null;
		for (GameEvent e: events) {
			if (e.getCoordinates().asCoords().equals(coordinates.asCoords()) && e.getType() == GameEvent.Type.BUILD) {
				output = e;
				return output;
			}
		}
		return output;
		*/
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
		return eventCenter.getBuildingEvents();
		/*
		sortEvents();
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.BUILD) { output.add(e); }
		}
		return output;
		*/
	}
	
	public ArrayList<GameEvent> getResearchEvents() {
		sortEvents();
		ArrayList<GameEvent> output = new ArrayList<GameEvent>();
		for (GameEvent e: events) {
			if (e.getType() == GameEvent.Type.RESEARCH) { output.add(e); }
		}
		return output;
	}
	
	public ArrayList<GameEvent> getEventsSorted() {
		sortEvents();
		return events;
	}
	
	public void loadEvents() {
		if (eventCenter == null) {
			this.eventCenter = new EventCenter(this);
		}
	}
	
	private void sortEvents() {
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
	
	public Inbox getInbox() {
		return inbox;
	}

	public EventCenter getEventCenter() {
		return eventCenter;
	}

	public String getDisplayName() {
		return this.persData.getDisplayName();
	}
	
}
