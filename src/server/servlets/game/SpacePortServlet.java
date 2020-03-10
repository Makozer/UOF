package server.servlets.game;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
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
		int pVal = 0;
		
		ASpaceShip a = null; // Test
		
        Enumeration<String> parameterNames = request.getParameterNames(); 
        while (parameterNames.hasMoreElements()) { 
            String shipName = parameterNames.nextElement();
            ASpaceShip template = hasShip(allResearchedShips, shipName);
            String[] paramSValue = request.getParameterValues(shipName);
            
            try { pVal = Integer.parseInt(paramSValue[0]); } catch (NumberFormatException e) { e.printStackTrace(); }
            
            try {
            	System.out.println(template.getClass().getPackageName() + "." +  template.getClass().getSimpleName());
	            Class aClass = Class.forName(template.getClass().getPackageName() + "." + template.getClass().getSimpleName());//Class.forName("yourPackagePath" + shipName); // need to pass full class name here
	            a = (ASpaceShip)aClass.getDeclaredConstructor().newInstance();
	            a.setQuantity(pVal);
	            a.setTechtree(techtree);
	            ships.add(a);
            } catch (ClassNotFoundException e) {
            	e.printStackTrace();
            } catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} 
            

            break;
        }    
        
        for (ASpaceShip as : ships) System.out.println(as.getClass().getSimpleName());
        System.out.println("Ende");

	} // End doPost
	
	protected ASpaceShip hasShip(ArrayList<ASpaceShip> allShips, String shipName) {
		for (ASpaceShip s: allShips) {
			if (shipName.equals(s.getName())) {return s;}
		}
    	return null;
    }

}
