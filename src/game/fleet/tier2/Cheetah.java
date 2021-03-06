package game.fleet.tier2;

import static game.settings.ShipT2Settings.*;
import game.fleet.ASpaceShip;
import game.research.TechTree;

public class Cheetah extends ASpaceShip {
	
	public Cheetah() {
		super();
	}

	public Cheetah(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = 		CHEETAH_ATTACK;				
		this.defense = 		CHEETAH_DEFENSE;							
		this.speed = 		CHEETAH_SPEED;				
		this.capacity = 	CHEETAH_CAPACITY;	
		this.levelMod = 	CHEETAH_LEVELMOD;
		this.timeToBuild = 	CHEETAH_BUILDSECONDS;
		this.costs =		CHEETAH_COSTS;
	}

}
