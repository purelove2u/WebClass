<%@page import="domain.MemberVO"%>
<%@page import="persistence.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//leaveForm.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	
	//leaveForm.jsp에서 세션불러서 값을 넣어놨으므로 아래처럼 다시 안해도 getparameger로 가져올 수 있다.
	//MemberVO login = (MemberVO)session.getAttribute("login");
	//String userid = login.getUserid();

	String userid = request.getParameter("userid");
	String password = request.getParameter("current_password");
	
	//DB처리 후 탈퇴 성공하면 세션제거 후 index페이지로 이동
	MemberDAO dao = new MemberDAO();
	int result = dao.leave(userid, password);
	
	if(result > 0){
		session.removeAttribute("login");
		//response.sendRedirect("../index.jsp");
		//스크립트로 재구성
		out.print("<script>");
		out.print("alert('회원탈퇴 성공');");
		out.print("location.href='../index.jsp'");
		out.print("</script>");
	}else{
		//탈퇴 실패시 leaveForm.jsp 이동
		//response.sendRedirect("../view/leaveForm.jsp");	
		//스크립트로 재구성
		out.print("<script>");
		out.print("alert('비밀번호를 확인해 주세요');");
		out.print("location.href='../view/leaveForm.jsp';");
		out.print("</script>");
	}
%>