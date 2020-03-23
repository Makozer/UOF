package server.servlets;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import community.message.*;
import database.*;
import database.utils.*;
import game.player.*;
import game.utils.*;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		
		// Check if the user wants to see a message
		int 					messageid = NumberUtils.stringAsInt(request.getParameter("msgid"));
		if (messageid > 0) {
			showMessage(request, response, messageid);
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check if delete
		int messageid = NumberUtils.stringAsInt(request.getParameter("msgdel"));
		if (messageid > 0) {
			deleteMessage(request, response, messageid);
			return;
		}
		
		// Check if new Message
		String toplayer = request.getParameter("toPlayer");
		if (toplayer.length() > 0) {
			newMessage(request, response, toplayer);
			return;
		}
		
		// Display Inbox :)
		HttpSession 			session = request.getSession();	
		Player 					player = (Player)session.getAttribute("player");
		request.setAttribute("messages", player.getInbox().getMessages());		
		request.getRequestDispatcher("messages.jsp").forward(request, response);
	}
	
	public boolean newMessage(HttpServletRequest request, HttpServletResponse response, String playername) throws ServletException, IOException {
		
		HttpSession 	session = request.getSession();	
		Player 			player = (Player)session.getAttribute("player");	
		
		int 			playerid = DBPlayer.getPlayerIdByDisplayName(playername);
		String 			title = request.getParameter("title");
		String 			message = request.getParameter("message");
		
		// If PlayerId is 0 then Error to User
		if (playerid == 0) {
			request.setAttribute("titelcontent", title);
			request.setAttribute("textcontent", message);
			request.setAttribute("error", "Dieser Spieler existiert nicht! Bitte achte auf groﬂ/kleinschreibung!");		
			request.getRequestDispatcher("newmessage.jsp").forward(request, response);
			return false;
		}
		
		// Creating the Message in the Database
		DBMessage.createMessage(new CommunityMessage(player.getPersData().getId(), playerid, title, message, new Date()));
		request.setAttribute("success", "Ihre Nachricht wurde verschickt.");	
		request.getRequestDispatcher("messages.jsp").forward(request, response);
		
		return true;
	}
	
	private boolean showMessage(HttpServletRequest request, HttpServletResponse response, int messageid) throws ServletException, IOException {
		HttpSession 	session = request.getSession();	
		Player 			player = (Player)session.getAttribute("player");
		Message			message = null;
		
		// get the Message and display it to the User
		try {
			message = player.getInbox().getMessageById(messageid);
		} catch (NoPermissionException e) {
			e.printStackTrace();
			return false;
		}
		
		request.setAttribute("sendtime", message.getTimestamp());
		request.setAttribute("fromplayer", message.getFromId());
		request.setAttribute("msgtitle", message.getTitle());
		request.setAttribute("msgcontent", message.getMessage());		
		request.getRequestDispatcher("showmessage.jsp").forward(request, response);
		return true;
	}
	
	private boolean deleteMessage(HttpServletRequest request, HttpServletResponse response, int messageid) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		Player player = (Player)session.getAttribute("player");
		player.getInbox().deleteMessage(messageid);
		response.sendRedirect(request.getContextPath() + "/messages.jsp");
		return true;
	}

}
