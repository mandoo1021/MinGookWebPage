<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.Date" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	String sessionId = (String) session.getAttribute("sessionId");
	Date now = new Date();
	SimpleDateFormat vans = new SimpleDateFormat("yyyy-MM-dd");
	String sdate = vans.format(now);
	
	String add1 = request.getParameter("address1");
	String add2 = request.getParameter("address2");
	String add3 = request.getParameter("address3");
	String add4 = request.getParameter("address4");
	String address = add1 + " " + add2 + " " + add3 + " " + add4;
	
	Cookie cartId = new Cookie("Shipping_cartId", URLEncoder.encode(request.getParameter("cartId"), "utf-8"));
	Cookie u_id = new Cookie("Shipping_uId", URLEncoder.encode(sessionId, "utf-8"));
	Cookie u_name = new Cookie("Shipping_name", URLEncoder.encode(request.getParameter("name"), "utf-8"));
	Cookie date = new Cookie("Shipping_shippingDate", URLEncoder.encode(sdate, "utf-8"));
	Cookie addressName = new Cookie("Shipping_addressName", URLEncoder.encode(address, "utf-8"));

	cartId.setMaxAge(365 * 24 * 60 * 60); u_id.setMaxAge(365 * 24 * 60 * 60);
	u_name.setMaxAge(365 * 24 * 60 * 60); date.setMaxAge(365 * 24 * 60 * 60);
	addressName.setMaxAge(365 * 24 * 60 * 60);

	response.addCookie(cartId); response.addCookie(u_id);
	response.addCookie(u_name); response.addCookie(date);
	response.addCookie(addressName);

	response.sendRedirect("orderConfirmation.jsp");
%>
