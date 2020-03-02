package game.planet;

import java.util.*;

import game.fleet.*;
import game.fleet.tier1.*;
import game.fleet.tier3.*;
import game.planet.buildings.*;
import game.research.*;
import game.ressource.*;

public class Planet {
	
	private Coordinates 			coords = null;
	private String 					name = "";
	
	// Buildings
	private HeadQuarter 			headQuarter = null;
	private IronMine				ironMine = null;
	
	
	private ArrayList<ARessource>	ressources = new ArrayList<ARessource>();
	private Fleet					fleet = new Fleet();
	
	public Planet() {
		
	}
	
	public Planet(Coordinates coords) {
		this.coords = coords;
	}
	
	public static void main(String[] args) {
		
	}
	
	public void testFill(TechTree techtree) {
		
		
		HeadQuarter hq = new HeadQuarter();
		hq.testFill(techtree);
		
		IronMine im = new IronMine();		
		im.testFill(techtree);
		
		Fleet fl = new Fleet();
		fl.testFill(techtree);
		
		
		this.coords = new Coordinates(1, 33, 7);
		this.name = "Martins TodesStern";
		this.headQuarter = hq;
		this.ironMine = im;
		this.ressources.add(new Iron(3333));
		this.ressources.add(new RareEarth(3333));
		this.ressources.add(new Water(3333));
		this.ressources.add(new Tritium(3333));
		this.fleet = fl;
		
		
	}

	public Coordinates getCoords() {
		return coords;
	}	
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ArrayList<ARessource> getRessources() {
		return ressources;
	}	

	public HeadQuarter getHeadQuarter() {
		return headQuarter;
	}

	public IronMine getIronMine() {
		return ironMine;
	}

	public Fleet getFleet() {
		return fleet;
	}
	
	
	
	

}
