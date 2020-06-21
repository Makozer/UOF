<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/style.css">

</head>
<body class="main hubbly">

<div class="container">
	<div class="row">
		<div
			class="col-md-12 min-vh-100 d-flex flex-column justify-content-center">
			<div class="row">
				<div class="col-lg-6 col-md-8 mx-auto">
					<div class="card rounded shadow shadow-sm">
					<h3 class="error">${error}</h3>
						<div class="card-header">
							<h3 class="mb-0">Login</h3>
						</div>
						<div class="card-body">
							<form class="form" role="form" id="formLogin" action="./LoginServlet" method="post">
								<div class="form-group">
									<label for="uname1">Email</label> <input type="email" name="email"
										class="form-control form-control-lg rounded-0"
										id="email" required>
									<div class="invalid-feedback">Enter Email</div>
								</div>
								<div class="form-group">
									<label>Password</label> <input type="password" name="password"
										class="form-control form-control-lg rounded-0" id="pwd"
										required autocomplete="new-password">
									<div class="invalid-feedback">Enter your password!</div>
								</div>
								<button type="submit" class="btn btn-success btn-lg"
									id="btnLogin">Login</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="./include/footer.jsp" />
</body>