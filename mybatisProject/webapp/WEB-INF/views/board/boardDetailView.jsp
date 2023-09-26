<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 조회</title>
<style>
	td > textarea {
		width: 100%;
		height: 100%;
		resize: none;
		box-sizing: border-box; /* 2022.2.11(금) 9h50 나의 질문 = 이게 어떤 속성이었는지 정확히 기억 안 나는 바, 찾아보기*/
	}
</style>
</head>
<body>
	<!--2022.2.11(금) 9h40-->
	<jsp:include page="../common/menubar.jsp" />
	<!--menubar.jsp에도 taglib 지시어 작성해두긴 했으나, 내가 사용하고 싶은 jsp 페이지마다 taglib 별도로 작성해줘야 함-->

	<div class="outer">
		<br>
		<h1 align="center">게시판 상세 조회</h1> <!--visual studio code에서 빨간색 글씨로 표시 = 이렇게 html 태그 안에 사용하는 것 권장x vs css로 사용 권장-->
		<br>

		<!--게시글 상세 조회 부분-->
		<table align="center" border="1">
			<!--(tr>(td*2))*6-->
			<tr>
				<td width="100">글 번호</td>
				<td width="500">${ b.boardNo }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${ b.boardTitle }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${ b.boardWriter }</td>
			</tr>
			<tr>
				<td>조회 수</td>
				<td>${ b.count }</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${ b.createDate }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td height="100">
					${ b.boardContent }
				</td>
			</tr>
		</table>

		<!--댓글 작성 부분-->
		<table align="center" border="1">
			<tr>
				<td width="100">댓글 작성</td>
				<td width="400"><textarea></textarea></td>
				<td width="100"><button>등록</button></td>
			</tr>

			<tr>
				<td colspan="3"><b>댓글(${ list.size() })</b></td> <!--또는 jstl "fn:length(list)"-->
			</tr>
			
			<!--core library 사용하려면, jsp 문서 상단에 taglib 지시어 작성 필요-->
			<c:forEach var="r" items="${ list }">
				<tr>
					<td>${ r.replyWriter }</td>
					<td>${ r.replyContent }</td>
					<td>${ r.createDate }</td>
				</tr>
			</c:forEach>
		</table>
		<br><br>

	</div>

</body>
</html>