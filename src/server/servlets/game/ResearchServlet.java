package server.servlets.game;

import static game.settings.GameSettings.DEBUGMODE;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import game.GameEvent;
import game.player.Player;

/**
 * Servlet implementation class ResearchServlet
 */
@WebServlet("/ResearchServlet")
public class ResearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResearchServlet() {
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
		String research = request.getParameter("research");
		Player player = (Player)session.getAttribute("player");
		if (player == null) { return; } else { player.update(); }

		if (DEBUGMODE) {System.out.println("Trying to research (" + research + ")");}
		if (player.getActivePlanet().isResearching()) {
			GameEvent event = player.getResearchEventByCoords(player.getActivePlanet().getCoords());
			if (DEBUGMODE) {System.out.println("doCancelResearch:" + event.toString());}
			player.doCancelResearch(event);
		} else {
			if (DEBUGMODE) {System.out.println("doBuild(" + research + ")");}
			if(!player.doResearch(research)) {
				request.setAttribute("error", "Nicht genug Ressourcen!");
				request.getRequestDispatcher("research.jsp").forward(request, response); 
				return;
			};
		}		
		response.sendRedirect(request.getContextPath() + "/research.jsp");

	}

}
