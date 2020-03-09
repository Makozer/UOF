<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<table class="middle headnav">
<tr>
<td><a href="profil.jsp">Profil</a></td>
<td><a href="messages.jsp" class="${ player.hasNewMessage() ? "red" : ""}">Nachrichten</a></td>

<td><a href="./LogoutServler">Logout</a></td>

</tr>
</table>