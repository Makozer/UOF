package game.planet.buildings.mining;

import java.util.Date;
import game.research.TechTree;
import game.ressource.*;
import static game.settings.BuildingSettings.*;

public class IronMine extends AResMiningBuilding {
	
	public IronMine(TechTree techtree, int level, Date date, ARessource ressource) {
		super(techtree, level, date, ressource);
		this.levelMod = IRONMINE_LEVELMOD;
		this.costs = IRONMINE_COSTS;
		this.description = IRONMINE_DESCRIPTION;
	}

}
