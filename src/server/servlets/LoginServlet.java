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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email"); 
		String password = request.getParameter("password"); 
		
		int playerid = DBUser.getPlayerIdByEmail(email);		
		boolean success = DBLogin.comparePassword(playerid, password);
		System.out.println("playerid login:" + playerid + ": " + success);
		
		if (success) {
			HttpSession session = request.getSession();
			Player player = GameLoader.loadPlayer(playerid);
			player.getPersData().setLastLogin(new Date());
			session.setAttribute("player", player);
			DateUtils dateUtils = new DateUtils();
			session.setAttribute("dateUtils", dateUtils);
			response.sendRedirect(request.getContextPath() + "/overview.jsp");
		} else {
			String error = "SomeThing went wrong";
			request.setAttribute("error", error);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
