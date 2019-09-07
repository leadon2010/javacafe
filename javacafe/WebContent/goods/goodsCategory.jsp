<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	
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
				<h3>adminGoodsList</h3>
				<div style="background-color: lime;"></div>
${datas}
				<table border=1>
					<tr>
						<th>Category</th>
						<th>CategoryName</th>
						<th>Description</th>
					</tr>
					<c:forEach items="${datas}" var="category">
						<tr>
							<td><input type="text" value="${category.category_id }"></td>
							<td><input type="text" value="${category.category_name }"></td>
							<td><input type="text" value="${category.category_desc }"></td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</article>
		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>

	</div>

</body>
</html>