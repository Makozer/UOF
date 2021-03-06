package game.fleet.tier1;

import game.fleet.*;
import game.research.TechTree;
import static game.settings.ShipT1Settings.*;

public class Falcon extends ASpaceShip {
	
	public Falcon() {
		super();
	}
	
	public Falcon(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = 		FALCON_ATTACK;				
		this.defense = 		FALCON_DEFENSE;							
		this.speed = 		FALCON_SPEED;				
		this.capacity = 	FALCON_CAPACITY;	
		this.levelMod = 	FALCON_LEVELMOD;
		this.timeToBuild = 	FALCON_BUILDSECONDS;
		this.costs =		FALCON_COSTS;
	}
	
}
