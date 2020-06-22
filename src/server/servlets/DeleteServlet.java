/**
 * Servlet selbst selbstloeschung des Benutzers um - Cedric
 */

package server.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.FunktionaleAnforderungen;
import game.player.Player;

/**
 * Servlet implementation class DeleteServet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Player player = (Player) session.getAttribute("player");
		if (player == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}

		// cascade db
		String displayname = player.getDisplayName();
		if (FunktionaleAnforderungen.deleteUser(displayname)) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} else {
			String error = "could not delete account";
			request.setAttribute("errordelete", error);
			request.getRequestDispatcher("profil.jsp").forward(request, response);
		}
		;

	}

}
