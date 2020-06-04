<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div style="">


<ul>
<li><a href="Page?p=overview">Übersicht</a></li>
<li><a href="Page?p=buildings">Gebäude</a></li>
<li><a href="Page?p=research">Forschung</a></li>
<li><a href="Page?p=spaceport">Raumschiffproduktion</a></li>
<!-- <li><a href="defense.jsp">Verteidigung</a></li>  -->
<li><a href="planets.jsp">Planeten</a></li>
<li><a href="Page?p=fleet">Flotte</a></li>
</ul>
--- <br/>
<ul>
<li><a href="researchoverview.jsp">Forschungsübersicht</a></li>

<li><a href="./GalaxyServlet?galaxy=${player.getActivePlanet().getCoords().getGalaxy()}&solarsystem=${player.getActivePlanet().getCoords().getSolarSystem()}">Galaxie</a></li>
<li><a href="combatsim.jsp">Kampfsimulator</a></li>
<li><a href="example.jsp">TESTSEITE</a></li>
</ul>
</div>