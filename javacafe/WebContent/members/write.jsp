<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>index page</title>
    <link rel="stylesheet" href="../css/common.css" type="text/css" media="screen" />

    <style type="text/css">
        #title {
            height: 16;
            font-family: '돋움';
            font-size: 12;
            text-align: center;
        }
    </style>

    <script src="../ckeditor/ckeditor.js"></script>

</head>

<jsp:useBean id="datas" class="bbs.BBSDAO" />
<jsp:useBean id="data" class="bbs.BBS" />
<jsp:setProperty name="data" property="*" />

<body>

    <br>
    <b>
        <font size="6" color="gray">문의게시판</font>
    </b>
    <br>

    <form name="writeFrm" method="post" action="../members/BBSServlet">
        <input type="hidden" name="action" value="insert">
        <input type="hidden" name="prod_no" value="${param.prod_no}">
        <table>
            <tr>
                <td id="title">작성자</td>
                <td><input name="user_no" type="text" size="70" maxlength="100" value="${sessionScope.userno.user_no}"
                        readonly="readonly" /></td>
            </tr>
            <tr>
                <td id="title">
                    제 목
                </td>
                <td>
                    <input name="title" type="text" size="70" maxlength="100" value="" />
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
                    <input type="reset" value="작성취소">
                    <input type="submit" value="등록">
                    <input type="button" value="목록"
                        onclick="window.location='../boards/BBSServlet?action=list&prod_no=${param.prod_no}'">
                </td>
            </tr>
        </table>
    </form>
    <myTag:paging paging="${paging}" jsfunc="dolist" />

    <script>
        CKEDITOR.replace("contents", {
            filebrowserUploadUrl: '../ckeditor/fileUpload.jsp',
            customConfig: '../ckeditor/config.js'
        });


        function dolist(page) {
            location.href = "../members/BBSServlet?action=list&page=" + page + "&prod_no=" + "${param.prod_no}";
        }

        document.querySelector('[name="writeFrm"]').addEventListener('submit', return_check);
        //document.querySelector('input[name="title"]').value = 'test';
        //CKEDITOR.instances.contents.setData('value');

        function return_check(e) {
            e.preventDefault();

            // document.getElementById("contents").value;
            // var data = CKEDITOR.instances.contents.getData();
            // if (data == '') {
            //     alert("input editor..");
            //     return false;
            // }
            //return true;

            //let writeFrm = document.querySelector('[name="writeFrm"]');
            let frmData = new FormData(e.target);
            frmData.append('title', CKEDITOR.instances.contents.getData());

            for (var value of frmData.values()) {
                console.log(value);
            }

            //frmData = {
            //    'action': 'insert',
            //    'prod_no': 'P12345'
            //}

            fetch('../members/BBSServlet', {
                    method: 'post',
                    //headers: {
                    //    'Content-type': 'application/x-www-form-urlencoded'
                    //},
                    body: frmData
                })
                //.then(resolve => resolve.json())
                .then(result => console.log(result))
                .catch(error => console.error(error));

            // let xhtp = new XMLHttpRequest();
            // xhtp.open('post', '../members/BBSServlet');
            // xhtp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
            // xhtp.send(frmData);
            // xhtp.onload = function() {
            // 	console.log(xhtp.responseText)
            // }
        }
    </script>

</body>

</html>