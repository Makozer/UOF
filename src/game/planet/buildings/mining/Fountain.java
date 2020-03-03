package game.planet.buildings.mining;

import java.util.Date;
import game.research.TechTree;
import game.ressource.*;
import static game.settings.BuildingSettings.*;

public class Fountain extends AResMiningBuilding {

	public Fountain(TechTree techtree, int level, Date date, ARessource ressource) {
		super(techtree, level, date, ressource);
		this.levelMod = FOUNTAIN_LEVELMOD;
		this.costs = FOUNTAIN_COSTS;
	}
	
	public static void main(String[] args) {
		TechTree techtree = new TechTree();
		techtree.testFill();
		int level = 3;
		Date date = new Date();
		Water water = new Water(3000);
		
		Fountain fountain = new Fountain(techtree, level, date, water);
		System.out.println(fountain.toString() + "\n");
		System.out.println(fountain.testLevelMod());
	}

}
