package game.research;

import static game.settings.ResearchSettings.*;

public class LaserPointer extends Research {

	public LaserPointer(TechTree techtree) {
		super(techtree, ResearchEnum.ATTACK);
		this.modification = LASERPOINTER_LEVELMOD;
		this.costs = LASERPOINTER_COSTS;
	}

}
