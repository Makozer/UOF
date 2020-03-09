<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<jsp:include page="./include/header.jsp" />

<h1>Flotte</h1>
<table border="1">
<tr><th>Flugzeug</th><th>Anzahl</th></tr>
<c:forEach var="ship" items="${player.getActivePlanet().getFleet().getFleet()}"><tr><td><c:out value="${ship.getName()}"/></td><td><c:out value="${ship.getQuantity()}"/></td></tr></c:forEach>
</table>

<jsp:include page="./include/footer.jsp" />