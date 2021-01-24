<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User Setting Page</title>
<link href="/sns_pjt/resources/css/bootstrap.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/style_main.css?after" type="text/css" rel="stylesheet" />
<style>
        input[type=text] {
            color: #908d8d;
            font-size: 11px;
        }

        input[type=tel] {
            color: #908d8d;
            font-size: 11px;
        }

        input[type=password] {
            color: #908d8d;
            font-size: 11px;
            width: 200px;
            height: 30px;
        }

        input[name=update_pw] {
            margin-left: 6%;
        }

        input[name=update_pw_chk] {
            margin-left: 2%;
        }

        input[type=button], input[type=reset] {
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

       input[type=button]:hover, input[type=reset]:hover {
           border: 0.5px solid #000000;
       }
</style>
<script type="text/javascript" src="/sns_pjt/resources/js/memjs.js" charset="utf-8"></script>
</head>
<body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
    <script src="/sns_pjt/resources/js/bootstrap.js"></script>
    <script src="/sns_pjt/resources/Search.js" type="text/javascript"></script>
    <!-- Btn EventFunction -->
    <script>
        function edit_password() {
            document.getElementById("edit_pw").readOnly = false;
            document.getElementById("edit_pw2").readOnly = false;
            document.getElementById("edit_name").readOnly = true;
            document.getElementById("edit_tel1").readOnly = true;
            document.getElementById("edit_tel2").readOnly = true;
            document.getElementById("edit_tel3").readOnly = true;
        };
        function edit_etc() {
            document.getElementById("edit_pw").readOnly = true;
            document.getElementById("edit_pw2").readOnly = true;
            document.getElementById("edit_name").readOnly = false;
            document.getElementById("edit_tel1").readOnly = false;
            document.getElementById("edit_tel2").readOnly = false;
            document.getElementById("edit_tel3").readOnly = false;
        };
    </script>
    
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
                    <input type="search" id="keyword" placeholder="검색" onkeypress="if (event.keyCode == 13) { goSearch();}" style="text-align :center; font-size:13px; color:#cacaca; border:0.7px solid #cacaca;" />
                </form>
            </div>
        </div>
    </div>

    <div id="write_div_wrap">
        <div id="userEditform_wrap">
            <form class="user_update_formtag" action="update_member" method="post" name="formtag">
                <!--이름과 폰번호는 비어있으면 원래의 값으로 Update, 비밀번호는 필수입력하기-->
                <h4 class="modify_inf">회원정보 변경</h4>
                <p class="modify_inf2">변경할 항목을 작성해주세요</p>
                <div class="user_update_btns"><button type="button" class="update_etc_check_btn" onclick="edit_etc()">회원정보 변경</button>
                <button type="button" class="update_pw_check_btn" onclick="edit_password()">비밀번호 변경</button></div> <br/>
                이름: <input type="text" id="edit_name" class="update_name" name="update_name" value="기존 이름" /><br /><br />
                비밀번호: <input type="password" id="edit_pw" name="update_pw" readonly="true" value="기존 비밀번호" /><br/><br/>
                비밀번호확인: <input type="password" id="edit_pw2" name="update_pw_chk" readonly="true" value="기존 비밀번호" /><br /><br />
                휴대폰 번호:
                <select name="tel1" id="edit_tel1" class="tel">
                    <option value="010" selected>010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="019">019</option>
                </select>
                 - <input type="tel" id="edit_tel2" class="tel" name="tel2" value="휴대폰번호 2"/> - <input type="tel" id="edit_tel3" class="tel" name="tel3" value="휴대폰번호 3"/>
                <br />
                <input type="button" onclick="infoUpdate()" value="수정" /> <input type="reset" value="취소" /> <br/><br />
                <a href="withdrawal_check" class="go_out">탈퇴하기</a>
            </form>
        </div>
    </div>
	
</body>
</html>