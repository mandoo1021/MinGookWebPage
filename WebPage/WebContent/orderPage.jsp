<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %> 
<html>
<head> 
<jsp:include page="reference.jsp" />
<title>주문 관리</title>
<script type="text/javascript">
	function deleteConfirm(id) {
		if (confirm("해당 내역을 삭제합니다") == true)
			location.href = "./deleteOrder.jsp?id=" + id;
		else
			return;
	}
</script>
</head>
<body class="bg-light">
	<%@ include file="dbconn.jsp" %>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3"
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">주문 관리</h1>
			</div>
		</div>
	</div>
		<div class="container" style="padding-bottom:10px">
			<form class="form-inline my-2 my-lg-0" action="orderPage.jsp">
				<select name="classification" class="form-control mr-sm-2" style="margin-right:5px">
					<option value="total">전체</option>
					<option value="order_no">주문번호</option>
					<option value="user_id">유저아이디</option>
					<option value="styleno">상품아이디</option>
					<option value="c_name">상품명</option>
					<option value="sdate">주문일자</option>
				</select>
				<input class="form-control mr-sm-2" name="search" type="search"
					placeholder="Search" aria-label="의류 검색">
				<button class="btn btn-warning" type="submit">검색</button>
			</form>
		</div>
	<div class="container">
		<div class="table-responsive">
			<table class="table table-hover">
				<thead align="center">
					<tr>
						<th>주문번호</th>
						<th>유저아이디</th>
						<th>상품아이디</th>
						<th>상품명</th>
						<th>수량</th>
						<th>주문일자</th>
						<th>비고</th>
					</tr>
				</thead>
				<%
					String classification = request.getParameter("classification");
					String search = request.getParameter("search");
					String searchTotal = "%"+search+"%";
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					if(search == null || search.equals("")){
						String sql = "select * from ORDERTABLE";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("order_no") %></td>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("styleno") %></td>
							<td><%=rs.getString("c_name") %></td>
							<td><%=rs.getInt("quantity") %></td>
							<td><%=rs.getString("sdate") %></td>
							<td><a href="#" onClick="deleteConfirm('<%=rs.getString("order_no")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					} else if(search != null && classification.equals("order_no")) {
						String sql = "select * from ORDERTABLE where order_no like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("order_no") %></td>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("styleno") %></td>
							<td><%=rs.getString("c_name") %></td>
							<td><%=rs.getString("quantity") %></td>
							<td><%=rs.getString("sdate") %></td>
							<td><a href="#" onClick="deleteConfirm('<%=rs.getString("order_no")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					}else if(search != null && classification.equals("user_id")) {
						String sql = "select * from ORDERTABLE where user_id like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("order_no") %></td>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("styleno") %></td>
							<td><%=rs.getString("c_name") %></td>
							<td><%=rs.getString("quantity") %></td>
							<td><%=rs.getString("sdate") %></td>
							<td><a href="#" onClick="deleteConfirm('<%=rs.getString("order_no")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					}else if(search != null && classification.equals("styleno")) {
						String sql = "select * from ORDERTABLE where styleno like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("order_no") %></td>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("styleno") %></td>
							<td><%=rs.getString("c_name") %></td>
							<td><%=rs.getString("quantity") %></td>
							<td><%=rs.getString("sdate") %></td>
							<td><a href="#" onClick="deleteConfirm('<%=rs.getString("order_no")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					}else if(search != null && classification.equals("c_name")) {
						String sql = "select * from ORDERTABLE where c_name like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("order_no") %></td>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("styleno") %></td>
							<td><%=rs.getString("c_name") %></td>
							<td><%=rs.getString("quantity") %></td>
							<td><%=rs.getString("sdate") %></td>
							<td><a href="#" onClick="deleteConfirm('<%=rs.getString("order_no")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					}else if(search != null && classification.equals("sdate")) {
						String sql = "select * from ORDERTABLE where sdate like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("order_no") %></td>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("styleno") %></td>
							<td><%=rs.getString("c_name") %></td>
							<td><%=rs.getString("quantity") %></td>
							<td><%=rs.getString("sdate") %></td>
							<td><a href="#" onClick="deleteConfirm('<%=rs.getString("order_no")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					}
	 			%>
					</tbody>
				</table>
		</div>
	</div>
</body>
</html>