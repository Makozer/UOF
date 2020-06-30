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
 * It stores all Research and levels and manages everything related to Researchs
 * @author Martin
 *
 */
public class TechTree {
	
	private ArrayList<Research> 		research 	= new ArrayList<Research>();
	private HashMap<String, Integer> 	levels 		= new HashMap<String, Integer>();
	
	public TechTree() {}
	
	
	/**
	 * With a given String the techtree is able to build itself 
	 * @param sql String that String from DataBase
	 */
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
	
	/**
	 * Adds a Research to the TechTree
	 * @param research
	 */
	public void addResearch(Research research) {
		this.research.add(research);
	}
	
	/**
	 * Unused
	 * @return
	 */
	public double getAttack() {
		double output = 0;
		for (Research r: research) {
			if (r.getType() == ResearchEnum.ATTACK) {output += r.getModValue();}
		}
		return output;
	}
	
	/**
	 * Unused
	 * @return
	 */
	public double getDefense() {
		double output = 0;
		for (Research r: research) {
			if (r.getType() == ResearchEnum.DEFEND) {output += r.getModValue();}
		}
		return output;
	}
	
	/**
	 * Unused
	 * @return
	 */
	public double getSpeed() {
		double output = 0;
		for (Research r: research) {
			if (r.getType() == ResearchEnum.SPEED) {output += r.getModValue();}
		}
		return output;
	}
	
	
	/**
	 * Unused
	 * @return
	 */
	public double getCapacity() {
		double output = 0;
		for (Research r: research) {
			if (r.getType() == ResearchEnum.CAPACITY) {output += r.getModValue();}
		}
		return output;
	}
	
	/**
	 * Sets to a given String Name a level
	 * @param name The Name that needs a Level to be Set
	 * @param n int the Level as a whole Number
	 */
	public void setLevel(String name, int n) {
		levels.put(name, n);
	}
	
	
	/**
	 * Returns the Level of a given Ship or Research.
	 * If its not there, return is 0
	 * @param name of the Research / Ship
	 * @return int level of the Research / Ship
	 */
	public int getLevel(String name) {
		Integer output = levels.get(name);
		if (output == null) {
			return 0;
		}
		return (int)output;
	}
	
	/**
	 * Returns the whole Researchlist
	 * @return
	 */
	public ArrayList<Research> getWholeResearch() {
		return ResearchRegister.getWholeResearchList(this);
	}

	/**
	 * Returns a researched SpaceShips as a Ship
	 * @param shipName String the name of the Ships 
	 * @return ASpaceShip if its researched then the ship, else null
	 */
	public ASpaceShip getResearchedShip(String shipName) {
		ArrayList<ASpaceShip> 	allShips = this.getAllResearchedShips();	// to know which Ships can be build
		for (ASpaceShip s: allShips) {
			if (shipName.equals(s.getName())) {return s;}
		}
    	return null;
    }

