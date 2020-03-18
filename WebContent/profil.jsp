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
  <h3>profile here</h2>
 
<table class="table table-striped table-dark table-hover">
  <thead>
    <tr>
      <th>1</th>
      <th>2</th>
      <th>change</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">Displayname</th>
      <td>1</td>
      <td>1</td>
    </tr>
    <tr>
      <th scope="row">surname</th>
      <td>2</td>
      <td>2</td>
    </tr>
    <tr>
      <th scope="row">lastname</th>
      <td>3</td>
      <td>3</td>
    </tr>
    <tr>
      <th scope="row">email</th>
      <td>4</td>
      <td>4</td>
    </tr>
    <tr>
      <th scope="row">consent</th>
      <td>5</td>
      <td>5</td>
    </tr>
    <tr>
      <th scope="row">delete</th>
      <td>6</td>
      <td>6</td>
    </tr>            
  </tbody>
</table>

</div>
<jsp:include page="./include/footer.jsp" />
</body>
</html>
