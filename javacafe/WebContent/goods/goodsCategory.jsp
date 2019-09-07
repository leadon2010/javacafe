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
					<form action="GoodsServlet" id="frm1"
						onsubmit="return validateForm()" method="get">
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
									<td>${category.category_id }</td>
									<td><input type="text" value="${category.category_name }" required></td>
									<td><input type="text" value="${category.category_desc }" required></td>
									<td><input type="button" value="Change" onclick="updateCategory(this)"></td>
								</tr>
							</c:forEach>
							<tr>
								<td><input type="text" name="category_id" id="category_id" onchange="updateCase(this)"></td>
								<td><input type="text" name="category_name" id="category_name"></td>
								<td><input type="text" name="category_desc" id="category_desc"></td>
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
		function updateCase(elm) {
			console.log(elm.value)
			document.getElementById("category_id").value = elm.value
					.toUpperCase();
		}
		function validateForm() {
			console.log("valudateForm");
			if (!document.getElementById("category_id").value
					|| !document.getElementById("category_name").value
					|| !document.getElementById("category_desc").value) {
				alert("input value");
				return false;
			}

			return true;
		}
		function updateCategory(obj) {
			var $id = obj.parentElement.children[0].text;
			var $name = obj.parentElement.parentElement.children[1].children[0].value;
			var $desc = obj.parentElement.parentElement.children[2].children[0].value;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					console.log(this.responseText);
				}
			}
			xhttp.open("POST", "GoodsServlet", true);
			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhttp.send("action=updateCategory&category_id=" + $id + "&category_name=" + $name
					+ "&category_desc=" + $desc);
		}
	</script>
</body>
</html>