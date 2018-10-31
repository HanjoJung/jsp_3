<%@page import="com.jhj.board.BoardDTO"%>
<%@page import="com.jhj.notice.NoticeDTO"%>
<%@page import="com.jhj.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>Bootstrap Theme Company Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="../../../temp/bootstrap.jsp"></c:import>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">

	<c:import url="../../../temp/header.jsp"></c:import>


	<div class="container-fluid">
		<div class="row">
			<h1>${board.toUpperCase()} Update</h1>
		</div>
		<div class="row">
			<form action="./${board}Update.do" method="post"
				enctype="multipart/form-data">

				<input type="hidden" name="num" value="${dto.getNum()}"
					readonly="readonly">
				<div class="form-group">
					<label for="title">제목:</label><input type="text"
						class="form-control" name="title" value="${dto.getTitle()}">
				</div>

				<div class="form-group">
					<label for="title">작성날자:</label> <input type="text" name="reg_dete"
						value="${dto.getReg_date()}" class="form-control"
						disabled="disabled">
				</div>

				<div class="form-group">
					<label for="title">조회수:</label> <input type="text" name="hit"
						value="${dto.getHit()}" class="form-control" disabled="disabled">
				</div>

				<div class="form-group">
					<label for="title">내용:</label>
					<textarea rows="10" cols="" name="contents" class="form-control">${dto.getContents()}</textarea>
				</div>

				<div class="form-group">
					<label for="title">작성자:</label> <input type="text" name="writer"
						readonly="readonly" class="form-control"
						value="${dto.getWriter()}">
				</div>
				<c:forEach items="${files}" var="file" varStatus="i">
					<div class="form-group">
						<label for="file">파일:</label> <input type="file"
							class="form-control" id="file" value="${file.oname}" name="f${i.index}">
					</div>
				</c:forEach>

				<button>완료</button>
			</form>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>