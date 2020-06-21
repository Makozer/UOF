<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1">
<link rel="shortcut icon"
	href="assets/images/scienceandfiction-battle-99291-144x144.png"
	type="image/x-icon">
<meta name="description" content="">


<title>Homescreen</title>
<link rel="preload" href="./css/style2.css">
<link rel="stylesheet" href="./css/icons.css">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/bootstrap-grid.min.css">
<link rel="stylesheet" href="./css/bootstrap-reboot.min.css">
<link rel="stylesheet" href="assets/tether/tether.min.css">
<link rel="stylesheet" href="assets/dropdown/css/style.css">
<link rel="stylesheet" href="assets/theme/css/style.css">
<link rel="stylesheet" href="./css/style2.css" type="text/css">
</head>
<body>
	<section class="menu cid-s2n8eesi32" id="menu1-1d">



		<nav
			class="navbar navbar-expand beta-menu navbar-dropdown align-items-center navbar-fixed-top navbar-toggleable-sm">
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<div class="hamburger">
					<span></span> <span></span> <span></span> <span></span>
				</div>
			</button>
			<div class="menu-logo">
				<div class="navbar-brand">
					<span class="navbar-logo"> <a href="overview.jsp"> <img
							src="assets/images/scienceandfiction-battle-99291-144x144.png"
							alt="overview	" title="Galaxy on fire" style="height: 4.5rem;">
					</a>
					</span> <span class="navbar-caption-wrap"><a
						class="navbar-caption text-white display-5" href="index.jsp">
							Universe on Fire</a></span>
				</div>
			</div>

			<div class="navbar-buttons pph-section-btn">
				<a class="btn btn-sm btn-primary display-4" href="login.jsp">Already
					registered? Login now! </a>
			</div>

		</nav>
	</section>

	<section class="engine"></section>
	<section class="header6 cid-s2pt9V40FA pph-fullscreen"
		data-bg-video="https://www.youtube.com/watch?v=Fv6mrxB74Tk"
		id="header6-1f">

		<div class="container">
			<div class="row justify-content-md-center">
				<div class="pph-white col-md-10">
					<h1
						class="pph-section-title align-center pph-bold pb-3 pph-fonts-style display-1">
						Explore the Universe!</h1>
					<p class="pph-text align-center pb-3 pph-fonts-style display-5">
						A Multiplayer Browsergame in which you can build up,explore and
						fight!</p>
					<div class="pph-section-btn align-center">
						<a class="btn btn-md btn-primary display-4" href="register.jsp">Register
							now!</a>
					</div>
				</div>
			</div>
		</div>

	</section>

	<jsp:include page="./include/footer.jsp" />



</body>
</html>