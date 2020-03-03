package game.planet.buildings;

import game.research.*;
import game.utils.*;

import static game.settings.BuildingSettings.*;

public class HeadQuarter extends ABuilding {

	public HeadQuarter() {
		
	}
	
	public HeadQuarter(TechTree techtree, int level) {
		super(techtree, level);
		this.levelMod = HEADQUARTER_LEVELMOD;
	}

	public void testFill(TechTree techtree) {
		
		this.levelMod = new Polynomial(1,0,0);		
		this.techtree = techtree;
	}

}
