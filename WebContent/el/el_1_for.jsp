<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("start", 1);
	request.setAttribute("end", 5);
	ArrayList<Integer> ar = new ArrayList<>();
	ar.add(100);
	ar.add(200);
	ar.add(300);
	request.setAttribute("list", ar);

	Map<String, String> map = new HashMap<>();
	map.put("f1", "name1");
	map.put("f2", "name2");
	map.put("f3", "name3");
	request.setAttribute("m", map);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		for (int i = 0; i <= 10; i = i + 2) {
	%>
	<h1><%=i%></h1>
	<%
		}
	%>
	<c:forEach var="i" begin="0" end="10" step="2">
		<h1>${i}</h1>
	</c:forEach>
	<hr>
	<c:forEach var="i" begin="${start}" end="${end}">
		<h1>${i}</h1>
	</c:forEach>
	<hr>
	<%-- 
	ERROR
	<c:forEach var="i" begin="${end}" end="${start}" step="-1">
	<h1>${i}</h1>
	</c:forEach>
	 --%>
	<%
		for (int i = 0; i < ar.size(); i++) {
		}
	%>
	<%
		for (Integer num : ar) {
		}
	%>
	<c:forEach items="${list}" var="num" varStatus="v">
		<h3>${num}</h3>
		<h3>${v.count}</h3>
		<h3>${v.index}</h3>
		<h3>${v.first}</h3>
		<h3>${v.last}</h3>
		<h3>${v.begin}</h3>
		<h3>${v.end}</h3>
		<h3>${v.step}</h3>
	</c:forEach>
	<!-- 
	count : 1부터 순서 
	index : 0부터 순서
	first : 첫번째 여부(boolean)
	last  : 마지막 여부(boolean)
	begin : 시작 번호
	end   : 끝번호
	step  : 증가값
	-->
	<hr>
	<c:forEach items="${m}" var="n">
		<h3>${n}</h3>
		<h3>${n.value}</h3>
		<h3>${n.key}</h3>
	</c:forEach>

</body>
</html>