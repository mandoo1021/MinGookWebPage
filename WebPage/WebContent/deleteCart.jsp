<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ page import="model.Clothes" %>
<%@ page import="dao.ClothesRepository" %>
<%
	String id = request.getParameter("cartId");
	if (id == null || id.trim().equals("")){
		response.sendRedirect("cart.jsp");
		return;
	}
	
	session.invalidate();
	response.sendRedirect("cart.jsp");
%>