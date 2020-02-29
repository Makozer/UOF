package game.fleet.tier2;

import game.fleet.ASpaceShip;
import game.research.TechTree;

public class Cheetah extends ASpaceShip {

	public Cheetah(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = 30;				
		this.defense = 10;				
		this.health = 10;				
		this.speed = 30;				
		this.capacity = 10;	
	}

}
