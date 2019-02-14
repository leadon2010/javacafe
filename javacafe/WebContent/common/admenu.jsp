<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>java menu</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />
</head>

<body>
	<ul>
		<!-- 손님화면메뉴 -->
		<c:if test="${empty sessionScope.userno}">
			<li>회원등급:손님</li>
			<li><a href="../members/main.jsp">홈화면</a></li>
			<li><a href="../members/login.jsp">로그인</a></li>
			<li><a href="../members/userRegister.jsp">회원가입</a></li>
		</c:if>

		<!-- 일반회원메뉴 -->
		<c:if test="${!empty sessionScope.userno}">
			<li>회원등급:${sessionScope.userno.grade}</li>
			<li><a href="../members/MemberServ?action=logout">로그아웃</a></li>
			<li><a href="../members/cartForm.jsp">장바구니담기</a></li>
			<li><a href="../members/MemberServ?action=cartlist">장바구니보기</a></li>
			<li><a href="../members/MemberServ?action=orderlist">주문보기</a></li>
		</c:if>

		<!-- 관리자메뉴 -->
		<c:if test="${sessionScope.userno.grade=='R'}">
			<li><a href="../members/MemberServ?action=find">회원조회</a></li>
			<li><a href="../members/MemberServ?action=select">회원전체리스트</a></li>
			<hr>
			<li><a href="../members/MemberServ?action=find">장바구니test</a></li>
			<li><a href="../members/MemberServ?action=find">주문정호test</a></li>
			<hr>
			<li><a href="../goods/GoodsServlet?action=selectAll">상품list</a></li>
			<li><a href="../goods/goods_form.jsp">상품조회</a></li>
			<li><a href="../products/prodRegister.jsp">상품등록</a></li>
			<hr>
		</c:if>
		<hr>
		<li><a href="../boards/list.jsp">게시판</a></li>



		<!-- 호섭작업메뉴 -->
		<hr>
		<hr>
		<li>↓↓호섭작업메뉴↓↓</li>
		<li><a href="../goods/GoodsServlet?action=goodsList">상품list</a></li>
		<li><a href="../goods/GoodsServlet?action=goodsConfirm">주문확인</a></li>
		<li><a href="../goods/GoodsServlet?action=cartCheck">장바구니</a></li>
		<li><a href="../goods/GoodsServlet?action=adminGoodsRegister">상품등록</a></li>
		<hr>
		<li><a href="../goods/prodAll.jsp">관리자상품리스트</a></li>
	</ul>
</body>
</html>