<%@page import="com.jhj.notice.NoticeDAO"%>
<%@page import="com.jhj.notice.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	NoticeDTO dto = new NoticeDTO();
	NoticeDAO dao = new NoticeDAO();

	dto.setNum(Integer.parseInt(request.getParameter("num")));
	dto.setTitle(request.getParameter("title"));
	dto.setContents(request.getParameter("contents"));
	dto.setWriter(request.getParameter("writer"));
	dto.setHit(Integer.parseInt(request.getParameter("hit")));

	int result = dao.update(dto);
	String str = "수정 완료";
	if (result == 0) {
		str = "수정 실패";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert('<%=str%>');
	location.href="./noticeList.jsp";
</script>
</head>
<body>

</body>
</html>