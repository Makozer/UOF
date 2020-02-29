package game.settings;

import java.util.*;
import game.ressource.*;
import game.utils.*;

public class ShipT3Settings {
	
	// YAMATO
	public static final int 					YAMATO_ATTACK = 9999;				
	public static final int 					YAMATO_DEFENSE = 9999;							
	public static final int 					YAMATO_SPEED = 9999;				
	public static final int 					YAMATO_CAPACITY = 9999;	
	public static final AMath 					YAMATO_LEVELMOD = new Polynomial(1, 1);
	public static final ArrayList<ARessource> 	YAMATO_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(200), 
													new Water(200)));


}
