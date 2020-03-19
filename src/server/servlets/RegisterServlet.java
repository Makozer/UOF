package server.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import game.player.PersonalData;
import game.player.Player;
import game.research.TechTree;
import game.utils.DateUtils;
import database.DBPeon;
import database.FehlerManager;
import database.NewPlayerManager;


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
			
				
			PersonalData pd = new PersonalData();
			System.out.println("Trying to register new user ... ");
			
			pd.setDisplayName(formDisplayname);
			pd.setPreName(formFirstname);
			pd.setSurName(formLastname);
			pd.setEmail(formEmail);
			Date testdate = new Date();
			pd.setBirthday(testdate);
			pd.setCreated(testdate);
			pd.setLastLogin(testdate);
			
			System.out.println(pd.toString());
			
			Player player = NewPlayerManager.createNewPlayer(pd, formPassword);
			if (player != null) {
				session.setAttribute("player", player);
				DateUtils dateUtils = new DateUtils();
				session.setAttribute("dateUtils", dateUtils);
				response.sendRedirect(request.getContextPath() + "/overview.jsp");
			} else {
				System.out.println("PLAYER WAS NULL IN REGISTERSERVLET!");
			}
			
			//session.setAttribute("Player", new Player(pd, new TechTree()));

			//DBPeon.insertBenutzer(pd);
			
			
			//Ansonsten Eingaben zurueck zum Formular
		} else {
			request.setAttribute("statust", error);
			request.setAttribute("formDisplayname", formDisplayname);
			request.setAttribute("formFirstname", formFirstname);
			request.setAttribute("formLastname", formLastname);
			request.setAttribute("formEmail", formEmail);

			
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}	
	}
	
}
