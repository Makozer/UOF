package game.planet.buildings.storage;

import game.research.TechTree;
import static game.settings.BuildingSettings.*;

public class RareEarthStorage extends AResStorageBuilding {

	public RareEarthStorage(TechTree techtree, int level) {
		super(techtree, level);
		this.levelMod = RAREEARTHSTORAGE_LEVELMOD;
		this.costs = RAREEARTHSTORAGE_COSTS;
	}

}
