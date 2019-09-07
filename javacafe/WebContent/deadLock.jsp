<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="common.My"%>
<%@ page import="common.Deadlock" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>java class import</title>
</head>
<body>
	<%
		My my = new My();
		Deadlock.main();
		
	%>
	<p>my:<%=my.getText()%></p>
</body>
</html>