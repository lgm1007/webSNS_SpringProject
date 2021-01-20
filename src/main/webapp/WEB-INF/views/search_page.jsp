<%@page import="com.mycomp.sns_pjt.dto.MDto"%>
<%@page import="com.mycomp.sns_pjt.dao.FDao"%>
<%@page import="com.mycomp.sns_pjt.dao.MDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>search Page</title>
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
		MDao mDao = new MDao();
		FDao fDao = new FDao();
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
	
	<!-- Search Result -->
	<div id="div_wrap">
		<div class="sch_div">
			<div class="sch_usercont" id="userid">
				<c:forEach items="${memberSearch}" var="mDtos">
					<% 
						MDto mDto = (MDto)pageContext.findAttribute("mDtos");
						if(!(mDto.getId().equals(sid))) {
					%>
					
					<!-- 검색한 유저들 팔로우/언팔로우 비동기 스크립트 -->
					<script type="text/javascript" charset="utf-8">
					var Followrequest = new XMLHttpRequest();
                	var Unfollowrequest = new XMLHttpRequest();
                	
                	function insertFollow<%=mDto.getId()%>() {
            			Followrequest.open("Post","./FollowRegisterServlet?follow="+ encodeURIComponent(document.getElementById("followID<%=mDto.getId()%>").value) +
            								 "&follower="+ encodeURIComponent(document.getElementById("followerID<%=mDto.getId()%>").value), true);
            			
            			Followrequest.onreadystatechange = FollowProcess<%=mDto.getId()%>;
            			Followrequest.send(null);
            		}
                	function FollowProcess<%=mDto.getId()%>() {
                		if(Followrequest.readyState == 4 && Followrequest.status == 200) {
            				var getWrite = Followrequest.responseText;
            				if(getWrite == 0) {
            					alert("팔로우를 실패하였습니다! (DB에러)");
            				} 
            				else {
            					document.getElementById("fl_btn<%=mDto.getId()%>").style.display = "none";
            	  				document.getElementById("ufl_btn<%=mDto.getId()%>").style.display = "block";
            				}
            			}
                	}
                	
                	function doUnFollow<%=mDto.getId()%>() {
            			Unfollowrequest.open("Post","./UnfollowServlet?follow="+ encodeURIComponent(document.getElementById("unfollowID<%=mDto.getId()%>").value) +
            					 "&follower="+ encodeURIComponent(document.getElementById("unfollowerID<%=mDto.getId()%>").value), true);
            			Unfollowrequest.onreadystatechange = UnfollowProcess<%=mDto.getId()%>;
            			Unfollowrequest.send(null);
            		}
                	function UnfollowProcess<%=mDto.getId()%>() {
                		if(Unfollowrequest.readyState == 4 && Unfollowrequest.status == 200) {
            				var getWrite = Unfollowrequest.responseText;
            				if(getWrite == 0) {
            					alert("팔로우를 실패하였습니다! (DB에러)");
            				}
            				else {
            					document.getElementById("ufl_btn<%=mDto.getId()%>").style.display = "none";
            	 				document.getElementById("fl_btn<%=mDto.getId()%>").style.display = "block";
            				}
            			}
                	}
					</script>
					
					<table class="mem_table">
						<tr>
							<td>
								<!-- 메인 페이지의 게시글 작성자, 검색에서 나오는 유저는 자신이 나오지 않으므로 바로 다른 유저의 프로필로 이동 -->
								<form action="others_page" method="post" class="srh_user_frm">
									<input type="hidden" value="${mDtos.id}" name="userId" />
									<input type="submit" class="sch_user_id" value="${mDtos.id}" />
								</form>
								<p class="sch_user_name">${mDtos.name}</p>
							</td>
							<td>
								<div class="btn_wrap">
									<input type="hidden" id="followerID<%=mDto.getId()%>" value="<%=sid%>" name="follower_session" /> 
									<input type="hidden" id="followID<%=mDto.getId()%>" value="<%=mDto.getId()%>" name="follow_mem" />
									<button type="button" class="fl_btn" id="fl_btn<%=mDto.getId()%>" onclick="insertFollow<%=mDto.getId()%>()">팔로우</button>

									<input type="hidden" id="unfollowerID<%=mDto.getId()%>" value="<%=sid%>" name="unfollower_session" /> 
									<input type="hidden" id="unfollowID<%=mDto.getId()%>" value="<%=mDto.getId()%>" name="unfollow_mem" />
									<button type="button" class="ufl_btn" id="ufl_btn<%=mDto.getId()%>" onclick="doUnFollow<%=mDto.getId()%>()">언팔로우</button>
									
									<% boolean checkF = fDao.checkIFollowU(sid, mDto.getId()); %>
									<!-- 팔로우 언팔로우 상황에 따라 버튼 display -->
									<script type="text/javascript" charset="utf-8">
									if(<%=checkF%>==false) {
										document.getElementById("ufl_btn<%=mDto.getId()%>").style.display = "none";
										document.getElementById("fl_btn<%=mDto.getId()%>").style.display = "block";
									}
									else {
										document.getElementById("fl_btn<%=mDto.getId()%>").style.display = "none";
										document.getElementById("ufl_btn<%=mDto.getId()%>").style.display = "block";
									}
									</script>
								</div>
							</td>
						</tr>
					</table>
				<%	}	%>
				</c:forEach>
			</div>
		</div>
	</div>
	
</body>
</html>