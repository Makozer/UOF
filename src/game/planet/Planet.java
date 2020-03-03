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
	// Ressources
	private ARessource				iron = null;
	private ARessource				rareEarth = null;
	private ARessource				water = null;
	private ARessource				tritium = null;
	
	// Buildings
	private HeadQuarter 			headQuarter = null;
	private University				university = null;
	private SpacePort				spacePort = null;
	// Mining Buildings
	private IronMine				ironMine = null;
	private RareEarthMine			rareEarthMine = null;
	private Fountain				fountain = null;
	private TritiumFabric			tritiumFabric = null;
	// Storage Buildings
	private IronStorage				ironStorage = null;
	private RareEarthStorage		rareEarthStorage = null;
	private WaterStorage			waterStorage = null;
	private TritiumStorage			tritiumStorage = null;	
	
	// Fleet that idles on the Planet
	private Fleet					fleet = new Fleet();
	
	public Planet(TechTree techtree, Coordinates coordinates, String name, int ironValue, int rareEarthValue, int waterValue, int tritiumValue, int headQuarterLvl, int universityLvl, int spacePortLvl, int ironMineLvl, int rareEarthMineLvl, int fountainLvl, int tritiumFabricLvl, int ironStorageLvl, int rareEarthStorageLvl, int waterStorageLvl, int tritiumStorageLvl) {
		Date date = new Date(); // get the actual Date
		// Basic
		this.techtree = techtree;
		this.coordinates = coordinates;
		this.setName(name);
		// Ressources
		this.iron = new Iron(ironValue);
		this.rareEarth = new RareEarth(rareEarthValue);
		this.water = new Water(waterValue);
		this.tritium = new Tritium(tritiumValue);
		// Buildings
		this.headQuarter = new HeadQuarter(techtree, headQuarterLvl);
		this.university = new University(techtree, universityLvl);
		this.spacePort = new SpacePort(techtree, spacePortLvl);
		// Res Mining Buildings
		this.ironMine = new IronMine(techtree, ironMineLvl, date, this.getIron());
		this.rareEarthMine = new RareEarthMine(techtree, rareEarthMineLvl, date, this.getRareEarth());
		this.fountain = new Fountain(techtree, fountainLvl, date, this.getWater());
		this.tritiumFabric = new TritiumFabric(techtree, tritiumFabricLvl, date, this.getTritium());
		// Res Storage Buildings
		this.ironStorage 		= new IronStorage(techtree, ironStorageLvl);
		this.rareEarthStorage 	= new RareEarthStorage(techtree, rareEarthStorageLvl);
		this.waterStorage		= new WaterStorage(techtree, waterStorageLvl);
		this.tritiumStorage 	= new TritiumStorage(techtree, tritiumStorageLvl);
		
	}
	
	public static void main(String[] args) {
		
	}
	
	public void testFill() {
		this.fleet.addShips(new Falcon(this.techtree, 666));
		this.fleet.addShips(new Cheetah(this.techtree, 33));
		this.fleet.addShips(new Yamato(this.techtree, 1));
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
		return iron;
	}

	public ARessource getRareEarth() {
		return rareEarth;
	}

	public ARessource getWater() {
		return water;
	}

	public ARessource getTritium() {
		return tritium;
	}	

	public HeadQuarter getHeadQuarter() {
		return headQuarter;
	}

	public University getUniversity() {
		return university;
	}

	public SpacePort getSpacePort() {
		return spacePort;
	}

	public IronMine getIronMine() {
		return ironMine;
	}

	public RareEarthMine getRareEarthMine() {
		return rareEarthMine;
	}

	public Fountain getFountain() {
		return fountain;
	}

	public TritiumFabric getTritiumFabric() {
		return tritiumFabric;
	}

	public IronStorage getIronStorage() {
		return ironStorage;
	}

	public RareEarthStorage getRareEarthStorage() {
		return rareEarthStorage;
	}

	public WaterStorage getWaterStorage() {
		return waterStorage;
	}

	public TritiumStorage getTritiumStorage() {
		return tritiumStorage;
	}
	
	
	
	

}
