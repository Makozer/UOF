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
Login: <input type="email" name="email"><br />
Password: <input type="password" name="password"><br />
<button type="submit">Login</button>
</form>
<br />
<a href="register.jsp">No account yet? Register!</a>


<jsp:include page="./include/footer.jsp" />