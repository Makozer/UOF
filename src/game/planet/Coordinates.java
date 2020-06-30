package game.planet;
/** This Class represents the Coordinates of a Planet.
 * @author Makozer
 */
public class Coordinates {
	
	private int galaxy = 0;
	private int solarsystem = 0;
	private int planetnumber = 0;
	
	/**
	 * Create Coordinates with Galaxy, SolarSystem, PlanetNumber
	 * @param galaxy
	 * @param solarsystem
	 * @param planetnumber
	 */
	public Coordinates(int galaxy, int solarsystem, int planetnumber) {
		this.galaxy = galaxy;
		this.solarsystem = solarsystem;
		this.planetnumber = planetnumber;
	}
	
	/**
	 * Tries to create Coordinates with Strings
	 * @param galaxy
	 * @param solar
	 * @param planet
	 */
	public Coordinates(String galaxy, String solar, String planet) {
		try {
			this.galaxy = Integer.parseInt(galaxy);
			this.solarsystem = Integer.parseInt(solar);
			this.planetnumber = Integer.parseInt(planet);
		} catch (NumberFormatException e) {
			this.galaxy = 0;
			this.solarsystem = 0;
			this.planetnumber = 0;
		}
	}

	public int getGalaxy() {
		return galaxy;
	}

	public int getSolarSystem() {
		return solarsystem;
	}

	public int getPlanetNumber() {
		return planetnumber;
	}
	
	/**
	 * Returns a String with Coordinates like 1:33:7 for WebInterface
	 * @return
	 */
	public String asCoords() {
		return galaxy + ":" + solarsystem + ":" + planetnumber;
	}

	@Override
	public String toString() {
		return "Coordinates [Galaxie=" + galaxy + ", Sonnenystem=" + solarsystem + ", Planet=" + planetnumber
				+ "]";
	}
	
}
