<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 양식</title>
</head>
<body>
	<!--2022.2.9(수) 14h20-->
	
	<!--나의 현재 위치에서부터 1단계 올라감 = ../ -> 거기에 있는 common 폴더 내부에 있는 jsp 파일을 include하고자 함-->
    <jsp:include page="../common/menubar.jsp" />

    <div class="outer">
        <br>
        <h1 align="center">회원 가입 양식</h1>

        <!--db/erd 설계 -> 테이블 만들어둠 -> 그 테이블의 내용 = 아래 양식에 입력받을 내용-->
        <form action="insert.me" method="post">
            <table align="center">
                <tr>
                    <td>* ID</td>
                    <td><input type="text" name="userId" required></td>
                </tr>

                <tr>
                    <td>* password</td>
                    <td><input type="password" name="userPwd" required></td>
                </tr>

                <tr>
                    <td>* name</td>
                    <td><input type="text" name="userName" required></td>
                </tr>

                <!--MEMBER 테이블에 필수 입력 사항이 아닌(nullable) 컬럼들의 경우, 회원 가입 시 입력받을지, 또는 다른 곳에서 추후 입력받을지 등은 '기획'의 영역-->
                <tr>
                    <td>&nbsp;&nbsp;email</td>
                    <td><input type="email" name="email"></td>
                </tr>

                <tr>
                    <td>&nbsp;&nbsp;birthday</td>
                    <td><input type="text" name="birthday" placeholder="YYMMDD 형식, 6자리"></td>
                </tr>

                <tr>
                    <td>&nbsp;&nbsp;gender</td>
                    <td align="center">
                        <input type="radio" name="gender" value="M" checked>male
                        <input type="radio" name="gender" value="F">female
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;&nbsp;phone</td>
                    <td><input type="text" name="phone" placeholder="- 포함"></td>
                </tr>

                <tr>
                    <td>&nbsp;&nbsp;address</td>
                    <td><input type="text" name="address"></td>
                </tr> 
            </table>

            <br>
            <div align="center">
                <button type="reset">초기화</button>
                <button type="submit">회원 가입</button>
            </div>

        </form>        
        <br><br>

    </div>

</body>
</html>