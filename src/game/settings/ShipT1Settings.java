package game.settings;

import java.util.*;
import game.ressource.*;
import game.utils.*;

/**
 * Stores the Settings for all T1 Ships
 * @author Martin
 *
 */
public class ShipT1Settings {
	
	// Falcon
	public static final int 					FALCON_ATTACK = 3;				
	public static final int 					FALCON_DEFENSE = 1;							
	public static final int 					FALCON_SPEED = 3;				
	public static final int 					FALCON_CAPACITY = 10;	
	public static final AMath 					FALCON_LEVELMOD = new Polynomial(0.25, 0, 0);
	public static final int 					FALCON_BUILDSECONDS = 3600;
	public static final ArrayList<ARessource> 	FALCON_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(200), 
													new Water(200)));


}
