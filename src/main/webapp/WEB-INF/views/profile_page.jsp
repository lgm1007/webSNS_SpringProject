<%@page import="com.mycomp.sns_pjt.dto.IDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycomp.sns_pjt.dto.BDto"%>
<%@page import="com.mycomp.sns_pjt.dao.IDao"%>
<%@page import="com.mycomp.sns_pjt.dao.FDao"%>
<%@page import="com.mycomp.sns_pjt.dao.BDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
<link href="/sns_pjt/resources/style_main.css?after" type="text/css" rel="stylesheet" />
<link href="/sns_pjt/resources/css/bootstrap.css?after" type="text/css" rel="stylesheet" />
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="/sns_pjt/resources/js/bootstrap.js" type="text/javascript"></script>
	<script src="/sns_pjt/resources/Search.js" type="text/javascript" charset="utf-8"></script>
	<%
		String sid = (String)session.getAttribute("sid");
		String sname = (String)session.getAttribute("sname");
		BDao bDao = new BDao();
		FDao fDao = new FDao();
		IDao iDao = new IDao();
	%>
	
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
   	
   	<!-- Profile -->
   	<div id="profile">
   		<div id="profile_wrap">
   			<div class="profilePicture"><img src="/sns_pjt/resources/img/prof.png" alt="profileImg" /></div>
   			<div class="profileComment">
   				<div class="profileComment_top">
   					<div class="profile_id"><%=sid%></div>
   					<a href="setting_page" class="profile_set"><img src="/sns_pjt/resources/img/profset.png"/></a>
   					<a href="logout" class="logout_btn">로그아웃</a>
   				</div><br/>
   				<div class="wrap_pro_list">
   					<ul class="list_of_fol">
   						<li class="follower_btn_li"><button type="button" class="btn_follower">팔로워</button></li>
   						<li class="numberFollower"><p>${followerCount}</p></li>
   						<li><button type="button" class="btn_follow">팔로우</button></li>
                        <li class="numberFollow"><p>${followCount}</p></li>
   					</ul>
   				</div><br />
   				<div class="profile_name"><%=sname%></div>
   			</div>
   		</div>
   	</div>
	
	<!-- 내 게시글 -->
	<div id="userPostContainer">
		<ul id="userPost">
			<c:forEach items="${boardList}" var="bDtos">
				<%
					BDto bDto = (BDto)pageContext.findAttribute("bDtos");
					ArrayList<IDto> iDtos = iDao.iSelect(bDto.getBd_key());
					pageContext.setAttribute("filename", iDtos.get(0).getFileName());
				%>
				<li>
					<form action="posted_board" method="get">
						<input type="hidden" name="bdKey" value="${bDtos.bd_key}"/>
                		<input type="hidden" name="memID" value="<%=sid%>" />
                		<button type="submit" class="mypost"><img src="<spring:url value='/img/${filename}'/>" /></button>
					</form>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>