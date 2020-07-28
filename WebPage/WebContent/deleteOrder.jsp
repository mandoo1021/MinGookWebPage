<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Clothes" %>
<%@ page import="dao.ClothesRepository" %>
<%@ include file="dbconn.jsp"%>
<%
	String orderId = request.getParameter("id");

	PreparedStatement pstmt = null;	
	ResultSet rs = null;

	String sql = "select * from ORDERTABLE";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	ClothesRepository dao = ClothesRepository.getInstance();
	
	ArrayList<Clothes> list = dao.getAllProducts();

	if (rs.next()) {
		sql = "delete from ORDERTABLE where order_no = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, orderId);
		pstmt.executeUpdate();
	} else
		out.println("일치하는 주문내역이 없습니다");
	
	if (rs != null)
		rs.close();
	if (pstmt != null)
		pstmt.close();
	if (conn != null)
		conn.close();
	
	
	response.sendRedirect("orderPage.jsp");
%>
