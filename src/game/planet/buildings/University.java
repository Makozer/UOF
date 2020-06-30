package game.planet.buildings;

import game.research.TechTree;
import static game.settings.BuildingSettings.*;

/**
 * The University of a Planet, higher level = faster research
 * @author Martin
 *
 */
public class University extends ABuilding {

	public University(HeadQuarter hq, TechTree techtree, int level) {
		super(hq, techtree, level);
		this.levelMod = UNIVERSITY_LEVELMOD;
		this.costs = UNIVERSITY_COSTS;
		this.description = UNIVERSITY_DESCRIPTION;
	}

}
