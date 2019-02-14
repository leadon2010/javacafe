<%@page import="members.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>사용자정보</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
<script>
	function go2(user_no) {
		//alert("go");
		//document.frm.action.value = "memberControl.jsp?action=cartlistUser&user_no=" + user_no;
		//document.frm.submit();
		window.location.href = "memberControl.jsp?action=cartlistUser&user_no="
				+ user_no;
	}
	function go3(user_no) {
		//var modify = document.getElementById("orders");
		window.location.href = "memberControl.jsp?action=orderlistUser&user_no="
				+ user_no;
	}
</script>
</head>
<jsp:useBean id="usrdo" class="members.MembersDO" />
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
				MembersDO a = (MembersDO) request.getAttribute("usrdo");
			%>
			<div align="center">
				<h1>회원정보 변경(userSearch)</h1>
				<form name="frm" action="memberControl.jsp" method="post">
					<input type="hidden" name="action" value="updateUserInfo">
					<table border="1" style="with: 50%">
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
							<td><input type="text" name="email"
								value="<%=a.getEmail()%>" /></td>
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
							<td><input type="text" name="grade"
								value="<%=a.getGrade()%>" /></td>
						</tr>
					</table>
					<input type="hidden" name="address2">
					<!--  -->
					<input type="hidden" name="reg_date" value="<%=a.getReg_date()%>" />
					<!--  -->
					<input type="hidden" name="out_date" value="<%=a.getOut_date()%>" />
					<!--  -->

					<!--  -->
					<input type="submit" value="회원등급변경">
					<!--  -->
					<input type="button" value="장바구니보기" id="cart"
						onclick="go2('<%=a.getUser_no()%>')"> <input type="button"
						value="주문정보보기" id="orders" onclick="go3('<%=a.getUser_no()%>')">
				</form>
			</div>
		</article>

		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>

	</div>

</body>
</html>