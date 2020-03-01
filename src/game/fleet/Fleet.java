package game.fleet;

import java.util.ArrayList;

import game.fleet.tier1.*;
import game.fleet.tier2.*;
import game.fleet.tier3.*;
import game.fleet.tierspecial.*;
import game.research.*;

public class Fleet {
	
	ArrayList<ASpaceShip> fleet = new ArrayList<ASpaceShip>();
	
	public static void main(String[] args) {
		
		// TechTree initialisieren, wird fürs Schiff benötigt
		TechTree techtree = new TechTree();
		AResearch lp = new LaserPointer(10);
		System.out.println(lp.toString());
		techtree.addAttackResearch(lp);
		
		
		Fleet fleet = new Fleet();
		Falcon falcon = new Falcon(techtree, 2);
		techtree.setLevel(falcon.getName(), 10);
		fleet.addShips(falcon);
		System.out.println(falcon.toString());
		System.out.println(fleet.toString());
	}
	
	public int getAttack() {
		double output = 0;
		for (ASpaceShip s: fleet) {
			output += s.getAttack() * s.getQuantity();
		}
		return (int)output;
	}
	
	public int getDefense() {
		double output = 0;
		for (ASpaceShip s: fleet) {
			output += s.getDefense() * s.getQuantity();
		}
		return (int)output;
	}
	
	
	/** getSpeed returns the slowest shipspeed in the fleet
	 * @return fleet speed
	 */
	public int getSpeed() {
		double output = 999999;
		for (ASpaceShip s: fleet) {
			if (s.getSpeed() < output) {
				output = s.getSpeed();
			}
		}
		return (int)output;
	}
	
	public int getCapacy() {
		int output = 0;
		for (ASpaceShip s: fleet) {
			output += s.getCapacity() * s.getQuantity();
		}
		return output;
	}
	
	public void addShips(ASpaceShip ship) {
		fleet.add(ship);
	}
	
	public void removeShips(ASpaceShip ship) {
		fleet.remove(ship);
	}

	@Override
	public String toString() {
		return "Fleet [getAttack()=" + getAttack() + ", getDefense()=" + getDefense() 
				+ ", getSpeed()=" + getSpeed() + ", getCapacy()=" + getCapacy() + "]";
	}
	
	

}
