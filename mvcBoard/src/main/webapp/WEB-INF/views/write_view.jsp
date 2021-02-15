<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css"/>" />
<title>스프링 게시판</title>
</head>
<body>
	<div class="container">
		<table class="table text-center">
			<form action="write" class="form-horizontal" method="post">
				<tr>
					<td>글쓰기</td>
				</tr>
				<tr>
					<td>
						<div class="form-group row">
							<label class="col-sm-2 control-label">성명</label>
							<div class="col-sm-3">
								<input name="bName" type="text" class="form-control" size="50">
							</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group row">
							<label class="col-sm-2 control-label">제목</label>
							<div class="col-sm-5">
								<input name="bTitle" type="text" class="form-control">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group row">
							<label class="col-sm-2 control-label">내용</label>
							<div class="col-sm-8">
								<textarea name="bContent" cols="50" rows="5"
									class="form-control"></textarea>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group row">
							<div class="col-sm-offset-2 col-sm-10 ">
								<input type="submit" class="btn btn-primary " value="등록 ">
								<input type="reset" class="btn btn-primary " value="취소 ">
							</div>
						</div>
					</td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>