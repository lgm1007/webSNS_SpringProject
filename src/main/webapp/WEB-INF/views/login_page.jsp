<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(session.getAttribute("id") != null) { %>
	<jsp:forward page="home_page.jsp"></jsp:forward>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome to SOL</title>
<link href="/resources/style_login.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="container">
        <ul class="ul_cont">
            <li>
                <div class="background">
                    <img src="img/background_page.png" style="max-width: 100%; height: 100%;"/>
                </div>
            </li>
            <li>
                <div class="log_in_cont">
                    <div class="log_in_box">
                        <img class="logo" src="img/sol_logo_128pix.png" alt="logo" /><br />
                        <form action="login_check.jsp" method="post" id="login_form">
                            <input type="text" name="id" placeholder="���̵�" /><br />
                            <input type="password" name="pw" placeholder="��й�ȣ" /><br />
                            <input type="submit" class="login_btn" value="�α���" />
                        </form>
                        <div class="btn_wrap">
                            <a href="join_page.jsp" class="sign_btn">ȸ������</a>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</body>
</html>