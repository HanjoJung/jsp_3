<%@page import="java.util.List"%>
<%@page import="com.jhj.file.FileDTO"%>
<%@page import="com.jhj.file.FileDAO"%>
<%@page import="com.jhj.board.BoardDTO"%>
<%@page import="com.jhj.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% 
		BoardDTO dto = (BoardDTO)request.getAttribute("dto");
		List<FileDTO> ar = (List<FileDTO>)request.getAttribute("files");
	%>
	
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
		<div class="row" align="center">
			<table class="table table-bordered table-hover"
				style="max-width: 1500px">
				<tr>
					<td style="width: 10%">번호</td>
					<td style="width: 50%">제목</td>
					<td style="width: 15%">작성자</td>
					<td style="width: 15%">작성날짜</td>
					<td style="width: 10%">조회수</td>
				</tr>
				<tr>
					<td><%=dto.getNum()%></td>
					<td><%=dto.getTitle()%></td>
					<td><%=dto.getWriter()%></td>
					<td><%=dto.getReg_date()%></td>
					<td><%=dto.getHit()%></td>
				</tr>
				<tr>
					<td colspan="5" align="center">내용</td>
				</tr>
				<tr>
					<td colspan="5" align="center"><%=dto.getContents()%></td>
				</tr>
				<tr>
					<td colspan="5">
				<%for(FileDTO fileDTO : ar){ %>
						<h3 style="display: inline-block;"><a href="../upload/<%=fileDTO.getFname()%>">
						<img style="max-width: 50px; max-height: 50px;" alt="" src="../upload/<%=fileDTO.getFname()%>"></a></h3>
					<%} %>
					</td>
				</tr>
				
			</table>
			<a href="./noticeList.do"><button>목록으로</button></a> 
			<a href="./noticeUpdateForm.do?num=<%=dto.getNum()%>"><button>수정</button></a>
			<a href="./noticeDeleteProcess.do?num=<%=dto.getNum()%>"><button>삭제</button></a>
		</div>
	</div>
	<%@ include file="../../../../../../../temp/footer.jsp"%>

</body>
</html>
