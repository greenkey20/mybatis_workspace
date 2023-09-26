<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>errorPage</title>
</head>
<body>
	<!--2022.2.9(수) 16h45-->
	
	<!--이 페이지와 같은 폴더/경로에 있는 파일을 include하고자 하므로, 아래와 같이 표기-->
	<jsp:include page="menubar.jsp" />
	
	<h1 align="center" style="color:forestgreen">${ errorMsg }</h1>
	
</body>
</html>