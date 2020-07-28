<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="dbconn.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	 
	String realFolder = "";
	String filename = "";
	String savefile = "resources/image";
	ServletContext scontext = request.getSession().getServletContext();
	realFolder = scontext.getRealPath(savefile);
	String encType = "utf-8"; //인코딩 타입
	int maxSize = 5 * 1024 * 1024; //최대 업로드될 파일의 크기5Mb

	MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType,
		new DefaultFileRenamePolicy());

	String styleno = multi.getParameter("styleno");
	String c_name = multi.getParameter("c_name");
	String color = multi.getParameter("color");
	String unitPrice = multi.getParameter("unitPrice");
	String[] sizeA = multi.getParameterValues("size");
	String size = "  ";
	String description = multi.getParameter("description");	
	String manufacturer = multi.getParameter("manufacturer");
	String category = multi.getParameter("category");
	String unitsInStock = multi.getParameter("unitsInStock");
	if(sizeA != null){
		for(int i = 0; i < sizeA.length; i++){
			size += sizeA[i] + "  ";
		}
	}
	Integer price;

	if (unitPrice.isEmpty())
		price = 0;
	else
		price = Integer.valueOf(unitPrice);

	long stock;

	if (unitsInStock.isEmpty())
		stock = 0;
	else
		stock = Long.valueOf(unitsInStock);

	Enumeration files = multi.getFileNames();
	String fname = (String) files.nextElement();
	String fileName = multi.getFilesystemName(fname);
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "select * from PRODUCT where styleno = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, styleno);
	rs = pstmt.executeQuery();
	
	if (rs.next()) {		
		if (fileName != null) { 
			sql = "UPDATE PRODUCT SET c_name=?, unitPrice=?, size=?, description=?, manufacturer=?, color=?, category=?, unitsInStock=?, filename=? WHERE styleno=?";	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_name);
			pstmt.setInt(2, price);
			pstmt.setString(3, size);
			pstmt.setString(4, description);
			pstmt.setString(5, manufacturer);
			pstmt.setString(6, color);
			pstmt.setString(7, category);
			pstmt.setLong(8, stock);
			pstmt.setString(9, fileName);
			pstmt.setString(10, styleno);
			pstmt.executeUpdate();
		} else {
			sql = "UPDATE PRODUCT SET c_name=?, unitPrice=?, size=?, description=?, manufacturer=?, color=?, category=?, unitsInStock=? WHERE styleno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_name);
			pstmt.setInt(2, price);
			pstmt.setString(3, size);
			pstmt.setString(4, description);
			pstmt.setString(5, manufacturer);
			pstmt.setString(6, color);
			pstmt.setString(7, category);
			pstmt.setLong(8, stock);
			pstmt.setString(9, styleno);
			pstmt.executeUpdate();			
		}		
	}
	if (rs != null)
		rs.close();
	if (pstmt != null)
		pstmt.close();
	if (conn != null)
		conn.close();
	
	response.sendRedirect("editPage.jsp");
%>

