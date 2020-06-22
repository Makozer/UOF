<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<jsp:include page="./include/header.jsp" />

<!-- Flottenuebersicht -->
<h1>Flotte</h1>
<form method="post" action="./FleetServlet">
	<table border="1">
		<tr>
			<th>Flugzeug</th>
			<th>Anzahl</th>
			<th></th>
		</tr>
		<c:forEach var="ship"
			items="${player.getActivePlanet().getFleet().getFleet()}">
			<tr>
				<td><c:out value="${ship.getName()}" /></td>
				<td><c:out value="${ship.getQuantity()}" /></td>
				<td><input type="text" size="30" maxlength="30" class="intinput"
					name="<c:out value="${ship.getName()}"/>"></td>
			</tr>
		</c:forEach>
		<br>
	</table>
	<h2>Art der Flotte</h2>

	<table>
		<tr>
			<td style="text-align: left;" colspan="4"><input type="radio"
				name="type" size="30" maxlength="30" value="ATTACK" checked="checked"> <label>
					Angriff</label> <br /> <input type="radio" name="type" value="TRANSPORT">
				<label> Transport von Rohstoffen</label></td>
		</tr>
		<tr>
			<th colspan="4">Rohstoffe:</th>
		</tr>
		<tr>
			<td>Eisen</td>
			<td>Seltene Erde</td>
			<td>Wasser</td>
			<td>Tritium</td>
		</tr>
		<tr>
			<td><input type="number"  size="30" maxlength="30" name="iron" class="intinput"></td>
			<td><input type="number"  size="30" maxlength="30" name="rare" class="intinput"></td>
			<td><input type="number"  size="30" maxlength="30" name="water" class="intinput"></td>
			<td><input type="number"  size="30" maxlength="30" name="tritium" class="intinput"></td>
		</tr>
	</table>

	<h2>Ziel:</h2>
	<table border="1">
		<tr>
			<th>Galaxie</th>
			<th>Sonnensystem</th>
			<th>Planet</th>
		</tr>
		<tr>
			<td><input type="text" size="30" maxlength="30" name="galaxy" class="intinput"></td>
			<td><input type="text" size="30" maxlength="30" name="solarsystem" class="intinput"></td>
			<td><input type="text" size="30" maxlength="30" name="planet" class="intinput"></td>
		</tr>
	</table>
	<p>
		<input type="submit" value="Flotte absenden">
	</p>
</form>
<jsp:include page="./include/footer.jsp" />