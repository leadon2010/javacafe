<%@page import="java.util.ArrayList"%>
<%@page import="members.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>index page</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
</head>

<jsp:useBean id="usrdo" class="members.MembersDO" />
<jsp:setProperty name="usrdo" property="*" />
<jsp:useBean id="usrdao" class="members.MembersDAO" />

<body>
	<div class="container">
		<!-- header page -->
		<header>
			<%@ include file="../common/header.jsp"%>
		</header>
		<!-- menu page -->
		<nav>
			<%
				MembersDO userno = (MembersDO) session.getAttribute("userno");
			%>
			<%
				if (userno.getGrade() != "R") {
			%>
			<%@ include file="../common/menu.jsp"%>
			<%
				} else {
			%>
			<%@ include file="../common/admenu.jsp"%>
			<%
				}
			%>
		</nav>
		<!-- article page -->
		<article>
			<!--  start page -->
			<div align="center">
				<form>
					<h3>회원리스트(userAll)</h3>
					<table border="1">
						<tr>
							<th>회원아이디</th>
							<th>성명</th>
							<th>이메일</th>
							<th>전화번호</th>
							<th>주소</th>
						</tr>
						<%
							for (MembersDO mdo : usrdao.selectAll()) {
						%>
						<tr>
							<td><a
								href="memberControl.jsp?action=search&user_no=<%=mdo.getUser_no()%>"><%=mdo.getUser_no()%></a></td>
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
			</div>
			<!--  end   page -->
		</article>
		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>
	</div>
</body>
</html>
