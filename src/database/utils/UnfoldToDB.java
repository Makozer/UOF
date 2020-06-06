package database.utils;

import java.sql.*;
import java.util.*;

import database.*;

public class UnfoldToDB {
	
	private static Connection con = null;

	public static void main(String[] args) {
		// Getting the owner
		String owner = DataBaseSettings.USER;
		
		// Creating the Sequences
		createSequences(owner);
		createTables(owner);

	}
	
	public static boolean doSQL(String sql) {
		boolean success = false;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					sql
					+ ";"
				);
				//pstmt.setString(4, pd.getEmail());
				//pstmt.setTimestamp(5, new Timestamp(pd.getBirthday().getTime()), Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin")));
				//pstmt.setInt(6, pd.getId());


			System.out.println("Unfolding DB Structure ... " + pstmt.toString());	
			int updatedRows = pstmt.executeUpdate();
			if (updatedRows > 0) {
				success = true; 
			} 
		} catch (SQLException e) {
			System.err.println("SQL-Error at UnfoldToDB " + e.getMessage());
		} catch (NullPointerException npe) {
			System.err.println("Nullpointer@UnfoldToDB: " + npe.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Couldnt close connection at UnfoldToDB");
			}
		}
		return success;
	}
	
	public static void createSequences(String owner) {

		// Create Event ID
		String eventId = "CREATE SEQUENCE public.eventid_seq "
								+ "INCREMENT 1 " 
								+ "START 1 " 
								+ "MINVALUE 1 "
								+ "MAXVALUE 9222222036664771337 "
								+ "CACHE 1; "
								+ "ALTER SEQUENCE public.eventid_seq "
								+ "OWNER TO \""+ owner + "\";";
		
		// Create Message ID
		String messageId = "CREATE SEQUENCE public.messageid_seq "
								+ "INCREMENT 1 " 
								+ "START 1 " 
								+ "MINVALUE 1 "
								+ "MAXVALUE 9222222036664771337 "
								+ "CACHE 1; "
								+ "ALTER SEQUENCE public.messageid_seq "
								+ "OWNER TO \""+ owner + "\";";
		
		// Create Password Seq
		String userSeq = "CREATE SEQUENCE public.userid_seq "
									+ "INCREMENT 1 " 
									+ "START 1 " 
									+ "MINVALUE 1 "
									+ "MAXVALUE 9222222036664771337 "
									+ "CACHE 1; "
									+ "ALTER SEQUENCE public.userid_seq "
									+ "OWNER TO \""+ owner + "\";";
	
		
		// Doing the tasks
		String[] tasks = {eventId, messageId, userSeq};
		
		for (String task : tasks) {
			doSQL(task);
		}
		
	}
	
	public static void createTables(String owner) {
		
		// Create techtree table
		String techtrees = "CREATE TABLE public.techtrees ( "
				+ "userid integer NOT NULL, " 
				+ "tree character varying(333) COLLATE pg_catalog.\"default\" NOT NULL, " 
				+ "CONSTRAINT techtree_pkey PRIMARY KEY (userid) " 
				+ ") "
				
				+ "WITH ( OIDS = FALSE ) "
				+ "TABLESPACE pg_default; "
				+ "ALTER TABLE public.techtrees "
				+ "OWNER TO \""+ owner + "\";";

		// Create event table
		String events = "CREATE TABLE public.events ( "
								+ "eventid bigint NOT NULL DEFAULT nextval('eventid_seq'::regclass), " 
								+ "typ character varying(10) COLLATE pg_catalog.\"default\" NOT NULL, " 
								+ "thisgalaxy integer NOT NULL, "
								+ "thissolarsystem integer NOT NULL, "
								+ "thisplanet integer NOT NULL, "
								+ "targetgalaxy integer, "
								+ "targetsolarsystem integer, "
								+ "targetplanet integer, "
								+ "building character varying COLLATE pg_catalog.\"default\", "
								+ "fleet character varying(333) COLLATE pg_catalog.\"default\", "
								+ "ressource character varying COLLATE pg_catalog.\"default\", "
								+ "starttime timestamp with time zone NOT NULL DEFAULT timezone('Europe/Berlin'::text, now()), "
								+ "endttime timestamp with time zone NOT NULL, "
								+ "userid integer NOT NULL, "
								+ "targetuserid integer, "
								+ "lock character varying COLLATE pg_catalog.\"default\", "
								+ "CONSTRAINT eventid_pkey PRIMARY KEY (eventid) "
								+ ") "
								
								+ "WITH ( OIDS = FALSE ) "
								+ "TABLESPACE pg_default; "
								+ "ALTER TABLE public.events "
								+ "OWNER TO \""+ owner + "\";";
		
		// Create messages table
		String messages = "CREATE TABLE public.messages ( "
							+ "messageid integer NOT NULL DEFAULT nextval('messageid_seq'::regclass), " 
							+ "touserid integer NOT NULL, " 
							+ "fromuserid integer NOT NULL, " 
							+ "title character varying COLLATE pg_catalog.\"default\", " 
							+ "msgcontent character varying COLLATE pg_catalog.\"default\", " 
							+ "created timestamp with time zone NOT NULL DEFAULT timezone('Europe/Berlin'::text, now()), " 
							+ "new bit, "
							+ "CONSTRAINT messageid_pkey PRIMARY KEY (messageid) " 
							+ ") "
							
							+ "WITH ( OIDS = FALSE ) "
							+ "TABLESPACE pg_default; "
							+ "ALTER TABLE public.messages "
							+ "OWNER TO \""+ owner + "\";";
		
		// Create planet table
		String planets = "CREATE TABLE public.planets ( "
							+ "galaxy integer NOT NULL, " 
							+ "solarsystem integer NOT NULL, " 
							+ "planetnumber integer NOT NULL, " 
							+ "userid integer NOT NULL, " 
							+ "planetname character varying COLLATE pg_catalog.\"default\" NOT NULL, " 
							+ "ressources character varying COLLATE pg_catalog.\"default\" NOT NULL, " 
							+ "buildings character varying COLLATE pg_catalog.\"default\" NOT NULL, " 
							+ "fleet character varying COLLATE pg_catalog.\"default\", " 
							+ "spaceportqueue character varying COLLATE pg_catalog.\"default\", " 
							+ "lastupdate timestamp with time zone NOT NULL DEFAULT timezone('Europe/Berlin'::text, now()), " 
							+ "CONSTRAINT planet_pkey PRIMARY KEY (galaxy, solarsystem, planetnumber) "
							+ ") "
							
							+ "WITH ( OIDS = FALSE ) "
							+ "TABLESPACE pg_default; "
							+ "ALTER TABLE public.planets "
							+ "OWNER TO \""+ owner + "\";";
		
		// Create user table
		String users = "CREATE TABLE public.users ( "
						+ "userid integer NOT NULL DEFAULT nextval('userid_seq'::regclass), " 
						+ "displayname character varying COLLATE pg_catalog.\"default\" NOT NULL, "
						+ "prename character varying COLLATE pg_catalog.\"default\", "
						+ "surname character varying COLLATE pg_catalog.\"default\", "
						+ "email character varying COLLATE pg_catalog.\"default\" NOT NULL, "
						+ "created timestamp with time zone NOT NULL DEFAULT timezone('Europe/Berlin'::text, now()), "
						+ "birthday timestamp with time zone, "
						+ "lastlogin timestamp with time zone NOT NULL DEFAULT timezone('Europe/Berlin'::text, now()), "
						+ "lock character varying COLLATE pg_catalog.\"default\", "
						+ "CONSTRAINT userid_pkey PRIMARY KEY (userid), "
						+ "CONSTRAINT displayname_unique UNIQUE (displayname), "
						+ "CONSTRAINT email_unique UNIQUE (email), "
						+ "CONSTRAINT pwd_fkey FOREIGN KEY (userid) "
						+ "REFERENCES public.passwords (userid) MATCH SIMPLE "
						+ "ON UPDATE CASCADE "
						+ "ON DELETE CASCADE "
						+ ") "
						
						+ "WITH ( OIDS = FALSE ) "
						+ "TABLESPACE pg_default; "
						+ "ALTER TABLE public.users "
						+ "OWNER TO \""+ owner + "\";";
		
		// Create password table
		String passwords = "CREATE TABLE public.passwords ( "
				+ "userid integer NOT NULL, " 
				+ "password character varying(64) COLLATE pg_catalog.\"default\" NOT NULL, " 
				+ "CONSTRAINT passwoerter_pkey PRIMARY KEY (userid) " 
				+ ") "
				
				+ "WITH ( OIDS = FALSE ) "
				+ "TABLESPACE pg_default; "
				+ "ALTER TABLE public.passwords "
				+ "OWNER TO \""+ owner + "\";";
		
		
		// Doing the tasks
		String[] tasks = {events, messages, techtrees, passwords, planets, users};
		
		for (String task : tasks) {
			doSQL(task);
		}
		
	}

}
