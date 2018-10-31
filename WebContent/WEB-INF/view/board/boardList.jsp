<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../../../temp/bootstrap.jsp"%>
</head>
<body>
	<c:import url="../../../temp/header.jsp" />

	<div class="container-fluid" align="center">
		<div class="row">
			<h1 style="float: left;">${board.toUpperCase()}</h1>
		</div>

		<div class="row">
			<form class="form-inline" action="./${board}List.do"
				style="float: left;">
				<div class="form-group">
					<select class="form-control" id="sel1"
						style="display: inline-block;" name="kind">
						<option>title</option>
						<option>contents</option>
						<option>writer</option>
					</select> <input type="text" class="form-control" id="search"
						placeholder="Enter search" name="search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>

		<div class="row">
			<table class="table table-hover table-bordered">
				<tr>
					<td>NUM</td>
					<td>TITLE</td>
					<td>WRITER</td>
					<td>DATE</td>
					<td>HIT</td>
				</tr>
				<c:forEach items="${list}" var="boardDTO">
					<tr>
						<td>${boardDTO.num}</td>
						<td><a href="./${board}SelectOne.do?num=${boardDTO.num}">
								<c:catch var="i">
									<c:forEach begin="0" end="${boardDTO.depth-1}"> 
										->
									</c:forEach> 
								</c:catch>
						${boardDTO.title}</a></td>
						<td>${boardDTO.writer}</td>
						<td>${boardDTO.reg_date}</td>
						<td>${boardDTO.hit}</td>
					</tr>
				</c:forEach>
			</table>
			<a href="./${board}Write.do" style="float: left;"><button>새글
					작성</button></a>
		</div>
		<div class="row">
			<ul class="pagination">
				<c:if test="${pager.CurBlock gt 1}">
					<li><a
						href="./${board}List.do?curPage=1
						&kind=${pager.getSearch().getKind()}
						&search=${pager.getSearch().getSearch()}"><span
							class="	glyphicon glyphicon-step-backward"></span></a></li>
					<li><a
						href="./${board}List.do?curPage=${pager.getStartNum() - 1}
						&kind=${pager.getSearch().getKind()}
						&search=${pager.getSearch().getSearch()}"><span
							class="glyphicon glyphicon-chevron-left"></span></a></li>
				</c:if>
				<c:forEach var="i" begin="${pager.getStartNum()}" end="${pager.getLastNum()}">
					<li><a
						href="./${board}List.do?curPage=${i}
					&kind=${pager.getSearch().getKind()}
					&search=${pager.getSearch().getSearch()}
					">${i}</a></li>
				</c:forEach>
				<c:if test="${pager.CurBlock lt pager.TotalBlock}">
					<li><a
						href="./${board}List.do?curPage=${pager.getLastNum() + 1}
						&kind=${pager.getSearch().getKind()}
						&search=${pager.getSearch().getSearch()}"><span
							class="glyphicon glyphicon-chevron-right"></span></a></li>
					<li><a
						href="./${board}List.do?curPage=${pager.getTotalPage()}
						&kind=${pager.getSearch().getKind()}
						&search=${pager.getSearch().getSearch()}"><span
							class="glyphicon glyphicon-step-forward"></span></a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<jsp:include page="../../../temp/footer.jsp"></jsp:include>
</body>
</html>