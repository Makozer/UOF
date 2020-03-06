package community.message;

import java.util.*;

public class Message {

	protected int fromId = 0;
	protected int toId = 0;
	protected String title = "";
	protected String message = "";
	protected Date timestamp = null;
	
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
	
}
