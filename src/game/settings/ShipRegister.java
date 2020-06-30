package game.settings;

import java.util.*;
import game.fleet.*;
import game.fleet.special.*;
import game.fleet.tier1.*;
import game.fleet.tier2.*;
import game.fleet.tier3.*;
import game.research.*;


/**
 * Stores all possible Ships to use / build.
 * @author Martin
 *
 */
public class ShipRegister {	
	
	public static final ArrayList<ASpaceShip> getSpecialShipList(TechTree techtree) {
		return new ArrayList<ASpaceShip>(Arrays.asList(
				new SpyDrone(techtree, 1)));
	}
	
	public static final ArrayList<ASpaceShip> getT1ShipList(TechTree techtree) {
		return new ArrayList<ASpaceShip>(Arrays.asList(
				new Falcon(techtree, 1)));
	}
	
	public static final ArrayList<ASpaceShip> getT2ShipList(TechTree techtree) {
		return new ArrayList<ASpaceShip>(Arrays.asList(
				new Cheetah(techtree, 1)));
	}
	
	public static final ArrayList<ASpaceShip> getT3ShipList(TechTree techtree) {
		return new ArrayList<ASpaceShip>(Arrays.asList(
				new Yamato(techtree, 1)));
	}
	
	/**
	 * Returns all possible Ships without checking if they are researched by the user
	 * @param techtree
	 * @return ArrayList<ASpaceShip>
	 */
	public static final ArrayList<ASpaceShip> getWholeShipList(TechTree techtree) {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		output.addAll(getSpecialShipList(techtree));
		output.addAll(getT1ShipList(techtree));
		output.addAll(getT2ShipList(techtree));
		output.addAll(getT3ShipList(techtree));
		return output;
	}
	
}
