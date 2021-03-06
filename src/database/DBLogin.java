package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.utils.PasswordUtils;

/**
 * Class for Login db actions.
 * @author cedri
 *
 */
public class DBLogin {

	public static void main(String[] args) {
		

	}
	
	/**
	 * compares userinput and db stored pw.
	 * @param playerid
	 * @param password
	 * @return
	 */
	public static boolean comparePassword(int playerid, String password) {
		String dbpw = getPassword(playerid);
		String checkpwd = PasswordUtils.getSaltyPassword(password);
		System.out.println("DB PWD: " + dbpw);
		if (dbpw.equals(checkpwd)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * retrieves obfuscated userpw from db.
	 * @param playerid
	 * @return
	 */
	private static String getPassword(int playerid) {
		String password = "";
		
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT password FROM public.passwords WHERE userid = ?" 
					);
			pstmt.setInt(1, playerid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			} else {
				return "";
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return password;
	}

}
