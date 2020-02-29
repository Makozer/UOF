package game.planet.ressource;

public abstract class ARessource {
	
	protected int ressourcevalue = 0;

	public ARessource(int ressourcevalue) {
		this.ressourcevalue = ressourcevalue;
	}

	public int getVal() {
		return ressourcevalue;
	}

	public void setVal(int ressourcevalue) {
		this.ressourcevalue = ressourcevalue;
	}
	
	
	

}
