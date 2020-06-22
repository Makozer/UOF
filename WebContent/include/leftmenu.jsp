<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="../WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="./javascript/sidebar.js?<%Date date = new Date();
			out.print(date.toString());%>"></script>
<link rel="stylesheet" type="text/css"
	href="css/sidebar.css?<%out.print(date.toString());%>" />
</head>
<body>
	<div class="wrapper">
		<!-- Sidebar  -->
		<nav id="sidebar" class="border border-danger rounded">
			<div class="sidebar-header">
				<h3>Navigation</h3>
			</div>
			<div>
				<table class="planetResOv border">
					<tr>
						<td colspan="4" style="padding: 11px;">Koordinaten:
							${player.getActivePlanet().getCoords().asCoords()} <br>
						<img src="./assets/images/planet.png" alt="Tritium" title=""
							style="height: 1.5rem;"><br> PlanetName:
							${player.getActivePlanet().getName()}
						</td>
					</tr>
				</table>
			</div>
			<ul class="list-unstyled components">

				<li><a href="./overview.jsp">Übersicht</a></li>
				<li><a href="./buildings.jsp">Gebäude</a></li>
				<li><a href="./research.jsp">Forschung</a></li>
				<li><a href="./spaceport.jsp">Raumschiffproduktion</a></li>
				<!-- <li><a href="./defense.jsp">Verteidigung</a></li>  -->
				<li><a href="./planets.jsp">Planeten</a></li>
				<li><a href="./fleet.jsp">Flotte</a></li>

			</ul>


			<ul class="list-unstyled components">
				<li><a href="researchoverview.jsp">Forschungsübersicht</a></li>
				<li><a
					href="./GalaxyServlet?galaxy=${player.getActivePlanet().getCoords().getGalaxy()}&solarsystem=${player.getActivePlanet().getCoords().getSolarSystem()}">Galaxie</a></li>
				<li><a href="combatsim.jsp">Kampfsimulator</a></li>
				<li><a href="example.jsp">TESTSEITE</a></li>
			</ul>
		</nav>
	</div>
</body>
</html>