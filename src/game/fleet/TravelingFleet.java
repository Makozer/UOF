package game.fleet;

import java.util.*;

import game.planet.*;
import game.ressource.*;

public class TravelingFleet extends Fleet {
	
	private Coordinates target = null;
	private Date startTime = null;
	private Date arrivalTime = null;
	private Date endTime = null;	
	
	private ArrayList<ARessource> ressources = new ArrayList<ARessource>();
	
	public TravelingFleet(Coordinates target, Date startTime) {
		super();
		this.target = target;
		this.startTime = startTime;
		calculateTime();
	}

	public TravelingFleet(Coordinates target, Date startTime, ArrayList<ARessource> ressources) {
		super();
		this.target = target;
		this.startTime = startTime;
		this.ressources = ressources;
		calculateTime();
	}
	
	private void calculateTime() {
		
	}

	public Coordinates getTarget() {
		return target;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public Date getEndTime() {
		return endTime;
	}
	
	

}
