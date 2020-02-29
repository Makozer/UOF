package game.fleet.tier1;
import game.fleet.*;
import game.research.TechTree;

public class Falcon extends ASpaceShip {
	
	public Falcon(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = 3;				
		this.defense = 1;				
		this.health = 1;				
		this.speed = 3;				
		this.capacity = 10;				
	}
	
	
}
