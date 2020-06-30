package game.planet;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import database.DBPlanet;
import game.fleet.*;
import game.fleet.tier1.*;
import game.fleet.tier2.*;
import game.fleet.tier3.*;
import game.planet.buildings.*;
import game.planet.buildings.mining.*;
import game.planet.buildings.storage.*;
import game.player.Player;
import game.research.*;
import game.ressource.*;
import game.settings.ResearchRegister;
import game.utils.DateUtils;
import game.utils.NumberUtils;

/**
 * The Main Class for Planets, everthing related to Planets happens here.
 * @author Martin
 *
 */
public class Planet {
	
	private Date					lastupdate = new Date();
	private TechTree				techtree = null;
	
	// Basic Propertys
	private Coordinates 			coordinates = null;
	private String 					name = "";
	
	// Activitys
	private String					isBuilding = "";
	private String					isResearching = "";
	
	// Ressources
	private HashMap<String, ARessource>	ressources = new HashMap<String, ARessource>();
	
	// Buildings
	private HashMap<String, ABuilding>	buildings = new HashMap<String, ABuilding>();
	
	// Fleet that idles on the Planet
	private Fleet					fleet = new Fleet();
	
	/**
	 * Constructor to create a Planet from DataBase Inputs
	 * @param techtree
	 * @param coordinates
	 * @param name
	 * @param buildingSQL
	 * @param ressourceSQL
	 * @param fleetSQL
	 * @param spaceportqueueSQL
	 * @param timestamp
	 */
	public Planet(TechTree techtree, Coordinates coordinates, String name, String buildingSQL, String ressourceSQL, String fleetSQL, String spaceportqueueSQL, String timestamp) {
		this.sqlLoad(techtree, coordinates, name, buildingSQL, ressourceSQL, fleetSQL, spaceportqueueSQL, timestamp);
	}
	
	/** Constructor to create a Planet with given Parameters
	 * @param techtree
	 * @param coordinates
	 * @param name
	 * @param ironValue
	 * @param rareEarthValue
	 * @param waterValue
	 * @param tritiumValue
	 * @param headQuarterLvl
	 * @param universityLvl
	 * @param spacePortLvl
	 * @param ironMineLvl
	 * @param rareEarthMineLvl
	 * @param fountainLvl
	 * @param tritiumFabricLvl
	 * @param ironStorageLvl
	 * @param rareEarthStorageLvl
	 * @param waterStorageLvl
	 * @param tritiumStorageLvl
	 * @param lastupdate
	 */
	public Planet(TechTree techtree, Coordinates coordinates, String name, 
						int ironValue, int rareEarthValue, int waterValue, int tritiumValue, 
						int headQuarterLvl, int universityLvl, int spacePortLvl, 
						int ironMineLvl, int rareEarthMineLvl, int fountainLvl, int tritiumFabricLvl, 
						int ironStorageLvl, int rareEarthStorageLvl, int waterStorageLvl, int tritiumStorageLvl, Date lastupdate) {
		this.createThisPlanet(techtree, coordinates, name, 
				ironValue, rareEarthValue, waterValue, tritiumValue, 
				headQuarterLvl, universityLvl, spacePortLvl, 
				ironMineLvl, rareEarthMineLvl, fountainLvl, tritiumFabricLvl, 
				ironStorageLvl, rareEarthStorageLvl, waterStorageLvl, tritiumStorageLvl, lastupdate);
	}
	
