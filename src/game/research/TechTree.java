package game.research;

import java.util.*;

/** This class saves and represents the Players TechTree.
 * @author Martin
 *
 */
public class TechTree {
	
	private ArrayList<AResearch> attackMod = new ArrayList<AResearch>();
	private ArrayList<AResearch> defenseMod = new ArrayList<AResearch>();	
	private ArrayList<AResearch> speedMod = new ArrayList<AResearch>();
	private ArrayList<AResearch> capacityMod = new ArrayList<AResearch>();
	
	private HashMap<String, Integer> levels = new HashMap<String, Integer>();
	
	

	public static void main(String[] args) {
		// Testmain
	}
	
	public void testFill() {
		this.addAttackResearch(new LaserPointer(4));
		this.addDefenseResearch(new LaserPointer(3));
		this.addSpeedResearch(new LaserPointer(2));
		this.addCapacityResearch(new LaserPointer(1));
		// SpaceShips
		this.setLevel("Falcon", 11);
		this.setLevel("Yamato", 11);
	}
	
	public void addAttackResearch(AResearch research) {
		attackMod.add(research);
	}
	
	public double getAttack() {
		double output = 0;
		for (AResearch r: attackMod) {
			output += r.getValue();
		}
		return output;
	}

	public void addDefenseResearch(AResearch research) {
		defenseMod.add(research);
	}
	
	public double getDefense() {
		double output = 0;
		for (AResearch r: defenseMod) {
			output += r.getValue();
		}
		return output;
	}
	
	public void addSpeedResearch(AResearch research) {
		speedMod.add(research);
	}
	
	public double getSpeed() {
		double output = 0;
		for (AResearch r: speedMod) {
			output += r.getValue();
		}
		return output;
	}
	
	public void addCapacityResearch(AResearch research) {
		capacityMod.add(research);
	}
	
	public double getCapacity() {
		double output = 0;
		for (AResearch r: capacityMod) {
			output += r.getValue();
		}
		return output;
	}
	
	public void setLevel(String name, int n) {
		levels.put(name, n);
	}
	
	public int getLevel(String name) {
		Integer output = levels.get(name);
		if (output == null) {
			return 0;
		}
		return (int)output;
	}
}
