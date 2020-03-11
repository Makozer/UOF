package game.ressource;

public class Iron extends ARessource {

	public Iron(int ressourcevalue) {
		super(ressourcevalue);
	}

	@Override
	public ARessource cloneMe(double mod) {
		return new Iron((int)(this.getValue() * mod));
	}

	@Override
	public ARessource cloneMe() {
		return new Iron(this.getValue());
	}

}
