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
    <table style="display: none;">
        <tr id="clone">
            <td width="73"></td>
            <td align="left"></td>
            <td width="73"></td>
            <td width="164"></td>
            <td width="58"></td>
        </tr>
    </table>

    <script>
        CKEDITOR.replace("contents", {
            filebrowserUploadUrl: '../ckeditor/fileUpload.jsp',
            customConfig: '../ckeditor/config.js'
        });


        function dolist(page) {
            location.href = "../members/BBSServlet?action=list&page=" + page + "&prod_no=" + "${param.prod_no}";
        }

        document.querySelector('[name="writeFrm"]').addEventListener('submit', return_check);

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
            let frmData = new FormData();
            frmData.append('action', e.target.action.value);
            frmData.append('prod_no', e.target.prod_no.value);
            frmData.append('user_no', e.target.user_no.value);
            frmData.append('title', e.target.title.value);
            frmData.append('password_yn', e.target.password_yn.value);
            frmData.append('contents', CKEDITOR.instances.contents.getData());

            for (var value of frmData.values()) {
                console.log(value);
            }

            fetch('../members/BBSServlet', {
                    method: 'post',
                    //headers: {
                    //    'Content-type': 'application/x-www-form-urlencoded'
                    //},
                    body: frmData
                })
                .then(resolve => resolve.json())
                .then(result => show_result(result))
                .catch(error => console.error(error));

        }

        function show_result(obj) {
            console.log(obj);
            var targetBody = document.querySelector('#reply_list>tbody');

            var cloneTr = document.getElementById('clone').cloneNode(true);

            console.log(cloneTr)
            cloneTr.children[0].textContent = obj.bbsnum;
            cloneTr.children[1].textContent = obj.title;
            cloneTr.children[2].textContent = obj.user_no;
            cloneTr.children[3].textContent = obj.reg_date;
            cloneTr.children[4].textContent = obj.readcount;
            targetBody.append(cloneTr);
        }
    </script>

</body>

</html>