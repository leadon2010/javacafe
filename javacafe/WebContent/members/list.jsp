<%@page import="bbs.BBS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>index page</title>
<link rel="stylesheet" href="../css/common.css" type="text/css"
	media="screen" />
	
	<style>
.bbs-table {
    width: 100%;
    margin: 0.7em 0 0 0;
}
.bbs-table th {
    color: #92B91C;
    border-top: 1px solid #92B91C;
    border-bottom: 1px solid #92B91C;
}

.bbs-table td {
    padding-top: 3px;
    padding-bottom: 3px;
    border-bottom: 1px solid silver;
    line-height: 1.45;
}
.bbs-table td a {
    color: #555;
    text-decoration: none;
}
.bbs-table td a:hover {
    color:#555;
    text-decoration: underline;
}

</style>
    
<script>
	function dolist(page){
		location.href="../members/BBSServlet?action=list&page="+page+"&prod_no="+"${param.prod_no}"; 
	} 
</script>

</head>

<body>	
			
	<br>
    <b><font size="6" color="gray">문의게시판</font></b>
    <br>
			
			
			<table width="100%" cellpadding="0" cellspacing="0" border="0" class ="bbs-table" >
			
					<tr>					
					<th style="width: 60px;">번호</th>				
					<th>제목</th>					
					<th>작성자</th>
					<th style="width: 84px;">작성일</th>
					<th style="width: 60px;">조회수</th>	
					
					<!-- <th style="width: 60px;">NO</th>
    				<th>TITLE</th>
    				<th style="width: 84px;">DATE</th>
    				<th style="width: 60px;">HIT</th>	 -->		
				    </tr>				

								
				<c:forEach items="${datas}" var="b">
				
				<tr>						
					<td width="73">${b.bbsnum }</td>
					<td align="left"><c:if test="${b.ref_lev > 0}">
                        <c:forEach begin="1" end="${b.ref_lev}">
                            &nbsp;&nbsp; <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
                        </c:forEach>
                        RE : 
                    </c:if>
                                        
				                                      
					<c:if test="${b.password_yn == 'y'}" >
                    <img src="../boards/자물쇠.JPG">
	                     <c:if test="${sessionScope.userno.user_no == b.user_no}">
	                     <a href="../members/BBSServlet?action=selectOne&bbsnum=${b.bbsnum}">${b.title}</a>
	                    </c:if>
	                     <c:if test="${sessionScope.userno.user_no != b.user_no}">
	                     ${b.title}
	                    </c:if>
					</c:if> 
                     <c:if test="${b.password_yn != 'y' }">        
                    <a href="../members/BBSServlet?action=selectOne&bbsnum=${b.bbsnum}">${b.title}</a>
                  	</c:if>                    
                    </td>
					<td width="73">${b.user_no }</td>
					<td width="164">${b.reg_date}</td> 
					<td width="58">${b.readcount}</td>
					
				</tr>									
						
				 
				 </c:forEach>					
								
			 </table>
   <c:if test="${not empty sessionScope.userno}">
			<table align="center">
				<tr>
					<td><input type=button value="글쓰기"
						onclick="window.location='write.jsp?prod_no=${param.prod_no}'">
					</td>															
				</tr>			
			</table>  
	</c:if>
			<myTag:paging paging="${paging}" jsfunc="dolist"/>				

</body>
</html>