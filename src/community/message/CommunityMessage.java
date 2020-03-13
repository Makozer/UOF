package community.message;

import java.util.Date;

public class CommunityMessage extends Message {

	public CommunityMessage(int fromId, int toId, String title, String message, Date timestamp) {
		super(fromId, toId, title, message, timestamp);
		// TODO get msgId and names from Database
	}

}
