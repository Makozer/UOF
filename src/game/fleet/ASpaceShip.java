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
	protected TechTree 	techtree = null;
	protected AMath 	levelMod = null;
	
	// Costs	
	protected ArrayList<ARessource> costs = null;
	
	
	public ASpaceShip(TechTree techtree, int quantity) {
		if (techtree == null) {
			throw new IllegalArgumentException("TechTree war null!");
		} else {
			this.setQuantity(quantity);
			this.techtree = techtree;			
		}		
	}

	
	public String getName() {
		return this.getClass().getSimpleName();
	}

	public double getAttack() {
		return ((this.attack / 100.0) * (100.0 + levelMod.getValue(techtree.getLevel(this.getName()))))  / 100.0 * (100.0 + this.techtree.getAttack());
	}
	
	public String getAttackAsString() {
		return NumberUtils.round(this.getAttack());
	}

	public double getDefense() {
		return (((this.defense / 100.0) * (100.0 + levelMod.getValue(techtree.getLevel(this.getName()))))  / 100.0 * (100.0 + this.techtree.getDefense()));
	}
	
	public String getDefenseAsString() {
		return NumberUtils.round(this.getDefense());
	}

	public double getSpeed() {
		return (((this.speed / 100) * (100 + (levelMod.getValue(techtree.getLevel(this.getName())) / 10)))  / 100 * (100 + this.techtree.getSpeed()));
	}
	
	public String getSpeedAsString() {
		return NumberUtils.round(this.getSpeed());
	}

	public int getCapacity() {
		return (int)(((this.capacity / 100.0) * (100.0 + levelMod.getValue(techtree.getLevel(this.getName()))))  / 100.0 * (100.0 + this.techtree.getCapacity()));
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("ASpaceShip.setQuantity(" + quantity + ") is not possible!");
		}
		this.quantity = quantity;
	}
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	public void reduceQuantity(int quantity) {
		if (this.quantity - quantity < 0) {
			throw new IllegalArgumentException("ASpaceShip.reduceQuantity(" + quantity + ") is not possible!");
		}
		this.quantity -= quantity;		
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
			output.add(r.cloneMe(levelMod.getValue(techtree.getLevel(this.getName()))));			
		}
		return costs;
	}


	@Override
	public String toString() {
		return "ASpaceShip [attack=" + attack + ", defense=" + defense + ", speed=" + speed + ", capacity=" + capacity
				+ ", quantity=" + quantity + ", getAttack()=" + getAttack() + ", getDefense()=" + getDefense()
				+ ", getSpeed()=" + getSpeed() + ", getCapacity()=" + getCapacity() + ", getQuantity()=" + getQuantity()
				+ "]";
	}

	
	
	
	
}
