package game.ressource;

public class Iron extends ARessource {

	public Iron(int ressourcevalue) {
		super(ressourcevalue);
	}
	
	public Iron(String ressourceValue) {
		super(ressourceValue);
	}

	@Override
	public ARessource cloneMe(double mod) {
		return new Iron((int)(this.getValue() * mod));
	}

	@Override
	public ARessource cloneMe() {
		return new Iron(this.getValue());
	}

	@Override
	public ARessource cloneMe(int value) {
		return new Iron(value);
	}	

}
