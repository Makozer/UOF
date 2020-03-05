package game.planet.buildings;

import game.research.TechTree;
import static game.settings.BuildingSettings.*;

public class University extends ABuilding {

	public University(TechTree techtree, int level) {
		super(techtree, level);
		this.levelMod = UNIVERSITY_LEVELMOD;
		this.costs = UNIVERSITY_COSTS;
		this.description = UNIVERSITY_DESCRIPTION;
	}

}
