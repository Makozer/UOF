<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<jsp:include page="./include/header.jsp" />

<h1>Planeten von ${ player.getDisplayName() }</h1>

<table border="1">
	<tr>
		<th>Koordinaten</th>
		<th>Name</th>
	</tr>
	<c:forEach var="planet" items="${player.getPlanets()}">
		<tr>
			<td>${ planet.getCoords().asCoords() }</td>
			<td>${ planet.getName() }</td>
		</tr>
	</c:forEach>
</table>
<jsp:include page="./include/footer.jsp" />