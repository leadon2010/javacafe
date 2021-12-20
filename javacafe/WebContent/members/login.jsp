<%@page import="members.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login Page</title>
</head>

<body>

	<div class="container">

		<!-- header page -->
		<header>
			<jsp:include page="../common/header.jsp"></jsp:include>
		</header>

		<!-- menu page -->
		<nav>
			<jsp:include page="../common/menu.jsp"></jsp:include>
		</nav>

		<!-- article page -->
		<article>
			<div align="center">
				<h1>Login Page(login)</h1>
				<form action="MemberServlet" method="post">
					<input type="hidden" name="action" value="login">
					<table border="1">
						<tr>
							<td>아이디</td>
							<td><input type="text" name="user_no" value="user1"></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password" value="1234"></td>
						</tr>
						<tr align="center">
							<td colspan="2"><input type="submit" value="로그인" width="100"></td>
						</tr>
					</table>
				</form>
				<input type="hidden" value="회원등록" onclick="location.href='userRegister.jsp'">
			</div>
		</article>

		<!-- footer page -->
		<footer>
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</footer>

	</div>

</body>

</html>