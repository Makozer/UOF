package game.research;

import java.util.*;
import game.ressource.ARessource;
import game.utils.*;

public class Research {	
	
	public enum ResearchEnum {		
		ATTACK,
		DEFEND,
		SPEED,
		CAPACITY
	}
	
	protected ResearchEnum 			type = null;
	protected TechTree				techtree = null;
	protected AMath 				modification = null;
	protected ArrayList<ARessource> costs = null;
	
	public Research(TechTree techtree, ResearchEnum type) {
		this.techtree = techtree;
	}	
	
	public double getModValue() {
		return modification.getValue(this.getLevel());
	}
	
	public ArrayList<ARessource> getResearchCosts() {
		ArrayList<ARessource> output = new ArrayList<ARessource>();
		for (ARessource r: costs) {
			output.add(r.cloneMe(modification.getValue(this.getLevel())));			
		}
		return costs;
	}	
	
	public ResearchEnum getType() {
		return type;
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public int getLevel() {
		return this.techtree.getLevel(this.getName());
	}

	@Override
	public String toString() {
		return "AResearch [level=" + this.getLevel() + ", getValue()=" + getModValue() + "]";
	}
	
	
	

}
