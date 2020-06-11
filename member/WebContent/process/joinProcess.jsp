<%@page import="persistence.MemberDAO"%>
<%@page import="domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//joinForm.jsp에서 넘긴 값 가져오기
	request.setCharacterEncoding("utf-8");
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	String confirm_password = request.getParameter("confirm_password");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String email = request.getParameter("email");
	
	if(password.equals(confirm_password)){
		
		//db작업한 후 회원가입 성공하면 로그인 페이지로 이동		
		MemberVO vo = new MemberVO();
		vo.setUserid(userid);
		vo.setPassword(password);
		vo.setName(name);
		vo.setGender(gender);
		vo.setEmail(email);
		MemberDAO dao = new MemberDAO();
		int result = dao.register(vo);
		
		if(result > 0){
			//response.sendRedirect("../view/loginForm.jsp");
			//스크립트 문으로 재구성
			out.print("<script>");
			out.print("alert('회원가입성공');");
			out.print("location.href='../view/loginForm.jsp';");
			out.print("</script>");
		}else{
			out.print("<script>");
			out.print("alert('회원가입실패');");
			out.print("location.href='../view/joinForm.jsp';");
			out.print("</script>");
		}
	}else{
		//회원가입 실패하면 회원가입 페이지로 이동
		//response.sendRedirect("../view/joinForm.jsp");	
		out.print("<script>");
		out.print("alert('비밀번호가 서로 다릅니다');");
		//out.print("location.href='history.back();'");
		out.print("location.href='../view/joinForm.jsp';");
		out.print("</script>");
	}
	
	
%>