	/**
	 * Returns all already researched Special Ships
	 * @return ArrayList<ASpaceShip> with the researched Ships
	 */
	public ArrayList<ASpaceShip> getResearchedSpecialShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getSpecialShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}

	/**
	 * Returns all already researched T1 Ships
	 * @return ArrayList<ASpaceShip> with the researched Ships
	 */
	public ArrayList<ASpaceShip> getResearchedT1SpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getT1ShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	/**
	 * Returns all already researched T2 Ships
	 * @return ArrayList<ASpaceShip> with the researched Ships
	 */
	public ArrayList<ASpaceShip> getResearchedT2SpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getT2ShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}
	
	/**
	 * Returns all already researched T3 Ships
	 * @return ArrayList<ASpaceShip> with the researched Ships
	 */
	public ArrayList<ASpaceShip> getResearchedT3SpaceShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		for (ASpaceShip s:ShipRegister.getT3ShipList(this)) {
			if (this.getLevel(s.getName()) > 0) { output.add(s);}
		}
		return output;
	}

	/**
	 * Returns all already researched Ships
	 * @return ArrayList<ASpaceShip> with the researched Ships
	 */
	public ArrayList<ASpaceShip> getAllResearchedShips() {
		ArrayList<ASpaceShip> output = new ArrayList<ASpaceShip>();
		output.addAll(getResearchedSpecialShips());
		output.addAll(getResearchedT1SpaceShips());
		output.addAll(getResearchedT2SpaceShips());
		output.addAll(getResearchedT3SpaceShips());
		return output;
	}
	
	/**
	 * Returns all already Researched Propulsion Researchs
	 * @return ArrayList<Research>
	 */
	public ArrayList<Research> getResearchedPropulsion() {
		ArrayList<Research> propulsion = new ArrayList<Research>();
		for (Research r : ResearchRegister.getPropulsionResearch(this)) {
			if (r.getRequiredTech().size() == 0) {
				propulsion.add(r);
			} else {
				// when something is required, check if its there
				for (Research rq : r.getRequiredTech()) {
					if (1 > this.getLevel(rq.getName())) {
						break;
					} else {
						// TODO Lowprio wont work if there are more required techs then one
						propulsion.add(r);
					}
				}
			}
		}
		return propulsion;
	}
	
	/**
	 * Returns all researchAble Special Ship Researches that possibly could be researched
	 * @return ArrayList<Research>
	 */
	public ArrayList<Research> getResearchSpecialShips() {
		ArrayList<Research> specials = new ArrayList<Research>();
		boolean check = true;
		for (Research r : ResearchRegister.getSpecialShipResearch(this)) {
			if (r.getRequiredTech().size() == 0) {
				specials.add(r);
			} else {
				check = true;
				for (Research req : r.getRequiredTech()) {
					if (!(this.getLevel(req.getName()) > 0)) { check = false; break; }
				}	
				if (check) { specials.add(r); }
			}
		}
		return specials;
	}
	
	/**
	 * Returns all researchAble T1 Ship Researches that possibly could be researched
	 * @return ArrayList<Research>
	 */
	public ArrayList<Research> getResearchT1Ships() {
		ArrayList<Research> t1ships = new ArrayList<Research>();
		boolean check = true;
		for (Research r : ResearchRegister.getShipT1Research(this)) {
			if (r.getRequiredTech().size() == 0) {
				t1ships.add(r);
			} else {
				check = true;
				for (Research req : r.getRequiredTech()) {
					if (!(this.getLevel(req.getName()) > 0)) { check = false; break; }
				}	
				if (check) { t1ships.add(r); }
			}
		}
		return t1ships;
	}
	
	/**
	 * Returns all researchAble T2 Ship Researches that possibly could be researched
	 * @return ArrayList<Research>
	 */
	public ArrayList<Research> getResearchT2Ships() {
		ArrayList<Research> t1ships = new ArrayList<Research>();
		boolean check = true;
		for (Research r : ResearchRegister.getShipT2Research(this)) {
			if (r.getRequiredTech().size() == 0) {
				t1ships.add(r);
			} else {
				check = true;
				for (Research req : r.getRequiredTech()) {
					if (!(this.getLevel(req.getName()) > 0)) { check = false; break; }
				}	
				if (check) { t1ships.add(r); }
			}
		}
		return t1ships;
	}
	
	
	/**
	 * Returns all researchAble T3 Ship Researches that possibly could be researched
	 * @return ArrayList<Research>
	 */
	public ArrayList<Research> getResearchT3Ships() {
		ArrayList<Research> t1ships = new ArrayList<Research>();
		boolean check = true;
		for (Research r : ResearchRegister.getShipT3Research(this)) {
			if (r.getRequiredTech().size() == 0) {
				t1ships.add(r);
			} else {
				check = true;
				for (Research req : r.getRequiredTech()) {
					if (!(this.getLevel(req.getName()) > 0)) { check = false; break; }
				}	
				if (check) { t1ships.add(r); }
			}
		}
		return t1ships;
	}
	
	/**
	 * With a given SQL Database String the TechTree is able to load itself
	 * @param sql String from DataBase
	 */
	private void sqlLoad(String sql) {
		String[] levels = sql.split( Pattern.quote( ";" ) );
		String[] techKeyValue = null;
		if (levels.length < 1) {return;}
		for (String tech: levels) {
			techKeyValue = tech.split( Pattern.quote( "=" ) );
			if (techKeyValue.length < 2) {return;}
			this.setLevel(techKeyValue[0], NumberUtils.stringAsInt(techKeyValue[1]));
			// TODO if its a research then add it via Fabric
			// if (this.hasResearched(techKeyValue[0])) { this.addResearch(ResearchFabric.createResearch(this, techKeyValue[0]));}
		}
	}
	
	/**
	 * Converts a TechTree to a SQL String thats stored in the Database
	 * @return String SQL
	 */
	public String asSQLString() {
		String output = "";
		for (Entry<String, Integer> entry : levels.entrySet()) {
			output += entry.getKey() + "=" + entry.getValue() + ";";
		}
		return output;
	}
		
}
