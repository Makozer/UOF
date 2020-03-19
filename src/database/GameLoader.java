package database;

import java.util.Date;

import game.*;
import game.fleet.*;
import game.research.*;
import game.planet.*;
import game.planet.buildings.*;
import game.planet.buildings.mining.*;
import game.player.*;

public class GameLoader {
	
	public static Player loadPlayer(int playerid) {

		Player player = null;
		
		// DataBase get Data by Player ID
		player = DBPlayer.getPlayerById(playerid);
		
		// DataBase get Planets by Player ID
		player.addPlanets(DBPlanet.getPlanetsById(playerid));
		
		// DataBase get events by Player ID
		
		// DataBase get messages by Player ID
		
		
		return player;
	}
	
	
	private PersonalData loadPersonalData(int id, String email, String displayName, String preName, String surName, int birthday, int created, int lastLogin) {
		Date birthdayDate = new Date(birthday * 1000);
		Date createDate = new Date(created * 1000);
		Date lastLoginDate = new Date(lastLogin * 1000);
		return new PersonalData(id, email, displayName, preName, surName, birthdayDate, createDate, lastLoginDate);
	}
	
	private Player createPlayer(PersonalData persData, TechTree techtree) {
		Player output = null;
		
		return output;
	}
	
	private void loadPlanet(Player player) {
		
	}
	
	private void loadEvents(Player player) {
		
	}
	
	private void loadMessages(Player player) {
		
	}


}
