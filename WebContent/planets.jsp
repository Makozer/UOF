<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<jsp:include page="./include/header.jsp" />
<!-- Planetenuebersicht -->
<h1>Planeten von ${ player.getDisplayName() }</h1>
<div>
	<table border="1" style="width: 100%">
		<tr>
			<th>Nr</th>
			<th>Koordinaten</th>
			<th>Name</th>
		</tr>
		<c:forEach var="planet" items="${player.getPlanets()}" varStatus="Nr">
			<tr>
				<td>${Nr.index +1 }.</td>
				<td>${ planet.getCoords().asCoords() }</td>
				<td>${ planet.getName() }</td>
			</tr>
		</c:forEach>
	</table>
</div>
<jsp:include page="./include/footer.jsp" />