<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<jsp:include page="./include/header.jsp" />



<!--  um neue nachrichten zu verfassen -->
<h1>Neue Nachricht schreiben</h1>
<form method="post" action="./MessageServlet">
	<h2 class="error">${error}</h2>
	<table border="1">
		<tr>
			<th>Empfänger</th>
			<td><input type="text" size="255" maxlength="255" name="toPlayer" placeholder="Spielername" /></td>
		</tr>
		<tr>
			<th>Titel der Nachricht</th>
			<th><input type="text" name="title" size="30" maxlength="30" placeholder="Titel"
				value="${titelcontent}" /></th>
		</tr>
		<tr>
			<td colspan="2"><textarea name="message" cols="66" rows="12"
					placeholder="Deine Nachricht">${textcontent}</textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" /></td>
		</tr>
	</table>

</form>
<jsp:include page="./include/footer.jsp" />

