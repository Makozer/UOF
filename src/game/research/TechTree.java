package game.research;

import java.util.*;
import game.fleet.*;
import game.fleet.tier1.Falcon;
import game.settings.*;

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
		TechTree techtree = new TechTree();
		techtree.setLevel("Falcon", 10);
		Falcon falcon = new Falcon(techtree, 1);
		System.out.println(falcon.toString());

	}
	
	public ASpaceShip createShip(String name, int quantity) {
		ASpaceShip output = null;
		ASpaceShip origin = this.getResearchedShip(name);
		try {
			output = origin.cloneMe(quantity);
			/*
            output = origin.getClass().getDeclaredConstructor().newInstance();
            output.setQuantity(quantity);
            output.setTechtree(this);
            */
		} catch (NullPointerException e) {
			// Everything fine xD
        } catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
		
		return output;
		/*
		 * Class aClass = Class.forName(origin.getClass().getPackageName() + "." + origin.getClass().getSimpleName());
	       newShip = (ASpaceShip)aClass.getDeclaredConstructor().newInstance();
	       newShip.setQuantity(quantity);
	       newShip.setTechtree(techtree);
	       ships.add(newShip);
		 */
	}
	
	public ASpaceShip createShip(ASpaceShip origin, int quantity) {
		ASpaceShip output = null;
		try {
			output = origin.cloneMe(quantity);
			/*
            output = origin.getClass().getDeclaredConstructor().newInstance();
            output.setQuantity(quantity);
            output.setTechtree(this);
            */
        } catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
		
		return output;
	}
	
	public void testFill() {
		this.addAttackResearch(new LaserPointer(this));
		this.addDefenseResearch(new LaserPointer(this));
		this.addSpeedResearch(new LaserPointer(this));
		this.addCapacityResearch(new LaserPointer(this));
		this.setLevel("LaserPointer", 11);
		// SpaceShips
		this.setLevel("SpyDrone", 11);
		this.setLevel("Falcon", 11);
		this.setLevel("Cheetah", 11);
		this.setLevel("Yamato", 11);
	}
	
	public void addResearch(AResearch research) {
		switch (research.getType()) {
		case ATTACK:
			this.addAttackResearch(research);
			break;
		case DEFEND:
			this.addDefenseResearch(research);
			break;
		case CAPACITY:
			this.addCapacityResearch(research);
			break;
		case SPEED:
			this.addCapacityResearch(research);
			break;
		}
	}
	
	public void addAttackResearch(AResearch research) {
		attackMod.add(research);
	}
	
	public double getAttack() {
		double output = 0;
		for (AResearch r: attackMod) {
			output += r.getModValue();
		}
		return output;
	}

	public void addDefenseResearch(AResearch research) {
		defenseMod.add(research);
	}
	
	public double getDefense() {
		double output = 0;
		for (AResearch r: defenseMod) {
			output += r.getModValue();
		}
		return output;
	}
	
	public void addSpeedResearch(AResearch research) {
		speedMod.add(research);
	}
	
	public double getSpeed() {
		double output = 0;
		for (AResearch r: speedMod) {
			output += r.getModValue();
		}
		return output;
	}
	
	public void addCapacityResearch(AResearch research) {
		capacityMod.add(research);
	}
	
	public double getCapacity() {
		double output = 0;
		for (AResearch r: capacityMod) {
			output += r.getModValue();
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
	
	public ASpaceShip getResearchedShip(String shipName) {
		ArrayList<ASpaceShip> 	allShips = this.getAllResearchedShips();	// to know which Ships can be build
		for (ASpaceShip s: allShips) {
			if (shipName.equals(s.getName())) {return s;}
		}
    	return null;
    }
	
	public ArrayList<ASpaceShip> getResearchedSpecialSpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getSpecialShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	public ArrayList<ASpaceShip> getResearchedT1SpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getT1ShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	public ArrayList<ASpaceShip> getResearchedT2SpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getT2ShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	public ArrayList<ASpaceShip> getResearchedT3SpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getT3ShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	public ArrayList<ASpaceShip> getAllResearchedShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		output.addAll(getResearchedSpecialSpaceShips());
		output.addAll(getResearchedT1SpaceShips());
		output.addAll(getResearchedT2SpaceShips());
		output.addAll(getResearchedT3SpaceShips());
		return output;
	}
		
}
