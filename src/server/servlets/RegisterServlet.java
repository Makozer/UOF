package server.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import game.player.PersonalData;
import game.player.Player;
import game.research.TechTree;
import database.DBPeon;
import manager.FehlerManager;


@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String formDisplayname = request.getParameter("displayname"); 
		String formFirstname = request.getParameter("first_name"); 
		String formLastname = request.getParameter("last_name"); 
		String formEmail = request.getParameter("email"); 
		String formPassword = request.getParameter("password"); 
		String formcPassword = request.getParameter("confirm_password"); 
		String formCheckbox = request.getParameter("checkbox");

		String error = "";
		
		if(!formPassword.equals(formcPassword)) {
			error += "Passwoerter stimmen nicht ueberein, Eingabe wdh.! <br>";
		}
		
		if(error.equals("")) {
			HttpSession session = request.getSession();
			
				
			PersonalData persData = new PersonalData();
			
			persData.setDisplayName(formDisplayname);
			persData.setPreName(formFirstname);
			persData.setSurName(formLastname);
			persData.setEmail(formEmail);
			persData.setPassword(formPassword);
			
			
			
			//session.setAttribute("PersonalData", persData);
			session.setAttribute("Player", new Player(persData, new TechTree()));
			
			//session.setAttribute("success", DBPeon.updateBenutzer(persData));
			//request.setAttribute("message", meldung + " " + benutzer.toString());
			DBPeon.insertBenutzer(persData);
			request.getRequestDispatcher("profil.jsp").forward(request, response);
			
			//Ansonsten Eingaben zurueck zum Formular
		} else {
			request.setAttribute("statust", error);
			request.setAttribute("formDisplayname", formDisplayname);
			request.setAttribute("formFirstname", formFirstname);
			request.setAttribute("formLastname", formLastname);
			request.setAttribute("formEmail", formEmail);
			
			//request.setAttribute("formPassword", formPassword);
			//request.setAttribute("formcPassword", formcPassword);
			
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}	
	}
}
