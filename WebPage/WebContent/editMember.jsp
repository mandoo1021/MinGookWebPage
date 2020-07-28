<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %> 
<html>
<head> 
<jsp:include page="reference.jsp" />
<title>회원 관리</title>
<script type="text/javascript">
	function deleteConfirm(id) {
		if (confirm("해당 회원을 삭제합니다") == true)
			location.href = "./deleteProduct.jsp?id=" + id;
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
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">회원 관리</h1>
			</div>
		</div>
	</div>
		<div class="container" style="padding-bottom:10px">
			<form class="form-inline my-2 my-lg-0" action="editMember.jsp">
				<select name="classification" class="form-control mr-sm-2" style="margin-right:5px">
					<option value="total">전체</option>
					<option value="name">이름</option>
					<option value="birth">생년월일</option>
					<option value="email">이메일</option>
					<option value="phone">전화번호</option>
					<option value="address">주소</option>
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
						<th>아이디</th>
						<th style="width:10%">이름</th>
						<th style="width:8%">성별</th>
						<th>생일</th>
						<th>이메일</th>
						<th>핸드폰번호</th>
						<th>주소</th>
						<th>가입일</th>
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
						String sql = "select * from MEMBER";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("user_id") %></td>
							<td style="width:10%"><%=rs.getString("user_name") %></td>
							<td style="width:8%"><%=rs.getString("user_gender") %></td>
							<td><%=rs.getString("user_birth") %></td>
							<td><%=rs.getString("user_email") %></td>
							<td><%=rs.getString("user_phone") %></td>
							<td><%=rs.getString("user_address") %></td>
							<td><%=rs.getString("resist_day") %></td>
							<td><a href="editMember_delete.jsp?id=<%=rs.getString("user_id") %>" 
								onClick="deleteConfirm('<%=rs.getString("user_id")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					} else if(search != null && classification.equals("name")) {
						String sql = "select * from MEMBER where user_name like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("user_name") %></td>
							<td><%=rs.getString("user_gender") %></td>
							<td><%=rs.getString("user_birth") %></td>
							<td><%=rs.getString("user_email") %></td>
							<td><%=rs.getString("user_phone") %></td>
							<td><%=rs.getString("user_address") %></td>
							<td><%=rs.getString("resist_day") %></td>
							<td><a href="editMember_delete.jsp?id=<%=rs.getString("user_id") %>" 
								onClick="deleteConfirm('<%=rs.getString("user_id")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					}else if(search != null && classification.equals("birth")) {
						String sql = "select * from MEMBER where user_birth like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("user_name") %></td>
							<td><%=rs.getString("user_gender") %></td>
							<td><%=rs.getString("user_birth") %></td>
							<td><%=rs.getString("user_email") %></td>
							<td><%=rs.getString("user_phone") %></td>
							<td><%=rs.getString("user_address") %></td>
							<td><%=rs.getString("resist_day") %></td>
							<td><a href="editMember_delete.jsp?id=<%=rs.getString("user_id") %>" 
								onClick="deleteConfirm('<%=rs.getString("user_id")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					}else if(search != null && classification.equals("email")) {
						String sql = "select * from MEMBER where user_email like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("user_name") %></td>
							<td><%=rs.getString("user_gender") %></td>
							<td><%=rs.getString("user_birth") %></td>
							<td><%=rs.getString("user_email") %></td>
							<td><%=rs.getString("user_phone") %></td>
							<td><%=rs.getString("user_address") %></td>
							<td><%=rs.getString("resist_day") %></td>
							<td><a href="editMember_delete.jsp?id=<%=rs.getString("user_id") %>" 
								onClick="deleteConfirm('<%=rs.getString("user_id")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					}else if(search != null && classification.equals("phone")) {
						String sql = "select * from MEMBER where user_phone like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("user_name") %></td>
							<td><%=rs.getString("user_gender") %></td>
							<td><%=rs.getString("user_birth") %></td>
							<td><%=rs.getString("user_email") %></td>
							<td><%=rs.getString("user_phone") %></td>
							<td><%=rs.getString("user_address") %></td>
							<td><%=rs.getString("resist_day") %></td>
							<td><a href="editMember_delete.jsp?id=<%=rs.getString("user_id") %>" 
								onClick="deleteConfirm('<%=rs.getString("user_id")%>')"  
								class="btn btn btn-primary btn-danger">삭제</a></td>
						</tr>
				<%
						}
					}else if(search != null && classification.equals("address")) {
						String sql = "select * from MEMBER where user_address like ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, searchTotal);
						rs = pstmt.executeQuery();
						while(rs.next()) {
				%>
					<tbody align="center">
						<tr>
							<td><%=rs.getString("user_id") %></td>
							<td><%=rs.getString("user_name") %></td>
							<td><%=rs.getString("user_gender") %></td>
							<td><%=rs.getString("user_birth") %></td>
							<td><%=rs.getString("user_email") %></td>
							<td><%=rs.getString("user_phone") %></td>
							<td><%=rs.getString("user_address") %></td>
							<td><%=rs.getString("resist_day") %></td>
							<td><a href="editMember_delete.jsp?id=<%=rs.getString("user_id") %>" 
								onClick="deleteConfirm('<%=rs.getString("user_id")%>')"  
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