package game.settings;

import java.util.*;
import game.fleet.*;
import game.fleet.special.*;
import game.fleet.tier1.*;
import game.fleet.tier2.*;
import game.fleet.tier3.*;
import game.research.*;


public class ShipSettings {
	
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
	
}
