<%@page import="com.jhj.notice.NoticeDTO"%>
<%@page import="com.jhj.file.FileDAO"%>
<%@page import="com.jhj.file.FileDTO"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.jhj.notice.NoticeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getServletContext().getRealPath("upload");
	int max = 1024 * 1024 * 10;
	MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8", new DefaultFileRenamePolicy());
	//path 경로에 파일 업로드가 끝났습니다
	//파일의 정보
	NoticeDTO noticeDTO = new NoticeDTO();
	noticeDTO.setTitle(multi.getParameter("title"));
	noticeDTO.setWriter(multi.getParameter("writer"));
	noticeDTO.setContents(multi.getParameter("contents"));
	FileDTO f1 = new FileDTO();
	String fname = multi.getFilesystemName("f1"); //파라미터의 이름
	String oname = multi.getOriginalFileName("f1");
	f1.setFname(fname);
	f1.setOname(oname);
	
	FileDTO f2 = new FileDTO();
	fname = multi.getFilesystemName("f2"); //파라미터의 이름
	oname = multi.getOriginalFileName("f2");
	f2.setFname(fname);
	f2.setOname(oname);
	
/* 	File f = multi.getFile("f1");
	Enumeration e = multi.getFileNames();*/ //파라미터 이름들 

	NoticeDAO dao = new NoticeDAO();
	int num = dao.getNum();
	noticeDTO.setNum(num);
	int result = dao.insert(noticeDTO);
	
	f1.setNum(num);
	f2.setNum(num);
	f1.setKind("N");
	f2.setKind("N");
	
	FileDAO fileDAO = new FileDAO();
	int result1 = fileDAO.insert(f1);
	int result2 = fileDAO.insert(f2);
	
	String str = "등록 완료";
	if (result1 == 0 || result2 == 0) {
		str = "등록 실패";
	}

	//map과 같은 형태로 request 속성 추가
	request.setAttribute("message", str);
	request.setAttribute("path", "./noticeList.jsp");

	//forword
	RequestDispatcher view = request.getRequestDispatcher("../common/result.jsp");
	view.forward(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>