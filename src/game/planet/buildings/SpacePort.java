package game.planet.buildings;

import static game.settings.BuildingSettings.*;
import game.research.TechTree;

public class SpacePort extends ABuilding {

	public SpacePort(TechTree techtree, int level) {
		super(techtree, level);
		this.levelMod = SPACEPORT_LEVELMOD;
		this.costs = SPACEPORT_COSTS;
	}

}
