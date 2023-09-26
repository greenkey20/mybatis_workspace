<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록 조회</title>
<style>
    #list-area {
        border: 1px solid white;
        text-align: center;
    }

    .outer a {
        color: white;
        text-decoration: none;
    }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> <!--2022.2.11(금) 16h40 강사님 링크 제대로 못 봄 vs 일단 web2 workspace에서 복사/붙여넣기함-->
</head>
<body>
	<!--2022.2.10(목) 16h10 -> 16h40 테스트 완료-->
    <jsp:include page="../common/menubar.jsp" />

    <div class="outer" align="center">
        <br>
        <h1 align="center">게시판</h1>
        <br>

		<!--(게시글 작성 및)상세조회 기능 구현한 뒤에 구현할 것임 -> 2022.2.11(금) 14h20 나의 idea = BoardListController와 비슷한 로직 + Board 객체에 값 넣어서 db로 보내고 select-->
        <div id="search-area">
            <form action="search.bo">
                <input type="hidden" name="currentPage" value="1">
                <select name="condition">
                    <option value="writer">작성자</option>
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
                <input type="text" name="keyword" value="${ keyword }"> <!--사용자가 검색하고자 입력했던 keyword가 input 입력난에 남아있도록 함-->
                <button type="submit">검색</button>
            </form>
        </div>
        <br>
        
        <!--2022.2.11(금) 16h40 추가 -> 내가 선택했던 검색 조건/condition이 선택/표시됨-->
        <script>
            $(function() {
                $("#search-area option[value=${ condition }]").attr("selected", true);
            })
        </script>

        <table id="list-area">
            <thead>
                <tr>
                    <th>글 번호</th>
                    <th width="400">제목</th>
                    <th>작성자</th>
                    <th>조회 수</th>
                    <th>작성일</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="b" items="${ list }"> <!--var로 선언된 변수는 반드시 EL 문법/구문으로 접근해야 함-->
                    <tr>
                        <td>${ b.boardNo }</td>
                        <td><a href="detail.bo?bno=${ b.boardNo }">${ b.boardTitle }</a></td> <!--BOARD 테이블의 primary key(unique and not null)인 boardNo로 특정 게시글을 지칭/식별할 수 있음-->
                        <td>${ b.boardWriter }</td>
                        <td>${ b.count }</td>
                        <td>${ b.createDate }</td>
                    </tr>
                </c:forEach>
            </tbody>           
        </table>
        <br>

        <div id="paging-area">
        	<!--2022.2.11(금) 17h25 '검색'을 한 경우의 '이전' 버튼 처리-->
        	<c:if test="${ pi.currentPage ne 1 }">
        		<c:choose>
        			<c:when test="${ empty condition }">
		        		<a href="list.bo?currentPage=${ pi.currentPage - 1}">[이전]</a>
        			</c:when>
        			
        			<c:otherwise>
        				<a href="search.bo?currentPage=${ pi.currentPage - 1}&condition=${ condition }&keyword=${ keyword }">[이전]</a>
        			</c:otherwise>
        		</c:choose>
        	</c:if>
        
        	<!--2022.2.11(금) 16h45 "request 객체의 condition이 비어있는 경우 = '검색'을 한 경우가 아님" vs "condition에 값이 들어있는 경우 = 검색을 수행한 경우"-->
            <c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }" step="1">
            	<c:choose>
            		<c:when test="${ empty condition }">
            			<c:choose>
		            		<c:when test="${ pi.currentPage ne p }">
		            			<a href="list.bo?currentPage=${ p }">[${ p }]</a>
		            		</c:when>
		            		
		            		<c:otherwise>
		            			${ p }
		            		</c:otherwise>
		            	</c:choose>
            		</c:when>
            		
            		<c:otherwise>
            			<c:choose>
		            		<c:when test="${ pi.currentPage ne p }">
		            			<a href="search.bo?currentPage=${ p }&condition=${ condition }&keyword=${ keyword }">[${ p }]</a>
		            		</c:when>
		            		
		            		<c:otherwise>
		            			${ p }
		            		</c:otherwise>
		            	</c:choose>
            		</c:otherwise>
            	</c:choose>
            </c:forEach>
            
            <!--2022.2.11(금) 17h30 '검색'을 한 경우의 '다음' 버튼 처리-->
            <c:if test="${ pi.currentPage ne pi.maxPage }">
            	<c:choose>
            		<c:when test="${ empty condition }">
		            	<a href="list.bo?currentPage=${ pi.currentPage + 1}">[다음]</a>
            		</c:when>
            		
            		<c:otherwise>
            			<a href="search.bo?currentPage=${ pi.currentPage + 1}&condition=${ condition }&keyword=${ keyword }">[다음]</a>
            		</c:otherwise>
            	</c:choose>
            </c:if>
        </div>

    </div> <!--div class="outer" 영역 끝-->

</body>
</html>