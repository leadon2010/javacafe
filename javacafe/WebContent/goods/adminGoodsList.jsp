<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>

<head>
	<title>goodsList.jsp(상품 목록 화면)</title>
	<link rel="stylesheet" href="../common/members.css" type="text/css" media="screen" />
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="//code.jquery.com/jquery.min.js"></script>

	<script>
		//$(function () {
		//	$(document).tooltip();
		//});
	</script>

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

		ul.menu li a,
		.dropbtn {
			display: inline-block;
			color: black;
			text-align: center;
			padding: 14px 16px;
			text-decoration: none;
		}

		ul.menu li a:hover,
		.dropdown:hover .dropbtn {
			background-color: green;
		}

		ul.menu li {
			float: left;
		}

		.prod_code {
			display: inline-block;
			width: 25%;
		}

		.prod_name {
			display: inline-block;
			width: 20%;
		}

		.prod_onhand {
			display: inline-block;
			width: 15%;
		}

		.prod_price {
			display: inline-block;
			width: 15%;
		}
	</style>
</head>


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
			<div align="center">
				<h3>adminGoodsList</h3>
				<div style="background-color: lime;">

					<ul class="menu">
						<li><a href="../goods/GoodsServlet?action=adminGoodsList">전체</a>&nbsp;&nbsp;&nbsp;</li>
						<c:forEach items="${category}" var="gds">
							<li><a href="../goods/GoodsServlet?action=adminGoodsList&prod_category=${gds.category_id}"
									title="${gds.category_desc}">
									${gds.category_name}</a>
							</li>
							<%-- 
							<li><a
							href="../goods/GoodsServlet?action=adminGoodsList&prod_category=BEANS">원두</a>
							&nbsp;&nbsp;&nbsp;</li>
							<li><a
							href="../goods/GoodsServlet?action=adminGoodsList&prod_category=DUTCH">더치원액</a>
							&nbsp;&nbsp;&nbsp;</li>
							<li><a
							href="../goods/GoodsServlet?action=adminGoodsList&prod_category=BEVERAGE">음료류</a>
							&nbsp;&nbsp;&nbsp;</li>
							<li><a
							href="../goods/GoodsServlet?action=adminGoodsList&prod_category=FOODS">푸드</a>
							&nbsp;&nbsp;&nbsp;</li>
							<li><a
							href="../goods/GoodsServlet?action=adminGoodsList&prod_category=CUPS" title="고품격 폴란드 수입 찻잔">찻잔</a>
							&nbsp;&nbsp;&nbsp;</li>
							 --%>
						</c:forEach>
					</ul>
				</div>
				<div style="border: 1px solid green;">
					<span class='prod_code'>상품코드</span>
					<span class='prod_name'>상품명</span>
					<!-- <span>${goods.prod_content}</span>    -->
					<span class='prod_onhand'>재고</span>
					<span class='prod_price'>상품가격</span>
				</div>
				<c:forEach items="${datas}" var="goods">
					<div style="cursor: pointer; border: 1px solid green;"
						onclick="location.href='../goods/GoodsServlet?action=adminGoodsForm&prod_no=${goods.prod_no}'">
						<span class='prod_code'>${goods.prod_no} <img src="../upload/${goods.prod_image}"
								width="100"></span>
						<span class='prod_name'>${goods.prod_name} </span>
						<!-- <span>${goods.prod_content}</span>    -->
						<span class='prod_onhand'>${goods.onhand_qty} </span>
						<span class='prod_price'>${goods.prod_price}</span>
					</div>
				</c:forEach>
			</div>
		</article>

		<!-- footer page -->
		<footer>
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</footer>

	</div>

</body>