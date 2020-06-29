<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<jsp:include page="./include/header.jsp" />
<!-- Uebersichtsseite des SPielers ueber seinen events -->
<h1>Übersicht</h1>
<div>
	<table border="1" style="width: 75%">

		<tr>
			<th colspan="2">Feindliche Angriffe</th>
		</tr>

		<c:choose>
			<c:when test="${player.getDefendEvents().size() > 0}">
				<c:forEach var="gameEvent" items="${player.getDefendEvents()}">
					<tr class="defend">
						<td><c:out
								value="${ dateUtils.getRemainingTimeAsString(gameEvent.getEndTime()) }" /></td>
						<td>Euer Planet ${gameEvent.getTarget().asCoords()} wird von
							${gameEvent.getCoordinates().asCoords()} aus angriffen.</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">Keine gegnerischen Angriffe.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
			<th colspan="2">Eigene Flotten</th>
		</tr>
		<c:choose>
			<c:when test="${player.getAttackEvents().size() > 0}">
				<c:forEach var="gameEvent" items="${player.getAttackEvents()}">
					<tr class="attack">
						<td><c:out
								value="${ dateUtils.getRemainingTimeAsString(gameEvent.getEndTime()) }" /></td>
						<td>Euer Flotte von ${gameEvent.getCoordinates().asCoords()}
							greift ${gameEvent.getTarget().asCoords()} an.</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">Keine eigenen Flottenbewegungen.</td>
				</tr>
			</c:otherwise>
		</c:choose>



		<tr>
			<th colspan="2">Rohstofftransporte</th>
		</tr>
		<c:choose>
			<c:when test="${player.getTransportEvents().size() > 0}">
				<c:forEach var="gameEvent" items="${player.getTransportEvents()}">
					<tr>
						<td><c:out
								value="${ dateUtils.getRemainingTimeAsString(gameEvent.getEndTime()) }" /></td>
						<td>Eine Flotte transportiert von
							${gameEvent.getCoordinates().asCoords()} aus Rohstoffe zum
							Planeten ${gameEvent.getTarget().asCoords()}.</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">Keine Transportbewegungen.</td>
				</tr>
			</c:otherwise>
		</c:choose>


		<tr>
			<th colspan="2">Gebäude</th>
		</tr>
		<c:choose>
			<c:when test="${player.getBuildingEvents().size() > 0}">
				<c:forEach var="gameEvent" items="${player.getBuildingEvents()}">
					<tr>
						<td><c:out
								value="${ dateUtils.getRemainingTimeAsString(gameEvent.getEndTime()) }" /></td>

						<td>Auf eurem Planeten
							${gameEvent.getCoordinates().asCoords()} wird
							${gameEvent.getBuildingName()} verbessert.</td>



					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">Keine Gebäude werden aktuell gebaut.</td>
				</tr>
			</c:otherwise>
		</c:choose>



		<tr>
			<th colspan="2">Forschung</th>
		</tr>
		<c:choose>
			<c:when test="${player.getResearchEvents().size() > 0}">
				<c:forEach var="gameEvent" items="${player.getResearchEvents()}">
					<tr>
						<td><c:out
								value="${ dateUtils.getRemainingTimeAsString(gameEvent.getEndTime()) }" /></td>

						<td>Auf eurem Planeten
							${gameEvent.getCoordinates().asCoords()} wird
							${gameEvent.getBuildingName()} erforscht.</td>



					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">Keine aktuelle Forschung im Gange.</td>
				</tr>
			</c:otherwise>
		</c:choose>




		<tr>
			<th colspan="2">Stationierte Flotte auf diesem Planet</th>
		</tr>
		<c:choose>
			<c:when
				test="${player.getActivePlanet().getFleet().getFleet().size() > 0}">
				<tr>
					<td colspan="2"><c:forEach var="ship"
							items="${player.getActivePlanet().getFleet().getFleet()}">		
			${ship.getName()} : ${ ship.getQuantity()}<br />
						</c:forEach></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">Aktuell sind keine RaumSchiffe vorhanden.</td>
				</tr>
			</c:otherwise>
		</c:choose>

		<tr>
			<th colspan="2">Raumschiffe in Bau</th>
		</tr>
		<c:choose>
			<c:when
				test="${player.getActivePlanet().getSpacePort().getBuildQueue().size() > 0}">
				<tr>
					<td colspan="2"><c:forEach var="ship"
							items="${player.getActivePlanet().getSpacePort().getBuildQueue()}">		
			${ship.getName()} : ${ ship.getQuantity()}<br />
						</c:forEach></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">Aktuell sind keine RaumSchiffe in Bau.</td>
				</tr>
			</c:otherwise>
		</c:choose>



	</table>
</div>
<jsp:include page="./include/footer.jsp" />