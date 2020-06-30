package game.fleet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import game.fleet.special.*;
import game.fleet.tier1.*;
import game.fleet.tier2.*;
import game.fleet.tier3.*;
import game.research.*;
import game.utils.NumberUtils;

/**
 * The Fleet is the Class which stores SpaceShips
 * Used everywhere as traveling Fleet or as stationed Fleet on a Planet
 * @author Martin
 *
 */
public class Fleet implements Iterable<ASpaceShip> {
	
	protected ArrayList<ASpaceShip> fleet = new ArrayList<ASpaceShip>();
	protected int 					playerId = 0;
	
	public Fleet() {}
	
	/**
	 * Used to create a Fleet with a SQL String from DataBase
	 * @param techtree
	 * @param sql
	 */
	public Fleet(TechTree techtree, String sql) {
		this.sqlLoad(techtree, sql);
	}

	public static void main(String[] args) {
		
	}
	
	public int getAttack() {
		double output = 0;
		for (ASpaceShip s: fleet) {
			output += s.getAttack() * s.getQuantity();
		}
		return (int)output;
	}
	
	public int getDefense() {
		double output = 0;
		for (ASpaceShip s: fleet) {
			output += s.getDefense() * s.getQuantity();
		}
		return (int)output;
	}
	
	
	/** getSpeed returns the slowest shipspeed in the fleet
	 * @return fleet speed
	 */
	public int getSpeed() {
		double output = 999999;
		for (ASpaceShip s: fleet) {
			if (s.getSpeed() < output) {
				output = s.getSpeed();
			}
		}
		return (int)output;
	}
	
	public int size() {
		return this.fleet.size();
	}
	
	public int getCapacy() {
		int output = 0;
		for (ASpaceShip s: fleet) {
			output += s.getCapacity() * s.getQuantity();
		}
		return output;
	}
	
	public void addShip(ASpaceShip ship) {
		boolean found = false;
		for (ASpaceShip s: this.getFleet()) {
			if (s.getName().equals(ship.getName())) {
				s.addQuantity(ship.getQuantity());
				found = true;
				break;
			}
		}
		if (!found) { fleet.add(ship); }		
	}
	
	public boolean hasShip(ASpaceShip ship) {
		for (ASpaceShip fship: fleet) {
			if (fship.getName().equals(ship.getName()) && fship.getQuantity() >= ship.getQuantity()) { return true; }
		}
		return false;
	}
	
	public void removeShips(ASpaceShip ship) {
		fleet.remove(ship);
	}
	
	public void setFleet(Fleet fleet) {
		this.fleet = fleet.getFleet();
	}
	
	/**
	 * Adds another Fleet to this existing Fleet
	 * @param fleet
	 */
	public void addFleet(Fleet fleet) {
		ASpaceShip thisShip = null;
		ASpaceShip addShip = null;
		boolean found = false;
		for (int i = 0; i < fleet.getFleet().size(); i++) {
			addShip = fleet.getFleet().get(i);
			found = false;
			for (int j = 0; j < this.fleet.size(); j++) {
				thisShip = this.fleet.get(j);
				if (thisShip.getName() == addShip.getName()) {
					thisShip.addQuantity(addShip.getQuantity());
					found = true;
					break;
				}
				if (j == this.fleet.size() - 1 && found == false) {
					this.fleet.add(addShip);
				}
			}
		}
	}
	
	public ArrayList<ASpaceShip> getFleet() {
		return this.fleet;
	}
	
	public boolean hasFleet(Fleet fleet) {
		for (ASpaceShip ship: fleet) {
			if (!hasShip(ship)) { return false;}
		}		
		return true;
	}
	
	/**
	 * Reduces this fleet by another Fleet
	 * @param fleet
	 */
	public void reduceFleet(Fleet fleet) {
		ASpaceShip thisShip = null;
		ASpaceShip reduceShip = null;
		boolean found = false;
		for (int i = 0; i < fleet.getFleet().size(); i++) {
			reduceShip = fleet.getFleet().get(i);
			found = false;
			for (int j = 0; j < this.fleet.size(); j++) {
				thisShip = this.fleet.get(j);
				if (thisShip.getName() == reduceShip.getName()) {
					thisShip.reduceQuantity(reduceShip.getQuantity());
					found = true;
					break;
				}
				if (j == this.fleet.size() - 1 && found == false) {
					throw new IllegalArgumentException("fleet.reduceFleet(" + reduceShip.toString() + ")Ship cant be removed!");
				}
			}
		}
	}
	
	public void reduceFleetByPct(double pct) {
		for (ASpaceShip ship: fleet) {
			ship.reduceQuantity(
					(int)(ship.getQuantity() / 100.0 * pct)
					);
		}
	}
	
	public long getCombatPower() {
		return this.getAttack() + this.getDefense();
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public Fleet clone() {
		Fleet clone = new Fleet();
		for (ASpaceShip ship : this.fleet) {
			clone.addShip(ship.cloneMe());
		}
		return clone;
	}

	@Override
	public String toString() {
		return "Fleet [getAttack()=" + getAttack() + ", getDefense()=" + getDefense() 
				+ ", getSpeed()=" + getSpeed() + ", getCapacy()=" + getCapacy() + "]";
	}

	@Override
	public Iterator<ASpaceShip> iterator() {
		return fleet.iterator();
	}
	
	/**
	 * Loads a Fleet with a given SQL String
	 * @param techtree
	 * @param sql
	 */
	private void sqlLoad(TechTree techtree, String sql) {
		if (sql == null || sql.length() == 0) {return;}
		String[] ships = sql.split( Pattern.quote( ";" ) );		
		String[] shipKeyValue = null;
		for (String tech: ships) {
			shipKeyValue = tech.split( Pattern.quote( "=" ) );
			this.addShip(ShipFabric.createShip(techtree, shipKeyValue[0], NumberUtils.stringAsInt(shipKeyValue[1])));
		}
	}
	
	public String asSQLString() {
		String output = "";
		for (ASpaceShip ship : fleet) {
			output += ship.getName() + "=" + ship.getQuantity() + ";";
		}
		return output;
	}
	
	public void clear() {
		this.fleet.clear();
	}

}
