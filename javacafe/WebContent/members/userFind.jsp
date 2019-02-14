<%@page import="members.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>User Find Form</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
</head>

<jsp:useBean id="usrdo" class="jdbc.MembersDO" />
<jsp:setProperty name="usrdo" property="*" />

<body>
	<div class="container">
		<!-- header page -->
		<header>
			<%@ include file="../common/header.jsp"%>
		</header>
		<!-- menu page -->
		<nav>
			<%@ include file="../common/menu.jsp"%>
		</nav>
		<!-- article page -->
		<article>
			<%
				//String a = session.getAttribute("usrdo").toString();
				MembersDO a = (MembersDO) request.getAttribute("usrdo");
				//out.println("Hello  " + a.getUser_no() + "<br>");
			%>
			<h1>User Find Form(userFind)</h1>
			<form action="memberControl.jsp" method="post">
				<input type="hidden" name="action" value="find">
				<table border="1" style="with: 50%">
					<tr>
						<td>사용자 아이디</td>
						<td><input type="text" name="user_no" /></td>
					</tr>
					<tr>
						<td>성명</td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="phone" /></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" name="address1" /></td>
					</tr>
					<tr>
						<td>생년월일시작</td>
						<td><input type="text" name="birth" /></td>
					</tr>
					<tr>
						<td>생년월일종료</td>
						<td><input type="text" name="birth" /></td>
					</tr>
				</table>
				<input type="hidden" name="address2">
				<!-- comment for line-->
				<input type="hidden" name="reg_date">
				<!-- comment for line-->
				<input type="hidden" name="out_date">
				<!-- comment for line-->
				<input type="hidden" name="grade">
				<!-- comment for line-->
				<input type="submit" value="Modify">
			</form>
		</article>
		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>
	</div>
</body>
</html>