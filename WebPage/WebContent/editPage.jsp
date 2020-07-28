<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ page import="java.sql.*" %>
<html>
<head> 
<jsp:include page="reference.jsp" />
<title>상품 편집</title>
<script type="text/javascript">
	function deleteConfirm(id) {
		if (confirm("해당 상품을 삭제합니다!!"))
			location.href = "./deleteProduct.jsp?id=" + id;
		else
			return;
	}
</script>
</head>
<%@ include file="dbconn.jsp" %>
<%
	String edit = request.getParameter("edit");
%>
<body class="bg-light">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3"
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">상품
					편집</h1>
			</div>
		</div>
	</div>
<div class="container">
	<div class="table-responsive">
	  <table class="table table-hover">
	    <thead align="center">
	      <tr>
		<th>StyleNo.</th>
		<th>상품명</th>
		<th>색상</th>
		<th>가격</th>
		<th>사이즈</th>
		<th>브랜드</th>
		<th>분류</th>
		<th>재고</th>
		<th colspan="2">비고</th>
	      </tr>
	    </thead>
	<%
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from PRODUCT";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
	%>
	    <tbody align="center">
	      <tr>
		<td><%=rs.getString("styleno") %></td>
		<td><%=rs.getString("c_name") %></td>
		<td><%=rs.getString("color") %></td>
		<td><%=rs.getString("unitPrice") %></td>
		<td><%=rs.getString("size") %></td>
		<td><%=rs.getString("manufacturer") %></td>
		<td><%=rs.getString("category") %></td>
		<td><%=rs.getString("unitsInStock") %></td>
		<td><a href="./updateProduct.jsp?id=<%=rs.getString("styleno")%>" class="btn btn btn-primary btn-primary">수정</a></td>
		<td><a href="#" onClick="deleteConfirm('<%=rs.getString("styleno")%>')"  class="btn btn btn-primary btn-danger">삭제</a></td>
	      </tr>
	      <%
				}
		  %>
	    </tbody>
	  </table>
	</div>
</div>
</body>
</html>