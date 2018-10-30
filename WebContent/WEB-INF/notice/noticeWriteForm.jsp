<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>Bootstrap Theme Company Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../../../../../../../temp/bootstrap.jsp"%>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">

	<%@ include file="../../../../../../../temp/header.jsp"%>


	<div class="container-fluid">
		<div class="row">
			<form action="noticeWriteProcess.do" method="post" 
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
	<%@ include file="../../../../../../../temp/footer.jsp"%>
</body>
</html>