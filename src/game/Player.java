package game;

import java.util.*;
import game.planet.*;
import game.research.TechTree;

public class Player {
	
	private int id = 0;
	private String email = "";
	private String password = "";
	
	private String displayname = "";
	private String prename = "";
	private String surname = "";
	
	
	private boolean agb = false;
	private boolean dsb = false;
	
	private Date lastLogin = null;
	private Date created = null;
	
	private TechTree techtree = null;
	
	private ArrayList<Planet> planets = new ArrayList<Planet>();

	public static void main(String[] args) {
		// Testmain

	}

}
