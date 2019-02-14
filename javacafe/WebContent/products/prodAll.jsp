<%@page import="jdbc.GoodsDO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Product Register</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
</head>

<jsp:useBean id="gdsdo" class="jdbc.GoodsDO" />
<jsp:useBean id="gdsdao" class="jdbc.GoodsDAO" />
<jsp:setProperty name="gdsdo" property="*" />


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
						<td>상품번호</td>
						<td>상품명</td>
						<td>상품재고</td>
						<td>상품판매가</td>
						<td>상품할인가</td>
					</tr>
					<%
						for (GoodsDO gds : gdsdao.selectAll()) {
					%>
					<tr>
						<td><a href="../goods/GoodsServlet?action=selectOne&prod_no=<%=gds.getProd_no()%>"><%=gds.getProd_no()%></a></td>
						<td><%=gds.getProd_name()%></td>
						<td><%=gds.getOnhand_qty()%></td>
						<td><%=gds.getProd_price()%></td>
						<td><%=gds.getOff_price()%></td>
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
