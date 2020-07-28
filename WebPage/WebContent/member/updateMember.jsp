<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
    
	function checkId() {
		if (!document.newMember.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		
		window.name="IDCheckForm";
		window.open("./checkMember.jsp?id="+ document.newMember.id.value, "IDCheck", "width=200, height=160, resizable =no, scrollbars=no");
		
		
	}
</script>
<%
	String sessionId = (String) session.getAttribute("sessionId");
%>
<sql:setDataSource var="dataSource"
	url="jdbc:mysql://localhost/mandoo1021"
	driver="com.mysql.jdbc.Driver" user="mandoo1021" password="alsrnr12" />

<sql:query dataSource="${dataSource}" var="resultSet">
   SELECT * FROM MEMBER WHERE user_id=?
   <sql:param value="<%=sessionId%>" />
</sql:query>	
<title>회원 수정</title>
</head>
<body class="bg-light" onload="init()">
	<jsp:include page="/menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3" style="font-size:70px; font-weight:bold;color:FFBE0A;">회원 수정</h1>
			</div>
		</div>
	</div>
	<c:forEach var="row" items="${resultSet.rows}">
	<c:set var="mail" value="${row.user_email}" />
	<c:set var="mail1" value="${mail.split('@')[0]}" />
	<c:set var="mail2" value="${mail.split('@')[1]}" />

	<c:set var="birth" value="${row.user_birth }" />
	<c:set var="year" value="${birth.split('-')[0]}" />
	<c:set var="month" value="${birth.split('-')[1]}" />
	<c:set var="day" value="${birth.split('-')[2]}" />
	
	<c:set var="address" value="${row.user_address }" />
	<c:set var="add1" value="${address.split('-')[0]}" />
	<c:set var="add2" value="${address.split('-')[1]}" />
	<c:set var="add3" value="${address.split('-')[2]}" />
	<c:set var="add4" value="${address.split('-')[3]}" />
	
	<div class="container">
		<form name="newMember" class="form-horizontal"action="processUpdateMember.jsp" method="post"onsubmit="return checkForm()">
			<div class="form-group  row">
				<label class="col-sm-2 ">아이디</label>				
				<div class="col-sm-3">
				<table>
					<tr> 
					<td><input name="id" type="text"   class="form-control" placeholder="id" value="<c:out value='${row.user_id }'/>" ></td>			
					<td><input type="button" style="margin-left:5px" class="btn btn-warning" onclick="checkId()" value="중복체크"></td>
					</tr>
				</table>					
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">비밀번호</label>
				<div class="col-sm-3">
					<input name="password" type="text" class="form-control" placeholder="password" value="<c:out value='${row.user_pw }'/>" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">비밀번호확인</label>
				<div class="col-sm-3">
					<input name="password_confirm" type="text" class="form-control" placeholder="password_confirm" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">성명</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control" placeholder="name" value="<c:out value='${row.user_name }'/>" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">성별</label>
				<div class="col-sm-10">
					<c:set var="gender" value="${row.user_gender }" />
					<input name="gender" type="radio" value="남"	<c:if test="${row.user_gender.equals('남')}"> <c:out value="checked" /> </c:if> >남 
					<input name="gender" type="radio" value="여"	<c:if test="${row.user_gender.equals('여')}"> <c:out value="checked" /> </c:if> >여
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">생일</label>
				<div class="col-sm-6">
					<input style="display:inline-block" class="col-md-2 form-control"
						type="text" name="birthyy" maxlength="4" placeholder="년(4자)" 	size="6" value="${year}"> 
					<select name="birthmm"	id="birthmm" style="display:inline-block" class="col-md-2 form-control">
						<option value="">월</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select> <input style="display:inline-block" class="col-md-2 form-control"
								 type="text" name="birthdd" maxlength="2" placeholder="일" size="4" value="${day}">
				</div>
			</div>
			<div class="form-group  row ">
				<label class="col-sm-2">이메일</label>
				<div class="col-sm-10">
					<input style="display:inline-block" class="col-md-3 form-control"
							type="text" name="mail1" maxlength="50" value="${mail1}">@
					<select style="display:inline-block" class="col-md-2 form-control" 
								name="mail2" id="mail2">
						<option>naver.com</option>
						<option>daum.net</option>
						<option>gmail.com</option>
						<option>nate.com</option>
					</select>
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2">전화번호</label>
				<div class="col-sm-3">
					<input name="phone" type="text" class="form-control" placeholder="phone" value="<c:out value='${row.user_phone}'/>">
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2 ">주소</label>
				<div class="col-sm-4">
					<input name="address1" id="postcode" style="display : inline-block" type="text" 
					 value="<c:out value='${add1}'/>" class="col-md-6 form-control" placeholder="우편번호">
					<input type="button" class="btn btn-warning" style="margin-left:3px;margin-bottom:6px;" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					<input name="address2" class="form-control" type="text" id="roadAddress"  value="<c:out value='${add2}'/>" placeholder="도로명주소">
					<input name="address3" class="form-control" type="text" id="jibunAddress"  value="<c:out value='${add3}'/>" placeholder="지번주소">
					<input name="address4" class="form-control" type="text" id="sample4_detailAddress"  value="<c:out value='${add4}'/>" placeholder="상세주소">
				</div>
			</div>
			
			<div class="form-group  row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="submit" class="btn btn-primary" value="회원수정 "> 
				</div>
			</div>
		</form>	
	</div>
	</c:forEach>
</body>
</html>
<script type="text/javascript">
	function init() {
		setComboMailValue("${mail2}");
		setComboBirthValue("${month}");
	}

	function setComboMailValue(val) {
		var selectMail = document.getElementById('mail2');
		for (i = 0, j = selectMail.length; i < j; i++) {
			if (selectMail.options[i].value == val) {
				selectMail.options[i].selected = true; 
				break;
			}
		}
	}
	function setComboBirthValue(val) {
		var selectBirth = document.getElementById('birthmm'); 
		for (i = 0, j = selectBirth.length; i < j; i++){
			if (selectBirth.options[i].value == val){
				selectBirth.options[i].selected = true; 
				break;
			}
		}
	}
	function checkForm() {
		if (!document.newMember.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (!document.newMember.password.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		if (document.newMember.password.value != document.newMember.password_confirm.value) {
			alert("비밀번호를 동일하게 입력하세요.");
			return false;
		}
	}
</script>