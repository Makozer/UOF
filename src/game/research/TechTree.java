package game.research;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.*;
import game.fleet.*;
import game.fleet.tier1.Falcon;
import game.research.Research.ResearchEnum;
import game.settings.*;
import game.utils.NumberUtils;

/** This class saves and represents the Players TechTree.
 * @author Martin
 *
 */
public class TechTree {
	
	private ArrayList<Research> 		research 	= new ArrayList<Research>();
	private HashMap<String, Integer> 	levels 		= new HashMap<String, Integer>();
	
	public TechTree() {}
	
	public TechTree(String sql) {
		this.sqlLoad(sql);
	}

	public static void main(String[] args) {
		TechTree techtree = new TechTree();
		techtree.testFill();
		String sql = techtree.asSQLString();
		System.out.println("test");
		System.out.println(sql);
		TechTree tech2 = new TechTree(sql);
		System.out.println("test2");
		System.out.println(tech2.asSQLString());

	}
	
	public void testFill() {
		this.addResearch(new LaserPointer(this));
		this.setLevel("LaserPointer", 11);
		// SpaceShips
		this.setLevel("SpyDrone", 11);
		this.setLevel("Falcon", 11);
		this.setLevel("Cheetah", 11);
		this.setLevel("Yamato", 11);
	}
	
	public void addResearch(Research research) {
		this.research.add(research);
	}
	
	public double getAttack() {
		double output = 0;
		for (Research r: research) {
			if (r.getType() == ResearchEnum.ATTACK) {output += r.getModValue();}
		}
		return output;
	}
	
	public double getDefense() {
		double output = 0;
		for (Research r: research) {
			if (r.getType() == ResearchEnum.DEFEND) {output += r.getModValue();}
		}
		return output;
	}
	
	public double getSpeed() {
		double output = 0;
		for (Research r: research) {
			if (r.getType() == ResearchEnum.SPEED) {output += r.getModValue();}
		}
		return output;
	}
	
	
	public double getCapacity() {
		double output = 0;
		for (Research r: research) {
			if (r.getType() == ResearchEnum.CAPACITY) {output += r.getModValue();}
		}
		return output;
	}
	
	public void setLevel(String name, int n) {
		levels.put(name, n);
	}
	
	public int getLevel(String name) {
		Integer output = levels.get(name);
		if (output == null) {
			return 0;
		}
		return (int)output;
	}
	
	public ASpaceShip getResearchedShip(String shipName) {
		ArrayList<ASpaceShip> 	allShips = this.getAllResearchedShips();	// to know which Ships can be build
		for (ASpaceShip s: allShips) {
			if (shipName.equals(s.getName())) {return s;}
		}
    	return null;
    }
	
	public ArrayList<ASpaceShip> getResearchedSpecialSpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getSpecialShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	public ArrayList<ASpaceShip> getResearchedT1SpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getT1ShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	public ArrayList<ASpaceShip> getResearchedT2SpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getT2ShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	public ArrayList<ASpaceShip> getResearchedT3SpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getT3ShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	public ArrayList<ASpaceShip> getAllResearchedShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		output.addAll(getResearchedSpecialSpaceShips());
		output.addAll(getResearchedT1SpaceShips());
		output.addAll(getResearchedT2SpaceShips());
		output.addAll(getResearchedT3SpaceShips());
		return output;
	}
	
	private void sqlLoad(String sql) {
		String[] levels = sql.split( Pattern.quote( ";" ) );
		String[] techKeyValue = null;
		for (String tech: levels) {
			techKeyValue = tech.split( Pattern.quote( "=" ) );
			this.setLevel(techKeyValue[0], NumberUtils.stringAsInt(techKeyValue[1]));
			// TODO if its a research then add it via Fabric
			// if (this.hasResearched(techKeyValue[0])) { this.addResearch(ResearchFabric.createResearch(this, techKeyValue[0]));}
		}
	}
	
	public String asSQLString() {
		String output = "";
		for (Entry<String, Integer> entry : levels.entrySet()) {
			output += entry.getKey() + "=" + entry.getValue() + ";";
		}
		return output;
	}
		
}
