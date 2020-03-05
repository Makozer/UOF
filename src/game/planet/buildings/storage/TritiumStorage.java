package game.planet.buildings.storage;

import game.research.TechTree;
import static game.settings.BuildingSettings.*;

public class TritiumStorage extends AResStorageBuilding {

	public TritiumStorage(TechTree techtree, int level) {
		super(techtree, level);
		this.levelMod = TRITIUMSTORAGE_LEVELMOD;
		this.costs = TRITIUMSTORAGE_COSTS;
		this.description = TRITIUMSTORAGE_DESCRIPTION;
	}
	
	@Override
	public int getMaxCapacity() {
		return (int)this.levelMod.getValue(this.level) * 10000;
	}
	
	@Override
	public int getSaveCapacity() {
		return (int)this.levelMod.getValue(this.level) * 1000;
	}

}
