<%@page import="bbs.BBS"%>
<%@page import="bbs.BBSDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>index page</title>

<style>
        #wrap {
            width: 800px;
            margin: 0 auto 0 auto;
        }
    
        #detailBoard{
            text-align :center;
        }
        
        #title{
            height : 16;
            font-family :'돋움';
            font-size : 12;
            text-align :center;
        }
    </style>


<link rel="stylesheet" href="../css/common.css" type="text/css"
	media="screen" />

	
<!-- <script type="text/javascript">
        function changeView(value)
        {
            if(value == 0)    location.href="BBSServlet?action=${bbs.bbsnum}&page=${page}";
            else if(value == 1){
                location.href='BBSServlet?action=${bbs.bbsnum}&page=${page}';
            }
                
        }
    </script> -->
    
    
    
</head>
<body>

	
 
 		<br>
    <b><font size="6" color="gray">문의게시판</font></b>
    <br>
 
 <div id="wrap">
    <br><br>
    <div id="board">
        <table id="detailBoard" width="800" border="3" bordercolor="lightgray" align = "center">
            <tr>
                <td id="title">작성일</td>
                <td>${bbs.reg_date}</td>
            </tr>
            <tr>
                <td id="title">작성자</td>
                <td>${bbs.user_no}</td>
            </tr>
            <tr>
                <td id="title">
                    제 목
                </td>
                <td>
                    ${bbs.title}
                </td>        
            </tr>
            <tr>
                <td id="title">
                    내 용
                </td>
                <td>
                    ${bbs.contents}
                </td>        
            </tr> 
      
      <tr align="center" valign="middle">
      <td colspan="5">      	
      	<c:if test="${sessionScope.userno.user_no == bbs.user_no}">
      	<input type=button value="수정" onclick="window.location='BBSServlet?action=edit&bbsnum=${bbs.bbsnum}'">
      	<input type=button value="삭제" onclick="window.location='BBSServlet?action=delete&bbsnum=${bbs.bbsnum}'">
      	</c:if>
      	<input type=button value="답글" onclick="window.location='reply.jsp?ref=${bbs.bbsnum}&re_step=${bbs.re_step}&ref_lev=${bbs.ref_lev}'">
      	<input type="button" value="목록" onclick="window.location='../members/BBSServlet?action=list&prod_no=${bbs.prod_no}'"> 
        
        <%-- <a href="<c:url value='BBSServlet?action=delete&bbsnum=${bbs.bbsnum}'/>">삭제</a> --%>
        <%-- <a href="<c:url value='reply.jsp?ref=${bbs.bbsnum}&re_step=${bbs.re_step}&ref_lev=${bbs.ref_lev}'/>">답글</a>
        <a href="<c:url value='BBSServlet?action=list&bbsnum=${bbs.bbsnum}'/>">목록</a> --%>
        </td>
        </tr>  
        
    </div>
      </table>
      </div>
      </div>              

</body>
</html>