package game.fleet;

import static game.settings.GameSettings.GAME_SPEED;

import java.util.Date;
import game.planet.Coordinates;
import game.ressource.Tritium;

public class TravelCalc {
	
	public static Date calculateTime(Coordinates a, Coordinates b, int speed) {
		// TODO VARIABLE SPEED SETTINGS BY USER REDUCING TRAVELCOST
		Date output = null;
		int addSec = 0;
		// TODO LOWPRIO include speed factor
		if (speed > 999999) {
			// SpyDrone
			if (a.getGalaxy() != b.getGalaxy()) {
				addSec = 15 * 60 + (1);
			} else if (a.getSolarSystem() != b.getSolarSystem()) {
				addSec = 5 * 60 + (1);
			} else {
				addSec = 1 * 60 + (1);
			}			
		}
		if (a.getGalaxy() != b.getGalaxy()) {
			addSec = 360 * 60 + (1);
		} else if (a.getSolarSystem() != b.getSolarSystem()) {
			addSec = 120 * 60 + (1);
		} else {
			addSec = 60 * 60 + (1);
		}		
		if (addSec > 0) { output = new Date(new Date().getTime() + (long)((addSec * 1000)/ GAME_SPEED));}
		return output;
	}
	
	public static Tritium calculateCosts(Coordinates a, Coordinates b,Fleet fleet) {
		// TODO LOWPRIO do a better calc then this :D
		int size = 0;
		for (ASpaceShip ship: fleet.getFleet()) {
			size += ship.getQuantity();
		}
		Tritium output = new Tritium(size * 10);
		return new Tritium(0);
	}

}
