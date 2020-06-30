package game.planet.buildings;

import static game.settings.BuildingSettings.*;

import java.util.ArrayList;
import java.util.*;
import game.fleet.*;
import game.research.*;

/**
 * The SpacePort of a Planet, higher level = faster production of SpaceShips
 * @author Martin
 *
 */
public class SpacePort extends ABuilding {
	
	ArrayList<ASpaceShip> buildQueue = new ArrayList<ASpaceShip>();
	Date timestamp = new Date();

	public SpacePort(HeadQuarter hq, TechTree techtree, int level) {
		super(hq, techtree, level);
		this.levelMod = SPACEPORT_LEVELMOD;
		this.costs = SPACEPORT_COSTS;
		this.description = SPACEPORT_DESCRIPTION;
	}

	public ArrayList<ASpaceShip> getBuildQueue() {
		return buildQueue;
	}	

	public void setBuildQueue(ArrayList<ASpaceShip> buildQueue) {
		this.buildQueue = buildQueue;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
