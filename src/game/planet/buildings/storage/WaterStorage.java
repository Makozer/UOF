package game.planet.buildings.storage;

import game.research.TechTree;
import static game.settings.BuildingSettings.*;

import game.planet.buildings.HeadQuarter;

public class WaterStorage extends AResStorageBuilding {

	public WaterStorage(HeadQuarter hq, TechTree techtree, int level) {
		super(hq, techtree, level);
		this.levelMod = WATERSTORAGE_LEVELMOD;
		this.costs = WATERSTORAGE_COSTS;
		this.description = WATERSTORAGE_DESCRIPTION;
	}

}
