package game.planet.buildings.mining;

import java.util.Date;
import game.research.TechTree;
import game.ressource.ARessource;
import static game.settings.BuildingSettings.*;

public class TritiumFabric extends AResMiningBuilding {

	public TritiumFabric(TechTree techtree, int level, Date date, ARessource ressource) {
		super(techtree, level, date, ressource);
		this.levelMod = TRITIUMFABRIC_LEVELMOD;
		this.costs = TRITIUMFABRIC_COSTS;
		this.description = TRITIUMFABRIC_DESCRIPTION;
	}

}
