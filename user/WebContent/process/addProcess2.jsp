<%@page import="domain.UserVO"%>
<%@page import="persistence.UserDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addProcess2</title>
</head>
<body>
	<%
		//사용자가 입력한 정보 가져오기
		//userAdd.jsp
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String birthYear = request.getParameter("birthYear");
		String addr = request.getParameter("addr");
		String mobile = request.getParameter("mobile");
	
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		vo.setUsername(userName);
		vo.setBirthyear(Integer.parseInt(birthYear));
		vo.setAddr(addr);
		vo.setMobile(mobile);
				
		//사용자에게 결과 페이지 보여주기 - 전체 User화면 보여주기
		if(dao.userInsert(vo) > 0){
			response.sendRedirect("../index.jsp");
		}
	
	%>
</body>
</html>
