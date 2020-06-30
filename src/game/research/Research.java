package game.research;

import static game.settings.GameSettings.GAME_SPEED;

import java.util.*;
import game.ressource.ARessource;
import game.utils.*;

/**
 * This Class represents all possible Researches, every Research is a Research ... maybe obvious but seriously! :D
 * @author Martin
 *
 */
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
	
	
	
	/**
	 * Only for tests used, dont use in web application!
	 * @param techtree
	 * @param type
	 */
	public Research(TechTree techtree, ResearchEnum type) {
		this.techtree = techtree;
	}	
	


	/**
	 * Constructor for the Research Class
	 * @param type Enum Type of Research, like Attack Defend etc.
	 * @param name String Research Name
	 * @param techtree TechTree that owns that Research
	 * @param modification AMath to calculate which effect that research has
	 * @param costs ArrayList<ARessource> Array with Costs to Research this Research
	 * @param requiredTech ArrayList<Research> Array with Tech that has to be researched before this becomes available
	 */
	public Research(ResearchEnum type, String name, TechTree techtree, AMath modification, ArrayList<ARessource> costs,
			ArrayList<Research> requiredTech) {
		this.type = type;
		this.name = name;
		this.techtree = techtree;
		this.modification = modification;
		this.costs = costs;
		this.requiredTech = requiredTech;
	}



	/**
	 * Returns the Modification of this Research
	 * Example: Level 1 Attackmod gives 10% more Attack Power to Ships
	 * @return double Modification of the Research
	 */
	public double getModValue() {
		return modification.getValue(this.getLevel());
	}
	
	/**
	 * Returns the Cost to research the next level based on the actual level
	 * @return ArrayList<ARessource> an Array with all Ressources needed to Research the next level
	 */
	public ArrayList<ARessource> getResearchCosts() {
		ArrayList<ARessource> output = new ArrayList<ARessource>();
		for (ARessource r: costs) {
			output.add(r.cloneMe(modification.getValue(this.getLevel())));			
		}
		return costs;
	}	
	
	/**
	 * Uses the given Costs that the Research needs to be researched to the next level to calculate the time needed to reach the next level
	 * @return int seconds to research
	 */
	public int getTimeToResearch() {
		int timeToBuild = 0;
		for (ARessource r : this.getResearchCosts()) {
			timeToBuild += r.getValue();
		}
		return timeToBuild * 10;
	}
	
	/**
	 * Used to calculate how much time is needed to research something with a given University level
	 * @param universitylevel
	 * @return int seconds to research
	 */
	public int getTimeToResearch(int universitylevel) {	
		int output = 1;
		output += 	this.getTimeToResearch();
		output += 	(int)(this.getTimeToResearch() / 100.0 * this.getModValue());
		output -= 	(int)(this.getTimeToResearch() / 100.0 * (universitylevel * 2));
		output = 	(int)(output / GAME_SPEED);
		return output;
	}
	
	/**
	 * Used for Web Interface to Display how many time is needed to research something
	 * @param universitylevel the level of the University
	 * @return String The time thats needed to research something with a given University Level
	 */
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

	/**
	 * Setter to set the required Tech
	 * @param requiredTech ArrayList<Research> an Array with required Techs
	 */
	public void setRequiredTech(ArrayList<Research> requiredTech) {
		this.requiredTech = requiredTech;
	}


}
