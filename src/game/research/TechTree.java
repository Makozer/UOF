package game.research;

import java.util.ArrayList;

/** This class saves and represents the Players TechTree.
 * @author Martin
 *
 */
public class TechTree {
	
	ArrayList<AResearch> attackMod = new ArrayList<AResearch>();
	ArrayList<AResearch> defenseMod = new ArrayList<AResearch>();	
	ArrayList<AResearch> healthMod = new ArrayList<AResearch>();
	ArrayList<AResearch> speedMod = new ArrayList<AResearch>();
	ArrayList<AResearch> capacityMod = new ArrayList<AResearch>();

	public static void main(String[] args) {
		// Testmain
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
	
	public void addhealthResearch(AResearch research) {
		healthMod.add(research);
	}
	
	public double getHealth() {
		double output = 0;
		for (AResearch r: healthMod) {
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
}
