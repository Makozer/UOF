package server.servlets.game;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import game.*;
import game.fleet.*;
import game.planet.*;
import game.planet.buildings.SpacePort;
import game.player.Player;
import game.research.*;
import game.ressource.ARessource;
import game.utils.NumberUtils;

/**
 * Servlet implementation class SpacePortServlet
 */
@WebServlet("/SpacePortServlet")
public class SpacePortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpacePortServlet() {
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
		
		// Required Objects
		HttpSession 			session = request.getSession();	
		Player 					player = (Player)session.getAttribute("player");	
		Planet					planet = player.getActivePlanet();
		SpacePort				spaceport = planet.getSpacePort();
		TechTree 				techtree = player.getTechTree();
		
		ArrayList<ASpaceShip> 	ships = new ArrayList<ASpaceShip>();
		ASpaceShip 				newShip = null;
		String 					shipName = "";
		int 					quantity = 0; 
		
		ArrayList<ARessource> 	shipCosts = new ArrayList<ARessource>();
		
		if (spaceport.getBuildQueue().size() == 0) { spaceport.setTimestamp(new Date());}
		
		// Loop to create all Ships given as String Array with Quantity Parameter
        Enumeration<String> parameterNames = request.getParameterNames(); 
        while (parameterNames.hasMoreElements()) { 
        	newShip = null;
            shipName = parameterNames.nextElement();            
            quantity = NumberUtils.stringAsInt(request.getParameterValues(shipName)[0]);
            
            // Creating and adding the ship to the List
            if (quantity > 0) { newShip = techtree.createShip(shipName, quantity);}
            if (newShip != null) { 
            	shipCosts = newShip.getCosts(quantity); 
            	if (player.decreaseRess(planet, shipCosts)) { ships.add(newShip); }
            }                        
        } 
        
        // Adding the list to the SpacePort buildQueue
        planet.getSpacePort().getBuildQueue().addAll(ships);
        
        // Redirecting ...
        response.sendRedirect(request.getContextPath() + "/spaceport.jsp");

	}// End doPost

}
