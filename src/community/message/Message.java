package community.message;

import java.util.*;

import game.*;

public class Message implements Comparable<Message> {

	protected int msgId = 0;
	protected int fromId = 0;
	protected int toId = 0;
	protected String title = "";
	protected String message = "";
	protected Date timestamp = null;
	
	public Message(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public Message(int msgId, int fromId, int toId, String title, String message, Date timestamp) {
		this.msgId = msgId;
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

	@Override
	public int compareTo(Message o) {
		Message other = (Message) o;		
		return this.timestamp.compareTo(other.getTimestamp());
	}
	
}
