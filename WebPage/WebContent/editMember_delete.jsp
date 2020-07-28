<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%
	String id = request.getParameter("id");
%>
<sql:setDataSource var="dataSource"
	url="jdbc:mysql://localhost/mandoo1021"
	driver="com.mysql.jdbc.Driver" user="mandoo1021" password="alsrnr12" />

<sql:update dataSource="${dataSource}" var="resultSet">
   DELETE FROM MEMBER WHERE user_id = ?
   <sql:param value="<%=id%>" />
</sql:update>

<c:if test="${resultSet>=1}"> 
	<c:redirect url="editMember.jsp" />
</c:if>