package game.settings;

import java.util.*;
import game.ressource.*;
import game.utils.*;

/**
 * Stores the Settings for all T3 Ships
 * @author Martin
 *
 */
public class ShipT3Settings {
	
	// YAMATO
	public static final int 					YAMATO_ATTACK = 9999;				
	public static final int 					YAMATO_DEFENSE = 9999;							
	public static final int 					YAMATO_SPEED = 9999;				
	public static final int 					YAMATO_CAPACITY = 9999;	
	public static final AMath 					YAMATO_LEVELMOD = new Polynomial(1, 1);
	public static final int 					YAMATO_BUILDSECONDS = 3600;
	public static final ArrayList<ARessource> 	YAMATO_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(9999), 
													new RareEarth(9999),
													new Water(9999)));


}
