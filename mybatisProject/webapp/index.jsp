<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBatis</title>
</head>
<body>
	<!--2022.2.9(수) 10h25 http://localhost:8004/mybatis/-->
	<!--framework = 개발자가 보다 편리한 환경에서 개발할 수 있도록 제공하는 뼈대/틀 -> 소프트웨어 개발의 입장에서는 공통으로 사용하는 library/개발도구/interface 등을 의미
		framework 사용의 필요성/왜 사용하는가? = 현재 웹프로그래밍의 규모가 커지고 있음 -> 거대하고 복잡도가 높은 프로젝트를 완성시키기 위해 많은 사람들이 필요함 -> 그 개발자들이 통일성 있게 빠르고 안정적으로 개발하기 위한 도구로 framework가 좋은 성과를 내고 있음 -> 생산성 향상(=비용 절감)에 도움이 됨
		framework의 특징 = 자유롭게 설계하고 coding(x) framework가 제공하는 guide대로 setting하고 설계하고 codes 작성(o) -> 개발할 수 있는 범위가 정해져있음 + 개발자를 위한 다양한 도구/플러그인들이 지원됨
		framework의 장점 = 개발 시간 단축 가능 + 오류로부터 자유로워질 수 있음
		framework의 단점 = 너무 framework에 의존하다보면 개발 능력이 떨어져서 framework 없이 개발하는 것이 어려워짐 + 습득하는 데에 시간이 오래 걸릴 수 있음
		framework의 종류
		1. 영속성(persistence): data 관련(CRUD) 기능들을 편하게 작업할 수 있게 하는 framework e.g. MyBatis
		 - 2022.3.11(금) 11h15 보충 설명 https://hyunmin1906.tistory.com/215 = 자료를 db에 저장하는 과정을 도와주고 자동화하는 매개 software; 응용 prgm과 db 사이에서 벌어질 수 있는 개념적 간극 추상화; db 가공하는 Java 객체층-데이터 저장하는 db층 사이를 매끄럽게 연결하는 이음매
		2. Java: web application에 초점을 맞춰 필요한 요소들을 module화해서 제공해주는 framework
		3. 화면 구현: front-end를 보다 쉽게 구현할 수 있게 해주는 틀을 제공하는 framework
		4. 기능 및 지원: 특정 기능이나 업무 수행에 도움을 주는 기능을 제공해주는 framework-->
	
	안녕! 나는 index file이야 :D
	
	<!--index 페이지가 로딩되자마자 WEB-INF/views/main.jsp로 곧바로(o) servlet 거쳐서(x) forwarding = 표준 액션 태그인 바, taglib 쓸 필요 없음-->
	<jsp:forward page="WEB-INF/views/main.jsp"></jsp:forward>
</body>
</html>