	/**
	 * Creates a Planet with all given Parameters
	 * @param techtree
	 * @param coordinates
	 * @param name
	 * @param ironValue
	 * @param rareEarthValue
	 * @param waterValue
	 * @param tritiumValue
	 * @param headQuarterLvl
	 * @param universityLvl
	 * @param spacePortLvl
	 * @param ironMineLvl
	 * @param rareEarthMineLvl
	 * @param fountainLvl
	 * @param tritiumFabricLvl
	 * @param ironStorageLvl
	 * @param rareEarthStorageLvl
	 * @param waterStorageLvl
	 * @param tritiumStorageLvl
	 * @param lastupdate
	 */
	private void createThisPlanet(TechTree techtree, Coordinates coordinates, String name, 
			int ironValue, int rareEarthValue, int waterValue, int tritiumValue, 
			int headQuarterLvl, int universityLvl, int spacePortLvl, 
			int ironMineLvl, int rareEarthMineLvl, int fountainLvl, int tritiumFabricLvl, 
			int ironStorageLvl, int rareEarthStorageLvl, int waterStorageLvl, int tritiumStorageLvl,
			Date lastupdate) {
		
		Date date = new Date(lastupdate.getTime());
		// Basic
		this.techtree = techtree;
		this.coordinates = coordinates;
		this.setName(name);
		// Ressources
		Iron iron = new Iron(ironValue);
		RareEarth rareEarth = new RareEarth(rareEarthValue);
		Water water = new Water(waterValue);
		Tritium tritium = new Tritium(tritiumValue);
		ressources.put(iron.getName(), iron);
		ressources.put(rareEarth.getName(), rareEarth);
		ressources.put(water.getName(), water);
		ressources.put(tritium.getName(), tritium);
		
		// Buildings
		HeadQuarter headQuarter = new HeadQuarter(techtree, headQuarterLvl);
		University university = new University(headQuarter, techtree, universityLvl);
		SpacePort spacePort = new SpacePort(headQuarter, techtree, spacePortLvl);
		spacePort.setDate(date);
		buildings.put(headQuarter.getName(), headQuarter);
		buildings.put(university.getName(), university);
		buildings.put(spacePort.getName(), spacePort);
		
		// Res Mining Buildings
		IronMine ironMine = new IronMine(headQuarter, techtree, ironMineLvl, date, this.getIron());
		RareEarthMine rareEarthMine = new RareEarthMine(headQuarter, techtree, rareEarthMineLvl, date, this.getRareEarth());
		Fountain fountain = new Fountain(headQuarter, techtree, fountainLvl, date, this.getWater());
		TritiumFabric tritiumFabric = new TritiumFabric(headQuarter, techtree, tritiumFabricLvl, date, this.getTritium());
		buildings.put(ironMine.getName(), ironMine);
		buildings.put(rareEarthMine.getName(), rareEarthMine);
		buildings.put(fountain.getName(), fountain);
		buildings.put(tritiumFabric.getName(), tritiumFabric);
		
		// Res Storage Buildings
		IronStorage ironStorage 		= new IronStorage(headQuarter, techtree, ironStorageLvl);
		RareEarthStorage rareEarthStorage 	= new RareEarthStorage(headQuarter, techtree, rareEarthStorageLvl);
		WaterStorage waterStorage		= new WaterStorage(headQuarter, techtree, waterStorageLvl);
		TritiumStorage tritiumStorage 	= new TritiumStorage(headQuarter, techtree, tritiumStorageLvl);
		buildings.put(ironStorage.getName(), ironStorage);
		buildings.put(rareEarthStorage.getName(), rareEarthStorage);
		buildings.put(waterStorage.getName(), waterStorage);
		buildings.put(tritiumStorage.getName(), tritiumStorage);
	}
	
	public static void main(String[] args) {
		TechTree techtree = new TechTree();
		Coordinates coordinates = new Coordinates(1, 33, 7);
		techtree.testFill();
		Planet planet1 = new Planet(techtree, coordinates, "TestPlanet", 
				6666, 3333, 9999, 666, 
				3, 2, 1, 
				5, 4, 3, 2, 
				6, 5, 4, 3, new Date());
		
		System.out.println("Planet Data");
		System.out.println("Name: " + planet1.getName());
		System.out.println("Coords: " + planet1.getCoords().asCoords());
		String ressSQL = planet1.asRessourceSQLString();
		String buildingSQL = planet1.asBuildingSQLString();
		planet1.addShip(new Falcon(techtree, 5));
		planet1.addShip(new Cheetah(techtree, 2));
		String fleetSQL = planet1.getFleet().asSQLString();
		System.out.println("Ressourcen: " + ressSQL);
		System.out.println("Buildings: " + buildingSQL);
		System.out.println("Fleet: " + fleetSQL);
		
		System.out.println("");
		System.out.println("Planet 2 Data");
		//Planet planet2 = new Planet(techtree, coordinates, "TestPlanet2", ressSQL, buildingSQL, fleetSQL);		
		//ressSQL = planet2.asRessourceSQLString();
		//buildingSQL = planet2.asBuildingSQLString();
		//fleetSQL = planet1.getFleet().asSQLString();
		System.out.println("Ressourcen: " + ressSQL);
		System.out.println("Buildings: " + buildingSQL);
		System.out.println("Fleet: " + fleetSQL);
	}
	
