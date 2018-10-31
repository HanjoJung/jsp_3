<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<!-- Theme Made By www.w3schools.com - No Copyright -->
<title>Bootstrap Theme Company Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:import url="../../../temp/bootstrap.jsp" />
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">
	<c:import url="../../../temp/header.jsp" />

	<div class="container-fluid">
		<div class="row">
			<h1>${board.toUpperCase()}</h1>
		</div>
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
					<td>${dto.getNum()}</td>
					<td>${dto.getTitle()}</td>
					<td>${dto.getWriter()}</td>
					<td>${dto.getReg_date()}</td>
					<td>${dto.getHit()}</td>
				</tr>
				<tr>
					<td colspan="5" align="center">내용</td>
				</tr>
				<tr>
					<td colspan="5" align="center">${dto.getContents()}</td>
				</tr>
				<tr>
					<td colspan="5"><c:forEach items="${list}" var="fileDTO">
							<h3 style="display: inline-block;">
								<a href="../upload/${fileDTO.fname}"> <img
									style="max-width: 50px; max-height: 50px;" alt=""
									src="../upload/${fileDTO.fname}"></a>
							</h3>
						</c:forEach></td>
				</tr>

			</table>
			<a href="./${board}List.do"><button>목록으로</button></a> 
			<a href="./${board}Update.do?num=${dto.num}"><button>수정</button></a> 
			<a href="./${board}Delete.do?num=${dto.num}"><button>삭제</button></a>
			<c:if test="${board ne 'notice'}">
			<a href="./${board}REply.do?num=${dto.num}"><button>답글</button></a>
			</c:if>
		</div>
	</div>
	<c:import url="../../../temp/footer.jsp" />

</body>
</html>
