<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head> 
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<title>Insert title here</title>
<script type="text/javascript">
function checkForm(Join){
    //체크박스 체크여부 확인 [하나]
    var chk1=document.deleteForm.deleteCheck.checked;
    
    if(!chk1){
        alert('약관에 동의해 주세요');
        return false;
    } 
}
</script>
</head>
<body class="bg-light">
	<jsp:include page="/menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3" style="font-size:70px; font-weight:bold;color:FFBE0A;">회원 탈퇴</h1>
			</div>
		</div>
	</div>
	<div class="container bg-light" align="center">
		<h4 class="alert alert-danger">
			탈퇴 후에는 아이디와 데이터는 복구할 수 없습니다.<br>
			그래도 탈퇴하시겠습니까?
		</h4>
	</div>
	<div class="container bg-light" align="center">
	<form name="deleteForm" action="deleteMember.jsp" onSubmit="return checkForm(this)"> 
		<input type="checkbox" name="deleteCheck">&nbsp;
		<b>안내사항을 모두 확인했습니다</b>			
		<div class="col-md-4 col-md-offset-4">
		<input type="submit" class="btn btn btn-lg btn-warning btn-block"
			style="margin-top:30px;"  value="탈퇴하기">
		</div>
	</form>
	</div>
</body>
</html>