	/**
	 * Forces the Planet to Update itself to the current time
	 * @param player
	 */
	public void update(Player player) {
		updateRessources();
		updateShipQueue(player);		
	}
	
	/**
	 * Updates all ressources of the Planet
	 */
	public void updateRessources() {
		this.getIronMine().update();
		this.getRareEarthMine().update();
		this.getFountain().update();
		this.getTritiumFabric().update();
	}
	
	/**
	 * Updates the ShipQueue of the Planet
	 * Used to produce Spaceships
	 * @param player the actual Player
	 */
	public void updateShipQueue(Player player) {
		// If no ships are to build, then do nothing
		if (this.getSpacePort().getBuildQueue().size() == 0) { return; }
		
		// Required Objects
		SpacePort 				spaceport = this.getSpacePort();
		ArrayList<ASpaceShip> 	buildqueue = spaceport.getBuildQueue();
		Fleet					fleet = this.getFleet();
		ASpaceShip 				shipToBuild = null;
		
		Date past = spaceport.getTimestamp();
		Date now = new Date();
		int diff = (int)(now.getTime() - past.getTime()) / 1000;
		int timeToBuild = 0;
	
		// Loop to build all ships
		do {
			shipToBuild = null;
			timeToBuild = 0;
			shipToBuild = buildqueue.get(0);
			if (shipToBuild != null) {
				timeToBuild = shipToBuild.getTimeToBuild(spaceport.getLevel());
				if (diff > timeToBuild) {
					fleet.addShip(shipToBuild.cloneMe(1));
					shipToBuild.reduceQuantity(1);
					diff -= timeToBuild;
				} else { break; }
				if (shipToBuild.getQuantity() == 0 && buildqueue.size() > 0) { buildqueue.remove(0); }				
			}			
		} while (diff > 0 && buildqueue.size() > 0);
		spaceport.setTimestamp(new Date(now.getTime() - (diff * 1000)));
		this.setLastupdate(new Date());
		//DBPlanet.updatePlanet(player, this);
	}
	
	public void addShip(ASpaceShip addShip) {
		this.fleet.addShip(addShip);
	}
	
	public void testFill() {
		this.fleet.addShip(new Falcon(this.techtree, 666));
		this.fleet.addShip(new Cheetah(this.techtree, 33));
		this.fleet.addShip(new Yamato(this.techtree, 1));
	}
	
	/** 
	 * If the Planet gets more Ressources
	 * @param ressources
	 */
	public void increaseRessources(ArrayList<ARessource> ressources) {
		for (ARessource r: ressources) {
			this.getRessourceByName(r.getName()).increaseValue(r.getValue());
		}
	}
	
	/**
	 * Checks if the Planet has enough ressources
	 * @param ressources
	 * @return
	 */
	public boolean hasRessources(ArrayList<ARessource> ressources) {
		for (ARessource r: ressources) {
			if (this.getRessourceByName(r.getName()).getValue() < r.getValue()) {return false;};
		}
		return true;
	}
	
	/**
	 * Decreases the Planets Ressources with the given Ressource Array
	 * @param ressources ArrayList<ARessource>
	 */
	public void decreaseRessources(ArrayList<ARessource> ressources) {
		for (ARessource r: ressources) {
			this.getRessourceByName(r.getName()).decreaseValue(r.getValue());
		}
	}

