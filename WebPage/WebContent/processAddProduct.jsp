<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Clothes" %>
<%@ page import="dao.ClothesRepository" %>

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
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		String url = "jdbc:mysql://localhost/mandoo1021";
		String user = "mandoo1021";
		String password = "alsrnr12";

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);

		String sql = "insert into PRODUCT values(?,?,?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, styleno);
		pstmt.setString(2, c_name);
		pstmt.setString(3, color);  
		pstmt.setInt(4, price);  
		pstmt.setString(5, size);	
		pstmt.setString(6, description);
		pstmt.setString(7, manufacturer);
		pstmt.setString(8, category);
		pstmt.setLong(9, stock);
		pstmt.setString(10, fileName);
		
		pstmt.executeUpdate();
		

	} catch (SQLException ex) {
		out.println("Member 테이블에 삽입이 실패되었습니다.<br>");
		out.println("SQLException: " + ex.getMessage());
	} finally {
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	response.sendRedirect("index.jsp");
%>
