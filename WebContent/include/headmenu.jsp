<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- Nachrichtentext wird Rot bei vorhandener Nachricht -->
<table class="middle headnav">
	<tr>
		<td><a href="profil.jsp">Profil</a></td>
		<td><a href="messages.jsp"
			class="${ player.getInbox().hasNewMessage() ? "red" : ""}">Nachrichten</a></td>

		<td><a href="./LogoutServlet">Logout</a></td>

	</tr>
</table>