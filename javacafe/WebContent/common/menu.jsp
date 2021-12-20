<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul>
	<!-- 손님화면메뉴 -->
	<c:if test="${empty sessionScope.userno}">
		<li>회원등급:손님</li>
		<li><a href="../goods/GoodsServlet?action=goodsList">홈화면</a></li>
		<li><a href="../members/login.jsp">로그인</a></li>
		<li><a href="../members/userRegister.jsp">회원가입</a></li>
	</c:if>

	<!-- 일반회원메뉴 -->
	<c:if test="${!empty sessionScope.userno}">
		<li>${sessionScope.userno.name}(${sessionScope.userno.grade})</li>
		<li><a href="../goods/GoodsServlet?action=goodsList">홈화면</a></li>
		<li><a href="../members/MemberServlet?action=cartlist">장바구니보기</a></li>
		<li><a href="../members/MemberServlet?action=orderlist">주문내역보기</a></li>
		<li><a href="../boards/BBSServlet?action=list">상품문의게시판</a></li>
		<li><a href="../members/MemberServlet.jsp?action=logout">로그아웃</a></li>
		<li>&nbsp;</li>
		<li><a href="../members/MemberServlet?action=mypage">My Page</a></li>
	</c:if>

	<!-- 관리자메뉴 -->
	<c:if test="${sessionScope.userno.grade=='R'}">
		<li>:::회원관리:::</li>
		<li><a href="../members/MemberServlet?action=find">회원조회</a></li>
		<li><a href="../members/MemberServlet?action=select">회원전체리스트</a></li>
		<li>&nbsp;</li>
		<li>:::상품관리:::</li>
		<li><a href="../goods/goods_form.jsp">상품조회</a></li>
		<li><a href="../goods/GoodsServlet?action=goodsList">상품리스트</a></li>
		<li><a href="../goods/GoodsServlet?action=adminGoodsRegisterForm">상품등록</a></li>
		<li><a href="../goods/GoodsServlet?action=adminGoodsList">관리자상품list</a></li>
		<li><a href="../goods/GoodsServlet?action=adminCategory">상품Category관리</a></li>
		<!-- <li><a href="../goods/GoodsServlet?action=selectAll">상품list</a></li> -->
		<li>&nbsp;</li>
		<li>↓↓호섭작업메뉴↓↓</li>
		<li><a href="../goods/GoodsServlet?action=goodsConfirm">주문확인</a></li>
		<li><a href="../goods/GoodsServlet?action=cartCheck">장바구니</a></li>
		<li><a href="../members/cartForm.jsp">장바구니담기</a></li>
		<li>&nbsp;</li>
	</c:if>

</ul>