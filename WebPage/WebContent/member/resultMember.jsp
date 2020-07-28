<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<title>회원 정보</title>
</head>
<body class="bg-light">
	<jsp:include page="/menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3" style="font-size:70px; font-weight:bold;color:FFBE0A;">회원 정보</h1>
			</div>
		</div>
	</div>
	<div class="container bg-light" align="center">
		<%
			String msg = request.getParameter("msg");

			if (msg != null) {
				if (msg.equals("0"))
					out.println(" <h2 class='alert alert-danger'>회원정보가 수정되었습니다.</h2>");
				else if (msg.equals("1"))
					out.println(" <h2 class='alert alert-danger'>회원가입을 축하드립니다.</h2>");
				else if (msg.equals("2")) {
					String loginId = (String) session.getAttribute("sessionId");
					response.sendRedirect("http://mandoo1021.cafe24.com/index.jsp");
				}				
			} else {
				out.println("<h2 class='alert alert-danger'>회원정보가 삭제되었습니다.</h2>");
			}
		%>
	</div>	
</body>
</html>