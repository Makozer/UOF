package game.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import game.player.*;

import community.message.Message;

public class ResultToTable {

	public static String convert(ResultSet rs) {
		String table = "<table>";
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			//keine id 1 skip
			rs.next();
			
			int col = rsmd.getColumnCount();
			table += "<tr>";
			for (int i = 1; i <= col; i++) {
				table += "<th>" + rsmd.getColumnName(i) + "</th>";
			}
			table += "<tr>";
			do {
				table += "<tr>";
				for (int i = 1; i <= col; i++) {
					table += "<td>" + rs.getString(i) + "</td>";
				}
				table += "</tr>";
			} while (rs.next());
		} catch (SQLException e) {
			table = "kein Ergebnis";
		}
		return table;
	}
	
	public static ArrayList<Message> convertMessages(ResultSet rs, Player player) {
		ArrayList<Message> temp = player.getMessages();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next())
		      {
		        temp.add(new Message(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6)));
		      }
		} catch (SQLException e) {
			
		}
		return temp;
	}
}