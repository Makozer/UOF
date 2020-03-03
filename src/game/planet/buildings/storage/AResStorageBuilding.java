package game.planet.buildings.storage;

import game.planet.buildings.ABuilding;
import game.research.*;
import game.utils.NumberUtils;

public abstract class AResStorageBuilding extends ABuilding {
	
	public AResStorageBuilding(TechTree techtree, int level) {
		super(techtree, level);
	}
	
	public int getMaxCapacity() {
		return (int)this.levelMod.getValue(this.level) * 100000;
	}
	
	public String getMaxCapacityAsString() {
		return NumberUtils.shortNumber(this.getMaxCapacity());
	}
	
	public int getSaveCapacity() {
		return (int)this.levelMod.getValue(this.level) * 10000;
	}
	
	public String getSaveCapacityAsString() {
		return NumberUtils.shortNumber(this.getSaveCapacity());
	}

}
