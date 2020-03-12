package game.planet.buildings.mining;

import java.util.Date;

import game.planet.buildings.HeadQuarter;
import game.research.TechTree;
import game.ressource.*;
import static game.settings.BuildingSettings.*;

public class Fountain extends AResMiningBuilding {

	public Fountain(HeadQuarter hq, TechTree techtree, int level, Date date, ARessource ressource) {
		super(hq, techtree, level, date, ressource);
		this.levelMod = FOUNTAIN_LEVELMOD;
		this.costs = FOUNTAIN_COSTS;
		this.description = FOUNTAIN_DESCRIPTION;
	}

}
