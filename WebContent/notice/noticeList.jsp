<%@page import="com.jhj.page.Pager"%>
<%@page import="com.jhj.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.jhj.page.MakePager"%>
<%@page import="com.jhj.notice.NoticeDAO"%>
<%@page import="com.jhj.board.BoardDAO"%>
<%@page import="com.jhj.page.Search"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<BoardDTO> ar = (List<BoardDTO>)request.getAttribute("list");
	Pager pager = (Pager)request.getAttribute("pager");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../temp/bootstrap.jsp"%>
</head>
<body>
	<jsp:include page="../temp/header.jsp"></jsp:include>

	<div class="container-fluid" align="center">
		<div class="row">
			<h1 style="float: left;">Notice</h1>
			<form class="form-inline" action="./noticeList.jsp"
				style="float: right;">
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
				<%
					for (BoardDTO boardDTO : ar) {
				%>
				<tr>
					<td><%=boardDTO.getNum()%></td>
					<td><a href="./noticeSelectOne.jsp?num=<%=boardDTO.getNum()%>"><%=boardDTO.getTitle()%></a></td>
					<td><%=boardDTO.getWriter()%></td>
					<td><%=boardDTO.getReg_date()%></td>
					<td><%=boardDTO.getHit()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<a href="./noticeWriteForm.jsp" style="float: left;"><button>새글
					작성</button></a>

			<ul class="pagination">
				<%
					if (pager.getCurBlock() > 1) {
				%>
				<li><a
					href="./noticeList.jsp?curPage=<%=1%>&kind=<%=pager.getSearch().getKind()%>&search=<%=pager.getSearch().getSearch()%>"><span
						class="	glyphicon glyphicon-step-backward"></span></a></li>
				<li><a
					href="./noticeList.jsp?curPage=<%=pager.getStartNum() - 1%>&kind=<%=pager.getSearch().getKind()%>&search=<%=pager.getSearch().getSearch()%>"><span
						class="glyphicon glyphicon-chevron-left"></span></a></li>
				<%
					}
					for (int i = pager.getStartNum(); i <= pager.getLastNum(); i++) {
				%>
				<li><a
					href="./noticeList.jsp?curPage=<%=i%>
					<%-- &kind=<%=pager.getSearch().getKind()%>&search=<%=pager.getSearch().getSearch()%> --%>
					"><%=i%></a></li>
				<%
					}
					if (pager.getCurBlock() < pager.getTotalBlock()) {
				%>
				<li><a
					href="./noticeList.jsp?curPage=<%=pager.getLastNum() + 1%>&kind=<%=pager.getSearch().getKind()%>&search=<%=pager.getSearch().getSearch()%>"><span
						class="glyphicon glyphicon-chevron-right"></span></a></li>
				<li><a
					href="./noticeList.jsp?curPage=<%=pager.getTotalPage()%>&kind=<%=pager.getSearch().getKind()%>&search=<%=pager.getSearch().getSearch()%>"><span
						class="glyphicon glyphicon-step-forward"></span></a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<jsp:include page="../temp/footer.jsp"></jsp:include>
</body>
</html>