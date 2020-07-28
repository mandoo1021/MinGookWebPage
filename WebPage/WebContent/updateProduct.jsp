<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<jsp:include page="reference.jsp" />
<title>상품 수정</title>
<script>
function updateConfirm(id) {
		if (confirm("해당 상품을 수정하시겠습니까?") == true)
			submit();
		else
			return;
	}
</script>
</head>
<body class="bg-light">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3"
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">상품
					수정</h1>
			</div>
		</div>
	</div>
	<%@ include file="dbconn.jsp"%>
	<%
		String productId = request.getParameter("id");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from PRODUCT where styleno = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, productId);
		rs = pstmt.executeQuery();
		if (rs.next()) {
	%>
	<div class="container bg-light">
		<form name="newProduct" action="./processUpdateProduct.jsp"
			class="form-horizontal" method="post" enctype="multipart/form-data">
			<div class="form-group row">
				<label class="col-sm-2">스타일번호</label>
				<div class="col-sm-3">
					<input type="text" id="styleno" name="styleno" class="form-control"
						value='<%=rs.getString("styleno")%>'>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상품명</label>
				<div class="col-sm-3">
					<input type="text" id="c_name" name="c_name" class="form-control"
						value="<%=rs.getString("c_name")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">색상</label>
				<div class="col-sm-3">
					<input type="text" id="color" name="color" class="form-control"
						value="<%=rs.getString("color")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">가격</label>
				<div class="col-sm-3">
					<input type="text" id="unitPrice" name="unitPrice"
						class="form-control" value="<%=rs.getInt("unitPrice")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">사이즈</label>
				<div class="col-sm-5">
					<input type="checkbox" name="size" value="S"> Small 
					<input type="checkbox" name="size" value="M"> Medium 
					<input type="checkbox" name="size" value="L"> Large 
					<input type="checkbox" name="size" value="XL"> Extra Large 
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상세 정보</label>
				<div class="col-sm-5">
					<textarea name="description" cols="50" rows="2"
						class="form-control"><%=rs.getString("description")%></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">브랜드명</label>
				<div class="col-sm-3">
					<input type="text" name="manufacturer" class="form-control"
						value="<%=rs.getString("manufacturer")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">분류</label>
				<div class="col-sm-5">
					<input type="radio" name="category" value="Top"> Top 
					<input type="radio" name="category" value="Outer"> Outer  
					<input type="radio" name="category" value="Pants"> Pants  
					<input type="radio" name="category" value="Shoes"> Shoes 
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">재고</label>
				<div class="col-sm-3">
					<input type="text" id="unitsInStock" name="unitsInStock"
						class="form-control" value="<%=rs.getLong("unitsInStock")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">이미지</label>
				<div class="col-sm-5">
					<input type="file" name="filename" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="submit" class="btn btn-primary" value="수정">
				</div>
			</div>
		</form>
	</div>
	</div>
	</div>
	<%
		}
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	%>
</body>
</html>
