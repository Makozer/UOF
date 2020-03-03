package game.planet.buildings.mining;

import java.util.*;
import game.ressource.*;
import game.planet.buildings.ABuilding;
import game.research.*;
import game.utils.*;
import static game.settings.GameSettings.*;

public class AResMiningBuilding extends ABuilding {
	
	protected Date 			date = null;
	protected ARessource 	ressource = null;

	public AResMiningBuilding(TechTree techtree, int level, Date date, ARessource ressource) {
		super(techtree, level);
		this.date = date;
		this.ressource = ressource;
	}

	
	/** Returns the actual Value of the Ressource
	 * @return int current Ressource Value
	 */
	public int getRessourceValue() {
		return (int)(ressource.getValue() + (((new Date().getTime() - this.date.getTime()) / 1000.0) * GAME_SPEED * levelMod.getValue(this.level)));
	}
	
	public int getResPerHour() {
		return (int)(((DateUtils.getDate(2020, 3, 11, 12).getTime() - DateUtils.getDate(2020, 3, 11, 11).getTime()) / 1000.0) * GAME_SPEED * levelMod.getValue(this.level));
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
