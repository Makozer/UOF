package game.planet.buildings.mining;

import java.util.*;
import game.ressource.*;
import game.planet.buildings.ABuilding;
import game.research.*;
import static game.settings.GameSettings.*;

public class AResMiningBuilding extends ABuilding {
	
	protected Date 			date = null;
	protected ARessource 	ressource = null;
	
	public AResMiningBuilding() {
		
	}

	public AResMiningBuilding(TechTree techtree, Date date, ARessource ressource) {
		super(techtree);
		this.date = date;
		this.ressource = ressource;
	}

	
	/** Returns the actual Value of the Ressource
	 * @return int current Ressource Value
	 */
	public int getRessourceValue() {
		return (int)(ressource.getValue() + (((this.date.getTime() - new Date().getTime()) / 1000.0) * GAME_SPEED * levelMod.getValue(this.techtree.getLevel(this.getName()))));
	}

	public void setRessource(ARessource ressource, Date date) {
		this.date = date;
		this.ressource = ressource;
	}
	
	

}
