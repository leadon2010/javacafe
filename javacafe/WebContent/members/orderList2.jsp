<%@page import="members.OrdersDO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="members.MembersDO"%>
<%@page import="members.CartsDO"%>
<%@page import="members.CartDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="goods.GoodsDO"%>
<%@page import="goods.GoodsDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>cart list</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
</head>

<jsp:useBean id="oda" class="members.OrdersDAO" />
<jsp:setProperty property="*" name="oda" />
<jsp:useBean id="od" class="members.OrdersDO" />

<sql:query var="rs" dataSource="jdbc/oracle_jsp">
select category_id, category_name, category_desc from category
</sql:query>
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
			<table border="1">
				<c:forEach items="${c1list}" var="l" varStatus="st">
					<tr>
						<th colspan="4" align="center">주문정보 <br>주문번호:${l.order_no}
						</th>
					</tr>
					<tr>
						<td>배송주소</td>
						<td colspan="3"><input type="text" name="deliver_addr" />${l.deliver_addr}</td>
					</tr>
					<tr>
						<td>배송요청정보</td>
						<td colspan="3"><textarea cols="100" rows="10">${l.delever_reg}</textarea></td>
					</tr>
					<%
						//List<HashMap<String, Object>> c2list = oda.selectAll("");
						//request.setAttribute("c2list", c2list);
					%>
					<tr>
						<td colspan="4" align="center">
							<h3>주문상세</h3>
						</td>
					</tr>
					<tr>
						<td>상품이름</td>
						<td>상품단가</td>
						<td>상품수량</td>
						<td>합계금액</td>
					</tr>
					<c:forEach items="${c2list}" var="i" varStatus="st">
						<tr>
							<td>${i.prod_name}</td>
							<td align="right">${i.sale_price}</td>
							<td align="right">${i.order_qty}</td>
							<td align="right">${i.sale_price*i.order_qty}</td>
						</tr>
					</c:forEach>
				</c:forEach>
				<tr>
					<td align="center" colspan="4"><input type="submit"
						value="결재하기" /></td>
				</tr>
			</table>
		</article>
		<!-- footer page -->
		<footer>
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</footer>
	</div>
</body>
</html>
