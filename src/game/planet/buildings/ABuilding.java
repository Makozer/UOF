package game.planet.buildings;

import java.util.*;
import game.planet.ressource.*;
import game.utils.*;

public abstract class ABuilding {
	
	protected String name = "";
	protected int level = 0;
	protected HashMap<ARessource, AMath> cost = new HashMap<ARessource, AMath>();
	
	public ABuilding(int level) {
		this.level = level;
	}
	
	
	

}
