<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<jsp:include page="./include/header.jsp" />

<h1>Neue Nachricht schreiben</h1>
<form method="post" action="./NewMessageServlet">

<table border="1">
<tr><th>Empfänger</th><td><input type="text" name="toPlayer" /></td></tr>
<tr><th>Titel der Nachricht</th><th><input type="text" name="title" /></th></tr>
<tr><td><textarea name="message" cols="66" rows="6"></textarea></td></tr>
</table>

</form>
<jsp:include page="./include/footer.jsp" />