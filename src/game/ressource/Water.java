package game.ressource;

/**
 * This Class represents the Ressource Water in the Game.
 * Its used for all Kinds like Costs to build a ship or the Storage on a Planet.
 * @author Martin
 *
 */
public class Water extends ARessource {

	public Water(int ressourcevalue) {
		super(ressourcevalue);
	}
	
	public Water(String ressourceValue) {
		super(ressourceValue);
	}

	@Override
	public ARessource cloneMe(double mod) {
		return new Water((int)(this.getValue() * mod));
	}
	
	@Override
	public ARessource cloneMe() {
		return new Water(this.getValue());
	}
	
	@Override
	public ARessource cloneMe(int value) {
		return new Water(value);
	}

}
