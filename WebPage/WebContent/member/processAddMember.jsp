<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String year = request.getParameter("birthyy");
	String month = request.getParameterValues("birthmm")[0];
	String day = request.getParameter("birthdd");
	String birth = year + "-" + month + "-" + day;
	String mail1 = request.getParameter("mail1");
	String mail2 = request.getParameterValues("mail2")[0];
	String mail = mail1 + "@" + mail2;
	String phone = request.getParameter("phone");
	String address1 = request.getParameter("address1");
	String address2 = request.getParameter("address2");
	String address3 = request.getParameter("address3");
	String address4 = request.getParameter("address4");
	String address = address1 + "-" + address2 + "-" +  address3 + "-" +  address4;

	Date currentDatetime = new Date(System.currentTimeMillis());
	java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
	java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
%>

<sql:setDataSource var="dataSource"
	url="jdbc:mysql://localhost/mandoo1021"
	driver="com.mysql.jdbc.Driver" user="mandoo1021" password="alsrnr12" />

<sql:update dataSource="${dataSource}" var="resultSet">
   INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
   <sql:param value="<%=id%>" />
	<sql:param value="<%=password%>" />
	<sql:param value="<%=name%>" />
	<sql:param value="<%=gender%>" />
	<sql:param value="<%=birth%>" />
	<sql:param value="<%=mail%>" />
	<sql:param value="<%=phone%>" />
	<sql:param value="<%=address%>" />
	<sql:param value="<%=timestamp%>" />
</sql:update>

<c:if test="${resultSet>=1}">
	<c:redirect url="resultMember.jsp?msg=1" />
</c:if>

