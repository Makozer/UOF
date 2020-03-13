package game.fleet;

import game.utils.NumberUtils;

public class Combat {
	
	public static Fleet fight(Fleet a, Fleet b) {
		// If Fleet a is stronger then b
		if (a.getCombatPower() > b.getCombatPower()) {			
			if (a.getCombatPower() > (2 * b.getCombatPower())) {				
				return a; // If Fleet a is over 2 times stronger then b, then it loses without losses
			} else {
				return brawl(a, b);
			}
		} else {
			if (b.getCombatPower() > (2 * a.getCombatPower())) {				
				return b; // If Fleet b is over 2 times stronger then a, then it loses without losses
			} else {
				return brawl(a, b);
			}
		}
	}
	
	private static Fleet brawl(Fleet a, Fleet b) {
		long combined = a.getCombatPower() + b.getCombatPower();
		double apct = a.getCombatPower() / combined * 100;
		double bpct = b.getCombatPower() / combined * 100;
		int rngesus = NumberUtils.getRndInt(0, 100);
		
		if (rngesus > apct) {
			// B won but takes rngesus as percent damage
			b.reduceFleetByPct(rngesus); 
			return b;
		} else {
			// A won but takes rngesus flipped as percent damage
			a.reduceFleetByPct(100 - rngesus); 
			return a;
		}
	}
}
