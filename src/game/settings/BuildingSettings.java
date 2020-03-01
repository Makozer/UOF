package game.settings;

import game.utils.*;

public class BuildingSettings {
	
	public static final AMath		BUILDING_MOD = new Polynomial(1, 0, 1);

	public static final AMath 		HEADQUARTER_LEVELMOD = BUILDING_MOD;
	public static final AMath 		UNIVERSITY_LEVELMOD = BUILDING_MOD;
	public static final AMath 		IRONMINE_LEVELMOD = BUILDING_MOD;
	public static final AMath 		SHIPYARD_LEVELMOD = BUILDING_MOD;

}
