package game.fleet;

import java.util.*;
import community.message.*;
import static game.settings.GameSettings.*;
import database.DBMessage;
import game.fleet.tier1.Falcon;
import game.planet.Coordinates;
import game.player.*;
import game.research.*;
import game.ressource.ARessource;
import game.utils.*;

public class Combat {
	
	public static void main(String[] args) {
		// Combat Sim to check if its working properly
		Fleet a = new Fleet();
		Fleet b = new Fleet();
		TechTree techtree = new TechTree();
		techtree.setLevel(new Falcon().getName(), 1);
		
		a.addShip(new Falcon(techtree, 150));
		b.addShip(new Falcon(techtree, 100));
		
		System.out.println("Kampfst�rke der jeweiligen Flotte:");
		System.out.println("A: " + a.getCombatPower());
		System.out.println("B: " + b.getCombatPower());
		long combined = a.getCombatPower() + b.getCombatPower();
		System.out.println("Verteilung der Kampfkraft A " + NumberUtils.round2dec((((double)a.getCombatPower() / (double)combined) * 100.0)) + ":" + NumberUtils.round2dec(((double)b.getCombatPower() / (double)combined) * 100.0) + " B");
		
		a.setPlayerId(1);
		b.setPlayerId(2);
		
		Fleet winner = null;
		Integer[] wins = new Integer[3];
		wins[0] = 0;
		wins[1] = 0;
		wins[2] = 0;
		
		for (int i = 0; i < 1000; i++) {
			a.clear();
			b.clear();
			a.addShip(new Falcon(techtree, 150));
			b.addShip(new Falcon(techtree, 100));
			winner = Combat.fight(a, b);
			if (winner.getPlayerId() == 1) {wins[1]++;}
			if (winner.getPlayerId() == 2) {wins[2]++;}
		}
		System.out.println("Ergebnis von 10000 K�mpfen");
		System.out.println("Siege von A: " + wins[1]);
		System.out.println("Siege von B: " + wins[2]);
	}
	
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
		
		Message messagetowinner = new CommunityMessage(loser.getPersData().getId(), winner.getPersData().getId(), "Kampfbericht von " + moshpit.asCoords(), msg, new Date());
		Message messagetoloser = new CommunityMessage(winner.getPersData().getId(), loser.getPersData().getId(), "Kampfbericht von " + moshpit.asCoords(), msg, new Date());
		DBMessage.createMessage(messagetowinner);
		DBMessage.createMessage(messagetoloser);
		
		
		return true;
	}
	
	public static Fleet fight(Fleet a, Fleet b) {
		// TODO NOT WORKING PROPERLY, DEFEND ALWAYS WINS
		// If Fleet a is stronger then b
		if (a.getCombatPower() > b.getCombatPower()) {			
			if (a.getCombatPower() > (2 * b.getCombatPower())) {	
				//if (DEBUGMODE) {System.out.println("a.getCombatPower(" + a.getCombatPower() + ") > 2 * b.getCombatPower(" + b.getCombatPower() + ")");}
				return a; // If Fleet a is over 2 times stronger then b, then it loses without losses
			} else {
				return brawl(a, b);
			}
		} else {
			if (b.getCombatPower() > (2 * a.getCombatPower())) {	
				//if (DEBUGMODE) {System.out.println("b.getCombatPower(" + b.getCombatPower() + ") > 2 * a.getCombatPower(" + a.getCombatPower() + ")");}
				return b; // If Fleet b is over 2 times stronger then a, then it loses without losses
			} else {
				return brawl(a, b);
			}
		}
	}
	
	private static Fleet brawl(Fleet a, Fleet b) {
		long combined = a.getCombatPower() + b.getCombatPower();
		double apct = (double)a.getCombatPower() / (double)combined * 100.0;
		double bpct = (double)b.getCombatPower() / (double)combined * 100.0;
		int rngesus = NumberUtils.getRndInt(0, 100);
		
		int buffa = (int)apct;
		int buffb = (int)bpct;
		if (buffa != buffb) {
			if(buffa > buffb) {apct += ((buffa - 50.0) * 2);}
			if(buffb > buffa) {bpct += ((buffa - 50.0) * 2);}
		}
		
		if (rngesus > apct) {
			// B won but takes rngesus as percent damage with an cap
			b.reduceFleetByPct(rngesus - 25); 
			return b;
		} else {
			// A won but takes rngesus flipped as percent damage with an cap
			a.reduceFleetByPct(100 - rngesus - 25); 
			return a;
		}
	}
}
