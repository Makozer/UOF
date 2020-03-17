package server.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import game.*;
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
		
		HttpSession session = request.getSession();				
		Player player = new Player();
		player.testFill();
		DateUtils dateUtils = new DateUtils();
		session.setAttribute("player", player);
		session.setAttribute("dateUtils", dateUtils);
		response.sendRedirect(request.getContextPath() + "/example.jsp");
		
		
		//request.getRequestDispatcher("example.jsp").forward(request, response);
		
		//String email = request.getParameter("email");
		//String password = request.getParameter("password");
		
		/*
		if (email.equals("martin@martin-kohne.de") && password.equals("123")) {
			//player.setEmail(email);
			//player.setName("Martin");
			session.setAttribute("user", player);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		*/
		
	}

}
