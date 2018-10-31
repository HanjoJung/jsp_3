<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../../../temp/bootstrap.jsp"></c:import>
</head>
<body>
	<c:import url="../../../temp/header.jsp"></c:import>

	<div class="container-fluid">
		<div class="row">
			<h1>MEMBER JOIN</h1>
		</div>
		<div class="row">
			<form action="memberjoin.do" method="post"
				enctype="multipart/form-data">
				<div class="form-group">
					<label for="title">ID:</label> <input type="text"
						class="form-control" placeholder="Enter id" name="id">
				</div>
				<div class="form-group">
					<label for="contents">PASSWORD:</label> <input type="password"
						class="form-control" placeholder="Enter password" name="pw">
				</div>
				<div class="form-group">
					<label for="contents">PASSWORD:</label> <input type="password"
						class="form-control" placeholder="Enter password" name="pw">
				</div>
				<div class="form-group">
					<label for="contents">이름:</label> <input type="text"
						class="form-control" placeholder="Enter name" name="name"></input>
				</div>
				<div class="form-group">
					<label for="contents">E-mail:</label> <input type="email"
						class="form-control" placeholder="Enter e-mail" name="email"></input>
				</div>
				<div class="form-group">
					선생님 <input type="radio" class="" name="kind" value="T"></input> 학생
					<input type="radio" class="" name="kind" value="S"></input>
				</div>
				<div class="form-group">
					<p>
						1학년 <input type="radio" class="" name="grade" value="1"></input>
						1반 <input type="radio" class="" name="ban" value="1"></input>
					</p>
					<p>
						2학년 <input type="radio" class="" name="grade" value="2"></input>
						2반 <input type="radio" class="" name="ban" value="2"></input>
					</p>
					<p>
						2학년 <input type="radio" class="" name="grade" value="3"></input>
						2반 <input type="radio" class="" name="ban" value="3"></input>
					</p>
				</div>
				<div class="form-group">
					<label for="contents">프로필 사진:</label> <input type="file"
						class="form-control" id="file" name="f2">
				</div>
				<button type="submit" class="btn btn-default">등록</button>
			</form>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>