package game.fleet;

import java.util.ArrayList;

import game.research.TechTree;
import game.ressource.ARessource;
import game.utils.*;

public abstract class ASpaceShip {
	
	// Basic Attributes
	protected int 		attack 		= 0;	// attack rating
	protected int 		defense 	= 0;	// defense rating
	protected double 	speed 		= 0;	// speed x lightspeed = real speed of ships
	protected int		capacity 	= 0;	// loading capacity
	
	protected int 		quantity 	= 0;	// amount of ships
	
	// Research Attributes
	protected boolean 	available = false; 	// if the ship is already researched	
	protected TechTree 	techtree = null;
	protected int 		level = 1;	
	protected AMath 	levelMod = null;
	
	// Costs	
	protected ArrayList<ARessource> costs = null;
	
	
	public ASpaceShip(TechTree techtree, int quantity) {
		if (techtree == null) {
			throw new IllegalArgumentException("TechTree war null!");
		} else {
			this.quantity = quantity;
			this.techtree = techtree;
		}		
	}


	public int getAttack() {
		return (int)((this.attack * levelMod.getValue(level) ) * this.techtree.getAttack());
	}

	public int getDefense() {
		return (int)((this.defense * levelMod.getValue(level) ) * this.techtree.getDefense());
	}

	public double getSpeed() {
		return (int)((this.speed * (levelMod.getValue(level) / 10) ) * this.techtree.getSpeed());
	}

	public int getCapacity() {
		return (int)((this.capacity * levelMod.getValue(level) ) * this.techtree.getCapacity());
	}

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}


	public ArrayList<ARessource> getCosts() {
		// Maybe lower the costs with better Research? :>
		return costs;
	}


	/** getResearchCosts() returns the actual Research Costs
	 * It calculates the costs from the basic costs and the levelMod with the Ship lvl
	 * Example: Basic cost 30, Ship lvl 3 and mod = 100%, new research costs are 60
	 * @return
	 */
	public ArrayList<ARessource> getResearchCosts() {
		ArrayList<ARessource> output = new ArrayList<ARessource>();
		for (ARessource r: costs) {
			output.add(r.cloneMe(levelMod.getValue(level)));			
		}
		return costs;
	}
	
	
	
	
}
