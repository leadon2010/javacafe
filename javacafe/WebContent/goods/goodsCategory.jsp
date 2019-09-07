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
	// $(document).ready(function() {
	// 	$('#submit').on("submit", function() {
	// 		$.ajax({
	// 			url : "GoodsServlet",
	// 			data : {
	// 				id : $('#category_id').val(),
	// 				name : $('#category_name').val(),
	// 				desc : $('#category_desc').val(),
	// 				action : 'insertCategory'
	// 			},
	// 			success : function() {
	// 				location.reload();
	// 			}
	// 		});
	// 	})

	// });
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
				<div style="background-color: lime;">goodsCategory</div>
				<div>
					<form action="GoodsServlet" id="frm1" onsubmit="return validateForm()" method="get">
						<input type="hidden" name="action" value="insertCategory">
						<table border=1>
							<tr>
								<th>Category</th>
								<th>CategoryName</th>
								<th>Description</th>
								<th>Add/Change</th>
							</tr>
							<c:forEach items="${datas}" var="category">
								<tr>
									<td><input type="text" value="${category.category_id }"></td>
									<td><input type="text" value="${category.category_name }"></td>
									<td><input type="text" value="${category.category_desc }"></td>
									<td><input type="button" value="Change"
										onclick="updateCategory(this)"></td>
								</tr>
							</c:forEach>
							<tr>
								<td><input type="text" id="category_id"></td>
								<td><input type="text" id="category_name"></td>
								<td><input type="text" id="category_desc"></td>
								<td align="center"><input id="submit" type="submit" value="Add">
							</tr>
						</table>
					</form>
				</div>
			</div>
		</article>
		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>
	</div>
	<script>
		function updateCategory(obj) {
			var $id = obj.parentElement.parentElement.children[0].children[0].value;
			var $name = obj.parentElement.parentElement.children[1].children[0].value;
			var $desc = obj.parentElement.parentElement.children[2].children[0].value;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					console.log(this.responseText);
				}
			}
			xhttp.open("POST", "GoodsServlet", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("action=updateCategory&id=" + $id + "&name=" + $name + "&desc=" + $desc);
		}
	</script>
</body>
</html>