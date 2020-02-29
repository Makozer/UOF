package game.fleet.tier2;

import game.fleet.*;
import game.research.TechTree;

/** A SpaceShip Class that gets even stronger if its fleet is bigger then the enemy ... :D
 * @author Makozer
 *
 */
public class Priest extends ASpaceShip {

	public Priest(TechTree techtree, int quantity) {
		super(techtree, quantity);
		this.attack = 10;				
		this.defense = 10;				
		this.health = 10;				
		this.speed = 10;				
		this.capacity = 10;	
	}

}
