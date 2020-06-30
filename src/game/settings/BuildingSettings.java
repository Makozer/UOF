package game.settings;

import java.util.*;
import game.ressource.*;
import game.utils.*;



/**
 * This Class stores all the Settings for the Buildings.
 * 
 * @author Martin
 *
 */
public class BuildingSettings {
	
	public static final AMath					BUILDING_MOD = new Polynomial(1, 0, 0);
	public static final AMath					BUILDING_RES_MOD = new Polynomial(0.025, 0, 0);
	public static final ArrayList<ARessource> 	BUILDING_RES_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(1000), 
													new RareEarth(500),
													new Water(1000)));
	public static final ArrayList<ARessource> 	BUILDING_STORAGE_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(3000), 
													new RareEarth(1000),
													new Water(2000)));
	
	public static final AMath					BUILDING_STORAGE_MOD = new Polynomial(1, 0, 0);

	// Basic Buildings
	public static final AMath 					HEADQUARTER_LEVELMOD = BUILDING_MOD;
	public static final ArrayList<ARessource> 	HEADQUARTER_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(600), 
													new RareEarth(300),
													new Water(150)));
	
	public static final AMath 					UNIVERSITY_LEVELMOD = BUILDING_MOD;
	public static final ArrayList<ARessource> 	UNIVERSITY_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(300), 
													new RareEarth(150),
													new Water(300)));
	
	public static final AMath 					SPACEPORT_LEVELMOD = BUILDING_MOD;
	public static final ArrayList<ARessource> 	SPACEPORT_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(1500), 
													new RareEarth(4500),
													new Water(1000),
													new Tritium(50)));
	
	
	// Ressource Mining Buildings
	public static final AMath 					IRONMINE_LEVELMOD = new Polynomial(0.0031, 0, 3, 15, 10);
	public static final ArrayList<ARessource> 	IRONMINE_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(300), 
													new RareEarth(50),
													new Water(100)));
	public static final AMath 					RAREEARTHMINE_LEVELMOD = new Polynomial(0.002, 0, 2, 10, 10);
	public static final ArrayList<ARessource> 	RAREEARTHMINE_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(150), 
													new RareEarth(50),
													new Water(100)));
	public static final AMath 					FOUNTAIN_LEVELMOD = new Polynomial(0.0033, 0, 3, 13, 10);
	public static final ArrayList<ARessource> 	FOUNTAIN_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(100), 
													new RareEarth(10)));
	public static final AMath 					TRITIUMFABRIC_LEVELMOD = new Polynomial(0.0011, 0, 1, 10, 0);
	public static final ArrayList<ARessource> 	TRITIUMFABRIC_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(666), 
													new RareEarth(1500),
													new Water(1000)));
	
	// Ressource Storage Buildings
	public static final AMath 					IRONSTORAGE_LEVELMOD = BUILDING_STORAGE_MOD;
	public static final ArrayList<ARessource> 	IRONSTORAGE_COSTS = BUILDING_STORAGE_COSTS;
	public static final AMath 					RAREEARTHSTORAGE_LEVELMOD = BUILDING_STORAGE_MOD;
	public static final ArrayList<ARessource> 	RAREEARTHSTORAGE_COSTS = BUILDING_STORAGE_COSTS;
	public static final AMath 					WATERSTORAGE_LEVELMOD = BUILDING_STORAGE_MOD;
	public static final ArrayList<ARessource> 	WATERSTORAGE_COSTS = BUILDING_STORAGE_COSTS;
	public static final AMath 					TRITIUMSTORAGE_LEVELMOD = BUILDING_STORAGE_MOD;
	public static final ArrayList<ARessource> 	TRITIUMSTORAGE_COSTS = BUILDING_STORAGE_COSTS;
	
	
	
	// Descriptions
	public static final String					HEADQUARTER_DESCRIPTION = "This is your Command Center on this Planet. Increasing this fastens up all other Buildingspeeds.";
	public static final String					UNIVERSITY_DESCRIPTION = "This is your University where you are able to research all kind of evil Stuff.";
	public static final String					SPACEPORT_DESCRIPTION = "This is your SpacePort where you are able to build SpaceShips. Increasing this results in faster Buildspeed.";
	
	public static final String					RESSDESC = "This Building generates Ressource. A higher Building generates more Ressource.";
	public static final String					IRONMINE_DESCRIPTION = RESSDESC;
	public static final String					RAREEARTHMINE_DESCRIPTION = RESSDESC;
	public static final String					FOUNTAIN_DESCRIPTION = RESSDESC;
	public static final String					TRITIUMFABRIC_DESCRIPTION = RESSDESC;
	
	public static final String					RESSTODESC = "This Building stores the Ressource. Increasing it results in higher Storage Capacity and SaveProof Capacity.";
	public static final String					IRONSTORAGE_DESCRIPTION = RESSTODESC;
	public static final String					RAREEARTHSTORAGE_DESCRIPTION = RESSTODESC;
	public static final String					WATERSTORAGE_DESCRIPTION = RESSTODESC;
	public static final String					TRITIUMSTORAGE_DESCRIPTION = RESSTODESC;

}
