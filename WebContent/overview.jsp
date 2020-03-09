<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<jsp:include page="./include/header.jsp" />

<h1>�bersicht</h1>

<table border="1">

<tr><th colspan="2">Feindliche Angriffe</th></tr>
<c:choose>
<c:when test="${player.getDefendEvents().size() > 0}">
	<c:forEach var="gameEvent" items="${player.getDefendEvents()}">
		<tr class="defend">
			<td><c:out value="${ dateUtils.getRemainingTimeAsString(gameEvent.getEndTime()) }"/></td>
			<td>Euer Planet ${gameEvent.getTarget().asCoords()} wird von ${gameEvent.getCoordinates().asCoords()} aus angriffen.</td>
		</tr>
	</c:forEach>
</c:when>    
<c:otherwise>
	<tr><td colspan="2">
	Keine gegnerischen Angriffe.
	</td></tr>
</c:otherwise>
</c:choose>

<tr><th colspan="2">Eigene Flotten</th></tr>
<c:choose>
<c:when test="${player.getAttackEvents().size() > 0}">
	<c:forEach var="gameEvent" items="${player.getAttackEvents()}">
		<tr>
			<td><c:out value="${ dateUtils.getRemainingTimeAsString(gameEvent.getEndTime()) }"/></td>
			<td>Euer Flotte von ${gameEvent.getCoordinates().asCoords()} greift ${gameEvent.getTarget().asCoords()} an.</td>
		</tr>
	</c:forEach>
</c:when>    
<c:otherwise>
	<tr><td colspan="2">
		Keine eigenen Flottenbewegungen.
	</td></tr>
</c:otherwise>
</c:choose>



<tr><th colspan="2">Rohstofftransporte</th></tr>
<c:choose>
<c:when test="${player.getTransportEvents().size() > 0}">
	<c:forEach var="gameEvent" items="${player.getTransportEvents()}">
		<tr>
			<td><c:out value="${ dateUtils.getRemainingTimeAsString(gameEvent.getEndTime()) }"/></td>
			<td>Eine Flotte transportiert von ${gameEvent.getCoordinates().asCoords()} aus Rohstoffe zum Planeten ${gameEvent.getTarget().asCoords()}.</td>		
		</tr>
	</c:forEach>
</c:when>    
<c:otherwise>
	<tr><td colspan="2">
		Keine Transportbewegungen.
	</td></tr>
</c:otherwise>
</c:choose>


<tr><th colspan="2">Geb�ude</th></tr>
<c:choose>
<c:when test="${player.getBuildingEvents().size() > 0}">
	<c:forEach var="gameEvent" items="${player.getBuildingEvents()}">
		<tr>
			<td><c:out value="${ dateUtils.getRemainingTimeAsString(gameEvent.getEndTime()) }"/></td>

			<td>Auf eurem Planeten ${gameEvent.getCoordinates().asCoords()} wird ${gameEvent.getBuildingName()} verbessert.</td>

			
			
		</tr>
	</c:forEach>
</c:when>    
<c:otherwise>
	<tr><td colspan="2">
		Keine Geb�ude werden aktuell gebaut.
	</td></tr>
</c:otherwise>
</c:choose>


 

</table>

<jsp:include page="./include/footer.jsp" />