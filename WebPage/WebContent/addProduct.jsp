<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="reference.jsp" />
<title>상품 등록</title>
<style>
</style>
</head>
<body class="bg-light">
	<jsp:include page="menu.jsp" />
	<div class="jumbotron bg-light">
		<div class="container">
			<div class="text-center">
				<h1 class="display-3"
					style="font-size: 70px; font-weight: bold; color: FFBE0A;">상품
					등록</h1>
			</div>
		</div>
	</div>
	<div class="container">
		<form name="newProduct" action="<c:url value="/processAddProduct.jsp"/>" class="form-horizontal"
		 method="post" enctype="multipart/form-data">
			<div class="form-group row">
				<label class="col-sm-2">스타일번호</label>
				<div class="col-sm-3">
					<input type="text" name="styleno" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상품명</label>
				<div class="col-sm-3">
					<input type="text" name="c_name" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">색상</label>
				<div class="col-sm-3">
					<input type="text" name="color" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">가격</label>
				<div class="col-sm-3">
					<input type="text" name="unitPrice" class="form-control">
				</div>
			</div>
			<div class="form-group row checkbox">
				<label class="col-sm-2">사이즈</label>
				<div class="col-sm-5">
					<input type="checkbox" name="size" value="S"> Small 
					<input type="checkbox" name="size" value="M"> Medium 
					<input type="checkbox" name="size" value="L"> Large 
					<input type="checkbox" name="size" value="XL"> Extra Large 
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상세 정보</label>
				<div class="col-sm-5">
					<textarea name="description" cols="50" rows="2" class="form-control"></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">브랜드명</label>
				<div class="col-sm-3">
					<input type="text" name="manufacturer" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">분류</label>
				<div class="col-sm-5">
					<input type="radio" name="category" value="Top"> Top 
					<input type="radio" name="category" value="Outer"> Outer  
					<input type="radio" name="category" value="Pants"> Pants  
					<input type="radio" name="category" value="Shoes"> Shoes 
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">재고</label>
				<div class="col-sm-3">
					<input type="text" name="unitsInStock" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">이미지</label>
				<div class="col-sm-3">
					<input type="file" name="productImage" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
					<input type="submit" class="btn btn btn-primary btn-warning" value="등록">
				</div>
			</div>
		</form>
	</div>
</body>
</html>