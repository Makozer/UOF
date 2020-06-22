<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<jsp:include page="./include/header.jsp" />

<div class="align center">
	Funktionale Anforderungen:
	<form action="./FunktionalSQLServlet" method="post">
		<button name="funktionalaktion" type=submit value="Erstellen"
			class="border rounded">Erstellen</button>
		<button name="funktionalaktion" type=submit value="Loeschen"
			class="border rounded">Löschen</button>
		<button name="funktionalaktion" type=submit value="Ausgeben"
			class="border rounded">Ausgeben</button>
	</form>
</div>

	${ result }

<h1>BeispielDaten</h1>

<table border="1">
	<tr>
		<th>Daten über den Spieler</th>
	</tr>
	<tr>
		<td>Spieler ID:</td>
		<td>${ player.getPersData().getId() }</td>
	</tr>
	<tr>
		<td>Spieler inGame Nick:</td>
		<td>${ player.getPersData().getDisplayName() }</td>
	</tr>
	<tr>
		<td>Spieler EMail:</td>
		<td>${ player.getPersData().getEmail() }</td>
	</tr>
	<tr>
		<td>Spieler Vorname:</td>
		<td>${ player.getPersData().getPreName() }</td>
	</tr>
	<tr>
		<td>Spieler Nachname:</td>
		<td>${ player.getPersData().getSurName() }</td>
	</tr>
	<tr>
		<td>Spieler RegistrierungsDatum:</td>
		<td>${ player.getPersData().getCreated() }</td>
	</tr>
	<tr>
		<td>Spieler Last Login:</td>
		<td>${ player.getPersData().getLastLogin() }</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<th>Daten über den Planeten</th>
	</tr>
	<tr>
		<td>Spieler Planetenanzahl:</td>
		<td>${ player.getPlanets().size() }</td>
	</tr>
	<tr>
		<td>Planet 1 Name:</td>
		<td>${ player.getPlanet(0).getName() }</td>
	</tr>
	<tr>
		<td>Planet 1 Koordinaten:</td>
		<td>Verschiedene Arten an Koordinaten zu kommen:<br /> ${ player.getPlanet(0).getCoords() }
			<br />${ player.getPlanet(0).getCoords().asCoords() } <br />${ player.getPlanet(0).getCoords().getGalaxy() }:${ player.getPlanet(0).getCoords().getSolarSystem() }:${ player.getPlanet(0).getCoords().getPlanetNumber() }
		</td>
	</tr>
	<tr>
		<td>Planet 1 ${ player.getPlanet(0).getIron().getName() }</td>
		<td>${ player.getPlanet(0).getIronMine().getRessourceValue() }</td>
	</tr>
	<tr>
		<td>Planet 1 ${ player.getPlanet(0).getRareEarth().getName() }</td>
		<td>${ player.getPlanet(0).getRareEarthMine().getRessourceValue() }
		</td>
	</tr>
	<tr>
		<td>Planet 1 ${ player.getPlanet(0).getWater().getName() }</td>
		<td>${ player.getPlanet(0).getFountain().getRessourceValue() }</td>
	</tr>
	<tr>
		<td>Planet 1 ${ player.getPlanet(0).getTritium().getName() }</td>
		<td>${ player.getPlanet(0).getTritiumFabric().getRessourceValue() }
		</td>
	</tr>
</table>
<br />
<table border="1">
	<tr>
		<th>Gebäude</th>
		<th>Level</th>
		<th>BauKosten</th>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getHeadQuarter().getName() }</td>
		<td>${ player.getPlanet(0).getHeadQuarter().getLevel() }</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getHeadQuarter().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getUniversity().getName() }</td>
		<td>${ player.getPlanet(0).getUniversity().getLevel() }</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getUniversity().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getSpacePort().getName() }</td>
		<td>${ player.getPlanet(0).getSpacePort().getLevel() }</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getSpacePort().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
</table>
<br />
<table border="1">
	<tr>
		<th>Gebäude</th>
		<th>Level</th>
		<th>RessourcenGenerierung</th>
		<th>BauKosten</th>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getIronMine().getName() }</td>
		<td>${ player.getPlanet(0).getIronMine().getLevel() }</td>
		<td>${ player.getPlanet(0).getIronMine().getResPerHour() }/h
			&nbsp;&nbsp;(${ player.getPlanet(0).getIronMine().getResPerSecond() }/s)</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getIronMine().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getRareEarthMine().getName() }</td>
		<td>${ player.getPlanet(0).getRareEarthMine().getLevel() }</td>
		<td>${ player.getPlanet(0).getRareEarthMine().getResPerHour() }/h
			&nbsp;&nbsp;(${ player.getPlanet(0).getRareEarthMine().getResPerSecond() }/s)</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getRareEarthMine().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getFountain().getName() }</td>
		<td>${ player.getPlanet(0).getFountain().getLevel() }</td>
		<td>${ player.getPlanet(0).getFountain().getResPerHour() }/h
			&nbsp;&nbsp;(${ player.getPlanet(0).getFountain().getResPerSecond() }/s)</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getFountain().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getTritiumFabric().getName() }</td>
		<td>${ player.getPlanet(0).getTritiumFabric().getLevel() }</td>
		<td>${ player.getPlanet(0).getTritiumFabric().getResPerHour() }/h
			&nbsp;&nbsp;(${ player.getPlanet(0).getTritiumFabric().getResPerSecond() }/s)</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getTritiumFabric().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
