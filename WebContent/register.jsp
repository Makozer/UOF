<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<!DOCTYPE html>
<html>
<head>
<title>Register</title>
</head>
<body>

<jsp:include page="./include/header.jsp" />
${statust}


<div class="signup-form" style = "position:relative; left:-90px;" >
    <form action="Register" method="post">
		<h2>Universe on Fire: Register</h2>
		<br></br>
		<p class="hint-text ">Create your account</p>
		<div class="form-group">
        	<input type="text" class="form-control" name="displayname" value="${formDisplayname}" placeholder="Displayname" required="required">
        </div>
        <div class="form-group">
			<div class="row">
				<div class="col-sm-6"><input type="text" class="form-control" name="first_name" value="${formFirstname}" placeholder="First Name" required="required"></div>
				<div class="col-sm-6"><input type="text" class="form-control" name="last_name" value="${formLastname}" placeholder="Last Name" required="required"></div>
			</div>        	
        </div>
        <div class="form-group">
        	<input type="email" class="form-control" name="email" value="${formEmail}" placeholder="Email" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name="password" value="${formPassword}" placeholder="Password" required="required">
        </div>
		<div class="form-group">
            <input type="password" class="form-control" name="confirm_password" value="${formcPassword}" placeholder="Confirm Password" required="required">
        </div>        
        <div class="form-group">
			<label class="checkbox-inline"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
		</div>
		<div class="form-group">
            <button type="submit" class="btn btn-primary btn-lg btn-block">Register Now</button>
        </div>
    </form>
	<div class="text-center">Already have an account? <a href="login.jsp">Sign in</a></div>
</div>

<jsp:include page="./include/footer.jsp" />
</body>
</html>
