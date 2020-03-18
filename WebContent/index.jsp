<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<jsp:include page="./include/header.jsp" />


<h1>Universe on Fire</h1>
<h2>Login</h2>
Login: <input type="text"><br />
Password: <input type="text">
<form action="./LoginServlet" method="post">
<button type="submit">Session holen</button>
</form>
<a href="register.jsp">No account yet? Register!</a>


<jsp:include page="./include/footer.jsp" />