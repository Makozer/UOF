<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>

<jsp:include page="./include/header.jsp" />

<h1>Überblick über Forschung</h1>
<table border="1" style="width: 100%">
<tr>
	<th>Name</th>
	<th>Typ</th>
	<th>Erforderliche Technologie</th>
	<th>Kosten für Stufe 1</th>
</tr>
<c:forEach var="r" items="${ player.getTechTree().getWholeResearch() }">
	<tr>
		<td>
			<c:out value="${r.getName()}" />
		</td>
		<td>
			<c:out value="${r.getType()}" />
		</td>
		<td>
			<table border="1">
			<c:forEach var="rq" items="${ r.getRequiredTech() }">
				<tr>
					<td>
						<c:out value="${rq.getName()}" />
					</td>
				</tr>				
			</c:forEach>
			</table>
		</td>
		<td>
			<table border="1">
			<c:forEach var="rc" items="${ r.getResearchCosts() }">
				<tr>
					<td>
						<c:out value="${rc.getName()}" /> : <c:out value="${rc.getValAsString()}" />
					</td>
				</tr>				
			</c:forEach>
			</table>
		</td>
	</tr>
</c:forEach>
</table>

<jsp:include page="./include/footer.jsp" />