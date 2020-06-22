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
		String query = request.getParameter("query"); 
		String output = "ayay";
		request.setAttribute("result", output);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		// 10 zufällige registrieren, ausgeben, zufällig löschen
	}
	
}
