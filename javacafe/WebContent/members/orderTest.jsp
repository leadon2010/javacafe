<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>index page</title>
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
			<!--  start page -->
			<table border="1">
				<tr>
					<th colspan="4" align="center">주문정보 <br>주문번호:
					</th>
				</tr>
				<tr>
					<td>배송주소</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td>배송요청정보</td>
					<td colspan="3"><textarea cols="100" rows="10"> </textarea></td>
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
				<c:forEach items="${c2list}" var="i" varStatus="st">
					<tr>
						<td>${i.prod_name}</td>
						<td align="right">${i.sale_price}</td>
						<td align="right">${i.order_qty}</td>
						<td align="right">${i.sale_price*i.order_qty}</td>
					</tr>
				</c:forEach>
			</table>
			<!--  end   page -->
		</article>

		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>

	</div>

</body>
</html>
