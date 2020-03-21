package server.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.message.*;
import database.DBMessage;
import game.player.Player;
import game.utils.NumberUtils;

/**
 * Servlet implementation class ShowMessageServlet
 */
@WebServlet("/ShowMessageServlet")
public class ShowMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMessageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession 			session = request.getSession();	
		Player 					player = (Player)session.getAttribute("player");	
		
		int 					msgid = NumberUtils.stringAsInt(request.getParameter("msgid"));

		Message message = DBMessage.getMessageById(msgid);
		
		if (player.getPersData().getId() != message.getToId() && player.getPersData().getId() != message.getFromId()) { 
			System.out.println(player.getPersData().getId() + " != " + message.getToId() + "/" + message.getFromId());
			return; 
		}
		
		request.setAttribute("sendtime", message.getTimestamp());
		request.setAttribute("fromplayer", message.getFromId());
		request.setAttribute("msgtitle", message.getTitle());
		request.setAttribute("msgcontent", message.getMessage());		
		request.getRequestDispatcher("showmessage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
