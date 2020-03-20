package game.utils;

import java.util.*;
import java.util.regex.Pattern;

import game.fleet.ASpaceShip;
import game.ressource.*;

public class RessourceUtils {

	public static void main(String[] args) {
		// SQL Test
		String sql = "Water=123;Tritium=1;Iron=321;";
		
		for (ARessource r : sqlToRessourceArrayList(sql)) {
			System.out.println(r.toString());
		}

	}
	
	public static ArrayList<ARessource> sqlToRessourceArrayList(String sql) {
		HashMap<String, ARessource> ressmap = new HashMap<String, ARessource>();
		Iron iron = new Iron(0); RareEarth rare = new RareEarth(0); Water water = new Water(0); Tritium tritium = new Tritium(0);
		ressmap.put(iron.getName(), iron); ressmap.put(rare.getName(), rare); ressmap.put(water.getName(), water); ressmap.put(tritium.getName(), tritium); 
		
		ArrayList<ARessource> ressarray = new ArrayList<ARessource>();
		
		String[] sqlsplit = sql.split( Pattern.quote( ";" ) );
		String[] keyValue = null;
		
		for (String ress: sqlsplit) {
			if (ress.length() < 2) {break;}
			keyValue = ress.split( Pattern.quote( "=" ) );
			ressarray.add(ressmap.get(keyValue[0]).cloneMe(NumberUtils.stringAsInt(keyValue[1])));
		}	
		
		return ressarray;
	}
	
	public static String arrayToSQL(ArrayList<ARessource> ress) {
		String sql = "";
		for (ARessource r : ress) {
			sql += r.getName() + "=" + r.getValue() + ";";
		}
		return sql;
	}

}
