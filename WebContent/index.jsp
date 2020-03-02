<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css?<% Date date = new Date(); out.print( date.toString()); %>"/>
<title>Universe on Fire</title>
</head>
<body>

<form action="./LoginServlet" method="post">
<button type="submit">Session holen</button>
</form>
<p>
<table>
<tr><td>Spieler ID:</td><td>${ player.getId() }</td></tr>
<tr><td>Spieler inGame Nick:</td><td> ${ player.getDisplayName() }</td></tr>
<tr><td>Spieler EMail:</td><td>  ${ player.getEmail() }</td></tr>
<tr><td>Spieler Vorname:</td><td>  ${ player.getPreName() }</td></tr>
<tr><td>Spieler Nachname: </td><td> ${ player.getSurName() }</td></tr>
<tr><td>Spieler RegistrierungsDatum:</td><td>  ${ player.getCreated() }</td></tr>
<tr><td>Spieler Last Login:</td><td>  ${ player.getLastLogin() }</td></tr>
<tr><td>Leer</td></tr>
<tr><td>Spieler Planetenanzahl: </td><td>${ player.getPlanets().size() } </td></tr>
<tr><td>Spieler Planet 1 Name:</td><td> ${ player.getPlanet(0).getName() } </td></tr>
<tr><td>Spieler Planet 1 Koordinaten:</td><td> ${ player.getPlanet(0).getCoords() } ${ player.getPlanet(0).getCoords().asCoords() } ${ player.getPlanet(0).getCoords().getGalaxy() }:${ player.getPlanet(0).getCoords().getSolarSystem() }:${ player.getPlanet(0).getCoords().getPlanetNumber() }  </td></tr>

</p>


</body>
</html>