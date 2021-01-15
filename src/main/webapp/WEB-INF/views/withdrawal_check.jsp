<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Withdrawal check</title>
<link href="/sns_pjt/resources/css/bootstrap.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/style_main.css?after" type="text/css" rel="stylesheet" />
<style type="text/css">
	    input[type=text] {
            color: #908d8d;
            font-size: 11px;
        }

        input[type=password] {
            color: #908d8d;
            font-size: 11px;
            width: 300px;
            height: 30px;
            text-align: center;
            margin-top: 10px;
            margin-left: auto;
            margin-right: auto;
        }

        input[type=submit], input[type=reset] {
            margin-top: 20px;
            margin-left: auto;
            margin-right: auto;
            background-color: #fafafa;
            font-size: 16px;
            font-weight: bold;
            border-style: none;
            border-radius: 4px;
            border: 0.5px solid #e8e8e8;
            padding: 4px 10px 4px 10px;
            cursor: pointer;
        }
        
        input[type=submit]:hover, input[type=reset]:hover, .go_out:hover {
        	border: 0.5px solid #000000;
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
                <li class="head_logo"><a href="main_page.jsp"><img src="/sns_pjt/resources/img/sol_logo_40px.png" alt="logo" /></a></li>
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
    
    <!-- Form wrap -->
    <div id="write_div_wrap">
        <div id="goout_form_wrap">
            <form class="goout_formtag" action="withdrawal" method="post" name="formtag">
                <h4><b>회원탈퇴를 위해 비밀번호를 확인합니다.</b></h4>
                <input type="password" placeholder="비밀번호를 입력하세요" name="re_pw" /><br/>
                <input type="password" placeholder="비밀번호를 재입력하세요" name="re_pw_chk" /><br/>
                <input type="submit" value="입력" /> <input type="reset" value="취소" /> 
            </form>
        </div>
    </div>
</body>
</html>