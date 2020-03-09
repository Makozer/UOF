package community.message;

import java.util.*;

import game.utils.*;

public class GameMessage extends Message {

	public GameMessage(String title, String message) {
		super(title, message);
		this.timestamp = new Date();
		this.msgId = NumberUtils.getRndInt(1000000, 9999999);
	}	

}
