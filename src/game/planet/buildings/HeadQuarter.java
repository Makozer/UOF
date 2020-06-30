package game.planet.buildings;

import game.research.*;
import static game.settings.BuildingSettings.*;

/**
 * The HeadQuarter of a Planet, higher level = faster building of Buildings
 * @author Martin
 *
 */
public class HeadQuarter extends ABuilding {
	
	public HeadQuarter(TechTree techtree, int level) {
		super(null, techtree, level);
		this.headQuarter = this;
		this.levelMod = HEADQUARTER_LEVELMOD;
		this.costs = HEADQUARTER_COSTS;
		this.description = HEADQUARTER_DESCRIPTION;
	}

}
