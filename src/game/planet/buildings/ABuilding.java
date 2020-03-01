package game.planet.buildings;

import java.util.*;

import game.research.*;
import game.ressource.*;
import game.utils.*;

public abstract class ABuilding {
	
	private TechTree 	techtree = null;	
	protected AMath		levelMod = null;
	
	public ABuilding(TechTree techtree) {
		this.techtree = techtree;
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}	

}
