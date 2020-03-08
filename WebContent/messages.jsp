<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Nachrichten</h1>
<jsp:include page="./include/leftmenu.jsp" />
<c:forEach var="msg" items="${ player.getMessages() }">
		<tr><td><c:out value="${msg.getTitle()}"/></td></tr>	
</c:forEach>

</body>
</html>