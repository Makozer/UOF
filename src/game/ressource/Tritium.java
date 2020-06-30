package game.ressource;

/**
 * This Class represents the Ressource Tritium in the Game.
 * Its used for all Kinds like Costs to build a ship or the Storage on a Planet.
 * @author Martin
 *
 */
public class Tritium extends ARessource {
	
	public Tritium(int ressourcevalue) {
		super(ressourcevalue);
	}
	
	public Tritium(String ressourceValue) {
		super(ressourceValue);
	}

	@Override
	public ARessource cloneMe(double mod) {
		return new Tritium((int)(this.getValue() * mod));
	}
	
	@Override
	public ARessource cloneMe() {
		return new Tritium(this.getValue());
	}
	
	@Override
	public ARessource cloneMe(int value) {
		return new Tritium(value);
	}

}
