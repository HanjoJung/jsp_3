<%@page import="com.jhj.board.BoardDTO"%>
<%@page import="com.jhj.notice.NoticeDTO"%>
<%@page import="com.jhj.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    int num = Integer.parseInt(request.getParameter("num"));
    NoticeDAO dao = new NoticeDAO();
    BoardDTO dto = dao.selectOne(num);
    %>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>Bootstrap Theme Company Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../../../../../../../temp/bootstrap.jsp" %>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">

<%@ include file="../../../../../../../temp/header.jsp" %>


	<div class="container-fluid">
		<div class="row">
			<form action="./noticeUpdateProcess.jsp">
				<p>
					번호 : <input type="text" name="num" value="<%=dto.getNum() %>"
						readonly="readonly">
				</p>
				<p>
					제목 : <input type="text" name="title" value="<%=dto.getTitle() %>">
				</p>
				<p>
					내용 : <input type="text" name="contents"
						value="<%=dto.getContents() %>">
				</p>
				<p>
					작성자 : <input type="text" name="writer"
						value="<%=dto.getWriter() %>">
				</p>
				<p>
					작성날자 : <input type="text" name="reg_dete"
						value="<%=dto.getReg_date() %>" readonly="readonly">
				</p>
				<p>
					조회수 : <input type="text" name="hit" value="<%=dto.getHit() %>"
						readonly="readonly">
				</p>
				<button>완료</button>
			</form>
		</div>
	</div>
<%@ include file="../../../../../../../temp/footer.jsp" %>
</body>
</html>