package community.message;

import java.util.Date;

/**
 * Used for all communication between users
 * @author Martin
 *
 */
public class CommunityMessage extends Message {

	/**
	 * From a PlayerId to a PlayerId with a title and a message and a timestamp :)
	 * @param fromId
	 * @param toId
	 * @param title
	 * @param message
	 * @param timestamp
	 */
	public CommunityMessage(int fromId, int toId, String title, String message, Date timestamp) {
		super(fromId, toId, title, message, timestamp);
	}

}
