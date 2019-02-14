<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="members.MembersDO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
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
			<%
				MembersDO a = (MembersDO) request.getAttribute("usrdo");
			%>
			<div align="center">
				<h3>홈화면(home)</h3>
				<form action="memberControl.jsp" method="post">
					<input type="hidden" name="action" value="updateUserInfo">
					<table border="1">
						<tr>
							<th colspan="2">사용자 정보</th>
						</tr>
						<tr>
							<td>사용자 아이디</td>
							<td><input type="text" name="user_no" readonly="readonly"
								value=<%=a.getUser_no()%> /></td>
						</tr>
						<tr>
							<td>패스워드</td>
							<td><input type="password" name="password"
								value=<%=a.getPassword()%> /></td>
						</tr>
						<tr>
							<td>성명</td>
							<td><input type="text" name="name" value="<%=a.getName()%>" /></td>
						</tr>
						<tr>
							<td>이메일</td>
							<td><input type="text" name="email" value=<%=a.getEmail()%> /></td>
						</tr>
						<tr>
							<td>전화번호</td>
							<td><input type="text" name="phone"
								value="<%=a.getPhone()%>" /></td>
						</tr>
						<tr>
							<td>주소</td>
							<td><input type="text" name="address1"
								value="<%=a.getAddress1()%>" /></td>
						</tr>
						<tr>
							<td>생년월일</td>
							<td><input type="date" name="birth" value=<%=a.getBirth()%> /></td>
						</tr>
						<tr>
							<td>회원등급</td>
							<td><input type="text" name="grade" readonly="readonly"
								value="<%=a.getGrade()%>" />
					</table>
					<!--  -->
					<input type="submit" value="회원정보변경">
				</form>
			</div>
			<c:if test="${userno.getGrade() =='R'}">
				<div align="center">
					<table>
						<tr>
							<th>카테고리</th>
							<th>카테고리설명</th>
						</tr>
						<sql:query var="rs" dataSource="jdbc/oracle_jsp">
						select category_id, category_name, category_desc from category
						</sql:query>
						<c:forEach var="row" items="${rs.rows}">
							<tr>
								<td>${row.category_name}</td>
								<td>${row.category_desc}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</article>
		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>
	</div>
</body>
</html>
