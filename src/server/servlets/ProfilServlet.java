package server.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DBUser;
import game.player.Player;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilServlet() {
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
		HttpSession session = request.getSession();		
		Player player = (Player)session.getAttribute("player");
		if (player == null) {response.sendRedirect(request.getContextPath() + "/index.jsp"); return;}
		
		String displayname = request.getParameter("displayname");
		String prename = request.getParameter("prename");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		
		player.getPersData().setDisplayName(displayname);
		player.getPersData().setPreName(prename);
		player.getPersData().setSurName(surname);
		player.getPersData().setEmail(email);
		
		DBUser.updatePlayerData(player);
		response.sendRedirect(request.getContextPath() + "/profil.jsp");
		
	}

}
