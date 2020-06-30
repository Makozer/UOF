package game.planet.buildings.mining;

import java.util.Date;

import game.planet.buildings.HeadQuarter;
import game.research.TechTree;
import game.ressource.ARessource;
import static game.settings.BuildingSettings.*;
import static game.settings.GameSettings.GAME_SPEED;

/**
 * The RareEarthMine of a Planet to generate RareEarth
 * @author Martin
 *
 */
public class RareEarthMine extends AResMiningBuilding {

	public RareEarthMine(HeadQuarter hq, TechTree techtree, int level, Date date, ARessource ressource) {
		super(hq, techtree, level, date, ressource);
		this.levelMod = RAREEARTHMINE_LEVELMOD;
		this.costs = RAREEARTHMINE_COSTS;
		this.description = RAREEARTHMINE_DESCRIPTION;
	}
	
	@Override
	public long getTimeToBuild(int level) {
		long combRessCost = 0;
		for (ARessource r: this.getBuildCosts(level)) {
			combRessCost += (r.getValue() * 2);
		}
		return (long)(((combRessCost * 6.6) / 100.0 * (100.0 - this.getHeadQuarter().getLevelModValue())) / GAME_SPEED);
	}

}
