package game.planet.buildings.storage;

import game.research.TechTree;
import static game.settings.BuildingSettings.*;

public class WaterStorage extends AResStorageBuilding {

	public WaterStorage(TechTree techtree, int level) {
		super(techtree, level);
		this.levelMod = WATERSTORAGE_LEVELMOD;
		this.costs = WATERSTORAGE_COSTS;
		this.description = WATERSTORAGE_DESCRIPTION;
	}

}
