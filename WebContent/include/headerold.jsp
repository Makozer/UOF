<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="../WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="./javascript/loader.js?<% Date date = new Date(); out.print( date.toString()); %>"></script>
<link rel="stylesheet" type="text/css" href="css/style.css?<% out.print( date.toString()); %>"/>
<title>Universe on Fire</title>
</head>
<body onload="loader()">

<input type="hidden" name="refreshedpage" value="
<c:if test="${not empty player}" >			
${ update.pageRefresh() }
</c:if>
"/>
<c:if test="${not empty player}" >


<input type="hidden" id="ironpersecond" value="${player.getActivePlanet().getIronMine().getResPerSecond()}">
<input type="hidden" id="rarepersecond" value="${player.getActivePlanet().getRareEarthMine().getResPerSecond()}">
<input type="hidden" id="waterpersecond" value="${player.getActivePlanet().getFountain().getResPerSecond()}">
<input type="hidden" id="tritiumpersecond" value="${player.getActivePlanet().getTritiumFabric().getResPerSecond()}">
</c:if>
<p id="testp"></p>
<table class="main">
	<tr>
		<td colspan="3">
			<c:if test="${not empty player}" >
			<jsp:include page="headmenu.jsp" />
			</c:if>
		</td>
	</tr>
	<tr>
		<td style="width:15%;">
			<a href="index.jsp">index</a><br />
			<c:if test="${not empty player}" >			
			<jsp:include page="leftmenu.jsp" />
			</c:if>
		</td>
		<td style="text-align: center;">
			<c:if test="${not empty player}" >

			<table class="planetResOv">
				<tr>
					<td colspan="4" style="padding:11px;">${player.getActivePlanet().getCoords().asCoords()}  ${player.getActivePlanet().getName()} </td>
				</tr>
				<tr>				
					<td>${ player.getActivePlanet().getIron().getName() }:</td>
					<td>${ player.getActivePlanet().getRareEarth().getName() }:</td>
					<td>${ player.getActivePlanet().getWater().getName() }:</td>
					<td>${ player.getActivePlanet().getTritium().getName() }:</td>
				</tr>
				<tr>				
					<td id="iron">${player.getActivePlanet().getIronMine().getResValAsString()}</td>
					<td id="rare">${player.getActivePlanet().getRareEarthMine().getResValAsString()}</td>
					<td id="water">${player.getActivePlanet().getFountain().getResValAsString()}</td>
					<td id="tritium">${player.getActivePlanet().getTritiumFabric().getResValAsString()}</td>
				</tr>
			</table>
			</c:if>