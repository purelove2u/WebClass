<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--내장 객체 : request --%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>userid : <%=request.getParameter("userid") %></h2>
	<h2>password : <%=request.getParameter("password") %></h2>
</body>
</html>