	public Coordinates getCoords() {
		return coordinates;
	}	
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


	public Fleet getFleet() {
		return fleet;
	}

	public ARessource getIron() {
		return ressources.get(new Iron(0).getName());
	}

	public ARessource getRareEarth() {
		return ressources.get(new RareEarth(0).getName());
	}

	public ARessource getWater() {
		return ressources.get(new Water(0).getName());
	}

	public ARessource getTritium() {
		return ressources.get(new Tritium(0).getName());
	}	
	
	public ABuilding getBuildingByName(String name) {
		return this.buildings.get(name);
	}
	
	/**
	 * Not used
	 * @param name
	 * @return
	 */
	public Research getResearchByName(String name) {
		ArrayList<Research> allresearch = ResearchRegister.getWholeResearchList(this.techtree);
		for (Research r : allresearch) {
			if (r.getName().equals(name)) { return r;}
		}
		return null;
	}
	
	/**
	 * Returns all Basic Buildings like HeadQuarter and University
	 * Used for WebInterface
	 * @return ArrayList<ABuilding>
	 */
	public ArrayList<ABuilding> getBasicBuildings() {
		ArrayList<ABuilding> output = new ArrayList<ABuilding>();
		output.add(getHeadQuarter());
		output.add(getUniversity());
		return output;
	}
	
	/**
	 * Returns all War related Buildings
	 * Used for Web Interface
	 * @return ArrayList<ABuilding>
	 */
	public ArrayList<ABuilding> getWarBuildings() {
		ArrayList<ABuilding> output = new ArrayList<ABuilding>();
		output.add(getSpacePort());
		return output;
	}
	
	/**
	 * Returns all Ressource related Buildings for the Web Interface
	 * @return
	 */
	public ArrayList<AResMiningBuilding> getResBuildings() {
		ArrayList<AResMiningBuilding> output = new ArrayList<AResMiningBuilding>();
		output.add(getIronMine());
		output.add(getRareEarthMine());
		output.add(getFountain());
		output.add(getTritiumFabric());
		return output;
	}
	
	public ArrayList<AResStorageBuilding> getResStorageBuildings() {
		ArrayList<AResStorageBuilding> output = new ArrayList<AResStorageBuilding>();
		output.add(getIronStorage());
		output.add(getRareEarthStorage());
		output.add(getWaterStorage());
		output.add(getTritiumStorage());
		return output;
	}

	public HeadQuarter getHeadQuarter() {
		return (HeadQuarter)buildings.get("HeadQuarter");
	}

	public University getUniversity() {
		return (University)buildings.get("University");
	}

	public SpacePort getSpacePort() {
		return (SpacePort)buildings.get("SpacePort");
	}

	public IronMine getIronMine() {
		return (IronMine)buildings.get("IronMine");
	}

	public RareEarthMine getRareEarthMine() {
		return (RareEarthMine)buildings.get("RareEarthMine");
	}

	public Fountain getFountain() {
		return (Fountain)buildings.get("Fountain");
	}

	public TritiumFabric getTritiumFabric() {
		return (TritiumFabric)buildings.get("TritiumFabric");
	}

	public IronStorage getIronStorage() {
		return (IronStorage)buildings.get("IronStorage");
	}

	public RareEarthStorage getRareEarthStorage() {
		return (RareEarthStorage)buildings.get("RareEarthStorage");
	}

	public WaterStorage getWaterStorage() {
		return (WaterStorage)buildings.get("WaterStorage");
	}

	public TritiumStorage getTritiumStorage() {
		return (TritiumStorage)buildings.get("TritiumStorage");
	}

	public boolean isBuilding() {
		return isBuilding.length() > 0;
	}
	
	public String isBuildingName() {
		return isBuilding;
	}

	public void setIsBuilding(String isBuilding) {
		this.isBuilding = isBuilding;
	}

	public boolean isResearching() {
		return isResearching.length() > 0;
	}
	
