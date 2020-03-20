<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<jsp:include page="./include/header.jsp" />


<h1>Universe on Fire</h1>
<h2>Login</h2>
<form action="./LoginServlet" method="post">
<h3 class="error">${error}</h3>
<table>
<tr><td>EMail:</td><td><input type="email" name="email"></td></tr>
<tr><td>Password:</td><td><input type="password" name="password"></td></tr>
<tr><td>&nbsp;</td><td><button type="submit" style="width:111px;">Login</button></td></tr>
</table>
</form>
<br />
<a href="register.jsp" style="font-size:20px;">No account yet? Register!</a>
<br />
<a href="#" style="font-size:20px;">Forgot Password?</a>


<jsp:include page="./include/footer.jsp" />