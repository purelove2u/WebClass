<%@page import="domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>
<%
	//MemberVO login = (MemberVO)session.getAttribute("login");
	//if(login == null){
	
	//위의 코드를 아래처럼 대체    
%>
<c:if test="${empty login}">

<form class="form-signin" name="loginform" action="../login.do" method="post">
  <div class="form-label-group">
    <input type="text" id="userid" name="userid" class="form-control" placeholder="id" required autofocus>
    <label for="userid">아이디</label>
  </div>

  <div class="form-label-group">
    <input type="password" id="current_password" name="password" class="form-control" placeholder="Password" required>
    <label for="pass">비밀번호</label>
  </div>

  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  <p class="mt-5 mb-3 text-muted text-center">&copy; 2019</p>
</form>

</c:if>

<%//}else{%>

<c:if test="${!empty login }">

<script>
	/*  값을 입력하는 순간 자료형이 결정되기 때문에 ' '를 통해 문자열임을 표시함 */
	<%-- let name = '<%=login.getName()%>'; --%>
	let name = '${login.name}';
</script>
<script src="../js/menu.js"></script>
<%// } %>

</c:if>

<script src="../js/button.js"></script>
<%@ include file="../layout/footer.jsp" %>