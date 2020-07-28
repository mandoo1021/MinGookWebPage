<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ page import="dao.ClothesRepository" %>
<%@ page import="model.Clothes" %>
<%@ page import="java.sql.*" %>
<%@ page errorPage="exceptionNoStyleno.jsp"%>
<html>
<head> 
<jsp:include page="reference.jsp"/>
<title>Mini</title>
<script type="text/javascript">
function addToCart() {
	if(confirm("상품을 장바구니에 추가하시겠습니까?")){
		document.addForm.submit();
	}else{
		document.addForm.reset();
	}

}

</script>
</head>
<body class="bg-light">
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3"
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">상세 정보</h1>
			</div>
		</div>
	</div>
	<%
		String id = request.getParameter("id");
	%>
	<div class="container">
		<div class="row">
			<%@include file="dbconn.jsp"%>
			<%
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "select * from PRODUCT where styleno=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()){
			%>
			<div class="col-md-5">
				<img src="/resources/image/<%=rs.getString("filename")%>"
					style="width:100%">
			</div>
			<div class="col-md-6">
				<h3><%=rs.getString("c_name") %></h3>
				<p><%=rs.getString("description") %>
				<p><b>의류 코드</b> : <span class="badge badge-danger">
				<%=rs.getString("styleno") %></span>
				<p><b>제조사</b> : <%=rs.getString("manufacturer") %>
				<p><b>사이즈</b> : <%=rs.getString("size") %>
				<p><b>분류</b> : <%=rs.getString("category") %>
				<p><b>재고</b> : <%=rs.getString("unitsInStock") %>
				<h4><%=rs.getString("unitPrice") %>원</h4>
				<p><form name="addForm" action="./addCart.jsp?id=<%=rs.getString("styleno") %>" method="post">
					      <a href="#" class="btn btn btn-dark" onclick="addToCart()" 
					      style="width:319px;margin-bottom:5px">Buy now</a><br>
					      <a href="./cart.jsp" class="btn btn-warning" style="width:157;">cart</a>
					      <a href="./index.jsp" class="btn btn-light" style="width:157; border:1px solid lightgray">list</a>
			      </form>
			</div>
			<%
				}
			%>
		</div>
	</div>
	<hr>
	<div class="container bg-light">
		<div class="row">
			<p><h3 style="padding-right:50px; border-right:1px solid lightgray;">Size Info</h3>
			<p><img style="padding-left:50px;" src="./resources/image/sizetable.GIF">
		</div>
	</div>
	<hr>
	<jsp:include page="footer.jsp" />
</body>
</html>