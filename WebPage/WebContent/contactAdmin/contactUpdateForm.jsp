<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="mvc.model.ContactDTO"%>
<%
	ContactDTO notice = (ContactDTO) request.getAttribute("contact");
%>
<html>
<head> 
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css"/>" />
<title>Contact</title>
</head>
<script type="text/javascript">
	function checkForm() {
		if (!document.newWrite.name.value) {
			alert("성명을 입력하세요.");
			return false;
		}
		if (!document.newWrite.phone.value) {
			alert("핸드폰번호를 입력하세요.");
			return false;
		}
		if (!document.newWrite.subject.value) {
			alert("제목을 입력하세요.");
			return false;
		}
		if (!document.newWrite.content.value) {
			alert("내용을 입력하세요.");
			return false;
		}		
	}
</script>
<body class="bg-light">
	<jsp:include page="../menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3"
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">수정하기</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<form name="newWrite" action="./ContactUpdateAction.do?num=<%=notice.getNum()%>"
			class="form-horizontal" method="post" onsubmit="return checkForm()">
			<input name="id" type="hidden" class="form-control"
				value="${sessionId}">
			<div class="form-group row">
				<label class="col-sm-2 control-label" >성명</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control"  placeholder="name" value="<%=notice.getUser_name()%>">
				</div>
				<label class="col-sm-2 control-label" >연락처</label>
				<div class="col-sm-3">
					<input name="phone" type="text" class="form-control" placeholder="phone" value="<%=notice.getUser_phone()%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">

					<input name="subject" type="text" class="form-control"
						placeholder="subject" value="<%=notice.getSubject()%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8">
					<textarea name="content" cols="50" rows="5" class="form-control"
						placeholder="content"><%=notice.getContent()%></textarea>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
				 	 <input type="submit" class="btn btn-primary " value="수정">				
					<a href="<c:url value="/ContactListAction.do?pageNum=1"/>" class="btn btn-primary">취소</a>
				</div>
			</div>
		</form>
		<hr>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>