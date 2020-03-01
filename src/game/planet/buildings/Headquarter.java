package game.planet.buildings;

import game.research.*;
import static game.settings.BuildingSettings.*;

public class Headquarter extends ABuilding {

	public Headquarter(TechTree techtree) {
		super(techtree);
		this.levelMod = HEADQUARTER_LEVELMOD;
	}

	

}
