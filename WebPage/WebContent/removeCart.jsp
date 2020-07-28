<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Clothes" %>
<%@ page import="dao.ClothesRepository" %> 
<%
	String id = request.getParameter("id");
	if (id == null || id.trim().equals("")){
		response.sendRedirect("detail.jsp");
		return;
	}
	
	ClothesRepository dao = ClothesRepository.getInstance();
	
	Clothes clothes = dao.getProductById(id);
	if(clothes == null){
		response.sendRedirect("exceptionNoStyleno.jsp");
	}
	
	ArrayList<Clothes> cartList = (ArrayList<Clothes>) session.getAttribute("cartlist");
	Clothes goodsQnt = new Clothes();
	// 상품 리스트 하나씩 출력하기
	for(int i = 0; i < cartList.size(); i++){
		goodsQnt = cartList.get(i);
		if(goodsQnt.getStyleNo().equals(id)) cartList.remove(goodsQnt);
	}
	
	response.sendRedirect("cart.jsp");
%>