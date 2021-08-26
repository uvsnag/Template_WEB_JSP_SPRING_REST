<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <head>
 <style><%@include file="/WEB-INF/views/css/style.css"%></style>
 <style><%@include file="/WEB-INF/lib/bootstrap.min.css"%></style>
 <style><%@include file="/WEB-INF/lib/font-awesome.min.css"%></style>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>
 
<%@include file="commons/header.jsp" %>

	<div class= "frame">
	 <div class ='title'>Edit Data:</div>
		<form method="POST" action="${pageContext.request.contextPath}/simple">
			<input type="hidden" name="type-update" value="update" />

			<%@include file="commons/formInfo.jsp"%>
		</form>
	</div>
<%@include file="commons/showsql.jsp" %>

<table class="tableInfo">
	<tr class ="row-title">
      <td>ID</td>
      <td>Name</td>
      <td>Date Of Birth</td>
      <td>Address</td>
      <td>Id Dep</td>
      <td>Edit</td>
    </tr>
  <c:forEach var="emp" items="${empLists}">
    <tr>
      <td>${emp.index}</td>
      <td>${emp.name}</td>
      <td>${emp.dateOfBirth}</td>
      <td>${emp.address}</td>
      <td>${emp.idDep}</td>
      <td>
      <form method="POST" action="${pageContext.request.contextPath}/simple">
		  <input type="hidden" name="type-update" value="delete" /> 
		 <input type="hidden" name="index" value="${emp.index}" />
		  <input type="submit" value="Delete" /> 
	 </form>
	 <form method="GET" action="${pageContext.request.contextPath}/simple">
		  <input type="hidden" name="type-update" value="get-update" /> 
		 <input type="hidden" name="index" value="${emp.index}" />
		  <input type="submit" value="Edit" /> 
	 </form>
	  </td>   
	  </tr>
  </c:forEach>
</table>
  
  
 
 </body>
</html>