package game.research;

import game.utils.*;
import static game.settings.ResearchSettings.*;

public class LaserPointer extends AResearch {

	public LaserPointer(int level) {
		super(ResearchEnum.ATTACK, level);
		this.modification = LASERPOINTER_LEVELMOD;
		this.costs = LASERPOINTER_COSTS;
	}

}
