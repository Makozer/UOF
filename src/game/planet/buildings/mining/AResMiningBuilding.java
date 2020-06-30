package game.planet.buildings.mining;

import java.util.*;
import game.ressource.*;
import game.planet.buildings.ABuilding;
import game.planet.buildings.HeadQuarter;
import game.research.*;
import game.utils.*;
import static game.settings.GameSettings.*;

/**
 * The Class for Ressource producing Buildings
 * @author Martin
 *
 */
public class AResMiningBuilding extends ABuilding {
	
	
	protected ARessource 	ressource = null;

	/**
	 * Constructor of the Ressource Building with given Ressource and Date to know when its last update was
	 * @param hq
	 * @param techtree
	 * @param level
	 * @param date
	 * @param ressource
	 */
	public AResMiningBuilding(HeadQuarter hq, TechTree techtree, int level, Date date, ARessource ressource) {
		super(hq, techtree, level);
		this.date = date;
		this.ressource = ressource;
	}
	
	/**
	 * Updates the Ressource to the current time
	 */
	public void update() {
		this.ressource.setValue(this.getRessourceValue());
		this.date = new Date();
	}

	
	/** Returns the actual Value of the Ressource
	 * @return int current Ressource Value
	 */
	public int getRessourceValue() {
		return (int)(this.ressource.getValue() + (((new Date().getTime() - this.date.getTime()) / 1000.0) * this.getResPerSecond()));
	}
	
	/**
	 * Returns a String with the Value
	 * @return String Ressource as Value
	 */
	public String getResValAsString() {
		return NumberUtils.shortNumber(this.getRessourceValue());
	}
	
	/**
	 * Returns the Ressource generated per Second
	 * Used for Web Interface and calculations
	 * @return Double 
	 */
	public double getResPerSecond() {
		return NumberUtils.doubleTo4dec((this.getLevelModValue() / (60 * 60)) * GAME_SPEED );
	}
	
	/**
	 * Returns the Ressource generated per Hour
	 * @return
	 */
	public int getResPerHour() {
		return (int)(this.getLevelModValue() * GAME_SPEED );
	}

	public void setRessource(ARessource ressource, Date date) {
		this.date = date;
		this.ressource = ressource;
	}


	@Override
	public String toString() {
		return getName() + "\n"	+ "date=" 			+ date + ", \n"
								+ "ressource=" 		+ ressource + ", \n"
								+ "level=" 			+ level + ", \n"
								+ "levelModValue=" 	+ getLevelModValue() + ", \n"
								+ "levelMod=" 		+ levelMod + ", \n"								
								+ "costs=" 			+ costs + ", \n"
								+ "getRessourceValue()=" + getRessourceValue();
	}	

}
