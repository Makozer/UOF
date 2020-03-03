package game.planet.buildings.storage;

import game.research.TechTree;
import static game.settings.BuildingSettings.*;

public class IronStorage extends AResStorageBuilding {

	public IronStorage(TechTree techtree, int level) {
		super(techtree, level);
		this.levelMod = IRONSTORAGE_LEVELMOD;
		this.costs = IRONSTORAGE_COSTS;
	}

}
