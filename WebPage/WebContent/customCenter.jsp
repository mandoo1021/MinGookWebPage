<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head> 
<title>고객 센터</title>
<jsp:include page="reference.jsp" />
</head>
<body class="bg-light">
<jsp:include page="menu.jsp" />
<div class="jumbotron bg-light">
	<div class="container bg-light">
		<img src="<c:url value="/resources/image/center.jpg"/>" style="height:1400px; width:1100px;">	
	</div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>