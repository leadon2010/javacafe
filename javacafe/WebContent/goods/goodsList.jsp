<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>goodsList.jsp(상품 목록 화면)</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>

<style>
ul.menu {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: white;
}

ul.menu li {
	float: left;
}

ul.menu li a, .dropbtn {
	display: inline-block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

ul.menu li a:hover, .dropdown:hover .dropbtn {
	background-color: green;
}

ul.menu li {
	float: left;
}
</style>
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
				<h3>goodsList</h3>
				<div style="background-color: lime;">

					<ul class="menu">
						<li><a href="../goods/GoodsServlet?action=goodsList">전체</a>
							&nbsp;&nbsp;&nbsp;</li>
						<li><a
							href="../goods/GoodsServlet?action=goodsList&prod_category=BEANS">원두</a>
							&nbsp;&nbsp;&nbsp;</li>
						<li><a
							href="../goods/GoodsServlet?action=goodsList&prod_category=DUTCH">더치원액</a>
							&nbsp;&nbsp;&nbsp;</li>
						<li><a
							href="../goods/GoodsServlet?action=goodsList&prod_category=BEVERAGE">음료류</a>
							&nbsp;&nbsp;&nbsp;</li>
						<li><a
							href="../goods/GoodsServlet?action=goodsList&prod_category=FOODS">푸드</a>
							&nbsp;&nbsp;&nbsp;</li>
						<li><a
							href="../goods/GoodsServlet?action=goodsList&prod_category=CUPS">찻잔</a>
							&nbsp;&nbsp;&nbsp;</li>
					</ul>
				</div>
				<c:forEach items="${datas}" var="goods">
					<div
						onclick="location.href='../goods/GoodsServlet?action=goodsForm&prod_no=${goods.prod_no}'"
						style="cursor: pointer">
						<span>${goods.prod_no} <img
							src="../images/${goods.prod_image}" width="250" height="250"></span>
						<span>${goods.prod_name} </span>
						<!-- <span>${goods.prod_content}</span>    -->
						
						<!--     -->
						<span>${goods.onhand_qty} </span>
						<!--     -->
						<span>${goods.prod_price}</span>
						<!--     -->
					</div>
				</c:forEach>
			</div>
		</article>
		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>

	</div>

</body>