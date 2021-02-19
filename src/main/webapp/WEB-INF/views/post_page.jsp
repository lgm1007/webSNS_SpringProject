<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Posting in SOL</title>
<link href="/sns_pjt/resources/css/bootstrap.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/style_main.css?after" type="text/css" rel="stylesheet" />
<style type="text/css">
	input[type=submit], input[type=reset] {
            margin-top: 5%;
            background-color: #fafafa;
            font-size: 16px;
            font-weight: bold;
            border-style: none;
            border-radius: 4px;
            border: 0.5px solid #e8e8e8;
            padding: 4px 10px 4px 10px;
            cursor: pointer;
        }
        input[type=submit]:hover, input[type=reset]:hover {
            border:0.5px solid #000000;
        }
</style>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
	<script src="/sns_pjt/resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="/sns_pjt/resources/Search.js" charset="utf-8"></script>

  <!-- Header -->
	<div id="header">
        <div class="headbar_div">
            <ul class="headbar">
                <li class="head_logo"><a href="home_page"><img src="/sns_pjt/resources/img/sol_logo_40px.png" alt="logo" /></a></li>
                <li class="head_icon">
                    <a href="like_page" class="icon_like"><img src="/sns_pjt/resources/img/like.png" alt="like" /></a>
                    <a href="profile_page" class="icon_me"><img src="/sns_pjt/resources/img/me.PNG" alt="me" /></a>
                    <a href="post_page" class="icon_pic"><img src="/sns_pjt/resources/img/pic.PNG" alt="pic" /></a>
                </li>
            </ul>
            <div class="head_input">
                <form action="search_page" name="hd_frm" method="post">
                    <input type="search" id="keyword" name="srch" placeholder="검색" onkeypress="if (event.keyCode == 13) { goSearch();}"  style="text-align :center; font-size:13px; color:#cacaca; border:0.7px solid #cacaca;" />
                </form>
            </div>
        </div>
    </div>
    <!-- Form wrap -->
    <div id="write_div_wrap">
        <div id="form_wrap">
            <form class="formtag" action="post_writing" method="post" name="formtag" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td class="inf_pic_wrap" ><p class="inf_pic">사진</p></td>
                    </tr>
                </table>
                <div id="fileArea">
                    <input type="file" multiple="multiple" class="upfile" name="upladimg" accept="image/jpeg, image/png, image/gif" />
                </div>
                <p class="upfile_info">< 5MB이상 크기의 사진은 올릴 수 없습니다. ></p>
                <p class="inf_content">내용</p>
                <textarea cols="50" rows="4" class="textarea" placeholder="최대 200자까지 입력할 수 있습니다" name="content"></textarea>
                <br />
                <br />
                <input type="submit" value="글쓰기" /> <input type="reset" value="취소" />
            </form>
        </div>
    </div>

</body>
</html>