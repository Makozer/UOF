<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="WEB-INF/taglib/customtags.tld" prefix="my"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Profile view</title>
</head>
<body>
<jsp:include page="./include/header.jsp" />

<div class="container">
  <h2>Profil</h2>
 <form method="post" action="./ProfilServlet">
<table>
    <tr>
      <th>Anzeigename</th>
      <td><input type="text" name="displayname" value="${player.getPersData().getDisplayName()}"></td>
    </tr>
    <tr>
      <th>Vorname</th>
      <td><input type="text" name="prename" value="${player.getPersData().getPreName()}"></td>
    </tr>
    <tr>
      <th>Nachname</th>
      <td><input type="text" name="surname" value="${player.getPersData().getSurName()}"></td>
    </tr>
    <tr>
      <th>E-Mail (Login)</th>
      <td><input type="text" name="email" value="${player.getPersData().getEmail()}"></td>
    </tr>    
    <tr>
      <th>Geburtstag</th>
      <td><input type="date" name="bday" value="${player.getPersData().getBirthday()}"></td>
    </tr> 
    <tr>
      <th>Letzter Login</th>
      <td>${player.getPersData().getLastLogin()}</td>
    </tr> 
    <tr>
      <th>Account erstellt am</th>
      <td>${player.getPersData().getCreated()}</td>
    </tr>   
    <tr>
      <td colspan="2"><button type="submit">Daten ändern</button></td>
    </tr>   
</table>
</form>
</div>
<jsp:include page="./include/footer.jsp" />
</body>
</html>
