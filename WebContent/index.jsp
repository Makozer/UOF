<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css?<% Date date = new Date(); out.print( date.toString()); %>"/>
<title>Universe on Fire</title>
</head>
<body>
<h1>Universe on Fire</h1>
<form action="./LoginServlet" method="post">
<button type="submit">Session holen</button>
</form>

</body>
</html>