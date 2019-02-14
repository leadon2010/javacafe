<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
 <!DOCTYPE html>
<html>
<head>
<title>게시판 - 답변글</title>        
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
  	
    <br>
    <b><font size="6" color="gray">답글 작성</font></b>
    <br>
    
    <form method="post" action="BBSServlet?action=insertReply" name="boardForm">
     <input type="hidden" name="user_no" value="${param.user_no}"/> 
    <input type="hidden" name="ref" value="${param.ref}"/>
    <input type="hidden" name="ref_lev" value="${param.ref_lev+1}"/>
    <input type="hidden" name="re_step" value="${param.re_step+1}"/>
 
    <table width="700" border="3" bordercolor="lightgray" align="center">       
            <tr>
            <td id="title">
                제 목
            </td>
            <td>
                <input name="title" type="text" size="70" maxlength="100" value=""/>
            </td>        
        </tr>
              <tr>
            <!-- <td id="title">
                작성자
            </td>
            <td>
                <input name="user_no" type="text" size="70" maxlength="100" value=""/>
            </td>         -->
        </tr>
        <tr>
            <td id="title">
                내 용
            </td>
            <td>
                <textarea name="contents" cols="72" rows="20">
                </textarea>            
            </td>        
        </tr>
 
        <tr align="center" valign="middle">
            <td colspan="5">
                <input type="reset" value="작성취소" >
                <input type="submit" value="등록" onclick="window.location='.jsp'">
                <input type="button" value="목록" onclick="window.location='BBSServlet?action=list&bbsnum=${bbs.bbsnum}'">            
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