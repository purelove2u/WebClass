<%@page import="domain.UserVO"%>
<%@page import="java.util.List"%>
<%@page import="persistence.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp"></jsp:include>
<%
	UserDAO dao = new UserDAO();
	List<UserVO> list = dao.getList();
%>
<div class="content"> 
	<div class="container">
		<h3>USER 전체 조회</h3> 
	  <table class="table">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">번호</th>
		      <th scope="col">이름</th>
		      <th scope="col">핸드폰</th>      
		    </tr>
		  </thead>
		  <tbody><!-- user 내용 뿌려줄 부분 -->
		  	<%for(UserVO vo : list){ %>
		  	<tr>
		  		<td><%=vo.getNo() %></td>
		  		<td><%=vo.getUsername() %></td>
		  		<td><%=vo.getMobile() %></td>
		  	</tr>
		  	<%} %>
		  </tbody>
	</table>
</div>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
