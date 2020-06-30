package server.servlets.game;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import database.DBEvent;
import database.DBUser;
import game.*;
import game.event.GameEvent;
import game.fleet.*;
import game.planet.*;
import game.player.Player;
import game.research.*;
import game.ressource.*;
import game.utils.NumberUtils;

/**
 * Servlet implementation class FleetServlet
 */
@WebServlet("/FleetServlet")
public class FleetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FleetServlet() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * The Fleet Servlet which handles Attacks or Ressource Transport requests by the User
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Required Objects
		HttpSession 			session = request.getSession();	
		Player 					player = (Player)session.getAttribute("player");	
		Planet					planet = player.getActivePlanet();
		Fleet					planetfleet = planet.getFleet();
		TechTree 				techtree = player.getTechTree();
		
		String 					type = request.getParameter("type");
		String					targetgalaxy = request.getParameter("galaxy");
		String					targetsolarsystem = request.getParameter("solarsystem");
		String					targetplanet = request.getParameter("planet");
		
		String					sIron = request.getParameter("iron");
		Iron					iron = null;
		String					sRare = request.getParameter("rare");
		RareEarth				rare = null;
		String					sWater = request.getParameter("water");
		Water					water = null;
		String					sTritium = request.getParameter("tritium");
		Tritium					tritium = null;
		ArrayList<ARessource> 	ressources = new ArrayList<ARessource>();
		
		Fleet					newFleet = new Fleet();
		ASpaceShip 				ship = null;
		String 					shipName = "";
		int 					quantity = 0; 
		Coordinates 			target = null;
		
		// Creating the target
        target = new Coordinates(targetgalaxy, targetsolarsystem, targetplanet);
        // Escaping if the Planet doesnt exist or if its the same planet where the ships start
        if (target.getGalaxy() == 0 || target.getPlanetNumber() == 0 || target.getSolarSystem() == 0) {
        	response.sendRedirect(request.getContextPath() + "/fleet.jsp");
        	return;
        }
        if (		planet.getCoords().getGalaxy() 			== target.getGalaxy() 
        		&& 	planet.getCoords().getSolarSystem() 	== target.getSolarSystem() 
        		&& 	planet.getCoords().getPlanetNumber() 	== target.getPlanetNumber()) { return; }
        
        // Getting the type 
        if (!type.equals("ATTACK") && !type.equals("TRANSPORT")) { return; }
        
        // Getting target player id
        int targetplayerid = DBUser.getPlayerIdByCoordinates(target);
        
        // Getting the ressources if its a transport
        if (type.equals("TRANSPORT")) {
        	iron = new Iron(sIron);
        	rare = new RareEarth(sRare);
        	water = new Water(sWater);
        	tritium = new Tritium(sTritium);
        	if (iron.getValue() > 0) {ressources.add(iron);}
        	if (rare.getValue() > 0) {ressources.add(rare);}
        	if (water.getValue() > 0) {ressources.add(water);}
        	if (tritium.getValue() > 0) {ressources.add(tritium);}
        }
        
        // Checking if Planet has enough ressources
        // TODO lowprio Maybe check with DB?
        if (type.equals("TRANSPORT") && !planet.hasRessources(ressources)) { return; }
		
		// Loop to create a fleet
        Enumeration<String> parameterNames = request.getParameterNames(); 
        while (parameterNames.hasMoreElements()) { 
        	ship = null;
            shipName = parameterNames.nextElement();            
            quantity = NumberUtils.stringAsInt(request.getParameterValues(shipName)[0]);
            
            System.out.println("p=" + shipName + "; q=" + quantity);
            
            // Creating and adding the ship to the List
            if (quantity > 0) { ship = ShipFabric.createShip(techtree, shipName, quantity);}
            if (ship != null) { 
            	if (planetfleet.hasShip(ship)) {newFleet.addShip(ship);} else { return; }  // TODO Error to User      	
            }                        
        } 
        
        if (newFleet.size() == 0) {
        	response.sendRedirect(request.getContextPath() + "/overview.jsp");
        	return;
        }
        
        // Checking if Planet has enough Tritium
        Tritium requiredFuel = TravelCalc.calculateCosts(planet.getCoords(), target, newFleet);
        ArrayList<ARessource> requiredRessources = new ArrayList<ARessource>();
        requiredRessources.add(requiredFuel);
        if (!planet.hasRessources(requiredRessources)) { return; }
        
        // Reducing Tritium on starting planet
        planet.decreaseRessources(requiredRessources);
        
        // Reducing Ships on Planet
        planet.getFleet().reduceFleet(newFleet);
        System.out.println(newFleet.toString());
        
        // Calculating the Time to travel
        Date arrivalTime = TravelCalc.calculateTime(planet.getCoords(), target, newFleet.getSpeed());
        
        // Adding the Event
        GameEvent event = new GameEvent(0, player.getPersData().getId(), targetplayerid, GameEvent.Type.valueOf(type), planet.getCoords(), target, "", newFleet, ressources, new Date(), arrivalTime);
        // DATABASE inform
        DBEvent.createEvent(event);
        player.addEvent(event);
        
        // Redirect
        response.sendRedirect(request.getContextPath() + "/overview.jsp");

	}

}
