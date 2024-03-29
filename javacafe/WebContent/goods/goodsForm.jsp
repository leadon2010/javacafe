<%@page import="goods.GoodsDO"%>
<%@page import="goods.GoodsDAO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>goodsForm.jsp(상품 상세화면)</title>
	<link rel="stylesheet" href="../common/members.css" type="text/css" media="screen" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		function resizeIframe(fr) {
			fr.setExpression('height', ifrm.document.body.scrollHeight);
			fr.setExpression('width', ifrm.document.body.scrollWidth);
		}
	</script>
	<script src="../ckeditor/ckeditor.js"></script>
	<script>
		window.onload = function () {
			console.log('window onload');
			CKEDITOR.replace("prod_content", {
				filebrowserUploadUrl: '../ckeditor/fileUpload.jsp',
				customConfig: '../ckeditor/config.js'
			});

		}

		function return_check() {
			// document.getElementById("contents").value;
			var data = CKEDITOR.instances.contents.getData();
			console(data);
			if (data == '') {
				alert("input editor..");
				return false;
			}
			return true;
		}

		function file_open() {
			window.open("upload.jsp", "upload",
				"width=300 height=200 left=300 top=30");
		}

		function orders() {
			document.frm.action.value = "cart2orderUser";
			document.frm.submit();
		}
	</script>
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
				<h3>goodsForm</h3>
				<form name="frm" action="../members/memberControl.jsp" method="post">
					<input type="hidden" name="action" value="cart">
					<input type="hidden" name="user_no" value="${userno.user_no}">
					<input type="hidden" name="prod_no" value="${goods.prod_no}" />
					<table>
						<tr>
							<td rowspan="8"><img src="../upload/${goods.prod_image}" width="300" height="450"></td>
						</tr>
						<tr>
							<td>상품명 : ${goods.prod_name}</td>
						</tr>
						<tr>
							<td>판매가 : <input width="50" type="text" readonly="readonly" name="sales_price" value="${goods.prod_price}" /></td>
						</tr>
						<tr>
							<td>할인금액 : ${goods.off_price}</td>
						</tr>
						<tr>
							<td>재고량 : ${goods.onhand_qty}</td>
						</tr>
						<tr>
							<td id="shopProductQuantityDiv"
								class="row productQuantityDiv designSettingElement text-body ">
								<span class="text">선택수량</span>
								<input type="number" name="order_qty" id="productQuantity"
									class="designSettingElement shape" value="1" min="1" data-initialQuantity="">
							</td>
						</tr>
						<tr>
							<td><input type="submit" value="장바구니담기" /></td>
						</tr>
						<!-- 장바구니로 이동 -->
						<tr>
							<td><button type="button" onclick="orders()">주문하기</button></td>
						</tr>
						<tr>
							<td colspan="2"><textarea name='prod_content'>${goods.prod_content}</textarea></td>
						</tr>
						<!-- 주문페이지로 이동 -->
					</table>
				</form>
				<!-- <iframe id="ifrm" width="100%" height="100%" frameborder="no" scrolling="no"
					src="../members/BBSServlet?action=list&prod_no=${goods.prod_no}" marginwidth="0" marginheight="0"
					onload="resizeIframe(this)"></iframe>
				</iframe> -->
				
				<div id="reply"></div>
			</div>
		</article>

		<!-- footer page -->
		<footer>
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</footer>

	</div>
	<script>
		$("#reply").load(
			"../members/BBSServlet?action=list&prod_no=${goods.prod_no}");
	</script>
</body>

</html>