<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<jsp:include page="./include/header.jsp" />

<form action="./BuildingServlet" method="post">
<h1>Gebäude auf ${ player.getActivePlanet().getName()} (${player.getActivePlanet().getCoords().asCoords()})</h1>
<h2 class="error">${error}</h2>
	<div class="table" >
		<table border="1" style= "width:100%">
			<tr>
				<th colspan="2">Standard Gebäude</th>
			</tr>
			<c:forEach var="b"
				items="${player.getActivePlanet().getBasicBuildings()}">
				<tr>
					<td>
						<h4>
							<c:out value="${b.getName()}" />
						</h4>
						<p>
							Ausbaustufe:
							<c:out value="${b.getLevel()}" />
							<br />
							<c:out value="${b.getDescription()}" />
						</p>
						<p>
							Benötigte Ressourcen zum bauen vom nächsten Level:<br />
							<c:forEach var="cost" items="${b.getBuildCosts()}">
								<c:out value="${cost.getName()}: ${cost.getValue()}; " />
							</c:forEach>
						</p>
					</td>
					<td><c:choose>
							<c:when
								test="${player.getActivePlanet().isBuildingName() == b.getName()}">
        		Dieses Gebäude wird gerade erweitert! <br />
        		Verbleibende Zeit: <c:out
									value="${player.getBuildEventByCoords(player.getActivePlanet().getCoords()).getRemainingTimeAsString()}" />
								<br />
								<button type="submit" name="building" class="border rounded"
									value="<c:out value="${b.getName()}"/>">Bau abbrechen</button>
							</c:when>
							<c:when test="${player.getActivePlanet().isBuilding()}">
        		Ein anderes Gebäude ist in Bau!
   			 </c:when>
							<c:otherwise>
								<p>Bauzeit: ${b.getTimeToBuildAsString()}</p>
								<button type="submit" name="building" class="border rounded"
									value="<c:out value="${b.getName()}"/>">Bau beginnen</button>
							</c:otherwise>
						</c:choose> <br /></td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="2">Angriff / Verteidigung</th>
			</tr>
			<c:forEach var="b"
				items="${player.getActivePlanet().getWarBuildings()}">
				<tr>
					<td>
						<h4>
							<c:out value="${b.getName()}" />
						</h4>
						<p>
							Ausbaustufe:
							<c:out value="${b.getLevel()}" />
							<br />
							<c:out value="${b.getDescription()}" />
						</p>
						<p>
							Benötigte Ressourcen zum bauen vom nächsten Level:<br />
							<c:forEach var="cost" items="${b.getBuildCosts()}">
								<c:out value="${cost.getName()}: ${cost.getValue()}; " />
							</c:forEach>
						</p>
					</td>
					<td><c:choose>
							<c:when
								test="${player.getActivePlanet().isBuildingName() == b.getName()}">
        		Dieses Gebäude wird gerade erweitert! <br />
        		Verbleibende Zeit: <c:out
									value="${player.getBuildEventByCoords(player.getActivePlanet().getCoords()).getRemainingTimeAsString()}" />
								<br />
								<button type="submit" name="building" class="border rounded"
									value="<c:out value="${b.getName()}"/>">Bau abbrechen</button>
							</c:when>
							<c:when test="${player.getActivePlanet().isBuilding()}">
        		Ein anderes Gebäude ist in Bau!
   			 </c:when>
							<c:otherwise>
								<p>Bauzeit: ${b.getTimeToBuildAsString()}</p>
								<button type="submit" name="building" class="border rounded"
									value="<c:out value="${b.getName()}"/>">Bau beginnen</button>
							</c:otherwise>
						</c:choose> <br /></td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="2">Ressourcen Generation</th>
			</tr>
			<c:forEach var="b"
				items="${player.getActivePlanet().getResBuildings()}">
				<tr>
					<td>
						<h4>
							<c:out value="${b.getName()}" />
						</h4>
						<p>
							Ausbaustufe:
							<c:out value="${b.getLevel()}" />
							<br />
							<c:out value="${b.getDescription()}" />
						</p>
						<p>
							Benötigte Ressourcen zum bauen vom nächsten Level:<br />
							<c:forEach var="cost" items="${b.getBuildCosts()}">
								<c:out value="${cost.getName()}: ${cost.getValue()}; " />
							</c:forEach>
						</p>
					</td>
					<td><c:choose>
							<c:when
								test="${player.getActivePlanet().isBuildingName() == b.getName()}">
        		Dieses Gebäude wird gerade erweitert! <br />
        		Verbleibende Zeit: <c:out
									value="${player.getBuildEventByCoords(player.getActivePlanet().getCoords()).getRemainingTimeAsString()}" />
								<br />
								<button type="submit" name="building" class="border rounded"
									value="<c:out value="${b.getName()}"/>">Bau abbrechen</button>
							</c:when>
							<c:when test="${player.getActivePlanet().isBuilding()}">
        		Ein anderes Gebäude ist in Bau!
   			 </c:when>
							<c:otherwise>
								<p>Bauzeit: ${b.getTimeToBuildAsString()}</p>
								<button type="submit" name="building" class="border rounded"
									value="<c:out value="${b.getName()}"/>">Bau beginnen</button>
							</c:otherwise>
						</c:choose> <br /></td>
				</tr>
			</c:forEach>
			<tr>
				<th colspan="2">Ressourcen Speicher</th>
			</tr>
			<c:forEach var="b"
				items="${player.getActivePlanet().getResStorageBuildings()}">
				<tr>
					<td>
						<h4>
							<c:out value="${b.getName()}" />
						</h4>
						<p>
							Ausbaustufe:
							<c:out value="${b.getLevel()}" />
							<br />
							<c:out value="${b.getDescription()}" />
						</p>
						<p>
							Benötigte Ressourcen zum bauen vom nächsten Level:<br />
							<c:forEach var="cost" items="${b.getBuildCosts()}">
								<c:out value="${cost.getName()}: ${cost.getValue()}; " />
							</c:forEach>
						</p>
					</td>
					<td><c:choose>
							<c:when
								test="${player.getActivePlanet().isBuildingName() == b.getName()}">
        		Dieses Gebäude wird gerade erweitert! <br />
        		Verbleibende Zeit: <c:out
									value="${player.getBuildEventByCoords(player.getActivePlanet().getCoords()).getRemainingTimeAsString()}" />
								<br />
								<button type="submit" name="building" class="border rounded"
									value="<c:out value="${b.getName()}"/>">Bau abbrechen</button>
							</c:when>
							<c:when test="${player.getActivePlanet().isBuilding()}">
        		Ein anderes Gebäude ist in Bau!
   			 </c:when>
							<c:otherwise>
								<p>Bauzeit: ${b.getTimeToBuildAsString()}</p>
								<button type="submit" name="building" class="border rounded"
									value="<c:out value="${b.getName()}"/>">Bau beginnen</button>
							</c:otherwise>
						</c:choose> <br /></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</form>



<jsp:include page="./include/footer.jsp" />

