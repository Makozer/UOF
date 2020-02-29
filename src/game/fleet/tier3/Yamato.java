package game.fleet.tier3;

import game.fleet.ASpaceShip;
import game.research.TechTree;
import static game.settings.ShipT3Settings.*;

public class Yamato extends ASpaceShip {

	public Yamato(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = YAMATO_ATTACK;				
		this.defense = YAMATO_DEFENSE;				
		this.health = YAMATO_HEALTH;				
		this.speed = YAMATO_SPEED;				
		this.capacity = YAMATO_CAPACITY;	
		
		// It's Over 9000!
	}

}
