<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="model.Clothes"%>
<%@ page import="dao.ClothesRepository"%>
<%
	request.setCharacterEncoding("UTF-8");

	String cartId = session.getId();

	String shipping_cartId = "";
	String shipping_uId = "";
	String shipping_name = "";
	String shipping_shippingDate = "";
	String shipping_addressName = "";
	
	Cookie[] cookies = request.getCookies();

	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie thisCookie = cookies[i];
			String n = thisCookie.getName();
			if (n.equals("Shipping_cartId"))
				shipping_cartId = URLDecoder.decode((thisCookie.getValue()), "utf-8");
			if (n.equals("Shipping_uId"))
				shipping_uId = URLDecoder.decode((thisCookie.getValue()), "utf-8");
			if (n.equals("Shipping_name"))
				shipping_name = URLDecoder.decode((thisCookie.getValue()), "utf-8");
			if (n.equals("Shipping_shippingDate"))
				shipping_shippingDate = URLDecoder.decode((thisCookie.getValue()), "utf-8");
			if (n.equals("Shipping_addressName"))
				shipping_addressName = URLDecoder.decode((thisCookie.getValue()), "utf-8");
		}
	}
%>
<html>
<head>
<jsp:include page="reference.jsp" />
<title>주문 정보</title>
</head>
<body class="bg-light">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3"
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">주문 완료</h1>
			</div>
		</div>
	</div>
	<div class="container col-8 alert alert-info">
		<div class="text-center ">
			<h1>영수증</h1>
		</div>
		<div class="row justify-content-between">
			<div class="col-4" align="left">
				<strong>배송 주소</strong> <br> 성명 : <% out.println(shipping_name); %><br> 
				주소 : <%	out.println(shipping_addressName);%><br>
			</div>
			<div class="col-4" align="right">
				<p>	<em>주문일자: <% out.println(shipping_shippingDate);	%></em>
			</div>
		</div>
		<div>
			<table class="table table-hover">			
			<tr>
				<th class="text-center">상품명</th>
				<th class="text-center">#</th>
				<th class="text-center">가격</th>
				<th class="text-center">소계</th>
			</tr>
			<%@ include file="dbconn.jsp"%>
			<%
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				int sum = 0;
				ArrayList<Clothes> cartList = (ArrayList<Clothes>) session.getAttribute("cartlist");
				if (cartList == null)
					cartList = new ArrayList<Clothes>();
				for (int i = 0; i < cartList.size(); i++) { // 상품리스트 하나씩 출력하기
					Clothes clothes = cartList.get(i);
					int total = clothes.getUnitPrice() * clothes.getQuantity();
					sum = sum + total;
					String sql = "insert into ORDERTABLE(order_no, user_id, styleno, c_name, quantity, sdate) values(concat('ID', cast( cast( rand()*10000000 as unsigned) as char)),?,?,?,?,?);";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, shipping_uId);
					pstmt.setString(2, clothes.getStyleNo());
					pstmt.setString(3, clothes.getCname());
					pstmt.setInt(4, clothes.getQuantity());
					pstmt.setString(5, shipping_shippingDate);
					
					pstmt.executeUpdate();
			%>
			<tr>
				<td class="text-center"><em><%=clothes.getCname()%> </em></td>
				<td class="text-center"><%=clothes.getQuantity()%></td>
				<td class="text-center"><%=clothes.getUnitPrice()%>원</td>
				<td class="text-center"><%=total%>원</td>
			</tr>
			<%
				}
			%>
			<tr>
				<td> </td>
				<td> </td>
				<td class="text-right">	<strong>총액: </strong></td>
				<td class="text-center text-danger"><strong><%=sum%> </strong></td>
			</tr>
			</table>
			배송은 주문일로부터 2~3일이내에 수령 가능합니다!<br>
			주문 번호 : <%= shipping_cartId.substring(1, 8) %>			
				<div style="margin-top:100px" align="center"><a href="index.jsp"  class="btn btn btn-success" 
				role="button"> 주문 완료 </a></div>		
		</div>
	</div>	
</body>
</html>
<%
	session.removeAttribute("cartlist");	

	for (int i = 0; i < cookies.length; i++) {
		Cookie thisCookie = cookies[i];
		String n = thisCookie.getName();

		if (n.equals("Shipping_cartId"))
			thisCookie.setMaxAge(0);
		if (n.equals("Shipping_uId"))
			thisCookie.setMaxAge(0);
		if (n.equals("Shipping_name"))
			thisCookie.setMaxAge(0);
		if (n.equals("Shipping_shippingDate"))
			thisCookie.setMaxAge(0);
		if (n.equals("Shipping_addressName"))
			thisCookie.setMaxAge(0);
		
		response.addCookie(thisCookie);
	}
	
%>
