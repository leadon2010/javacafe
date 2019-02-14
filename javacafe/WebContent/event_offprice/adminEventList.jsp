<%@page import="jdbc.EventOffpriceDO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="members.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>이벤트 화면</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
</head>

<jsp:useBean id="eventdo" class="jdbc.EventOffpriceDO" />
<jsp:setProperty name="eventdo" property="*" />
<jsp:useBean id="eventdao" class="jdbc.EventOffpriceDAO" />

<body>
	<div class="container">
		<!-- header page -->
		<header>
			<%@ include file="../common/header.jsp"%>
		</header>
		<!-- menu page -->
		<%-- <nav>
			<%
				EventOffpriceDO userno = (EventOffpriceDO) session.getAttribute("userno");
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
		</nav> --%>
		
		<!-- article page -->
		<article>
			<!--  start page -->
			<div align="center">
				<form>
					<h3>이벤트(adminEventList)</h3>
					
					<table border="1">
						<tr>
							<th>이벤트명</th>
							<th>이벤트 시작일</th>
							<th>이벤트 종료일</th>
							<th>해당 카테고리</th>
							<th>할인율</th>
						</tr>
						
						<c:forEach items="datas" var="event">
						<tr>
							<td><a
								href="../jdbc/EventServlet?action=adminEventList&event_name=${eventdo.event_name}">${eventdo.event_name}</a></td>
							<td>${event.start_date}</td>
							<td>${event.End_date}</td>
							<td>${event.Prod_category}</td>
							<td>${event.Off_pct}</td>
						</tr>
						</c:forEach>
						
						<tr>
							<td><input type="button"><a href="../jdbc/EventServlet?action=adminEventRegister">등록</td>
							<td><input type="button"><a href="../jdbc/EventServlet?action=adminEventCorrect">수정</td>
							<td><input type="button"><a href="../jdbc/EventServlet?action=adminEventDelete">삭제</td>
						</tr>
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
