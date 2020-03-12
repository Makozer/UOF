package game.planet.buildings.storage;

import game.research.TechTree;
import static game.settings.BuildingSettings.*;

import game.planet.buildings.HeadQuarter;

public class IronStorage extends AResStorageBuilding {

	public IronStorage(HeadQuarter hq, TechTree techtree, int level) {
		super(hq, techtree, level);
		this.levelMod = IRONSTORAGE_LEVELMOD;
		this.costs = IRONSTORAGE_COSTS;
		this.description = IRONMINE_DESCRIPTION;
	}

}
