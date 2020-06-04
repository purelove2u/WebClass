<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="student" class="domain.Student"/>

<div>
이름 : <jsp:getProperty property="username" name="student"/>
</div>
<div>
학년 : <jsp:getProperty property="usergrade" name="student"/>
</div>
