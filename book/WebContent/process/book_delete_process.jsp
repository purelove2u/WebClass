<%@page import="persistence.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//book_delete.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String code = request.getParameter("code");
	
	//데이터베이스 작업 후 성공하면 book_selectAll.jsp
	BookDAO dao = new BookDAO();
	Boolean result = dao.bookDelete(code);
	
	if(result){
		response.sendRedirect("../book_selectAll.jsp");
	}
	//실패하면 index.jsp로 이동
	else{
		response.sendRedirect("../index.jsp");
	}
	
%>