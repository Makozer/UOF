package game.planet.buildings;

import game.research.*;
import static game.settings.BuildingSettings.*;

public class HeadQuarter extends ABuilding {
	
	public HeadQuarter(TechTree techtree, int level) {
		super(techtree, level);
		this.levelMod = HEADQUARTER_LEVELMOD;
		this.costs = HEADQUARTER_COSTS;
		this.description = HEADQUARTER_DESCRIPTION;
	}

}
