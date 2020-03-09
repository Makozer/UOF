package game.planet.buildings;

import static game.settings.BuildingSettings.*;

import java.util.ArrayList;
import java.util.*;
import game.fleet.*;
import game.research.*;

public class SpacePort extends ABuilding {
	
	ArrayList<ASpaceShip> buildQueue = new ArrayList<ASpaceShip>();
	Date timestamp = new Date();

	public SpacePort(TechTree techtree, int level) {
		super(techtree, level);
		this.levelMod = SPACEPORT_LEVELMOD;
		this.costs = SPACEPORT_COSTS;
		this.description = SPACEPORT_DESCRIPTION;
	}

	public ArrayList<ASpaceShip> getBuildQueue() {
		return buildQueue;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
