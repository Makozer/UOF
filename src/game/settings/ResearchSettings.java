package game.settings;

import java.util.*;
import game.ressource.*;
import game.utils.*;

public class ResearchSettings {
	
	// LaserPointer
	public static final AMath 					LASERPOINTER_LEVELMOD = new Polynomial(0.25, 0, 0);
	public static final ArrayList<ARessource> 	LASERPOINTER_COSTS = new ArrayList<ARessource>(Arrays.asList(
													new Iron(200), 
													new Water(200)));


}
