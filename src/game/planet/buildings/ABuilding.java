package game.planet.buildings;

import java.util.ArrayList;

import game.research.*;
import game.ressource.ARessource;
import game.utils.*;

public abstract class ABuilding {
	
	protected TechTree 					techtree = null;
	protected int						level = 0;
	protected AMath						levelMod = null;
	protected ArrayList<ARessource> 	costs = null;
	
	public ABuilding(TechTree techtree, int level) {
		this.techtree = techtree;
		this.level = level;
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}	
	
	public int getLevel() {
		return this.level;
	}
	
	public double getLevelModValue() {
		return levelMod.getValue(this.level);
	}

	public ArrayList<ARessource> getCosts() {
		return costs;
	}	

}
