<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
      crossorigin="anonymous"
    />
    <title></title>
  </head>
  <body>
    <div class="container" style="margin-top: 50px;">
      <h3 class="text-center">상품 리스트</h3>
      <a href="view/admin_goods_write.jsp">상품등록</a>
      <table class="table table-bordered">
        <thead class="thead-dark text-center">
          <th scope="col">번 호</th>
          <th scope="col">카테고리</th>
          <th scope="col">상품명</th>
          <th scope="col">단 가</th>
          <th scope="col">수 량</th>
          <th scope="col">등록일자</th>
        </thead>
        <tbody>
          <!-- 내용 반복 시키는 곳 -->
		<c:forEach var="vo" items="${list}">
			<tr><!-- 리스트 목록 보여주기 -->
				<td class='text-center'>${vo.pno}</td><!--번호-->
				<td class='text-center'>${vo.category}</td><!--카테고리-->
				<td><a href="view.do?bno=${vo.pno}">${vo.name}</a></td><!--상품명-->
				<td class='text-center'>${vo.price}</td><!--단가-->
				<td class='text-center'>${vo.amount}</td><!--수량-->
				<td class='text-center'>${vo.regdate}</td><!--등록일자-->
			</tr>		
			</c:forEach>          
        </tbody>
      </table>
    </div>
  </body>
</html>
