<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	//info.jsp 에서 넘긴 값 가져오기(forward로 넘어올 때만 가능)
	String userid = request.getParameter("userid");
	out.print(userid);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>session값 가져오기: <%=session.getAttribute("userid") %></h1>
</body>
</html>