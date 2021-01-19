<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post Edit</title>
<link href="/sns_pjt/resources/style_main.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/css/bootstrap.css?after" type="text/css" rel="stylesheet" />
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="/sns_pjt/resources/js/bootstrap.js" type="text/javascript"></script>
    <script src="/sns_pjt/resources/Search.js" type="text/javascript" charset="utf-8"></script>
    
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
                <form action="search_page" name="hd_frm" method="get">
                    <input type="search" id="keyword" name="srch" placeholder="검색" onkeypress="if (event.keyCode == 13) { goSearch();}"  style="text-align :center; font-size:13px; color:#cacaca; border:0.7px solid #cacaca;" />
                </form>
            </div>
        </div>
   	</div>
   	
   	<div id="write_div_wrap">
   		<div id="form_wrap">
   			<form class="formtag" action="edit_action" method="post" name="formtag">
   				<table>
   					<tr>
   						<td><h3>포스트 수정 / 삭제</h3></td>
   					</tr>
   				</table>
   				<p class="inf_content">내용</p>   <textarea rows="4" cols="50" class="textarea" placeholder="최대 200자까지 입력할 수 있습니다" name="content"></textarea>
   				<input type="hidden" name="bdEditKey" value="${bdEditKey}" />
   				<br/>
   				<br/>
   				<input type="submit" class="btns" value="수정" /> <input type="reset" class="btns" value="취소" />
   			</form>
   			<br />
   			<form action="post_delete" method="post">
   				<input type="hidden" name="bdKey" value="${bdEditKey}" />
   				<button type="submit" class="btns btn_delete">글 삭제</button>
   			</form>
   		</div>
   	</div>
    
</body>
</html>