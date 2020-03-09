<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>

<jsp:include page="./include/header.jsp" />
<form action="./MessageServlet" method="post">
<table border="1">
<tr><th>Zeitpunkt</th><th>Von</th><th>Titel</th><th>L�schen</th></tr>
<c:forEach var="msg" items="${ player.getMessages() }">
		<tr><td><c:out value="${msg.getTimestamp()}"/></td><td><c:out value="${msg.getFromName()}"/></td><td><c:out value="${msg.getTitle()}"/></td><td><button type="submit" name="msgdel" value="<c:out value="${b.getMsgId()}"/>">X</button></td></tr>	
</c:forEach>
</table>
</form>
<jsp:include page="./include/footer.jsp" />