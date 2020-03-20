package game.settings;

import java.util.*;

import game.fleet.ASpaceShip;
import game.research.*;
import game.research.Research.*;
import game.ressource.*;
import game.utils.*;

public class ResearchRegister {
	
	public static final ArrayList<Research> getWholeResearchList(TechTree techtree) {
		ArrayList<Research> output = new ArrayList<Research>();
		output.addAll(getPropulsionResearch(techtree));
		output.addAll(getSpecialShipResearch(techtree));
		output.addAll(getShipT1Research(techtree));
		output.addAll(getAttackResearch(techtree));
		return output;
	}
	
	public static final ArrayList<Research> getPropulsionResearch(TechTree techtree) {
		return new ArrayList<Research>(Arrays.asList(
				new Research(ResearchEnum.SPEED, "SpyPropulsion", techtree, new Polynomial(1, 1, 0), 
						new ArrayList<ARessource>(Arrays.asList(new RareEarth(1000), new Tritium(100))), new ArrayList<Research>(Arrays.asList())), 
				new Research(ResearchEnum.SPEED, "Raketenantrieb", techtree, new Polynomial(1, 1, 0), 
						new ArrayList<ARessource>(Arrays.asList(new Iron(1000), new RareEarth(100), new Water(500), new Tritium(500))), new ArrayList<Research>(Arrays.asList()))
				
				));
	}
	
	public static final ArrayList<Research> getSpecialShipResearch(TechTree techtree) {
		ArrayList<Research> specialships = new ArrayList<Research>();
		for (ASpaceShip ship : ShipRegister.getT1ShipList(techtree)) {
			specialships.add(
						new Research(ResearchEnum.SHIP, ship.getName(), techtree, ship.getLevelMod(), ship.getResearchCosts(), 
								new ArrayList<Research>(Arrays.asList(ResearchRegister.getPropulsionResearch(techtree).get(0))))
					);
		}		
		return specialships;
	}
	
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
	
	public static final ArrayList<Research> getAttackResearch(TechTree techtree) {
		return new ArrayList<Research>(Arrays.asList(
				
				
				));
	}

}
