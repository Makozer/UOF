package game.fleet.special;

import static game.settings.ShipSpecialSettings.*;

import game.fleet.ASpaceShip;
import game.research.TechTree;

public class SpyDrone extends ASpaceShip {
	
	public SpyDrone() {
		super();
	}

	public SpyDrone(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = 		SPYDRONE_ATTACK;				
		this.defense = 		SPYDRONE_DEFENSE;							
		this.speed = 		SPYDRONE_SPEED;				
		this.capacity = 	SPYDRONE_CAPACITY;	
		this.levelMod = 	SPYDRONE_LEVELMOD;
		this.timeToBuild = 	SPYDRONE_BUILDSECONDS;
		this.costs = 		SPYDRONE_COSTS;
	}

}
