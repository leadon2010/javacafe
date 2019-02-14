<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>index page</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
</head>

<jsp:useBean id="usrdo" class="jdbc.MembersDO" />
<jsp:setProperty name="usrdo" property="*" />
<jsp:useBean id="usrdao" class="jdbc.MembersDAO" />

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
			<!--  start page -->
			<form>
				<table border="1">
					<tr>
						<td>회원아이디</td>
						<td>성명</td>
						<td>이메일</td>
						<td>전화번호</td>
						<td>주소</td>
					</tr>
					<%
						for (MembersDO mdo : usrdao.selectAll()) {
					%>
					<tr>
						<td><a href="memberControl.jsp?action=search&user_no=<%=mdo.getUser_no()%>"><%=mdo.getUser_no()%></a></td>
						<td><%=mdo.getName()%></td>
						<td><%=mdo.getEmail()%></td>
						<td><%=mdo.getPhone()%></td>
						<td><%=mdo.getAddress1()%></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<!--  end   page -->
		</article>

		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>

	</div>

</body>
</html>
