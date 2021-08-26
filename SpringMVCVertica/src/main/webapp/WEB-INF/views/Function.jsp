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
	<div class="frame">
		<form method="POST" action="${pageContext.request.contextPath}/function">
			<input type="text" name="input"  />
			<br/>
			<br/>
			<p>Result: ${input}</p>
			<div>
				<br /> <input type="submit" value="Submit" />
			</div>
		</form><br /> <br /> 
	CREATE OR REPLACE FUNCTION myzeroifnull(z INT) RETURN INT<br /> 
   AS BEGIN <br /> 
     RETURN (CASE WHEN (z IS NULL) THEN 1 ELSE z+1 END);<br /> 
   END
	</div>
	<%@include file="commons/showsql.jsp"%>

</body>
</html>