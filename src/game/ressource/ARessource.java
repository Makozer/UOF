package game.ressource;

import game.utils.NumberUtils;

/**
 * This Class represents the Abstract Ressource in the Game.
 * Its used for all Kinds like Costs to build a ship or the Storage on a Planet.
 * @author Martin
 *
 */
public abstract class ARessource {
	
	protected int ressourcevalue = 0;

	/** Creates a Ressource with a given int Value
	 * @param ressourcevalue int 
	 */
	public ARessource(int ressourceValue) {
		this.ressourcevalue = ressourceValue;
	}
	
	public ARessource(String ressourceValue) {
		this.setValUsingString(ressourceValue);
	}

	public int getValue() {
		return ressourcevalue;
	}

	public void setValue(int ressourcevalue) {
		this.ressourcevalue = ressourcevalue;
	}
	
	public void setValUsingString(String number) {
		try {
			this.ressourcevalue = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			this.ressourcevalue = 0;
		}
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
	public abstract ARessource cloneMe(int value);
	public abstract ARessource cloneMe(double multiply);
	
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	/**
	 * Uses its Value as a String in the Web Interface
	 * @return String Value
	 */
	public String getValAsString() {
		return NumberUtils.shortNumber(ressourcevalue);
	}

	@Override
	public String toString() {
		return getName() + ": " + getValue();
	}	
	
	

}
