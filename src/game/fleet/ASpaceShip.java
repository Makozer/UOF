package game.fleet;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;

import game.*;
import game.research.*;
import game.ressource.*;
import game.utils.*;
import static game.settings.GameSettings.*;

public abstract class ASpaceShip extends AGameObject {
	
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
	protected int					timeToBuild = 999999;
	
	public ASpaceShip() {
		this(new TechTree(), 1);
	}	
	
	public ASpaceShip(TechTree techtree, int quantity) {
		this.setQuantity(quantity);
		this.techtree = techtree;	
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}

	public double getAttack() {
		return ((this.attack / 100.0) * (100.0 + levelMod.getValue(techtree.getLevel(this.getName()))))  / 100.0 * (100.0 + this.techtree.getAttack());
	}
	
	public String getAttackAsString() {
		return NumberUtils.round2dec(this.getAttack());
	}

	public double getDefense() {
		return (((this.defense / 100.0) * (100.0 + levelMod.getValue(techtree.getLevel(this.getName()))))  / 100.0 * (100.0 + this.techtree.getDefense()));
	}
	
	public String getDefenseAsString() {
		return NumberUtils.round2dec(this.getDefense());
	}

	public double getSpeed() {
		return (((this.speed / 100) * (100 + (levelMod.getValue(techtree.getLevel(this.getName())) / 10)))  / 100 * (100 + this.techtree.getSpeed()));
	}
	
	public String getSpeedAsString() {
		return NumberUtils.round2dec(this.getSpeed());
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
	
	
	

	public TechTree getTechtree() {
		return techtree;
	}


	public void setTechtree(TechTree techtree) {
		this.techtree = techtree;
	}


	public ArrayList<ARessource> getCosts() {
		// Maybe lower the costs with better Research? :>
		return costs;
	}
	
	public ArrayList<ARessource> getCosts(int n) {
		ArrayList<ARessource> output = new ArrayList<ARessource>();
		for (ARessource r: this.getCosts()) {
			output.add(r.cloneMe(n));
		}
		return output;
	}


	/** getResearchCosts() returns the actual Research Costs
	 * It calculates the costs from the basic costs and the levelMod with the Ship lvl
	 * Example: Basic cost 30, Ship lvl 3 and mod = 100%, new research costs are 60
	 * @return
	 */
	public ArrayList<ARessource> getResearchCosts() {
		ArrayList<ARessource> output = new ArrayList<ARessource>();
		for (ARessource r: this.getCosts()) {
			output.add(r.cloneMe(levelMod.getValue(techtree.getLevel(this.getName()))));			
		}
		return costs;
	}
	

	/** Returns the time needed to build this SpaceShip
	 * @return int time to build in seconds
	 */
	public int getTimeToBuild(int spaceportlevel) {	
		int output = 1;
		output += 	this.timeToBuild;
		output -= 	(int)(this.timeToBuild / 100.0 * this.getModValue());
		output -= 	(int)(this.timeToBuild / 100.0 * (spaceportlevel * 2));
		output = 	(int)(output / GAME_SPEED);
		return output;
	}
	
	public String getTimeToBuildAsString(int spaceportlevel) {
		return DateUtils.getRemainingTimeAsString(new Date(new Date().getTime() 
				+ (long)(this.getTimeToBuild(spaceportlevel) * 1000)			
					));
	}
	
	public double getModValue() {
		return levelMod.getValue(this.getLevel());
	}
	
	public int getLevel() {
		return this.techtree.getLevel(this.getName());
	}
	
	public ASpaceShip cloneMe(int quantity) {
		ASpaceShip output = this.cloneMe();
		output.setQuantity(quantity);
		return output;
	}
	
	public ASpaceShip cloneMe() {
		ASpaceShip output = null;
		try {
			output = this.getClass().getDeclaredConstructor().newInstance();
			output.setQuantity(this.getQuantity());
			output.setTechtree(this.getTechtree());
			output.attack = this.attack;
			output.defense = this.defense;
			output.speed = this.speed;
			output.capacity = this.capacity;
			output.levelMod = this.levelMod;
			output.costs = this.costs;
			output.timeToBuild = this.timeToBuild;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.out.println("Exception in ASpaceShip.cloneMe()");
		}
		return output;
	}

	@Override
	public String toString() {
		return this.getName() + "[attack=" + attack + ", defense=" + defense + ", speed=" + speed + ", capacity=" + capacity
				+ ", quantity=" + quantity + ", techtree=" + techtree + ", levelMod=" + levelMod + ", costs=" + costs
				+ ", getResearchCosts()=" + getResearchCosts() + ", getModValue()="
				+ getModValue() + ", getTimeToBuild()=" + getTimeToBuild(1) + "]";
	}
	
	
	
	
}
