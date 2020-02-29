package game.fleet;

import game.research.TechTree;

public abstract class ASpaceShip {
	
	protected int attack = 0;				// attack rating
	protected int defense = 0;				// defense rating
	protected int health = 0;				// healthpoints
	protected double speed = 0;				// speed x lightspeed = real speed of ships
	protected int capacity = 0;				// loading capacity
	protected int quantity = 0;				// amount of ships
	protected boolean available = false; 	// if the ship is already researched
	
	protected TechTree techtree = null;
	
	// Idea
	protected int level = 0; // Maybe leveling up system? though fights or research?
	
	
	public ASpaceShip(TechTree techtree, int quantity) {
		this.quantity = quantity;
		this.techtree = techtree;
	}


	public int getAttack() {
		return (int)((this.attack + 0) * this.techtree.getAttack());
	}

	public int getDefense() {
		return defense;
	}

	public int getHealth() {
		return health;
	}

	public double getSpeed() {
		return speed;
	}

	public int getCapacity() {
		return capacity;
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
	
	
	
	
}
