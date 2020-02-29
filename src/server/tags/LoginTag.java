package server.tags;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LoginTag extends SimpleTagSupport {

	private String loginForm = "<div><form method=\"post\" action=\"LoginServlet\">"
			+ "<input type=\"email\" name=\"email\" placeholder=\"E-Mail\" \\><br />"
			+ "<input type=\"password\" name=\"password\" placeholder=\"Password\" \\><br />"
			+ "<button class=\"btn\" type=\"submit\">Login</button>"
			+ "</div>";
	
	public void doTag() {
		PageContext pageContext = (PageContext) getJspContext();
		HttpSession session = pageContext.getSession();
		JspWriter out = getJspContext().getOut();
		
		String text = "";
		
		if (session.getAttribute("user") == null) {
			text = loginForm;
		} else {
			text = "Hallo :)";
		}
		
		try {
			out.append(text);
		} catch (IOException e) {
			System.out.println("Writer im LogoutButtonTag konnte nicht gestartet werden.");
		}
	}
	
}
