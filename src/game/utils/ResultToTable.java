package game.utils;

import java.sql.ResultSet;


import java.sql.ResultSetMetaData;
import java.sql.SQLException;

// Not used anymore?

/** Klasse zum Konvertieren eines ResultSets zu einer HTML-Tabelle*/
public class ResultToTable {

	public static String resultToTable(ResultSet rs) {

		String table = "<table>";									// �ffnendes Tag 
		try {
			ResultSetMetaData rsmd = rs.getMetaData();				// Informationen wie Spaltennamen etc
			int col = rsmd.getColumnCount();						// Anzahl der Spalten
			table += "<tr>";										// Starte erste Zeile
			for (int i = 1; i <= col; i++) {						// Iteriere durch alle Spaltennamen ("Kopfzeile")
				table += "<th>" + firstUpper(rsmd.getColumnName(i)) + "</th>";  // F�lle Kopfzeile mit Inhalten
			}
			table += "</tr>";										// Schlie�e Kopfzeile
			// Ab hier: Eigentliche Inhalte werden gef�llt
			do {
				table += "<tr>";									// Starte Zeile										
				for (int i = 1; i <= col; i++) {					// Iteriere �ber alle Ergebnisse
					table += "<td>"  + rs.getString(i) + "</td>";// F�lle Zeile mit Inahlten
				}
				table += "</tr>";									// Schlie�e Zeile
			} while (rs.next());
			table += "</table> <br/>";								// Schlie�e Table
		// Tritt z.B. auf, wenn die Tabelle leer ist.
		} catch (SQLException e) {
			table = "Keine Ergebnisse";
		}
		return table;
	}
	
	
	private static String firstUpper(String input) {
		char first = Character.toUpperCase(input.charAt(0));
		String firstUpper = first + input.substring(1);
		return firstUpper;
	}
}