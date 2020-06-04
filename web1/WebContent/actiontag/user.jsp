<%@page import="domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- User 클래스 사용하고 싶다면? --%>
<%
	User user = new User();
	user.setName("홍길동");
%>
<h1><%=user.getName()%></h1>