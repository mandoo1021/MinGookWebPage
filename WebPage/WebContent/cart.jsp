<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Clothes"%>
<%@ page import="dao.ClothesRepository"%>
<%@ include file="dbconn.jsp" %>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<%
	String cartId = session.getId();
%>
<%
	String sessionId = session.getId();
%>
<script>
	function loginCheck(){
		<%
			Object obj = session.getAttribute("sessionId");
			if(obj == null){
		%>
				alert("로그인이 필요합니다");
				location.href = "http://mandoo1021.cafe24.com/member/memberLogin.jsp"
				return false;
		<%
			} else {	
		%>	
				return true;
		<%	
			}
		%>
	}
</script>
<title>장바구니</title>
</head>
<body class="bg-light">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3" style="font-size:70px; font-weight:bold;color:FFBE0A;">장바구니</h1>
			</div>
		</div>
	</div>
	<div class="container bg-light">
		<div class="row">
			<table width="100%">
				<tr>
					<td align="left"><a href="./deleteCart.jsp?cartId=<%=cartId%>" class="btn btn-danger">삭제하기</a></td>
					<td align="right"><a href="./shippingInfo.jsp?cartId=<%= cartId %>" onclick="return loginCheck();" class="btn btn-success">주문하기</a></td>
				</tr>
			</table>
		</div>
		<div style="padding-top: 50px">
			<table class="table table-hover">
				<tr>
					<th>상품</th>
					<th>가격</th>
					<th>수량</th>
					<th>소계</th>
					<th>비고</th>
				</tr>
				<%	
					int sum = 0;
					ArrayList<Clothes> cartList = (ArrayList<Clothes>) session.getAttribute("cartlist");
					if (cartList == null)
						cartList = new ArrayList<Clothes>();

					for (int i = 0; i < cartList.size(); i++) { // 상품리스트 하나씩 출력하기
						Clothes clothes = cartList.get(i);
						int total = clothes.getUnitPrice() * clothes.getQuantity();
						sum = sum + total;
				%>
				<tr>
					<td><%=clothes.getStyleNo()%> - <%=clothes.getCname()%></td>
					<td><%=clothes.getUnitPrice()%></td>
					<td><%=clothes.getQuantity()%></td>
					<td><%=total%></td>
					<td><a href="./removeCart.jsp?id=<%=clothes.getStyleNo()%>" class="badge badge-danger">삭제</a></td>
				</tr>
				<%
					}
				%>
				<tr>
					<th></th>
					<th></th>
					<th>총액</th>
					<th><%=sum%></th>
					<th></th>
				</tr>
			</table>
			<a href="./index.jsp" class="btn btn-warning"> &laquo; 쇼핑 계속하기</a>
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
