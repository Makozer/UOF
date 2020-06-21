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
		<nav id="sidebar">
			<div class="sidebar-header">
				<h3>Navigation</h3>
			</div>

			<ul class="list-unstyled components">

				<li><a href="Page?p=overview">Übersicht</a></li>
				<li><a href="Page?p=buildings">Gebäude</a></li>
				<li><a href="Page?p=research">Forschung</a></li>
				<li><a href="Page?p=spaceport">Raumschiffproduktion</a></li>
				<!-- <li><a href="defense.jsp">Verteidigung</a></li>  -->
				<li><a href="planets.jsp">Planeten</a></li>
				<li><a href="Page?p=fleet">Flotte</a></li>

			</ul>
			
			<div class="row">
				<ul>
					<li><a href="researchoverview.jsp">Forschungsübersicht</a></li>

					<li><a
						href="./GalaxyServlet?galaxy=${player.getActivePlanet().getCoords().getGalaxy()}&solarsystem=${player.getActivePlanet().getCoords().getSolarSystem()}">Galaxie</a></li>
					<li><a href="combatsim.jsp">Kampfsimulator</a></li>
					<li><a href="example.jsp">TESTSEITE</a></li>
				</ul>
			</div>

		</nav>
	</div>
</body>
</html>