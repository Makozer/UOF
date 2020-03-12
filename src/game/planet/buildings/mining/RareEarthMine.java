package game.planet.buildings.mining;

import java.util.Date;

import game.planet.buildings.HeadQuarter;
import game.research.TechTree;
import game.ressource.ARessource;
import static game.settings.BuildingSettings.*;

public class RareEarthMine extends AResMiningBuilding {

	public RareEarthMine(HeadQuarter hq, TechTree techtree, int level, Date date, ARessource ressource) {
		super(hq, techtree, level, date, ressource);
		this.levelMod = RAREEARTHMINE_LEVELMOD;
		this.costs = RAREEARTHMINE_COSTS;
		this.description = RAREEARTHMINE_DESCRIPTION;
	}

}
