package game.ressource;

public class RareEarth extends ARessource {

	public RareEarth(int ressourcevalue) {
		super(ressourcevalue);
	}

	@Override
	public ARessource cloneMe(double mod) {
		return new RareEarth((int)(this.getValue() * mod));
	}

}
