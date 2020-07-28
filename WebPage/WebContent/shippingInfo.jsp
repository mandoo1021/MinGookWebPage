<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Clothes"%>
<%@ page import="dao.ClothesRepository"%>
<%@ include file="dbconn.jsp" %>
<html>
<head> 
<jsp:include page="reference.jsp" />
<%
	String cartId = session.getId();
%>
<title>배송 정보</title>
</head>
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
</script>
<%
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "select * from MEMBER";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
%>
<body class="bg-light">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3"
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">배송 정보</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<form action="./processShippingInfo.jsp" class="form-horizontal"
		 method="post">
		 	<input type="hidden" name="cartId" value="<%=request.getParameter("cartId")%>"/>
		 	<div class="form-group  row">
				<label class="col-sm-2">주문자 아이디</label>
				<div class="col-sm-3">
					<input name="id" type="text" class="form-control" value="<%=session.getAttribute("sessionId") %>" disabled >
				</div>
			</div>	
			<div class="form-group  row">
				<label class="col-sm-2">수령인</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control" placeholder="name" >
				</div>
			</div>	
			<div class="form-group  row">
				<label class="col-sm-2">전화번호</label>
				<div class="col-sm-3">
					<input name="phone" type="text" class="form-control" placeholder="phone" >
				</div>
			</div>
			<div class="form-group  row">
				<label class="col-sm-2 ">주소</label>
				<div class="col-sm-4">
					<input name="address1" id="postcode" style="display : inline-block" type="text" class="col-md-6 form-control" placeholder="우편번호">
					<input type="button" class="btn btn-warning" style="margin-left:3px;margin-bottom:6px;" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					<input name="address2" class="form-control" type="text" id="roadAddress" placeholder="도로명주소">
					<input name="address3" class="form-control" type="text" id="jibunAddress" placeholder="지번주소">
					<input name="address4" class="form-control" type="text" id="sample4_detailAddress" placeholder="상세주소">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">배송 메모</label>
				<div class="col-sm-5">
					<textarea name="memo" cols="50" rows="2" class="form-control"></textarea>
				</div>
			</div>
			<hr style="margin-top:100px; margin-bottom:30px">
		<div class="form-group">
			<label>Product Info (상품 정보)</label>
		</div>
	<div class="table-responsive">
	  <table class="table table-hover" style="padding-bottom:100px">
	  	<thead align="center">
	  		<tr><th colspan="9">상품정보</th></tr>
	  	</thead>
	    <thead align="center">
	      <tr>
	    <th>번호</th>
		<th>상품명</th>
		<th>색상</th>
		<th>가격</th>
		<th>브랜드</th>
		<th>분류</th>
		<th>수량</th>
	      </tr>
	    </thead>
	    <%
	    	int sum = 0;
	    	int cnt = 0;
	    	ArrayList<Clothes> cartList = (ArrayList<Clothes>) session.getAttribute("cartlist");
	    	if(cartList == null) cartList = new ArrayList<Clothes>();
	    	for(int i = 0; i < cartList.size(); i++){
	    		Clothes clothes = cartList.get(i);
	    		int total = clothes.getUnitPrice() * clothes.getQuantity();
	    		sum = sum + total;
	    		cnt += 1;
	    %>
	    <tbody align="center">
	    	<tr>
	    		<td><%=cnt %></td>
	    		<td><%=clothes.getCname()%></td>
	    		<td><%=clothes.getColor()%></td>
	    		<td><%=clothes.getUnitPrice()%>원</td>
	    		<td><%=clothes.getManufacturer()%></td>
	    		<td><%=clothes.getCategory()%></td>
	    		<td><%=clothes.getQuantity()%></td>
	    	</tr>
	    </tbody>
	    <%
	    	}
	    %>
	    <tfoot>
	    	<tr>
	    		<th></th>
	    		<th></th>
	    		<th></th>
	    		<th></th>
	    		<th>총액</th>
	    		<th><%=sum %></th>
	    	</tr>
	    </tfoot>
	  </table>
	</div>
	<div class="container bg-light" style="height:300px" align="center">
		<div class="col-md-4 col-md-offset-4">
			<input type="submit" class="btn btn btn-lg btn-dark btn-block" value="주문하기">
		</div>
	</div>
	</form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>