package game.settings;

import game.utils.*;

public class BuildingSettings {
	
	public static final AMath		BUILDING_MOD = new Polynomial(1, 0, 1);
	public static final AMath		BUILDING_RES_MOD = new Polynomial(1, 0, 1);
	public static final AMath		BUILDING_RES_STORAGE_MOD = new Polynomial(1, 0, 1);

	// Basic Buildings
	public static final AMath 		HEADQUARTER_LEVELMOD = BUILDING_MOD;
	public static final AMath 		UNIVERSITY_LEVELMOD = BUILDING_MOD;
	public static final AMath 		SPACEPORT_LEVELMOD = BUILDING_MOD;
	
	// Ressource Mining Buildings
	public static final AMath 		IRONMINE_LEVELMOD = BUILDING_RES_MOD;
	public static final AMath 		RAREEARTHMINE_LEVELMOD = BUILDING_RES_MOD;
	public static final AMath 		FOUNTAIN_LEVELMOD = BUILDING_RES_MOD;
	public static final AMath 		TRITIUMFABRIC_LEVELMOD = BUILDING_RES_MOD;
	
	// Ressource Storage Buildings
	public static final AMath 		IRONSTORAGE_LEVELMOD = BUILDING_RES_STORAGE_MOD;
	public static final AMath 		RAREEARTHSTORAGE_LEVELMOD = BUILDING_RES_STORAGE_MOD;
	public static final AMath 		WATERSTORAGE_LEVELMOD = BUILDING_RES_STORAGE_MOD;
	public static final AMath 		TRITIUMSTORAGE_LEVELMOD = BUILDING_RES_STORAGE_MOD;
	

}
