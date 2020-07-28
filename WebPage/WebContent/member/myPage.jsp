<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<html>
<head> 
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<title>마이 페이지</title>
</head>
<body class="bg-light">
	<jsp:include page="/menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3" style="font-size:70px; font-weight:bold;color:FFBE0A;">마이 페이지</h1>
			</div>
		</div>
	</div>
	<div class="container bg-light" align="center">
		<div class="col-md-4 col-md-offset-4">
			<a href="#" class="nav-link"><button class="btn btn btn-lg btn-warning btn-block" type="button">주문 조회</button></a>
			<a href="updateMember.jsp" class="nav-link"><button class="btn btn btn-lg btn-warning btn-block" type="button">회원 정보 수정</button></a>
			<a href="deletePage.jsp" class="nav-link"><button class="btn btn btn-lg btn-warning btn-block" type="button">회원 탈퇴</button></a>
		</div>
	</div>
</body>
</html>