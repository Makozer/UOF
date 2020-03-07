<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="./SpacePortServlet" method="post">
<jsp:include page="./include/leftmenu.jsp" />
<h1>Produktion von Raumschiffen auf ${ player.getActivePlanet().getName() }</h1>
<p>HIER KOMMT DIE LISTE DER AKTUELL ZU BAUENDEN RAUMSCHIFFE</p>
<h2>Erforschte Raumschiffe:</h2>
<table border="1">
	<tr><th>Erforschte SpezialRaumSchiffe</th><th>Anzahl</th></tr>
	<c:forEach var="s" items="${ player.getTechTree().getResearchedSpecialSpaceShips() }">
		<tr><td><c:out value="${s.getName()}"/><br/><c:out value="${s.getCosts()}"/></td><td><input type="text" name="<c:out value="${s.getName()}"/>"></td></tr>	
	</c:forEach>
	<tr><th>Erforschte Tier 1 Raumschiffe</th><th>Anzahl</th></tr>
	<c:forEach var="s" items="${ player.getTechTree().getResearchedT1SpaceShips() }">
		<tr><td><c:out value="${s.getName()}"/><br/><c:out value="${s.getCosts()}"/></td><td><input type="text" name="<c:out value="${s.getName()}"/>"></td></tr>	
	</c:forEach>
	<tr><th>Erforschte Tier 2 Raumschiffe</th><th>Anzahl</th></tr>
	<c:forEach var="s" items="${ player.getTechTree().getResearchedT2SpaceShips() }">
		<tr><td><c:out value="${s.getName()}"/><br/><c:out value="${s.getCosts()}"/></td><td><input type="text" name="<c:out value="${s.getName()}"/>"></td></tr>	
	</c:forEach>
	<tr><th>Erforschte Tier 3 Raumschiffe</th><th>Anzahl</th></tr>
	<c:forEach var="s" items="${ player.getTechTree().getResearchedT3SpaceShips() }">
		<tr><td><c:out value="${s.getName()}"/><br/><c:out value="${s.getCosts()}"/></td><td><input type="text" name="<c:out value="${s.getName()}"/>"></td></tr>	
	</c:forEach>
	<tr><th>Baubefehl:</th><th><input type="submit" value="Bauen"></th></tr>
</table>
</form>
</body>
</html>