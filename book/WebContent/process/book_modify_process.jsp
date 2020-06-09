<%@page import="persistence.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//book_modify.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String code = request.getParameter("code");
	int price = Integer.parseInt(request.getParameter("price"));
	
	//DB작업 후 성공하면 book_selectAll.jsp
	BookDAO dao = new BookDAO();
	
	int result = dao.modify(code, price);
	
	if(result > 0){
		response.sendRedirect("../book_selectAll.jsp");
	}
	//실패하면 index.jsp
	else{
		response.sendRedirect("../index.jsp");
	}

%>
