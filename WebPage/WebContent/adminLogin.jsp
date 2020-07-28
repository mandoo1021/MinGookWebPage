<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<html>
<head> 
<jsp:include page="reference.jsp" />
<title>로그인하기</title>
</head>
<body class="bg-light">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3" style="font-size:70px; font-weight:bold;color:FFBE0A;">관리자 로그인</h1>
			</div>
		</div>
	</div>
	<div class="container bg-light" align="center">
		<div class="col-md-4 col-md-offset-4">
			<h3 class="form-signin-heading" style="color:FFBE0A;">Please sign in</h3>
			<%
				String error = request.getParameter("error");
				if (error != null) {
					out.println("<div class='alert alert-danger'>");
					out.println("아이디와 비밀번호를 확인해 주세요");
					out.println("</div>");
				}
			%>
			<form class="form-signin" action="processAdminMember.jsp" method="post">
				<div class="form-group">
					<label for="inputUserName" class="sr-only">User Name</label>
					<input type="text" class="form-control" placeholder="ID" name='admin_id' required autofocus>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="sr-only">Password</label> 
					<input	type="password" class="form-control" placeholder="Password" name='admin_pw' required>
				</div>
				<button class="btn btn btn-lg btn-warning btn-block" type="submit">로그인</button>
			</form>
		</div>
	</div>
</body>
</html>