	public String isResearchingName() {
		return isResearching;
	}

	public void setIsResearching(String isResearching) {
		this.isResearching = isResearching;
	}

	public HashMap<String, ABuilding> getBuildings() {
		return buildings;
	}

	public HashMap<String, ARessource> getRessources() {
		return ressources;
	}	
	
	public ARessource getRessourceByName(String name) {
		return this.ressources.get(name);
	}
	
	/**
	 * Loads the Planet with given DataBase SQL Strings
	 * @param techtree
	 * @param coordinates
	 * @param name
	 * @param ressourceSQL
	 * @param buildingSQL
	 * @param fleetSQL
	 * @param spaceportqueueSQL
	 * @param timestamp
	 */
	private void sqlLoad(TechTree techtree, Coordinates coordinates, String name, String ressourceSQL, String buildingSQL, String fleetSQL, String spaceportqueueSQL, String timestamp) {
		
		Date lastupdate = DateUtils.stampToDate(timestamp);
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		String[] sql = buildingSQL.split( Pattern.quote( ";" ) );
		String[] keyValue = null;
		for (String tech: sql) {
			if (tech.length() < 2) {break;}
			keyValue = tech.split( Pattern.quote( "=" ) );
			data.put(keyValue[0], NumberUtils.stringAsInt(keyValue[1]));
		}
		sql = ressourceSQL.split( Pattern.quote( ";" ) );
		keyValue = null;
		for (String tech: sql) {
			if (tech.length() < 2) {break;}
			keyValue = tech.split( Pattern.quote( "=" ) );
			data.put(keyValue[0], NumberUtils.stringAsInt(keyValue[1]));
		}
		
		this.createThisPlanet(techtree, coordinates, name, 
							data.get("Iron"), data.get("RareEarth"), data.get("Water"), data.get("Tritium"), 
							data.get("HeadQuarter"), data.get("University"), data.get("SpacePort"), 
							data.get("IronMine"), data.get("RareEarthMine"), data.get("Fountain"), data.get("TritiumFabric"), 
							data.get("IronStorage"), data.get("RareEarthStorage"), data.get("WaterStorage"), data.get("TritiumStorage"), lastupdate);

		this.getSpacePort().setBuildQueue(ShipFabric.createArrayFromSQL(techtree, spaceportqueueSQL));
		
		Date date = lastupdate;
		this.getIronMine().setDate(date);
		this.getRareEarthMine().setDate(date);
		this.getFountain().setDate(date);
		this.getTritiumFabric().setDate(date);
		
		
		this.fleet = new Fleet(techtree, fleetSQL);
	}
	
	/**
	 * Returns all Buildings as String with their Level
	 * Example: "HeadQuarter=3; University=2" etc.
	 * Used for DataBase Storage
	 * @return String all Buildings
	 */
	public String asBuildingSQLString() {
		String output = "";
		for (Entry<String, ABuilding> entry : buildings.entrySet()) {
			output += entry.getKey() + "=" + entry.getValue().getLevel() + ";";
		}
		return output;
	}
	
	/**
	 * Returns all Ressources as SQL Strings
	 * Used for Storage in DataBase
	 * @return
	 */
	public String asRessourceSQLString() {
		String output = "";
		// TODO Update Ressources
		for (Entry<String, ARessource> entry : ressources.entrySet()) {
			output += entry.getKey() + "=" + entry.getValue().getValue() + ";";
		}
		return output;
	}
	
	/**
	 * Converts the Fleet on this Planet to a SQL String
	 * Used to Store Fleet in DataBase
	 * @return
	 * @see fleet
	 */
	public String asFleetSQLString() {
		return this.fleet.asSQLString();
	}
	
	/**
	 * Converts the SpacePortQueue to a String that gets stored in the DataBase
	 * @return
	 */
	public String asSpacePortQueueSQLString() {
		String output = "";
		for (ASpaceShip ship : this.getSpacePort().getBuildQueue()) {
			output += ship.getName() + "=" + ship.getQuantity() + ";";
		}
		return output;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}	

}
