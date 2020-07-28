<%@ page language="java" contentType="text/html; charset=UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="mvc.model.ContactDTO" %>
<%
	String sessionId = (String) session.getAttribute("sessionId");
	List contactList = (List) request.getAttribute("contactlist");
	int total_record = ((Integer) request.getAttribute("total_record")).intValue();
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	int total_page = ((Integer) request.getAttribute("total_page")).intValue();
%>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" />
<title>adminContact</title>
<script type="text/javascript">
	function checkForm() {	
		if (${sessionId==null}) {
			alert("로그인 해주세요.");
			return false;
		}
	
		location.href = "./ContactWriteForm.do?id=<%=sessionId%>"
	}
</script>
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
		<form class="my-2 my-lg-0" action="<c:url value="./ContactListAction.do"/>" method="post">
			<div>
				<div class="text-right">
					<span class="badge badge-success">전체 <%=total_record%>건	</span>
				</div>
			</div>
			<div style="padding-top: 50px">
				<table class="table table-hover text-center">
					<tr>
						<th style="width:10%">번호</th>
						<th style="width:60%">제목</th>
						<th style="width:10%">작성자</th>
						<th style="width:20%">작성일</th>
					</tr>
					<%
						for (int j = 0; j < contactList.size(); j++) {
							ContactDTO notice = (ContactDTO) contactList.get(j);
					%>
					<tr>
						<td style="width:10%"><%=notice.getNum()%></td>
						<td style="width:60%"><a href="./ContactViewAction.do?num=<%=notice.getNum()%>&pageNum=<%=pageNum%>"><%=notice.getSubject()%></a></td>
						<td style="width:10%"><%=notice.getUser_name()%></td>
						<td style="width:20%"><%=notice.getRegist_day()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			<div align="center">
				<c:set var="pageNum" value="<%=pageNum%>" />
				<c:forEach var="i" begin="1" end="<%=total_page%>">
					<a href="<c:url value="./ContactListAction.do?pageNum=${i}" /> ">
						<c:choose>
							<c:when test="${pageNum==i}">
								<font color='4C5317'><b> [${i}]</b></font>
							</c:when>
							<c:otherwise>
								<font color='4C5317'> [${i}]</font>

							</c:otherwise>
						</c:choose>
					</a>
				</c:forEach>
			</div>
			<div align="left">
				<table>
					<tr>
						<td width="100%" style="width:100%" align="center">&nbsp;&nbsp; 
						<select name="items" class="form-control" style="height:40px;width:100px;display:inline;">
								<option value="subject">제목</option>
								<option value="content">내용</option>
								<option value="user_name">작성자</option>
						</select> 
						<input name="text" type="search" placeholder="Search" style="height:40px;width:300px;display:inline;" class="form-control"/>
						<input type="submit" id="btnAdd" style="height:40px;width:70px;display:inline;" class="form-control btn btn-warning" style="" value="검색 " />
						</td>
						<td width="100%" align="right">
							<a href="#" onClick="checkForm(); return false;" style="margin-bottom:100px;" class="btn btn-primary">&laquo;글쓰기</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
		<hr>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>