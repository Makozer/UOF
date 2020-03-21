package game.fleet;

import java.awt.geom.Area;
import java.util.*;
import community.message.*;
import database.DBMessage;
import game.planet.Coordinates;
import game.player.*;
import game.ressource.ARessource;
import game.utils.*;

public class Combat {
	
	public static boolean createCombatLog(Player winner, Player loser, Coordinates moshpit, Fleet survivor, Fleet attacker, Fleet defender, ArrayList<ARessource> ress) {
		String msg = "";
		msg += "Hallo,<br /><br />es hat ein Kampf stattgefunden auf dem Planeten: " + moshpit.asCoords();
		msg += "<br />Die Flotten waren wie folgt aufgestellt:";
		msg += "<table><tr><th>Schiffsname</th><th>Angreifer</th><th>Verteidiger</th></tr>";
		for (ASpaceShip attackship : attacker.getFleet()) {
			msg += "<tr><td>" + attackship.getName() + "</td><td>" + attackship.getQuantity() + "</td><td>";
			for(ASpaceShip defendship : defender.getFleet()) {if(attackship.getName().equals(defendship.getName())) {msg += ""+ defendship.getQuantity(); break;}} 
			msg += "</td></tr>";
		}
		msg += "</table>";
		msg += "<br /><br />Dabei hat die Flotte von " + winner.getPersData().getDisplayName() + " gewonnen und es blieben noch folgende Raumschiffe ueber:<br />";
		msg += "<table><tr><th>Schiffsname</th><th>Anzahl</th></tr>";
		for (ASpaceShip survivership : survivor.getFleet()) {
			msg += "<tr><td>";
			msg += survivership.getName() + "</td><td>" + survivership.getQuantity();
			msg += "</td></tr>";
		}
		msg += "</table> <br/>";
		
		if (ress.size() > 0) {
			msg += "Gepluendert wurden folgende Rohstoffe: <br />";
			for (ARessource r : ress) {
				msg += r.toString() + "<br />";
			}
		}
		
		Message messagetowinner = new Message(loser.getPersData().getId(), winner.getPersData().getId(), "Kampfbericht von " + moshpit.asCoords(), msg, new Date());
		Message messagetoloser = new Message(winner.getPersData().getId(), loser.getPersData().getId(), "Kampfbericht von " + moshpit.asCoords(), msg, new Date());
		DBMessage.createMessage(messagetowinner);
		DBMessage.createMessage(messagetoloser);
		
		return true;
	}
	
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
