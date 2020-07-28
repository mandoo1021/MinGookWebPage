<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" /> 
<title>페이지 오류</title>
</head>
<body class="bg-light">
	<jsp:include page="../menu.jsp" />
	<div class="jumbotron bg-light text-center">
		<div class="container bg-light">
			<h2 class="alert alert-danger">요청하신 페이지를 찾을 수 없습니다.</h2>
		</div>
	</div>
	<div class="container bg-light" align="center">
		<p><%=request.getRequestURL() %></p>
		<p> <a href="<c:url value="/index.jsp"/>" class="btn btn-warning">홈으로 &raquo;</a>	
	</div>
</body>
</html>