package game;

import java.util.*;

import game.GameEvent.Type;
import game.fleet.*;
import game.planet.*;
import game.planet.buildings.*;
import game.research.TechTree;
import game.ressource.ARessource;
import game.ressource.Iron;
import game.ressource.Water;
import game.utils.DateUtils;

public class Player {
	
	private int 	id = 0;
	private String 	email = "";
	
	private String 	displayName = "";
	private String 	preName = "";
	private String 	surName = "";
	private Date	birthday = null;
	
	private Date 	created = 	null;
	private Date 	lastLogin = null;	
	
	private TechTree techtree = null;
	
	private ArrayList<GameEvent> 		events 			= new ArrayList<GameEvent>();	
	private ArrayList<Planet> 			planets 		= new ArrayList<Planet>();
	
	public Player() {}

	public Player(int id, String email, String displayName, String preName, String surName, Date lastLogin,
			Date created, TechTree techtree) {
		this.id = id;
		this.email = email;
		this.displayName = displayName;
		this.preName = preName;
		this.surName = surName;
		this.lastLogin = lastLogin;
		this.created = created;
		this.techtree = techtree;
	}

	public static void main(String[] args) {
		// Testmain

	}
	
	public void init() {
		// initalize
	}
	
	public void testFill() {
		this.id = 1337;
		this.email = "mk113@web.de";
		this.displayName = "Makozer";
		this.preName = "Martin";
		this.surName = "K";
		this.created = new Date(2011);
		this.lastLogin = new Date();
		
		TechTree techtree = new TechTree();
		techtree.testFill();
		
		Planet planet = new Planet(techtree, new Coordinates(1, 33, 7), "MartinsPlanet1", 6666, 3333, 6666, 666, 3, 2, 2, 5, 2, 3, 1, 4, 3, 2, 1);
		planet.testFill();
		this.addPlanet(planet);
		
		
		// Erstellung von 4 BeispielEvents
		Fleet fleet1 = new Fleet();
		Fleet fleet2 = new Fleet();
		fleet1.testFill(techtree);
		fleet2.testFill(techtree);
		
		Date in1h = DateUtils.getFutureDateByHours(1);
		Date in2h = DateUtils.getFutureDateByHours(2);
		Date in11h = DateUtils.getFutureDateByHours(11);
		Date in22h = DateUtils.getFutureDateByHours(22);
		
		ArrayList<ARessource> ress = new ArrayList<ARessource>(Arrays.asList(new Iron(1234), new Water(4321)));

		GameEvent attack 	= new GameEvent(GameEvent.Type.ATTACK, 		this.getPlanet(0).getCoords(), 	new Coordinates(1, 33, 6), 		fleet1, null, new Date(), in1h);
		GameEvent defend 	= new GameEvent(GameEvent.Type.DEFEND, 		new Coordinates(1, 33, 8), 		this.getPlanet(0).getCoords(), 	fleet2, null, new Date(), in2h);
		GameEvent transport = new GameEvent(GameEvent.Type.TRANSPORT, 	this.getPlanet(0).getCoords(), 	new Coordinates(1, 33, 8), 		fleet2, ress, new Date(), in11h);
		GameEvent build 	= new GameEvent(GameEvent.Type.BUILD, 		this.getPlanet(0).getCoords(), 	null, new Date(), in22h);
		
		this.events.add(attack);
		this.events.add(defend);
		this.events.add(transport);
		this.events.add(build);
	}
	
	public boolean doAttack(Coordinates planetcoords, Coordinates target, Fleet fleet) {
		// TODO
		// Create Game Event
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
	
	public boolean doBuild(Coordinates planetcoords, String building) {
		// TODO
		// Create Game Event
		return false;
	}
	
	public void doCancelBuild(GameEvent event) {
		// TODO
	}
	
	public void eventUpdate() {
		// TODO updates and calculates all Events
		
	}
	
	public void addPlanet(Planet planet) {
		this.planets.add(planet);
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
	
	public ArrayList<GameEvent> getEvents() {
		return events;
	}
	
	public ArrayList<GameEvent> getEventsSorted() {
		events.sort(
				new Comparator<GameEvent>() {
					public int compare(GameEvent first, GameEvent second) {
						return first.compareTo(second);
					}
				});
		return events;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getSurName() {
		return surName;
	}	

	public Date getBirthday() {
		return birthday;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public TechTree getTechtree() {
		return techtree;
	}

	public void setTechtree(TechTree techtree) {
		this.techtree = techtree;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public Date getCreated() {
		return created;
	}

}
