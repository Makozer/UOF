package game.planet.buildings.storage;

import game.research.TechTree;
import static game.settings.BuildingSettings.*;

import game.planet.buildings.HeadQuarter;

public class RareEarthStorage extends AResStorageBuilding {

	public RareEarthStorage(HeadQuarter hq, TechTree techtree, int level) {
		super(hq, techtree, level);
		this.levelMod = RAREEARTHSTORAGE_LEVELMOD;
		this.costs = RAREEARTHSTORAGE_COSTS;
		this.description = RAREEARTHMINE_DESCRIPTION;
	}

}
