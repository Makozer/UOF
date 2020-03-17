package server.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBPeon;
import game.player.Player;
import game.utils.NumberUtils;

/**
 * Servlet implementation class NewMessageServlet
 */
@WebServlet("/NewMessageServlet")
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMessageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession 			session = request.getSession();	
		Player 					player = (Player)session.getAttribute("player");	
		
		String 					toPlayer = request.getParameter("toPlayer");
		String 					title = request.getParameter("title");
		String 					message = request.getParameter("message");
		
		// TODO Database Name to ID convert
		
		int 					toPlayerId = NumberUtils.stringAsInt(toPlayer);
		
		// TODO Database connection create Message
		DBPeon.createNewMessage(player.getPersData().getId(), toPlayerId, title, message);
		
		response.sendRedirect(request.getContextPath() + "/messages.jsp");

	}

}
