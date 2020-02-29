package game.planet;
/** This Class represents the Coordinates of a Planet.
 * @author Makozer
 */
public class Coordinates {
	
	private int galaxy = 0;
	private int solarsystem = 0;
	private int planetnumber = 0;
	
	public Coordinates(int galaxy, int solarsystem, int planetnumber) {
		this.galaxy = galaxy;
		this.solarsystem = solarsystem;
		this.planetnumber = planetnumber;
	}

	public int getGalaxy() {
		return galaxy;
	}

	public int getSolarsystem() {
		return solarsystem;
	}

	public int getPlanetnumber() {
		return planetnumber;
	}
	
	public String asCoords() {
		return galaxy + ":" + solarsystem + ":" + planetnumber;
	}

	@Override
	public String toString() {
		return "Coordinates [Galaxie=" + galaxy + ", Sonnenystem=" + solarsystem + ", Planet=" + planetnumber
				+ "]";
	}
	
}
