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
<title>Join SOL!</title>
<link href="/resources/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="/resources/style_login.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/resources/js/memjs.js" charset="utf-8"></script>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
    <script src="/resources/js/bootstrap.js"></script>
    <div id="container">
        <ul class="ul_cont">
            <li>
                <div class="background">
                    <img src="/resources/img/background_page.png" style="width:659px; height:750px " />
                </div>
            </li>
            <li>
                <div class="create_cont">
                    <div class="create_box">
                        <img class="logo" src="img/sol_logo_128pix.png" alt="logo" /><br />
                        
                        <form action="join" method="post" name="reg_frm" >
                        	<input type="text" name="id" placeholder="아이디" /><br />
                        	<input type="password" name="pw" placeholder="비밀번호" /><br />
                        	<input type="password" name="pw_chk" placeholder="비밀번호확인" /><br />
                        	<input type="text" name="name" placeholder="이름" /><br />
                        	<select name="tel1">
                            	<option value="010" selected>010</option>
                            	<option value="011">011</option>
                            	<option value="016">016</option>
                            	<option value="017">017</option>
                            	<option value="019">019</option>
                         	</select> 
                            - <input type="tel" name="tel2" placeholder="전화번호2" /> - <input type="tel" name="tel3" placeholder="전화번호3" /><br/>
                        	<input type="button" onclick="infoConfirm()" class="join_btn" value="가입하기" />
                        </form>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</body>
</html>