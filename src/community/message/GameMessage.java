package community.message;

import java.util.*;
import static game.settings.GameSettings.*;

import game.utils.*;

/**
 * Used for Events etc.
 * Combatlog etc.
 * @author Martin
 *
 */
public class GameMessage extends Message {

	public GameMessage(String title, String message) {
		super(title, message);
		this.timestamp = new Date();
		this.msgId = NumberUtils.getRndInt(1000000, 9999999);
		if (DEBUGMODE) { System.out.println(this.toString());}
	}	

}
