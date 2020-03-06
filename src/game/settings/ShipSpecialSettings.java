package game.settings;

import java.util.*;
import game.ressource.*;
import game.utils.*;

public class ShipSpecialSettings {
	
	// SPYDRONE
	public static final int 					SPYDRONE_ATTACK = 3;				
	public static final int 					SPYDRONE_DEFENSE = 1;							
	public static final int 					SPYDRONE_SPEED = 3;				
	public static final int 					SPYDRONE_CAPACITY = 10;	
	public static final AMath 					SPYDRONE_LEVELMOD = new Polynomial(1, 1);
	public static final ArrayList<ARessource> 	SPYDRONE_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(200), 
													new Water(200)));


}
