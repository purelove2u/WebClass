<%@page import="persistence.MemberDAO"%>
<%@page import="domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	//joinForm.jsp에서 넘긴 값 가져오기
	MemberVO member = new MemberVO();
	member.setUserid(request.getParameter("userid"));	
	
	String password = request.getParameter("password");
	String confirm_password = request.getParameter("confirm_password");
	if(password.equals(confirm_password)){
		member.setPassword(password);
	}else{
		out.print("<script>");
		out.print("alert('비밀번호가 서로 다릅니다');");
		out.print("location.href='location.back();'");
		out.print("</script>");
	}	
	member.setName(request.getParameter("name"));
	member.setGender(request.getParameter("gender"));
	member.setEmail(request.getParameter("email"));
	//DB작업한 후 회원가입 성공하면
	//로그인 페이지로 이동
	MemberDAO dao = new MemberDAO();
	int result=dao.register(member);
	if(result > 0){
		out.print("<script>");
		out.print("alert('회원가입성공');");
		out.print("location.href='../view/loginForm.jsp';");
		out.print("</script>");
	}else{
		//회원가입 실패하면 회원가입페이지로 이동
		out.print("<script>");
		out.print("alert('회원가입실패');");
		out.print("location.href='../view/joinForm.jsp';");
		out.print("</script>");
	}
%>


