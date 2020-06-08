package game.event;

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
		BUILD,
		RESEARCH
	}
	
	private int						id = 0;
	private int						playerid = 0;
	private int						targetplayerid = 0;
	private Type 					type = null;	
	private Coordinates 			coordinates = new Coordinates(0, 0, 0);
	private Coordinates 			target = new Coordinates(0, 0, 0);	
	private String					buildingName = "";
	private Fleet					fleet = new Fleet();	
	private ArrayList<ARessource> 	ressource = new ArrayList<ARessource>(); 	
	private Date 					startTime = new Date();
	private Date 					arrivalTime = new Date();
	private Date 					endTime = new Date();	
	
	public GameEvent() {
		// ONLY FOR TESTING!!!
	}
	
	public GameEvent(int playerid, Type type, Coordinates coordinates, String buildingName, ArrayList<ARessource> ressource, Date startTime, Date endTime) {
		this.playerid = playerid; 
		this.type = type;
		this.coordinates = coordinates;
		this.buildingName = buildingName;
		this.ressource = ressource;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public GameEvent(int id, int playerid, int toplayerid, Type type, Coordinates coordinates, Coordinates target, String buildingName, Fleet fleet, ArrayList<ARessource> ressource, Date startTime, Date endTime) {
		this.id = id;
		this.playerid = playerid;
		this.targetplayerid = toplayerid;
		this.type = type;
		this.coordinates = coordinates;
		this.target = target;
		this.buildingName = buildingName;
		this.fleet = fleet;
		this.ressource = ressource;
		this.startTime = startTime;		
		this.endTime = endTime;
	}	

	public void setType(Type type) {
		this.type = type;
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
		if (this.fleet == null) {return new Fleet();}
		return fleet;
	}

	public ArrayList<ARessource> getRessource() {
		if (this.fleet == null) {return new ArrayList<ARessource>();}
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerid() {
		return playerid;
	}

	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}

	public int getTargetplayerid() {
		return targetplayerid;
	}

	public void setTargetplayerid(int toplayerid) {
		this.targetplayerid = toplayerid;
	}

	@Override
	public String toString() {
		return "GameEvent [id=" + id + ", playerid=" + playerid + ", targetplayerid=" + targetplayerid + ", type="
				+ type + ", coordinates=" + coordinates + ", target=" + target + ", buildingName=" + buildingName
				+ ", fleet=" + fleet + ", ressource=" + ressource + ", startTime=" + startTime + ", arrivalTime="
				+ arrivalTime + ", endTime=" + endTime + "]";
	}	

}
