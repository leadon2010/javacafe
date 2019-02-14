<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>boardInsertForm</title>
<script src="../ckeditor/ckeditor.js"></script>
<script>
	window.onload = function() {
		CKEDITOR.replace("contents", {
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
	
	function file_open(){
		window.open("upload.jsp", "upload", "width=300 height=200 left=300 top=30");
	}
</script>

</head>
<body>
	<%=application.getRealPath("/upload")%><br>
	<form name="frm" onsubmit="return_check()" method="post" action="boardSelect.jsp">
		제목<input name="title" id="title" type="text"><br>
		내용<textarea name="contents" id="contents" rows="10" cols="60"></textarea><br>
		<input type="text" name="attach1" />
		<input type="button" value="파일첨부" onclick="file_open()" /> <br>
		<input type="submit" value="저장">
	</form>
</body>
</html>