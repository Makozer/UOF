package game.ressource;

public class Tritium extends ARessource {
	
	public Tritium(int ressourcevalue) {
		super(ressourcevalue);
	}

	@Override
	public ARessource cloneMe(double mod) {
		return new Tritium((int)(this.getValue() * mod));
	}

}
