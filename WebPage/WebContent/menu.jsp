<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Core태그를 써서 경로를 지정해주는 이유는 동일 소스로 context path를 자동 포함시키기 위함 --%>
<style>
#auto.dropdown:hover .dropdown-menu {
    display: block;
    margin-top: 0;
}
.dropdown-toggle::after {
    display:none;
}
</style>
<%
	String sessionId = (String) session.getAttribute("sessionId");
%>
<script>
	function logoutCheck(){
		if(confirm("로그아웃 하시겠습니까?")){
			return true;
		} else {
			return false;
		}
	}
	function languageCheck(){
		alert("준비중입니다.");
	}
</script>
<fmt:setLocale value='<%=request.getParameter("language") %>'/>
<fmt:bundle basename="bundle.menu">
<nav class="navbar navbar-expand bg-light navbar-light" style="height:15px">
	<div class="container">
		<div class="navbar-header">
		</div>
		<div>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown" style="padding-right:20px;font-size:12px;">
				    <a class="nav-link dropdown-toggle" href="?language=ko" id="dropdown01" onclick="return languageCheck();" 
				     data-toggle="dropdown" aria-haspopup="true">Language</a>
				    <%--<div class="dropdown-menu" aria-labelledby="dropdown01">
				  	    <a class="dropdown-item" href="?language=ko">Korean</a>
					    <a class="dropdown-item" href="?language=en">English</a>
			   		</div> --%>
				</li>
				<li class="nav-item" style="padding-right:20px;font-size:12px"><a class="nav-link" 	
				href="<c:url value="https://blog.naver.com/mandoo1021"/>" target="_blank">개발자 블로그</a></li>
				<li class="nav-item" style="font-size:12px"><a class="nav-link" 
				href="<c:url value="/adminPage.jsp"/>"><fmt:message key="adminpage"/> </a></li>
			</ul>
		</div>
	</div>
</nav>
<nav class="navbar navbar-expand bg-light navbar-light">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/index.jsp"/>">
			<img src="<c:url value="/resources/image/main.png"/>" style="height:80px;width:300px;"></a>
		</div>
		<div>
			<ul class="navbar-nav mr-auto">
				<c:choose>
					<c:when test="${empty sessionId}">
						<li class="nav-item"><a class="nav-link" href="<c:url value="/member/memberLogin.jsp"/>"><fmt:message key="login"/> </a></li>
						<li class="nav-item"><a class="nav-link" href="<c:url value="/member/addMember.jsp"/>"><fmt:message key="createmember"/></a></li>
					</c:when>
					<c:otherwise>
						<li style="padding-top: 7px; color: black">[<%=sessionId%>님]</li>
						<li class="nav-item" style="font-color : black;"><a class="nav-link" onclick="return logoutCheck();" href="<c:url value="/member/logoutMember.jsp"/>">로그아웃 </a></li>
						<li class="nav-item"><a class="nav-link" href="<c:url value="/member/myPage.jsp"/>"><fmt:message key="mypage"/></a></li>
						<li class="nav-item"><a class="nav-link" href="<c:url value="/ContactListAction.do?pageNum=1"/>">문의하기</a></li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item"><a class="nav-link" href="<c:url value="/cart.jsp"/>"><fmt:message key="basket"/></a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="https://www.doortodoor.co.kr/parcel/pa_004.jsp"/>" target="_blank">배송조회</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="/customCenter.jsp"/>">고객센터</a></li>
				<li class="nav-item dropdown"  id="auto">
				
				   <%--  <a class="nav-link dropdown-toggle" href="#" id="dropdown01" 
				     data-toggle="collapse" aria-haspopup="true" >
				     <fmt:message key="board"/></a>
				    <div class="dropdown-menu" aria-labelledby="dropdown01">
					    <a class="dropdown-item" href="#"><fmt:message key="freeboard"/></a>
					    <a class="dropdown-item" href="#"><fmt:message key="gallary"/></a>
			   		</div>--%>
			   		
			    </li>
			</ul>
		</div>
	</div>
</nav>
</fmt:bundle>