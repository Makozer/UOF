package game.settings;

import java.util.*;
import game.ressource.*;
import game.utils.*;

public class BuildingSettings {
	
	public static final AMath					BUILDING_MOD = new Polynomial(1, 0, 0);
	public static final AMath					BUILDING_RES_MOD = new Polynomial(0.025, 0, 0);
	public static final ArrayList<ARessource> 	BUILDING_RES_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(1000), 
													new RareEarth(500),
													new Water(1000)));
	public static final ArrayList<ARessource> 	BUILDING_STORAGE_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(10000), 
													new RareEarth(5000),
													new Water(10000)));
	
	public static final AMath					BUILDING_STORAGE_MOD = new Polynomial(1, 0, 0);

	// Basic Buildings
	public static final AMath 					HEADQUARTER_LEVELMOD = BUILDING_MOD;
	public static final ArrayList<ARessource> 	HEADQUARTER_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(10000), 
													new RareEarth(10000),
													new Water(10000)));
	public static final AMath 					UNIVERSITY_LEVELMOD = BUILDING_MOD;
	public static final ArrayList<ARessource> 	UNIVERSITY_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(1000), 
													new RareEarth(5000),
													new Water(2500)));
	public static final AMath 					SPACEPORT_LEVELMOD = BUILDING_MOD;
	public static final ArrayList<ARessource> 	SPACEPORT_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(10000), 
													new RareEarth(10000),
													new Water(5000)));
	
	
	// Ressource Mining Buildings
	public static final AMath 					IRONMINE_LEVELMOD = BUILDING_RES_MOD;
	public static final ArrayList<ARessource> 	IRONMINE_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(500), 
													new RareEarth(500),
													new Water(100)));
	public static final AMath 					RAREEARTHMINE_LEVELMOD = BUILDING_RES_MOD;
	public static final ArrayList<ARessource> 	RAREEARTHMINE_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(500), 
													new RareEarth(500),
													new Water(100)));
	public static final AMath 					FOUNTAIN_LEVELMOD = BUILDING_RES_MOD;
	public static final ArrayList<ARessource> 	FOUNTAIN_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(500), 
													new RareEarth(100),
													new Water(100)));
	public static final AMath 					TRITIUMFABRIC_LEVELMOD = BUILDING_RES_MOD;
	public static final ArrayList<ARessource> 	TRITIUMFABRIC_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(10000), 
													new RareEarth(10000),
													new Water(10000)));
	
	// Ressource Storage Buildings
	public static final AMath 					IRONSTORAGE_LEVELMOD = BUILDING_STORAGE_MOD;
	public static final ArrayList<ARessource> 	IRONSTORAGE_COSTS = BUILDING_STORAGE_COSTS;
	public static final AMath 					RAREEARTHSTORAGE_LEVELMOD = BUILDING_STORAGE_MOD;
	public static final ArrayList<ARessource> 	RAREEARTHSTORAGE_COSTS = BUILDING_STORAGE_COSTS;
	public static final AMath 					WATERSTORAGE_LEVELMOD = BUILDING_STORAGE_MOD;
	public static final ArrayList<ARessource> 	WATERSTORAGE_COSTS = BUILDING_STORAGE_COSTS;
	public static final AMath 					TRITIUMSTORAGE_LEVELMOD = BUILDING_STORAGE_MOD;
	public static final ArrayList<ARessource> 	TRITIUMSTORAGE_COSTS = BUILDING_STORAGE_COSTS;
	

}
