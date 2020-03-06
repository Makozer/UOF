<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="" method="post">
<jsp:include page="./include/leftmenu.jsp" />
<h1>Produktion von Raumschiffen</h1>
<h2>Erforschte Raumschiffe:</h2>
<table border="1">
	<tr><th>Erforschte SpezialRaumSchiffe</th><th>Anzahl</th></tr>
	<c:forEach var="s" items="${player.getTechtree().getResearchedSpecialSpaceShips()}">
		<tr><td><c:out value="${s.getName()}"/><br/><c:out value="${s.getCosts()}"/></td><td><input type="text" name="<c:out value="${s.getName()}"/>"></td></tr>	
	</c:forEach>
	<tr><th>Erforschte Tier 1 Raumschiffe</th><th>Anzahl</th></tr>
	<c:forEach var="s" items="${player.getTechtree().getResearchedT1SpaceShips()}">
		<tr><td><c:out value="${s.getName()}"/><br/><c:out value="${s.getCosts()}"/></td><td><input type="text" name="<c:out value="${s.getName()}"/>"></td></tr>	
	</c:forEach>
	<tr><th>Erforschte Tier 2 Raumschiffe</th><th>Anzahl</th></tr>
	<c:forEach var="s" items="${player.getTechtree().getResearchedT2SpaceShips()}">
		<tr><td><c:out value="${s.getName()}"/><br/><c:out value="${s.getCosts()}"/></td><td><input type="text" name="<c:out value="${s.getName()}"/>"></td></tr>	
	</c:forEach>
	<tr><th>Erforschte Tier 3 Raumschiffe</th><th>Anzahl</th></tr>
	<c:forEach var="s" items="${player.getTechtree().getResearchedT3SpaceShips()}">
		<tr><td><c:out value="${s.getName()}"/><br/><c:out value="${s.getCosts()}"/></td><td><input type="text" name="<c:out value="${s.getName()}"/>"></td></tr>	
	</c:forEach>
	<tr><th>Baubefehl:</th><th><input type="submit" value="Bauen"></th></tr>
</table>
</form>
</body>
</html>