</table>
<br />
<table border="1">
	<tr>
		<th>Gebäude</th>
		<th>Level</th>
		<th>Nicht stehlbare RessourcenGrenze</th>
		<th>Max RessourcenGrenze</th>
		<th>BauKosten</th>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getIronStorage().getName() }</td>
		<td>${ player.getPlanet(0).getIronStorage().getLevel() }</td>
		<td>${ player.getPlanet(0).getIronStorage().getSaveCapacityAsString() }</td>
		<td>${ player.getPlanet(0).getIronStorage().getMaxCapacityAsString() }</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getIronStorage().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getRareEarthStorage().getName() }</td>
		<td>${ player.getPlanet(0).getRareEarthStorage().getLevel() }</td>
		<td>${ player.getPlanet(0).getRareEarthStorage().getSaveCapacityAsString() }</td>
		<td>${ player.getPlanet(0).getRareEarthStorage().getMaxCapacityAsString() }</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getRareEarthStorage().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getWaterStorage().getName() }</td>
		<td>${ player.getPlanet(0).getWaterStorage().getLevel() }</td>
		<td>${ player.getPlanet(0).getWaterStorage().getSaveCapacityAsString() }</td>
		<td>${ player.getPlanet(0).getWaterStorage().getMaxCapacityAsString() }</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getWaterStorage().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>
	<tr>
		<td>${ player.getPlanet(0).getTritiumStorage().getName() }</td>
		<td>${ player.getPlanet(0).getTritiumStorage().getLevel() }</td>
		<td>${ player.getPlanet(0).getTritiumStorage().getSaveCapacityAsString() }</td>
		<td>${ player.getPlanet(0).getTritiumStorage().getMaxCapacityAsString() }</td>
		<td><c:forEach var="cost"
				items="${player.getPlanet(0).getTritiumStorage().getCosts()}">
				<c:out value="${cost.getName()} : ${cost.getValue()}; " />
			</c:forEach></td>
	</tr>

</table>

<h3>Flugzeuge auf Planet 1</h3>
<table border="1">
	<tr>
		<th>Flugzeug</th>
		<th>Anzahl</th>
		<th>Attack</th>
		<th>Defense</th>
		<th>Speed</th>
		<th>Capacity</th>
		<th>Costs</th>
	</tr>
	<c:forEach var="ship"
		items="${player.getPlanet(0).getFleet().getFleet()}">
		<tr>
			<td><c:out value="${ship.getName()}" /></td>
			<td><c:out value="${ship.getQuantity()}" /></td>
			<td><c:out value="${ship.getAttackAsString()}" /></td>
			<td><c:out value="${ship.getDefenseAsString()}" /></td>
			<td><c:out value="${ship.getSpeedAsString()}" /></td>
			<td><c:out value="${ship.getCapacity()}" /></td>
			<td><c:forEach var="cost" items="${ship.getCosts()}">
					<c:out value="${cost.getName()} : ${cost.getValue()}; " />
				</c:forEach></td>
		</tr>
	</c:forEach>
</table>

<h3>Game Events:</h3>
<table border="1">
	<tr>
		<th>Zeitpunkt</th>
		<th>Art</th>
		<th>Koordinaten</th>
		<th>Ziel</th>
		<th>StartZeit</th>
		<th>Flotte</th>
		<th>Ressourcen</th>
	</tr>
	<c:forEach var="gameEvent" items="${player.getEventsSorted()}">
		<tr>
			<td><c:out value="${gameEvent.getEndTime()}" /></td>
			<td><c:out value="${gameEvent.getType()}" /></td>
			<td><c:out value="${gameEvent.getCoordinates().asCoords()}" /></td>
			<td><c:out value="${gameEvent.getTarget().asCoords()}" /></td>
			<td><c:out value="${gameEvent.getStartTime()}" /></td>
			<td>

				<table border="1">
					<tr>
						<th>Flugzeug</th>
						<th>Anzahl</th>
						<th>Attack</th>
						<th>Defense</th>
						<th>Speed</th>
						<th>Capacity</th>
					</tr>
					<c:forEach var="ship" items="${gameEvent.getFleet().getFleet()}">
						<tr>
							<td><c:out value="${ship.getName()}" /></td>
							<td><c:out value="${ship.getQuantity()}" /></td>
							<td><c:out value="${ship.getAttackAsString()}" /></td>
							<td><c:out value="${ship.getDefenseAsString()}" /></td>
							<td><c:out value="${ship.getSpeedAsString()}" /></td>
							<td><c:out value="${ship.getCapacity()}" /></td>
						</tr>
					</c:forEach>
				</table>

			</td>
			<td><c:forEach var="cost" items="${gameEvent.getRessource()}">
					<c:out value="${cost.getName()} : ${cost.getValue()}; " />
				</c:forEach></td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="./include/footer.jsp" />

