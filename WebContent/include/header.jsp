<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="../WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="./javascript/loader.js?"></script>

<!-- Styledokumente -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1">
<link rel="shortcut icon"
	href="assets/images/scienceandfiction-battle-99291-144x144.png"
	type="image/x-icon">
<link rel="preload" href="./css/style2.css">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="./css/icons.css">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/bootstrap-grid.min.css">
<link rel="stylesheet" href="./css/bootstrap-reboot.min.css">
<link rel="stylesheet" href="assets/tether/tether.min.css">
<link rel="stylesheet" href="assets/dropdown/css/style.css">
<link rel="stylesheet" href="assets/theme/css/style.css">
<link rel="stylesheet" href="./css/style2.css" type="text/css">



<title>Universe on Fire</title>
</head>

<!--  Hintergrundbild -->
<body onload="loader()" class="hubbly">


	<input type="hidden" name="refreshedpage"
		value="
<c:if test="${not empty player}" >			
${ update.pageRefresh() }
</c:if>
" />

	<c:if test="${not empty player}">
		<input type="hidden" id="ironpersecond"
			value="${player.getActivePlanet().getIronMine().getResPerSecond()}">
		<input type="hidden" id="rarepersecond"
			value="${player.getActivePlanet().getRareEarthMine().getResPerSecond()}">
		<input type="hidden" id="waterpersecond"
			value="${player.getActivePlanet().getFountain().getResPerSecond()}">
		<input type="hidden" id="tritiumpersecond"
			value="${player.getActivePlanet().getTritiumFabric().getResPerSecond()}">
	</c:if>


	<section class="menu cid-s2n8eesi32" id="menu1-1d">
		<div>
			<nav
				class="navbar border border-danger navbar-expand beta-menu navbar-dropdown align-items-center navbar-fixed-top navbar-toggleable-sm">
				

				<c:if test="${not empty player}">

					<div class="menu-logo">
						<div class="navbar-brand">
							<span class=""> <a href="overview.jsp"> <img
									src="assets/images/scienceandfiction-battle-99291-144x144.png"
									alt="Overview" title="" style="height: 3.5rem;">
							</a>
							</span> <span class="navbar-caption-wrap"><a
								class="navbar-caption text-white display-5" href="overview.jsp">
									UOF</a></span>
						</div>
					</div>

					<div class="collapse navbar-collapse">
						<ul class="navbar-nav nav-dropdown" data-app-modern-menu="true">
							<li class="nav-item"><a class="text-white display-4"> <img
									src="./assets/images/iron.jpg" alt="" title="Iron"
									style="height: 1.5rem;"> ${ player.getActivePlanet().getIron().getName() }:
									${player.getActivePlanet().getIronMine().getResValAsString()}
							</a></li>
							<li class="nav-item"><a class="text-white display-4"> <img
									src="./assets/images/re.png" alt="rareearth" title=""
									style="height: 1.5rem;"> ${ player.getActivePlanet().getRareEarth().getName() }:
									${player.getActivePlanet().getRareEarthMine().getResValAsString()}
							</a></li>
							<li class="nav-item"><a class="text-white display-4"> <img
									src="./assets/images/water-icon-flat-vector-illustration-260nw-360343787.jpg"
									alt="Water" title="" style="height: 1.5rem;"> ${ player.getActivePlanet().getWater().getName() }:
									${player.getActivePlanet().getFountain().getResValAsString()}
							</a></li>
							<li class="nav-item"><a class="text-white display-4"> <img
									src="./assets/images/39495-200.png" alt="Tritium" title=""
									style="height: 1.5rem;"> ${ player.getActivePlanet().getTritium().getName() }:
									${player.getActivePlanet().getTritiumFabric().getResValAsString()}
							</a></li>
						</ul>
						<div>
							<table class="middle headnav">
								<tr>
									<td class="btn btn-sm btn-link display-2 border"><img
										src="./assets/images/4092564-about-mobile-ui-profile-ui-user-website_114033.png"
										alt="Tritium" title="" style="height: 1.5rem;"> <a
										href="profil.jsp">Profil</a></td>

									<td class="btn 	btn-sm btn-link display-2 border"><img
										src="./assets/images/messageiconfree.png" alt="Tritium"
										title="" style="height: 1.5rem;"> <a href="messages.jsp"
										class="${ player.getInbox().hasNewMessage() ? "red" : ""}">
											Nachrichten</a></td>

								</tr>
							</table>
						</div>
						<div class="navbar-buttons pph-section-btn">
							<a class="btn btn-sm btn-primary text-nowrap display-4"
								href="./LogoutServlet">Logout </a>
						</div>
					</div>

				</c:if>

			</nav>
		</div>
	</section>


	<table class="main">
		<tr>
			<td colspan="3"><c:if test="${not empty player}">
					<jsp:include page="headmenu.jsp" />
				</c:if></td>
		</tr>
		<tr>
			<td style="width: 15%;"><br /> <c:if test="${not empty player}">
					<jsp:include page="leftmenu.jsp" />
				</c:if></td>
			<td style="text-align: center;"><c:if test="${not empty player}">
					<!--  ins leftmenue verschoben-->
					<table class="planetResOv">
						<tr>
							<td><br></td>
						</tr>
					</table>
				</c:if>