package game.ressource;

public abstract class ARessource {
	
	protected int ressourcevalue = 0;

	/** Creates a Ressource with a given int Value
	 * @param ressourcevalue int 
	 */
	public ARessource(int ressourcevalue) {
		this.ressourcevalue = ressourcevalue;
	}

	public int getValue() {
		return ressourcevalue;
	}

	public void setValue(int ressourcevalue) {
		this.ressourcevalue = ressourcevalue;
	}
	
	public abstract ARessource cloneMe(double mod);
	
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String toString() {
		return getName() + ": " + getValue();
	}	
	
	

}
