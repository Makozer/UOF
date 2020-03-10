package game;

import java.util.*;

import community.message.*;
import game.fleet.*;
import game.planet.*;
import game.planet.buildings.*;
import game.research.*;
import game.ressource.*;
import game.settings.ShipSettings;
import game.utils.*;

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
	private ArrayList<Message>			messages		= new ArrayList<Message>();
	
	boolean 							hasNewMessage 	= false;
	private int 						activePlanet 	= 0;
	
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
		
		Player player = new Player();
		TechTree techtree = new TechTree();
		techtree.testFill();
		player.testFill();
		player.setTechtree(techtree);
		System.out.println("Alle Schiffe:");
		for (ASpaceShip s: ShipSettings.getWholeShipList(techtree)) {
			System.out.println(s.toString());
		}
		System.out.println("Erforschte Schiffe:");
		for (ASpaceShip s: techtree.getAllResearchedShips()) {
			System.out.println(s.toString());
		}
		//System.out.println(player.getDefendEvents().size());

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
		this.setTechtree(techtree);
		Planet planet = new Planet(techtree, new Coordinates(1, 33, 7), "Martin und Nehles Planet", 66666, 33333, 66666, 666, 3, 2, 2, 5, 2, 3, 1, 4, 3, 2, 1);
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
		//GameEvent build 	= new GameEvent(GameEvent.Type.BUILD, 		this.getPlanet(0).getCoords(), 	"HeadQuarter",					ress, new Date(), in22h);
		
		this.events.add(attack);
		this.events.add(defend);
		this.events.add(transport);
		//this.events.add(build);
		
		this.addMessage(new GameMessage("HeadQuarter wurde auf 1:33:7 auf LvL 11 erhöht!", "Test"));
		this.addMessage(new CommunityMessage(1337, 666, "You're evil", "muahahahhahaa", new Date()));
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
				return false;
			}
		}
		return true;
	}
	
	public boolean decreaseRess(Planet planet, ArrayList<ARessource> ressCosts) {
		HashMap<String, ARessource> planetRessources = planet.getRessources();
		if (!this.hasRess(planet, ressCosts)) { return false; }
		for (ARessource r: ressCosts) {
			planetRessources.get(r.getName()).decreaseValue(r.getValue());
			
		}
		return true;
	}
	
	
	public boolean doAttack(Coordinates planetcoords, Coordinates target, Fleet fleet) {
		// TODO
		// Zeit Berechnung!
		// Verbrauch für KraftStoff Berechnung!
		// Create Game Event
		//
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
		if (!this.decreaseRess(planet, buildCosts)) { return false; }
		
		// Creating GameEvent
		GameEvent event = new GameEvent(GameEvent.Type.BUILD, planetcoords, buildingName, buildCosts, new Date(), building.getBuildTime());
		this.addEvent(event);
		planet.setIsBuilding(buildingName);
		
		// TODO DataBase Connection :)		
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
		
		// TODO DataBase Connection -> eintragen		
		return true;
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
		events.sort(
				new Comparator<GameEvent>() {
					public int compare(GameEvent first, GameEvent second) {
						return first.compareTo(second);
					}
				});
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

	public TechTree getTechTree() {
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
	
	
	
}
