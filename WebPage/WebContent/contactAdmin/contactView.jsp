<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="mvc.model.ContactDTO"%>
<%
	ContactDTO notice = (ContactDTO) request.getAttribute("contact");
	int num = ((Integer) request.getAttribute("num")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
%>
<html>
<head>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css"/>" />
<script>
	function deleteCheck(){
		if(!confirm("삭제하시겠습니까?")){
			return false;
		} 
	}
</script>
<title>Contact</title>
</head>
<body class="bg-light">
	<jsp:include page="../menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3"
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">문의하기</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<form name="newUpdate"
			action="./ContactUpdateFormAction.do?num=<%=notice.getNum()%>&pageNum=<%=nowpage%>"
			class="form-horizontal" method="post">
			<table class="table table-bordered text-center">
				<tr>
					<th>제목</th>
					<td colspan="3"><%=notice.getSubject()%></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><%=notice.getUser_name()%></td>
					<th>작성일</th>
					<td><%=notice.getRegist_day()%></td>
				</tr>
				<tr>
					<th colspan="4">내&nbsp;용</th>
				</tr>
				<tr style="height:500px; text-align:left;">
					<td colspan="4" rowspan="5"><%=notice.getContent()%></td>
				</tr>
			</table>
			<div class="form-group row"  align="left">
				<div class="col-sm-offset-2 col-sm-10">
					<c:set var="userId" value="<%=notice.getUser_id()%>" />
					<c:if test="${sessionId==userId}">
						<p>
						<a href="./ContactDeleteAction.do?num=<%=notice.getNum()%>&pageNum=<%=nowpage%>"
							onclick="return deleteCheck();" class="btn btn-danger"> 삭제</a>
						<input type="submit" class="btn btn-success" value="수정 ">
					</c:if>
					<a href="./ContactListAction.do?pageNum=<%=nowpage%>"
						class="btn btn-primary"> 목록</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>