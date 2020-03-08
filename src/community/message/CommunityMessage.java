package community.message;

import java.util.Date;

public class CommunityMessage extends Message {

	public CommunityMessage(int msgId, int fromId, int toId, String title, String message, Date timestamp) {
		super(msgId, fromId, toId, title, message, timestamp);
	}

}
