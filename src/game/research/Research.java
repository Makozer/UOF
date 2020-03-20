package game.research;

import static game.settings.GameSettings.GAME_SPEED;

import java.util.*;
import game.ressource.ARessource;
import game.utils.*;

public class Research {	
	
	public enum ResearchEnum {		
		ATTACK,
		DEFEND,
		SPEED,
		CAPACITY,
		SHIP
	}
	
	protected ResearchEnum 				type 			= null;
	protected String					name			= "";
	protected TechTree					techtree 		= null;
	protected AMath 					modification 	= null;
	protected ArrayList<ARessource> 	costs 			= null;
	protected ArrayList<Research> 		requiredTech 	= null;
	
	public Research(TechTree techtree, ResearchEnum type) {
		this.techtree = techtree;
	}	
	


	public Research(ResearchEnum type, String name, TechTree techtree, AMath modification, ArrayList<ARessource> costs,
			ArrayList<Research> requiredTech) {
		this.type = type;
		this.name = name;
		this.techtree = techtree;
		this.modification = modification;
		this.costs = costs;
		this.requiredTech = requiredTech;
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
	
	public int getTimeToResearch() {
		int timeToBuild = 0;
		for (ARessource r : this.getResearchCosts()) {
			timeToBuild += r.getValue();
		}
		return timeToBuild;
	}
	
	public int getTimeToResearch(int universitylevel) {	
		int output = 1;
		output += 	this.getTimeToResearch();
		output -= 	(int)(this.getTimeToResearch() / 100.0 * this.getModValue());
		output -= 	(int)(this.getTimeToResearch() / 100.0 * (universitylevel * 2));
		output = 	(int)(output / GAME_SPEED);
		return output;
	}
	
	public String getTimeToResearchAsString(int universitylevel) {
		return DateUtils.getRemainingTimeAsString(new Date(new Date().getTime() 
				+ (long)(this.getTimeToResearch(universitylevel) * 1000)			
					));
	}
	
	public ResearchEnum getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		// TODO
		return "";
	}
	
	public int getLevel() {
		return this.techtree.getLevel(this.getName());
	}

	@Override
	public String toString() {
		return "AResearch [level=" + this.getLevel() + ", getValue()=" + getModValue() + "]";
	}


	public ArrayList<Research> getRequiredTech() {
		return requiredTech;
	}

	public void setRequiredTech(ArrayList<Research> requiredTech) {
		this.requiredTech = requiredTech;
	}


}
