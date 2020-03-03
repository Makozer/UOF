package game.fleet;

import java.util.*;

import game.fleet.tier1.Falcon;
import game.fleet.tier2.Cheetah;
import game.fleet.tier3.Yamato;
import game.planet.*;
import game.research.TechTree;
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
	
	@Override
	public void testFill(TechTree techtree) {
		this.addShips(new Falcon(techtree, 666));
		this.addShips(new Cheetah(techtree, 33));
		this.addShips(new Yamato(techtree, 1));
	}
	
	private void calculateTime() {
		// TODO Zeit Berechnung
		this.arrivalTime = new Date();
		this.endTime = new Date();
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
