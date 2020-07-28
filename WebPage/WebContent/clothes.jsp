<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<html>
<head> 
<title>mini</title>
</head>
<body>
<div class="container">
		<div class="row" align="center">
			<%@include file="dbconn.jsp"%>
			<%	
				request.setCharacterEncoding("utf-8");
				
				String category = request.getParameter("category");
				String id = request.getParameter("search");
				String search = "%"+id+"%";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				request.setCharacterEncoding("UTF-8");
				
				if(category == null && id == null){
					String sql = "select * from PRODUCT";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()){
			%>
			<div class="col-md-4">
				<a href="./detail.jsp?id=<%=rs.getString("styleno") %>"><img width="400" height="350"
					src="/resources/image/<%=rs.getString("filename")%>"
					style="width: 100%"></a>
				<h3><%=rs.getString("c_name") %></h3>
				<p><%=rs.getString("manufacturer") %>
				<p><%=rs.getString("unitPrice") %>원
				<p>
					<a href="./detail.jsp?id=<%=rs.getString("styleno") %>"
						class="btn btn-warning" role="button">상세 정보 &raquo;</a>
			</div>
			<%
					}
				} else if(category == null && id != null){
					String sql = "select * from PRODUCT where c_name like ? or manufacturer like ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.setString(2, search);
					rs = pstmt.executeQuery();
					while(rs.next()){
						%>
						<div class="col-md-4">
							<img width="400" height="350"
								src="/resources/image/<%=rs.getString("filename")%>"
								style="width: 100%">
							<h3><%=rs.getString("c_name") %></h3>
							<p><%=rs.getString("manufacturer") %>
							<p><%=rs.getString("unitPrice") %>원
							<p>
								<a href="./detail.jsp?id=<%=rs.getString("styleno") %>"
									class="btn btn-warning" role="button">상세 정보 &raquo;</a>
						</div>
				<%		
					}
				}	else {
					String sql = "select * from PRODUCT where category=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, category);
					rs = pstmt.executeQuery();
					while(rs.next()){
						%>
						<div class="col-md-4">
							<img width="400" height="350"
								src="/resources/image/<%=rs.getString("filename")%>"
								style="width: 100%">
							<h3><%=rs.getString("c_name") %></h3>
							<p><%=rs.getString("manufacturer") %>
							<p><%=rs.getString("unitPrice") %>원
							<p>
								<a href="./detail.jsp?id=<%=rs.getString("styleno") %>"
									class="btn btn-warning" role="button">상세 정보 &raquo;</a>
						</div>
			<%
					}
				}
				if(rs != null) rs.close();
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close(); 
				response.sendRedirect("index.jsp");
			%>
		</div>
		<hr>
	</div>
</body>
</html>