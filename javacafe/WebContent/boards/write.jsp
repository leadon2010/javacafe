<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>index page</title>
<link rel="stylesheet" href="../css/common.css" type="text/css"
	media="screen" />
	
	 <style type="text/css">
        #title{
            height : 16;
            font-family :'돋움';
            font-size : 12;
            text-align :center;
        }
    </style>	

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
</script>	

</head>

<jsp:useBean id="datas" class="bbs.BBSDAO" />
<jsp:useBean id="data" class="bbs.BBS" />
<jsp:setProperty name="data" property="*" />

<body>

			<br>
    <b><font size="6" color="gray">문의게시판</font></b>
    <br>

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
<form method="post" action="BBSServlet">
<input type="hidden" name ="action" value="insert">
<table>
  <tr> 
   <td>
    <table width="700" border="3" bordercolor="lightgray" align="center">
    <tr>		
             </td>    			
             <td id="title">작성자</td>
             <td><input name="user_no" type="text" size="70" maxlength="100" value="${sessionScope.userno.user_no}" readonly="readonly"/></td>            
        </tr>
            <tr>
            <td id="title">
                제 목
            </td>
            <td>
                <input name="title" type="text" size="70" maxlength="100" value=""/>
            </td>        
        </tr>        
        	  <tr>
            <td id="title">
               비 밀 글
            </td>
            <td>
                <input type="checkbox" name="password_yn" value="y" />
            </td>        
        </tr>        
        <tr>
            <td id="title">
                내 용
            </td>
            <td>
                <textarea name="contents" cols="72" rows="20"></textarea>                             
            </td>        
        </tr>
        <!-- <tr>
            <td id="title">
                파일첨부
            </td>
            <td>
                <input type="file" name="board_file" />
            </td>    
        </tr> -->
 
        <tr align="center" valign="middle">
            <td colspan="5">
                <input type="reset" value="작성취소" >
                <input type="submit" value="등록" >
                <input type="button" value="목록" onclick="window.location='BBSServlet?action=list&bbsnum=${bbs.bbsnum}'">            
            </td>
        </tr>
</table>
</td>
</tr>
 </table>
 </form>
</article>

		<!-- footer page -->
		<footer><%@ include file="../common/footer.jsp"%></footer>

	</div>

</body>
</html>