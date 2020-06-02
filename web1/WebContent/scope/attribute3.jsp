<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// attribute2.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String email = request.getParameter("email");
	String home = request.getParameter("home");
	String tel = request.getParameter("tel");
	
	// 가져온 값을 session 영역에 담기
	//변수 안쓰고 아래처럼도 가능
	//session.setAttribute("email", request.getParameter("email"));
	request.setAttribute("email", email);
	request.setAttribute("home", home);
	request.setAttribute("tel", tel);
	//위에는 request로 변환시켜봄
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=session.getAttribute("name") %> 님 반갑습니다.</h1>
	<h2><a href="attribute4.jsp">확인하러 가기</a></h2>
</body>
</html>