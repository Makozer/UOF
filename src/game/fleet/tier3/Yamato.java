package game.fleet.tier3;

import game.fleet.ASpaceShip;
import game.research.TechTree;
import static game.settings.ShipT3Settings.*;

public class Yamato extends ASpaceShip {
	
	public Yamato() {
		super();
	}

	public Yamato(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = 		YAMATO_ATTACK;				
		this.defense = 		YAMATO_DEFENSE;							
		this.speed = 		YAMATO_SPEED;				
		this.capacity = 	YAMATO_CAPACITY;
		this.levelMod = 	YAMATO_LEVELMOD;
		this.timeToBuild = 	YAMATO_BUILDSECONDS;
		this.costs = 		YAMATO_COSTS;
	}

}
