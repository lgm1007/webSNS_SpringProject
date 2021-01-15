<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome to SOL</title>
<link href="/sns_pjt/resources/style_login.css?after" type="text/css" rel="stylesheet" />
</head>
<body>
    
	<div id="container">
        <ul class="ul_cont">
            <li>
                <div class="background">
                    <img src="/sns_pjt/resources/img/background_page.png" style="max-width: 100%; height: 100%;" alt="background"/>
                </div>
            </li>
            <li>
                <div class="log_in_cont">
                    <div class="log_in_box">
                        <img class="logo" src="/sns_pjt/resources/img/sol_logo_128pix.png" alt="logo" /><br />
                        <form action="login" method="post" id="login_form">
                            <input type="text" name="id" placeholder="아이디" /><br />
                            <input type="password" name="pw" placeholder="비밀번호" /><br />
                            <input type="submit" class="login_btn" value="로그인" />
                        </form>
                        <div class="btn_wrap">
                            <a href="join_page" class="sign_btn">회원가입</a>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</body>
</html>