<%@page import="jdbc.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>User Registration Form</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
</head>

<%-- <jsp:useBean id="data" class="jdbc.MembersDO" />
<jsp:setProperty name="data" property="*" /> --%>

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
			<h1>User Search Form</h1>
			<form action="memberControl.jsp" method="post">
				<input type="hidden" name="action" value="update">
				<table border="1" style="with: 50%">
					<tr>
						<td>사용자 아이디</td>
						<td><input type="text" name="user_no"
							value=<%=a.getUser_no()%> /></td>
					</tr>
					<tr>
						<td>패스워드</td>
						<td><input type="password" name="password"
							value=<%=a.getPassword()%> /></td>
					</tr>
					<tr>
						<td>성명</td>
						<td><input type="text" name="name" value=<%=a.getName()%> /></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="email"
							value=<%=a.getEmail()%> /></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="phone"
							value=<%=a.getPhone()%> /></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text" name="address1"
							value=<%=a.getAddress1()%> /></td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><input type="text" name="birth"
							value=<%=a.getBirth()%> /></td>
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