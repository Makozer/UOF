package server.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.*;

@WebServlet("/FunktionalSQLServlet")
public class FunktionalSQLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aktion = request.getParameter("funktionalaktion"); 
		String result = "";
		switch (aktion) {
		case "Erstellen": 
			for (int i = 0; i < 10; i++) {
				database.utils.RandomPlayer.randomPlayer(); 
			}
			result = "10 Spieler hinzugefuegt";
			break; 
		case "Loeschen": 
			if (FunktionaleAnforderungen.getBenutzerAnzahl() > 0) {
					FunktionaleAnforderungen.deleteRandomUser();
					result = "zufaelliger spieler geloescht";
			} else {
				result = "Kein Spieler mehr zum loeschen da";
			}
			break; 
			
		case "Ausgeben": 
			result = FunktionaleAnforderungen.getBenutzer(); 
			break; 
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("example.jsp").forward(request, response);
	}
	
	
}
