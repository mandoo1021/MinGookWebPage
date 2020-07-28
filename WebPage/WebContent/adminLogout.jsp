<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%
	session.removeAttribute("sessionAdminId");
	response.sendRedirect("index.jsp");
%>