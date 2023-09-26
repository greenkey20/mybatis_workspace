<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menubar</title>
<style>
    .login-area a {
        text-decoration: none;
        color: black;
        font-size: 12px;
    }

    .nav-area {
        background-color: yellowgreen;
        color: white;
        height: 50px;
    }

    .menu {
        display: table-cell; /* 12h30? 무슨 속성인지 강사님 설명 제대로 못 들음*/
        width: 250px;
        height: 50px;
        vertical-align: middle;
        font-size: 20px;
        font-weight: bold;
    }

    .menu:hover {
        cursor: pointer;
        background-color: limegreen;
        color: green;
    }

    .outer {
        width: 900px;
        background-color: yellowgreen;
        color: white;
        margin: auto;
        margin-top: 50px;
    }
</style>
</head>
<body>
	<!--2022.2.9(수) 12h15-->
    <h1 align="center">welcome to MyBatis world :D</h1>
    <br>

    <div class="login-area" align="right">
        <c:choose>
            
            <c:when test="${ empty loginUser }">
                <!--case1 = 로그인 전 = id 및 비밀번호 입력란 + 로그인 요청 버튼 표시-->
                <form action="login.me" method="post"> <!--로그인 시 보안이 중요한 정보가 넘어가므로 post 방식으로 요청 보냄-->
                    <!--table>(tr>td*3)*3-->
                    <table>
                        <tr>
                            <td>ID</td>
                            <td><input type="text" name="userId" id="" required></td>
                            <td rowspan="2"><button type="submit" style="height: 50px;">로그인</button></td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td><input type="password" name="userPwd" id="" required></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="3" align="center">
                                <a href="enrollForm.me">회원 가입</a> <!--2022.2.9(수) 17h25 나의 질문 = servlet 요청 시 왜 contextPath/ 안 붙이나요? -> 강사님 답변 = 상대경로는 마지막 slash 뒤에 붙는 것.. 원래 안 써도 (접근)되는데, semi-project까지는 많이들 헷갈려하니까 항상 썼었음.. 등등 설명해 주셨는데, 제대로 이해 못함 ㅠ.ㅠ-->
                                <!--2022.2.10(목) 11h30 나의 질문 = id 입력한 뒤 tab 누르면 '로그인' 버튼으로 넘어감 vs 비밀번호 입력란으로 넘어가게 하려면 어떻게 해야 할까?-->
                                <a href="#">ID/PW 찾기</a> <!--2023.9.24(일) 11h25 무의미하게/가짜로/허위 a 태그 걸기 -> 더 정확하게 말하자면 특정 id를 가진 요소의 위치로 스크롤됨 -> id가 특정되지 않아 상응하는 위치가 없다면 그 페이지의 top으로 스크롤 됨(https://www.codeit.kr/community/questions/UXVlc3Rpb246NWUzNDUyMjU4MGU1MTMzNzNkOTYxOGE0)-->
                            </td>            
                        </tr>
                    </table>
                </form>
            </c:when>
            
            <c:otherwise>
                <!--case2 = 로그인 후 = 'xx님, 환영합니다' 표시-->
                <div>
                    <!--table>(tr>td*2)*2-->
                    <table>
                        <tr>
                            <td colspan="2">
                                <h3>${ loginUser.userName } 님, 환영합니다</h3> <!--2022.2.10(목) 11h20 sessionScope.은 안 쓰기로 함-->
                            </td>
                        </tr>
                        <tr>
                            <td><a href="">my page</a></td>
                            <td><a href="logout.me">로그아웃</a></td>
                        </tr>
                    </table>
                </div>
            </c:otherwise>
        
        </c:choose>
        
    </div> <!--div class="login-area" 영역 끝-->

    <br>
    <div class="nav-area" align="center">
        <!--.menu*4-->
        <div class="menu">home</div>
        <div class="menu">공지사항</div>
        <div class="menu" onclick="location.href='list.bo?currentPage=1';">게시판</div>
        <div class="menu">etc</div>
    </div>  

</body>
</html>