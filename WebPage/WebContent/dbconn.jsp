<%@ page import="java.sql.*" %>
<%
	Connection conn = null;
	
	try{
		String url="jdbc:mysql://localhost/mandoo1021";
		String user="mandoo1021";
		String password="alsrnr12";
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);
	} catch(SQLException ex){
		out.println("데이터베이스 연결이 실패했습니다.<br>");
		out.println("SQLException : " + ex.getMessage());
	}
%>
