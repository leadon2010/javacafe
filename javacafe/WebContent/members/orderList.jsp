<%@page import="members.OrdersDO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="members.MembersDO"%>
<%@page import="members.CartsDO"%>
<%@page import="members.CartDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.GoodsDO"%>
<%@page import="jdbc.GoodsDAO"%>
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
				<h3>주문리스트(orderList)</h3>
				<table border="1">
					<c:forEach items="${oslist}" var="l" varStatus="st">
						<tr>
							<th colspan="4" align="center">주문정보 <br>주문번호:${l.ord.order_no}
							</th>
						</tr>
						<tr>
							<td>배송주소</td>
							<td colspan="3">${l.ord.deliver_addr}</td>
						</tr>
						<tr>
							<td>배송요청정보</td>
							<td colspan="3">${l.ord.delever_reg}</td>
						</tr>

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
						<c:forEach items="${l.odlist}" var="i" varStatus="st">
							<tr>
								<td>${i.prod_no}</td>
								<td align="right">${i.sale_price}</td>
								<td align="right">${i.order_qty}</td>
								<td align="right">${i.sale_price*i.order_qty}</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</table>
			</div>
		</article>
		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>
	</div>
</body>
</html>
