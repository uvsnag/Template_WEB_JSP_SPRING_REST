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
	 <div class ='title'>Query on :</div>
		<form method="GET" action="${pageContext.request.contextPath}/multi">
				
			 <input checked type="radio" id="table" name="queryOn" value="table">
			  <label for="table">Join table</label>
			  <input type="radio" id="flatten" name="queryOn" value="flatten">
			  <label for="flatten">Flattened Table</label>
			  <input type="radio" id="projection" name="queryOn" value="projection">
			  <label for="projection">Projection</label><br><br/>
			<div>
			Id:
				<select id="dep" name="deparId"  >
				  <option value="1">1</option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				  <option value="6">6</option>
				</select>
			</div>
			<div >
				<br/>
				<input type="submit" value="Search" /> 
			</div>
		</form>
	</div>
<script type="text/javascript">

window.onload = function() {
    var depId =${depId};
    var queryfrom ="${queryfrom}";
    var options= document.getElementById('dep').options;
    for (var i= 0, n= options.length; i < n ; i++) {
        if (options[i].value==depId) {
            document.getElementById("dep").selectedIndex = i;
            break;
        }
    }
    
    document.getElementById(queryfrom).checked = true;
    
}
</script>
<%@include file="commons/showsql.jsp" %>
 
<table class="tableInfo">
	<tr class ="row-title">
      <td>Employee Name</td>
      <td>Date Of Birth</td>
      <td>Address</td>
      <td>Dept Name</td>
      <td>Describe</td>
    </tr>
  <c:forEach var="emp" items="${empLists}">
    <tr>
      <td>${emp.empName}</td>
      <td>${emp.dateOfBirth}</td>
      <td>${emp.address}</td>
      <td>${emp.depName}</td>
      <td>${emp.describe}</td>
	  </tr>
  </c:forEach>
</table>
  
  
 
 </body>
</html>