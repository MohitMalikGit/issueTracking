<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="com.fil.issueTracking.model.Employee,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LogIn</title>
</head>
<body>
	<h1>List of All Employee</h1>
	
	<%
		out.println(request.getAttribute("list"));
	%>
	
	<form action="/findById" method="get">
	<input type="text" name="id"/>
	<button type="submit">Submit</button>
	</form>
	
	<%
	if(request.getAttribute("emp") != null){
		out.println(request.getAttribute("emp"));
	}
	
	%>
	
</body>
</html>