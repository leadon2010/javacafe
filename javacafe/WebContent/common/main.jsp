<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>index page</title>

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
		<article id='article'>
		</article>

		<!-- footer page -->
		<footer>
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</footer>

	</div>
	<script>
		$('#article').load('welcome.html');
	</script>
</body>

</html>