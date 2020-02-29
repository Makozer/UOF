package game.research;

import game.utils.*;

public abstract class AResearch {
	
	protected AMath modification = new Polynomial(1);
	protected int level = 0;
	
	public AResearch(int level) {
		this.level = level;
	}	
	
	public double getValue() {
		return modification.getY(level);
	}

}
