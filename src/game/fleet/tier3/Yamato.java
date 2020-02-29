package game.fleet.tier3;

import game.fleet.ASpaceShip;
import game.research.TechTree;

public class Yamato extends ASpaceShip {

	public Yamato(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = 9999;				
		this.defense = 9999;				
		this.health = 9999;				
		this.speed = 9999;				
		this.capacity = 9999;	
		
		// It's Over 9000!
	}

}
