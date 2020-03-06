package community.message;

import java.util.*;

public class GameMessage extends Message {

	public GameMessage(String title, String message) {
		super(title, message);
		this.timestamp = new Date();
	}	

}
