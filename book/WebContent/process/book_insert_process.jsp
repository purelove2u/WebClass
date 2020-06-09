<%@page import="domain.BookVO"%>
<%@page import="persistence.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//book_inser.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String code = request.getParameter("code");
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String price = request.getParameter("price");
	
	//db작업 후 성공하면 전체 리스트 보여주기
	BookDAO dao = new BookDAO();
	BookVO vo = new BookVO();
	vo.setCode(code);
	vo.setTitle(title);
	vo.setWriter(writer);
	vo.setPrice(Integer.parseInt(price));
	
	int result = dao.bookInsert(vo);
	
	if(result > 0) {
		response.sendRedirect("../book_selectAll.jsp");
	}
	
	//실패하면 book_insert.jsp로 돌려보내기
	else{
		response.sendRedirect("../index.jsp");
	}
	
	
%>