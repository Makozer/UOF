package game.planet.buildings;

import java.util.Date;

import game.research.*;
import game.ressource.*;
import game.utils.*;

public class IronMine extends AResMiningBuilding {

	public IronMine() {
		
	}
	
	public IronMine(TechTree techtree, Date date, ARessource ressource) {
		super(techtree, date, ressource);
	}
	
	
	public void testFill(TechTree techtree) {
		techtree.setLevel(this.getName(), 11);
		
		this.date = new Date();
		this.levelMod = new Polynomial(1,0,0);
		this.ressource = new Iron(3333);
		this.techtree = techtree;
	}

}
