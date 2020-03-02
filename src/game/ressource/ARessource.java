package game.ressource;

public abstract class ARessource {
	
	protected int ressourcevalue = 0;
	
	public ARessource() {
		
	}

	public ARessource(int ressourcevalue) {
		this.ressourcevalue = ressourcevalue;
	}

	public int getVal() {
		return ressourcevalue;
	}

	public void setVal(int ressourcevalue) {
		this.ressourcevalue = ressourcevalue;
	}
	
	public abstract ARessource cloneMe(double mod);
	
	public String getName() {
		return this.getClass().getSimpleName();
	}	
	

}
