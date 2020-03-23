<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<jsp:include page="./include/header.jsp" />

<h1>Forschung</h1>
<c:if test = "${player.getActivePlanet().getUniversity().getLevel() > 0}">
<form method="post" action="./ResearchServlet">
<table border="1">
<tr><th colspan="2">Antriebsforschung</th></tr>



<c:forEach var="research" items="${ player.getTechTree().getResearchedPropulsion() }">
<tr><td><c:out value="${research.getName()}"/><p>Ausbaustufe: <c:out value="${player.getTechTree().getLevel(research.getName())}"/><br />
			<c:out value="${research.getDescription()}"/>
		</p><br />Benötigte Ressourcen:<br /><c:out value="${research.getResearchCosts()}"/></td><td>


<c:choose>
	<c:when test="${player.getActivePlanet().isResearchingName() == research.getName()}">
        		Diese Forschung wird gerade erforscht! <br />
        		Verbleibende Zeit: <c:out value="${player.getResearchEventByCoords(player.getActivePlanet().getCoords()).getRemainingTimeAsString()}"/> <br />
        		<button type="submit" name="research" value="<c:out value="${research.getName()}"/>">Forschung abbrechen</button>
	</c:when>
	<c:when test="${player.getActivePlanet().isResearching()}">
		Eine andere Forschung läuft gerade!
	</c:when>    
	<c:otherwise>
    	<p>Forschungszeit: <c:out value="${research.getTimeToResearchAsString(player.getActivePlanet().getUniversity().getLevel())}"/></p>
		<button type="submit" name="research" value="<c:out value="${research.getName()}"/>">Forschung beginnen</button>
	</c:otherwise>
</c:choose>


</td></tr>	
</c:forEach>


<tr><th colspan="2">Schiffsforschung</th></tr>
<c:if test = "${player.getActivePlanet().getSpacePort().getLevel() > 0}">

<tr><th colspan="2">Spezial Raumschiffe</th></tr>
<c:forEach var="research" items="${ player.getTechTree().getResearchedSpecialShips() }">
<tr><td><c:out value="${research.getName()}"/><br/>Ausbaustufe: <c:out value="${player.getTechTree().getLevel(research.getName())}"/><br />Benötigte Ressourcen:<br /><c:out value="${research.getResearchCosts()}"/></td><td>


<c:choose>
	<c:when test="${player.getActivePlanet().isResearchingName() == research.getName()}">
        		Diese Forschung wird gerade erforscht! <br />
        		Verbleibende Zeit: <c:out value="${player.getResearchEventByCoords(player.getActivePlanet().getCoords()).getRemainingTimeAsString()}"/> <br />
        		<button type="submit" name="research" value="<c:out value="${research.getName()}"/>">Forschung abbrechen</button>
	</c:when>
	<c:when test="${player.getActivePlanet().isResearching()}">
		Eine andere Forschung läuft gerade!
	</c:when>    
	<c:otherwise>
    	<p>Forschungszeit: <c:out value="${research.getTimeToResearchAsString(player.getActivePlanet().getUniversity().getLevel())}"/></p>
		<button type="submit" name="research" value="<c:out value="${research.getName()}"/>">Forschung beginnen</button>
	</c:otherwise>
</c:choose>


</td></tr>	
</c:forEach>

<tr><th colspan="2">Standard Raumschiffe</th></tr>
<c:forEach var="research" items="${ player.getTechTree().getResearchedT1Ships() }">
<tr><td><c:out value="${research.getName()}"/><br/>Ausbaustufe: <c:out value="${player.getTechTree().getLevel(research.getName())}"/><br />Benötigte Ressourcen:<br /><c:out value="${research.getResearchCosts()}"/></td><td>


<c:choose>
	<c:when test="${player.getActivePlanet().isResearchingName() == research.getName()}">
        		Diese Forschung wird gerade erforscht! <br />
        		Verbleibende Zeit: <c:out value="${player.getResearchEventByCoords(player.getActivePlanet().getCoords()).getRemainingTimeAsString()}"/> <br />
        		<button type="submit" name="research" value="<c:out value="${research.getName()}"/>">Forschung abbrechen</button>
	</c:when>
	<c:when test="${player.getActivePlanet().isResearching()}">
		Eine andere Forschung läuft gerade!
	</c:when>    
	<c:otherwise>
    	<p>Forschungszeit: <c:out value="${research.getTimeToResearchAsString(player.getActivePlanet().getUniversity().getLevel())}"/></p>
		<button type="submit" name="research" value="<c:out value="${research.getName()}"/>">Forschung beginnen</button>
	</c:otherwise>
</c:choose>


</td></tr>	
</c:forEach>

</c:if>
<c:if test = "${player.getActivePlanet().getSpacePort().getLevel() == 0}">
<tr><th colspan="2">Ihr benötigt erst einen SpacePort auf diesem Planeten um an Raumschiffen zu forschen!</th></tr>
</c:if>
</table>
</form>
</c:if>
<c:if test = "${player.getActivePlanet().getUniversity().getLevel() == 0}">
<h2>Ihr benötigt erst eine Universität auf diesem Planeten um zu forschen!</h2>
</c:if>
<jsp:include page="./include/footer.jsp" />