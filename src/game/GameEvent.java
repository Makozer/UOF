package game;

import java.util.*;

import game.fleet.*;
import game.planet.*;
import game.ressource.ARessource;

public class GameEvent implements Comparable {
	
	enum Type {
		ATTACK,
		DEFEND,
		TRANSPORT,
		BUILD
	}
	
	private Type 					type = null;	
	private Coordinates 			coordinates = null;
	private Coordinates 			target = null;	
	private Fleet					fleet = null;	
	private ArrayList<ARessource> 	ressource = null; 	
	private Date 					startTime = null;
	private Date 					arrivalTime = null;
	private Date 					endTime = null;	
	
	public GameEvent(Type type, Coordinates coordinates, ArrayList<ARessource> ressource, Date startTime, Date endTime) {
		this.type = type;
		this.coordinates = coordinates;
		this.ressource = ressource;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public GameEvent(Type type, Coordinates coordinates, Coordinates target, Fleet fleet, ArrayList<ARessource> ressource, Date startTime, Date endTime) {
		this.type = type;
		this.coordinates = coordinates;
		this.target = target;
		this.fleet = fleet;
		this.ressource = ressource;
		this.startTime = startTime;		
		this.endTime = endTime;
		calculateTime();
	}
	
	private void calculateTime() {
		this.arrivalTime = new Date(new Date().getTime() + (this.endTime.getTime() - this.startTime.getTime() / 2));
	}

	public String getType() {
		return type.toString();
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public Coordinates getTarget() {
		return target;
	}	
	
	public Fleet getFleet() {
		return fleet;
	}

	public ArrayList<ARessource> getRessource() {
		return ressource;
	}

	public Date getRemainingTime() {
		return new Date(endTime.getTime() - new Date().getTime());
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

	@Override
	public int compareTo(Object o) {
		int output = 0;
		GameEvent other = (GameEvent) o;		
		return this.endTime.compareTo(other.getEndTime());
	}
	
}
