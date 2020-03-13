package game.fleet;

import java.util.Date;
import game.planet.Coordinates;
import game.ressource.Tritium;

public class TravelCalc {
	
	public static Date calculateTime(Coordinates a, Coordinates b, int speed) {
		Date output = null;
		int addSec = 0;
		if (speed > 999999) {
			// SpyDrone
			if (a.getGalaxy() != b.getGalaxy()) {
				addSec = 15 * 60 + (1);
			}
			if (a.getSolarSystem() != b.getSolarSystem()) {
				addSec = 5 * 60 + (1);
			}
			addSec = 1 * 60 + (1);
		}
		if (a.getGalaxy() != b.getGalaxy()) {
			addSec = 360 * 60 + (1);
		}
		if (a.getSolarSystem() != b.getSolarSystem()) {
			addSec = 120 * 60 + (1);
		}
		addSec = 60 * 60 + (1);
		if (addSec > 0) { output = new Date(new Date().getTime() + (addSec * 1000));}
		return output;
	}
	
	public static Tritium calculateCosts(Coordinates a, Coordinates b,Fleet fleet) {
		// TODO calc Tritium
		Tritium output = new Tritium(0);
		return new Tritium(0);
	}

}
