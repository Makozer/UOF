package game.planet;

import java.util.*;

import game.fleet.*;
import game.fleet.tier1.*;
import game.fleet.tier2.*;
import game.fleet.tier3.*;
import game.planet.buildings.*;
import game.planet.buildings.mining.*;
import game.planet.buildings.storage.*;
import game.research.*;
import game.ressource.*;

public class Planet {
	
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
	
	public Planet(TechTree techtree, Coordinates coordinates, String name, 
						int ironValue, int rareEarthValue, int waterValue, int tritiumValue, 
						int headQuarterLvl, int universityLvl, int spacePortLvl, 
						int ironMineLvl, int rareEarthMineLvl, int fountainLvl, int tritiumFabricLvl, 
						int ironStorageLvl, int rareEarthStorageLvl, int waterStorageLvl, int tritiumStorageLvl) {
		
		Date date = new Date(); // get the actual Date
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
		University university = new University(techtree, universityLvl);
		SpacePort spacePort = new SpacePort(techtree, spacePortLvl);
		buildings.put(headQuarter.getName(), headQuarter);
		buildings.put(university.getName(), university);
		buildings.put(spacePort.getName(), spacePort);
		
		// Res Mining Buildings
		IronMine ironMine = new IronMine(techtree, ironMineLvl, date, this.getIron());
		RareEarthMine rareEarthMine = new RareEarthMine(techtree, rareEarthMineLvl, date, this.getRareEarth());
		Fountain fountain = new Fountain(techtree, fountainLvl, date, this.getWater());
		TritiumFabric tritiumFabric = new TritiumFabric(techtree, tritiumFabricLvl, date, this.getTritium());
		buildings.put(ironMine.getName(), ironMine);
		buildings.put(rareEarthMine.getName(), rareEarthMine);
		buildings.put(fountain.getName(), fountain);
		buildings.put(tritiumFabric.getName(), tritiumFabric);
		// Res Storage Buildings
		IronStorage ironStorage 		= new IronStorage(techtree, ironStorageLvl);
		RareEarthStorage rareEarthStorage 	= new RareEarthStorage(techtree, rareEarthStorageLvl);
		WaterStorage waterStorage		= new WaterStorage(techtree, waterStorageLvl);
		TritiumStorage tritiumStorage 	= new TritiumStorage(techtree, tritiumStorageLvl);
		buildings.put(ironStorage.getName(), ironStorage);
		buildings.put(rareEarthStorage.getName(), rareEarthStorage);
		buildings.put(waterStorage.getName(), waterStorage);
		buildings.put(tritiumStorage.getName(), tritiumStorage);
		
	}
	
	public static void main(String[] args) {
		
	}
	
	public void update() {
		updateShipQueue();		
	}
	
	public void updateShipQueue() {
		SpacePort spaceport = this.getSpacePort();
		ArrayList<ASpaceShip> buildqueue = spaceport.getBuildQueue();
		
		Date past = spaceport.getTimestamp();
		Date now = new Date();
		long diff = now.getTime() - past.getTime();
		
		do {
			
		} while (diff > 0);
	}
	
	public void addShip(ASpaceShip addShip) {
		ArrayList<ASpaceShip> fleet = this.fleet.getFleet();
		ASpaceShip thisShip = null;
		boolean found = false;

		for (int i = 0; i < fleet.size(); i++) {
			thisShip = fleet.get(i);
			if (thisShip.getName() == addShip.getName()) {
				thisShip.addQuantity(addShip.getQuantity());
				found = true;
				break;
			}
			if (i == fleet.size() - 1 && found == false) {
				fleet.add(addShip);
			}
		}
	}
	
	public void testFill() {
		this.fleet.addShips(new Falcon(this.techtree, 666));
		this.fleet.addShips(new Cheetah(this.techtree, 33));
		this.fleet.addShips(new Yamato(this.techtree, 1));
	}
	
	public void increaseRessources(ArrayList<ARessource> ressources) {
		for (ARessource r: ressources) {
			this.getRessourceByName(r.getName()).increaseValue(r.getValue());
		}
	}
	
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
	
	public ArrayList<ABuilding> getBasicBuildings() {
		ArrayList<ABuilding> output = new ArrayList<ABuilding>();
		output.add(getHeadQuarter());
		output.add(getUniversity());
		return output;
	}
	
	public ArrayList<ABuilding> getWarBuildings() {
		ArrayList<ABuilding> output = new ArrayList<ABuilding>();
		output.add(getSpacePort());
		return output;
	}
	
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

}
