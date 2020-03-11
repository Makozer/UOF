package game.settings;

import java.util.*;
import game.ressource.*;
import game.utils.*;

public class ShipT2Settings {
	
	// CHEETAH
	public static final int 					CHEETAH_ATTACK = 30;				
	public static final int 					CHEETAH_DEFENSE = 10;							
	public static final int 					CHEETAH_SPEED = 30;				
	public static final int 					CHEETAH_CAPACITY = 10;	
	public static final AMath 					CHEETAH_LEVELMOD = new Polynomial(1, 1);
	public static final int 					CHEETAH_BUILDSECONDS = 3600;
	public static final ArrayList<ARessource> 	CHEETAH_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(2000), 
													new RareEarth(2000),
													new Water(2000)));


}
