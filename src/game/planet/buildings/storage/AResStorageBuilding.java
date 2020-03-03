package game.planet.buildings.storage;

import game.planet.buildings.ABuilding;
import game.research.*;

public abstract class AResStorageBuilding extends ABuilding {
	
	public AResStorageBuilding(TechTree techtree, int level) {
		super(techtree, level);
	}
	
	public int getMaxCapacity() {
		return (int)this.levelMod.getValue(this.level) * 100000;
	}
	
	public int getSaveCapacity() {
		return (int)this.levelMod.getValue(this.level) * 10000;
	}

}
