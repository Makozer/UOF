package server.servlets.game;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
 
        Enumeration<String> parameterNames = request.getParameterNames();
 
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            out.write(paramName);
            out.write("n");
 
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                out.write(paramValue);
                out.write(" | ");
            }
 
        }
 
        out.close();
	}

}
