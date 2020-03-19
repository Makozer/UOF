package game.fleet;

import java.util.ArrayList;
import java.util.regex.Pattern;

import game.research.*;
import game.utils.NumberUtils;

public class ShipFabric {
	
	public static ASpaceShip createShip(TechTree techtree, String name, int quantity) {
		ASpaceShip output = null;
		ASpaceShip origin = techtree.getResearchedShip(name);
		try {
			output = origin.cloneMe(quantity);
			/*
            output = origin.getClass().getDeclaredConstructor().newInstance();
            output.setQuantity(quantity);
            output.setTechtree(this);
            */
		} catch (NullPointerException e) {
			// Everything fine xD
        } catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
		return output;
		/*
		 * Class aClass = Class.forName(origin.getClass().getPackageName() + "." + origin.getClass().getSimpleName());
	       newShip = (ASpaceShip)aClass.getDeclaredConstructor().newInstance();
	       newShip.setQuantity(quantity);
	       newShip.setTechtree(techtree);
	       ships.add(newShip);
		 */
	}
	
	public static ASpaceShip createShip(ASpaceShip origin, int quantity) {
		ASpaceShip output = null;
		try {
			output = origin.cloneMe(quantity);
			/*
            output = origin.getClass().getDeclaredConstructor().newInstance();
            output.setQuantity(quantity);
            output.setTechtree(this);
            */
        } catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
		
		return output;
	}
	
	public static ArrayList<ASpaceShip> createArrayFromSQL(TechTree techtree, String sql){
		ArrayList<ASpaceShip> ships = new ArrayList<ASpaceShip>();
		if (sql == null) { return ships; }
		String[] sqlships = sql.split( Pattern.quote( ";" ) );
		String[] keyValue = null;
		for (String ship: sqlships) {
			if (ship.length() < 2) {break;}
			keyValue = ship.split( Pattern.quote( "=" ) );
			ships.add(createShip(techtree, keyValue[0], NumberUtils.stringAsInt(keyValue[1])));
		}
		return ships;
	}

}
