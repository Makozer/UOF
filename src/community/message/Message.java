package community.message;

import java.util.*;

import database.DBUser;


/**
 * @author Cedric
 * This class is used for every Message in the Game, see GameMessage or CommunityMessage
 */
public class Message implements Comparable<Message> {

	protected int 		msgId = 0;
	protected int 		fromId = 0;
	protected String 	fromName = ""; 
	protected int 		toId = 0;
	protected String 	toName = "";
	protected String 	title = "";
	protected String 	message = "";
	protected Date 		timestamp = null;
	
	public Message(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public Message(int fromId, int toId, String title, String message, Date timestamp) {
		this.fromId = fromId;
		this.toId = toId;
		this.title = title;
		this.message = message;
		this.timestamp = timestamp;
	}	

	public int getMsgId() {
		return msgId;
	}

	public int getFromId() {
		return fromId;
	}
	
	public String getFromName() {
		String name = "";
		name = DBUser.getPlayerById(fromId).getDisplayName();
		return name;
	}

	public int getToId() {
		return toId;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	@Override
	public int compareTo(Message o) {
		// TODO lowest prio .... y cast this?
		Message ycastthis = (Message) o;		
		return this.timestamp.compareTo(ycastthis.getTimestamp());
	}

	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", fromId=" + fromId + ", fromName=" + fromName + ", toId=" + toId
				+ ", toName=" + toName + ", title=" + title + ", message=" + message + ", timestamp=" + timestamp + "]";
	}
	
}
