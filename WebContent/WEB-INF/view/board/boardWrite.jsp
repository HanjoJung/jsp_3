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
			<h1>${board.toUpperCase()}Writer</h1>
		</div>
		<div class="row">
			<form action="${board}Write.do" method="post"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="title">제목:</label> <input type="text"
						class="form-control" id="title" placeholder="Enter title"
						name="title">
				</div>
				<div class="form-group">
					<label for="contents">작성자:</label> <input type="text"
						class="form-control" id="writer" name="writer">
				</div>
				<div class="form-group">
					<label for="contents">내용:</label>
					<textarea rows="10" cols="3" class="form-control" id="contents"
						placeholder="Enter contents" name="contents"></textarea>
				</div>
				<div class="form-group">
					<label for="contents">파일:</label> <input type="file"
						class="form-control" id="file" name="f1">
				</div>
				<div class="form-group">
					<label for="contents">파일:</label> <input type="file"
						class="form-control" id="file" name="f2">
				</div>
				<button type="submit" class="btn btn-default">등록</button>
			</form>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>