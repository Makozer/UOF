<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<jsp:include page="./include/header.jsp" />

<h1>Galaxieübersicht:</h1>
<form method="get" action="./GalaxyServlet">
	<table border="1">
		<tr>
			<th>Galaxie:</th>
			<th><input type="number" size="30" maxlength="30" name="galaxy"
				value="${galaxy > 0 ? galaxy : player.getActivePlanet().getCoords().getGalaxy()}"></th>
		</tr>
		<tr>
			<th>Sonnensystem:</th>
			<th><input type="number" size="30" maxlength="30" name="solarsystem"
				value="${solarsystem > 0 ? solarsystem : player.getActivePlanet().getCoords().getSolarSystem()}"></th>
		</tr>
		<tr>
			<th colspan="2"><a
				href="./GalaxyServlet?galaxy=${galaxy}&solarsystem=${solarsystem - 1}">
					<button type="button" style="margin-right: 22px"><--</button>
			</a>
				<button type="submit">Aktualisieren</button> <a
				href="./GalaxyServlet?galaxy=${galaxy}&solarsystem=${solarsystem + 1}"><button
						style="margin-left: 22px" type="button">--></button></a></th>
		</tr>
		<tr>
			<th>Planet</th>
			<th>Name</th>
		</tr>
		<c:forEach var="name" items="${output}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><c:out value="${name}" /></td>
			</tr>
		</c:forEach>
	</table>
</form>
<h2 class="error">${error}</h2>

<jsp:include page="./include/footer.jsp" />