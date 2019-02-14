<%@page import="jdbc.GoodsDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Product Register</title>
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
</script>	

</head>

<%-- <jsp:useBean id="gdsdo" class="jdbc.GoodsDO" />
<jsp:setProperty name="gdsdo" property="*" /> --%>

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
			<%
				GoodsDO a = (GoodsDO) request.getAttribute("gdsdo");
			%>
			<form>
				<table border="1">
					<tr>
						<td>상품번호</td>
						<td><input type="text" name="prod_no" value=<%=a.getProd_no()%> /></td>
					</tr>
					<tr>
						<td>상품명</td>
						<td><input type="text" name="prod_name" value="<%=a.getProd_name()%>" /></td>
					</tr>
					<tr>
						<td>상품설명</td>
						<td><textarea name="prod_content" ><%=a.getProd_content()%></textarea></td>
					</tr>
					<tr>
						<td>상품재고량</td>
						<td><input type="text" name="onhand_qty" value="<%=a.getOnhand_qty()%>" /></td>
					</tr>
					<tr>
						<td>상품판매가</td>
						<td><input type="text" name="prod_price" value="<%=a.getProd_price()%>" /></td>
					</tr>
					<tr>
						<td>상품할인가</td>
						<td><input type="text" name="off_price" value="<%=a.getOff_price()%>" /></td>
					</tr>
					<tr>
						<td>상품카테고리</td>
						<td><input type="text" name="prod_category" value="<%=a.getProd_category()%>" /></td>
					</tr>
					<tr>
						<td>상품사용여부</td>
						<td><input type="text" name="useyn" value="<%=a.getUseyn()%>" /></td>
					</tr>
					<tr align="center">
						<td colspan="2"><input type="submit" value="수정"></td>
					</tr>
				</table>
			</form>
			<!--  end   page -->
		</article>

		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>

	</div>

</body>
</html>
