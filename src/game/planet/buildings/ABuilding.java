package game.planet.buildings;

import game.research.*;
import game.utils.*;

public abstract class ABuilding {
	
	protected TechTree 	techtree = null;
	protected int		level = 0;
	protected AMath		levelMod = null;
	
	public ABuilding() {
		
	}
	
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

	public AMath getLevelMod() {
		return levelMod;
	}
	
	public double getLevelModValue() {
		return levelMod.getValue(this.level);
	}

}
