package game.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import game.event.*;
import game.player.*;

public class GameUpdate {
	
	private Player user;
	
	public GameUpdate() {}
	
	public GameUpdate(Player user) {
		this.user = user;
	}
	
	public static void main(String[] args) {
		System.out.println("Test");
		System.out.println(new GameUpdate().toString());
		System.out.println(new GameUpdate().toString());
	}
	
	public boolean pageRefresh() {	
		// TODO Connection create here and give it to all others and close it after refresh
		boolean output = false;
		Date now = new Date();
		user.getInbox().update();		
		output = updatePlayerToDate(user, now);
		// Close connection
		return output;
	}
	
	private boolean updatePlayerToDate(Player user, Date date) {
		
		return true; // TODO Just to be able to upload :)
		
		// Getting the Users EventCenter and update it
		EventCenter ec = user.getEventCenter();
		ec.update();
		
		// Getting the Events that already occured
		ArrayList<GameEvent> events = ec.getCalcEventsToDate(date);
		
		if (events.size() > 0) {
			// TODO trying to lock this user!!!
			return calculateEvents(events, user, date);
		}
		
		return true;		
	}
	
	private boolean calculateEvents(ArrayList<GameEvent> events, Player user, Date date) {				

		Iterator<GameEvent> i = events.iterator();
		while (i.hasNext()) {
			GameEvent event = i.next();					
			if (event.getEndTime().getTime() < date.getTime()) {
				switch (event.getType()) {
					case ATTACK:
						calculateAttack(event, i);
						break;
					case DEFEND:
						calculateDefend(event, i);
						break;
					case TRANSPORT:
						calculateTransport(event, i);
						break;
					case BUILD:
						calculateBuild(event, i);
						break;
					case RESEARCH:
						calculateResearch(event, i);
						break;
					default:
						throw new IllegalArgumentException("Wrong Event in your List");				
				}
			}
		}

		
		return true;
	}

}
