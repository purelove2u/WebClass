<%@page import="java.util.List"%>
<%@page import="domain.BookVO"%>
<%@page import="persistence.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//book_search.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String criteria = request.getParameter("criteria");
	String keyword = request.getParameter("keyword");
	
	//db작업 후 결과를 request에 담고
	BookDAO dao = new BookDAO();
	List<BookVO> search = dao.searchList(criteria, keyword);
	 
	request.setAttribute("search", search);
	
	//book_searchAll.jsp로 이동
	pageContext.forward("../book_searchAll.jsp");
	//pageContext.forward("../index.jsp");

%>