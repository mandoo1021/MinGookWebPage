<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Clothes"%>
<%@ page import="dao.ClothesRepository"%>
<%
	String id = request.getParameter("id");
	String send = "cart.jsp";
	
	if (id == null || id.trim().equals("")) {
		send = "index.jsp";
		return;
	}

	ClothesRepository dao = ClothesRepository.getInstance();

	Clothes clothes = dao.getProductById(id);
	if (clothes == null) {
		send = "exceptionNoStyleno.jsp";
	}

	ArrayList<Clothes> goodsList = dao.getAllProducts();
	Clothes goods = new Clothes();
	for (int i = 0; i < goodsList.size(); i++) {
		goods = goodsList.get(i);
		if (goods.getStyleNo().equals(id)) { 			
			break;
		}
	}
	
	ArrayList<Clothes> list = (ArrayList<Clothes>) session.getAttribute("cartlist");
	if (list == null) { 
		list = new ArrayList<Clothes>();
		session.setAttribute("cartlist", list);
	}

	int cnt = 0;
	Clothes goodsQnt = new Clothes();
	for (int i = 0; i < list.size(); i++) {
		goodsQnt = list.get(i);
		if (goodsQnt.getStyleNo().equals(id)) {
			cnt++;
			int orderQuantity = goodsQnt.getQuantity() + 1;
			goodsQnt.setQuantity(orderQuantity);
		}
	}

	if (cnt == 0) { 
		goods.setQuantity(1);
		list.add(goods);
	}

	response.sendRedirect(send);
%>