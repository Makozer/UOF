package server.servlets.game;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import game.*;
import game.fleet.*;
import game.research.*;
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
		TechTree 				techtree = player.getTechTree();
		
		ArrayList<ASpaceShip> 	ships = new ArrayList<ASpaceShip>();
		ASpaceShip 				newShip = null;
		String 					shipName = "";
		int 					quantity = 0; 
		
		
		// Loop to create all Ships given as String Array with Quantity Parameter
        Enumeration<String> parameterNames = request.getParameterNames(); 
        while (parameterNames.hasMoreElements()) { 
        	newShip = null;
            shipName = parameterNames.nextElement();            
            quantity = NumberUtils.stringAsInt(request.getParameterValues(shipName)[0]);
            
            // TODO check if player has enough res on planet
            // Creating and adding the ship to the List
            if (quantity > 0) { newShip = techtree.createShip(shipName, quantity); }
            // TODO reduce res on player planet!
            if (newShip != null) { ships.add(newShip); }            
        } 
        
        // Adding the list to the SpacePort buildQueue
        player.getActivePlanet().getSpacePort().getBuildQueue().addAll(ships);
        response.sendRedirect(request.getContextPath() + "/spaceport.jsp");

	} // End doPost

}
