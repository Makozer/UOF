package game.utils;

import java.util.*;
import game.planet.buildings.*;
import game.planet.buildings.mining.*;
import game.research.*;
import game.ressource.*;

public class UnitChecker {

	public static void main(String[] args) {
		TechTree techtree = new TechTree();		
		HeadQuarter hq = new HeadQuarter(techtree, 1);
		techtree.setLevel(hq.getName(), 1);
		//IronMine im = new IronMine(hq, techtree, 1, new Date(), new Iron(500));
		AResMiningBuilding building = new IronMine(hq, techtree, 1, new Date(), new Iron(500));

		for (int i = 0; i < 21; i++) {
			building.setLevel(i);
			System.out.println("Level:\t" + i + ";\tLeistung:\tlvlmod(" + NumberUtils.round2decToString(building.getLevelModValue(i)) + ");h(" + building.getResPerHour() + ");s(" + building.getResPerSecond() + ");\tBauzeit:\t" + building.getTimeToBuildAsString(i) + ";\tBaukosten:\t" + building.getBuildCosts(i));
		}
	}

}
