package game.ressource;

public class Water extends ARessource {

	public Water(int ressourcevalue) {
		super(ressourcevalue);
	}

	@Override
	public ARessource cloneMe(double mod) {
		return new Water((int)(this.getValue() * mod));
	}
	
	@Override
	public ARessource cloneMe() {
		return new Water(this.getValue());
	}

}
