package game.planet.buildings;


import java.util.*;
import game.research.*;
import game.ressource.ARessource;
import game.utils.*;
import static game.settings.GameSettings.*;

/**
 * Abstract Class of all Buildings with nearly all needed methods
 * @author Martin
 *
 */
public abstract class ABuilding {
	
	protected TechTree 					techtree = null;
	protected HeadQuarter				headQuarter = null;
	protected int						level = 0;
	protected AMath						levelMod = null;
	protected ArrayList<ARessource> 	costs = null;
	protected String					description = "";
	protected Date 						date = null;
	
	/**
	 * Constructor, give me an HeadQuarter, a TechTree and my level :)
	 * @param hq
	 * @param techtree
	 * @param level
	 */
	public ABuilding(HeadQuarter hq, TechTree techtree, int level) {
		this.headQuarter = hq;
		this.techtree = techtree;
		this.level = level;
	}	
	
	/**
	 * Increases the level + 1
	 */
	public void levelUp() {
		this.level += 1;
	}
	
	/**
	 * Increases the level with a Timestamp
	 * @param date Timestamp
	 */
	public void levelUp(Date date) {
		this.date = date;
		this.level += 1;
	}
	
	/**
	 * Returns how much this building modifys the value with its given level
	 * @return
	 */
	public double getLevelModValue() {
		return levelMod.getValue(this.level);
	}
	
	/**
	 * Test Method to know how much it would modify with a given level
	 * @param n level
	 * @return double modification
	 */
	public double getLevelModValue(int n) {
		return levelMod.getValue(n);
	}

	/**
	 * Gets the Basic Costs
	 * @return ArrayList<ARessource> 
	 */
	public ArrayList<ARessource> getCosts() {
		return costs;
	}	
	
	public HeadQuarter getHeadQuarter() {
		return headQuarter;
	}

	public void setHeadQuarter(HeadQuarter headQuarter) {
		this.headQuarter = headQuarter;
	}

	public ArrayList<ARessource> getBuildCosts() {
		return getBuildCosts(this.level);
	}
	
	/** 
	 * Gets the Costs to upgrade this Building based on its basic Costs and level
	 * @param level of the Building
	 * @return ArrayList<ARessource>
	 */
	public ArrayList<ARessource> getBuildCosts(int level) {
		ArrayList<ARessource> output = new ArrayList<ARessource>();
		double mods = this.getLevelModValue(level - 1);
		double mod = (mods / 2) * (mods / 66);
		for (ARessource r: costs) {
			output.add(
					r.cloneMe(
							1 + (mod / 66)
							)
					);			
		}
		return output;
	}
	
	/** Returns the time needed to build this building
	 * @return int time to build in seconds
	 */
	public long getTimeToBuild() {
		return getTimeToBuild(this.level);
	}
	
	/** 
	 * Returns the time needed to build this building with given level
	 * @param level
	 * @return int time to build in seconds
	 */
	public long getTimeToBuild(int level) {
		long combRessCost = 0;
		for (ARessource r: this.getBuildCosts(level)) {
			combRessCost += r.getValue();
		}
		return (long)(((combRessCost * 6.6) / 100.0 * (100.0 - this.getHeadQuarter().getLevelModValue())) / GAME_SPEED);
	}
	
	/**
	 * Converts remaining time to String
	 * Used for Web Interface
	 * @return
	 */
	public String getTimeToBuildAsString() {
		return DateUtils.getRemainingTimeAsString(new Date(new Date().getTime() 
			+ (long)(this.getTimeToBuild() * 1000)			
				));
	}
	
	/**
	 * Converts remaining time to String with a given level
	 * Used for Web Interface
	 * @return
	 */
	public String getTimeToBuildAsString(int level) {
		return DateUtils.getRemainingTimeAsString(new Date(new Date().getTime() 
			+ (long)(this.getTimeToBuild(level) * 1000)			
				));
	}
	
	public String testLevelMod() {
		String output = this.getName() + " levelModValue: \n";
		for (int i = 0; i < 12; i++) {
			output += i + ": \t" + NumberUtils.round2decToString(this.getLevelModValue(i)) + ";\n";
		}
		return output;
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}
		
	public String getDescription() {
		return description;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return this.level;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

}
