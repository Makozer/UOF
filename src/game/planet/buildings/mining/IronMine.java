package game.planet.buildings.mining;

import java.util.Date;

import game.planet.buildings.HeadQuarter;
import game.research.TechTree;
import game.ressource.*;
import static game.settings.BuildingSettings.*;

/**
 * The IronMine of a Planet to generate Iron
 * @author Martin
 *
 */
public class IronMine extends AResMiningBuilding {
	
	public IronMine(HeadQuarter hq, TechTree techtree, int level, Date date, ARessource ressource) {
		super(hq, techtree, level, date, ressource);
		this.levelMod = IRONMINE_LEVELMOD;
		this.costs = IRONMINE_COSTS;
		this.description = IRONMINE_DESCRIPTION;
	}

}
