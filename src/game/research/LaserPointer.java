package game.research;

import static game.settings.ResearchSettings.*;

public class LaserPointer extends AResearch {

	public LaserPointer(TechTree techtree) {
		super(techtree, ResearchEnum.ATTACK);
		this.modification = LASERPOINTER_LEVELMOD;
		this.costs = LASERPOINTER_COSTS;
	}

}
