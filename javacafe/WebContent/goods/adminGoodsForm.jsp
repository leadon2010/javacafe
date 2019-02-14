<%@page import="jdbc.GoodsDO"%>
<%@page import="jdbc.GoodsDAO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>goodsForm.jsp(상품 상세화면)</title>
<link rel="stylesheet" href="../common/members.css" type="text/css"
	media="screen" />

<script src="../ckeditor/ckeditor.js"></script>
<script>
	window.onload = function() {
		CKEDITOR.replace("prod_content", {
			filebrowserUploadUrl : '../ckeditor/fileUpload.jsp',
			customConfig : '../ckeditor/config.js'
		});
	}
	function return_check() {
		// document.getElementById("contents").value;
		var data = CKEDITOR.instances.contents.getData();
		//console(data);
		if (data == '') {
			alert("input editor..");
			return false;
		}
		return true;
	}
	function file_open() {
		window.open("upload.jsp", "upload", "width=300 height=200 left=300 top=30");
	}
</script>
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
				<h3>adminGoodsForm</h3>
				<form action="../members/memberControl.jsp" method="post">
					<input type="hidden" name="action" value="updateGoods"> <input
						type="hidden" name="user_no" value="${userno.user_no}"> <input
						type="hidden" name="prod_no" value="${goods.prod_no}" />
					<table>
						<tr>
							<td align="center">상품명</td>
							<td><input type="text" name="prod_name"
								value="${goods.prod_name}" /></td>
						</tr>
						<tr>
							<td align="center">상품이미지</td>
							<td><input type="text" name="prod_image"
								value="${goods.prod_image}" /> <input type="button" value="파일첨부"
								onclick="file_open()"><br></td>
						</tr>
						<tr>
							<td align="center">상품설명</td>
							<td><textarea name="prod_content" cols=70 rows=15>${goods.prod_content}</textarea></td>
						</tr>
						<tr>
							<td align="center">상품재고량</td>
							<td><input type="text" name="onhand_qty"
								value="${goods.onhand_qty}" /></td>
						</tr>
						<tr>
							<td align="center">상품판매가</td>
							<td><input type="text" name="prod_price"
								value="${goods.prod_price}" /></td>
						</tr>
						<tr>
							<td align="center">상품할인가</td>
							<td><input type="text" name="off_price"
								value="${goods.off_price}" /></td>
						</tr>

						<tr>
							<td align="center">상품카테고리</td>
							<td><select name="prod_category">
									<c:forEach items="${rs.rows}" var="gds">
										<option value="${gds.category_id}">
											${gds.category_name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td align="center">상품사용여부</td>
							<td><input type="text" name="useyn" value="${goods.useyn}" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="변경" /></td>
						</tr>
						<!-- 주문페이지로 이동 -->
					</table>
				</form>
			</div>
		</article>

		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>

	</div>
</body>
</html>