package server.servlets.game;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import game.*;
import game.fleet.*;
import game.fleet.tier1.*;
import game.research.*;

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
        // TODO Auto-generated constructor stub
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
		//TODO array von parametern auslesen
		// Flotte erstellen
		// als fleet in SpacePort einbauen
		
		
		// Benötigte Objekte
		HttpSession session = request.getSession();	
		Player player = (Player)session.getAttribute("player");		
		TechTree techtree = player.getTechTree();					// Ships need their TechTree
		ArrayList<ASpaceShip> ships = new ArrayList<ASpaceShip>();	// to save Ships which are build
		ArrayList<ASpaceShip> allResearchedShips = techtree.getAllResearchedShips();	// to save Ships which are build
		
		Falcon falcon = new Falcon(techtree, 1);
		//falcon.getClass().;
		
        Enumeration<String> parameterNames = request.getParameterNames(); 
        while (parameterNames.hasMoreElements()) { 
            String shipName = parameterNames.nextElement();
            String[] paramSValue = request.getParameterValues(shipName);
            int pVal = Integer.parseInt(paramSValue[0]);
            if (hasShip(allResearchedShips, shipName)) {
            	if (pVal > 0) {
            		//ASpaceShip newShip = new Class<falcon.getClass()>(techtree,1);
            	}
            }

 
            /*
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValue[i];

            }
            */
 
        }    
        

	} // End doPost
	
	protected boolean hasShip(ArrayList<ASpaceShip> allShips, String shipName) {
		for (ASpaceShip s: allShips) {
			if (shipName.equals(s.getName())) {return true;}
		}
    	return false;
    }

}
