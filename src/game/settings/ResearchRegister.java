package game.settings;

import java.util.*;

import game.fleet.ASpaceShip;
import game.research.*;
import game.research.Research.*;
import game.ressource.*;
import game.utils.*;

/**
 * This Class is the Register for all Researches.
 * Used for everything Research related if something exists etc.
 * @author Martin
 *
 */
public class ResearchRegister {
	
	/**
	 * Returns all possible Researches
	 * @param techtree Required techtree
	 * @return ArrayList<Research>
	 */
	public static final ArrayList<Research> getWholeResearchList(TechTree techtree) {
		ArrayList<Research> output = new ArrayList<Research>();
		output.addAll(getPropulsionResearch(techtree));
		output.addAll(getSpecialShipResearch(techtree));
		output.addAll(getShipT1Research(techtree));
		output.addAll(getShipT2Research(techtree));
		output.addAll(getShipT3Research(techtree));
		output.addAll(getAttackResearch(techtree));
		return output;
	}
	
	/**
	 * Returns all possible Propulsion Researches
	 * @param techtree Required techtree
	 * @return ArrayList<Research>
	 */
	public static final ArrayList<Research> getPropulsionResearch(TechTree techtree) {
		ArrayList<Research> output = new ArrayList<Research>();
		output.addAll(getPropulsionT1Research(techtree));
		output.addAll(getPropulsionT2Research(techtree));
		output.addAll(getPropulsionT3Research(techtree));
		return output;
	}
	
	/**
	 * Return all T1 Propulsion Tech
	 * @param techtree User TechTree
	 * @return ArrayList<Research> 
	 */
	public static final ArrayList<Research> getPropulsionT1Research(TechTree techtree) {
		return new ArrayList<Research>(Arrays.asList(
				new Research(ResearchEnum.SPEED, "SpyPropulsion", techtree, new Polynomial(1, 1, 0), 
						new ArrayList<ARessource>(Arrays.asList(new RareEarth(1000), new Tritium(100))), new ArrayList<Research>(Arrays.asList())), 
				new Research(ResearchEnum.SPEED, "Raketenantrieb", techtree, new Polynomial(1, 1, 0), 
						new ArrayList<ARessource>(Arrays.asList(new Iron(1000), new RareEarth(100), new Water(500), new Tritium(500))), new ArrayList<Research>(Arrays.asList()))
				));
	}
	
	/**
	 * Return all T2 Propulsion Tech
	 * @param techtree User TechTree
	 * @return ArrayList<Research> 
	 */
	public static final ArrayList<Research> getPropulsionT2Research(TechTree techtree) {
		return new ArrayList<Research>(Arrays.asList(
				new Research(ResearchEnum.SPEED, "Spacebender Drive", techtree, new Polynomial(1, 1, 0), 
						new ArrayList<ARessource>(Arrays.asList(new Iron(1000), new RareEarth(100), new Water(500), new Tritium(500))), new ArrayList<Research>(Arrays.asList(ResearchRegister.getPropulsionT1Research(techtree).get(1))))
				));
	}
	
	/**
	 * Return all T3 Propulsion Tech
	 * @param techtree User TechTree
	 * @return ArrayList<Research> 
	 */
	public static final ArrayList<Research> getPropulsionT3Research(TechTree techtree) {
		return new ArrayList<Research>(Arrays.asList(
				new Research(ResearchEnum.SPEED, "HyperWarp", techtree, new Polynomial(1, 1, 0), 
						new ArrayList<ARessource>(Arrays.asList(new Iron(100000), new RareEarth(100000), new Water(500000), new Tritium(500000))), new ArrayList<Research>(Arrays.asList(ResearchRegister.getPropulsionT2Research(techtree).get(0))))
				));
	}
	
	/**
	 * Return all Special Tech Ships
	 * @param techtree User TechTree
	 * @return ArrayList<Research> 
	 */
	public static final ArrayList<Research> getSpecialShipResearch(TechTree techtree) {
		ArrayList<Research> specialships = new ArrayList<Research>();
		for (ASpaceShip ship : ShipRegister.getSpecialShipList(techtree)) {
			specialships.add(
						new Research(ResearchEnum.SHIP, ship.getName(), techtree, ship.getLevelMod(), ship.getResearchCosts(), 
								new ArrayList<Research>(Arrays.asList(ResearchRegister.getPropulsionResearch(techtree).get(0))))
					);
		}		
		return specialships;
	}
	
	/**
	 * Return all T1 Tech Ships
	 * @param techtree User TechTree
	 * @return ArrayList<Research> 
	 */
	public static final ArrayList<Research> getShipT1Research(TechTree techtree) {
		ArrayList<Research> t1ships = new ArrayList<Research>();
		for (ASpaceShip ship : ShipRegister.getT1ShipList(techtree)) {
			t1ships.add(
						new Research(ResearchEnum.SHIP, ship.getName(), techtree, ship.getLevelMod(), ship.getResearchCosts(), 
								new ArrayList<Research>(Arrays.asList(ResearchRegister.getPropulsionResearch(techtree).get(1))))
					);
		}		
		return t1ships;
	}
	
	/**
	 * Return all T2 Tech Ships
	 * @param techtree User TechTree
	 * @return ArrayList<Research> 
	 */
	public static final ArrayList<Research> getShipT2Research(TechTree techtree) {
		ArrayList<Research> t1ships = new ArrayList<Research>();
		for (ASpaceShip ship : ShipRegister.getT2ShipList(techtree)) {
			t1ships.add(
						new Research(ResearchEnum.SHIP, ship.getName(), techtree, ship.getLevelMod(), ship.getResearchCosts(), 
								new ArrayList<Research>(Arrays.asList(ResearchRegister.getPropulsionResearch(techtree).get(2))))
					);
		}		
		return t1ships;
	}
	
	/**
	 * Return all T3 Tech Ships
	 * @param techtree User TechTree
	 * @return ArrayList<Research> 
	 */
	public static final ArrayList<Research> getShipT3Research(TechTree techtree) {
		ArrayList<Research> t1ships = new ArrayList<Research>();
		for (ASpaceShip ship : ShipRegister.getT3ShipList(techtree)) {
			t1ships.add(
						new Research(ResearchEnum.SHIP, ship.getName(), techtree, ship.getLevelMod(), ship.getResearchCosts(), 
								new ArrayList<Research>(Arrays.asList(ResearchRegister.getPropulsionResearch(techtree).get(3))))
					);
		}		
		return t1ships;
	}
	
	public static final ArrayList<Research> getAttackResearch(TechTree techtree) {
		return new ArrayList<Research>(Arrays.asList(
				
				
				));
	}

}
