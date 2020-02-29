package game.research;

import game.utils.Polynomial;

public class LaserPointer extends AResearch {

	public LaserPointer(int level) {
		super(level);
		this.polyMod = new Polynomial(2, 0);
	}

}
