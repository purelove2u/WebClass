<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">List Board</h3>
		</div>
		<div class="row">
			<div class="col-md-4">
				<input type="button" class="btn btn-success" value="새글작성" onclick="location.href='view/qna_board_write.jsp'"/>
			</div><!--글쓰기 버튼-->
			<div class="col-md-4 offset-md-4"><!--검색 들어갈 부분-->
				<form action="list.do" method="post" id="search">
					<select name="criteria" id="">
						<option value="n" <c:out value="${pageVO.search.criteria==null?'selected':''}"/>>-------</option>
						<option value="title" <c:out value="${pageVO.search.criteria=='title'?'selected':''}"/>>title</option>
						<option value="content" <c:out value="${pageVO.search.criteria=='content'?'selected':''}"/>>content</option>
						<option value="name" <c:out value="${pageVO.search.criteria=='name'?'selected':''}"/>>name</option>
					</select>
					<input type="text" name="keyword" value="${pageVO.search.keyword}"/>
					<button type="button" class="btn btn-primary">검색</button>
				</form>
			</div>
		</div>
		<br>
		<table class="table table-bordered">
			<tr>
				<th class='text-center' style='width:100px'>번호</th>
				<th class='text-center'>제목</th>
				<th class='text-center'>작성자</th>
				<th class='text-center'>날짜</th>
				<th class='text-center' style='width:100px'>조회수</th>
			</tr>
			<c:forEach var="vo" items="${list}">
			<tr><!-- 리스트 목록 보여주기 -->
				<td class='text-center'>${vo.bno}</td><!--번호-->
				<td>
					<c:if test="${vo.re_lev!=0}">
						<c:forEach begin="0" end="${vo.re_lev*1}">
							&nbsp;
						</c:forEach>
					</c:if>
					<a href="hitupdate.do?bno=${vo.bno}&page=${pageVO.search.page}&criteria=${pageVO.search.criteria}&keyword=${pageVO.search.keyword}">${vo.title}</a>
				</td><!--제목-->
				<td class='text-center'>${vo.name}</td><!--작성자-->
				<td class='text-center'>${vo.regdate}</td><!--날짜-->
				<td class='text-center'>
				<span class="badge badge-pill badge-primary">${vo.readcount}</span></td>
			</tr>
			</c:forEach>		
		</table>
		<div class="container">
			<div class="row  justify-content-md-center">
				<nav aria-label="Page navigation example">
				  <ul class="pagination"><!--하단의 페이지 나누기 부분-->
					<c:if test="${pageVO.prev}">
						<li class="page-item">
							<a href="list.do?page=${pageVO.search.page-1}&criteria=${pageVO.search.criteria}&keyword=${pageVO.search.keyword}" class="page-link">Previous</a>
						</li>
					</c:if>
					<c:forEach begin="${pageVO.startPage}" end="${pageVO.endPage}" var="idx">
						<c:choose>
							<c:when test="${pageVO.search.page==idx}">
								<li class="page-item active">
									<a class="page-link">${idx}</a>
								</li>								
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" href="list.do?page=${idx}&criteria=${pageVO.search.criteria}&keyword=${pageVO.search.keyword}">${idx}</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageVO.next}">
						<li class="page-item">
							<a href="list.do?page=${pageVO.search.page+1}&criteria=${pageVO.search.criteria}&keyword=${pageVO.search.keyword}" class="page-link">Next</a>
						</li>
					</c:if>
				  </ul>
				</nav>					
			</div>
		</div>
		<div style="height:20px"></div>
	</div>	
</section>
<script>
$(function(){
	$(".btn-primary").click(function(){
		//검색조건이 선택되지 않은 경우 경고창 띄우기
		if($("select[name='criteria']").val()==='n'){
			alert('검색 조건을 입력해 주세요');
			return false;
		}else if($("input[name='keyword']").val()===""){
		//검색어를 입력하지 않은 경우 경고창 띄우기
			alert('검색어를 입력해 주세요');
			$("input[name='keyword']").focus();
			return false;
		}
		//이상 없으면 submit()하기
		$("#search").submit();		
	})
})

</script>
<%@include file="../include/footer.jsp"%>











