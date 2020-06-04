package game.planet.buildings.mining;

import java.util.*;
import game.ressource.*;
import game.planet.buildings.ABuilding;
import game.planet.buildings.HeadQuarter;
import game.research.*;
import game.utils.*;
import static game.settings.GameSettings.*;

public class AResMiningBuilding extends ABuilding {
	
	
	protected ARessource 	ressource = null;

	public AResMiningBuilding(HeadQuarter hq, TechTree techtree, int level, Date date, ARessource ressource) {
		super(hq, techtree, level);
		this.date = date;
		this.ressource = ressource;
	}
	
	public void update() {
		this.ressource.setValue(this.getRessourceValue());
		this.date = new Date();
	}

	
	/** Returns the actual Value of the Ressource
	 * @return int current Ressource Value
	 */
	public int getRessourceValue() {
		return (int)(this.ressource.getValue() + (((new Date().getTime() - this.date.getTime()) / 1000.0) * GAME_SPEED * this.getResPerSecond()));
	}
	
	public String getResValAsString() {
		return NumberUtils.shortNumber(this.getRessourceValue());
	}
	
	public double getResPerSecond() {
		return NumberUtils.doubleTo4dec(this.getLevelModValue() / (60 * 60) );
	}
	
	public int getResPerHour() {
		return (int)this.getLevelModValue();
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
