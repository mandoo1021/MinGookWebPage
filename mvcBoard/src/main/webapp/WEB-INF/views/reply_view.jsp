<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css"/>" />
<title>Insert title here</title>
</head>
<body>
<%-- 
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="reply" method="post">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup}">
			<input type="hidden" name="bStep" value="${reply_view.bStep}">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent}">
			<tr>
				<td> 번호 </td>
				<td> ${reply_view.bId} </td>
			</tr>
			<tr>
				<td> 히트 </td>
				<td> ${reply_view.bHit} </td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="bName" value="${reply_view.bName}"></td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" value="${reply_view.bTitle}"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <textarea rows="10"  name="bContent">${reply_view.bContent}</textarea></td>
			</tr>
			<tr >
				<td colspan="2"><input type="submit" value="답변"> <a href="list" >목록</a></td>
			</tr>
		</form>
	</table> --%>
	<div class="container">
		<table class="table text-center">
			<form action="reply" class="form-horizontal" method="post">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup}">
			<input type="hidden" name="bStep" value="${reply_view.bStep}">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent}">
				<tr>
					<td>답변하기</td>
				</tr>
				<tr>
					<td>
						<div class="form-group row">
							<label class="col-sm-2 control-label">번호</label>
							<div class="col-sm-3">
								${reply_view.bId}
							</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="form-group row"ㅇ>
							<label class="col-sm-2 control-label">히트</label>
							<div class="col-sm-3">
								${reply_view.bHit}
							</div>
					</td>
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
								<input type="submit" class="btn btn-primary " value="답변 "> &nbsp;&nbsp;
								<a href="list"><input type="button" class="btn btn-primary " value="목록 "></a>
							</div>
						</div>
					</td>
				</tr>
			</form>
		</table>
	</div>
	
</body>
</html>