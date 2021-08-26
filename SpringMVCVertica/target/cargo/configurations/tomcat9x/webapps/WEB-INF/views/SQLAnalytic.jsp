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

	<%@include file="commons/header.jsp"%>


	<%@include file="commons/showsql.jsp"%>

	<table class="tableInfo">
		<tr class="row-title">
			<td>Index of department</td>
			<td>Dept Name</td>
			<td>Number of Employee</td>
		</tr>
		<c:forEach var="emp" items="${empLists}">
			<tr>
				<td>${emp.index}</td>
				<td>${emp.name}</td>
				<td>${emp.numOfEmployee}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>