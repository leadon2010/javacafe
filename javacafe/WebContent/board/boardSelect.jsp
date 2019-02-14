<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>boardSelect</title>
</head>
<body>
	<h3>상세보기</h3>
	제목:<%=request.getParameter("title")%><br>
	내용:<%=request.getParameter("contents")%><br>
	첨부파일:
	<a href="BoardFileDownload?filename=<%=request.getParameter("attach1")%>"><%=request.getParameter("attach1")%></a>
	<br>
</body>
</html>