package server.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBLogin;
import database.DBUser;
import game.*;
import game.control.GameLoader;
import game.control.GameUpdate;
import game.player.Player;
import game.utils.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * This Servlet manages the Login for Users
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email"); 
		String password = request.getParameter("password"); 
		
		int playerid = DBUser.getPlayerIdByEmail(email);		
		boolean success = DBLogin.comparePassword(playerid, password);
		System.out.println("playerid login:" + playerid + ": " + success);
		
		if (success) {
			// Creating Session
			HttpSession session = request.getSession();
			
			// Creating Player
			Player user = GameLoader.loadPlayer(playerid);
			user.getPersData().setLastLogin(new Date());
			session.setAttribute("player", user);
			
			// DateUtils
			DateUtils dateUtils = new DateUtils();
			session.setAttribute("dateUtils", dateUtils);
			
			// GameUpdate
			GameUpdate gameupdate = new GameUpdate(user);
			session.setAttribute("update", gameupdate);
			
			response.sendRedirect(request.getContextPath() + "/overview.jsp");
		} else {
			String error = "User/password combination wrong";
			request.setAttribute("error", error);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		/*
		HttpSession session = request.getSession();				
		Player player = new Player();
		player.testFill();
		DateUtils dateUtils = new DateUtils();
		session.setAttribute("player", player);
		session.setAttribute("dateUtils", dateUtils);
		response.sendRedirect(request.getContextPath() + "/example.jsp");
		*/
		
	}

}
