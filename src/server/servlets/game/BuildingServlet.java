package server.servlets.game;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import game.*;
import game.player.Player;
import static game.settings.GameSettings.*;

/**
 * Servlet implementation class BuildingServlet
 */
@WebServlet("/BuildingServlet")
public class BuildingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildingServlet() {
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
		String building = request.getParameter("building");
		Player player = (Player)session.getAttribute("player");
		if (player == null) { return; } else { player.update(); }
		if (DEBUGMODE) {System.out.println("Trying to build building(" + building + ")");}
		if (player.getActivePlanet().isBuilding()) {
			GameEvent event = player.getBuildEventByCoords(player.getActivePlanet().getCoords());
			if (DEBUGMODE) {System.out.println("doCancelBuild:" + event.toString());}
			player.doCancelBuild(event);
		} else {
			if (DEBUGMODE) {System.out.println("doBuild(" + building + ")");}
			if(!player.doBuild(building)) {
				request.setAttribute("error", "Nicht genug Ressourcen!");
				request.getRequestDispatcher("buildings.jsp").forward(request, response); 
				return;
			};
		}		
		response.sendRedirect(request.getContextPath() + "/buildings.jsp");
	}

}
