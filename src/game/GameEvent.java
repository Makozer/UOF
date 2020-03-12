package game;

import java.util.*;

import game.fleet.*;
import game.planet.*;
import game.ressource.ARessource;
import game.utils.DateUtils;
import game.utils.NumberUtils;

public class GameEvent implements Comparable<GameEvent> {
	
	public enum Type {
		ATTACK,
		DEFEND,
		TRANSPORT,
		BUILD
	}
	
	private Type 					type = null;	
	private Coordinates 			coordinates = null;
	private Coordinates 			target = null;	
	private String					buildingName = "";
	private Fleet					fleet = null;	
	private ArrayList<ARessource> 	ressource = null; 	
	private Date 					startTime = null;
	private Date 					arrivalTime = null;
	private Date 					endTime = null;	
	
	public GameEvent(Type type, Coordinates coordinates, String buildingName, ArrayList<ARessource> ressource, Date startTime, Date endTime) {
		this.type = type;
		this.coordinates = coordinates;
		this.buildingName = buildingName;
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

	public Type getType() {
		return type;
	}
	
	public String getTypeAsString() {
		return type.toString();
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public Coordinates getTarget() {
		return target;
	}		
	
	public String getBuildingName() {
		return buildingName;
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
	
	public String getRemainingTimeAsString() {
		return DateUtils.getRemainingTimeAsString(endTime);
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
	public int compareTo(GameEvent o) {
		return this.endTime.compareTo(o.getEndTime());
	}

	@Override
	public String toString() {
		return "GameEvent [type=" + type + ", coordinates=" + coordinates + ", target=" + target + ", buildingName="
				+ buildingName + ", fleet=" + fleet + ", ressource=" + ressource + ", startTime=" + startTime
				+ ", arrivalTime=" + arrivalTime + ", endTime=" + endTime + "]";
	}
	
	
	
}
