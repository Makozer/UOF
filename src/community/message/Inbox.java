package community.message;

import java.util.*;

import database.DBMessage;
import database.utils.*;
import game.player.*;

public class Inbox {
	
	private Player 					player 		= null;
	private ArrayList<Message>		messages 	= new ArrayList<Message>();
	private Date					lastupdate	= null;
	private boolean					hasnewmsg	= false;
	
	public Inbox(Player player) {
		this.player = player;
	}

	public static void main(String[] args) {

	}
	
	public void addMessage(Message message) {
		this.hasnewmsg = true;
		this.messages.add(message);
	}
	
	public void addMessages(ArrayList<Message> messages) {
		for (Message message : messages) {
			this.addMessage(message);
		}		
	}
	
	public ArrayList<Message> getMessages() {
		this.hasnewmsg = false;
		this.update();
		return messages;
	}
	
	public Message getMessageById(int messageid) throws NoPermissionException {
		Message message = new GameMessage("Keine Nachricht gefunden!", "Irgendwas lief schief, wir fanden keine Nachricht unter den Angaben!");
		for (Message m : messages) {
			if (m.getMsgId() == messageid) {return m;}
		}
		return message;
	}
	
	public boolean deleteMessage(int messageid) {
		for (Message m: messages) {
			if (m.getMsgId() == messageid) {
				this.messages.remove(m);
				if (m instanceof CommunityMessage) {DBMessage.deleteMessage(this.player, m);}
				return true;
			}
		}
		return false;
	}
	
	private void update() {
		// if Inbox hasnt loaded once
		if (this.lastupdate == null) {
			loadAll();
			sortMe();
			return;
		}
		
		// Cooldown 5 Sec for updating with DataBase to increase overall performance
		if (    ((new Date().getTime() - this.lastupdate.getTime()) / 1000) > 5 ) {
			this.messages.addAll(DBMessage.getMessages(player, lastupdate));
			this.lastupdate = new Date();
			sortMe();
		}
		
	}
	
	private void loadAll() {
		this.messages.addAll(DBMessage.getMessages(player));
		this.lastupdate = new Date();
	}
	
	private void sortMe() {
		this.messages.sort(
				new Comparator<Message>() {
					public int compare(Message first, Message second) {
						return first.compareTo(second);
					}
				}
			);		
	}

	public int getPlayerId() {
		return this.player.getPersData().getId();
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public boolean hasNewMessage() {
		return hasnewmsg;
	}

}
