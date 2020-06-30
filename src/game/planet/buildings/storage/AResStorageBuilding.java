package game.planet.buildings.storage;

import game.planet.buildings.ABuilding;
import game.planet.buildings.HeadQuarter;
import game.research.*;
import game.utils.NumberUtils;

/**
 * Abstract Class for all Storage Buildings
 * @author Martin
 *
 */
public abstract class AResStorageBuilding extends ABuilding {
	
	public AResStorageBuilding(HeadQuarter hq, TechTree techtree, int level) {
		super(hq, techtree, level);
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
