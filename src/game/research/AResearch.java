package game.research;

import java.util.ArrayList;

import game.ressource.ARessource;
import game.utils.*;

public abstract class AResearch {	
	
	protected ResearchEnum 			type = null;
	protected int 					level = 0;
	protected AMath 				modification = new Polynomial(1);
	protected ArrayList<ARessource> costs = null;
	
	public AResearch(ResearchEnum type, int level) {
		this.type = type;
		this.level = level;
	}	
	
	public double getValue() {
		return modification.getValue(this.level);
	}
	
	public ArrayList<ARessource> getResearchCosts() {
		ArrayList<ARessource> output = new ArrayList<ARessource>();
		for (ARessource r: costs) {
			output.add(r.cloneMe(modification.getValue(this.level)));			
		}
		return costs;
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String toString() {
		return "AResearch [level=" + level + ", getValue()=" + getValue() + "]";
	}
	
	
	

}
