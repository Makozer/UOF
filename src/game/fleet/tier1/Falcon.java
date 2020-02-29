package game.fleet.tier1;

import game.fleet.*;
import game.research.TechTree;
import static game.settings.ShipT1Settings.*;

public class Falcon extends ASpaceShip {
	
	public Falcon(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = FALCON_ATTACK;				
		this.defense = FALCON_DEFENSE;				
		this.health = FALCON_HEALTH;				
		this.speed = FALCON_SPEED;				
		this.capacity = FALCON_CAPACITY;				
	}
	
}
