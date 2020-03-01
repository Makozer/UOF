package game.planet.buildings;

import java.util.*;
import game.ressource.*;
import game.research.*;

public class AResStorageBuilding extends ABuilding {
	
	protected Date 			date = null;
	protected ARessource 	ressource = null;

	public AResStorageBuilding(TechTree techtree, Date date) {
		super(techtree);
	}

	public int getRessource(Date date) {
		return (int)(ressource.getVal() + (((this.date.getTime() - date.getTime()) / 1000.0) * levelMod.getValue(this.techtree.getLevel(this.getName()))));
	}

	public void setRessource(ARessource ressource, Date date) {
		this.date = date;
		this.ressource = ressource;
	}
	
	

}
