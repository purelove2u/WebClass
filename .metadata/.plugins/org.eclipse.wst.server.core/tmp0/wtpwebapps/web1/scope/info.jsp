<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- form 보내기 : button 이 submit 형태가 가장 쉬운 방법 --%>
	<form action="response.jsp" method="post">
		<div>
			<label for="userid">userid</label>
			<input type="text" name="userid" id="userid"/>
		</div>
		<div>
			<!-- <input type="submit" value=""/>-->
			<button type="submit">보내기</button>
		</div>
	</form>
</body>
</html>
