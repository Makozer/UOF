package game.ressource;

public class RareEarth extends ARessource {

	public RareEarth(int ressourcevalue) {
		super(ressourcevalue);
	}
	
	public RareEarth(String ressourceValue) {
		super(ressourceValue);
	}

	@Override
	public ARessource cloneMe(double mod) {
		return new RareEarth((int)(this.getValue() * mod));
	}
	
	@Override
	public ARessource cloneMe() {
		return new RareEarth(this.getValue());
	}
	
	@Override
	public ARessource cloneMe(int value) {
		return new RareEarth(value);
	}

}
