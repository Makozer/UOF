<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<jsp:include page="./include/header.jsp" />

<h1>Kampfsimulator</h1>

<table border="1">
<tr><th>Name</th><th>Flotte Alpha</th><th>Flotte Beta</th></tr>

<tr><th colspan="3">Raumschiffe</th></tr>
<tr><td>Falcon</td><td><input type="number"></td><td><input type="number"></td></tr>

<tr><th colspan="3">Forschung</th></tr>
<tr><td>BAMAngriff</td><td><input type="number"></td><td><input type="number"></td></tr>
<tr><th colspan="3"><button type="submit">Kampfsimulation starten</button></th></tr>
</table>

<jsp:include page="./include/footer.jsp" />