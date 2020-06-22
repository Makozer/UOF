<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<jsp:include page="./include/header.jsp" />
<h1>Nachricht lesen</h1>
<br />
<br />
<!--  nachricht darstellen -->
<table border="1">
	<tr>
		<th colspan="2">${sendtime}</th>
	</tr>
	<tr>
		<th>Von</th>
		<th>${fromplayer}</th>
	</tr>
	<tr>
		<th>Titel</th>
		<th>${msgtitle}</th>
	</tr>
	<tr>
		<td colspan="2">${msgcontent}</td>
	</tr>
</table>

<jsp:include page="./include/footer.jsp" />