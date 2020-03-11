package game.ressource;

import game.utils.NumberUtils;

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
	
	public void increaseValue(int n) {
		this.ressourcevalue += n;
	}
	
	public void multiplyValue(double m) {
		this.ressourcevalue = (int)(ressourcevalue * m);
	}
	
	public void decreaseValue(int n) {
		this.ressourcevalue -= n;
	}
	
	public abstract ARessource cloneMe();
	public abstract ARessource cloneMe(double multiply);
	
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public String getValAsString() {
		return NumberUtils.shortNumber(ressourcevalue);
	}

	@Override
	public String toString() {
		return getName() + ": " + getValue();
	}	
	
	

}
