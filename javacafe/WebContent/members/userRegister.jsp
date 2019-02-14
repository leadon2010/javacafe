<%@page import="members.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>User Registration Form</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
</head>
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
			<div align="center">
				<h1>User Register Form(userRegister)</h1>
				<form action="memberControl.jsp" method="post">
					<input type="hidden" name="action" value="insert">
					<table border="1" style="with: 50%">
						<tr>
							<td>사용자 아이디</td>
							<td><input type="text" name="user_no" value="" /></td>
						</tr>
						<tr>
							<td>패스워드</td>
							<td><input type="password" name="password" value="" /></td>
						</tr>
						<tr>
							<td>성명</td>
							<td><input type="text" name="name" value="" /></td>
						</tr>
						<tr>
							<td>이메일</td>
							<td><input type="text" name="email" value="" /></td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td><input type="text" name="phone" value="" /></td>
						</tr>
						<tr>
							<td>주소</td>
							<td><input type="text" name="address1" value="" /></td>
						</tr>
						<tr>
							<td>생년월일</td>
							<td><input type="text" name="birth" value="19800131" /></td>
						</tr>
					</table>
					<input type="hidden" name="address2">
					<!-- comment for line-->
					<input type="hidden" name="reg_date">
					<!-- comment for line-->
					<input type="hidden" name="out_date">
					<!-- comment for line-->
					<input type="hidden" name="grade" value="A" />
					<!-- comment for line-->
					<input type="submit" value="회원등록">
				</form>
			</div>
		</article>
		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>
	</div>
</body>
</html>