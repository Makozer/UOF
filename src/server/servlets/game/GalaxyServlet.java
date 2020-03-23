package server.servlets.game;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import database.*;
import game.settings.*;
import game.utils.*;

/**
 * Servlet implementation class GalaxyServlet
 */
@WebServlet("/GalaxyServlet")
public class GalaxyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalaxyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// required Objects
		String 		galaxyS = request.getParameter("galaxy");
		String 		solarsystemS = request.getParameter("solarsystem");
		int 		galaxy = NumberUtils.stringAsInt(galaxyS);
		int 		solarsystem = NumberUtils.stringAsInt(solarsystemS);
		
		// Escaping when User wants to troll
		if (galaxy < 1 || galaxy > UniverseSettings.GALAXYMAX || 
				solarsystem < 1 || solarsystem > UniverseSettings.SOLARSYSTEMMAX) {
			// Error to User
			request.setAttribute("galaxy", 1);
			request.setAttribute("solarsystem", 1);
			request.setAttribute("error", "FORBIDDEN AREA");
			request.getRequestDispatcher("galaxy.jsp").forward(request, response); 
			return;
		}
		
		// getting the DataBase Data
		ArrayList<String> solarsystemA = DBPlanet.getSolarSystem(galaxy, solarsystem);		
		
		// output
		if (solarsystemA != null) {
			request.setAttribute("galaxy", galaxy);
			request.setAttribute("solarsystem", solarsystem);
			request.setAttribute("output", solarsystemA);
			request.getRequestDispatcher("galaxy.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
