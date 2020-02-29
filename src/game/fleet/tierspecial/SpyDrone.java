package game.fleet.tierspecial;

import static game.settings.ShipTSpecialSettings.*;

import game.fleet.ASpaceShip;
import game.research.TechTree;

public class SpyDrone extends ASpaceShip {

	public SpyDrone(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = 	SPYDRONE_ATTACK;				
		this.defense = 	SPYDRONE_DEFENSE;							
		this.speed = 	SPYDRONE_SPEED;				
		this.capacity = SPYDRONE_CAPACITY;	
		this.levelMod = SPYDRONE_LEVELMOD;
		this.costs = 	SPYDRONE_COSTS;
	}

}
