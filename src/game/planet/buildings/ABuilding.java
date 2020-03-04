package game.planet.buildings;


import java.util.*;
import game.research.*;
import game.ressource.ARessource;
import game.utils.*;

public abstract class ABuilding {
	
	protected TechTree 					techtree = null;
	protected int						level = 0;
	protected AMath						levelMod = null;
	protected ArrayList<ARessource> 	costs = null;
	
	public ABuilding(TechTree techtree, int level) {
		this.techtree = techtree;
		this.level = level;
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}	
	
	public int getLevel() {
		return this.level;
	}
	
	public void levelUp() {
		this.level += 1;
	}
	
	public void levelUp(Date date) {
		this.level += 1;
		// TODO Message erstellen für User
	}
	
	public double getLevelModValue() {
		return levelMod.getValue(this.level);
	}
	
	public double getLevelModValue(int n) {
		return levelMod.getValue(n);
	}

	public ArrayList<ARessource> getCosts() {
		return costs;
	}	
	
	public String testLevelMod() {
		String output = this.getName() + " levelModValue: \n";
		for (int i = 0; i < 12; i++) {
			output += i + ": \t" + NumberUtils.round2dec(this.getLevelModValue(i)) + ";\n";
		}
		return output;
	}

}
