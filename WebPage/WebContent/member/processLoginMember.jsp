<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String password = request.getParameter("password");
%>

<sql:setDataSource var="dataSource"
	url="jdbc:mysql://localhost/mandoo1021"
	driver="com.mysql.jdbc.Driver" user="mandoo1021" password="alsrnr12" />

<sql:query dataSource="${dataSource}" var="resultSet">
   SELECT * FROM MEMBER WHERE user_id=? and user_pw=?  
   <sql:param value="<%=id%>" />
	<sql:param value="<%=password%>" />
</sql:query>

<c:forEach var="row" items="${resultSet.rows}">
	<%
		session.setAttribute("sessionId", id);
	%>
	<c:redirect url="resultMember.jsp?msg=2" />
</c:forEach>

<c:redirect url="memberLogin.jsp?error=1" />
