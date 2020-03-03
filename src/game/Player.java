package game;

import java.util.*;

import game.fleet.*;
import game.planet.*;
import game.research.TechTree;

public class Player {
	
	private int 	id = 0;
	private String 	email = "";
	
	private String 	displayName = "";
	private String 	preName = "";
	private String 	surName = "";
	
	private Date 	created = 	null;
	private Date 	lastLogin = null;	
	
	private TechTree techtree = null;
	
	private ArrayList<Planet> 			planets 		= new ArrayList<Planet>();
	private ArrayList<TravelingFleet> 	travelingFleets = new ArrayList<TravelingFleet>();
	
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
		this.email = "martin@martin-kohne.de";
		this.displayName = "Makozer";
		this.preName = "Martin";
		this.surName = "Kohne";
		this.created = new Date(2011);
		this.lastLogin = new Date();
		
		TechTree techtree = new TechTree();
		techtree.testFill();
		
		Planet planet = new Planet(techtree, new Coordinates(1, 33, 7), "MartinsPlanet1", 6666, 3333, 6666, 666, 3, 2, 2, 5, 2, 3, 1, 4, 3, 2, 1);
		planet.testFill();
		this.addPlanet(planet);
		
		TravelingFleet tv = new TravelingFleet(new Coordinates(1, 33, 6), new Date());
		TravelingFleet tv2 = new TravelingFleet(new Coordinates(1, 33, 8), new Date());
		tv.testFill(techtree);
		tv2.testFill(techtree);
		this.travelingFleets.add(tv);
		this.travelingFleets.add(tv2);
		
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
	
	public void removePlanet(Planet planet) {
		this.planets.remove(planet);
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

	public ArrayList<TravelingFleet> getTravelingFleets() {
		return travelingFleets;
	}	

}
