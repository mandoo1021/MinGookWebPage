<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<html>
<head> 
<jsp:include page="reference.jsp" />
<title>관리자페이지</title>
</head>
<script>
	function logoutCheck(){
		if(confirm("로그아웃 하시겠습니까?")){
			return true;
		} else {
			return false;
		}
	}
</script>
<%
	if(session.getAttribute("sessionId") != null && session.getAttribute("sessionAdminId") == null){
		response.sendRedirect("adminLogin.jsp");
	} else if(session.getAttribute("sessionAdminId") == null){
		response.sendRedirect("adminLogin.jsp");
	}
%>
<body class="bg-light">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3" style="font-size:70px; font-weight:bold;color:FFBE0A;">관리자 페이지</h1>
			</div>
		</div>
	</div>
	<div class="container bg-light" align="center">
		<div class="col-md-4 col-md-offset-4">
			<a href="addProduct.jsp" class="nav-link"><button class="btn btn btn-lg btn-warning btn-block" type="button">상품 등록</button></a>
			<a href="editPage.jsp" class="nav-link"><button class="btn btn btn-lg btn-warning btn-block" type="button">상품 편집</button></a>
			<a href="editMember.jsp" class="nav-link"><button class="btn btn btn-lg btn-warning btn-block" type="button">회원 관리</button></a>
			<a href="orderPage.jsp" class="nav-link"><button class="btn btn btn-lg btn-warning btn-block" type="button">주문관리</button></a>
			<a href="adminLogout.jsp" class="nav-link"><button onclick="return logoutCheck();" class="btn btn btn-lg btn-warning btn-block" type="button">관리자 로그아웃</button></a>
		</div>
	</div>
</body>
</html>