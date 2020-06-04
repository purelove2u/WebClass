<%@page import="domain.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="student" class="domain.Student"/>
<jsp:setProperty property="*" name="student"/>
<%
	/* Student stud = new Student();
	stud.setUsername(request.getParameter("username"));
	stud.setUsergrade(Integer.parseInt(request.getParameter("usergrade"))); */
	
	//DB 작업하는 코드
	/* StudentDAO dao = new StudentDAO();
	dao.insert(stud); */	
%>
<div>
이름 : <jsp:getProperty property="username" name="student"/>
</div>
<div>
학년 : <jsp:getProperty property="usergrade" name="student"/>
</div>
<jsp:forward page="student_result.jsp"></jsp:forward>